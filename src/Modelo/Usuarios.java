package Modelo;
import Modelo.Conexion.ClaseConexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.sql.*;

public class Usuarios {
  
    private String UUIDUsuario;
    private String Nombre;
    private String Apellido;
    private String CorreoElectronico;
    private String Contraseña;
    private int Edad;

    public String getUUIDUsuario() {
        return UUIDUsuario;
    }
   
    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public int getEdad() {
        return Edad;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            
            PreparedStatement addUsuario = conexion.prepareStatement("Insert into Usuario(UUID,Nombre,Apellido,CorreoEletronico,Contraseña,Edad) values(?,?,?,?,?,?)");
            
            //Establecer valores de la consulta SQL
            addUsuario.setString(1, UUID.randomUUID().toString());
            addUsuario.setString(2, getNombre());
            addUsuario.setString(3, getApellido());
            addUsuario.setString(4, getCorreoElectronico());
            addUsuario.setString(5, getContraseña());
            addUsuario.setInt(6, getEdad());


            
            //Ejecutar la consulta
            addUsuario.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public boolean IniciarSesion() {

        Connection conexion = ClaseConexion.getConexion();
        boolean resultado = false;

        try {

            String sql = "Select * from Usuario WHERE CorreoElectronico = ? AND Contraseña = ? ";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getCorreoElectronico());
            statement.setString(2, getContraseña());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultado = true;

            }

        } catch (SQLException ex) {
            System.err.println("Error en el modelo : Metodo INICIARSESION" + ex);
        }
        
        return false;
    }
    
    public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	}
	catch (NoSuchAlgorithmException e) {
		System.out.println(e.toString());
		return null;
	}
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
 
	for(byte b : hash) {
		sb.append(String.format("%02x", b));
	}
 
	return sb.toString();
}
    
}
