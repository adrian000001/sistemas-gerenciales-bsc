/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafico;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brayme2
 */
public class nodo {
    private String nombreNodo;
    private String label;
    private String color;
    private String tipoArea;
    private int valorEuristica;
    private boolean visitado=false;
    private String idNodo;//El id que se tiene en el Json.
    private List<arista> listaAristas = new ArrayList<arista>();
    private int costoAestrella=0;
    private int costoCaminoAestrella=0;
    public String getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(String idNodo) {
        this.idNodo = idNodo;
    }
    
    
    public nodo(String nombreNodo, int valorEuristica) {
        this.nombreNodo = nombreNodo;
        this.valorEuristica = valorEuristica;
        this.visitado = false;
        this.idNodo = idAleatoria();
    }

     public nodo(String nombreNodo) {
        this.nombreNodo = nombreNodo;        
    }
     public nodo(String nombreNodo, String label, String color, String tipoArea) {
        this.nombreNodo = nombreNodo;
        this.label=label;
        this.color= color;
        this.tipoArea=tipoArea;
    }
    private String idAleatoria(){
        SecureRandom random = new SecureRandom();        
        String primeraCadena= new BigInteger(40, random).toString(32);
        String segundaCadena = Integer.toString((int)(Math.random()*9999));
        String terceraCadena= Integer.toString((int)(Math.random()*9999));
        String cuartaCadena= new BigInteger(20, random).toString(32);
        String quintaCadena= new BigInteger(60, random).toString(32);  
        String idNodo= primeraCadena +"-" +segundaCadena+"-"+terceraCadena+"-"+cuartaCadena+"-"+quintaCadena;
        return idNodo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void agregarArista(arista a){
        this.listaAristas.add(a);        
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }    
    
    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo(String nombreNodo) {
        this.nombreNodo = nombreNodo;
    }

    public int getValorEuristica() {
        return valorEuristica;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValorEuristica(int valorEuristica) {
        this.valorEuristica = valorEuristica;
    }

    public List<arista> getListaAristas() {
        return listaAristas;
    }

    public void setListaAristas(List<arista> listaAristas) {
        this.listaAristas = listaAristas;
    }

    public void setCostoAestrella(int costoAestrella) {
        this.costoAestrella = costoAestrella;
    }

    public int getCostoCaminoAestrella() {
        return costoCaminoAestrella;
    }

    public void setCostoCaminoAestrella(int costoCaminoAestrella) {
        this.costoCaminoAestrella = costoCaminoAestrella;
    }

    public int getCostoAestrella() {
        return costoAestrella;
    }

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }
    
    
}
