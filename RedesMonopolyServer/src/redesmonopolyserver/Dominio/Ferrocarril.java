/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Dominio;

/**
 *
 * @author kamgm
 */
public class Ferrocarril {
    private String nombre;
    private int unFerrocarril;
    private int dosFerrocarril;
    private int tresFerrrocarril;
    private int cuatroFerrocarril;
    private int hipoteca;

    public Ferrocarril(String nombre, int unFerrocarril, int dosFerrocarril, int tresFerrrocarril, int cuatroFerrocarril, int hipoteca) {
        this.nombre = nombre;
        this.unFerrocarril = unFerrocarril;
        this.dosFerrocarril = dosFerrocarril;
        this.tresFerrrocarril = tresFerrrocarril;
        this.cuatroFerrocarril = cuatroFerrocarril;
        this.hipoteca = hipoteca;
    }
}
