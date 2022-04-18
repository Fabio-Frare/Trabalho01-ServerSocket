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

//    public void setQtdeFuncionarios(int qtdeFuncionarios) {
//        this.qtdeFuncionarios = qtdeFuncionarios;
//    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

//    private String listaPessoas() {
//        String msg = "";
//        for (Pessoa pessoa : pessoas) {
//            msg += "    "+ pessoa.getCpf();
//            msg += ";   " + pessoa.getNome();            
//            msg += ";   " + pessoa.getEndereco();
//        }
//        return msg;
//    }    
//                
//    @Override
//    public String toString() {
//        return "Empresa: " +
//                "Nome: " + nome + 
//                " CNPJ: " + cnpj + 
//                " Qtde de Funcionários: " + pessoas.size()
//                + "Pessoas:" + 
//                listaPessoas();
//    }
    
//     private String listaPessoas() {
//        String msg = "";
//        for (Pessoa pessoa : pessoas) {
//            msg += "cpf:"+ pessoa.getCpf();
//            msg += ",nome:" + pessoa.getNome();            
//            msg += ",endereco:" + pessoa.getEndereco() ;           
//        }
//        return msg;
//    }    
//                
//    @Override
//    public String toString() {
//        return "empresa:{" +
//                "nome:" + nome + 
//                ",cnpj:" + cnpj + 
//                ",funcionários:" + pessoas.size()
//                + ",Pessoas:" + listaPessoas()+ "}";
//    }

    @Override
    public String toString() {
        return "Empresa{" + "nome=" + nome + ", cnpj=" + cnpj + ", qtdeFuncionarios=" + qtdeFuncionarios + ", pessoas=" + pessoas + '}';
    }
    
    
}
