package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@Audited
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_calle")
    private String nombreCalle;

    @Column(name = "numero")
    private int numero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

    public Domicilio() {
    }

    public Domicilio(String nombreCalle, int numero) {

        this.nombreCalle = nombreCalle;
        this.numero = numero;
    }


}
