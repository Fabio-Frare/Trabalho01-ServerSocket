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
    
    public String listaEmpresas() {  
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
    
    public String listaPessoas() {  
        JSONObject jsonPessoas = new JSONObject();
        int i = 0;
        
        for (Pessoa dadosPessoa : dadosPessoa) {
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("cpf", dadosPessoa.getCpf());
            jsonPessoa.put("nome", dadosPessoa.getNome());
            jsonPessoa.put("endereco", dadosPessoa.getEndereco());
            
            jsonPessoas.put(i, jsonPessoa);
            i++;
        }
        
        return jsonPessoas.toJSONString();
    }

    public String listaTodos() {
        String jsonStr = JSONArray.toJSONString(dadosEmpresas);    
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
        return "Empresa com o CNPJ " + cnpj + " n??o encontrada.";  
    }
    
    public String buscaPessoa(String cpfPessoa){
        for(Pessoa p : dadosPessoa){
            if(p.getCpf().equalsIgnoreCase(cpfPessoa)){
                return utils.convertePessoaToJson(p);
            }
        }
        
        return "Pessoa com o CPF: " + cpfPessoa + " n??o encontrada.";
    }
      
    public String deletarEmpresa(String cnpj) { 
        String nomeEmpresa="";
        for (Empresa dadosEmpresa : dadosEmpresas) {
            if(dadosEmpresa.getCnpj().equalsIgnoreCase(cnpj)) {
                nomeEmpresa = dadosEmpresa.getNome();
                if(dadosEmpresa.getPessoas().size() > 0){
                    return "Empresa " + nomeEmpresa + " possui pessoa vinculada n??o sendo poss??vel excluir o registro.";
                } else {
                    dadosEmpresas.remove(dadosEmpresa);                                    
                    return "Empresa " + nomeEmpresa + " exclu??do com sucesso.";
                }
            }
        }   
        return "Empresa com o CNPJ: " + cnpj + " n??o encontrada.";      
    }

    public String deletarPessoa(String cpf) {
        String resposta   = "Pessoa com o CPF: " + cpf + " n??o encontrada.";
        String nomePessoa = "";
        boolean sucesso   = false;
        
        for(int i = 0; i < dadosPessoa.size(); i++){
            if(dadosPessoa.get(i).getCpf().equalsIgnoreCase(cpf)){
                nomePessoa = dadosPessoa.get(i).getNome();
                dadosPessoa.remove(i);
                sucesso = true;
                break;
            }
        }
        
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            List<Pessoa> listaPessoas = new ArrayList<>();                             
            listaPessoas = dadosEmpresas.get(i).getPessoas();
            for (int j = 0; j < listaPessoas.size(); j++) {
                if(listaPessoas.get(j).getCpf().equalsIgnoreCase(cpf)) {
                    dadosEmpresas.get(i).getPessoas().remove(j);
                }
            }    
        }
        
        if(sucesso){
            resposta = "Pessoa " + nomePessoa + " exclu??do com sucesso.";
        }
        return resposta;
    }
    
    public String atualizaEmpresa(String msg) throws ParseException {
        empresa = new Empresa();
        empresa = utils.converteJsonToEmpresa(msg);
        String cnpjEmpresa = empresa.getCnpj();
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            if(dadosEmpresas.get(i).getCnpj().equalsIgnoreCase(cnpjEmpresa)) {
               dadosEmpresas.get(i).setNome(empresa.getNome());
               return "Empresa com o CNPJ: " + cnpjEmpresa + " atualizada."; 
            }
        }   
        return "Empresa com o CNPJ: " + cnpjEmpresa + " n??o encontrada.";      
    }
    
    public String atualizaPessoa(String msg) throws ParseException {
        pessoa = new Pessoa();
        pessoa = utils.converteJsonToPessoa(msg);
        String cpfPessoa = pessoa.getCpf();
        String resposta = "Pessoa com o CPF: " + cpfPessoa + " n??o encontrada.";
        boolean sucesso = false;
        
        for(int i = 0; i < dadosPessoa.size(); i++){
            if(dadosPessoa.get(i).getCpf().equalsIgnoreCase(cpfPessoa)) {
                dadosPessoa.set(i, pessoa);
                sucesso = true;
                break;
            }
        }
        
        for (int i = 0; i < dadosEmpresas.size(); i++) {
            List<Pessoa> listaPessoas = new ArrayList<>();
            listaPessoas = dadosEmpresas.get(i).getPessoas();
            for (int j = 0; j < listaPessoas.size(); j++) {
                if(listaPessoas.get(j).getCpf().equalsIgnoreCase(cpfPessoa)) {
                    listaPessoas.get(j).setNome(pessoa.getNome());
                    listaPessoas.get(j).setEndereco(pessoa.getEndereco());
                }
            } 
        }
        
        if(sucesso){
            resposta = "Pessoa com o CPF: " + cpfPessoa + " atualizada.";
        }
        
        return resposta;
    }

    // M??todo auxiliar para mockar os dados para testes.
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
            if(e.getCnpj().equalsIgnoreCase(cnpjEmpresa)){
                return e;
            }
        }
        
        return new Empresa();
    }
    
    private Pessoa getPessoaFromCpf(String cpfPessoa){
        for(Pessoa p : dadosPessoa){
            if(p.getCpf().equalsIgnoreCase(cpfPessoa)){
                return p;
            }
        }
        
        return new Pessoa();
    }

    public String vincularPessoaToEmpresa(String cpfPessoa, String cnpjEmpresa) {
        String resposta = "N??o foi poss??vel vincular esta pessoa";
        
        if((buscaPessoa(cpfPessoa)).equalsIgnoreCase("Pessoa com o CPF: " + cpfPessoa + " n??o encontrada.")){
            Empresa empresa = getEmpresaFromCnpj(cnpjEmpresa);
            Pessoa pessoa   = getPessoaFromCpf(cpfPessoa);
            
            if(empresa.getCnpj() != null && pessoa.getCpf() != null){
                empresa.getPessoas().add(pessoa);
                resposta = "Cpf " + cpfPessoa + " associado a empresa " + empresa.getNome();
            }
        }
        else{
            resposta = "Esta pessoa j?? esta vinculada a uma empresa";
        }
        
        return resposta;
    }
    
}
