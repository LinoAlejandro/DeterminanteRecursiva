package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import Controllers.Controlador;

public class FramePrincipal extends JFrame{

	int dimension;
	JTextField[][] matriz;
	Controlador controlador;
	
	public FramePrincipal(){
		super("calculadora de determinantes");
		controlador = new Controlador();
		this.setLayout(new BorderLayout());
		this.setBounds(20,20,350,350);
		iniciarComponentes();
		this.setVisible(true);
	}
	
	public void iniciarComponentes(){
		
		JPanel panelMatriz = new JPanel();
		panelMatriz.setLayout(new GridLayout(3,3));
		matriz = new JTextField[3][3];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = new JTextField();
				panelMatriz.add(matriz[i][j]);
			}
		}
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Configurar");
		JMenuItem menuItem = new JMenuItem("Cambiar preferencias...");
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] modelo = {"2","3","4","5","6"};  
				SpinnerListModel sModel = new SpinnerListModel(modelo);
				JSpinner spinner = new JSpinner(sModel);
				String dimensionString = JOptionPane.showInputDialog(null, "Dimension de la matriz");
				if(Pattern.matches("[0-9]", dimensionString)){
					dimension = Integer.parseInt(dimensionString);
					if(dimension < 7 && dimension > 1){
						matriz = new JTextField[dimension][dimension];
						panelMatriz.removeAll();
						panelMatriz.setLayout(new GridLayout(dimension, dimension));
						for (int i = 0; i < matriz.length; i++) {
							for (int j = 0; j < matriz[0].length; j++) {
								matriz[i][j] = new JTextField();
								panelMatriz.add(matriz[i][j]);
								panelMatriz.revalidate();
								panelMatriz.repaint();
								revalidate();
								repaint();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Su numero sale de los limiter(2-6)");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Numero con formato invalido");
				}
			}
		});
		
		menu.add(menuItem);
		menubar.add(menu);
		
		JPanel panelAceptar = new JPanel();
		panelAceptar.setLayout(new FlowLayout());
		
		JButton enviar = new JButton("Aceptar");
		enviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validarCasillas(matriz)){
					int determinante = controlador.obtenerMatriz(matriz);
					JOptionPane.showMessageDialog(null, determinante);
				} else {
					JOptionPane.showMessageDialog(null, "Rellene todos los campo");
				}
			}

			private boolean validarCasillas(JTextField[][] matriz) {
				for (int i = 0; i < matriz.length; i++) {
					for (int j = 0; j < matriz[0].length; j++) {
						if(matriz[i][j].getText().equals("")){
							return false;
						}
					}
				}
				return true;
			}
		});
		
		panelAceptar.add(enviar);
		
		this.add(panelAceptar, BorderLayout.SOUTH);
		this.setJMenuBar(menubar);
		this.add(panelMatriz, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		FramePrincipal fp = new FramePrincipal();
	}
}
