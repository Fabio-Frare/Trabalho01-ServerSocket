package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class Empresa {
    private String nome;
    private String cnpj;
    private int qtdeFuncionarios;
    private  List<Pessoa> pessoas  = new ArrayList<>();

    public Empresa() {
        this.qtdeFuncionarios = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getQtdeFuncionarios() {
        return pessoas.size();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return "Empresa{" + "nome=" + nome + ", cnpj=" + cnpj + ", qtdeFuncionarios=" + qtdeFuncionarios + ", pessoas=" + pessoas + '}';
    }
    
}
