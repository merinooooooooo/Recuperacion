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
            
            Boolean hayErrores = false;

            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            
            if (vista.txtCorrreoElectronico.getText().contains("@") || vista.txtCorrreoElectronico.getText().contains(".com")) {
                modelo.setCorreoElectronico(vista.txtCorrreoElectronico.getText());
            } else {
                JOptionPane.showMessageDialog(vista, "Ingrese un correo valido");
                hayErrores = true;

            }
            if(vista.txtContraseña.getText().length() < 6){
                JOptionPane.showMessageDialog(vista, "La contraseña debe tener mas de 6 caracteres");
                   hayErrores = true;
            }else{
            
                modelo.setContraseña(modelo.convertirSHA256(vista.txtContraseña.getText()));
            }
            
            try {
                int edadNumerica = Integer.parseInt(vista.txtEdad.getText());
                if (edadNumerica > 100 || edadNumerica == 0) {

                    int edad = Integer.parseInt(vista.txtEdad.getText());
                    modelo.setEdad(edad);
                       hayErrores = true;
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad valida");
                }
            } catch (Exception ex) {
                   hayErrores = true;
                JOptionPane.showMessageDialog(vista, "Ingrese solo numeros");
            }
           
     
            
            if(hayErrores){
             JOptionPane.showMessageDialog(vista, "completa el formulario de manera correcta");
            }else{
                   JOptionPane.showMessageDialog(vista, "Usuario guardado");
                  modelo.Guardar();
                  InicioSesion.initFrmInicioSesion();
                 vista.dispose();
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
