package org.example;

public class Cuenta {
    String tipoCuenta;
    int saldo;
    String banco;
    String redTarjeta;
    String categoria;

    public Cuenta(String tipoCuenta, int saldo, String banco, String redTarjeta, String categoria) {
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.banco = banco;
        this.redTarjeta = redTarjeta;
        this.categoria = categoria;
    }
}
