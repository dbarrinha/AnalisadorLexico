
package analisadorlexico;

import java.util.List;
/*0 = error
    1 = identificador
    2 = palavra reservada
    3 = digito
    4 = simbolo especial
    5 = comentário   */ 

public class Sintatico {
    AnalisadorLexico lexico = new AnalisadorLexico();
    List<Token> tokens;
    Token token;
    public static int index = 0;
    public void sintatico(){
        token = lexico.lexico();
        if(token.getTipo() != 2 && !"programa".equals(tokens.get(index).getNome())){
            System.out.println("Não encontrado a palavra 'programa' ");return;
        }
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado Identificador ");return;
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !";".equals(token.getNome())){
            System.out.println("Não encontrado ';' ");return;
        }
        token = lexico.lexico();
        bloco();
        token = lexico.lexico();
        if(token.getTipo() != 4 && !".".equals(token.getNome())){
            System.out.println("Não encontrado '.' ");return;
        }
    }
    
    public void bloco(){
        if(token.getTipo() == 1 && "tipo".equals(token.getNome()))declara_tipo();
        if(token.getTipo() == 1 && "var".equals(token.getNome()))declara_var();
        
        while((token.getTipo() == 1 && "procedimento".equals(token.getNome()))
                || (token.getTipo() == 1 && "funcao".equals(token.getNome()))){
            
            if("procedimento".equals(token.getNome()))declara_proced();
            else declara_funcao();
        }
        comando_composto();
    }
    
    public void declara_funcao(){
        
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador ");
        }
        token = lexico.lexico();
        if(token.getTipo() == 4 && !"(".equals(token.getNome()) ){
            parametros_formais();
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !":".equals(token.getNome())){
            System.out.println("Não encontrado ':' ");return;
        }
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador ");
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !";".equals(token.getNome())){
            System.out.println("Não encontrado ';' ");return;
        }
        token = lexico.lexico();
        bloco();
    }
    
    public void declara_proced(){
        index++;
        if(tokens.get(index).getTipo() != 1){
            System.out.println("Não encontrado identificador para proced");
        }
    }
    
    public void parametros_formais(){
        
    }
    
    public void comando_composto(){
        
    }
    
    public void declara_tipo(){
        index++;
        do{
            if(tokens.get(index).getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            lista_identificadores();
            index++;
            if(tokens.get(index).getTipo() != 4 && !"=".equals(tokens.get(index).getNome())){
                System.out.println("Não encontrado '=' ");return;
            }
            index++;
            if(tokens.get(index).getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            index++;
            if(tokens.get(index).getTipo() != 4 && !";".equals(tokens.get(index).getNome())){
                System.out.println("Não encontrado ';' ");return;
            }
            index++;
        }while(tokens.get(index).getTipo() == 1);
    }
    
    public void declara_var(){
        index++;
        do{
            if(tokens.get(index).getTipo() == 1){//isso tá certo?
                lista_identificadores();
            }else {
                 System.out.println("Não encontrado identificador");return;
            }
            index++;
            if(tokens.get(index).getTipo() != 4 && !":".equals(tokens.get(index).getNome())){
                System.out.println("Não encontrado ':' ");return;
            }
            index++;
            if(tokens.get(index).getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            index++;
            if(tokens.get(index).getTipo() != 4 && !";".equals(tokens.get(index).getNome())){
                System.out.println("Não encontrado ';' ");return;
            }
            index++;
        }while(tokens.get(index).getTipo() == 1);
    }
    
    public void lista_identificadores(){
        do {            
            index++;//testas virgula
        } while (tokens.get(index).getTipo() == 1);
    }
    
    public static void main(String [] args){
        
        int i = 0;
        
        
        
    }
}
