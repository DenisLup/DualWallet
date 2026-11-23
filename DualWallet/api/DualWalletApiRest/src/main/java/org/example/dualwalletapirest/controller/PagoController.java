package org.example.dualwalletapirest.controller;

import org.example.dualwalletapirest.entity.Pago;
import org.example.dualwalletapirest.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    @Autowired
    private PagoRepository pagoRepository;

    //GET -> http://localhost:8080/api/pagos
    @GetMapping
    public ResponseEntity<List<Pago>> findAll(){
        return ResponseEntity.ok(pagoRepository.findAll());
    }
    //GET -> http://localhost:8080/api/pagos/id
    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Long id){
        return pagoRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //POST -> http://localhost:8080/api/pagos
    @PreAuthorize("hasRole('ADMIN'")
    @PostMapping
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                pagoRepository.save(pago)
        );
    }
    //DELETE -> http://localhost:8080/api/pagos/id
    @PreAuthorize("hasRole('ADMIN'")
    @DeleteMapping("/{id}")
    public ResponseEntity<Pago> deletePago(@PathVariable Long id){
        if(!pagoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        pagoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //PUT -> http://localhost:8080/api/pagos/id
    @PreAuthorize("hasRole('ADMIN'")
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(
            @PathVariable Long id,
            @RequestBody Pago pago
    ){
        return pagoRepository.findById(id).map(p ->{
            p.setIdPago(id);
            return ResponseEntity.ok(pagoRepository.save(pago));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
