package br.com.skill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skill.DTO.AtualizarNivelDTO;
import br.com.skill.DTO.UsuarioRequestDTO;
import br.com.skill.DTO.UsuarioResponseDTO;
import br.com.skill.DTO.UsuarioSkillRequestDTO;
import br.com.skill.DTO.UsuarioSkillResponseDTO;
import br.com.skill.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<UsuarioResponseDTO> getUsuarios() {
		return service.listarUsuarios();
	}
	
	@PostMapping("/cadastrar")
	@Operation(summary = "Metodo para listar todos os comentarios feitos por um usuario")
	@ApiResponse(responseCode = "200", description = "Sucesso",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioResponseDTO.class))})
	public UsuarioResponseDTO inserirUsuario(@RequestBody @Valid UsuarioRequestDTO usuario) throws Exception {
		
		return service.cadastrarUsuario(usuario);
	}
	
	@PostMapping("/adicionarskill")
	public UsuarioResponseDTO inserirSkill(@RequestBody UsuarioSkillRequestDTO usuarioSkill) {
		return service.associarSkill(usuarioSkill);
	}
	
	@PostMapping("/login")
	public UsuarioResponseDTO autenticar(@RequestBody UsuarioRequestDTO userInfo)  {
		return service.autenticar(userInfo);
	}
	
	@GetMapping("/{id}")
	public UsuarioResponseDTO buscarId(@PathVariable Integer id) {
		return service.buscarPorId(id);
	}
	
	@GetMapping("/{id}/skills")
	public List<UsuarioSkillResponseDTO> listarSkills(@PathVariable Integer id) {
		return service.mostrarSkills(id);
	}
	
	@PutMapping("/{id}/atualizarnivel")
	public UsuarioSkillResponseDTO atualizarNota(@PathVariable Integer id, @RequestBody AtualizarNivelDTO infoNivel) {
		return service.atualizarNota(infoNivel);
	}
	
	@DeleteMapping("/{id}/removerskill/{skillId}")
	public UsuarioResponseDTO desassociarSkill(@PathVariable Integer id, @PathVariable Integer skillId){
		return service.desassociarSkill(id,skillId);
	}
	
}
