import java.util.LinkedList;

public class MedicionPredecirLinkedList extends Medible{

    private LinkedList<String> linkedList;

    public MedicionPredecirLinkedList(LinkedList linkedList) {
        this.linkedList = linkedList;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
       
        for(int i = 0; i < repeticion; i++){
            predecir("cas");
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.linkedList;
    }
    public LinkedList<String> predecir(String prefijo){
        LinkedList aux = new LinkedList();
        
        for (String palabra : this.linkedList) {
            if(palabra.startsWith(prefijo)){
                aux.add(palabra);
            }
        }
        return aux;
    }
}
