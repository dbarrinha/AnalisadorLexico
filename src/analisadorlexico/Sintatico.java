
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
        if(token.getTipo() != 2 && !"programa".equals(token.getNome())){
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
        System.out.println("Entrou bloco");
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
        System.out.println("Entrou declara função");
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador ");return;
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
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador para proced");
        }
        token = lexico.lexico();
        if(token.getTipo() == 4 && "(".equals(token.getNome()))parametros_formais();
        
        if(!";".equals(token.getNome())){
            System.out.println("Não encontrado ';' em proced");return;
        }
        token = lexico.lexico();
        bloco();
    }
    
    public void parametros_formais(){
        System.out.println("Entrou parametros formais");
        do{
            if("var" == token.getNome()){
                System.out.println("Encotrado var em parametros");
                token = lexico.lexico();
            }
            if(token.getTipo() == 1){//isso tá certo?
                lista_identificadores();
            }else {
                 System.out.println("Não encontrado identificador");return;
            }
            if(token.getTipo() != 4 && !":".equals(token.getNome())){
                System.out.println("Não encontrado ':' ");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' ");return;
            }
            token = lexico.lexico();
        }while(token.getTipo() == 1 || token.getNome() == "var");
        
        
    }
    
    public void comando_composto(){
        System.out.println("Entrou comando composto");
        token = lexico.lexico();
        if(!"inicio".equals(token.getNome())){
            System.out.println("Não encontrado 'inicio'");return;
        }
        token = lexico.lexico();
        do{
        comando_sem_rotulo();
        token = lexico.lexico();
        if(!";".equals(token.getNome())){
            System.out.println("Não encontrado ';' em comando composto");return;
        }
        token = lexico.lexico();
        }while(!"fim".equals(token.getNome()));
    }
    
    public void comando_sem_rotulo(){
        System.out.println("Entrou comando sem rotulo");
        if(token.getTipo() == 1){
            token = lexico.lexico();
            if(":=".equals(token.getNome()))atribuicao();
            else chamada_proced();
        }
        token = lexico.lexico();
        if("se".equals(token.getNome()))comando_condicional();
        if("enquanto".equals(token.getNome()))comando_repetitivo();
        if("leia".equals(token.getNome()))comando_leia();
        if("imprima".equals(token.getNome()))comando_imprima();
        
    }
    
    public void chamada_proced(){
        
        //tem que incrementar no final
    }
    
    public void atribuicao(){
        token = lexico.lexico();
        expressao();
        
        //tem que incrementar no final
    }
    
    public void comando_condicional(){
        //tem que incrementar no final
    }
    
    public void comando_repetitivo(){
        //tem que incrementar no final
    }
    
    public void comando_leia(){
        //tem que incrementar no final
    }
    
    public void comando_imprima(){
        //tem que incrementar no final
    }
    
    public void expressao(){
        System.out.println("Entrou expressao");
       do{ 
           expressao_simples();
           token = lexico.lexico();
       }while("=".equals(token.getNome()) || "<>".equals(token.getNome()) || "<".equals(token.getNome()) || "<=".equals(token.getNome()) || ">".equals(token.getNome()) || ">=".equals(token.getNome()));
    }
    
    public void expressao_simples(){
        System.out.println("Entrou expressão simples");
        if("+".equals(token.getNome()) || "-".equals(token.getNome())){
            System.out.println("Encontrou "+ token.getNome() + "em espressao simples");
            token = lexico.lexico();
        }
        termo();
       while("+".equals(token.getNome()) || "-".equals(token.getNome()) || "ou".equals(token.getNome())){
           
       
        
        System.out.println("Encontrou "+ token.getNome() + "em espressao simples");
        token = lexico.lexico();
        
        termo();
         };//condição de continuação, se tiver termo
    }
    
    public void termo(){
        
    }
    
    public void fator(){
        
    }
    
    public void chamada_funcao(){
        
    }
    
    public void declara_tipo(){
        token = lexico.lexico();
        do{
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            lista_identificadores();
            token = lexico.lexico();
            if(token.getTipo() != 4 && !"=".equals(token.getNome())){
                System.out.println("Não encontrado '=' ");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' ");return;
            }
            token = lexico.lexico();
        }while(token.getTipo() == 1);
    }
    
    public void declara_var(){
        System.out.println("Entrou definição de variavel");
        token = lexico.lexico();
        do{
            if(token.getTipo() == 1){//isso tá certo?
                lista_identificadores();
            }else {
                 System.out.println("Não encontrado identificador");return;
            }
            
            if(!":".equals(token.getNome())){
                System.out.println("Não encontrado ':' ");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");return;
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' ");return;
            }
            token = lexico.lexico();
            System.out.println("ENCONTROU UMA VARIÁVEL");
        }while(token.getTipo() == 1);
    }
    
    public void lista_identificadores(){
        System.out.println("Entrou Lista Identificadores");
        do {            
            token = lexico.lexico();//testas virgula
            if(!",".equals(token.getNome())){
                System.out.println("Não encontrado ',' em lista identificadores");
                return;
            }
            token = lexico.lexico();
        } while (token.getTipo() == 1);
    }
    
    public static void main(String [] args){
        
        Sintatico s = new Sintatico();
        s.sintatico();
        
        
        
    }
}
