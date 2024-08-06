package br.com.skill.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.exceptions.EmailException;
import com.application.exceptions.LoginIncorretoException;
import com.application.exceptions.ResourceNotFoundException;

import br.com.skill.DTO.AtualizarNivelDTO;
import br.com.skill.DTO.UsuarioRequestDTO;
import br.com.skill.DTO.UsuarioResponseDTO;
import br.com.skill.DTO.UsuarioSkillRequestDTO;
import br.com.skill.DTO.UsuarioSkillResponseDTO;
import br.com.skill.entity.Skill;
import br.com.skill.entity.Usuario;
import br.com.skill.entity.UsuarioSkill;
import br.com.skill.repository.SkillRepository;
import br.com.skill.repository.UsuarioRepository;
import br.com.skill.repository.UsuarioSKillRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired 
	private SkillRepository skillRepository;
	
	@Autowired
	private UsuarioSKillRepository usuarioSkillRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public List<UsuarioResponseDTO> listarUsuarios(){
		List<Usuario> usuarios = repository.findAll();
		return usuarios.stream().map((u) -> new UsuarioResponseDTO(u)).collect(Collectors.toList());
	}
	
	public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuario) throws Exception {
		if(repository.findByEmail(usuario.getEmail()).isPresent()) throw new EmailException("Email já existente no sistema!");
		Usuario usuarioSave = new Usuario();
		usuarioSave.setEmail(usuario.getEmail());
		usuarioSave.setSenha(encoder.encode(usuario.getSenha()));
		usuarioSave.setRole(usuario.getRole());
		
		return new UsuarioResponseDTO(repository.save(usuarioSave));
	}
	
	public UsuarioResponseDTO buscarPorId(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		if(usuario.isEmpty()) throw new RuntimeException("Tratar usuário não encontrado");
		return new UsuarioResponseDTO(usuario.get());
	}
	
	public UsuarioResponseDTO associarSkill(UsuarioSkillRequestDTO userSkill) {
		Optional<Usuario> usuarioOpt = repository.findById(userSkill.getUsuarioId());
		Usuario usuario = usuarioOpt.get();
		Set<UsuarioSkill> usuarioSkillList = usuario.getSkills();
		
		for(int i = 0; i< userSkill.getSkillsId().size(); i++) {
			Optional<Skill> skillOpt = skillRepository.findById(userSkill.getSkillsId().get(i));
			Skill skill = skillOpt.get();
			Optional<UsuarioSkill> relacaoExistente = usuarioSkillList.stream()
	                .filter(s -> s.getId().getSkill().getId().equals(skill.getId()))
	                .findFirst();
			if(relacaoExistente.isPresent())throw new RuntimeException("O usuário já possui a skill!");
			UsuarioSkill relacao = new UsuarioSkill(usuario,skill);
			relacao.setNivel(userSkill.getNiveis().get(i));
			usuarioSkillList.add(relacao);
		}
		usuario.setSkills(usuarioSkillList);
		
		return new UsuarioResponseDTO(repository.save(usuario));
	}
	
	public UsuarioResponseDTO autenticar(UsuarioRequestDTO login) {
		   Optional<Usuario> usuario = repository.findByEmail(login.getEmail());
		   
		    if (usuario.isPresent() && encoder.matches(login.getSenha(), usuario.get().getSenha())) {
		    	return new UsuarioResponseDTO(usuario.get());
		    }else if (usuario.isPresent() && !encoder.matches(login.getSenha(), usuario.get().getSenha())){
		    	
		    	throw new LoginIncorretoException();
		    }else {
		    	throw new ResourceNotFoundException("Usuario não encontrado!");
		    }
		}
	
	public List<UsuarioSkillResponseDTO> mostrarSkills(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		if(usuario.isEmpty()) throw new ResourceNotFoundException("O id: "+ id + "não possui um usuário correspondente.");
		return  usuario.get().getSkills().stream().map(s -> new UsuarioSkillResponseDTO(s)).collect(Collectors.toList());
	}
		
	public UsuarioSkillResponseDTO atualizarNota(AtualizarNivelDTO request){
		Optional<Usuario> usuarioOpt = repository.findById(request.getUsuarioId());
		Optional<Skill> skillOpt = skillRepository.findById(request.getSkillId());
		if(usuarioOpt.isEmpty() || skillOpt.isEmpty()) throw new ResourceNotFoundException("Não foi possível atualizar o nível.");
		
		Optional<UsuarioSkill> usuarioSkillOpt = 
				usuarioSkillRepository.findByIdUsuarioAndIdSkill(usuarioOpt.get(), skillOpt.get());
		if(usuarioSkillOpt.isEmpty()) throw new ResourceNotFoundException("O usuário não possui a skill especificada.");
		UsuarioSkill usuarioSkill = usuarioSkillOpt.get();
		usuarioSkill.setNivel(request.getNivel());
		return new UsuarioSkillResponseDTO(usuarioSkillRepository.save(usuarioSkill));
	}
	
	public ResponseEntity<String> desassociarSkill(AtualizarNivelDTO request){
		Optional<Usuario> usuarioOpt = repository.findById(request.getUsuarioId());
		Optional<Skill> skillOpt = skillRepository.findById(request.getSkillId());
		if(usuarioOpt.isEmpty() || skillOpt.isEmpty()) throw new ResourceNotFoundException("Não foi possível atualizar o nível.");
		Optional<UsuarioSkill> usuarioSkillOpt = 
				usuarioSkillRepository.findByIdUsuarioAndIdSkill(usuarioOpt.get(), skillOpt.get());
		if(usuarioSkillOpt.isEmpty()) throw new ResourceNotFoundException("O usuário não possui a skill especificada.");
		usuarioSkillRepository.deleteBySkillIdAndUsuarioId(skillOpt.get().getId(),usuarioOpt.get().getId());
		return ResponseEntity.ok().body("A skill: " + skillOpt.get().getNome()
				+ "Foi removida do usuário com sucesso.");
	}
}
