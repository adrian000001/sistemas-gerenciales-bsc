/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafico;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author brayme2
 */
public class arista {

    public int getPeso() {
        return peso;
    }

    public boolean isDirección() {
        return dirección;
    }
    private nodo nodoA=null;
    private nodo nodoB=null;    
    private int peso ;
    private boolean dirección;
    private String idArista;
    public arista(nodo nodoA, nodo nodoB) {
        this.nodoA = nodoA;
        this.nodoB = nodoB;
        this.idArista= idAleatoria();
    }
    private String idAleatoria(){
        SecureRandom random = new SecureRandom();        
        String primeraCadena= new BigInteger(40, random).toString(32);
        String segundaCadena = Integer.toString((int)(Math.random()*9999));
        String terceraCadena= Integer.toString((int)(Math.random()*9999));
        String cuartaCadena= new BigInteger(20, random).toString(32);
        String quintaCadena= new BigInteger(60, random).toString(32);  
        String idArista= primeraCadena +"-" +segundaCadena+"-"+terceraCadena+"-"+cuartaCadena+"-"+quintaCadena;
        return idArista;
    }
    public String getIdArista() {
        return idArista;
    }

    public void setIdArista(String idArista) {
        this.idArista = idArista;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
     public void setPesoAumentado(int peso) {
        this.peso = this.peso + peso;
    }
    public arista(nodo nodoA, nodo nodoB, int peso) {
        this.nodoA = nodoA;
        this.nodoB = nodoB;
        this.peso = peso;
    }

    public nodo getNodoA() {
        return nodoA;
    }

    public void setNodoA(nodo nodoA) {
        this.nodoA = nodoA;
    }

    public nodo getNodoB() {
        return nodoB;
    }

    public void setNodoB(nodo nodoB) {
        this.nodoB = nodoB;
    }
    
}
