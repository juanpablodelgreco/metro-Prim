package metro;

public class Arista implements Comparable<Arista> {
	private int nodoDesde;
	private int nodoHasta;
	private int costo;

	public Arista(int nodoDesde, int nodoHasta, int costo) {
		this.nodoDesde = nodoDesde;
		this.nodoHasta = nodoHasta;
		this.costo = costo;
	}

	public int getNodoDesde() {
		return nodoDesde;
	}

	public int getNodoHasta() {
		return nodoHasta;
	}

	public int getCosto() {
		return costo;
	}

	@Override
	public int compareTo(Arista a2) {
		return this.costo - a2.costo;
	}
}
