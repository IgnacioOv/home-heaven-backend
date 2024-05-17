package com.homeheaven.backend.repository;

import com.homeheaven.backend.entity.Venta;
import org.springframework.stereotype.Repository; //ni idea

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class VentaRepository {

    private ArrayList<Venta> ventas = new ArrayList<>(
            Arrays.asList(
                Venta.builder()
                        .id(1)
                        .buyerId(123)
                        .sellerId(456)
                        .productId(789)
                        .quantity(2)
                        .total(50.00)
                        .build()
    ));

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public Venta getVentaById(Integer id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null; // Venta not found with the given ID
    }


    //agregar + metodos de ventas.

    public void addVenta(Venta venta){
        ventas.add(venta);
    }


}
