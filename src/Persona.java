import java.time.LocalDate;

public class Persona {
    int id;
    String nombre;
    String apellido;
    LocalDate fecha;

    public Persona(int id, String nombre, String apellido, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
