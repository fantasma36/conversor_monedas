/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author fatal
 */
public class Moneda {
    private String moneda_nombre;
    private String moneda_codigo;

    public String getMoneda_codigo() {
        return moneda_codigo;
    }

    public void setMoneda_codigo(String moneda_codigo) {
        this.moneda_codigo = moneda_codigo;
    }
    private double moneda_valor;

    public String getMoneda_nombre() {
        return moneda_nombre;
    }

    public void setMoneda_nombre(String moneda_nombre) {
        this.moneda_nombre = moneda_nombre;
    }

    public double getMoneda_valor() {
        return moneda_valor;
    }

    public void setMoneda_valor(double moneda_valor) {
        this.moneda_valor = moneda_valor;
    }
   
    public Moneda(){

    }
    public Moneda(String nombre,String codigo,double valor){
        this.moneda_nombre=nombre;
        this.moneda_codigo=codigo;
        this.moneda_valor=valor;
    }
    @Override
    public String toString(){
        return moneda_nombre;
    }
    
}
