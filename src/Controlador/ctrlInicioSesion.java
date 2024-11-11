package Controlador;

import Modelo.Usuarios;
import Vista.InicioSesion;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
