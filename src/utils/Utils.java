package utils;

import model.Empresa;
import model.Pessoa;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fabio
 */
public class Utils {
    
   public String convertePessoaToJson (Pessoa pessoa, String operacao) {
        
        JSONObject pessoaJson = new JSONObject();  
        pessoaJson.put("operacao", "1");
        pessoaJson.put("entidade", "pessoa");
        pessoaJson.put("nome"    , pessoa.getNome());
        pessoaJson.put("cpf"     , pessoa.getCpf());
        pessoaJson.put("endereco", pessoa.getEndereco());

        return pessoaJson.toJSONString();
    }
    
    public Pessoa converteJsonToPessoa(String msg) throws ParseException {
        
        JSONParser parser = new JSONParser(); 
        JSONObject json = (JSONObject) parser. parse(msg);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome((String) json.get("nome"));
        pessoa.setCpf((String) json.get("cpf"));
        pessoa.setEndereco((String) json.get("endereco"));
        
//        System.out.println("teste converteJsonToPessoa: " + pessoa.getNome());
        
        return pessoa;        

    }
    
    public Empresa converteJsonToEmpresa(String msg) throws ParseException {
        
        JSONParser parser = new JSONParser(); 
        JSONObject json = (JSONObject) parser. parse(msg);
        
        Empresa empresa = new Empresa();
        empresa.setNome((String) json.get("nome"));  
        empresa.setCnpj((String) json.get("cnpj"));
        
        System.out.println("teste converteJsonToEmpresa: " + empresa.getNome());
        
        return empresa;        

    }
    
    public String retornaOperacao(String msg) throws ParseException {
        
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();        
        jsonObject = (JSONObject) parser.parse(msg);
        
        String operacao = (String) jsonObject.get("operacao");
        System.out.println("Operacao controller: " + operacao);
        
        return operacao;
    }
    
    public String retornaEntidade(String msg) throws ParseException {
        
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();        
        jsonObject = (JSONObject) parser.parse(msg);
        
        String entidade = (String) jsonObject.get("entidade");
        System.out.println("Entidade controller: " + entidade);
        
        return entidade;
    }
}
