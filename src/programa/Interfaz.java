package programa;

//importamos todas las librerias que necesitamos
import conexion.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//extendemos la clase a jframe para tener la interfaz grafica
public class Interfaz extends JFrame
{
//contador para actualizar la tabla y mostrar la informacion
    int contador = 0;
//elementos para la conexion y CRUD
    conexion con = new conexion();
    productosDAO crud = new productosDAO();
    ArrayList<ProductosModelo> listaProductos;
    
//elementos del ambiente grafico
    //titulos de la tabla
    String[] titulos = {"id","nombre","precio"};
    //Scrollpane para la tabla
    JScrollPane jsp;
    //panels
    private final JPanel PanelPrincipal = new JPanel();
    private final JPanel panelAgregar = new JPanel(new GridLayout(2,2));
    private final JPanel panelEliminar = new JPanel(new GridLayout(1,2));
    private final JPanel panelActualizar = new JPanel(new GridLayout(2,2));
    private final JPanel panelTabla = new JPanel(new BorderLayout());
    //tables
    private static JTable tabla;
    private DefaultTableModel modelo;
    //buttons
    private Button agregar;
    private Button eliminar;
    private Button actualizar;
    private Button consultar;
    private Button salir;
    //labels
    private JLabel SeccionAgregar;
    private JLabel SeccionEliminar;
    private JLabel SeccionActualizar;
    private JLabel nombre;
    private JLabel precio;
    private JLabel id;
    private JLabel id2;
    //textfields
    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JTextField campoPrecio2;
    private JTextField campoId;
    private JTextField campoId2;
    
    //Constructor de la clase
    public Interfaz()
    {      
        //definimos las propiedades del jframe
        this.setTitle("Productos en stock");
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //cargamos los elementos
        iniciar_componentes();
        
        //los agregamos al jframe
        this.add(PanelPrincipal, BorderLayout.CENTER);
        this.pack();
    }
    
    //Metodo para agregar todos los componentes a los paneles
    public void iniciar_componentes()
    {
        //definimos el layout del panel principal
        PanelPrincipal.setLayout(new BoxLayout(PanelPrincipal, BoxLayout.Y_AXIS));
        //Cargamos los metodos 
        crear_consultas();
        crear_tabla();
    }
    
    //Creamos la tabla
    public void crear_tabla()
    {
        //creamos el modelo de la tabla
        modelo = new DefaultTableModel();
        //creamos las columnas
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        //llenamos el modelo de la tabla
        llenar_tabla(listaProductos);
        //agregamos los datos a la tabla
        tabla = new JTable(modelo);

        //centramos el texto de la tabla
        DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i = 0; i < tabla.getColumnCount(); i++)
            tabla.getColumnModel().getColumn(i).setCellRenderer(alinear);
        
        //añadimos la tabla al scroll
        jsp = new JScrollPane(tabla);
        
        //lo añadimos al paneltabla
        panelTabla.add(jsp, BorderLayout.CENTER);
        
        //editamos el boton consultar y sus eventos
        consultar = new Button("consultar");
        panelTabla.add(consultar, BorderLayout.AFTER_LINE_ENDS);
        
