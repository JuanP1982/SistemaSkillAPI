package br.com.skill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skill.DTO.LoginRequestDTO;
import br.com.skill.DTO.LoginResponseDTO;
import br.com.skill.entity.Usuario;
import br.com.skill.exceptions.LoginIncorretoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Controlador da classe de autenticação")
public class AuthController {
	
	@Autowired 
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	@Operation(summary = "Metodo para Autenticar um usuário")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO login) {
	    try {
	        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail().trim().toLowerCase(), login.getSenha());
	        var auth = this.authenticationManager.authenticate(usernamePassword);

	        var token = tokenService.generateToken((Usuario)auth.getPrincipal());
	        return ResponseEntity.ok(new LoginResponseDTO(token, (Usuario)auth.getPrincipal()));

	    } catch (BadCredentialsException e) {
	        throw new LoginIncorretoException();
	    }
	}
   }
	
	