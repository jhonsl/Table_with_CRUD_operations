package programa;

/*clase en la que haremos un modelo de la tabla de la base de datos para tener
mejor manejo de toda la informacion y aplicar la vista por controlador´*/
public class ProductosModelo 
{
    /*creamos los valores que van a tomar las respectivas columbas de la tabla 
    de la base de datos*/
    private int id = 0;
    private String nombre;
    private double precio;
    
    //sobrecargamos el constructor 
    
    /*creamos un constructor sin parametros para facilitar la operacion retrieve
    de un determinado producto en las operaciones crud*/
    public ProductosModelo()
    {
        this.id = 0;
        this.nombre = null;
        this.precio = 0;
    }
    
    /*creamos un constructor con 2 parametros debido a que la variable id
    es autoincremental en la base de datos, por lo que no deberemos insertarla
    manualmente*/
    public ProductosModelo(String nombre,double precio)
    {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    /*creamos un metodo con 3 parametros para poder insertar toda la 
    respectiva informacion en las instancias de los productos*/
    public ProductosModelo(int id,String nombre,double precio)
    {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    //creamos los respectivos setter y getter

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
