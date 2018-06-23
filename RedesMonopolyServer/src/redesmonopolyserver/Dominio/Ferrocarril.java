/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class Ferrocarril implements Serializable{
    private String nombre;
    private int unFerrocarril;
    private int dosFerrocarril;
    private int tresFerrrocarril;
    private int cuatroFerrocarril;
    private int hipoteca;
    private int precio;

    public Ferrocarril(String nombre, int unFerrocarril, int dosFerrocarril, int tresFerrrocarril, int cuatroFerrocarril, int hipoteca) {
        this.nombre = nombre;
        this.unFerrocarril = unFerrocarril;
        this.dosFerrocarril = dosFerrocarril;
        this.tresFerrrocarril = tresFerrrocarril;
        this.cuatroFerrocarril = cuatroFerrocarril;
        this.hipoteca = hipoteca;
        this.precio = 200;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnFerrocarril() {
        return unFerrocarril;
    }

    public void setUnFerrocarril(int unFerrocarril) {
        this.unFerrocarril = unFerrocarril;
    }

    public int getDosFerrocarril() {
        return dosFerrocarril;
    }

    public void setDosFerrocarril(int dosFerrocarril) {
        this.dosFerrocarril = dosFerrocarril;
    }

    public int getTresFerrrocarril() {
        return tresFerrrocarril;
    }

    public void setTresFerrrocarril(int tresFerrrocarril) {
        this.tresFerrrocarril = tresFerrrocarril;
    }

    public int getCuatroFerrocarril() {
        return cuatroFerrocarril;
    }

    public void setCuatroFerrocarril(int cuatroFerrocarril) {
        this.cuatroFerrocarril = cuatroFerrocarril;
    }

    public int getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(int hipoteca) {
        this.hipoteca = hipoteca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
