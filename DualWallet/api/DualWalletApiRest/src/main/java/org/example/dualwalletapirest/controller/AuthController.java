package org.example.dualwalletapirest.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dualwalletapirest.dto.LoginRequest;
import org.example.dualwalletapirest.dto.RegisterRequest;
import org.example.dualwalletapirest.entity.Usuario;
import org.example.dualwalletapirest.repository.UsuarioRepository;
import org.example.dualwalletapirest.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar al usuario con email y password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );

            // Si las credenciales son correctas, generar token
            String token = jwtService.generateToken(authentication);
            return ResponseEntity.ok(Map.of("token", token));

        } catch (AuthenticationException e) {
            // Contraseña incorrecta, usuario inexistente o cuenta deshabilitada
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(registerRequest.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "El email ya está registrado"));
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(registerRequest.email());
        usuario.setPassword(passwordEncoder.encode(registerRequest.password()));  // Cifrar password
        usuario.setRoles("ROLE_USER");  // Rol por defecto
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario registrado correctamente"));
    }
}

