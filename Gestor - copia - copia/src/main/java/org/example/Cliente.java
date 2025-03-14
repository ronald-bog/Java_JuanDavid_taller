package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    String nombre;
    String identificacion;
    String direccion;
    String ciudad;
    List<Cuenta> cuentas = new ArrayList<>();
    int puntajeCrediticio;

    public Cliente(String nombre, String identificacion, String direccion, String ciudad, int puntajeCrediticio) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.puntajeCrediticio = puntajeCrediticio;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
}
