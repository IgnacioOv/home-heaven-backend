package com.homeheaven.backend.controller;

import com.homeheaven.backend.entity.Venta;
import com.homeheaven.backend.service.VentaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;

@RestController
@RequestMapping("ventas")
public class VentasController {

    private final VentaService ventaService;

    public VentasController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ArrayList<Venta> getVentas() {
        return ventaService.getVentas();
    }

    @GetMapping("/{ventaId}")
    public Venta getVentaById(@PathVariable int ventaId) {
        return ventaService.getVentaById(ventaId);
    }

    @PostMapping
    public void addVenta(@RequestBody Venta venta) {
        ventaService.addVenta(venta);
    }

    // Agrega otros métodos para obtener ventas, actualizar ventas, etc.

}
