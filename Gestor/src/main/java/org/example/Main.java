package org.example;

import java.io.*;
import java.util.*;
//class Cuenta implements Serializable{
class Cuenta{
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
//class Cliente implements Serializable {
class Cliente {
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

public class Main {

    public static List<Cliente> leerArchivoTexto(String rutaArchivo) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            if (linea.equals("FIN")) break;
            // Comprobar que la línea contiene los 4 elementos esperados (nombre, identificación, dirección, ciudad)
            String[] datosCliente = linea.split("/");
            if (datosCliente.length == 4) {
                Cliente cliente = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2], datosCliente[3], 0);

                // Leer las cuentas asociadas al cliente
                while (!(linea = br.readLine()).startsWith("PC=")) {
                    String[] datosCuenta = linea.split(",");
                    if (datosCuenta.length >= 3) { // Verificación de al menos tipo de cuenta, saldo, banco
                        String categoria = datosCuenta.length == 5 ? datosCuenta[4] : null;
                        Cuenta cuenta = new Cuenta(datosCuenta[0], Integer.parseInt(datosCuenta[1]), datosCuenta[2], datosCuenta.length > 3 ? datosCuenta[3] : null, categoria);
                        cliente.agregarCuenta(cuenta);
                    }
                }

                // Extraer puntaje crediticio
                cliente.puntajeCrediticio = Integer.parseInt(linea.split("=")[1]);
                clientes.add(cliente);
            } else {
                System.out.println("Formato incorrecto en la línea: " + linea);
            }
            br.readLine(); // Leer la línea del separador `#`
        }
        br.close();
        return clientes;
    }

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

    public static void main(String[] args) {
        try {
            // Leer archivo de texto y serializar clientes
            List<Cliente> clientes = leerArchivoTexto("clientes.txt");
/*
            serializarClientes(clientes, "clientes.ser");

            // Deserializar clientes
            clientes = deserializarClientes("clientes.ser");
*/

            // Generar reporte de riesgos
            generarReporteRiesgos(clientes, "riesgos.txt");

            // Generar archivo por banco
            generarArchivoPorBanco(clientes, "Davivienda", "davivienda.txt");

            // Obtener saldo total por tipo de cuenta
            obtenerSaldoTotalPorTipoCuenta(clientes, "456", "CA");

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
