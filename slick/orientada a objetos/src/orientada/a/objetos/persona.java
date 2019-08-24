/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientada.a.objetos;

/**
 *
 * @author federico
 */
public class persona{
    
    private String nombre;
    private String apellido;
    private int edad;
    private float altura;
    private int DNI;

    public void setNombre(String nombrepersona){   
        nombre = nombrepersona;
    }
    public String getNombre(){   
        return nombre;
    }
    public void setApellido(String apellidopersona){
         apellido = apellidopersona;
    }
    public String getApellido(){   
        return apellido;
    }
    public void setEdad(int edadpersona){
        edad = edadpersona;
    }
    public int getEdad(){  
        return edad;
    }
    public void setAltura(float alturapersona){
        altura = alturapersona;
    }
    public float getAltura(){   
        return altura;
    }
    public void setDni(int dnipersona){
        DNI = dnipersona;
    }
    public int getDni(){
        return DNI;
    }
       
}
