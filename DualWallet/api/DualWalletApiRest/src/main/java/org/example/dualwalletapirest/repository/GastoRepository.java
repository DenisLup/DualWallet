package org.example.dualwalletapirest.repository;

import org.example.dualwalletapirest.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto,Long> {
    List<Gasto> findByFechaGasto(LocalDate fechaGasto);
}
