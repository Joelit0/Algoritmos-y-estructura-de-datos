
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author ernesto
 */
public class TProyecto extends TGrafoDirigido implements ICompilar {

    public TProyecto(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<Comparable> compilarDependencias(String modulo) {
        LinkedList<Comparable> compilados = new LinkedList();
        Collection<TVertice> vertices = this.getVertices().values();
        
        for (TVertice vertice : vertices){
            desvisitarVertices();
            if (vertice.getEtiqueta().equals(modulo)){
                vertice.compilarModulo(compilados);
            }
        }
        
        return compilados;
    }

   
}
