/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

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
    //verifica colisiones en la pared
    public boolean hitWall(int i){
        return (rectangle.getX() <= 0 || rectangle.getX() + rectangle.getWidth() >= i);
    }
    //caltcula el rebote segun la restitucion, osea la energia que es absorvida por la pared
    public void reverseVX(double restitucion){
        vX = -vX*restitucion;
    }
    //verifica colisiones en el piso
    public boolean hitFloor(int i,Bloque b){
        return (rectangle.getY()+rectangle.getHeight() >= i || colide(b));
        
    }
    public boolean hitFloor(int i){
        return (rectangle.getY()+rectangle.getHeight() >= i);
        
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
