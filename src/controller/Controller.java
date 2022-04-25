package controller;

import datasource.Datasource;
import org.json.simple.parser.ParseException;
import utils.Utils;

/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class Controller {
    private Utils utils;
    private Datasource datasource;

    public String trataDados(String msg) throws ParseException {
        utils          = new Utils();
        datasource     = new Datasource();
        
        String operacao = utils.retornaOperacao(msg);
        String entidade = utils.retornaEntidade(msg);
        String cnpjEmpresa = utils.retornaCnpjEmpresa(msg);
        String cpfPessoa = utils.retornaCpfPessoa(msg);
        
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
                    return datasource.listaEmpresas();
                }
                 if (entidade.equalsIgnoreCase("todos")) {
                    return datasource.listaTodos();
                }
                break;
            case "2":   //UPDATE
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.atualizaPessoa(msg);
                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.atualizaEmpresa(msg);
                }
                break;
            case "3": //GET
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.buscaPessoa(cpfPessoa);
                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.buscaEmpresa(cnpjEmpresa);
                }
                break;
            case "4":  //DELETE
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.deletarPessoa(cpfPessoa);
                }
                if (entidade.equalsIgnoreCase("empresa")) {
                    return datasource.deletarEmpresa(cnpjEmpresa);
                }
                break;
            case "6":
                if (entidade.equalsIgnoreCase("pessoa")) {
                    return datasource.vincularPessoaToEmpresa(cpfPessoa, cnpjEmpresa);
                }
                break;
            default:
                System.out.println("Default switch case.");
                break;
        }
         return "saiu do switch case controller.";    
    }
    
}
