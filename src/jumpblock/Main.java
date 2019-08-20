/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import org.newdawn.slick.SlickException;

/**
 *
 * @author sironi_federico
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        JumpBlock sm = new JumpBlock("Simulador Fisica");
        VentanaInicio vI = new VentanaInicio("title",sm);
    }
    
}
