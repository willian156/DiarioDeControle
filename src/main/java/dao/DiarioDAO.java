package dao;

import database.DbConnection;
import models.Diario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiarioDAO {
    public DiarioDAO() {
    }

    public static void create(Diario diario) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        String save = "insert into Diarios (Data, Criador, Descricao) values (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(save);
        stmt.setString(2, diario.getCriador());
        stmt.setString(3, diario.getDescricao());
        stmt.execute();
        connection.close();
    }

    public static List<Diario> retrive(int id) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        String search = "select *from Diarios where id_login = ?";
        PreparedStatement stmt = connection.prepareStatement(search);
        stmt.setInt(1, id);
        ResultSet rst = stmt.executeQuery();
        ArrayList<Diario> listDiario = new ArrayList<Diario>();

        while(rst.next()) {
            Diario diarios = new Diario();
            diarios.setId(rst.getInt("id"));
            diarios.setDescricao(rst.getString("Descricao"));
            diarios.setData(rst.getString("Data"));
            diarios.setCriador(rst.getString("Criador"));
            diarios.setId_login(rst.getInt("Id_login"));
            listDiario.add(diarios);
        }

        rst.close();
        connection.close();
        return listDiario;
    }

    public static void update(Diario diario) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        if (connection != null) {
            String update = "update Diarios set Data = ?, Criador = ?, Descricao = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.setString(2, diario.getCriador());
            stmt.setString(3, diario.getDescricao());
            stmt.setInt(4, diario.getId());
            stmt.close();
        }

    }

}
