package org.example.dualwalletapirest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.EmbeddableInstantiator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "gasto_participante")

public class GastoParticipante {
    @EmbeddedId
    private GastoParticipanteId id = new GastoParticipanteId();

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "monto_parte")
    private Double montoParte;
}
