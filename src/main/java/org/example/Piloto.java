package org.example;


import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Piloto {
    private Integer driverId;
    private String code;
    private String forename;
    private String surname;
    private String dob; // Date of Birth in String format, ideally should be a Date type
    private String nationality;
    private String url;

    // Constructor sin el ID del piloto, para cuando se cree uno nuevo y aún no se tenga un ID.
    public Piloto(String code, String forename, String surname, String dob, String nationality, String url) {
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.nationality = nationality;
        this.url = url;
    }

    // Constructor completo, incluyendo el ID del piloto, para cuando se recupere de la BD.
    public Piloto(int driverId, String code, String forename, String surname, String dob, String nationality, String url) {
        this(code, forename, surname, dob, nationality, url);
        this.driverId = driverId;
    }

    // Getters y setters
    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Método toString() para imprimir la información del piloto.
    @Override
    public String toString() {
        return "Piloto{" +
                "driverId=" + driverId +
                ", code='" + code + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob='" + dob + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}











