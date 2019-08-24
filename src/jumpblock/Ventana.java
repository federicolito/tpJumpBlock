package jumpblock;




import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.newdawn.slick.SlickException;


public class Ventana extends JFrame{
    
    
    private JumpBlock sm;

    public Ventana(JumpBlock sm) {
        this.sm = sm;
    }
    
    public void abrir(){
        iniciarventana();
        iniciarelementos();
        setVisible(true);      
    }

    public void iniciarventana(){ 
        this.setTitle("ingresar  texto");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        this.setLayout(null); 
        
        
        
       }
       
    
    public void iniciarelementos(){
        
        JLabel JLabel1 = new JLabel();
        JLabel1.setVisible(true);
        JLabel1.setBounds(10,50,1000,100);
        
        JLabel JLabel2 = new JLabel();
        JLabel2.setVisible(true);
        JLabel2.setBounds(0,0,600,600);
        
        JButton salir = new JButton("salir");
        salir.setBackground(Color.red);
        salir.setVisible(true);
        salir.setHorizontalAlignment(SwingConstants. CENTER);
        salir.setBounds(250, 300, 100, 40);
        
        
        JButton iniciar = new JButton("Jugar"); 
        iniciar.setBackground(Color.green);
        iniciar.setVisible(true);
        iniciar.setHorizontalAlignment(SwingConstants. CENTER);
        iniciar.setBounds(250, 250, 100, 40);
        
        
        JTextField nombre = new JTextField();
        nombre.setSize(5, 5);
        nombre.setVisible(true);  
        nombre.setBounds(150, 200, 300, 50);
     
        ImageIcon icono = new javax.swing.ImageIcon(getClass().getResource("/recursos/titulo.png"));
        Image imagen = icono.getImage(); 
        ImageIcon iconoEscalado = new ImageIcon (imagen.getScaledInstance(580,100,Image.SCALE_SMOOTH));
        JLabel1.setIcon(iconoEscalado);



        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo.jpg"));
        Image image = icon.getImage(); 
        ImageIcon iconoEscalad = new ImageIcon (image.getScaledInstance(600,600,Image.SCALE_SMOOTH));
        JLabel2.setIcon(iconoEscalad);
                
        this.add(salir);        
        this.add(iniciar);
        this.add(nombre);
        this.add(JLabel1);
        this.add(JLabel2);
        
        
        
        
        iniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                try {
                    sm.getContenedor().start();
                    sm.init(sm.getContenedor());
                } catch (SlickException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        
        salir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

              setVisible(false);
              dispose();
                
               

            }
        });
       
    }
}

 
    
    
    
    
    
    
    
    
    
    
    
    
    

