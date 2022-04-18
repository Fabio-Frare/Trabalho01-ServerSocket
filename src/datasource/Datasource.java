package datasource;

import java.util.ArrayList;
import java.util.List;
import model.Empresa;
import model.Pessoa;
import org.json.simple.JSONArray;
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
    private static final List<Empresa> dadosEmpresas = new ArrayList();
    private String lista = ""; 

    public Datasource() {
        this.utils = new Utils();
        populaBanco();
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
        JSONObject jsonEmpresas = new JSONObject();          
        for (Empresa dadosEmpresa : dadosEmpresas) {
            jsonEmpresas.put("cnpj", dadosEmpresa.getCnpj());
            jsonEmpresas.put("nome", dadosEmpresa.getNome());
            jsonEmpresas.put("qtde", dadosEmpresa.getQtdeFuncionarios());
            lista += jsonEmpresas.toJSONString();
        }   
        return lista;    
    }
    
    public String listaPessoas() {
        JSONObject jsonEmpresas = new JSONObject();    
        for (Empresa dadosEmpresa : dadosEmpresas) {
            jsonEmpresas.put("cnpj", dadosEmpresa.getCnpj());
            jsonEmpresas.put("nome", dadosEmpresa.getNome());
            jsonEmpresas.put("qtde", dadosEmpresa.getQtdeFuncionarios());
            
            List<Pessoa> listaPessoas = new ArrayList<>();
            JSONObject jsonPessoas = new JSONObject(); 
            listaPessoas = dadosEmpresa.getPessoas();
            for (Pessoa pessoa : listaPessoas) {     
                jsonPessoas.put("nome"    , pessoa.getNome());
                jsonPessoas.put("cpf"     , pessoa.getCpf());
                jsonPessoas.put("endereco", pessoa.getEndereco());
                lista += jsonPessoas.toJSONString();
            }
        }   
        return lista;    
    }

       public String listaTodos() {
           
           String jsonStr = JSONArray.toJSONString(dadosEmpresas);
            System.out.println("Lista todos: " + jsonStr);
           
//            JSONObject jsonEmpresas = new JSONObject();
//            for (int i = 0; i < dadosEmpresas.size(); i++) {
//                jsonEmpresas.put("cnpj", dadosEmpresas.get(i).getCnpj());
//                jsonEmpresas.put("nome", dadosEmpresas.get(i).getNome());
//                jsonEmpresas.put("qtde", dadosEmpresas.get(i).getQtdeFuncionarios());
//
//                String pessoas = "";
//                List<Pessoa> listaPessoas = new ArrayList<>();
//                int qtde = dadosEmpresas.get(i).getQtdeFuncionarios();
//                if(qtde > 0) {                    
//                    listaPessoas = dadosEmpresas.get(i).getPessoas();
//                    for (int j = 0; j < listaPessoas.size(); j++) {
//                        pessoas += utils.convertePessoaToJson(listaPessoas.get(j));
//                    }
//                    jsonEmpresas.put("pessoa", pessoas);
//                    
//                }               
//                lista += jsonEmpresas.toJSONString();
//                System.out.println("Lista datasource: " + lista);
//        }              
        return jsonStr;  
    }
       
    public String buscaEmpresa(String cnpj) {
        JSONObject jsonEmpresas = new JSONObject();          
        for (Empresa dadosEmpresa : dadosEmpresas) {
            if(dadosEmpresa.getCnpj().equalsIgnoreCase(cnpj)) {
                jsonEmpresas.put("cnpj", dadosEmpresa.getCnpj());
                jsonEmpresas.put("nome", dadosEmpresa.getNome());
                jsonEmpresas.put("qtde", dadosEmpresa.getQtdeFuncionarios());
                return jsonEmpresas.toJSONString();
            }
        }   
        return "Empresa com o CNPJ " + cnpj + " não encontrada.";  
    }

    public String buscaPessoa(String cpf) {
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            List<Pessoa> listaPessoas = new ArrayList<>();                             
            listaPessoas = dadosEmpresas.get(i).getPessoas();
            for (int j = 0; j < listaPessoas.size(); j++) {
                if(listaPessoas.get(j).getCpf().equalsIgnoreCase(cpf)) {
                    return utils.convertePessoaToJson(listaPessoas.get(j));
                }
            }    
        } 
        return "Pessoa com o CPF: " + cpf + " não encontrada.";
    }
       
   

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
        e1.setCnpj("123");
        
        Pessoa p1 = new Pessoa();
        p1.setNome("Fabio");
        p1.setCpf("789");
        p1.setEndereco("Rua estranha");
        e1.getPessoas().add(p1);
        
        Pessoa p2 = new Pessoa();
        p2.setNome("Lucas");
        p2.setCpf("123");
        p2.setEndereco("Rua normal");
        e1.getPessoas().add(p2);
        
        Empresa e2 = new Empresa();
        e2.setNome("IPM");
        e2.setCnpj("456");
        
        dadosEmpresas.add(e1);
        dadosEmpresas.add(e2);
        }
//        System.out.println(dadosEmpresas.toString());
    }

    



 

    

    
}
