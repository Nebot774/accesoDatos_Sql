package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUDPilotos {
    private Connection connection;

    public OperacionesCRUDPilotos(Connection connection) {
        this.connection = connection;
    }

    public void CrearPiloto(Piloto piloto) throws SQLException {
        String sql = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, piloto.getCode());
            pstmt.setString(2, piloto.getForename());
            pstmt.setString(3, piloto.getSurname());
            pstmt.setString(4, piloto.getDob());
            pstmt.setString(5, piloto.getNationality());
            pstmt.setString(6, piloto.getUrl());
            pstmt.executeUpdate();
        }
    }

    public Piloto LeerPiloto(int driverId) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE driverid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Piloto(
                        rs.getInt("driverid"),
                        rs.getString("code"),
                        rs.getString("forename"),
                        rs.getString("surname"),
                        rs.getString("dob"),
                        rs.getString("nationality"),
                        rs.getString("url")
                );
            }
        }
        return null;
    }

    public List<Piloto> LeerPilotos() throws SQLException {
        List<Piloto> pilotos = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pilotos.add(new Piloto(
                        rs.getInt("driverid"),
                        rs.getString("code"),
                        rs.getString("forename"),
                        rs.getString("surname"),
                        rs.getString("dob"),
                        rs.getString("nationality"),
                        rs.getString("url")
                ));
            }
        }
        return pilotos;
    }

    public void ActualizarPiloto(Piloto piloto) throws SQLException {
        String sql = "UPDATE drivers SET code = ?, forename = ?, surname = ?, dob = ?, nationality = ?, url = ? WHERE driverid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, piloto.getCode());
            pstmt.setString(2, piloto.getForename());
            pstmt.setString(3, piloto.getSurname());
            pstmt.setString(4, piloto.getDob());
            pstmt.setString(5, piloto.getNationality());
            pstmt.setString(6, piloto.getUrl());
            pstmt.setInt(7, piloto.getDriverId());
            pstmt.executeUpdate();
        }
    }

    public void BorrarPiloto(Piloto piloto) throws SQLException {
        String sql = "DELETE FROM drivers WHERE driverid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, piloto.getDriverId());
            pstmt.executeUpdate();
        }
    }

    public void MostrarClasificacionPiloto() throws SQLException {
        String sql = "SELECT d.forename, d.surname, SUM(r.points) as points FROM drivers d " +
                "JOIN results r ON d.driverid = r.driverid " +
                "GROUP BY d.driverid " +
                "ORDER BY points DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("forename") + " " + rs.getString("surname") + " - " + rs.getInt("points"));
            }
        }
    }

    public void MostrarClasificacionConstructores() throws SQLException {
        String sql = "SELECT c.name, SUM(r.points) as points FROM constructors c " +
                "JOIN results r ON c.constructorid = r.constructorid " +
                "GROUP BY c.constructorid " +
                "ORDER BY points DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("points"));
            }
        }
    }
}

