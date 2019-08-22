/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import java.applet.AudioClip;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;



/**
 *
 * @author federico
 */
public class Bloque {
    private Rectangle rectangle;
    private double m;
    private double vX,vY;
    private double vYSalto;
    private double suma ;
    private double friccion;
    private double fuerza;
    private double coefRozam;
    private double aceleracionX;
    private double gravity;
    private boolean falling = true;
    private boolean jumping = false;

    
    //contructor

    public Bloque( Rectangle rectangle,double m,double coefRozam,double gravity) {
        Bloque b = this;
        this.rectangle = rectangle;
        this.m = m;
        this.vX = 0;
        this.vY = 0;
        this.coefRozam = coefRozam;
        this.gravity = gravity;
        friccion = coefRozam * gravity;
        aceleracionX = friccion;

        
    }
    
    
    //metodos
    public void update(){
        rectangle.setX((float) (rectangle.getX()+ vX)); 
        rectangle.setY((float) (rectangle.getY()+ vY));
    }
    public void caer(double ups){
        if (falling){
            vY += (gravity/(ups*6))/ups;
        }
    }
    //calcula la velocidad final en base a choque elastico
    public double calcularV(Bloque b,boolean queV){
        suma = m + b.m;
        double vf;
        if(queV){
            vf = ((m -  b.m)/ suma) * vX + ((2 * b.m)/ suma) * b.vX;
        }else{
            vf = ((m -  b.m)/ suma) * vY + ((2 * b.m)/ suma) * b.vY;
        }
        return vf;
    }
    public boolean colide(Bloque b){
        return (rectangle.intersects(b.getRectangle()));
    }
    public boolean colideY(Bloque b){
        return !(getRectangle().getY() + getRectangle().getHeight() + 0.5 <= b.getRectangle().getY() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth()) || getRectangle().getY() - 0.5  >= b.getRectangle().getY() +  b.getRectangle().getHeight() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth()));
    }
    //verifica colisiones en la pared
    public void hitWall(int i, AudioClip rebote_1){
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
    public void reverseVX(double restitucion){
        vX = -vX*restitucion;
    }
    //verifica colisiones en el piso
    public boolean hitFloor(double i,Bloque b){
        return (rectangle.getY()+rectangle.getHeight() >= i || colideY(b));
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
    public void reverseVY(double restitucion){
        vY = -vY*restitucion;
    }
    
    
    
    
    
    
    
    //setters and getters
    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public double getvYSalto() {
        return vYSalto;
    }

    public void setvYSalto(double vYSalto) {
        this.vYSalto = vYSalto;
        vY = vYSalto;
    }

    public double getAceleracionX() {
        return aceleracionX;
    }

    public void setAceleracionX(double aceleracionX) {
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
    
    
    
}
