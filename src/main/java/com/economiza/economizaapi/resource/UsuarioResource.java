package com.economiza.economizaapi.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.economiza.economizaapi.dto.UsuarioLoginDTO;
import com.economiza.economizaapi.exception.NotFoundException;
import com.economiza.economizaapi.model.Usuario;
import com.economiza.economizaapi.repository.CartaoDeCreditoRepository;
import com.economiza.economizaapi.repository.CategoriaGastoRepository;
import com.economiza.economizaapi.repository.FonteDeRendaRepository;
import com.economiza.economizaapi.repository.GastoRepository;
import com.economiza.economizaapi.repository.UsuarioRepository;
import com.economiza.economizaapi.security.JwtManager;
import com.economiza.economizaapi.security.JwtResponse;
import com.economiza.economizaapi.service.UsuarioService;
import com.economiza.economizaapi.service.util.HashUtil;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	@Autowired private UsuarioService usuarioService;
	@Autowired private UsuarioRepository UsuarioRepository;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtManager jwtManager;
	@Autowired private FonteDeRendaRepository fonteDeRendaRepository;
	@Autowired private GastoRepository gastoRepository;
	@Autowired private CartaoDeCreditoRepository cartaoDeCreditoRepository;
	@Autowired private CategoriaGastoRepository categoriaGastoRepository;



	@CrossOrigin
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		Usuario createdUser = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuario(#cod)")
	@PutMapping("/{cod}")
	public ResponseEntity<Usuario> update(@PathVariable(name = "cod") Long cod, @RequestBody Usuario user){
		Usuario updateUser = UsuarioRepository.findById(cod).get();
		updateUser.setEmail(user.getEmail());
		updateUser.setNome(user.getNome());
		Usuario updatedUser = usuarioService.update(updateUser);
		return ResponseEntity.ok(updatedUser);
	}

	@CrossOrigin
	@PreAuthorize("@accessManager.usuario(#cod)")
	@PatchMapping("/{cod}")
	public ResponseEntity<Usuario> updateSenha(@PathVariable(name = "cod") Long cod, @RequestBody Usuario user){
		Usuario updateUser = UsuarioRepository.findById(cod).get();
		String hash = HashUtil.getSecureHash(user.getSenha());
		updateUser.setSenha(hash);
		Usuario updatedUser = usuarioService.update(updateUser);
		return ResponseEntity.ok(updatedUser);
	}
	@CrossOrigin
	@GetMapping
	public ResponseEntity<Usuario> getUsuario() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = UsuarioRepository.findByEmail(email);
		if(!result.isPresent())  {
			throw new NotFoundException("Não foi encontrado um usuário com esse email = " + email);
		}
		Usuario usuario = result.get();
		return ResponseEntity.ok(usuario);
	}
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody UsuarioLoginDTO user) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getSenha());
		Authentication auth = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		User userSpring = (User) auth.getPrincipal();
		
		String email =  userSpring.getUsername();
		List<String> roles = userSpring
				.getAuthorities()
				.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		String jwt = jwtManager.createToken(email, roles);
		return ResponseEntity.ok(new JwtResponse(jwt, userSpring.getUsername(), userSpring.getAuthorities()));
		
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PreAuthorize("@accessManager.usuario(#cod)")
	@DeleteMapping("/{cod}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(name = "cod") Long cod) {
		Usuario deletedUser = null;
		deletedUser = usuarioService.getById(cod);
		if(deletedUser != null) {
			categoriaGastoRepository.deleteByUsuarioCod(deletedUser.getCod());
			cartaoDeCreditoRepository.deleteByUsuarioCod(deletedUser.getCod());
			gastoRepository.deleteByUsuarioCod(deletedUser.getCod());
			fonteDeRendaRepository.deleteByUsuarioCod(deletedUser.getCod());
			usuarioService.deleteById(cod);
			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
