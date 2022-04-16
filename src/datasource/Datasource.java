package datasource;

import java.util.ArrayList;
import java.util.List;
import model.Empresa;
import model.Pessoa;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.Utils;

/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class Datasource {
    private final Utils utils;
    private Pessoa pessoa;
    private Empresa empresa;
    private static final List<Empresa> dadosEmpresas = new ArrayList();;

    public Datasource() {
        this.utils = new Utils();
//        populaBanco();
    }

    public String addPessoa(String msg) throws ParseException {   
        this.pessoa = new Pessoa();
        this.pessoa = utils.converteJsonToPessoa(msg);         
        return "Pessoa "+ pessoa.getNome() + " inserida com sucesso.";
    }

    public String addEmpresa(String msg) throws ParseException {
        this.empresa = new Empresa();  
        this.empresa = utils.converteJsonToEmpresa(msg);
        dadosEmpresas.add(empresa);       
        return "Empresa "+ empresa.getNome() + " inserida com sucesso.";
    }
    public String ListaEmpresas() {
        String lista = "";        
        JSONObject jsonEmpresas = new JSONObject();  
        
        for (Empresa dadosEmpresa : dadosEmpresas) {
            jsonEmpresas.put("cnpj", dadosEmpresa.getCnpj());
            jsonEmpresas.put("nome", dadosEmpresa.getNome());
            jsonEmpresas.put("qtde", dadosEmpresa.getQtdeFuncionarios());
            lista += jsonEmpresas.toJSONString();
        }   
        return lista;    
    }

//
//    public List<Pessoa> getDadosPessoas() {
//        return dadosPessoas;
//    }
//
//    public void setDadosPessoas(List<Pessoa> dadosPessoas) {
//        this.dadosPessoas = dadosPessoas;
//    }
//
//    public List<Empresa> getDadosEmpresas() {
//        return dadosEmpresas;
//    }
//
//    public void setDadosEmpresas(List<Empresa> dadosEmpresas) {
//        this.dadosEmpresas = dadosEmpresas;
//    }
//
//    public String ListaPessoas() {
//        String pessoas = "";
//        for (var pessoa : dadosPessoas) {
//            pessoas += "Nome: "     + pessoa.getNome()
//                    + " CPF: "      + pessoa.getCpf()
//                    + " Endereço: " + pessoa.getEndereco()
//                    + "Empresa: "   + pessoa.getCnpjEmpresa()
//                    + " || ";
//        }
//        return pessoas;
//    }
//
//    public String buscaPessoa(String cpf) {
//        String pessoas = "";
//        for (var pessoa : dadosPessoas) {
//            if (pessoa.getCpf().equals(cpf)) {
//                pessoas += "Nome: " + pessoa.getNome() + " CPF: " + pessoa.getCpf() + "Endereço: " + pessoa.getEndereco();
//                return pessoas;
//            }
//        }
//        return "Pessoa não encontrada.";
//    }
//
//    public String deletaPessoa(String cpf) {
//        for (int i = 0; i < dadosPessoas.size(); i++) {
//            System.out.println("deletar Pessoa: " + dadosPessoas.get(i).getCpf());
//            if (dadosPessoas.get(i).getCpf().equalsIgnoreCase(cpf)) {
//                dadosPessoas.remove(dadosPessoas.get(i));
//                return "Pessoa excluída com sucesso.";
//            }
//        }
//        return "Empresa não encontrada.";
//    }
//
//    public String ListaEmpresas() {
//        String empresas = "";
//        for (var empresa : dadosEmpresas) {
//            empresas += "Nome: " + empresa.getNome() + " CNPJ: " + empresa.getCnpj() + " Endereço: " + empresa.getEndereco() + " || ";
//        }
//        return empresas;
//    }
//
//    public String buscaEmpresa(String cnpj) {
//        String empresas = "";
//        for (var empresa : dadosEmpresas) {
//            if (empresa.getCnpj().equals(cnpj)) {
//                empresas += "Nome: " + empresa.getNome() + " CNPJ: " + empresa.getCnpj() + "Endereço: " + empresa.getEndereco();
//                return empresas;
//            }
//        }
//        return "Empresa não encontrada.";
//    }
//
//    public String deletaEmpresa(String cnpj) {
//        for (int i = 0; i < dadosEmpresas.size(); i++) {
//            System.out.println("deletar Empresa: " + dadosEmpresas.get(i).getCnpj());
//            if (dadosEmpresas.get(i).getCnpj().equalsIgnoreCase(cnpj)) {
//                dadosEmpresas.remove(dadosEmpresas.get(i));
////                System.out.println("empresas: " + dadosEmpresas.get(i).getNome());
//                return "Empresa excluída com sucesso.";
//            }
//        }
//        return "Empresa não encontrada.";
//    }
//
//    public String atualizaPessoa(String msg) {
//        utils = new Utils();
//        String cpf = utils.reverteConversao(msg.substring(7, 18));
//        pessoa = utils.converteStringPessoa(msg);
//        for (int i = 0; i < dadosPessoas.size(); i++) {
//            if (dadosPessoas.get(i).getCpf().equalsIgnoreCase(cpf)) {
//                dadosPessoas.set(i, pessoa);
//                return "Pessoa atualizado com sucesso.";
//            }
//        }
//        return "Atualização de pessoa não efetuada.";
//    }
//
//    public String atualizaEmpresa(String msg) {
//        utils = new Utils();
//        String cnpj = utils.reverteConversao(msg.substring(7, 21));
//        empresa = utils.converteStringEmpresa(msg);
//        for (int i = 0; i < dadosEmpresas.size(); i++) {
//            if (dadosEmpresas.get(i).getCnpj().equalsIgnoreCase(cnpj)) {
//                dadosEmpresas.set(i, empresa);
//                return "Empresa atualizado com sucesso.";
//            }
//        }
//        return "Atualização de empresa não efetuada.";
//    }
    
    private void populaBanco() {        
        if(dadosEmpresas.isEmpty()) {
        Empresa e1 = new Empresa();
        e1.setNome("Singular");
        e1.setCnpj("123456789");
        
        Pessoa p1 = new Pessoa();
        p1.setNome("Fabio");
        p1.setCpf("123456");
        p1.setEndereco("Rua estranha");
        e1.getPessoas().add(p1);
        
        Pessoa p2 = new Pessoa();
        p2.setNome("Lucas");
        p2.setCpf("654321");
        p2.setEndereco("Rua normal");
        e1.getPessoas().add(p2);
        
        dadosEmpresas.add(e1);
        }
//        System.out.println(dadosEmpresas.toString());
    }

    

    
}
