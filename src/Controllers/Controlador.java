package Controllers;

import javax.swing.JTextField;

import Model.MatrizOperaciones;

public class Controlador {
	
	MatrizOperaciones operacionesController;
	
	public Controlador(){
		operacionesController = new MatrizOperaciones();
	}

	public int obtenerMatriz(JTextField[][] matriz) {
		int[][] intMatriz = this.matrizTextToMatrizInt(matriz);
		int determinante = operacionesController.calcularDeterminante(intMatriz);
		return determinante;
	}
	
	public int[][] matrizTextToMatrizInt(JTextField[][] matriz) {
		int[][] intMatriz = new int[matriz.length][matriz.length];
		
		for (int i = 0; i < intMatriz.length; i++) {
			for (int j = 0; j < intMatriz.length; j++) {
				intMatriz[i][j] = Integer.parseInt(matriz[i][j].getText());
			}
		}
		
		return intMatriz;
	}
}
