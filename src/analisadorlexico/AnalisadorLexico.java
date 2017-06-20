
package analisadorlexico;

import java.util.ArrayList;


public class AnalisadorLexico {
    
    
    char caracterAtual;
    String tokenAtual;
    public static int index = 0;
    
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
    public static void identificador2(ArrayList<Character> fonte){
        
        int tamanho = fonte.size();
        int estado = 0;
        int flag = 0;
        String acc = "";
        
        do{
            switch(estado){
                case 0:{
                    if(isLetra(fonte.get(index))){
                        estado = 1;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 1:{
                    if(isLetraOuDigito(fonte.get(index))){
                        estado =2;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index)=='_' || fonte.get(index)=='$'){
                        estado = 3;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 2:{
                    if(isLetraOuDigito(fonte.get(index))){
                        estado =2;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 3: {
                    if(isLetraOuDigito(fonte.get(index))){
                        estado =2;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                
                
            }   
            
        }while(index < tamanho && flag != 1);
        if(estado == 1 || estado == 2){
            if(PalavrasReservadas.isPlavraReservada(acc))System.out.println("Reconheceu: " + acc + " -> Palavra Reservada");
            else System.out.println("Reconheceu: " + acc + " -> Identificador");
            
        }
        else{
            System.out.println("Não reconheceu: "+acc);
            
        }
    }
    public static void digitos(ArrayList<Character> fonte){
        
        int tamanho = fonte.size();
        int estado = 0;
        int flag = 0;
        String acc = "";
        
        do{
            switch(estado){
                case 0:{//1
                    if(fonte.get(index) == '-'){
                        estado = 1;acc += fonte.get(index);index++;
                    }else if(isDigito(fonte.get(index))){
                        estado = 2;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 1:{//2
                    if(isDigito(fonte.get(index))){
                        estado =2;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 2:{//3
                    if(isDigito(fonte.get(index))){
                        estado =2;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index) == ','){
                        estado =3;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 3: {//4
                    if(isDigito(fonte.get(index))){
                        estado =4;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 4: {//5
                    if(isDigito(fonte.get(index))){
                        estado =4;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                
                
                
            }   
            
        }while(index < tamanho && flag != 1);
        if(estado == 2 || estado == 4){
            System.out.println("Reconheceu: " + acc + " -> Digito");
            
        }
        else{
            System.out.println("Não reconheceu: "+acc);
            
        }
    }
    
    public static void simbolosEspeciais(ArrayList<Character> fonte){
        int tamanho = fonte.size();
        int estado = 0;
        int flag = 0;
        String acc = "";
        
        do{
            switch(estado){
                case 0:{
                    if(fonte.get(index) == ';' || fonte.get(index) == ',' || fonte.get(index) == '.' || fonte.get(index) == '*' 
                            || fonte.get(index) == '(' || fonte.get(index) == ')' || fonte.get(index) == '/' 
                            || fonte.get(index) == '{' || fonte.get(index) == '}' || fonte.get(index) == '@' ){
                        estado = 1;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index) == '+' || fonte.get(index) == '-' 
                            || fonte.get(index) == '>' || fonte.get(index) == ':'){
                        estado = 2;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index)=='<'){
                        estado= 4;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 1:{
                    flag = 1;
                    break;
                }
                case 2:{
                    if(fonte.get(index)=='='){
                        estado = 3;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 3: {
                    flag = 1;
                    break;
                }
                case 4: {
                    if(fonte.get(index)=='>'){
                        estado =5;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 5: {
                    flag = 1;
                    break;
                }
                
                
            }   
            
        }while(index < tamanho && flag != 1);
        if(estado == 1 || estado == 2 || estado == 3 || estado == 4 || estado == 5){
            System.out.println("Reconheceu: " + acc + " -> Simbolo Especial");
            
        }
        else{
            System.out.println("Não reconheceu: "+acc);
            
        }
    }
            
    public static void main(String[] args) {
        ArquivoTxt txt = new ArquivoTxt();
        txt.LerTxt();
        ArrayList<Character> fonte = txt.getTokens();
        
        //está pulando o espaço
            
        while(index < fonte.size()){
            if(isLetra(fonte.get(index))){
                identificador2(fonte);
            }else if(isDigito(fonte.get(index)) || (fonte.get(index)=='-' && isDigito(fonte.get(index+1)))){
                digitos(fonte);
            }else if(Character.isWhitespace(fonte.get(index))){
                System.out.println("#########Pulou por ser espaço em branco##########\n");
                index++;
            }else if(PalavrasReservadas.isSimbolo(fonte.get(index))){
                simbolosEspeciais(fonte);
            }else{
                System.out.println("ERROR!");
                index=fonte.size()+1;
            }
        }

                
           
    }
    
}
