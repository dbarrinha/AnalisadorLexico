/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import analisadorlexico.ArquivoTxt;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

    
public class QuestaoUri {
    public void lerTeste(){
        try {
            BufferedReader in = new BufferedReader(new FileReader("teste.txt"));
            String value;
            
            
            while((value = in.readLine()) != null){
                
                
                       
            }
            
            in.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArquivoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void main(){
        
    }
}
