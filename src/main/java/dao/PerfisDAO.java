package dao;

import database.DbConnection;
import models.Login;
import models.Perfis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfisDAO {
    public PerfisDAO(){
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
