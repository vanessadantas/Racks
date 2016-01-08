package racks;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MapaSala {
	// 30 colunas
	// 16 linhas

	Rack[][] mapa = new Rack[16][30];

	public static void main(String[] args) {
		MapaSala ms = new MapaSala();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Informe o caminho do arquivo: ");
		try {
			String entrada = br.readLine();
			ms.iniciarMapa();
			ms.imprimirMapa();
			ms.lerArquivo(entrada);
			//String rota = ms.definirRota(entrada);
			//ms.calcularDistanciaTotal(rota);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void lerArquivo(String entrada) {
		try {
			Scanner scanner = new Scanner(new FileReader(entrada));
			while (scanner.hasNext()) {
				String enter = scanner.next();
				System.out.print(enter);
				String rota = definirRota(enter);
				calcularDistanciaTotal(rota);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String definirRota(String entrada) {
		String[] racks = entrada.split("-");
		
		Rack inicio = buscarRack(racks[0]);
		Rack fim = buscarRack(racks[1]);
		
		if (! (inicio.isMesmaColuna(fim) || inicio.isMesmaLinha(fim))) {
			int linha = inicio.coordenadas[0].linha;
			int coluna = fim.coordenadas[0].coluna;
			Rack rack = mapa[linha][coluna];
			String rota = inicio.nome + "-" + rack.nome + "-" + fim.nome;
			return rota;
		}
		return entrada;
	}
	
	private void calcularDistanciaTotal(String rota) {
		String[] racks = rota.split("-");
		int distanciaTotal = 0;
		int distancia = 0;
		for (int i = 0; i < racks.length; i++) {
			if (i + 1 < racks.length) {
				Rack inicio = buscarRack(racks[i]);
				Rack fim = buscarRack(racks[i + 1]);
				
				distancia = calcularDistancia(inicio, fim);
				if (distancia > 0) {
					distanciaTotal +=  distancia + 1;
				} else {
					break;
				}
			}
		}
		System.out.print(" - " + distanciaTotal + " quadros.");
		System.out.println(distanciaTotal * 60 + " centimetros");

	}

	private Rack buscarRack(String nome) {
		for (int x = 1; x < 16; x++) {
			for (int y = 1; y < 30; y++) {
				Rack rack = mapa[x][y];
				if (rack != null && rack.nome.equals(nome)) {
					return rack;
				}
			}
		}
		System.out.println("Rack não encontrado");
		return null;
	}

	private int calcularDistancia(Rack inicio, Rack fim) {
		int distancia = 0;
		
		if (inicio.isMesmaLinha(fim)) {
			int colInicio = inicio.coordenadas[0].coluna;
			int colFim = fim.coordenadas[1].coluna;
			distancia = (colFim - colInicio);
		}
		if (inicio.isMesmaColuna(fim)) {
			int linhaInicio = inicio.coordenadas[0].linha;
			int linhaFim = fim.coordenadas[1].linha;
			distancia = (linhaFim - linhaInicio);
		}
		return Math.abs(distancia);
	}

	private void imprimirMapa() {
		System.out.println(
				"\t[01][02][03][04][05][06][07][08][09][10][11][12][13][14][15][16][17][18][19][20][21][22][23][24][25][26][27][28][29]");
		System.out.println(
				"\t--------------------------------------------------------------------------------------------------------------------");

		for (int x = 1; x < 16; x++) {
			System.out.print(x + "\t");
			for (int y = 1; y < 30; y++) {
				if (mapa[x][y] == null) {
					System.out.print("[  ]");
				} else {
					Rack rack = mapa[x][y];
					System.out.print("[" + rack.nome + "]");
				}
			}
			System.out.println();
		}
	}

	private void iniciarMapa() {
		// linha 3
		criarRack("01", 3, 2, 3);
		criarRack("02", 3, 4, 5);
		criarRack("03", 3, 6, 7);
		criarRack("04", 3, 8, 9);

		criarRack("06", 3, 12, 13);
		criarRack("07", 3, 14, 15);
		criarRack("08", 3, 16, 17);
		criarRack("09", 3, 18, 19);

		criarRack("11", 3, 22, 23);
		criarRack("12", 3, 24, 25);
		criarRack("13", 3, 26, 27);
		criarRack("14", 3, 28, 29);

		// linha 5
		criarRack("15", 5, 2, 3);
		criarRack("16", 5, 4, 5);
		criarRack("17", 5, 6, 7);
		criarRack("18", 5, 8, 9);

		criarRack("20", 5, 12, 13);
		criarRack("21", 5, 14, 15);
		criarRack("22", 5, 16, 17);
		criarRack("23", 5, 18, 19);

		criarRack("25", 5, 22, 23);
		criarRack("26", 5, 24, 25);
		criarRack("27", 5, 26, 27);
		criarRack("28", 5, 28, 29);

		// linha 8
		criarRack("29", 8, 2, 3);
		criarRack("30", 8, 4, 5);
		criarRack("31", 8, 6, 7);
		criarRack("32", 8, 8, 9);

		criarRack("34", 8, 12, 13);
		criarRack("35", 8, 14, 15);
		criarRack("36", 8, 16, 17);
		criarRack("37", 8, 18, 19);

		criarRack("39", 8, 22, 23);
		criarRack("40", 8, 24, 25);
		criarRack("41", 8, 26, 27);
		criarRack("42", 8, 28, 29);

		// linha 10
		criarRack("43", 10, 2, 3);
		criarRack("44", 10, 4, 5);
		criarRack("45", 10, 6, 7);
		criarRack("46", 10, 8, 9);

		criarRack("48", 10, 12, 13);
		criarRack("49", 10, 14, 15);
		criarRack("50", 10, 16, 17);
		criarRack("51", 10, 18, 19);

		criarRack("53", 10, 22, 23);
		criarRack("54", 10, 24, 25);
		criarRack("55", 10, 26, 27);
		criarRack("56", 10, 28, 29);
	}

	private void criarRack(String nome, int linha, int col1, int col2) {
		Coordenada c1 = new Coordenada(linha, col1);
		Coordenada c2 = new Coordenada(linha, col2);
		Coordenada[] coordenadas = { c1, c2 };

		Rack rack = new Rack(nome, coordenadas);
		mapa[linha][col1] = rack;
		mapa[linha][col2] = rack;
	}
}
