import java.sql.SQLOutput;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;


	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
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
			METODO_CLASIFICACION_INSERCION);

		int[] resAscendente = clasif.clasificar(vectorAscendente,
			METODO_CLASIFICACION_INSERCION);

		int[] resDescendente = clasif.clasificar(vectorDescendente,
			METODO_CLASIFICACION_INSERCION);

		for (int i = 0; i < resAleatorio.length; i++) {
			System.out.print(resAleatorio[i] + " ");
		}

		System.out.println();

		for (int i = 0; i < resAscendente.length; i++) {
			System.out.print(resAscendente[i] + " ");
		}

		System.out.println();

		for (int i = 0; i < resDescendente.length; i++) {
			System.out.print(resDescendente[i] + " ");
		}

		System.out.println();

		System.out.println(clasif.estaOrdenado(resAleatorio));
		System.out.println(clasif.estaOrdenado(resAscendente));
		System.out.println(clasif.estaOrdenado(resDescendente));

	}
}
