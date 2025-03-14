package org.example;

public class Cuenta {
    private String tipoCuenta;
    private int saldo;
    private String banco;
    private String redTarjeta;
    private String categoria;

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getRedTarjeta() {
        return redTarjeta;
    }

    public void setRedTarjeta(String redTarjeta) {
        this.redTarjeta = redTarjeta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Cuenta(String tipoCuenta, int saldo, String banco, String redTarjeta, String categoria) {
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.banco = banco;
        this.redTarjeta = redTarjeta;
        this.categoria = categoria;
    }
}
