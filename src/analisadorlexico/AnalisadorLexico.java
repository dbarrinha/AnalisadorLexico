
package analisadorlexico;

import java.util.ArrayList;


public class AnalisadorLexico {
    
    ArrayList<Token> tokens;
    
    public void identificador(){
        
    }
    
    public static void main(String[] args) {
        ArquivoTxt txt = new ArquivoTxt();
        txt.LerTxt();
        ArrayList<Character> fonte = txt.getTokens();
        char c = fonte.get(0);
       while(true){ 
            if(txt.isLetra(c)){
                //identificador
            }else if(txt.isDigito(c) || (c=='-' && txt.isDigito(fonte.get(1)))){
                //digito
            }else if(c=='/' || c=='@' || c=='-'){
                //comentario
            }else{
                
            }
                
        }    
    }
    
}
