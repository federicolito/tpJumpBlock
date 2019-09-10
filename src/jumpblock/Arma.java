/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;


import java.applet.AudioClip;
import java.lang.reflect.Array;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author sironi_federico
 */
public class Arma {
    //aqui creo las imagenes de la arma y la bala
    private Image imagenArma;
    private int ancho,alto;
    //cordenadas del arma
    private float x;
    private float y;
    float angulo;
    //mira
    private double objetivoX;
    private double objetivoY;
    //balas
    private Bala[] municion;
    
    
    public Arma(Image imagenArma,Image imagenBala,int cantMunicion){
        this.imagenArma = imagenArma;
        ancho = 70;
        alto = 70;
        municion = new Bala[cantMunicion];
        for(int i = 0;i < cantMunicion;i++){
            municion[i] = new Bala(imagenBala);
        }
    }
    //actualiza el angulo y la x y la y
    public void update(Bloque b,double xPos,double yPos,double i,double e,AudioClip explosion){
        objetivoX = xPos;
        objetivoY = yPos;
        x = b.getRectangle().getX()+b.getRectangle().getWidth()/2;
        y = b.getRectangle().getY()+b.getRectangle().getHeight()/2;
        angulo = (float) Math.toDegrees(Math.atan2(objetivoY  - (b.getRectangle().getY()+(b.getRectangle().getHeight()/2)),objetivoX - (b.getRectangle().getX()+(b.getRectangle().getHeight()/2))));
        imagenArma.setRotation(angulo);
        
    }
   
    //dibuja el arma
    public void dibujarArma(){
        if(objetivoX > x){
            imagenArma.setCenterOfRotation(0, alto/2);
            imagenArma.draw(x, y-(alto/2), ancho, alto);
        }else{
            imagenArma.setCenterOfRotation(0, -alto/2);
            imagenArma.draw(x, y+(alto/2), ancho, -alto);
        }
    }
    public void dibujarBala(Graphics grphcs){
        for(int i = 0;i<municion.length;i++){
            municion[i].draw(grphcs);
        }
    }
    
    public void disparar(float ups){
        for(int i = 0;i<municion.length;i++){
            municion[i].getImagenBala().setRotation(angulo);
            municion[i].getRectangle().setX((float) x);
            municion[i].getRectangle().setY((float) y);
            municion[i].getImagenBala().setCenterOfRotation(0, 0);
            municion[i].setvY((float) (Math.sin(Math.toRadians(angulo))/(ups/1)));
            municion[i].setvX((float) (Math.cos(Math.toRadians(angulo))/(ups/1)));
        }   
    }

    public Bala[] getBala() {
        return municion;
    }

    public void setBala(Bala[] municion) {
        this.municion = municion;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    
    
    
    
    
}
