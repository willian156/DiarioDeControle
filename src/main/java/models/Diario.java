package models;

import java.sql.Date;
import java.time.LocalDate;

import static controller.LoginController.usuarioLogado;


public class Diario {

    public int id;
    public String descricao;
    public String criador;
    public int id_login;

    public Diario(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {

        this.id_login = usuarioLogado.getId();
    }
}
