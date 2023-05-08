
import java.util.LinkedList;

/*
 * NO LICENCE 
 * Author: Ing. Nicolás Navarro Gutérrez
 */

/**
 *
 * @author nnavarro
 */
public interface ITGrafoRedDatos
{

    /**
     * 
     * @return 
     */
    public LinkedList<LinkedList<TVertice>> componentesConexos();
    
    /**
     *
     * @param origen
     * @return 
     */
    public LinkedList<String> saltosDesde(Comparable origen);
}
