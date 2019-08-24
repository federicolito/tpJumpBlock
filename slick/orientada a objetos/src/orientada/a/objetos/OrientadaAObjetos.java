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
public class OrientadaAObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        persona per1 = new persona();
        per1.setNombre("federico");
        per1.setApellido("sironi");
        per1.setEdad(16);
        per1.setAltura(1.68f);
        per1.setDni(44195261);
        System.out.println("su nombre es: " + per1.getNombre());
        System.out.println("su apelido es: " + per1.getApellido());
        System.out.println("su edad es: " + per1.getEdad());
        System.out.println("su altura es: " + per1.getAltura());
        System.out.println("su dni es: " + per1.getDni());
    }
    
}
