
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
        System.out.println("**ENTROU SINTÁTICO");
        token = lexico.lexico();
        if(token.getTipo() != 2 && !"programa".equals(token.getNome())){
            System.out.println("Não encontrado a palavra 'programa' ");error();
        }
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado Identificador Após programa");error();
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !";".equals(token.getNome())){
            System.out.println("Não encontrado ';' ");error();
        }
        token = lexico.lexico();
        bloco();
        token = lexico.lexico();
        if(token.getTipo() != 4 && !".".equals(token.getNome())){
            System.out.println("Não encontrado '.' ");error();
        }
        System.out.println("**PROGRAMA FINALIZADO CORRETAMENTE!");
    }
    
    public void bloco(){
        System.out.println("##ENTROU BLOCO");
        if(token.getTipo() == 2 && "tipo".equals(token.getNome()))declara_tipo();
        if(token.getTipo() == 2 && "var".equals(token.getNome()))declara_var();
        
        while("procedimento".equals(token.getNome()) || "funcao".equals(token.getNome()) ){
            
            if("procedimento".equals(token.getNome()))declara_proced();
            else declara_funcao();
        }
        comando_composto();
        System.out.println("##ENTROU BLOCO");
    }
    
    public void declara_funcao(){
        System.out.println("ENTROU DECLARA FUNÇÃO");
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador ");error();
        }
        token = lexico.lexico();
        if(token.getTipo() == 4 && !"(".equals(token.getNome()) ){
            parametros_formais();
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !":".equals(token.getNome())){
            System.out.println("Não encontrado ':' ");error();
        }
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador ");
        }
        token = lexico.lexico();
        if(token.getTipo() != 4 && !";".equals(token.getNome())){
            System.out.println("Não encontrado ';' ");error();
        }
        token = lexico.lexico();
        bloco();
    }
    
    public void declara_proced(){
        System.out.println("ENTROU DECLARA PROCEDIMENTO");
        token = lexico.lexico();
        if(token.getTipo() != 1){
            System.out.println("Não encontrado identificador para proced");
        }
        token = lexico.lexico();
        if(token.getTipo() == 4 && "(".equals(token.getNome()))parametros_formais();
        
        if(!";".equals(token.getNome())){
            System.out.println("Não encontrado ';' em proced");error();
        }
        token = lexico.lexico();
        bloco();
    }
    
    public void parametros_formais(){
        System.out.println("ENTROU PARAMETROS FORMAIS");
        do{
            if("var" == token.getNome()){
                System.out.println("Encotrado var em parametros");
                token = lexico.lexico();
            }
            if(token.getTipo() == 1){//isso tá certo?
                lista_identificadores();
            }else {
                 System.out.println("Não encontrado identificador");error();
            }
            if(token.getTipo() != 4 && !":".equals(token.getNome())){
                System.out.println("Não encontrado ':' ");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' ");error();
            }
            token = lexico.lexico();
        }while(token.getTipo() == 1 || token.getNome() == "var");
        
        
    }
    
    public void comando_composto(){
        System.out.println("--ENTROU COMANDO COMPOSTO");
        if(!"inicio".equals(token.getNome())){
            System.out.println("Não encontrado 'inicio'");error();
        }
        token = lexico.lexico();
        do{
        comando_sem_rotulo();
        if(!";".equals(token.getNome())){
            System.out.println("Não encontrado ';' em comando composto");error();
        }
            
        token = lexico.lexico();
        }while(!"fim".equals(token.getNome()));
        System.out.println("--FIM DE COMANDO COMPOSTO");
    }
    
    public void comando_sem_rotulo(){
        if(token.getTipo() == 1){
            token = lexico.lexico();
            if(":=".equals(token.getNome()))atribuicao();
            else chamada_proced();
        }
        
        if("se".equals(token.getNome()))comando_condicional();
        if("enquanto".equals(token.getNome()))comando_repetitivo();
        if("leia".equals(token.getNome()))comando_leia();
        if("imprima".equals(token.getNome()))comando_imprima();
        
    }
    
    public void chamada_proced(){
        System.out.println("ENTROU CHAMADA PROCEDIMENTO");
        if("(".equals(token.getNome())){
            lista_expressoes();
            token = lexico.lexico();
        }
        System.out.println("FIM CHAMADA PROCEDIMENTO");
    }
    
    public void atribuicao(){
        System.out.println("ENTROU ATRIBUIÇÃO");
        token = lexico.lexico();
        expressao();
        token = lexico.lexico();
        System.out.println("FIM ATRIBUIÇÃO");
    }
    
    public void comando_condicional(){
        System.out.println("ENTROU COMANDO CONDICIONAL");
        token = lexico.lexico();
        expressao();
        if(!"entao".equals(token.getNome())){
            System.out.println("Não encontrado 'entao' em comando condicional");error();
        }
        token = lexico.lexico();
        comando_sem_rotulo();
        token = lexico.lexico();//se não for o senão, vai incrementar sem precisar, ai vai alscar lá na frente
        if("senao".equals(token.getNome())){
            token = lexico.lexico();
            comando_sem_rotulo();
            token = lexico.lexico();
        }
    }
    
    public void comando_repetitivo(){
        System.out.println("ENTROU COMANDO REPETITIVO");
        token = lexico.lexico();
        expressao();
        if(!"do".equals(token.getNome())){
            System.out.println("Não encontrado 'do' em comando repetitivo");error();
        }
        token = lexico.lexico();
        comando_sem_rotulo();
        token = lexico.lexico();
    }
    
    public void comando_leia(){
        System.out.println("ENTROU COMANDO LEIA");
        token = lexico.lexico();
        if(!"(".equals(token.getNome())){
            System.out.println("Não encontrado '(' em comando leia");error();
        }
        token = lexico.lexico();
        if(token.getTipo() == 1){
            lista_identificadores();
        }else {
            System.out.println("Não encontrado identificador em comando leia");error();
        }
        if(!")".equals(token.getNome())){
            System.out.println("Não encontrado ')' em comando leia");error();
        }
        token = lexico.lexico();
        System.out.println("LEIA FINALIZADA COM SUCESSO");
    }
    
    public void comando_imprima(){
        System.out.println("ENTROU COMANDO IMPRIMA");
        token = lexico.lexico();
        if(!"(".equals(token.getNome())){
            System.out.println("Não encontrado '(' em comando imprima");error();
        }
        token = lexico.lexico();
        lista_expressoes();
        if(!")".equals(token.getNome())){
            System.out.println("Não encontrado ')' em comando imprima");error();
        }
        token = lexico.lexico();
    }
    
    public void lista_expressoes(){
        do{
            expressao();
        }while(";".equals(token.getNome()));
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
        do{
            if(!"+".equals(token.getNome()) || !"-".equals(token.getNome()) || !"ou".equals(token.getNome())){
                System.out.println("Não encontrado + - ou em expressão simples");error();
            }  
            token = lexico.lexico();
            termo();
        }while("+".equals(token.getNome()) || "-".equals(token.getNome()) || "ou".equals(token.getNome()));
    }
    
    public void termo(){
        fator();
        do{
            if(!"*".equals(token.getNome()) || !"div".equals(token.getNome()) || !"e".equals(token.getNome())){
             System.out.println("Não encontrado * div e em termo");error();
            }  
            token = lexico.lexico();
            fator();
        }while("div".equals(token.getNome()) || "*".equals(token.getNome()) || "e".equals(token.getNome()));
        
    }
    
    public void fator(){
        if(token.getTipo() == 1){
            token = lexico.lexico();
            if("(".equals(token.getNome())){
                chamada_funcao();
                token = lexico.lexico();// DECIDIR DEPOIS EM CHAMADA DE FUNÇÃO
            }
        }else if(token.getTipo() == 3){
            System.out.println("Encontrado digito em Fator");
            token = lexico.lexico();
        }else if("(".equals(token.getNome())){
            expressao();
        }
    }
    
    public void chamada_funcao(){
        lista_expressoes();
        if(!")".equals(token.getNome())){
            System.out.println("Não encontrado ')' em chamada de função");error();
        }
    }
    
    public void declara_tipo(){
        token = lexico.lexico();
        do{
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");error();
            }
            token = lexico.lexico(); 
            if(token.getTipo() != 4 && !"=".equals(token.getNome())){
                System.out.println("Não encontrado '=' ");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado tipo identificador");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' em declaração de tipo");error();
            }
            token = lexico.lexico();
        }while(token.getTipo() == 1);
    }
    
    public void declara_var(){
        System.out.println("ENTROU DECLARA VARIAVEL");
        token = lexico.lexico();
        do{
            if(token.getTipo() == 1){//isso tá certo?
                lista_identificadores();
            }else {
                 System.out.println("Não encontrado identificador");error();
            }
            
            if(!":".equals(token.getNome())){
                System.out.println("Não encontrado ':' ");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador");error();
            }
            token = lexico.lexico();
            if(token.getTipo() != 4 && !";".equals(token.getNome())){
                System.out.println("Não encontrado ';' ");error();
            }
            token = lexico.lexico();
            System.out.println("ENCONTROU UMA VARIÁVEL");
        }while(token.getTipo() == 1);
    }
    
    public void lista_identificadores(){
        System.out.println("ENTROU LISTA IDENTIFICADORES");
        token = lexico.lexico();
        while(",".equals(token.getNome())){            
            token = lexico.lexico();
            if(token.getTipo() != 1){
                System.out.println("Não encontrado identificador após ',' ");error();
            }
            token = lexico.lexico();
        }
    }
    
    public void error(){
        System.exit(0);
    }
    
    public static void main(String [] args){
        
        Sintatico s = new Sintatico();
        s.sintatico();
        
        
        
    }
}
