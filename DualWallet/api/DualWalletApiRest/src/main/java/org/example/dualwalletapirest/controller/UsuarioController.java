package org.example.dualwalletapirest.controller;

import org.example.dualwalletapirest.entity.Usuario;
import org.example.dualwalletapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET -> http://localhost:8080/api/usuarios
    //Encontrar a todos los usuarios
    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
    //GET -> http://localhost:8080/api/usuarios/id
    //Encontrar a los usuarios por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return usuarioRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //POST -> http://localhost:8080/api/usuarios
    @PreAuthorize("hasRole('ADMIN'")
    @PostMapping
    public ResponseEntity<Usuario> addUser(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(usuario));
    }
    //DELETE -> http://localhost:8080/api/usuarios/id
    @PreAuthorize("hasRole('ADMIN'")
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUserById(@PathVariable Long id){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //PUT -> http://localhost:8080/api/usuarios/id
    @PreAuthorize("hasRole('ADMIN'")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioRepository.findById(id).map(u -> {
            u.setId(id);
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
