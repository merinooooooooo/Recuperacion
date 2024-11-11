package Controlador;

import Modelo.Usuarios;
import Vista.InicioSesion;
import Vista.Login;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlLogin implements MouseListener{
    
    Usuarios modelo;
    Login vista;

    
    public ctrlLogin(Usuarios Modelo,Login Vista){
        this.modelo = Modelo;
        this.vista = Vista;
        
        vista.btnGuardar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnGuardar) {

            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            modelo.setCorreoElectronico(vista.txtCorrreoElectronico.getText());
            System.out.println("este es eñ correo que intento guardar " + vista.txtCorrreoElectronico.getText());
            modelo.setContraseña(modelo.convertirSHA256(vista.txtContraseña.getText()));
            int edad = Integer.parseInt(vista.txtEdad.getText());
            modelo.setEdad(edad);
            
            modelo.Guardar();
            
            JOptionPane.showMessageDialog(vista, "Usuario guardado");
        }
        
        if (e.getSource() == vista.btnGuardar) {
            
            InicioSesion.initFrmInicioSesion();
            vista.dispose();
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    
}
