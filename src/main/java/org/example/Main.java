package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            //
            em.getTransaction().begin();

            Factura factura1 = new Factura();

            factura1.setNumero(12);
            factura1.setFecha("10/08/2020");

            Domicilio dom = Domicilio.builder().nombreCalle("San Martin").numero(1222).build();
            Cliente cliente = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(15245732).build();
            cliente.setDomicilio(dom);
            dom.setCliente(cliente);

            factura1.setCliente(cliente);

            Categoria carnes = Categoria.builder().denominacion("Carnes").build();
            Categoria lacteos = Categoria.builder().denominacion("Lacteos").build();
            Categoria limpieza = Categoria.builder().denominacion("Limpieza").build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt Ser sabor frutilla")
                    .precio(20)
                    .build();

            Articulo art2 = Articulo.builder()
                    .cantidad(300)
                    .denominacion("Detergente Magistral")
                    .precio(80)
                    .build();

            art1.getCategorias().add(carnes);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = DetalleFactura.builder()
                    .articulo(art1)
                    .cantidad(2)
                    .subtotal(40)
                    .build();

            art1.getDetalle().add(det1);
            factura1.getDetalles().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder()
                    .articulo(art2)
                    .cantidad(1)
                    .subtotal(80)
                    .build();

            art2.getDetalle().add(det2);
            factura1.getDetalles().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(120);

            em.persist(factura1);
            em.flush();
            em.flush();
            em.getTransaction().commit();

        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error: " + e.getMessage());}


        em.close();
        entityManagerFactory.close();
    }
}
