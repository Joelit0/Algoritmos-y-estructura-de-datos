public class Main {
  public static void main(String[] args) {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();

    System.out.println(arbol.insertar("RECTORÍA", ""));

    System.out.println(arbol.insertar("VICERRECTORÍA DEL MEDIO UNIVERSITARIO", "RECTORÍA"));
    System.out.println(arbol.insertar("VICERRECTORÍA ACADÉMICA", "RECTORÍA"));
    System.out.println(arbol.insertar("VICERRECTORÍA ADMINISTRATIVA", "RECTORÍA"));

    System.out.println(arbol.insertar("FACULTAD DE CIENCIAS EMPRESARIALES", "VICERRECTORÍA ACADÉMICA"));
    System.out.println(arbol.insertar("FACULTAD DE CIENCIAS HUMANOS", "VICERRECTORÍA ACADÉMICA"));
    System.out.println(arbol.insertar("FACULTAD DE INGENIERÍA Y TECNOLOGÍAS", "VICERRECTORÍA ACADÉMICA"));
    System.out.println(arbol.insertar("FACULTAD DE PSICOLOGÍA", "VICERRECTORÍA ACADÉMICA"));

    System.out.println(arbol.insertar("DEPARTAMENTO DE INFORMÁTICA Y CIENCIAS DE LA COMPUTACIÓN", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS"));
    System.out.println(arbol.insertar("DEPARTAMENTO DE INGENIERÍA ELÉCTRICA", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS"));
    System.out.println(arbol.insertar("DEPARTAMENTO DE MATEMÁTICAS", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS"));

    System.out.println(arbol.tamaño());

    System.out.println(arbol.buscar("FACULTAD DE PSICOLOGÍA").getEtiqueta());
    System.out.println(arbol.buscar("DEPARTAMENTO DE MATEMÁTICAS").getEtiqueta());
    System.out.println(arbol.buscar("FACULTAD DE DERECHO"));
    System.out.println(arbol.buscar(""));

    System.out.println(arbol.listarIndentado());
  }
}
