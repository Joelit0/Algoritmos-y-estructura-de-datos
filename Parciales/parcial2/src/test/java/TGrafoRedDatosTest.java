
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TGrafoRedDatosTest
{
    
    TGrafoDirigido gd1;
    
    TGrafoNoDirigido gnd1;
    TGrafoRedDatos gnd10;
    
    @BeforeEach
    public void setUp() throws Exception
    {
        Collection<TVertice> vertices = new LinkedList<TVertice>();
        Collection<TArista> aristas = new LinkedList<TArista>();

        TVertice v1 = new TVertice("Artigas");
        TVertice v2 = new TVertice("Canelones");
        TVertice v3 = new TVertice("Durazno");
        TVertice v4 = new TVertice("Florida");
        TVertice v5 = new TVertice("Montevideo");
        TVertice v6 = new TVertice("PuntaDelEste");
        TVertice v7 = new TVertice("Rocha");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);

        TArista a1 = new TArista("Artigas", "Rocha", 400);
        TArista a2 = new TArista("Canelones", "Artigas", 500);
        TArista a3 = new TArista("Canelones", "Colonia", 200);
        TArista a4 = new TArista("Canelones", "Durazno",170);
        TArista a5 = new TArista("Canelones", "PuntaDelEste", 90);
        TArista a6 = new TArista("Colonia", "Montevideo", 180);
        TArista a7 = new TArista("Florida", "Durazno", 60);
        TArista a8 = new TArista("Montevideo", "Artigas", 700);
        TArista a9 = new TArista("Montevideo", "Canelones", 30);
        TArista a10 = new TArista("Montevideo", "PuntaDelEste", 130);
        TArista a11 = new TArista("PuntaDelEste", "Rocha", 90);
        TArista a12 = new TArista("Rocha", "Montevideo", 270);

        aristas.add(a1);
        aristas.add(a2);
        aristas.add(a3);
        aristas.add(a4);
        aristas.add(a5);
        aristas.add(a6);
        aristas.add(a7);
        aristas.add(a8);
        aristas.add(a9);
        aristas.add(a10);
        aristas.add(a11);
        aristas.add(a12);

        gd1 = new TGrafoDirigido(vertices, aristas);
        gnd1 = new TGrafoNoDirigido(vertices,aristas);
        gnd10 = new TGrafoRedDatos(vertices,aristas);
        
    }

    // RECORDAR HACER TESTs LO MAS COMPLETO POSIBLE :)
    /**
     * Test of componentesConexos method, of class TGrafoRedDatos.
     */
    @Test
    public void testComponentesConexos()
    {
        assertTrue(gnd10.componentesConexos().size()>0);
    }

    /**
     * Test of saltosDesde method, of class TGrafoRedDatos.
     */
    @Test
    public void testSaltosDesde()
    {

    }
    
    //----------------------TESTS GRAFO DIRIGIDO-------------------------//

    //TGrafoDirigido menos costo entre dos vertices
    @Test
    public void testEliminarArista(){
        //Obtener arista Rocha - Montevideo
        TArista arista = new TArista("Rocha", "Montevideo", 270);
        //Eliminar arista
        gd1.eliminarArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen());
        //verificar que la arista no exista en el grafo
        assertTrue(gd1.existeArista(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()));
    };

    //TGrafoDirigido existe arista test
    @Test
    public void testExisteArista(){
        //Obtener arista Rocha - Montevideo
        TArista arista = new TArista("Rocha", "Montevideo", 270);
        //verificar que la arista exista en el grafo
        assertTrue(gd1.existeArista(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()));
    };

    //TGrafoDirigido eliminar vertice test
    @Test
    public void testEliminarVertice(){
        //Obtener vertice Artigas
        TVertice vertice = new TVertice("Artigas");
        //Eliminar vertice
        gd1.eliminarVertice(vertice.getEtiqueta());
        //verificar que el vertice no exista en el grafo
        assertFalse(gd1.existeVertice(vertice.getEtiqueta()));
    };

    //TGrafoDirigido existe vertice test
    @Test
    public void testExisteVertice(){
        //Obtener vertice Artigas
        TVertice vertice = new TVertice("Artigas");
        //verificar que el vertice exista en el grafo
        assertTrue(gd1.existeVertice(vertice.getEtiqueta()));
    };

    //TGrafoDirigido obtener vertices test
    @Test
    public void testBuscarVertice(){
        //Obtener vertice Artigas
        TVertice vertice = new TVertice("Artigas");
        //verificar que el vertice exista en el grafo
        TVertice busqueda = gd1.buscarVertice(vertice.getEtiqueta());
        //Chequear que TVertice busqueda sea igual a vertice y no sea nulo
        assertNotNull(busqueda);
        assertEquals(busqueda.getEtiqueta(), vertice.getEtiqueta());
    }

    //TGrafoDirigido insertar arista test
    @Test
    public void testInsertarNuevaArista(){
        //Obtener arista Rocha - Montevideo
        TArista arista = new TArista("Rocha", "Durazno", 330);
        //verificar que la arista no exista en el grafo
        assertFalse(gd1.existeArista(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()));
        //Insertar arista
        gd1.insertarArista(arista);
        //verificar que la arista exista en el grafo
        assertTrue(gd1.existeArista(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()));
    }

    //TGrafoDirigido insertar vertice test
    @Test
    public void testInsertarVertice(){
        //Creamos nuevo vertice
        TVertice v8 = new TVertice("Minas");
        //verificar que el vertice no exista en el grafo
        assertFalse(gd1.existeVertice(v8.getEtiqueta()));
        //Insertar vertice
        gd1.insertarVertice(v8);
        //verificar que el vertice exista en el grafo
        assertTrue(gd1.existeVertice(v8.getEtiqueta()));
    }

    //TGrafoDirigido bpf test
    @Test
    public void testBPF(){
        //Creamos lista de vertices esperados
        LinkedList<TVertice> verticesEsperados = new LinkedList();
        verticesEsperados.add(new TVertice("Artigas"));
        verticesEsperados.add(new TVertice("Rocha"));
        verticesEsperados.add(new TVertice("Montevideo"));
        verticesEsperados.add(new TVertice("Canelones"));
        verticesEsperados.add(new TVertice("Durazno"));
        verticesEsperados.add(new TVertice("PuntaDelEste"));
        verticesEsperados.add(new TVertice("Florida"));
        //Creamos lista de vertices obtenidos
        Collection<TVertice> verticesObtenidos = gd1.bpf();

        //Verificamos que las listas sean iguales
        LinkedList<TVertice> aux = new LinkedList<>();
        aux.addAll(verticesObtenidos);

        for (int i = 0; i < aux.size(); i ++) {
            assertEquals(aux.get(i).getEtiqueta(), verticesEsperados.get(i).getEtiqueta());
        }
    }

    //TGrafoDirigido centr del grafo test
    @Test
    public void testCentroDelGrafo(){
        //Obtenemos el centro del grafo
        Comparable centro = gd1.centroDelGrafo();
        //Verificamos que el centro sea el esperado
        assertEquals("Artigas", centro);
    }

    //TGrafoDirigido FLOYD test
    @Test
    public void testFloyd(){
        //Obtenemos la matriz de costos del grafo
        Double[][] matrizCostos = gd1.floyd();
        //Imprimimos la matriz de costos
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                System.out.print(matrizCostos[i][j] + " ");
            }
            System.out.println("");
        }
        //Validamos que los resultados sean los esperados
        assertEquals(0.0, matrizCostos[0][0], 0.0);
    }

    //TGrafoDirigido conectado test
    @Test
    public void testConectadoCon(){
        //Obtenemos un vertice
        TVertice v1 = new TVertice("Artigas");
        //Obtenemos otro vertice
        TVertice v2 = new TVertice("Canelones");
        //Verificamos que los vertices esten conectados
        assertTrue(gd1.conectadoCon(v1.getEtiqueta(), v2.getEtiqueta()));
    }

    //TGrafoDirigido excentricidad test
    @Test
    public void testExcentricidad(){
        //Obtenemos un vertice
        TVertice v1 = new TVertice("Artigas");
        //Obtenemos la excentricidad del vertice
        Double excentricidad = gd1.obtenerExcentricidad(v1.getEtiqueta());
        //Verificamos que la excentricidad sea la esperada
        assertFalse(excentricidad.intValue()>0);
    }

    //TGrafoDirigido Warshall test
    @Test
    public void testWarshall(){
        //Obtenemos la matriz de adyacencia del grafo
        boolean[][] matrizAdyacencia = gd1.warshall();
        //Imprimimos la matriz de adyacencia
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println("");
        }
        //Validamos que los resultados sean los esperados
        assertFalse(matrizAdyacencia[0][0]);
    }

    //TGrafoDirigido todos los caminos test
    @Test
    public void testTodosLosCaminos(){
        //Obtenemos un vertice
        TVertice v1 = new TVertice("Canelones");
        //Obtenemos otro vertice
        TVertice v2 = new TVertice("Artigas");
        //Obtenemos todos los caminos entre los vertices
        TCaminos caminos = gd1.todosLosCaminos(v1.getEtiqueta(), v2.getEtiqueta());
        //Imprimimos los caminos
        caminos.imprimirCaminosConsola();
        //Validamos que los resultados sean los esperados
        assertEquals(2, caminos.getCaminos().size());
    }
    
    //TGrafoDirigido tiene ciclo test
    @Test
    public void testTieneCiclo(){
        //Verificamos que el grafo tenga tenga ciclos a partir de un vertice
        assertTrue(gd1.tieneCiclo("Artigas"));
        //Verificamos que el grafo no tenga tenga ciclos a partir de un vertice
        assertFalse(gd1.tieneCiclo("Durazno"));
    }

    //TGrafoDirigido BEA test
    @Test
    public void testBEA(){
        //Creamos lista de vertices esperados
        LinkedList<TVertice> verticesEsperados = new LinkedList();
        verticesEsperados.add(new TVertice("Rocha"));
        verticesEsperados.add(new TVertice("Montevideo"));
        verticesEsperados.add(new TVertice("Canelones"));
        verticesEsperados.add(new TVertice("PuntaDelEste"));
        verticesEsperados.add(new TVertice("Durazno"));

        //Creamos lista de vertices obtenidos
        Collection<TVertice> verticesObtenidos = gd1.bea();

        //Verificamos que las listas sean iguales
        LinkedList<TVertice> aux = new LinkedList<>();
        aux.addAll(verticesObtenidos);

        for (int i = 0; i < aux.size(); i ++) {
            assertEquals(aux.get(i).getEtiqueta(), verticesEsperados.get(i).getEtiqueta());
        }
    }

    //TGrafoDirigido clasificacion Topologica test
    @Test
    public void testClasificacionTopologica(){
        //Creamos lista de vertices esperados
        LinkedList<TVertice> verticesEsperados = new LinkedList();
        verticesEsperados.add(new TVertice("Florida"));
        verticesEsperados.add(new TVertice("Artigas"));
        verticesEsperados.add(new TVertice("Rocha"));
        verticesEsperados.add(new TVertice("Montevideo"));
        verticesEsperados.add(new TVertice("Canelones"));
        verticesEsperados.add(new TVertice("PuntaDelEste"));
        verticesEsperados.add(new TVertice("Durazno"));

        //Creamos lista de vertices obtenidos
        Collection<TVertice> verticesObtenidos = gd1.clasificacionTopologica();

        //Verificamos que las listas sean iguales
        LinkedList<TVertice> aux = new LinkedList<>();
        aux.addAll(verticesObtenidos);

        for (int i = 0; i < aux.size(); i ++) {
            assertEquals(aux.get(i).getEtiqueta(), verticesEsperados.get(i).getEtiqueta());
        }
    }

    //TGrafo dirigido Menos costo test
    @Test
    public void testMenosCosto(){
        //Obtenemos un vertice
        TVertice v1 = new TVertice("Montevideo");
        //Obtenemos otro vertice
        TVertice v2 = new TVertice("Artigas");
        //Obtenemos el camino de costo minimo entre los vertices
        TCamino camino = gd1.menosCosto(v1.getEtiqueta(), v2.getEtiqueta());
        //Imprimimos el camino
        camino.imprimirEtiquetasConsola();
        //Validamos que la cantidad de vertices que se necesita para llegar desde el vertice origen al vertice destino sea la esperada
        assertEquals(3, camino.getOtrosVertices().size()+1);
    }

    //TGrafo dirigido no conectados test
    @Test
    public void testNoConectados(){
        //Devuelve los vertices que no estan conectados con TODOS los nodos
         LinkedList<Comparable> verticesNoConectados = gd1.verticesNoConectadosConElResto();
        TreeSet<Comparable> aux = new TreeSet<>();
        aux.addAll(verticesNoConectados);

         //Imprimimos los vertices no conectados
        for (Comparable vertice : aux) {
            System.out.println(vertice);
        }
        //Validamos que la cantidad de vertices no conectados sea la esperada
        assertEquals(7, aux.size());
    }

    //-------------------------------------------------------------------//
    //----------------------TESTS GRAFO NO DIRIGIDO----------------------//


    //TGrafoNoDirigido insertar arista Test
    @Test
    public void testInsertarArista() {

        //Insertar vertice
        TVertice vertice = new TVertice("Salto");
        gnd1.insertarVertice(vertice);
        //Insertar nueva ariesta en grafo no dirigido
        TArista arista = new TArista("Montevideo", "Salto", 600);
        assertTrue(gnd1.insertarArista(arista));
    }
    
    //TGrafoNoDirigido Kruskal Algorithm Test
    @Test
    public void testKruskal() {
        //Obtener el arbol de expansion minima
        TGrafoNoDirigido arbol = gnd1.Kruskal();
        //Verificar que el arbol de expansion minima tenga la cantidad de vertices correcta
        assertEquals(arbol.getVertices().size(), gnd1.getVertices().size());
        //Verificar que el arbol de expansion minima tenga la cantidad de aristas correcta
        assertEquals(arbol.getLasAristas().size(), gnd1.getVertices().size() - 1);
    }
    
    //TGrafoNoDirigido chequea con bpf si dos vertices estan conectados
    @Test
    public void testConectados() {
        //Obtenemos dos vertices del grafo y verificamos que los vertices esten conectados
        assertTrue(gnd1.conectados(gnd1.buscarVertice("Artigas"), gnd1.buscarVertice("Rocha")));
    }
    
    //TGrafoNoDirigido BEA Test
    @Test
    public void testBea_Comparable() {
        //Hacer BEA desde un vertice del grafo
        TVertice v1 = gnd1.buscarVertice("Artigas");
        Collection<TVertice> recorrido = gnd1.bea(v1.getEtiqueta());
        //Verificar que el recorrido tenga la cantidad de vertices correcta
        assertEquals(recorrido.size(), gnd1.getVertices().size()-1);
    }
    
    //TGrafoNoDirigido Puntos de articulacion Test
    @Test
    public void testPuntosArticulacion() {
        //Obtenemos los puntos de articulacion del grafo
        LinkedList<TVertice> puntosArticulacion = gnd1.puntosArticulacion();
        //Verificar que el grafo tenga puntos de articulacion
        assertTrue(puntosArticulacion.size() > 0);
    }
    
    //TGrafoNoDirigido Puntos de articulacion con parametro Test
    @Test
    public void testPuntosArticulacionParametrizado() {
        //Obtener punto de articulacion del grafo y eliminarlo
        LinkedList<TVertice> puntosArticulacion = gnd1.puntosArticulacion();
        gnd1.eliminarVertice(puntosArticulacion.get(0).getEtiqueta());
        // el grafo se divide en 2 o más componentes conexos
        assertFalse(gnd1.esConexo());
    }
    
    //TGrafoNoDirigido Conexo Test
    @Test
    public void testEsConexo() {
        //Obtenemos un vertice del grafo
        TVertice v1 = gnd1.buscarVertice("Artigas");
        //Eliminamos ese vertice del grafo
        gnd1.eliminarVertice(v1.getEtiqueta());
        //Verificar que el grafo se divide en 2 o mas componenetes conexos
        assertFalse(gnd1.esConexo());
    }
}
