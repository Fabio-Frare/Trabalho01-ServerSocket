package controller;

import datasource.Datasource;
import org.json.simple.parser.ParseException;
import utils.Utils;

/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class Controller {
    // {"entidade":"pessoa","endereco":"dasdsa","operacao":1,"cpf":"sadas","nome":"ada"}
    private Utils utils;
    private Datasource datasource;

    public String trataDados(String msg) throws ParseException {
        utils          = new Utils();
        datasource     = new Datasource();
        
        String operacao = utils.retornaOperacao(msg);
        String entidade = utils.retornaEntidade(msg);
        String cnpjEmpresa = utils.retornaCnpjEmpresa(msg);
        
         switch (operacao) {
            case "1":
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.addPessoa(msg);
                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.addEmpresa(msg);
                }   
                break;
            case "5":
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.listaPessoas();
                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.ListaEmpresas();
                }
                 if (entidade.equalsIgnoreCase("todos")) {
                    return datasource.listaTodos();
                }
                break;
            case "6":
                return datasource.ListaEmpresas();
//            case "UPDATE":
//                if (entidade.equalsIgnoreCase("1")) {
//                    msg = datasource.atualizaPessoa(msg);
//                    enviarDados(msg);
//                }
//                if (entidade.equalsIgnoreCase("2")) {
//                    msg = datasource.atualizaEmpresa(msg);
//                    enviarDados(msg);
//                }
//                break;
            case "3":
//                if (entidade.equalsIgnoreCase("pessoa")) {
//                    return datasource.listaPessoas();
//                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.BuscaEmpresa(cnpjEmpresa);
                }
//                if (entidade.equalsIgnoreCase("1")) {
//                    String cpf = msg.substring(7, msg.length());
//                    msg = datasource.buscaPessoa(cpf);
//                    enviarDados(msg);
//                }
//                if (entidade.equalsIgnoreCase("2")) {
//                    String cnpj = msg.substring(7, msg.length());
//                    msg = datasource.buscaEmpresa(cnpj);
//                    enviarDados(msg);
//                }
//                break;
            case "DELETE":
//                if (entidade.equalsIgnoreCase("1")) {
//                    String cpf = msg.substring(7, msg.length());
//                    msg = datasource.deletaPessoa(cpf);
//                    enviarDados(msg);
//                }
//                if (entidade.equalsIgnoreCase("2")) {
//                    String cnpj = msg.substring(7, msg.length());
//                    System.out.println("CNPJ: " + cnpj);
//                    msg = datasource.deletaEmpresa(cnpj);
//                    enviarDados(msg);
//                }
//                break;
            case "LIST**":
//                if (entidade.equalsIgnoreCase("1")) {
//                    msg = datasource.ListaPessoas();
//                    enviarDados(msg);
//                    receberDados();
//                }
//                if (entidade.equalsIgnoreCase("2")) {
//                    msg = datasource.ListaEmpresas();
//                    enviarDados(msg);
//                    receberDados();
//                }
//                break;
            default:
                System.out.println("Default switch case.");
                break;
        }
         return "saiu do switch case controller.";
    
    }
    
    
    
}
