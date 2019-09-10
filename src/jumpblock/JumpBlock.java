/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import java.applet.AudioClip;
import obtenerRecursos.Audio;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author federico
 */
public class JumpBlock extends BasicGame{    
    private AppGameContainer contenedor;
    private Input entrada;
    //sonidos
    private Audio audio = new Audio();
    private AudioClip rebote_1 = audio.getAudio("/recursos/bouncing.wav");
    private AudioClip pelotaPiso = audio.getAudio("/recursos/personajeGolpePiso.wav");
    private AudioClip disparo = audio.getAudio("/recursos/disparo.wav");
    private AudioClip explosion = audio.getAudio("/recursos/choqueBala.wav");
    //actualizaciones por segundo
    private float ups = 500f;
    //gravedad
    private float gravity = 9.81f;
    //bloques
    public Bloque[] bloque = new Bloque[7];
    private float[] vX = new float[bloque.length];
    private float[] vY = new float[bloque.length];
    private Bloque personaje;
    private float vPersonaje;
    private float vYPersonaje;
    int xPosM ;
    int yPosM ;
    private Image imagenBloque;
    private Image imagenBloque2;
    private Image imagenArma;
    private String nombre;
    
    
    //contructor
    public JumpBlock(String title) throws SlickException {
        super(title);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1000, 700, false);
        contenedor.setShowFPS(true);
        //personaje

        
        
    }
    //metodos heredados de la libreria slick2D
    @Override
    public void init(GameContainer gc) throws SlickException {
        imagenBloque = new Image("recursos/cubo.png");
        imagenBloque2 = new Image("recursos/cubo2.png");
        personaje = new Bloque(new Rectangle(300,400,60,60),60,0.5f,gravity,imagenBloque);
        //bloques enemigos
        for(int i =0 ;i<bloque.length;i++){
            bloque[i] = new Bloque(new Rectangle(100*i,-100*i,(float)Math.random()*40+10,(float)Math.random()*40+10),(float)Math.random()*40+1,0.5f,gravity,imagenBloque2);
        }
    }
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        entrada = contenedor.getInput();
        actualizarMouse();
        updateBloques();
        moverPersonaje();
    }
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        grphcs.setBackground(Color.white);
        drawBloques(grphcs);
        personaje.draw(grphcs);
        grphcs.setColor(org.newdawn.slick.Color.red);
        dibujarMouse(grphcs);
        grphcs.drawString(nombre, personaje.getRectangle().getX(),personaje.getRectangle().getY());
