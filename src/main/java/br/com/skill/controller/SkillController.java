package br.com.skill.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.skill.entity.Skill;
import br.com.skill.entity.SkillFoto;
import br.com.skill.service.SkillFotoService;
import br.com.skill.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/skills")
@Tag(name = "Skills", description = "Controlador da classe Skills")
public class SkillController {
	@Autowired
	private SkillService service;
	
	@Autowired
	private SkillFotoService fotoService;
	
	@GetMapping()
	public List<Skill> getSkills() {
		return service.listarSkills();
	}
	
	@GetMapping("/{id}")
	public Skill getSkillId(@PathVariable Integer id) {
		return service.buscarId(id);
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Integer id){
		SkillFoto foto = fotoService.buscar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	@PostMapping(path = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Skill inserirSkill(@RequestPart Skill skill, @RequestPart MultipartFile file) throws IOException {
		return service.inserirSkill(skill,file);
	}
	
	@PutMapping(path = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Skill> atualizarSkill(@PathVariable Integer id, @RequestPart Skill skill, @RequestPart(required = false) MultipartFile file) throws IOException {
		return service.atualizarSkill(id, file, skill);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarSkill(@PathVariable Integer id){
		return service.deletarSkill(id);
	}
	
}
