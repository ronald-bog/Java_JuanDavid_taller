package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {
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
                cliente.setPuntajeCrediticio(Integer.parseInt(linea.split("=")[1]));
                clientes.add(cliente);
            } else {
                System.out.println("Formato incorrecto en la línea: " + linea);
            }
            br.readLine(); // Leer la línea del separador `#`
        }
        br.close();
        return clientes;
    }
}
