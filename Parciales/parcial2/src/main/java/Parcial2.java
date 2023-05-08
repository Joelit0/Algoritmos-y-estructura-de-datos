
import java.util.LinkedList;

public class Parcial2
{

    private static final String ARCH_SERVIDORES = "src/main/java/servidores.txt";
    private static final String ARCH_ENLACES = "src/main/java/enlaces.txt";
    private static final String ARCH_SALTOS = "src/main/java/saltos.txt";
    private static final String ARCH_COMPONENTES = "src/main/java/componentes.txt";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // 1 - Cargar el Grafo
        TGrafoRedDatos grafo = UtilGrafos.cargarGrafo(ARCH_SERVIDORES, ARCH_ENLACES, false, TGrafoRedDatos.class);

        // 2 - Obtener los componentes conexos
        System.out.println("COMPONENTES CONEXOS");
        LinkedList<LinkedList<TVertice>> componentesConexos = grafo.componentesConexos();
        
        
        // 2.1 - Emitir archivo de salida componentes.txt
        // COMPLETAR CÓDIGO
        
        String[] lineasImprimir = new String [componentesConexos.size()];
        int counter = 0;
        for (int i = 0; i < componentesConexos.size(); i++){
            String componente = "";
            for(int x = 0; x < componentesConexos.get(i).size(); x++){
                componente += componentesConexos.get(i).get(x).getEtiqueta().toString() + " - ";
            }
            lineasImprimir[counter] = componente;
            counter++;
        }
        ManejadorArchivosGenerico.escribirArchivo(ARCH_COMPONENTES, lineasImprimir);

        // 2.2 - Imprimir por pantalla el resultado
        // COMPLETAR CÓDIGO
        counter = 0;
        for (int i = 0; i < componentesConexos.size(); i++){
            String componente = "";
            
            for(int x = 0; x < componentesConexos.get(i).size(); x++){
                componente += componentesConexos.get(i).get(x).getEtiqueta().toString() + " - ";
            }
            System.out.println("Componente conexo numero: " + (counter+1) + " || Con vertices: " + componente);
            counter++;
        }

        // 3 - Obtener los saltos del servidor CS30
        System.out.println("\nSALTOS");
        LinkedList<String> saltosDesde = grafo.saltosDesde("CS30");

        // 3.1 - Escribir archivo de salida saltos.txt
        // COMPLETAR CÓDIGO
        counter = 0;
        String[] lineasImprimirSaltos = new String [saltosDesde.size()];
        for (int y = 0; y < saltosDesde.size(); y++){
            lineasImprimirSaltos[counter] =  saltosDesde.get(y);
            counter++;
        }
        ManejadorArchivosGenerico.escribirArchivo(ARCH_SALTOS, lineasImprimirSaltos);

        // 3.2 - Imprimir por consola el resultado
        for (int y = 0; y < saltosDesde.size(); y++){
            System.out.println(lineasImprimirSaltos[y]);
        }
    }
}
