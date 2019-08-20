/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpblock;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.newdawn.slick.SlickException;

/**
 *
 * @author federico
 */
public class VentanaInicio extends JFrame{
    private String title;
    private JTextArea area = new JTextArea();
    private JumpBlock sm;
                
    
    
    public VentanaInicio(String title,JumpBlock sm){
        this.title = title;
        iniciarComponentes();
        this.sm = sm;
    }

    
    
    private void iniciarComponentes(){
        
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {"Username:", username,"Password:", password};
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            
                
            area.setBounds(10, 10, 480, 360);
            area.setEditable(false);
            add(area);


            JTextField field = new JTextField();
            field.setText( "Escriba aqui" );
            field.setBounds(10, 380, 480, 25);
            field.setHorizontalAlignment(SwingConstants. CENTER);
            field.setVisible(true);



            JScrollPane scroll = new JScrollPane(area);
            scroll.setBounds(10, 10, 480, 360);

            JCheckBox checkbox = new JCheckBox("agregar texto al final");
            checkbox.setBounds(10, 425, 250, 25);
            checkbox.setVisible(true);
            add(scroll);
            scroll.setVisible(true);



            JButton botonenviar = new JButton("enviar");
            botonenviar.setSize(5, 5);
            botonenviar.setBounds(400, 425, 80, 40);
            botonenviar.setHorizontalAlignment(SwingConstants. CENTER);
            botonenviar.setVisible(true);
            botonenviar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (checkbox.isSelected()){
                        try {
                            sm.getContenedor().start();
                            sm.init(sm.getContenedor());
                        } catch (SlickException ex) {
                            Logger.getLogger(VentanaInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            
                        

                    }else{
                    }
                }
            });


            JButton botonborrar = new JButton("borrar");
            botonborrar.setBounds(310, 425, 80, 40);
            botonborrar.setHorizontalAlignment(SwingConstants. CENTER);
            botonborrar.setVisible(true);
            botonborrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int optionPane = JOptionPane
                        .showConfirmDialog(null, "Estas Seguró", "alerta", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (optionPane == JOptionPane.YES_OPTION){
                        area.setText("");
                        field.setText("Escriva aqui");
                    }

                }
            });




            add(field);
            add(botonenviar);
            add(botonborrar);
            add(checkbox);
            setSize(500,500);
            setTitle(title);
            setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
            setLayout(null);
            setVisible(true);
            setResizable(false);
        }

    }
    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }
    


    
}
