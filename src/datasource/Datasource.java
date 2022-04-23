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
    private static final List<Pessoa>  dadosPessoa   = new ArrayList();
    private String lista = ""; 

    public Datasource() {
        this.utils = new Utils();
        populaBanco();
    }

    public String addPessoa(String msg) throws ParseException {   
        pessoa = utils.converteJsonToPessoa(msg);
        
        dadosPessoa.add(pessoa);
        
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
        int i = 0;
        
        for (Empresa dadosEmpresa : dadosEmpresas) {
            JSONObject jsonEmpresa = new JSONObject();
            jsonEmpresa.put("cnpj", dadosEmpresa.getCnpj());
            jsonEmpresa.put("nome", dadosEmpresa.getNome());
            jsonEmpresa.put("qtde", dadosEmpresa.getQtdeFuncionarios());
            
            jsonEmpresas.put(i, jsonEmpresa);
            i++;
        }
        
        return jsonEmpresas.toJSONString();
    }
    
//    public String ListaEmpresas() {       
//        JSONObject jsonEmpresas = new JSONObject();  
//        if(dadosEmpresas.isEmpty()) {
//            return "Não existem empresas cadastradas.";
//        } else {
//            for (Empresa dadosEmpresa : dadosEmpresas) {
//            jsonEmpresas.put("cnpj", dadosEmpresa.getCnpj());
//            jsonEmpresas.put("nome", dadosEmpresa.getNome());
//            jsonEmpresas.put("qtde", dadosEmpresa.getQtdeFuncionarios());
//            lista += jsonEmpresas.toJSONString();
//        }   
//        return lista; 
//        }           
//    }
    
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
//            System.out.println("Lista todos: " + jsonStr);
           
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
      
    public String deletarEmpresa(String cnpj) { 
        String nomeEmpresa="";
        for (Empresa dadosEmpresa : dadosEmpresas) {
            if(dadosEmpresa.getCnpj().equalsIgnoreCase(cnpj)) {
                nomeEmpresa = dadosEmpresa.getNome();
                if(dadosEmpresa.getPessoas().size() > 0){
                    return "Empresa " + nomeEmpresa + " possui pessoa vinculada não sendo possível excluir o registro.";
                } else {
                    dadosEmpresas.remove(dadosEmpresa);                                    
                    return "Empresa " + nomeEmpresa + " excluído com sucesso.";
                }
            }
        }   
        return "Empresa com o CNPJ: " + cnpj + " não encontrada.";      
    }
    
    public String deletarPessoa(String cpf) {
         for (int i = 0; i < dadosEmpresas.size(); i++) {
            List<Pessoa> listaPessoas = new ArrayList<>();                             
            listaPessoas = dadosEmpresas.get(i).getPessoas();
            for (int j = 0; j < listaPessoas.size(); j++) {
                if(listaPessoas.isEmpty()) {
                    return "Não existem resgistros de pessoas cadastradas.";
                }
                if(listaPessoas.get(j).getCpf().equalsIgnoreCase(cpf)) {
                    String nomePessoa =listaPessoas.get(j).getNome();
                    dadosEmpresas.get(i).getPessoas().remove(j);
                    return "Pessoa " + nomePessoa + " excluído com sucesso.";
                } 
            }    
        } 
        return "Pessoa com o CPF: " + cpf + " não encontrada.";
    }
    
    public String atualizaEmpresa(String msg) throws ParseException {
        empresa = new Empresa();
        empresa = utils.converteJsonToEmpresa(msg);
        String cnpjEmpresa = empresa.getCnpj();
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            if(dadosEmpresas.get(i).getCnpj().equalsIgnoreCase(cnpjEmpresa)) {
               dadosEmpresas.get(i).setNome(empresa.getNome());
            }
            return "Empresa com o CNPJ: " + cnpjEmpresa + " atualizada."; 
        }   
        return "Empresa com o CNPJ: " + cnpjEmpresa + " não encontrada.";      
    }
    
    public String atualizaPessoa(String msg) throws ParseException {
        pessoa = new Pessoa();
        pessoa = utils.converteJsonToPessoa(msg);
        String cpfPessoa = pessoa.getCpf();
        
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            List<Pessoa> listaPessoas = new ArrayList<>();                             
            listaPessoas = dadosEmpresas.get(i).getPessoas();
            for (int j = 0; j < listaPessoas.size(); j++) {
                if(listaPessoas.get(j).getCpf().equalsIgnoreCase(cpfPessoa)) {
                    listaPessoas.get(j).setNome(pessoa.getNome());
                    listaPessoas.get(j).setEndereco(pessoa.getEndereco());
                    return "Pessoa com o CPF: " + cpfPessoa + " atualizada."; 
                }
            } 
        } 
        return "Pessoa com o CPF: " + cpfPessoa + " não encontrada.";
    }

    // Método auxiliar para mockar os dados para testes.
    private void populaBanco() {        
        if(dadosEmpresas.isEmpty()) {
            Empresa e1 = new Empresa();
            e1.setNome("Singular");
            e1.setCnpj("123");

            Pessoa p1 = new Pessoa();
            p1.setNome("Fabio");
            p1.setCpf("123");
            p1.setEndereco("Rua Presidente Getulio");
            e1.getPessoas().add(p1);

            Pessoa p2 = new Pessoa();
            p2.setNome("Lucas");
            p2.setCpf("456");
            p2.setEndereco("Rua Ibirama");
            e1.getPessoas().add(p2);

            Empresa e2 = new Empresa();
            e2.setNome("IPM");
            e2.setCnpj("456");
            
            Pessoa p3 = new Pessoa();
            p3.setNome("Marcos");
            p3.setCpf("789");
            p3.setEndereco("Rua Blumenau");
            e2.getPessoas().add(p3);

            dadosEmpresas.add(e1);
            dadosEmpresas.add(e2);
            
            dadosPessoa.add(p1);
            dadosPessoa.add(p2);
            dadosPessoa.add(p3);
        }
    }
    
    private Empresa getEmpresaFromCnpj(String cnpjEmpresa){
        for(Empresa e : dadosEmpresas){
            if(e.getCnpj() == cnpjEmpresa){
                return e;
            }
        }
        
        return new Empresa();
    }
    
    private Pessoa getPessoaFromCpf(String cpfPessoa){
        for(Pessoa p : dadosPessoa){
            if(p.getCpf() == cpfPessoa){
                return p;
            }
        }
        
        return new Pessoa();
    }

    public String vincularPessoaToEmpresa(String cpfPessoa, String cnpjEmpresa) {
        String resposta = "Não foi possível vincular esta pessoa";
        
        if(buscaPessoa(cpfPessoa) == "Pessoa com o CPF: " + cpfPessoa + " não encontrada."){
            Empresa empresa = getEmpresaFromCnpj(cnpjEmpresa);
            Pessoa pessoa   = getPessoaFromCpf(cpfPessoa);
            
            if(empresa.getCnpj() != null && pessoa.getCpf() != null){
                empresa.getPessoas().add(pessoa);
            }
        }
        else{
            resposta = "Esta pessoa já esta vinculada a uma empresa";
        }
        
        return resposta;
    }
    
}
