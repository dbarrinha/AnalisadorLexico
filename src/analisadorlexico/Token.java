/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

/**
 *
 * @author danilo.barrinha
 */
public class Token {
    private String nome;
    private int tipo;
    /*0 = error
    1 = identificador
    2 = palavra reservada
    3 = digito
    4 = simbolo especial
    5 = comentário   */     
                
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
