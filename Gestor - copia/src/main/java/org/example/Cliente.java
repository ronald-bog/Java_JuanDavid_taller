package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String identificacion;
    private String direccion;
    private String ciudad;
    private List<Cuenta> cuentas = new ArrayList<>();
    private int puntajeCrediticio;

    public void setPuntajeCrediticio(int puntajeCrediticio) {
        this.puntajeCrediticio = puntajeCrediticio;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public int getPuntajeCrediticio() {
        return puntajeCrediticio;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s - ID: %s - Dir: %s \n", nombre, identificacion, direccion);
    }
}
