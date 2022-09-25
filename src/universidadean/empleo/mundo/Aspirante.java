/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de septiembre de 2022
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

/**
 * Es la clase que representa a un Aspirante <br>
 */
@DatabaseTable(tableName = "aspirantes")
public class Aspirante {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que la profesión es ADMINISTRADOR
     */
    public static final String ADMINISTRADOR = "Administrador";

    /**
     * Indica que la profesión es INGENIERO INDUSTRIAL
     */
    public static final String INGENIERO_INDUSTRIAL = "Ingeniero Industrial";

    /**
     * Indica que la profesión es contador
     */
    public static final String CONTADOR = "Contador";

    /**
     * Indica que la profesión es economista
     */
    public static final String ECONOMISTA = "Economista";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La cédula del aspirante y la llave primaria de la tabla
     */
    @DatabaseField(id = true)
    private int cedula;

    /**
     * El nombre del aspirante
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * La profesión del aspirante
     */
    @DatabaseField
    private String profesion;

    /**
     * Los años de experiencia del aspirante
     */
    @DatabaseField
    private int aniosExperiencia;

    /**
     * La edad del aspirante
     */
    @DatabaseField
    private int edad;

    /**
     * El teléfono del aspirante
     */
    @DatabaseField
    private String telefono;

    /**
     * La ruta de la imagen del aspirante
     */
    @DatabaseField
    private String imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo aspirante con los parámetros por defecto
     */
    public Aspirante() {

    }

    /**
     * Construye un nuevo aspirante con los parámetros indicados
     */
    public Aspirante(int cedulaA, String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        cedula = cedulaA;
        nombre = nombreA;
        profesion = profesionA;
        aniosExperiencia = aniosExperienciaA;
        edad = edadA;
        telefono = telefonoA;
        imagen = imagenA;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la cedula del aspirante
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * Modifica la cédula del aspirante
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * Retorna el nombre del aspirante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del aspirante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la profesion del aspirante
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * Modifica la profesion del aspirante
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    /**
     * Obtiene los años de experiencia del aspirante
     */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    /**
     * Cambia los años de experiencia del aspirante
     */
    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    /**
     * Obtiene la edad del aspirante
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Cambia la edad del aspirante
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el teléfono del aspirante
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Cambia el teléfono del aspirante
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la ruta a la imagen del empleado
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Cambia la ruta al imagen del empleado
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Retorna una cadena con el nombre del aspirante, su profesión y los años de experiencia
     */
    public String toString() {
        return String.format("%d) %s - %s", cedula, nombre, profesion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aspirante aspirante = (Aspirante) o;
        return cedula == aspirante.cedula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}