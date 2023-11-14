package org.example;

import java.nio.file.Path;
import java.sql.*;
import java.nio.file.Path;
import java.sql.PreparedStatement;


public class Main {
    public static void main(String[] args) {

        Path rutaBaseDatos = Path.of("src", "main", "resources", "db", "f12006sqlite.db");
        String url = "jdbc:sqlite:" + rutaBaseDatos.toAbsolutePath();

        //comprobacion de que podemos conectarnos a la base de datos
        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("Conexión establecida con la base de datos.");

            //hazemos una instancia de OperacionesCRUDPilotos
            OperacionesCRUDPilotos operaciones = new OperacionesCRUDPilotos(connection);

            //creamos un piloto
            Piloto nuevoPiloto = new Piloto("56", "Serlli ", "peña", "2004-01-01", "Español", "http://url.com");
            operaciones.CrearPiloto(nuevoPiloto);

            // Leer un piloto por enteros
            Piloto pilotoLeido = operaciones.LeerPiloto(56);
            if (pilotoLeido != null) {
                System.out.println("Piloto leído: " + pilotoLeido.getForename() + " " + pilotoLeido.getSurname());
            }

            // Actualizar un piloto
            nuevoPiloto.setSurname("Blazquez");
            operaciones.ActualizarPiloto(nuevoPiloto);

            // Borrar un piloto
            operaciones.BorrarPiloto(nuevoPiloto);

            // Mostrar clasificación de pilotos
            operaciones.MostrarClasificacionPiloto();

            // Mostrar clasificación de constructores
            operaciones.MostrarClasificacionConstructores();


        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }

    }
}


