package Modelo;

import Modelo.Conexion.ClaseConexion;
import Vista.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Libros {
    
    private String NombreLibro;
    private String Autor;
    private String AñoDePublicacion;
    private String Estado;
    private String ISBM;
    private String GeneroLiterario;
    private int CantidadPaginas;
    private String Editorial;

    public void setNombreLibro(String NombreLibro) {
        this.NombreLibro = NombreLibro;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public void setAñoDePublicacion(String AñoDePublicacion) {
        this.AñoDePublicacion = AñoDePublicacion;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setISBM(String ISBM) {
        this.ISBM = ISBM;
    }

    public void setGeneroLiterario(String GeneroLiterario) {
        this.GeneroLiterario = GeneroLiterario;
    }

    public void setCantidadPaginas(int CantidadPaginas) {
        this.CantidadPaginas = CantidadPaginas;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public String getNombreLibro() {
        return NombreLibro;
    }

    public String getAutor() {
        return Autor;
    }

    public String getAñoDePublicacion() {
        return AñoDePublicacion;
    }

    public String getEstado() {
        return Estado;
    }

    public String getISBM() {
        return ISBM;
    }

    public String getGeneroLiterario() {
        return GeneroLiterario;
    }

    public int getCantidadPaginas() {
        return CantidadPaginas;
    }

    public String getEditorial() {
        return Editorial;
    }
    
      public void Guardar() {
          
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addLibro = conexion.prepareStatement("INSERT INTO Libros(NombreLibro,Autor,AñoDePublicacion,Estado,ISBM,GeneroLiterario,CantidadPaginas,Editorial) VALUES( '?','?','?','?','?','?','?','?')");
            //Establecer valores de la consulta SQL
            addLibro.setString(1, getNombreLibro());
            addLibro.setString(2, getAutor());
            addLibro.setString(3, getAñoDePublicacion());
            addLibro.setString(4, getEstado());
            addLibro.setString(5, getISBM());
            addLibro.setString(6, getGeneroLiterario());
            addLibro.setInt(7, getCantidadPaginas());
            addLibro.setString(8, getEditorial());

            addLibro.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
      
      public void Mostrar(JTable tabla) {
          
        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"NombreLibro", "Autor", "AñoDePublicacion", "Estado","ISBM","GeneroLiterario","CantidadPaginas","Editorial"});
        try {
            
            Statement statement = conexion.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM Libros");
            
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("NombreLibro"),
                    rs.getString("Autor"),
                    rs.getString("AñoDePublicacion"),
                    rs.getString("Estado"),
                    rs.getString("ISBM"),
                    rs.getString("GeneroLiterario"),
                    rs.getInt("CantidadPaginas"),
                    rs.getString("Editorial"), 

                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
      
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from Libros where NombreLibro = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
        public void cargarDatosTabla(Crud vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbLibros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
            if (filaSeleccionada != -1) {
                String NombreLibro = vista.jtbLibros.getValueAt(filaSeleccionada, 0).toString();
                String Autor = vista.jtbLibros.getValueAt(filaSeleccionada, 1).toString();
                String AñoDePublicacion = vista.jtbLibros.getValueAt(filaSeleccionada, 2).toString();
                String Estado = vista.jtbLibros.getValueAt(filaSeleccionada, 3).toString();
                String ISBM = vista.jtbLibros.getValueAt(filaSeleccionada, 4).toString();
                String GeneroLiterario = vista.jtbLibros.getValueAt(filaSeleccionada, 5).toString();
                int CantidadPaginas = (int) vista.jtbLibros.getValueAt(filaSeleccionada, 6);
                String Editorial = vista.jtbLibros.getValueAt(filaSeleccionada, 7).toString();


            // Establece los valores en los campos de texto
                vista.txtNombreLibro.setText(NombreLibro);
                vista.txtAutor.setText(Autor);
                vista.txtAñoDePublicacion.setText(AñoDePublicacion);
                vista.txtEstado.setText(Estado);
                vista.txtISBM.setText(ISBM);
                vista.txtGeneroLiterario.setText(GeneroLiterario);
                vista.txtCantidadPaginas.setText(String.valueOf(CantidadPaginas));
                vista.txtEditorial.setText(Editorial);

        }
    }
    
    
}
