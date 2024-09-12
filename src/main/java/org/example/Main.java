package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            //
            em.getTransaction().begin();

            Categoria carnes = new Categoria("Carnes");

            Categoria lacteos = new Categoria("Lacteos");

            Categoria limpieza = new Categoria("Limpieza");

            Articulo crema = new Articulo(3,"Crema de Leche",1850);

            Articulo detergente = new Articulo(4,"Lavandina",3600);

            crema.getCategorias().add(carnes);
            crema.getCategorias().add(lacteos);

            lacteos.getArticulos().add(crema);
            carnes.getArticulos().add(crema);
            detergente.getCategorias().add(limpieza);
            limpieza.getArticulos().add(detergente);


            Factura fac1 = new Factura("14/10/2024", 35);

            Cliente cliente = new Cliente("Juan", "Gutierrez", 44058201);
            Domicilio domicilio = new Domicilio("Huarpes",517);

            cliente.setDomicilio(domicilio);

            fac1.setCliente(cliente);

            DetalleFactura linea1 = new DetalleFactura();

            linea1.setArticulo(crema);
            linea1.setCantidad(6);
            linea1.setSubtotal(11100);

            fac1.getFacturas().add(linea1);

            DetalleFactura linea2 = new DetalleFactura();

            linea2.setArticulo(detergente);
            linea2.setCantidad(1);
            linea2.setSubtotal(50);

            fac1.getFacturas().add(linea2);

            em.persist(fac1);


            em.flush();

            em.getTransaction().commit();



        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}


        em.close();
        entityManagerFactory.close();
    }
}
