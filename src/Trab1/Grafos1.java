//Maria Alice Sens Matos

package Trab1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Grafos1 {

	public static void main(String[] args) {
		//int[][] ch = { { 0, 1, 1}, 
					//	{ 1, 0, 1 }, 
					//	{ 1, 1, 0 } };
		int[][] ch = { { 0, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 } };

		System.out.println(tipoDoGrafo(ch));
		System.out.println(verticesDoGrafo(ch));
		System.out.println(arestasDoGrafo(ch));
	}

	static String tipoDoGrafo(int[][] matriz) {
		String sysout = "";
		if (grafoDirigido(matriz)) {
			sysout = "Grafo Dirigido!\n";
		} else {
			sysout = "Grafo não Dirigido!\n";
		}

		if (grafoNulo(matriz)) {
			sysout += "Grafo Nulo\n";
		}
		if (!grafoSimples(matriz)) {
			sysout += "Multigrafo\n";
		}
		if (grafoSimples(matriz)) {
			sysout += "Grafo Simples\n";
		}
		if (grafoCompleto(matriz)) {
			sysout += "Grafo Completo\n";
		}
		if (grafoRegular(matriz)) {
			sysout += "Grafo Regular\n";
		}
		if (grafoBipartido(matriz)) {
			sysout += "Grafo Bipartido\n";
		}
		return sysout + "\n";
	}

	static String arestasDoGrafo(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != matriz[j][i]) {
					return arestasDirigido(matriz);
				}
			}
		}
		return arestasNaoDirigido(matriz);
	}

	static String verticesDoGrafo(int matriz[][]) {
		if (grafoDirigido(matriz)) {
			return verticesDirigido(matriz);
		}
		return verticesNaoDirigido(matriz);
	}

	static boolean grafoNulo(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean grafoDirigido(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != matriz[j][i]) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean grafoSimples(int[][] matriz) {
		int somaDiagonal = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				somaDiagonal += matriz[i][i];
				if (somaDiagonal == 0) {
					return true;
				}
				if (matriz[i][j] > 1) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean grafoRegular(int[][] matrix) {
		List<Integer> g = new ArrayList<>();

		for (int i = 0; i < matrix.length; i++) {
			int qtdGraus = 0;
			for (int j = 0; j < matrix.length; j++) {
				qtdGraus += matrix[i][j];
			}
			g.add(qtdGraus);
		}
		boolean regular = new HashSet<Integer>(g).size() == 1;
		return regular;
	}

	static boolean grafoCompleto(int[][] matriz) {
		if (!grafoSimples(matriz)) {
			return false;
		}
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] == 0 && i != j) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean grafoBipartido(int[][] matriz) {
		ArrayList<Integer> conjunto1 = new ArrayList<>();
		ArrayList<Integer> conjunto2 = new ArrayList<>();
		if (!grafoDirigido(matriz)) {
			for (int i = 0; i < matriz.length; i++) {
				boolean v1 = true;
				boolean v2 = true;

				for (int j = 0; j <= 0; j++) {

					for (int k = 0; k < conjunto1.size(); k++) {
						if ((conjunto1.size() != 0 && matriz[conjunto1.get(k)][i] > 0) || conjunto1.contains(i)) {
							v1 = false;
						}
					}
					if (v1) {
						conjunto1.add(i);
						break;
					}
					for (int k = 0; k < conjunto2.size(); k++) {
						if ((conjunto2.size() != 0 && matriz[conjunto2.get(k)][i] > 0) || conjunto2.contains(i)) {
							v2 = false;
						}
					}
					if (v2) {
						conjunto2.add(i);
						break;
					}
				}
				if (!v1 && !v2) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	static String arestasNaoDirigido(int[][] matriz) {
		int countAresta = 0;
		int result = 0;
		String ret = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				if (matriz[i][j] == matriz[j][i]) {
					if (matriz[i][j] >= 1) {
						countAresta++;
					}
				}
			}
		}
		result += countAresta / 2;
		ret = "\nQuantidade de arestas: " + result + "\n";

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				if (matriz[i][j] != 0) {
					ret += "V" + i + " - V" + j + "\n";
					matriz[j][i] = 0;
				}
			}
		}
		return ret;
	}

	static String arestasDirigido(int[][] matriz) {
		String ret = "";
		int countAresta = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				if (matriz[i][j] != 0) {
					countAresta++;
				}

			}
		}
		ret = "\nQuantidade de arestas: " + countAresta + "\n";

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				if (matriz[i][j] != 0) {
					ret += "V" + i + "V" + j + "\n";
				}
			}
		}
		return ret + "\n";
	}

	static String verticesNaoDirigido(int[][] matriz) {
		String ret = "";
		int count = 0;
		int[] vetor = new int[matriz.length];
		int soma = 0;
		for (int l = 0; l < matriz[0].length; l++) {
			soma = 0;
			for (int c = 0; c < matriz[1].length; c++) {

				soma += matriz[l][c];
			}
			vetor[l] = soma;
			count++;
			ret += "V" + count + ": " + vetor[l] + " grau(s)\n";
		}

		for (int i = 0; i < matriz[0].length; i++) {
			for (int j = i; j < matriz[1].length; j++) {
				if (vetor[i] > vetor[j]) {
					int temp = vetor[i];
					vetor[i] = vetor[j];
					vetor[j] = temp;
				}
			}
		}

		ret += "Sequencia de graus:";
		for (var item : vetor) {
			ret += item + ", ";
		}
		return ret + "\n";
	}

	public static String verticesDirigido(int[][] matriz) {
		String ret = "";
		int countGSaida = 0;
		int countGEntrada = 0;
		int soma = 0;
		int[] vetorSaida = new int[matriz.length];
		int[] vetorEntrada = new int[matriz.length];

		for (int l = 0; l < matriz.length; l++) {
			soma = 0;
			for (int c = 0; c < matriz.length; c++) {
				soma += matriz[l][c];
			}
			vetorSaida[l] = soma;
			countGSaida++;
			ret += "V" + countGSaida + ": " + vetorSaida[l] + " grau(s) de saída\n";
		}

		for (int l = 0; l < matriz.length; l++) {
			soma = 0;
			for (int c = 0; c < matriz.length; c++) {
				soma += matriz[c][l];
			}
			vetorEntrada[l] = soma;
			countGEntrada++;
			ret += "V" + countGEntrada + ": " + vetorEntrada[l] + " grau(s) de entrada\n";
		}

		ret += "Sequencia de graus de saída: ";
		for (int item : vetorSaida) {
			ret += item + ", ";
		}

		ret += "\nSequencia de graus de entrada: ";
		for (int item : vetorEntrada) {
			ret += item + ", ";
		}
		return ret;
	}
}
