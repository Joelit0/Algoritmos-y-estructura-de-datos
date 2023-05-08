package Ejercicio4;

class Alumno {
  private String nombre;

  public Alumno() {
    nombre = "";
  }

  public String getNombreAdmiracion() {
    return nombre.concat("!");
  }
}
