package logica;

import conexion.ClsConexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hetzo
 */
public class ClsProductos {

    private final String SQL_INSERT = "INSERT INTO productos (proDescripcion, proCategoria, proCantidad, proCosto, proPrecio, proEstado) values(?,?,?,?,?,?)";
    private PreparedStatement PS;
    private final ClsConexion CN;

    public ClsProductos() {
        PS = null;
        CN = new ClsConexion();
    }

    /**
     * metodo que guarde
     */
    public int insertDatos(String descripcion, String categoria, int cantidad, float costo, float precio, String estado) {
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setString(1, descripcion);
            PS.setString(2, categoria);
            PS.setInt(3, cantidad);
            PS.setFloat(4, costo);
            PS.setFloat(5, precio);
            PS.setString(6, estado);
            int res = PS.executeUpdate();//este muestra el numero de consultas que se ejecutaron, tambien capturo lo que hizo en una variable
            //esto se hizo para ver si hay algo exitoso mostrarlo en pantalla para estar seguros cuales estan funcionando y cuales no 
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado!: ");

            }
        } catch (SQLException e) {
            System.err.println("error al guardar los datos en la base de datos!: " + e.getMessage());
        } finally {
            PS = null;//el finaly normalmente se utiliza para cerrar conecciones y recursos
            CN.close();
        }
        return 0;
    }
}
