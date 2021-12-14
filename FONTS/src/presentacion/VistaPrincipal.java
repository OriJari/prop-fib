package presentacion;

import  javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;

public class VistaPrincipal {
    //controlador presentacio
    private ControladorPresentacion CP;

    //components gui
    private JFrame fin = new JFrame("Recomendation System");
    private JPanel laminapor = new JPanel();
    private JPanel laminalog = new JPanel();
    private JButton login = new JButton("Log In");
    private JButton singup = new JButton("Sing Up");

    //atributs
    private int panelactual = 0;

    public VistaPrincipal(ControladorPresentacion controladorPresentacion) {
        CP = controladorPresentacion;
    }

    public void hacerVisible() {

        fin.setVisible(true);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        basic_frame();

    }

    private void basic_frame(){
        fin.setTitle("Recomendation System");
        fin.setResizable(false);

        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tampantalla = mipantalla.getScreenSize();
        int h = tampantalla.height;
        int w = tampantalla.width;
        fin.setSize(960,540);
        fin.setLocation(w/4,h/4);
    }

    //private void laminapor(){

    //}




/*

    class Lamina_portada extends JPanel implements ActionListener {



        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font mifont = new Font("Arial Bold", Font.BOLD, 26);
            g2.setFont(mifont);
            Toolkit mipantalla = Toolkit.getDefaultToolkit();
            Dimension tamPantalla = mipantalla.getScreenSize();
            int altura = tamPantalla.height;
            int amplada = tamPantalla.width;
            g2.drawString("RECOMENDATION", amplada - (amplada - 350), altura - (altura - 100));
            button();


        }
/*
        public void button() {
            add(button_login);
            add(button_singup);
            button_login.addActionListener(this);
            button_singup.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
            Object botonPulsado = e.getSource();
            if (botonPulsado == button_login) {

            } else {

            }

        }
    }

    class Lamina_login extends JPanel implements ActionListener {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font mifont = new Font("Arial Bold", Font.BOLD, 26);
            g2.setFont(mifont);
            Toolkit mipantalla = Toolkit.getDefaultToolkit();
            Dimension tamPantalla = mipantalla.getScreenSize();
            int altura = tamPantalla.height;
            int amplada = tamPantalla.width;
            g2.drawString("LOG IN", amplada - (amplada - 350), altura - (altura - 100));
            button();


        }

        public void button() {
            JButton button_login = new JButton("Log In");

            add(button_login);
            button_login.addActionListener((this));

        }

        public void actionPerformed(ActionEvent e) {

        }
    }
*/
}