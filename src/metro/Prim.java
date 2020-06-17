package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim {
	private int cantNodos;
	private int cantTuneles;
	private int cantPuentes;
	private int aristasEnUso;
	private PriorityQueue<Arista> cola;
	private ArrayList<Arista> arbolAbarcadorMinimo;
	private int costo;
	private ArrayList<Integer> noSolucion;
	private ArrayList<Integer> solucion;
	private String path;

	public Prim(String path) {
		this.path = path;
		cola = new PriorityQueue<Arista>();
		arbolAbarcadorMinimo = new ArrayList<Arista>();
		noSolucion = new ArrayList<Integer>();
		solucion = new ArrayList<Integer>();
		this.aristasEnUso = 0;
		this.costo = 0;
		cargarAristas();
		for (int i = 0; i < cantNodos; i++)
			noSolucion.add(i);
	}

	void algoritmoPrim(int nodoOrigen) {
		Arista arista;
		Integer eliminar;
		PriorityQueue<Arista> aux = new PriorityQueue<Arista>();
		noSolucion.remove(nodoOrigen);
		solucion.add(nodoOrigen);
		while (!cola.isEmpty() && aristasEnUso != cantNodos) {
			arista = cola.poll();
			if (solucion.contains(arista.getNodoDesde()) && noSolucion.contains(arista.getNodoHasta())
					|| solucion.contains(arista.getNodoHasta()) && noSolucion.contains(arista.getNodoDesde())) {
				if (solucion.contains(arista.getNodoDesde()) && noSolucion.contains(arista.getNodoHasta())) {
					eliminar = arista.getNodoHasta();
					noSolucion.remove(eliminar);
					solucion.add(eliminar);
				} else {
					eliminar = arista.getNodoDesde();
					noSolucion.remove(eliminar);
					solucion.add(eliminar);
				}
				arbolAbarcadorMinimo.add(arista);
				this.costo += arista.getCosto();
				this.aristasEnUso++;
				while (!aux.isEmpty())
					cola.offer(aux.poll());

			} else
				aux.offer(arista);
		}
		mostrarArbolCosto();
	}

	private void cargarAristas() {
		try {
			Scanner sc = new Scanner(new File(path + ".in"));
			int i;
			this.cantNodos = sc.nextInt();
			this.cantTuneles = sc.nextInt();
			this.cantPuentes = sc.nextInt();
			for (i = 0; i < cantTuneles; i++)
				cola.offer(new Arista(sc.nextInt()-1, sc.nextInt()-1, 0));
			for (i = 0; i < cantPuentes; i++)
				cola.offer(new Arista(sc.nextInt()-1, sc.nextInt()-1, 1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void mostrarArbolCosto() {
		System.out.println("El costo del arbol abarcador minimo es: " + this.costo);
		for (Arista arista : arbolAbarcadorMinimo) {
			System.out.println((arista.getNodoDesde()+1) + " - " + (arista.getNodoHasta()+1) + " = " + arista.getCosto());
		}
	}
}
