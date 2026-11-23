package org.example.dualwalletapirest.controller;

import org.example.dualwalletapirest.entity.Gasto;
import org.example.dualwalletapirest.entity.Usuario;
import org.example.dualwalletapirest.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {
    @Autowired
    private GastoRepository gastoRepository;
    //GET -> http://localhost:8080/api/gastos
    @GetMapping
    public ResponseEntity<List<Gasto>> findAll(){
        return ResponseEntity.ok(gastoRepository.findAll());
    }
    //GET -> http://localhost:8080/api/gastos/id
    @GetMapping("/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable Long id){
        return gastoRepository.findById(id).map(ResponseEntity::ok) // 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());// 404 Not Found
    }

    //GET -> http://localhost:8080/api/gastos?fecha-gasto = "Fecha del gasto"
    @GetMapping(params = "fecha-gasto")
    public ResponseEntity<List<Gasto>> findByFechaGasto(@RequestParam(name = "fecha-gasto") LocalDate fechaGasto){
        List<Gasto> gastosEnEsaFecha = new ArrayList<>();
        for(Gasto g : gastoRepository.findAll()){
            if(g.getFechaGasto() == fechaGasto){
                gastosEnEsaFecha.add(g);
            }
        }
        return ResponseEntity.ok(gastosEnEsaFecha);
    }
    //POST -> http://localhost:8080/api/gastos
    @PostMapping
    public ResponseEntity<Gasto> addGasto(@RequestBody Gasto gasto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gastoRepository.save(gasto));
    }
    //DELETE -> http://localhost:8080/api/gastos/id
    @DeleteMapping("/{id}")
    public ResponseEntity<Gasto> deleteGasto(@PathVariable Long id){
        if(!gastoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        gastoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //PUT -> http://localhost:8080/api/gastos/id
    @PreAuthorize("hasRole('ADMIN'")
    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable Long id, @RequestBody Gasto gasto){
        return gastoRepository.findById(id).map(g ->{
            g.setId(id);
            return ResponseEntity.ok(gastoRepository.save(gasto));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
