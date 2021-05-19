package dao;

import database.DbConnection;
import models.Diario;
import models.Login;
import models.Perfis;
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

    public static Perfis puxarPerfil(int id) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        String search = "select * from Perfis where id_login = ?";
        PreparedStatement stmt = connection.prepareStatement(search);
        stmt.setInt(1, id);
        ResultSet rst = stmt.executeQuery();

        Perfis perfil = new Perfis();
        perfil.setId(rst.getInt("id"));
        perfil.setDescricao(rst.getString("Descricao"));
        perfil.setIdade(rst.getInt("idade"));
        perfil.setNome_completo(rst.getString("nome_completo"));
        perfil.setId_login(rst.getInt("Id_login"));

        connection.close();

        return perfil;

    }

    public static Perfis criarPerfil(Login login) throws SQLException{
        Perfis perfil = new Perfis();
        Connection connection = DbConnection.getConnectionSqlite();
        String save2 = "insert into Perfis (nome_completo, descricao, idade, id_login) values (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(save2);
        stmt = connection.prepareStatement(save2);
        stmt.setString(1, login.getLogin());
        stmt.setString(2, "insira sua descrição aqui");
        stmt.setInt(3, login.getId());
        stmt.setInt(4, login.getId());
        stmt.execute();

        String search = "select * from Perfis where id_login = ?";
        stmt = connection.prepareStatement(search);
        stmt.setInt(1, login.getId());
        ResultSet rst = stmt.executeQuery();

        perfil.setId(rst.getInt("id"));
        perfil.setDescricao(rst.getString("Descricao"));
        perfil.setIdade(rst.getInt("idade"));
        perfil.setNome_completo(rst.getString("nome_completo"));
        perfil.setId_login(rst.getInt("Id_login"));

        connection.close();

        return perfil;

    }

}
