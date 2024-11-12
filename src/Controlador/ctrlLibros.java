package Controlador;

import Modelo.Libros;
import Vista.Crud;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlLibros implements MouseListener{
    
    private Libros Modelo;
    private Crud Vista;

    public ctrlLibros(Libros modelo, Crud vista) {
        this.Modelo = modelo;
        this.Vista = vista;

        Vista.btnInsertar.addMouseListener(this);
        modelo.Mostrar(vista.jtbLibros);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbLibros.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == Vista.btnInsertar) {
             
             if (Vista.txtNombreLibro.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setNombreLibro(Vista.txtNombreLibro.getText());
             }

             if (Vista.txtAutor.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setAutor(Vista.txtAutor.getText());
             }

             if (Vista.txtAñoDePublicacion.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setAñoDePublicacion(Vista.txtAñoDePublicacion.getText());
             }

             if (Vista.txtEstado.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setEstado(Vista.txtEstado.getText());
             }

             if (Vista.txtISBM.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setISBM(Vista.txtISBM.getText());
             }

             if (Vista.txtGeneroLiterario.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setGeneroLiterario(Vista.txtGeneroLiterario.getText());
             }

             try {
                 int edadNumerica = Integer.parseInt(Vista.txtCantidadPaginas.getText());
                  Modelo.setCantidadPaginas(Integer.parseInt(Vista.txtCantidadPaginas.getText()));

                 if (edadNumerica < 10000 || edadNumerica == 0) {
                     Modelo.setCantidadPaginas(Integer.parseInt(Vista.txtCantidadPaginas.getText()));

                 }
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(Vista, "Ingrese solo numeros");
             }

             if (Vista.txtEditorial.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(Vista, "Llene los campos");
             } else {
                 Modelo.setEditorial(Vista.txtEditorial.getText());
             }
                  
            
            Modelo.Guardar();   
            Modelo.Mostrar(Vista.jtbLibros);
        }
        
        if(e.getSource() == Vista.btnEliminar){
            Modelo.Eliminar(Vista.jtbLibros);
            Modelo.Mostrar(Vista.jtbLibros);
        }
        
        if(e.getSource() == Vista.jtbLibros){
            Modelo.cargarDatosTabla(Vista);
        }
        
        if(e.getSource() == Vista.btnActualizar){
            Modelo.setNombreLibro(Vista.txtNombreLibro.getText());
             Modelo.setAutor(Vista.txtAutor.getText());
             Modelo.setAñoDePublicacion(Vista.txtAñoDePublicacion.getText());
             Modelo.setEstado(Vista.txtEstado.getText());
             Modelo.setISBM(Vista.txtISBM.getText());
             Modelo.setGeneroLiterario(Vista.txtGeneroLiterario.getText());
             Modelo.setCantidadPaginas(Integer.parseInt(Vista.txtCantidadPaginas.getText()));
             Modelo.setEditorial(Vista.txtEditorial.getText());
            
            Modelo.Actualizar(Vista.jtbLibros);
            Modelo.Mostrar(Vista.jtbLibros);
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