//        drawCord(grphcs);
//        drawCordPersonaje(grphcs);
//        grphcs.drawString("angulo" + arma.angulo, 0, 0);
    }
    //metodos
    //METODOS DE ACTUALIZACIONES
    public void actualizarMouse(){
        xPosM = Mouse.getX();
        yPosM = Mouse.getY();
    }
    public void updateBloques(){
        /*para no cambiar la velocidad por la cantidad de actualizacioes cree un bucle for que se 
        repite hasta alcanzar las UPS y divido la velocidad de cada bloque la divido por las UPS*/
        for(double n = 0;n < ups;n ++){
            //VERIFICO COLISIONES EN EL EJE X DE TODOS LOS BLOQUES
            for (int i = 0;i < bloque.length;i ++){
                for (int q = 0;q < bloque.length;q ++){
                    //PRIMERO VERIFICO COLISIONES ENTRE LOS ENEMIGOS
                    //EL PRIMER "IF" LO UTILIZO PARA QUE UN MISMO BLOQUE NO DETETE COLISIONES CON EL MISMO
                    if (i != q){
                        if(bloque[i].isColiding(bloque[q])){
                            //calculo las velocidades en caso de colision 
                            vX[i] = bloque[i].calcularV(bloque[q],true);
                            vX[q] = bloque[q].calcularV(bloque[i],true); 
                            bloque[i].setvX(vX[i]);
                            bloque[q].setvX(vX[q]);
                            /*EL SIQUIENTE "if" LO UTILIZO PARA QUE EN CASO DE COLISION LOS BLOQUES SE POSICIONEN 
                            JUSTO AL LADO DEL OTRO PARA EVITAR QUE SE TRASPASEN*/
                            if (bloque[i].getRectangle().getX()<bloque[q].getRectangle().getX()){
                                bloque[i].getRectangle().setX(bloque[i].getRectangle().getX()-(bloque[i].getRectangle().getX()+bloque[i].getRectangle().getWidth() - bloque[q].getRectangle().getX()));
                                rebote_1.play();  
                            }else{
                                bloque[i].getRectangle().setX(bloque[i].getRectangle().getX()+(bloque[q].getRectangle().getX()+bloque[q].getRectangle().getWidth()-bloque[i].getRectangle().getX()));
                                rebote_1.play();  
                            }
                        }
                        if (!bloque[i].isHittingFloor((int)contenedor.getHeight(),bloque[q]) & !bloque[i].isHittingFloor((int)contenedor.getHeight(),personaje)){
                            bloque[i].setFalling(true);
                        }else{
                            if ((float) bloque[i].getvY() == 0){
                                bloque[i].setFalling(false);
                            }else{
                                bloque[i].reverseVY(0);
                            }
                        }
                    }
                }
                //colisiones del persnaje contra los bloques
                if(personaje.isColiding(bloque[i])){
                    //calculo las velocidades en caso de colision 
                    vPersonaje = personaje.calcularV(bloque[i],true);
                    vX[i] = bloque[i].calcularV(personaje,true);
                    personaje.setvX(vPersonaje);
                    bloque[i].setvX(vX[i]);
                    /*EL SIQUIENTE "if" LO UTILIZO PARA QUE EN CASO DE COLISION LOS BLOQUES SE POSICIONEN 
                    JUSTO AL LADO DEL OTRO PARA EVITAR QUE SE TRASPASEN*/  
                    if (personaje.getRectangle().getX()<bloque[i].getRectangle().getX()){
                        personaje.getRectangle().setX(personaje.getRectangle().getX()-(personaje.getRectangle().getX()+personaje.getRectangle().getWidth() - bloque[i].getRectangle().getX()));
                        rebote_1.play();  
                    }else{
                        personaje.getRectangle().setX(personaje.getRectangle().getX()+(bloque[i].getRectangle().getX()+bloque[i].getRectangle().getWidth()-personaje.getRectangle().getX()));
                        rebote_1.play();  
                    }
                }
                //lo siguiente sirve para que si un bloque esta tocando el piso sufra friccion
                bloque[i].sufrirFriccion(contenedor.getHeight(),ups,true);
                bloque[i].sufrirFriccion(contenedor.getHeight(),ups,false);
                //esto es para verificar colisiones de la pared
                bloque[i].isHittingWall(contenedor.getWidth(),rebote_1); 
//                if (Math.floor(Math.random()*1000000) == 999){
//                    bloque[i].getArma().disparar(ups);
//                    disparo.play();
//                }
//                if (Math.floor(Math.random()*100000) == 129){
//                    bloque[i].setvX(10/ups);
//                }
//                if (Math.floor(Math.random()*100000) == 367){
//                    bloque[i].setvY(10/ups);
//                }
//                if (personaje.getArma().getBala().isColiding(bloque[i])){
//                    bloque[i].setvY(-1000/ups);
//                    bloque[i].setvX(-personaje.getArma().getBala().getvX());
//                    personaje.getArma().getBala().setvX(0);
//                    personaje.getArma().getBala().setvY(0);
//                }
//                if (bloque[i].getArma().getBala().colide(personaje)){
//                    personaje.setFalling(true);
//                    personaje.setvY(-100/ups);
//                    personaje.setvX(-bloque[i].getArma().getBala().getvX());
//                    bloque[i].getArma().getBala().setvX(0);
//                    bloque[i].getArma().getBala().setvY(0);
//                }
                bloque[i].caer(ups);
                //actualizo la posicion del bloque
                bloque[i].update(personaje.getRectangle().getX(),personaje.getRectangle().getY(),contenedor.getHeight(),contenedor.getWidth(),explosion);
            }
        }
    }
    public void moverPersonaje(){
        /*para no cambiar la velocidad por la cantidad de actualizacioes cree un bucle for que se 
        repite hasta alcanzar las UPS y divido la velocidad de cada bloque la divido por las UPS*/
        for(int n = 1;n < ups;n ++){
            if (entrada.isKeyDown(Input.KEY_RIGHT)){   
                if(personaje.getvX()< 30/ups){
                    personaje.setvX(personaje.getvX()+((personaje.getAceleracionX()/ups)/ups));  
                }
            }else{
                personaje.sufrirFriccion(contenedor.getHeight(),ups,false);
            }
            if (entrada.isKeyDown(Input.KEY_LEFT)){
                if(personaje.getvX() > -30/ups){
                    personaje.setvX(personaje.getvX()-((personaje.getAceleracionX()/ups)/ups)); 
                }
            }else{
                personaje.sufrirFriccion(contenedor.getHeight(),ups,true);
            }
            if (!personaje.isJumping()){
                if (entrada.isKeyPressed(Input.KEY_UP)){
                    personaje.setvYSalto(-30/ups);
                    personaje.update(xPosM,contenedor.getHeight()-yPosM,contenedor.getHeight(),contenedor.getWidth(),explosion);
                    personaje.setFalling(true);
                    personaje.setJumping(true);
                }
            }
            if (entrada.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                personaje.getArma().disparar(ups);
                disparo.play();
            }
            //esto es para verificar colisiones de la pared
            personaje.isHittingWall(contenedor.getWidth(),pelotaPiso);
            
            for(int i = 0;i < bloque.length;i ++){
                if (!personaje.isHittingFloor((int) contenedor.getHeight(),bloque[i]) || personaje.isColiding(bloque[i])){
                    personaje.setFalling(true);
                    
                }else{
                    if ((float)personaje.getvY() <= 0.0){
                        personaje.setFalling(false);
                    }else{
                        personaje.reverseVY(0);
                        personaje.setJumping(false);
                    }
                }
            }
            personaje.caer(ups);
            //actualizo la posicion del bloque
            personaje.update(xPosM,contenedor.getHeight()-yPosM,contenedor.getHeight(),contenedor.getWidth(),explosion);
            
        }
    }
    
    
    //METOSDOS DE DIBUJO DE LOS PERSONAJES
    public void dibujarMouse(Graphics grphcs){
        grphcs.drawLine(personaje.getRectangle().getX(),personaje.getRectangle().getY(),xPosM,contenedor.getHeight()-yPosM);
        grphcs.setLineWidth(6);
        grphcs.drawOval(xPosM-10,contenedor.getHeight()-yPosM-10, 20, 20);
    }
    //dibujo todos los bloques enemigos
    public void drawBloques(Graphics grphcs){
        for (int i = 0;i<bloque.length;i++){
            bloque[i].draw(grphcs);
        }
    }
    //dibujo informacion de las bloques como :cordenandas y velociadad
    public void drawCord(Graphics grphcs){
        for (int i = 0;i<bloque.length;i++){
            grphcs.drawString("x:" + Integer.toString((int)bloque[i].getRectangle().getX()) + "y:" + Integer.toString((int)bloque[i].getRectangle().getY()) + "v:" + Double.toString((int)(Math.sqrt(Math.pow(bloque[i].getvX(),2)+Math.pow(bloque[i].getvY(),2))*ups)),  (int)bloque[i].getRectangle().getX(), (int)bloque[i].getRectangle().getY());
            //dibujo una linea que representa el vector de velocidad del personaje
            grphcs.drawLine((int)bloque[i].getRectangle().getX()+bloque[i].getRectangle().getWidth()/2, (int)bloque[i].getRectangle().getY()+bloque[i].getRectangle().getHeight()/2,/*jjjaj*/(int)bloque[i].getRectangle().getX()+bloque[i].getRectangle().getWidth()/2+(int)(bloque[i].getvX()*ups*10), (int)bloque[i].getRectangle().getY()+bloque[i].getRectangle().getHeight()/2+(int)(bloque[i].getvY()*ups*10));
        }   
    }
    //dibujo informacion de el personaje como :cordenandas y velociadad
    public void drawCordPersonaje(Graphics grphcs){
        grphcs.drawString("x:" + Integer.toString((int)personaje.getRectangle().getX()) + "y:" + Integer.toString((int)personaje.getRectangle().getY()) + "v:" + Double.toString((int)(Math.sqrt(Math.pow(personaje.getvX(),2)+Math.pow(personaje.getvY(),2))*ups)),  (int)personaje.getRectangle().getX(), (int)personaje.getRectangle().getY());
        //dibujo una linea que representa el vector de velocidad del personaje
        grphcs.drawLine((int)personaje.getRectangle().getX()+personaje.getRectangle().getWidth()/2, (int)personaje.getRectangle().getY()+personaje.getRectangle().getHeight()/2,/*jjjaj*/(int)personaje.getRectangle().getX()+personaje.getRectangle().getWidth()/2+(int)(personaje.getvX()*ups*10), (int)personaje.getRectangle().getY()+personaje.getRectangle().getHeight()/2+(int)(personaje.getvY()*ups*10));
    }
    
    public AppGameContainer getContenedor() {
        return contenedor;
    }
    
    public void setContenedor(AppGameContainer contenedor) {
        this.contenedor = contenedor;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
