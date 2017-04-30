package spaceapps.services;

import spaceapps.domain.Cords;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class FireService {
    private Connection conn;

    public FireService() {
        connectDatabase();
    }

    private void connectDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://10.10.11.61:5432/spaceapps";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.err.println("Error iniciando conn.");
            e.printStackTrace();

        }
    }

    public ArrayList<Cords> extractFires() {
        String query = "SELECT latitude, longitude FROM fue";
        ArrayList<Cords> resultado = new ArrayList<Cords>();
        double latitude, longitude;
        try {
            System.out.println(conn);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
                Cords cords = new Cords(latitude,longitude);
                resultado.add(cords);
            }
        } catch (SQLException e) {
        }
        return resultado;
    }
}
