package org.example.dualwalletapirest.controller;

import org.apache.coyote.Response;
import org.example.dualwalletapirest.dto.GastoParticipanteDTO;
import org.example.dualwalletapirest.entity.Gasto;
import org.example.dualwalletapirest.entity.GastoParticipante;
import org.example.dualwalletapirest.entity.GastoParticipanteId;
import org.example.dualwalletapirest.entity.Usuario;
import org.example.dualwalletapirest.repository.GastoParticipanteRepository;
import org.example.dualwalletapirest.repository.GastoRepository;
import org.example.dualwalletapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos-participante")
public class GastoParticipanteController {
    @Autowired
    private GastoParticipanteRepository gastoParticipanteRepository;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET -> http://localhost:8080/api/gastos-participante
    @GetMapping
    public ResponseEntity<List<GastoParticipante>> findAll() {
        return ResponseEntity.ok(gastoParticipanteRepository.findAll());
    }

    //GET -> http://localhost:8080/api/gastos-participante/id
    @GetMapping("/gasto/{idGasto}/usuario/{idUsuario}")
    public ResponseEntity<GastoParticipante> findById(
            @PathVariable Long idGasto,
            @PathVariable Long idUsuario) {
        GastoParticipanteId id = new GastoParticipanteId(idGasto, idUsuario);
        return gastoParticipanteRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //POST -> http://localhost:8080/api/gastos-participante
    @PostMapping
    public ResponseEntity<GastoParticipante> addGastoParticipante(@RequestBody GastoParticipanteDTO gastoParticipante) {
        Usuario usuario = usuarioRepository.findById(gastoParticipante.getUsuario().getId()).orElse(new Usuario());
        Gasto gasto = gastoRepository.findById(gastoParticipante.getGasto().getId()).orElse(new Gasto());

        GastoParticipanteId idGasto = new GastoParticipanteId(gasto.getId(), usuario.getId());
        GastoParticipante gastoP = new GastoParticipante(
                idGasto, usuario, gastoParticipante.getMonto());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                gastoParticipanteRepository.save(gastoP)
        );
    }

    //DELETE -> http://localhost:8080/api/gastos-participante/id
    @DeleteMapping("/gasto/{idGasto}/usuario/{idUsuario}")
    public ResponseEntity<GastoParticipante> deleteGastoParticipante(
            @PathVariable Long idGasto,
            @PathVariable Long idUsuario) {
        GastoParticipanteId id = new GastoParticipanteId(idGasto, idUsuario);
        if (!gastoParticipanteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gastoParticipanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT -> http://localhost:8080/api/gastos-participante/id
    @PreAuthorize("hasRole('ADMIN'")
    @PutMapping("/gasto/{gastoId}/usuario/{usuarioId}")
    public ResponseEntity<GastoParticipante> updateGastoParticipante(
            @PathVariable Long gastoId,
            @PathVariable Long usuarioId,
            @RequestBody GastoParticipante gastoParticipante
    ) {
        GastoParticipanteId id = new GastoParticipanteId(gastoId, usuarioId);
        return gastoParticipanteRepository.findById(id).map(gp -> {
            gp.setMontoParte(gastoParticipante.getMontoParte());
            return ResponseEntity.ok(gastoParticipante);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}