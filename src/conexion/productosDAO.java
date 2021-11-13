package conexion;

//importamos las librerias y clases necesarias
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import programa.ProductosModelo;

//clase con la que haremos todas las operaciones CRUD con la base de datos
public class productosDAO 
{
    //creamos una variable para la conexion
    private Connection con;
    
    //creamos el metodo para hacer create nuevos productos
    public void AgregarProducto(ProductosModelo producto)
    {
        //encerramos todo en un try para manejar los errores
        try{
            //si no hay ninguna conexion entonces generamos una
            if(con == null)
                con = conexion.getConexion();
            
            String sql = "INSERT INTO PRODUCTO(nombre,precio) values(?,?)";
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            int RowsInserted = statement.executeUpdate();
            
            if(RowsInserted > 0)
            {
                JOptionPane.showMessageDialog(null, "Insercion exitosa \nProducto: " + producto.getNombre()+"\nPrecio: " + 
                        producto.getPrecio() + "$","Exito",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("La insercion fue exitosa");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("Hubo un error con la insercion");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    //creamos el metodo para hacer update de la informaion de un respectivo producto
    public void ActualizarProducto(ProductosModelo producto)
    {
        //encerramos todo en un try para manejar los errores
        try{
            //si no hay ninguna conexion entonces generamos una
            if(con == null)
                con = conexion.getConexion();
            
            String sql = "UPDATE producto SET precio = ? WHERE idproducto=?";
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, producto.getPrecio());
            statement.setInt(2, producto.getId());
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0)
            {
                JOptionPane.showMessageDialog(null, "Actualizacion exitosa \nProducto: " + producto.getNombre()+"\nNuevo Precio: " + 
                        producto.getPrecio() + "$","Exito",JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("La modificacion fue exitosa");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("Hubo un error en la modificacion");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    //Creamos el metodo para hacer retrieve de la informacion de un determinado producto
    public ProductosModelo ObtenerProducto(int id)
    {
        /*creamos una instancia de la clase objeto que sera donde almacenaremos la informacion
        del producto que deseemos analizar*/
        ProductosModelo producto = new ProductosModelo();    
        //encerramos todo en un try para manejar los errores
        try
        {
            //si no hay ninguna conexion entonces generamos una
            if(con == null)
                con = conexion.getConexion();
            
            String sql = "SELECT * FROM producto WHERE idproducto = ?";    
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            result.next();
            //agregamos toda la informacion obtenida a la instancia del objeto
            producto = new ProductosModelo(result.getInt(1),result.getString(2),result.getInt(3));          
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        //retornamos dicho producto o instancia
        return producto;
    }
    
    //Creamos el metodo para hacer retrieve de toda la informacion de todos los productos de la basa de datos
    public ArrayList<ProductosModelo> ObtenerProductos()
    {       
        /*creamos un array donde almacenaremos todas las instancias de los objetos que almacenaran toda
        la informacion de todos los productos*/
        ArrayList<ProductosModelo> productos = new ArrayList<ProductosModelo>();
        //encerramos todo en un try para manejar los errores
        try
        {
            //si no hay ninguna conexion entonces generamos una
            if(con == null)
                con = conexion.getConexion();
            
            String sql = "SELECT * FROM producto";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next())
            {
                ProductosModelo producto = new ProductosModelo(result.getInt(1),result.getString(2),result.getInt(3));
                productos.add(producto);
            }  
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        //retnornamos dicho array con toda la informacion de la base de datos
        return productos;
    }
    
    //creamos el metodo para hacer delete de un determinado producto de la base de datos 
    public void EliminarProducto(int id)
    {
        //encerramos todo en un try para manejar los errores
        try{
            //si no hay ninguna conexion entonces generamos una
            if(con == null)
                con = conexion.getConexion();
        
            String sql = "DELETE FROM producto WHERE idproducto=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            
            int RowsDeleted = statement.executeUpdate();
            if(RowsDeleted > 0)
            {
                JOptionPane.showMessageDialog(null, "Producto eliminado","Advertencia",JOptionPane.WARNING_MESSAGE);
                System.out.println("Se elimino el producto");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("No se pudo eliminar el producto");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
