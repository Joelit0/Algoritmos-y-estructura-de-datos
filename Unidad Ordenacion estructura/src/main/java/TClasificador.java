
public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_HEAPSORT = 4;
	public static final int METODO_CLASIFICACION_QUICKSORT = 5;
	public static final int METODO_CLASIFICACION_SELECCION_DIRECTA = 6;

	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			case METODO_CLASIFICACION_HEAPSORT:
				return ordenarPorHeapSort(datosParaClasificar);
			case METODO_CLASIFICACION_QUICKSORT:
				return ordenarPorQuickSort(datosParaClasificar);
			case METODO_CLASIFICACION_SELECCION_DIRECTA:
				return ordenarPorSeleccionDirecta(datosParaClasificar);
			default:
				System.err.println("Este codigo no deberia haberse ejecutado");
				break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		if (primero < ultimo){
			int r = primero;
			while(r <= ultimo / 2){
				if (ultimo == 2*r){ //r tiene un hijo solo
					if (datosParaClasificar[r] < datosParaClasificar[r*2]){
						intercambiar(datosParaClasificar, r, 2 * r);
						r = 2 * r;
					} else {
						r = ultimo;
					}
				} else { //r tiene 2 hijos
					int posicionIntercambio = 0;
					if (datosParaClasificar[2*r] > datosParaClasificar[2*r + 1]){
						posicionIntercambio = 2 * r;
					} else {
						posicionIntercambio = 2 * r +1;
					}
					if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]){
						intercambiar(datosParaClasificar,r,posicionIntercambio);
						r = posicionIntercambio;
					} else {
						r = ultimo;
					}
				}
			}
		}
	}

	protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
		quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
		return datosParaClasificar;
	}

	private void quicksort(int[] entrada, int i, int j) {
		int izquierda = i;
		int derecha = j;
		int posicionPivote = encuentraPivote(izquierda, derecha);

		if (posicionPivote >= i){
			int pivote = entrada[posicionPivote];
			while (izquierda <= derecha) {
				while ((entrada[izquierda] < pivote) && (izquierda < j)) {
					izquierda++;
				}

				while ((pivote < entrada[derecha]) && (derecha > i)) {
					derecha--;
				}

				if (izquierda <= derecha) {
					intercambiar(entrada, izquierda, derecha);
					izquierda++;
					derecha--;
				}
			}

			if (i < derecha)
				quicksort(entrada, i, derecha);

			if (izquierda < j)
				quicksort(entrada, izquierda, j);
		}
	}

	public int encuentraPivote(int izquierda,int derecha){
		return (izquierda+derecha) / 2;
	}

	public int[] ordenarPorSeleccionDirecta(int[] datosParaClasificar) {
		for (int i = 0;  i < datosParaClasificar.length; i++) {
			int indiceDelMenor = i;
			int claveMenor = datosParaClasificar[i];

			for (int j = i + 1; j != datosParaClasificar.length; j++) {
				if (datosParaClasificar[j] < claveMenor) {
					indiceDelMenor = j;
					claveMenor = datosParaClasificar[j];
				}
			}

			intercambiar(datosParaClasificar, i, indiceDelMenor);
		}

		return datosParaClasificar;
	}
	protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		for (int i = (datosParaClasificar.length / 2) - 1; i >= 0; i--) {
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}

		for (int i = datosParaClasificar.length - 1; i  >= 0; i--) {
			intercambiar(datosParaClasificar,0,i);
			armaHeap(datosParaClasificar, 0, i-1);
		}
		return datosParaClasificar;
	}

	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j, inc;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

		for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];

			if (inc < (datosParaClasificar.length / 2)) {
				for (int i = inc; i < datosParaClasificar.length; i++) {
					int aux = datosParaClasificar[i];
					j = i - inc;

					while (j > -1 && aux < datosParaClasificar[j]) {
						datosParaClasificar[j + inc] = datosParaClasificar[j];
						j = j - inc;
					}

					datosParaClasificar[j + inc] = aux;
				}
			}
		}

		return datosParaClasificar;
	}

	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int aux = datosParaClasificar[i];
				int j = i - 1;

				while ((j > -1) && (aux < datosParaClasificar[j])) {
					datosParaClasificar[j+1] = datosParaClasificar[j];
					j--;
				}
				datosParaClasificar[j+1] = aux;
			}
			return datosParaClasificar;
		}

		return null;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		int n = datosParaClasificar.length - 1;
		for (int i = 0; i <= n; i++) {
			for (int j = n; j >= (i + 1); j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
				}
			}
		}
		return datosParaClasificar;
	}

	public boolean estaOrdenado(int[] vector) {
		for (int i = 0; i < vector.length - 1; i++) {
			if (vector[i] > vector[i+1]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String args[]) {
		TClasificador clasif = new TClasificador();
		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		int[] vectorAscendente = gdg.generarDatosAscendentes();
		int[] vectorDescendente = gdg.generarDatosDescendentes();

		int[] resAleatorio = clasif.clasificar(vectorAleatorio,
				METODO_CLASIFICACION_QUICKSORT);

		for (int i = 0; i < resAleatorio.length; i++) {
			System.out.print(resAleatorio[i] + " ");
		}

		System.out.println();

		int[] resAscendente = clasif.clasificar(vectorAscendente,
				METODO_CLASIFICACION_QUICKSORT);

		for (int i = 0; i < resAscendente.length; i++) {
			System.out.print(resAscendente[i] + " ");
		}

		System.out.println();

		int[] resDescendente = clasif.clasificar(vectorDescendente,
				METODO_CLASIFICACION_QUICKSORT);

		for (int i = 0; i < resDescendente.length; i++) {
			System.out.print(resDescendente[i] + " ");
		}
	}
}
