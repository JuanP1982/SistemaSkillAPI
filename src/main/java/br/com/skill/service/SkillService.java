package br.com.skill.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.application.exceptions.ResourceNotFoundException;

import br.com.skill.entity.Skill;
import br.com.skill.repository.SkillRepository;

@Service
public class SkillService {
	@Autowired
	private SkillRepository repository;
	
	@Autowired
	private SkillFotoService fotoService;
	
	public List<Skill> listarSkills(){
		return repository.findAll();
	}
	
	public Skill buscarId(Integer id){
		return repository.findById(id).orElseThrow();
	}
	
	public Skill inserirSkill(Skill skill, MultipartFile file) throws IOException {
		if(repository.findByNome(skill.getNome()) != null) throw new RuntimeException("Tratar skill já existente");
		repository.save(skill);
		if(file != null && !file.isEmpty()) {
			fotoService.inserirFoto(skill, file);
		}
		return linkImagem(skill);
	}
	
	public ResponseEntity<Skill> atualizarSkill(Integer id,MultipartFile file, Skill skill) throws IOException {
		Optional<Skill> skillOpt = repository.findById(id);
		if(!skillOpt.isPresent()) throw new RuntimeException("Skill não encontrada!");
		
		skillOpt.get().setDescricao(skill.getDescricao());
		skillOpt.get().setNome(skill.getNome());
		skillOpt.get().setUrl(skillOpt.get().getUrl());
		repository.save(skillOpt.get());
		if(file != null && !file.isEmpty()) {
			fotoService.atualizarImagem(file, skillOpt.get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(skillOpt.get());
	}
	
	public ResponseEntity<String> deletarSkill(Integer id){
		if(repository.findById(id).isEmpty()) throw new ResourceNotFoundException("Skill não encontrada.");
		fotoService.deletarFoto(id);
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("A skill foi deletada com sucesso!");
	}
	
	
	public Skill linkImagem(Skill skill) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/skills/{id}/foto")
				.buildAndExpand(skill.getId()).toUri();
		
		skill.setUrl(uri.toString());
		repository.save(skill);
		return skill;
	}
}
