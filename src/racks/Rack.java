package racks;

public class Rack {
	
	String nome;
	Coordenada[] coordenadas = new Coordenada[2];

	public Rack(String nome, Coordenada[] coordenadas) {
		this.nome = nome;
		this.coordenadas = coordenadas;
	}

	public boolean isMesmaLinha(Rack outro) {
		if (this.coordenadas[0].linha == outro.coordenadas[0].linha) {
			return true;
		}
		return false;
	}
	
	public boolean isMesmaColuna(Rack outro) {
		if (this.coordenadas[0].coluna == outro.coordenadas[0].coluna) {
			return true;
		}
		return false;
	}
}
