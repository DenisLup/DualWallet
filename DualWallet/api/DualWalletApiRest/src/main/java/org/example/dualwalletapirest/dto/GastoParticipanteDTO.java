package org.example.dualwalletapirest.dto;

import lombok.*;
import org.example.dualwalletapirest.entity.Gasto;
import org.example.dualwalletapirest.entity.Usuario;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GastoParticipanteDTO {
    private Usuario usuario;
    private Gasto gasto;
    private Double monto;
}
