package org.example.dualwalletapirest.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString

@Embeddable
public class GastoParticipanteId implements Serializable {

    @Column(name = "gasto_id")
    private Long gastoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GastoParticipanteId that = (GastoParticipanteId) o;
        return Objects.equals(gastoId, that.gastoId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gastoId, usuarioId);
    }
}