/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import java.applet.AudioClip;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author sironi_federico
 */
public class Bala {
    private Rectangle rectangle = new Rectangle(0,0,9,9);
    private float vX;
    private float vY;
    private Image imagenBala;
    
    public Bala(Image imagenBala) {
        this.imagenBala = imagenBala;
    }
    
    //metodos
    public void update(){
        rectangle.setX((float) (rectangle.getX()+vX));
        rectangle.setY((float) (rectangle.getY()+vY));
        
        
    }
    public boolean colide(Bloque b){
        return (rectangle.intersects(b.getRectangle()));
    }
    public void hitFloor(double i, AudioClip explosion){
        if  (rectangle.getY()+rectangle.getHeight() >= i){
            if (vY != 0){
                explosion.play();
            }
            vX=0;
            vY=0;
        }
    }
    public void hitWall(double i, AudioClip explosion){
        if(rectangle.getX() <= 0 || rectangle.getX() + rectangle.getWidth() >= i){
            if (vX != 0){
                explosion.play();
            }
            
            vX=0;
            vY=0;
        }
    }
    public void draw(Graphics grphcs){
        imagenBala.draw(rectangle.getX()-( imagenBala.getWidth()/20), rectangle.getY()-(imagenBala.getHeight() /20), imagenBala.getWidth()/10,imagenBala.getHeight()/10 );
        grphcs.drawOval(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),rectangle.getHeight() );
    }
    //getters and setters
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
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

    public Image getImagenBala() {
        return imagenBala;
    }

    public void setImagenBala(Image imagenBala) {
        this.imagenBala = imagenBala;
    }
    
    

}
