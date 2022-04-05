package datasource;

import java.util.ArrayList;
import java.util.List;
import model.Empresa;
import model.Pessoa;
import org.json.simple.parser.ParseException;
import utils.Utils;

/**
 *
 * @author fabio
 */
public class Datasource {
    private final Utils utils;
    private Pessoa pessoa;
    private Empresa empresa;
    private final List<Pessoa> dadosPessoas;
    private final List<Empresa> dadosEmpresas;

    public Datasource() {
        dadosPessoas  = new ArrayList<>();
        dadosEmpresas = new ArrayList();
        utils = new Utils();
    }

    public String addPessoa(String msg) throws ParseException {   
        pessoa = new Pessoa();
        pessoa = utils.converteJsonToPessoa(msg);    
        dadosPessoas.add(pessoa);        
//        System.out.println("Datasource addpessoa: " + pessoa.getNome() + "\n Tamanho: " + dadosPessoas.size());

        return "Pessoa inserida com sucesso.";
    }

    public String addEmpresa(String msg) throws ParseException {
        empresa = new Empresa();
        

        empresa = utils.converteJsonToEmpresa(msg);
        dadosEmpresas.add(empresa);

        return "Empresa inserida com sucesso.";
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
//
//    public void metodoCriarObjetos() {
//        Empresa empresa = new Empresa();
//        empresa.setNome("Singular Sistemas");
//        empresa.setCnpj("123");
//        empresa.setEndereco("Rua 7 de setembro");
//
//        Empresa empresa2 = new Empresa();
//        empresa2.setNome("Dalila Têxtil");
//        empresa2.setCnpj("456");
//        empresa2.setEndereco("Rua Mirador");
//
//        System.out.println("metodo criar empresa: ");
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome("Fábio Frare");
//        pessoa.setCpf("123");
//        pessoa.setEndereco("Rua 7 de setembro");
////        pessoa.setCnpjEmpresa(empresa.getCnpj());
////        empresa.getPessoas().add(pessoa.getCpf());
//
//        dadosPessoas.add(pessoa);
//        dadosEmpresas.add(empresa);
//        dadosEmpresas.add(empresa2);
//
//    }
}
