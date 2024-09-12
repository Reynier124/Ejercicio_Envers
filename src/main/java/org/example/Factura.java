package org.example;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private int numero;

    @ManyToOne(cascade = CascadeType.PERSIST)

    @JoinColumn(name = "fk_cliente")

    private Cliente cliente;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)

    @JoinColumn(name = "factura_id")
 

    private Set<DetalleFactura> facturas = new HashSet<>();



    public Factura() {
    }

    public Factura(String fecha, int numero) {
        this.fecha = fecha;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<DetalleFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Set<DetalleFactura> facturas) {
        this.facturas = facturas;
    }
}
