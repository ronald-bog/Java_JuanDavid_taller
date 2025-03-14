package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reports {
    public static void generarReporteRiesgos(List<Cliente> clientes, String rutaArchivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

        for (Cliente cliente : clientes) {
            bw.write(cliente.identificacion + " - " + cliente.nombre + " - " + cliente.ciudad + "\n");
            bw.write("Cuentas bancarias\n");

            for (Cuenta cuenta : cliente.cuentas) {
                // Determinar el tipo de cuenta
                String tipoCuenta;
                switch (cuenta.tipoCuenta) {
                    case "CC":
                        tipoCuenta = "Cuenta corriente";
                        break;
                    case "CA":
                        tipoCuenta = "Cuenta de ahorros";
                        break;
                    case "TC":
                        tipoCuenta = "Tarjeta de crédito";
                        break;
                    default:
                        tipoCuenta = "Tipo de cuenta desconocido";
                        break;
                }

                // Determinar el tipo de tarjeta
                String redTarjeta = "";
                if (cuenta.redTarjeta != null) {
                    switch (cuenta.redTarjeta) {
                        case "V":
                            redTarjeta = "Visa";
                            break;
                        case "MC":
                            redTarjeta = "MasterCard";
                            break;
                        case "AE":
                            redTarjeta = "American Express";
                            break;
                        default:
                            redTarjeta = cuenta.redTarjeta; // Si no es conocido, imprime el valor original
                            break;
                    }
                }

                // Escribir la información de la cuenta
                bw.write(tipoCuenta + " - " + cuenta.banco + " - " + redTarjeta + "\n");
            }

            // Escribir el reporte de comportamiento crediticio
            String reporte = determinarComportamientoCrediticio(cliente.puntajeCrediticio);
            bw.write("Reporte: " + cliente.puntajeCrediticio + " - " + reporte + "\n");
            bw.write("#\n");
        }

        bw.close();
    }


    public static String determinarComportamientoCrediticio(int puntaje) {
        if (puntaje >= 150 && puntaje <= 300) return "malo";
        else if (puntaje > 300 && puntaje <= 475) return "regular";
        else if (puntaje > 475 && puntaje <= 670) return "bueno";
        else return "excelente";
    }

    public static void generarArchivoPorBanco(List<Cliente> clientes, String banco, String rutaArchivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        bw.write("Cuentas del banco " + banco + "\n#\n");

        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.cuentas) {
                if (cuenta.banco.equals(banco)) {
                    bw.write(cliente.identificacion + " " + cliente.nombre + "\n");

                    // Determinar el tipo de cuenta
                    String tipoCuenta;
                    switch (cuenta.tipoCuenta) {
                        case "CC":
                            tipoCuenta = "Cuenta corriente";
                            break;
                        case "CA":
                            tipoCuenta = "Cuenta de ahorros";
                            break;
                        case "TC":
                            tipoCuenta = "Tarjeta de crédito";
                            break;
                        default:
                            tipoCuenta = "Tipo de cuenta desconocido";
                            break;
                    }

                    // Determinar el tipo de tarjeta
                    String redTarjeta = "";
                    if (cuenta.redTarjeta != null) {
                        switch (cuenta.redTarjeta) {
                            case "V":
                                redTarjeta = "Visa";
                                break;
                            case "MC":
                                redTarjeta = "MasterCard";
                                break;
                            case "AE":
                                redTarjeta = "American Express";
                                break;
                            default:
                                redTarjeta = cuenta.redTarjeta; // Si no es conocido, imprime el valor original
                                break;
                        }
                    }

                    // Escribir la información de la cuenta
                    bw.write(tipoCuenta + " " + cuenta.saldo + " " + redTarjeta + "\n");
                    bw.write("#\n");
                    break;
                }
            }
        }
        bw.close();
    }


/*    public static void generarArchivoPorBanco(List<Cliente> clientes, String banco, String rutaArchivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        bw.write("Cuentas del banco " + banco + "\n#\n");

        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.cuentas) {
                if (cuenta.banco.equals(banco)) {
                    bw.write(cliente.identificacion + " " + cliente.nombre + "\n");
                    bw.write(cuenta.tipoCuenta + " " + cuenta.saldo + " " + (cuenta.redTarjeta != null ? cuenta.redTarjeta : "") + "\n");
                    bw.write("#\n");
                    break;
                }
            }
        }
        bw.close();
    }*/

    public static void obtenerSaldoTotalPorTipoCuenta(List<Cliente> clientes, String idCliente, String tipoCuenta) {
        for (Cliente cliente : clientes) {
            if (cliente.identificacion.equals(idCliente)) {
                int saldoTotal = 0;
                boolean cuentaEncontrada = false;

                for (Cuenta cuenta : cliente.cuentas) {
                    if (cuenta.tipoCuenta.equals(tipoCuenta)) {
                        saldoTotal += cuenta.saldo;
                        cuentaEncontrada = true;
                    }
                }

                if (cuentaEncontrada) {
                    System.out.println("El saldo total para el cliente " + cliente.nombre + " es: " + saldoTotal);
                } else {
                    System.out.println("El cliente no tiene una cuenta del tipo " + tipoCuenta);
                }
                return;
            }
        }
        System.out.println("Cliente no encontrado");
    }

}
