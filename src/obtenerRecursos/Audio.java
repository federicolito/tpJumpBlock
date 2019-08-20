/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerRecursos;

/**
 *
 * @author Henry Moreno / YouTube: El Rincón del programador
 * @see <a href = "http://www.youtube.com/c/ElRinconDelProgramador" /> El rincón del programador / tutoriales de programación </a>
 */
import java.applet.AudioClip;

public class Audio {

    public AudioClip getAudio(String direccion) {
        return java.applet.Applet.newAudioClip(getClass().getResource(direccion));
    }
   
}