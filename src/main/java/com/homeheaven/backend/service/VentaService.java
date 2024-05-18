package com.homeheaven.backend.service;

import com.homeheaven.backend.entity.Venta;
import com.homeheaven.backend.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public ArrayList<Venta> getVentas() {
        return ventaRepository.getVentas();
    }

    public Venta getVentaById(int id) {
        return ventaRepository.getVentaById(id);
    }

    public void addVenta(Venta venta){
        ventaRepository.addVenta(venta);
    }
}
