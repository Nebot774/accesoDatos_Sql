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

        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }

    }
}


