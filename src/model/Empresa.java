package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class Empresa {
    private String nome;
    private String cnpj;
    private int qtdeFuncionarios;
    private  List<Pessoa> pessoas  = new ArrayList<>();
    

 

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
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    private String listaPessoas() {
        String msg = "";
        for (Pessoa pessoa : pessoas) {
            msg += "    "+ pessoa.getCpf();
            msg += ";   " + pessoa.getNome();            
            msg += ";   " + pessoa.getEndereco();
            msg += "\n";            
        }
        return msg;
    }    
            
    @Override
    public String toString() {
        return "Empresa: " +
                "\n    Nome: " + nome + 
                "\n    CNPJ: " + cnpj + 
                "\n    Qtde de Funcionários: " + pessoas.size()
                + "\nPessoas:\n" + 
                listaPessoas();
    }
    
    
}
