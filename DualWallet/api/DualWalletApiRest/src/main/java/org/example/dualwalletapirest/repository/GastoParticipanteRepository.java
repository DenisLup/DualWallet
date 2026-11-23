package org.example.dualwalletapirest.repository;

import org.example.dualwalletapirest.entity.GastoParticipante;
import org.example.dualwalletapirest.entity.GastoParticipanteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoParticipanteRepository extends JpaRepository<GastoParticipante, GastoParticipanteId> {
}
