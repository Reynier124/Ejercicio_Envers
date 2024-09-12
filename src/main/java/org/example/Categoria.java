package org.example;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Builder
@Audited
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "denominacion")
    private String denominacion;

   @ManyToMany(mappedBy = "categorias")
   @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    public Categoria(String denominacion) {
        this.denominacion = denominacion;
    }
}
