
package analisadorlexico;

public class PalavrasReservadas {
    
    static String [] palavrasreservadas = {"programa","se","então"
            ,"senão","enquanto","faca","ate","repita","inteiro"
            ,"real","caractere","caso","escolha","fim","procedimento"
            ,"função","de","para","inicio"};
    
    
    
    
    
    
    static Character[] simbolos = {';',',','.','+','-','*','(',')','<','>',':','=','{','}','/','@'};
    public static boolean isPlavraReservada(String palavra){
        for(int i=0;i < palavrasreservadas.length;i++){
            if(palavra.equalsIgnoreCase(palavrasreservadas[i]))return true;
        }
        return false;
    }
    public static boolean isSimbolo(Character c){
        for(int i=0;i < simbolos.length;i++){
            if(c.equals(simbolos[i]))return true;
        }
        return false;
    }
}
