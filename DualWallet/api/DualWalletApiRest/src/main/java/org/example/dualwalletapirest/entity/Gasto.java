package org.example.dualwalletapirest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString @Builder

@Entity
@Table(name = "gasto")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "concepto", length = 150)
    private String concepto;

    @Column(name = "monto")
    private double monto;

    @Column(name = "fecha_gasto")
    private LocalDate fechaGasto;

    @OneToOne
    @JoinColumn(name = "pagado_por")
    private Usuario pagadoPor;
}
