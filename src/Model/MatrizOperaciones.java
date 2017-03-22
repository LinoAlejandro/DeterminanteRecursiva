package Model;

public class MatrizOperaciones {
	
	public MatrizOperaciones(){
	}
	
	public int[][] matricita(int[][] original, int y, int x){
		int[][] matriz = new int[original.length-1][original.length-1];
		int contX = 0;
		int contY = 0;
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original.length; j++) {
				if(i != y){
					if(j != x) {
						matriz[contY][contX] = original[i][j];
						contX++;
						if(contX >= matriz.length){
							contX = 0;
							contY++;
						}
					}
				}
			}
		}
		return matriz;
	}
	
	public int calcularDeterminante(int[][] matriz){
		if(matriz.length == 1){
			return matriz[0][0];
		} else {
			int determinante = 0;
			boolean bandera = true;
			for (int i = 0; i < matriz.length; i++) {
				if (bandera) {
					determinante += (matriz[0][i] * calcularDeterminante(matricita(matriz, 0, i)));
					
				} else {
					determinante -= (matriz[0][i] * calcularDeterminante(matricita(matriz, 0, i)));
				}
				bandera = ! bandera;
			}
			return determinante;
		}
	}
}
