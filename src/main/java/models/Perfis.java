package models;

import static controller.LoginController.usuarioLogado;

public class Perfis {

    private int id;
    private String nome_completo;
    private int idade;
    private String descricao;
    private int id_login;


    public Perfis(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(Login usuario) {
        usuario  = new Login();
        usuario = usuarioLogado;
        this.id_login = usuario.getId();
    }
}
