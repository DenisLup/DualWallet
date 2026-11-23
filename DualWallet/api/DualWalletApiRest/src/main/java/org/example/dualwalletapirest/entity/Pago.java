package org.example.dualwalletapirest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString @Builder

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @OneToOne
    @JoinColumn(name = "pagador_id", nullable = false)
    private Usuario pagador;

    @OneToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario recibidor;

    @Column(name = "monto")
    private Double monto;

    @CreationTimestamp
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "descripcion", length = 100)
    private String descripcion;
}
