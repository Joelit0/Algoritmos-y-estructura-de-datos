
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TestCases {

    public TestCases() {}
    
    @Test
    public void testSinDependencias() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vertice = new TVertice("1");
        vertices.add(vertice);
        vertice = new TVertice("2");
        vertices.add(vertice);
        
        LinkedList<TArista> aristas = new LinkedList<>();
        TProyecto proy = new TProyecto(vertices, aristas);
        LinkedList<Comparable> lista = proy.compilarDependencias("1");
        
        assertEquals(0, lista.size());
    }
    
    @Test
    public void testDosDependencia() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vertice = new TVertice("1");
        vertices.add(vertice);
        vertice = new TVertice("2");
        vertices.add(vertice);
        vertice = new TVertice("3");
        vertices.add(vertice);
        
        LinkedList<TArista> aristas = new LinkedList<>();
        TArista arista = new TArista("2", "1", 1);
        aristas.add(arista);
        arista = new TArista("1", "2", 1);
        aristas.add(arista);
        
        
        TProyecto proy = new TProyecto(vertices, aristas);
        LinkedList<Comparable> lista = proy.compilarDependencias("2");
        
        assertEquals(2, lista.size());
    }
    
    @Test
    public void testUnaDependencia() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vertice = new TVertice("1");
        vertices.add(vertice);
        vertice = new TVertice("2");
        vertices.add(vertice);
        vertice = new TVertice("3");
        vertices.add(vertice);
        
        LinkedList<TArista> aristas = new LinkedList<>();
        TArista arista = new TArista("1", "2", 1);
        aristas.add(arista);
        
        
        
        TProyecto proy = new TProyecto(vertices, aristas);
        LinkedList<Comparable> lista = proy.compilarDependencias("1");
        for (Comparable str : lista){
            System.out.println(str);
        }
        
        assertEquals(1, lista.size());
    }
    
}