        //editamos el evento para cuando den click en el boton
        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //si es la primera vez que se le hace click, se muestra la informacion almcenada en la base de datos
                if(contador == 0)
                {
                    listaProductos = crud.ObtenerProductos();
                    llenar_tabla(listaProductos);
                    contador += 1;
                }
                
            }
        };
        //agrregamos el evento al boton
        consultar.addActionListener(oyente);
        
        //editamos el boton salir y sus eventos
        salir = new Button("Salir");
        panelTabla.add(salir, BorderLayout.SOUTH);
        
        ActionListener oyente2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //Cuando le demos click al boton salir del programa
                System.exit(0);
            }
        };        
        //Agregamos el evento al boton
        salir.addActionListener(oyente2);
        
        //agregamos los elementos del panel tabla al panel principal
        PanelPrincipal.add(panelTabla);
    }
    
    //metodo que recibe los elementos en la tabla de la base de datos y llena la tabla de java
    public void llenar_tabla(ArrayList<ProductosModelo> productos)
    {
        /*inicialmente la tabla se cargara vacia
        si la tabla tiene informacion se ejecuta el siguiente codigo*/
        if(productos != null)
        {
            //creamos una matriz con el tamaño de la tabla de la base de datos
            String[][] cuerpo = new String[productos.size()][3];
            //Llenamos la matriz con todos los elementos de la base de datos
            for(int i = 0; i<productos.size(); i++)
            {
                //agregamos cada fila de la base de datos al modelo de la tabla de java
                modelo.addRow(new Object[]{productos.get(i).getId(),productos.get(i).getNombre(),productos.get(i).getPrecio()});
            }
        }
    }
    
    //Creamos el espacio para agregar,eliminar,consultar o actualizar productos
    public void crear_consultas()
    {
    //Editamos la zona para agregar productos        
        SeccionAgregar = new JLabel("Seccion para agregar elementos",SwingConstants.CENTER);
        PanelPrincipal.add(SeccionAgregar);
        
        //editamos los labels y su respectivo textfield
        nombre = new JLabel("Nombre",SwingConstants.CENTER);
        panelAgregar.add(nombre);
        campoNombre = new JTextField();
        panelAgregar.add(campoNombre);
        
        precio = new JLabel("Precio",SwingConstants.CENTER);
        panelAgregar.add(precio);
        campoPrecio = new JTextField();
        panelAgregar.add(campoPrecio);
         
        //Agregamos esta seccion al panel principal
        PanelPrincipal.add(panelAgregar);
        
        //editamos el boton agregar
        agregar = new Button("Agregar");
        //agregamos el boton al panel principal
        PanelPrincipal.add(agregar);
        //editamos el evento para cuando le den click
        ActionListener oyente = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /*creamos un nuevo objeto con los respectivos valores que obtenemos de los campos de texto
                se crea con el modelo hecho de la tabla*/
                ProductosModelo productoNuevo = new ProductosModelo(campoNombre.getText(),Double.parseDouble(campoPrecio.getText()));
                //utilizamos la funcion insert de crud para agregar a la base de datos
                crud.AgregarProducto(productoNuevo);
                
                //consultamos de nuevo los productos para saber la nueva cantidad que hay
                listaProductos = crud.ObtenerProductos();
                //agregamos una nueva fila al modelo de la tabla de java con la informacion nueva
                modelo.addRow(new Object[]{listaProductos.get(listaProductos.size()-1).getId(),listaProductos.get(listaProductos.size()-1).getNombre(),listaProductos.get(listaProductos.size()-1).getPrecio()});
                //actualizamos instantaneamente la informacion de la tabla de java
                modelo.fireTableDataChanged();
            }
        };
        //agregamos el evento al boton
        agregar.addActionListener(oyente);
        
    //Editamos la zona para eliminar un producto    
        SeccionEliminar = new JLabel("Seccion para eliminar elementos",SwingConstants.CENTER);
        PanelPrincipal.add(SeccionEliminar);
    
        //editamos los labels y su respectivo textfield
        id = new JLabel("Id",SwingConstants.CENTER);
        panelEliminar.add(id);
        campoId = new JTextField();
        panelEliminar.add(campoId);
        
        //Agregamos esta seccion al panel principal
        PanelPrincipal.add(panelEliminar);
        
        //editamos el boton eliminar
        eliminar = new Button("eliminar");
        //editamos el evento para cuando le den click
        ActionListener oyente2 = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /*obtenemos el texto del campo, lo convertimos en un integer y con el eliminamos dicho producto que tenga ese id
                con la ayuda de la funcion delete de crud*/
                crud.EliminarProducto(Integer.parseInt(campoId.getText()));
                
                //creamos una variable en 0 para calcular la fila en la que se encontraba dicho producto
                int fila = 0;
                //creamos un ciclo para inspeccionar producto por producto para saber cual fila tenia ese id
                for(int i = 0; i<listaProductos.size(); i++)
                {
                    //cuando encontramos la fila en la que se encontraba hacemos break para terminar el ciclo
                    if(listaProductos.get(i).getId() == Integer.parseInt(campoId.getText()))
                        break;
                    
                    fila += 1;
                }
                //consultamos de nuevo los productos para saber la nueva cantidad que hay
                listaProductos = crud.ObtenerProductos();
                //eliminamos la fila en la que se encontraba ese producto
                modelo.removeRow(fila);
                //actualizamos instantaneamente la informacion de la tabla de java
                modelo.fireTableDataChanged();
            }
        };
        //agregamos el evento al boton
        eliminar.addActionListener(oyente2);
        //agregamos el boton al panel principal
        PanelPrincipal.add(eliminar);
        
    //Editamos la zona para actualizar un producto   
        SeccionActualizar = new JLabel("Seccion para Actualizar elementos",SwingConstants.CENTER);
        PanelPrincipal.add(SeccionActualizar);
    
        //editamos los labels y su respectivo textfield
        id2 = new JLabel("Id",SwingConstants.CENTER);
        panelActualizar.add(id2);
        campoId2 = new JTextField();
        panelActualizar.add(campoId2);
        precio = new JLabel("Nuevo Precio",SwingConstants.CENTER);
        panelActualizar.add(precio);
        campoPrecio2 = new JTextField();
        panelActualizar.add(campoPrecio2);
        
        //Agregamos esta seccion al panel principal
        PanelPrincipal.add(panelActualizar);
        
        //editamos el boton actualizar
        actualizar = new Button("actualizar");
        //editamos el evento para cuando le den click
        ActionListener oyente3 = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /*Obtenemos el texto del campo id y lo convertimos en un integer,
                Con dicho integer obtenemos el producto que tenga ese id con la ayuda de los metodos
                de crud y creamos un nuevo objeto con ese producto*/
                ProductosModelo productoNuevo = crud.ObtenerProducto(Integer.parseInt(campoId2.getText()));
                /*Con ayuda del modelo del producto, le actualizamos su precio con el metodo setPrecio
                y el nuevo precio sera el que obtengamos despues de convertir a integer el texto del campo
                que dice nuevo precio*/
                productoNuevo.setPrecio(Integer.parseInt(campoPrecio2.getText()));
                //Actualizamos el producto en la base de datoz con ayuda de los metodos de crud
                crud.ActualizarProducto(productoNuevo);
                
                //consultamos de nuevo los productos para saber la nueva cantidad que hay
                listaProductos = crud.ObtenerProductos();
                //creamos una variable en 0 para calcular la fila en la que se encontraba dicho producto
                int fila = 0;
                for(int i = 0; i<listaProductos.size(); i++)
                {
                    //cuando encontramos la fila en la que se encontraba hacemos break para terminar el ciclo
                    if(listaProductos.get(i).getId() == Integer.parseInt(campoId2.getText()))
                        break;
                    
                    fila += 1;
                }
                //Teniendo ya la fila donde se encontraba ese producto, actualizamos el valor en la tabla de java
                modelo.setValueAt(productoNuevo.getPrecio(), fila, 2);
                //actualizamos instantaneamente la informacion de la tabla de java
                modelo.fireTableDataChanged();
            }
        };
        //agregamos el evento al boton
        actualizar.addActionListener(oyente3);
        //agregamos el boton al panel principal
        PanelPrincipal.add(actualizar);
    }
}
