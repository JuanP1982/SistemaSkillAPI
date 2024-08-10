package br.com.skill.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.skill.entity.Skill;
import br.com.skill.entity.SkillFoto;
import br.com.skill.repository.SkillFotoRepository;

@Service
public class SkillFotoService {
	@Autowired
	private SkillFotoRepository repository;
	
	public SkillFoto inserirFoto(Skill skill, MultipartFile file) throws IOException {
		SkillFoto foto = new SkillFoto();
		foto.setId(skill.getId());
		foto.setDados(file.getBytes());
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setSkill(skill);
		return repository.save(foto);
	}
	
	public SkillFoto buscar(Integer id) {
		Optional<SkillFoto> fotoOpt = repository.findById(id);
		if (!fotoOpt.isPresent()) throw new RuntimeException("Foto não encontrada para o id especificado.");
		Skill skill = new Skill();
		skill.setId(id);
		Optional<SkillFoto> foto = repository.findBySkill(skill);
		if(!foto.isPresent()) throw new RuntimeException("A foto não esta associada a skill especificada.");
		return foto.get();
	}
	
	public SkillFoto atualizarImagem(MultipartFile file, Skill skill) throws IOException {
		Optional<SkillFoto> old = repository.findById(skill.getId());
		if (old.isPresent()) {
			SkillFoto foto = new SkillFoto();
			foto.setId(skill.getId());
			foto.setDados(file.getBytes());
			foto.setNome(file.getName());
			foto.setTipo(file.getContentType());
			foto.setSkill(skill);
			return repository.save(foto);
		} else {
			return inserirFoto(skill, file);
		}
	}
		
		public void deletarFoto(Integer id) {
	        Optional<SkillFoto> old = repository.findById(id);
	        if (old.isPresent()) {
	            repository.delete(old.get());
	        } else {
	            throw new RuntimeException("Falha ao deletar a foto");
	        }
	}
}
