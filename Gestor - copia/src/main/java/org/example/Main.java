package org.example;

import java.io.*;
import java.util.*;

public class Main {

/*    public static void serializarClientes(List<Cliente> clientes, String rutaArchivo) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
        oos.writeObject(clientes);
        oos.close();
    }
    public static List<Cliente> deserializarClientes(String rutaArchivo) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo));
        List<Cliente> clientes = (List<Cliente>) ois.readObject();
        ois.close();
        return clientes;
    }*/
/*    public static void generarReporteRiesgos(List<Cliente> clientes, String rutaArchivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

        for (Cliente cliente : clientes) {
            bw.write(cliente.identificacion + " - " + cliente.nombre + " - " + cliente.ciudad + "\n");
            bw.write("Cuentas bancarias\n");
            for (Cuenta cuenta : cliente.cuentas) {
                bw.write(cuenta.tipoCuenta + " - " + cuenta.banco + " - " + (cuenta.redTarjeta != null ? cuenta.redTarjeta : "") + "\n");
            }
            String reporte = determinarComportamientoCrediticio(cliente.puntajeCrediticio);
            bw.write("Reporte: " + cliente.puntajeCrediticio + " - " + reporte + "\n");
            bw.write("#\n");
        }
        bw.close();
    }*/

    public static void main(String[] args) {
        try {
            // Leer archivo de texto y serializar clientes
            List<Cliente> clientes = Files.leerArchivoTexto("archivos/clientes.txt");
            clientes.forEach(cliente -> System.out.println(cliente));
/*
            serializarClientes(clientes, "clientes.ser");
            // Deserializar clientes
            clientes = deserializarClientes("clientes.ser");
*/
            // Generar reporte de riesgos
            Reports.generarReporteRiesgos(clientes, "archivos/riesgos.txt");

            // Generar archivo por banco
            Reports.generarArchivoPorBanco(clientes, "Davivienda", "archivos/davivienda.txt");

            // Obtener saldo total por tipo de cuenta
            Reports.obtenerSaldoTotalPorTipoCuenta(clientes, "456", "CA");

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
