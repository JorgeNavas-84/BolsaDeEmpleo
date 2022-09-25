/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 19 de septiembre de 2022
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes!= null <br>
 * En el vector de aspirantes no hay dos o más con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    public final static String URL_BASE_DATOS = "jdbc:h2:file:./data/bolsadeempleo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la tabla que contiene todos los aspirantes
     */
    private Dao<Aspirante, Integer> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de empleo a partir de la base de datos.
     */
    public BolsaDeEmpleo() {
        ConnectionSource conexion = null;
        try {
            // Creamos la conexión a la base de datos
            conexion = new JdbcConnectionSource(URL_BASE_DATOS);

            // Y ahora traemos los datos que están en la tabla
            aspirantes = DaoManager.createDao(conexion, Aspirante.class);

            // Creamos la tabla, si no existe
            TableUtils.createTableIfNotExists(conexion, Aspirante.class);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Permite cerrar base de datos
     * @throws Exception cualquier error que pueda ocurrir
     */
    public void cerrarBaseDatos() throws Exception {
        aspirantes.getConnectionSource().close();
    }

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public List<Aspirante> darAspirantes() {
        List<Aspirante> copia = new ArrayList<>();
        try {
            for (Aspirante a : aspirantes.queryForAll()) {
                copia.add(a);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     * @return Se retornó true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(int cedula, String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) throws SQLException {
        Aspirante aspiranteBuscado = buscarAspirante(cedula);
        boolean agregado = false;
        if (aspiranteBuscado == null) {
            Aspirante nuevoAspirante = new Aspirante(cedula, nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.create(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes está ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        // TODO: Realizar el ejercicio correspondiente
        /**
         *
         *         boolean sorted = false;
         *         int temp;
         *         while (!sorted) {
         *             sorted = true;
         *             for (int i = 0; i < darAspirantes().size() - 1; i++) {
         *                 for (int j = 0; j < darAspirantes().size() - 1; j++) {
         *                     Aspirante n = darAspirantes().get(j);
         *                     if (n.getCedula() > ) {
         *                         temp = c[j];
         *                         c[i] = c[i + 1];
         *                         c[i + 1] = temp;
         *                         sorted = false;
         *                     }
         *                 }
         *             }
         *         }
         *     }
         */
    }
    /**
     * Retorna la cantidad de aspirantes que tienen más de la edad
     * que se pasa como parámetro.
     */
    public int contarPorEdad(int edad) {
        // TODO: Realizar el ejercicio correspondiente
        int pedad = edad;
        int contador = 0;
        for (int j = 0; j < darAspirantes().size() ; j++) {
            Aspirante n = darAspirantes().get(j);
            if (n.getEdad() >= pedad){
                contador ++;
            }
        }
        return contador;
    }

    /**
     * Obtiene la cantidad de aspirantes que pertenecen a la profesión
     * que se pasa como parámetro.
     */
    public int contarPorProfesion(String profesion) {
        // TODO: Realizar el ejercicio correspondiente
        int contador = 0;
        for (int j = 0; j < darAspirantes().size(); j++) {
            Aspirante n = darAspirantes().get(j);
            if (n.getProfesion().equals(profesion)){
                contador ++;
            }
        }
        return contador;
    }

    /**
     * Obtiene el promedio de los años de experiencia de todos los aspirantes
     */
    public double promedioAniosExperiencia() {
        // TODO: Realizar el ejercicio correspondiente
        double contador = 0;
        double cuantos = 0;
        for (int j = 0; j < darAspirantes().size(); j++) {
            Aspirante n = darAspirantes().get(j);
            contador = contador + n.getAniosExperiencia();
            cuantos ++;
        }
        return contador/cuantos;
    }


    /**
     * Busca un Aspirante según su cédula y retorna el aspirante con esa cédula
     * o null si no lo encontró.
     */
    public Aspirante buscarAspirante(int cedula) throws SQLException {
        Aspirante aspirante = null;

        // TODO: Búsqueda de aspirantes por cédula ****
        for(Aspirante a : aspirantes.queryForAll()){
            if (a.getCedula()==cedula){
                aspirante = a;
            }
        }
        return aspirante;
    }

    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     */
    public Aspirante buscarAspiranteMasJoven() {
        // TODO: Realizar el ejercicio correspondiente
        Aspirante aspirante = null;
        int menor = Integer.MAX_VALUE;
        for (int j = 0; j < darAspirantes().size(); j++) {
            Aspirante n = darAspirantes().get(j);
            if (n.getEdad() < menor) {
                menor = n.getEdad();
                aspirante = n;
            }
        }
        return aspirante;
    }

    /**
     * Retorna la cédula aspirante que tenga la mayor edad en la bolsa.
     */
    public int buscarAspiranteMayorEdad() {
        // TODO: Realizar el ejercicio correspondiente
        int cedula = -1;
        int nunMax = Integer.MIN_VALUE;
        for (int j = 0; j < darAspirantes().size(); j++) {
            Aspirante n = darAspirantes().get(j);
            if (n.getEdad() > nunMax) {
                nunMax = n.getEdad();
                cedula = n.getCedula();
            }
        }
        return cedula;
    }

    /**
     * Busca la cédula del aspirante con más años de experiencia en la bolsa.
     */
    public int buscarAspiranteMayorExperiencia() {
        // TODO: Realizar el ejercicio correspondiente
        int cedula = -1;
        int nunMax = Integer.MIN_VALUE;
        for (int j = 0; j < darAspirantes().size(); j++) {
            Aspirante n = darAspirantes().get(j);
            if (n.getAniosExperiencia() > nunMax) {
                nunMax = n.getAniosExperiencia();
                cedula = n.getCedula();
            }
        }
        return cedula;
    }


    /**
     * Contrata a un aspirante.<br>
     * Es decir, se eliminó el aspirante de la lista de aspirantes.

     * @return Se retornó true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) throws Exception {
        boolean contratado = false;

        // TODO: Realizar el ejercicio correspondiente
        for (Aspirante a : aspirantes ) {
            if(a.getNombre().equals(nombre)){
                aspirantes.delete(a);
                contratado = true;
            }
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos años de experiencia <br>
     * son menores a la cantidad de años especificada <br>
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) throws Exception {
        int eliminados = 0;
        int cont = 0;

        // TODO: Realizar el ejercicio correspondiente
        for (Aspirante a : aspirantes){
            if (a.getAniosExperiencia() < aniosExperiencia){
                aspirantes.delete(a);
                eliminados ++;
            }
        }
        return eliminados;
    }

}