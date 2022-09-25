/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package universidadean.empleo.interfaz;

import universidadean.empleo.mundo.Aspirante;
import universidadean.empleo.mundo.BolsaDeEmpleo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Es la clase principal de la interfaz
 */
public class InterfazBolsaDeEmpleo extends JFrame {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta al archivo de aspirantes
     */
    public static final String ARCHIVO_ASPIRANTES = "./data/aspirantes.dat";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la bolsa de empleo
     */
    private BolsaDeEmpleo bolsa;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestra la lista de aspirantes
     */
    private PanelLista panelLista;

    /**
     * Es el panel donde se muestran los datos de un aspirante
     */
    private PanelInformacion panelInformacion;

    /**
     * Es el panel donde se introducen los datos para agregar un aspirante
     */
    private PanelAgregarAspirante panelAgregar;

    /**
     * Es el panel donde están los botones para los puntos de extensión
     */
    private PanelExtension panelExtension;

    /**
     * Panel con las opciones de búsqueda y ordenamiento
     */
    private PanelBusquedaOrdenamiento panelBusquedaOrdenamiento;

    /**
     * Panel Consultas
     */
    private PanelConsultas panelConsultas;

    /**
     * Panel con una imagen decorativa
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa todos sus componentes.
     * @param archivoAspirantes es el nombre del archivo de propiedades que contiene la información de los aspirantes
     */
    public InterfazBolsaDeEmpleo(String archivoAspirantes) {
        bolsa = new BolsaDeEmpleo();

        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bolsa de Empleo");
        setSize(new Dimension(760, 585));
        setResizable(false);

        panelImagen = new PanelImagen();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 3;
        add(panelImagen, gbc);

        panelLista = new PanelLista(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(panelLista, gbc);

        panelInformacion = new PanelInformacion();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(panelInformacion, gbc);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridBagLayout());
        panelBusquedaOrdenamiento = new PanelBusquedaOrdenamiento(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        panelOpciones.add(panelBusquedaOrdenamiento, gbc);

        panelConsultas = new PanelConsultas(this);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelOpciones.add(panelConsultas, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(panelOpciones, gbc);

        panelAgregar = new PanelAgregarAspirante(this);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelAgregar, gbc);

        panelExtension = new PanelExtension(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(panelExtension, gbc);

        actualizarLista();
        centrar();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xEsquina = (screen.width - getWidth()) / 2;
        int yEsquina = (screen.height - getHeight()) / 2;
        setLocation(xEsquina, yEsquina);
    }

    /**
     * Actualiza la lista de aspirantes
     */
    private void actualizarLista() {
        panelLista.actualizarLista(bolsa.darAspirantes());
    }

    /**
     * Ordena los aspirantes por años de experiencia y actualiza la lista
     */
    public void promedioAniosExperiencia() {
        double promedio = bolsa.promedioAniosExperiencia();
        String mensaje = String.format("El promedio de los años de experiencia es %.2f", promedio);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Ordena los aspirantes por edad y actualiza la lista
     */
    public void contarPorProfesion() {
        String profesiones[] = {"Administrador", "Contador", "Economista", "Ingeniero Industrial"};
        String prof = (String) JOptionPane.showInputDialog(null,
                "Profesion a contar?",
                "Contar profesiones",
                JOptionPane.QUESTION_MESSAGE,
                null,
                profesiones,
                profesiones[0]);
        if (prof != null) {
            int n = bolsa.contarPorProfesion(prof);
            String mensaje = String.format("Hay %d aspirantes de la profesion %s", n, prof);
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    /**
     * Obtiene la cantidad de aspirantes que más de la edad que se pasa como parámetro
     */
    public void contarPorEdad() {
        String edadPedida = JOptionPane.showInputDialog(null,
                "Edad mínima del aspirante",
                "Contar por edad",
                JOptionPane.QUESTION_MESSAGE);
        try {
            int edad = Integer.parseInt(edadPedida);
            int cantidad = bolsa.contarPorEdad(edad);
            String mensaje = String.format("Hay %d aspirantes que tienen más de %d años de edad.", cantidad, edad);
            JOptionPane.showMessageDialog(null, mensaje);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Edad incorrecta!");
        }
    }

    private int posicionAspirante(int cedula) {
        List<Aspirante> aspirantes = bolsa.darAspirantes();
        for (int i = 0; i < aspirantes.size(); ++i) {
            if (aspirantes.get(i).getCedula() == cedula) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca un aspirante usando la cedula y cuando lo encuentra lo selecciona en la lista y muestra sus datos
     */
    public void buscar() throws SQLException {
        String cedulaBuscar = JOptionPane.showInputDialog(this, "Cedula");
        if (cedulaBuscar != null) {
            int cedula = Integer.parseInt(cedulaBuscar);
            Aspirante aspirante = bolsa.buscarAspirante(cedula);

            actualizarLista();
            if (aspirante != null) {
                int posicion = posicionAspirante(cedula);
                panelLista.seleccionar(posicion);
                verDatos(aspirante);
            }
            else {
                JOptionPane.showMessageDialog(this, "No se encontró el aspirante buscado");
            }
        }
    }

    /**
     * Muestra los datos de un Aspirante en el panel correspondiente
     * @param aspiranteP El Aspirante del que se quieren ver los datos - aspiranteP != null
     */
    public void verDatos(Aspirante aspiranteP) {
        panelInformacion.mostrarDatos(aspiranteP);
    }

    /**
     * Agrega un nuevo aspirante
     * @param nombreA El nombre del aspirante - nombreA != null
     * @param profesionA La profesión del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA Años de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA La edad del aspirante - edadA > 0
     * @param telefonoA El teléfono del aspirante - telefonoA > 0
     * @param imagenA La ruta a la imagen del aspirante - imagenA != null
     */
    public void agregarAspirante(int cedulaA, String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) throws SQLException {
        boolean agregado = bolsa.agregarAspirante(cedulaA, nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
        if (agregado) {
            actualizarLista();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el aspirante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca el aspirante más joven de la bolsa de empleos y muestra sus datos en el panel información
     */
    public void buscarMasJoven() {
        Aspirante aspirante = bolsa.buscarAspiranteMasJoven();

        actualizarLista();
        if (aspirante != null) {
            int posicion = posicionAspirante(aspirante.getCedula());
            panelLista.seleccionar(posicion);
            verDatos(aspirante);
        }
        else {
            JOptionPane.showMessageDialog(this, "Aún no hay hojas de vida de aspirantes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca el aspirante con mayor edad de la bolsa de empleos y muestra sus datos en el panel información
     */
    public void buscarMayorEdad() {
        int cedula = bolsa.buscarAspiranteMayorEdad();

        actualizarLista();
        if (cedula != -1) {
            int posicion = posicionAspirante(cedula);
            panelLista.seleccionar(posicion);
            Aspirante a = bolsa.darAspirantes().get(posicion);
            verDatos(a);
        }
        else {
            JOptionPane.showMessageDialog(this, "Aún no hay hojas de vida de aspirantes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca el aspirante con mayor experiencia de la bolsa de empleos y muestra sus datos en el panel información
     */
    public void buscarMayorExperiencia() {
        int cedula = bolsa.buscarAspiranteMayorExperiencia();

        actualizarLista();
        if (cedula != -1) {
            int posicion = posicionAspirante(cedula);
            panelLista.seleccionar(posicion);
            Aspirante a = bolsa.darAspirantes().get(posicion);
            verDatos(a);
        }
        else {
            JOptionPane.showMessageDialog(this, "Aún no hay hojas de vida de aspirantes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Contrata al aspirante del cual se están mostrando los datos. <br>
     * <b>post: </b>El aspirante contratado no esta en la lista de aspirantes.
     */
    public void contratar() {
        String nombre = panelLista.darNombreSeleccionado();
        if (nombre == null || !panelLista.haySeleccionado()) {
            JOptionPane.showMessageDialog(this, "Aún no ha seleccionado un aspirante", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            boolean contratado = false;
            try {
                contratado = bolsa.contratarAspirante(nombre);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (!contratado) {
                JOptionPane.showMessageDialog(this, "No se pudo contratar al aspirante", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                panelInformacion.limpiarDatos();
                actualizarLista();
            }
        }
    }

    /**
     * Elimina los aspirantes la una cantidad de años de experiencia menos<br>
     * a la especificada por el usuario
     */
    public void eliminarPorExperiencia() {
        String anios = JOptionPane.showInputDialog(this, "Indique los años de experiencia");

        if (anios != null && anios.equals("")) {
            JOptionPane.showMessageDialog(this, "Los años de experiencia deben ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (anios != null) {
            try {
                int cantidadAnios = Integer.parseInt(anios);

                if (cantidadAnios < 0) {
                    JOptionPane.showMessageDialog(this, "Los años de experiencia deben ser un valor numérico positivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int eliminados = bolsa.eliminarAspirantesPorExperiencia(cantidadAnios);

                    if (eliminados > 0) {
                        JOptionPane.showMessageDialog(this, "Se eliminaron " + eliminados + " aspirantes", "Aspirantes Eliminados", JOptionPane.INFORMATION_MESSAGE);
                        actualizarLista();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "No se eliminó ningún aspirante", "Aspirantes Eliminados", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Los años de experiencia deben ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Ejecuta el punto de extensión 1
     */
    public void reqFuncOpcion1() {
        JOptionPane.showMessageDialog(this, "Gracias por usar el programa", "Bolsa de empleo", JOptionPane.INFORMATION_MESSAGE);
        try {
            bolsa.cerrarBaseDatos();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Son los parámetros de ejecución de la aplicación. No deben usarse.
     */
    public static void main(String[] args) {
        try {
            InterfazBolsaDeEmpleo interfaz = new InterfazBolsaDeEmpleo(ARCHIVO_ASPIRANTES);
            interfaz.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}