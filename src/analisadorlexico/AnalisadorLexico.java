
package analisadorlexico;

import java.util.ArrayList;


public class AnalisadorLexico {
    
    ArrayList<Token> tokens;
    char caracterAtual;
    String tokenAtual;
    
    public static boolean isDigito(char value){
        return Character.isDigit(value);
    }
    public static boolean isLetra(char value){
        return Character.isLetter(value);
    }
    public static boolean isLetraOuDigito(char value){
        return Character.isLetterOrDigit(value);
    }
    
    
    //testeas de é quebra de linha ou espaço pra terminar tbm
    public static String identificador2(ArrayList<Character> fonte){
        int i = 0;
        int tamanho = fonte.size();
        int estado = 0;
        char c;
        String acc = "";
        
        while(i < tamanho){
            
            switch(estado){
                case 0:{
                    if(isLetra(fonte.get(i)))estado = 1;
                    else if(fonte.get(i)==' ' || fonte.get(i)=='\n')estado = -1;
                    else estado = -1;
                    break;
                }
                case 1:{
                    if(isLetraOuDigito(fonte.get(i)))estado =2;
                    else if(fonte.get(i)=='_' || fonte.get(i)=='$')estado = 3;
                    else if(fonte.get(i)==' ' || fonte.get(i)=='\n')estado = -1;
                    else estado = -1;
                    break;
                }
                case 2:{
                    if(isLetraOuDigito(fonte.get(i)))estado =2;
                    else if(fonte.get(i)==' ' || fonte.get(i)=='\n')estado = -1;
                    else estado = -1;
                    break;
                }
                case 3: {
                    if(isLetraOuDigito(fonte.get(i)))estado =2;
                    else if(fonte.get(i)==' ' || fonte.get(i)=='\n')estado = -1;
                    else estado = -1;
                    break;
                }
                case -1:{
                    System.out.println("Não reconhecido");
                    return acc;
                }
                
            }
            acc += fonte.get(i);
            i++;
        }
        if(estado == 1 || estado == 2){
            System.out.println("Reconheceu: " + acc);
            return acc;
        }
        else{
            System.out.println("Não reconheceu: "+acc);
            return acc;
        }
    }
    
    public static void main(String[] args) {
        ArquivoTxt txt = new ArquivoTxt();
        txt.LerTxt();
        ArrayList<Character> fonte = txt.getTokens();
            char c = fonte.get(0);
            System.out.println(identificador2(fonte));
//            if(txt.isLetra(c)){
//                
//            }else if(txt.isDigito(c) || (c=='-' && txt.isDigito(fonte.get(1)))){
//                //digito
//            }else if(c=='/' || c=='@' || c=='-'){
//                //comentario
//            }else{
//                //simbolos especiais
//            }
                
           
    }
    
}
