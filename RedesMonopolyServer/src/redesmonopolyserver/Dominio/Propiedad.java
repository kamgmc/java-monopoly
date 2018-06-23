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
public class Propiedad implements Serializable{
    private String nombre;
    private Color color;
    private int alquiler;
    private int precioCompra;
    private int costoXCasa;
    private int costoXHotel;
    private int hipoteca;
    private int unaCasa;
    private int dosCasas;
    private int tresCasas;
    private int cuatroCasas;
    private int conHotel;
    private int posCasasX;
    private int posCasasY;
    private int posJugadorX;
    private int posJUgadorY;

    public Propiedad(String nombre, Color color, int alquiler, int precioCompra, int costoXCasa, int costoXHotel, int hipoteca, int unaCasa, int dosCasas, int tresCasas, int cuatroCasas, int conHotel, int posCasasX, int posCasasY, int posJugadorX, int posJUgadorY) {
        this.nombre = nombre;
        this.color = color;
        this.alquiler = alquiler;
        this.precioCompra = precioCompra;
        this.costoXCasa = costoXCasa;
        this.costoXHotel = costoXHotel;
        this.hipoteca = hipoteca;
        this.unaCasa = unaCasa;
        this.dosCasas = dosCasas;
        this.tresCasas = tresCasas;
        this.cuatroCasas = cuatroCasas;
        this.conHotel = conHotel;
        this.posCasasX = posCasasX;
        this.posCasasY = posCasasY;
        this.posJugadorX = posJugadorX;
        this.posJUgadorY = posJUgadorY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCostoXCasa() {
        return costoXCasa;
    }

    public void setCostoXCasa(int costoXCasa) {
        this.costoXCasa = costoXCasa;
    }

    public int getCostoXHotel() {
        return costoXHotel;
    }

    public void setCostoXHotel(int costoXHotel) {
        this.costoXHotel = costoXHotel;
    }

    public int getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(int hipoteca) {
        this.hipoteca = hipoteca;
    }

    public int getUnaCasa() {
        return unaCasa;
    }

    public void setUnaCasa(int unaCasa) {
        this.unaCasa = unaCasa;
    }

    public int getDosCasas() {
        return dosCasas;
    }

    public void setDosCasas(int dosCasas) {
        this.dosCasas = dosCasas;
    }

    public int getTresCasas() {
        return tresCasas;
    }

    public void setTresCasas(int tresCasas) {
        this.tresCasas = tresCasas;
    }

    public int getCuatroCasas() {
        return cuatroCasas;
    }

    public void setCuatroCasas(int cuatroCasas) {
        this.cuatroCasas = cuatroCasas;
    }

    public int getConHotel() {
        return conHotel;
    }

    public void setConHotel(int conHotel) {
        this.conHotel = conHotel;
    }

    public int getPosCasasX() {
        return posCasasX;
    }

    public void setPosCasasX(int posCasasX) {
        this.posCasasX = posCasasX;
    }

    public int getPosCasasY() {
        return posCasasY;
    }

    public void setPosCasasY(int posCasasY) {
        this.posCasasY = posCasasY;
    }

    public int getPosJugadorX() {
        return posJugadorX;
    }

    public void setPosJugadorX(int posJugadorX) {
        this.posJugadorX = posJugadorX;
    }

    public int getPosJUgadorY() {
        return posJUgadorY;
    }

    public void setPosJUgadorY(int posJUgadorY) {
        this.posJUgadorY = posJUgadorY;
    }
    
    
    
    public enum Color{MARRON, CELESTE, FUXIA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
}
