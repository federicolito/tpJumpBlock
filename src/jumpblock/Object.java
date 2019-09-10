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
 * @author sironi_federico
 */
public class Object {
    private Rectangle rectangle;
    private Image image;
    private boolean falling;
    private float vX;
    private float vY;
    private float m;
    private float aceleracionX;
    private float gravity;

    public Object(Rectangle rectangle, Image image, float m, float aceleracionX, float gravity) {
        this.rectangle = rectangle;
        this.image = image;
        this.m = m;
        this.aceleracionX = aceleracionX;
        this.gravity = gravity;
    }
    //metodos
    public void update(float ups){
        rectangle.setX(rectangle.getX() + (vX/ups));
        rectangle.setY(rectangle.getY() + (vY/ups));
    }
    public void caer(){
        if (falling){
            vY += gravity;
        }
    }
    //calcula la velocidad final en base a choque elastico
    public float calcularV(Object b,boolean queV){
        float suma = m + b.m;
        double vf;
        if(queV){
            vf = ((m -  b.m)/ suma) * vX + ((2 * b.m)/ suma) * b.vX;
        }else{
            vf = ((m -  b.m)/ suma) * vY + ((2 * b.m)/ suma) * b.vY;
        }
        return (float) vf;
    }
    public void colide(Object b,AudioClip rebote){
        if (rectangle.intersects(b.getRectangle())){
            float vX1= calcularV(b,true);
            float vX2 = b.calcularV(this,true);
            vX = vX1;
            b.vX = vX2;
            rebote.play();
        }
    }
    public void colideY(Object b,AudioClip rebote){
        if (getRectangle().getY() + getRectangle().getHeight() + 0.5 <= b.getRectangle().getY() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth()) || getRectangle().getY() - 0.5  >= b.getRectangle().getY() +  b.getRectangle().getHeight() ^ (getRectangle().getX() + getRectangle().getWidth() < b.getRectangle().getX() || getRectangle().getX() > b.getRectangle().getX() + b.getRectangle().getWidth())){
            if (!falling){
                rebote.play();
            }
            reboteVY(0.707f);
            float vY1= calcularV(b,false);
            float vY2 = b.calcularV(this,false);
            vY = vY1;
            b.vY = vY2;
            falling = false;
        }else{
            falling = true;
        }
    }
    public void hitFloor(float piso,AudioClip rebote){
        if (rectangle.getY() + rectangle.getHeight() >= piso){
            if (!falling){
                rebote.play();
            }
            reboteVY(0.707f);
            falling = false;
        }else{
            falling = true;
        }
    }
    public void hitWall(float pared, AudioClip rebote){
        if(rectangle.getX() <= 0 || rectangle.getX() + rectangle.getWidth() >= pared){
            reboteVX(1);
            if (getRectangle().getX() < 0){
                rectangle.setX(0);
                rebote.play();  
            }else{
                rectangle.setX(pared-rectangle.getWidth());
                rebote.play();  
            }
        }
    }
    public void reboteVX(float restitucion){
        vX = -vX*restitucion;
    }
    public void reboteVY(float restitucion){
        vY = -vY*restitucion;
    }
    public void sufrirFriccion(float piso,boolean derizq){
        if (rectangle.getY() + rectangle.getHeight() >= piso){
            if(derizq){
                if(vX < (double)0.0){
                    vX += aceleracionX;  
                }
            }else{
                if(vX > (double)0.0){
                    vX -= aceleracionX;  
                }
            }
        }
    }
    
    
    //setters and getters
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

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

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getAceleracionX() {
        return aceleracionX;
    }

    public void setAceleracionX(float aceleracionX) {
        this.aceleracionX = aceleracionX;
    }
    
}
