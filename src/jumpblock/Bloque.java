/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import java.applet.AudioClip;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;



/**
 *
 * @author federico
 */
public class Bloque {
    private Rectangle rectangle;
    private float m;
    private float vX,vY;
    private float vYSalto;
    private float aceleracionX;
    private float gravity;
    private boolean falling = true;
    private boolean jumping = false;
    private Image imagen;
    private Arma arma;

    
    //contructor

    public Bloque(Rectangle rectangle,float m,float aceleracionX,float gravity,Image imagen){
        this.rectangle = rectangle;
        this.m = m;
        this.vX = 0;
        this.vY = 0;
        this.aceleracionX = aceleracionX;
        this.gravity = gravity;
        this.imagen = imagen;
    }
    
    
    //metodos
    public void update(double xPos,double yPos,double i,double e,AudioClip explosion){
        rectangle.setX((float) (rectangle.getX()+ vX)); 
        rectangle.setY((float) (rectangle.getY()+ vY));
    }
    public void caer(double ups){
        if (falling){
            vY += (gravity/(ups*6))/ups;
        }
    }
    //calcula la velocidad final en base a choque elastico
    public float calcularV(Bloque b,boolean queV){
        float suma = m + b.m;
        float vf;
        if(queV){
            vf = ((m -  b.m)/ suma) * vX + ((2 * b.m)/ suma) * b.vX;
        }else{
            vf = ((m -  b.m)/ suma) * vY + ((2 * b.m)/ suma) * b.vY;
        }
        return vf;
    }
    public boolean isColiding(Bloque b){
        return (rectangle.intersects(b.getRectangle()));
    }
    public boolean isColidingY(Bloque b){
        return !(getRectangle().getY() + getRectangle().getHeight() + 0.5 <= b.getRectangle().getY() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth()) || getRectangle().getY() - 0.5  >= b.getRectangle().getY() +  b.getRectangle().getHeight() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth()));
    }
    //verifica colisiones en la pared
    public void isHittingWall(int i, AudioClip rebote_1){
        if(rectangle.getX() <= 0 || rectangle.getX() + rectangle.getWidth() >= i){
            reverseVX(1);
            if (getRectangle().getX()<0){
                rectangle.setX(0);
                rebote_1.play();  
            }else{
                rectangle.setX(i-rectangle.getWidth());
                rebote_1.play();  
            }
        }
    }
    //caltcula el rebote segun la restitucion, osea la energia que es absorvida por la pared
    public void reverseVX(float restitucion){
        vX = -vX*restitucion;
    }
    //verifica colisiones en el piso
    public boolean isHittingFloor(double i,Bloque b){
        return (rectangle.getY()+rectangle.getHeight() >= i|| isColidingY(b));
    }
    public void sufrirFriccion(int i, double ups,boolean derizq){
        if (rectangle.getY()+rectangle.getHeight() >= i){
            if(derizq){
                if(vX < (double)0.0){
                    vX += (aceleracionX/ups)/ups;  
                    
                }
            }else{
                if(vX > (double)0.0){
                    vX -= (aceleracionX/ups)/ups; 
                    
                }
            }
        }
    }
    //caltcula el rebote segun la restitucion, osea la energia que es absorvida por el piso
    public void reverseVY(float restitucion){
        vY = -vY*restitucion;
    }
    public void draw(Graphics grphcs){
        imagen.draw(rectangle.getX(),rectangle.getY(), rectangle.getWidth(),rectangle.getHeight());
    }
    //setters and getters
    public float getvX() {
        return vX;
    }

    public void setvX(float vX) {
        this.vX = vX;
    }

    public float getvY() {
        return vY;
    }

    public void setvY(float vY) {
        this.vY = vY;
    }

    public float getvYSalto() {
        return vYSalto;
    }

    public void setvYSalto(float vYSalto) {
        this.vYSalto = vYSalto;
        vY = vYSalto;
    }

    public float getAceleracionX() {
        return aceleracionX;
    }

    public void setAceleracionX(float aceleracionX) {
        this.aceleracionX = aceleracionX;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }
    
    
    
    
}
