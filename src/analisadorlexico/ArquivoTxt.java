
package analisadorlexico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


    
public class ArquivoTxt {
    ArrayList<Character> tokens;

    public ArrayList<Character> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Character> tokens) {
        this.tokens = tokens;
    }
    
    public void EscreverTxt(String s){
        File file = new File("tokens.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
           
                FileWriter fw;
        try {
            fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
           
}   //--Valor 32 = Space
    //--Valor 13 10 quebra de linha
    public void LerTxt(){
            tokens = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader("tokens.txt"));
            int value;
            
            
            while((value = in.read()) != -1){
                
                char c = (char) value;
                
                if(c == '\n' ){//--quebra de linha
                    
                    System.out.println("quebra de linha!");
                    tokens.add('\n');
                }else if(value == 13){//--segundo caracter da quebra de linha,deve ser ignorado
                    
                }else if(Character.isSpace(c)){//--Espaço
                    System.out.println("Space");
                    tokens.add(' ');
                }else{
                    System.out.println("Caracter lido: "+ c + " "+isDigitoOrLetra(c));
                    tokens.add(c);
                }
                
                       
            }
            
            in.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
   
    public String isDigitoOrLetra(char value){
        
        if(Character.isDigit(value)){
            return "Digito";
        }else if(Character.isLetter(value)){
            return "Letra";
        }
        return "nada";
    }
    
    public boolean isDigito(char value){
        return Character.isDigit(value);
    }
    public boolean isLetra(char value){
        return Character.isLetter(value);
    }
    public boolean isLetraOuDigito(char value){
        return Character.isLetterOrDigit(value);
    }
    
}
