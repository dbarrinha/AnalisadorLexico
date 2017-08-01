
package analisadorlexico;

import java.util.ArrayList;
import java.util.List;


public class AnalisadorLexico {
    
    private List<Token> tokens = new ArrayList();
    public static int index = 0;
    ArrayList<Character> fonte;
    
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
    public Token identificador2(ArrayList<Character> fonte){
        
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
        Token token = new Token();
        if(estado == 1 || estado == 2){
            if(PalavrasReservadas.isPlavraReservada(acc)){
                //System.out.println("Reconheceu: " + acc + " -> Palavra Reservada");
                
                token.setNome(acc);
                token.setTipo(2);
                return token;
                
            }
            else {
                //System.out.println("Reconheceu: " + acc + " -> Identificador");
                token.setNome(acc);
                token.setTipo(1);
                return token;
            }
            
        }
        else{
            //System.out.println("Não reconheceu: "+acc);
            token.setTipo(0);
            return token;
        }
    }
    public  Token digitos(ArrayList<Character> fonte){
        
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
        Token token = new Token();
        if(estado == 2 || estado == 4){
            //System.out.println("Reconheceu: " + acc + " -> Digito");
                token.setNome(acc);
                token.setTipo(3);return token;
                
        }
        else{
            //System.out.println("Não reconheceu: "+acc);
            token.setTipo(0);return token;
        }
    }
    
    public  Token simbolosEspeciais(ArrayList<Character> fonte){
        int tamanho = fonte.size();
        int estado = 0;
        int flag = 0;
        String acc = "";
        
        do{
            switch(estado){
                case 0:{
                    if(fonte.get(index) == ';' || fonte.get(index) == ',' || fonte.get(index) == '=' || fonte.get(index) == '.' || fonte.get(index) == '*' 
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
                    if(fonte.get(index)=='>' || fonte.get(index)=='='){
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
        Token token = new Token();
        if(estado == 1 || estado == 2 || estado == 3 || estado == 4 || estado == 5){
            //System.out.println("Reconheceu: " + acc + " -> Simbolo Especial");
                token.setNome(acc);
                token.setTipo(4);return token;
        }
        else{
            //System.out.println("Não reconheceu: "+acc);
            token.setTipo(0);return token;
        }
    }
    
    public  Token comentarios(ArrayList<Character> fonte){
        int tamanho = fonte.size();
        int estado = 1;
        int flag = 0;
        String acc = "";
        String textoComentado = "";
        do{
            switch(estado){
                case 1:{
                    if(fonte.get(index) == '/' ){
                        estado = 2;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index) == '-'){
                        estado = 6;acc += fonte.get(index);index++;
                    }
                    else if(fonte.get(index)=='@'){
                        estado= 10;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 2:{
                    if(fonte.get(index) == '/' ){
                        estado = 3;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 3:{
                    if(fonte.get(index)=='/'){
                        estado = 4;acc += fonte.get(index);index++;
                    }
                    else {
                        estado =3;
                        textoComentado += fonte.get(index);//acc += fonte.get(index);
                        index++;
                    }
                    break;
                }
                case 4:{
                    if(fonte.get(index)=='/'){
                        estado = 5;acc += fonte.get(index);index++;
                    }
                    else estado =3;
                    break;
                }
                case 5:{
                    flag = 1;
                    break;
                }
                case 6:{
                    if(fonte.get(index)=='-'){
                        estado = 7;
                        acc += fonte.get(index);
                        index++;
                    }
                    else flag = 1;
                    break;
                }
                case 7:{
                    if(fonte.get(index)=='-'){
                        estado = 8;
                        acc += fonte.get(index);
                        index++;
                    }
                    else {
                        estado =7;
                        textoComentado += fonte.get(index);//acc += fonte.get(index);
                        index++;
                    }
                    break;
                }
                case 8:{
                    if(fonte.get(index)=='-'){
                        estado = 9;
                        acc += fonte.get(index);
                        index++;
                    }
                    else estado=7;
                    break;
                }
                case 9:{
                    flag = 1;
                    break;
                }
                case 10:{
                    if(fonte.get(index)=='@'){
                        estado = 11;acc += fonte.get(index);index++;
                    }
                    else flag = 1;
                    break;
                }
                case 11:{
                    if(fonte.get(index)=='\n'){
                        estado = 12;index++;
                    }
                    else {
                        estado=11;
                        textoComentado += fonte.get(index);//acc += fonte.get(index);
                        index++;
                    }
                    break;
                }
                case 12:{
                    flag = 1;
                    break;
                }
            }
        }while(index < tamanho && flag != 1);
        Token token = new Token();
        if(estado == 5 || estado == 9 || estado == 12 ){
            //System.out.println("Reconheceu: " + acc + " -> Comentário");
            //System.out.println("Texto Comentado: " + textoComentado );
                token.setNome(acc);
                token.setTipo(5);return token;
        }
        else{
            //System.out.println("Não reconheceu comentário: "+acc + "esperado a finalização do comentário");
            token.setTipo(0);return token;
        }
    }
    public AnalisadorLexico(){
        ArquivoTxt txt = new ArquivoTxt();
        txt.LerTxt();
        fonte = txt.getTokens();
    }
    public Token lexico(){
        
            
        
            Token token = new Token();
            if(index == fonte.size()){
                
                token.setTipo(0);
                return token;
            }
            if(isLetra(fonte.get(index))){
                token =identificador2(fonte);
            }else if(isDigito(fonte.get(index)) || (fonte.get(index)=='-' && isDigito(fonte.get(index+1)))){
                token =digitos(fonte);
            }else if(Character.isWhitespace(fonte.get(index))){
                
                index++;token = lexico();
            }
            else if(fonte.get(index)=='/' && fonte.get(index+1)=='/' || fonte.get(index)=='-' && fonte.get(index+1)=='-' || fonte.get(index)=='@' && fonte.get(index+1)=='@'){
                token =comentarios(fonte);
            }
            else if(PalavrasReservadas.isSimbolo(fonte.get(index))){
                token =simbolosEspeciais(fonte);
            }else{
                token.setTipo(0);
                System.out.println("ERROR - CARACTER INVÁLIDO!");
            }
            
        return token;
    }
   

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    
}
