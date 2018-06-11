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
    
    public enum Color{MARRON, CELESTE, FUXIA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
}
