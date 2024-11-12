package Controlador;

import Modelo.Usuarios;
import Vista.Crud;
import Vista.InicioSesion;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlInicioSesion implements MouseListener{
    
    Usuarios Modelo;
    InicioSesion Vista;

    public ctrlInicioSesion(Usuarios Modelo, InicioSesion Vista ){
        this.Modelo = Modelo;
        this.Vista = Vista;
        
        Vista.btnIniciar.addMouseListener(this);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == Vista.btnIniciar) {
            
             Boolean hayErrores = false;
        
         if (Vista.txtCorreoInicio.getText().contains("@") || Vista.txtCorreoInicio.getText().contains(".com")) {
                Modelo.setCorreoElectronico(Vista.txtCorreoInicio.getText());
            } else {
                JOptionPane.showMessageDialog(Vista, "Ingrese un correo valido");
                hayErrores = true;

            }
         if(Vista.txtContraseñaInicio.getText().length() < 6){
                JOptionPane.showMessageDialog(Vista, "La contraseña debe tener mas de 6 caracteres");
                   hayErrores = true;
            }else{
            
                Modelo.setContraseña(Modelo.convertirSHA256(Vista.txtContraseñaInicio.getText()));
            }
            
           if(hayErrores){
             JOptionPane.showMessageDialog(Vista, "completa el formulario de manera correcta");
            }else{
                   JOptionPane.showMessageDialog(Vista, "Usuario guardado");
                  Modelo.Guardar();
                  Crud.initCrud();
                  Vista.dispose();
            }
            
            
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
