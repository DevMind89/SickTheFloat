import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Juego implements ActionListener {
	
	JFrame jfrFramePrincipal = null;
	JPanel jpnPanelPrincipal = null;
	JPanel jpnPanelAliado = null;
	JPanel jpnPanelEnemigo = null;
	JLabel FlotaAliada = null;
	JLabel FlotaEnemiga = null;
	JMenuBar jmbMenu = null;
	JMenu jmJuego = null;
	JMenuItem jmiNuevaPartida = null;
	JMenuItem jmiSalir = null;
	Botones botonesAliados[][] = null;
	Botones botonesEnemigos[][] = null;
	JLabel imagen = null;
	JLabel BarcosAliadosTocados = null;
	JLabel BarcosEnemigosTocados = null;
	Barcos objBarcosAliados = null;
	Barcos objBarcosEnemigos = null;
	int respuesta;
	int contadorbarcosaliadostocados=0;
	int contadorbarcosenemigostocados=0;
	
	public Juego(){
		iniciar();
		jfrFramePrincipal.setVisible(true);
		jfrFramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		objBarcosAliados = new Barcos();
		objBarcosEnemigos = new Barcos();
		objBarcosEnemigos.VisualizarMatriz();
		objBarcosAliados.VisualizarMatriz();
	}	
	public void iniciar() {
		
		////FORMULARIO		
		jfrFramePrincipal  = new JFrame("Hundir la Flota");
		jfrFramePrincipal.setSize(2560, 1440);
		jfrFramePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		Image icon = new ImageIcon(getClass().getResource("Imagenes/icono.png")).getImage();
		jfrFramePrincipal.setIconImage(icon);
		jfrFramePrincipal.setLocationRelativeTo(null);
		
		////BARRA DE MENU
		jmbMenu = new JMenuBar();
		jfrFramePrincipal.setJMenuBar(jmbMenu);
		
		////BOTON JUEGO
		jmJuego = new JMenu("Juego");
		jmbMenu.add(jmJuego);
		
		////BOTON NUEVA PARTIDA
		jmiNuevaPartida = new JMenuItem("Nueva Partida");
		jmiNuevaPartida.setMnemonic('N');
		jmiNuevaPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		jmiNuevaPartida.addActionListener(this);
		jmJuego.add(jmiNuevaPartida);
		
		////BOTON SALIR
		jmiSalir = new JMenuItem("Salir");
		jmiSalir.setMnemonic('S');
		jmiSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		jmiSalir.addActionListener(this);
		jmJuego.add(jmiSalir);		
		
		////PANEL
		jpnPanelPrincipal = new JPanel();
		jpnPanelPrincipal.setBackground(Color.black);
		jpnPanelPrincipal.setLayout(null);
		jfrFramePrincipal.add(jpnPanelPrincipal);
		
		////ETIQUETA FLOTA ALIADA
		FlotaAliada = new JLabel("FLOTA ALIADA");
		FlotaAliada.setForeground(Color.YELLOW);
		FlotaAliada.setFont(new Font("tahoma", Font.BOLD, 30));
		FlotaAliada.setBounds(230, 20 , 250, 30);
		jpnPanelPrincipal.add(FlotaAliada);
		
		////PANEL ALIADO
		jpnPanelAliado = new JPanel();
		jpnPanelAliado.setBounds(100, 50, 500, 400);
		jpnPanelAliado.setLayout(new GridLayout(10,10,2,2));
		jpnPanelPrincipal.add(jpnPanelAliado);	
		
		////BOTONES ALIADOS
		botonesAliados = new Botones[10][10];
		for(int i=0;i<botonesAliados.length;i++){
			for(int j=0;j<botonesAliados.length;j++){
				botonesAliados[i][j] = new Botones();
				botonesAliados[i][j].addActionListener(this);
				jpnPanelAliado.add(botonesAliados[i][j]);
			}	
		}
		
		////ETIQUETA FLOTA ENEMIGA
		FlotaEnemiga = new JLabel("FLOTA ENEMIGA");
		FlotaEnemiga.setForeground(Color.RED);
		FlotaEnemiga.setFont(new Font("tahoma", Font.BOLD, 30));
		FlotaEnemiga.setBounds(1470, 450 , 400, 30);
		jpnPanelPrincipal.add(FlotaEnemiga);
	
		////PANEL ENEMIGO
		jpnPanelEnemigo = new JPanel();
		jpnPanelEnemigo.setBounds(1340, 480, 500, 400);
		jpnPanelEnemigo.setLayout(new GridLayout(10,10,2,2));
		jpnPanelPrincipal.add(jpnPanelEnemigo);		
		
		////BOTONES ENEMIGOS
		botonesEnemigos = new Botones[10][10];		
		for(int i=0;i<botonesEnemigos.length;i++){
			for(int j=0;j<botonesEnemigos.length;j++){
				botonesEnemigos[i][j] = new Botones("?");
				botonesEnemigos[i][j].addActionListener(this);
				jpnPanelEnemigo.add(botonesEnemigos[i][j]);
				
			}	
		}
		
		////IMAGEN FONDO		
		imagen = new JLabel();
		imagen.setSize(2560, 1440);
		ImageIcon fondo = new ImageIcon(getClass().getResource("Imagenes/fondo.jpg"));
		ImageIcon ImagenEscalada = new ImageIcon(fondo.getImage().getScaledInstance(imagen.getWidth(),
												imagen.getHeight(),Image.SCALE_DEFAULT));
		imagen.setIcon(ImagenEscalada);
		jpnPanelPrincipal.add(imagen);
		
		////BARCOS ALIADOS TOCADOS
		BarcosAliadosTocados = new JLabel("BARCOS ALIADOS TOCADOS: "+contadorbarcosaliadostocados+"/20");
		BarcosAliadosTocados.setForeground(Color.YELLOW);
		BarcosAliadosTocados.setFont(new Font("tahoma", Font.BOLD, 20));
		BarcosAliadosTocados.setBounds(170, 450, 400, 40);
		imagen.add(BarcosAliadosTocados);
		
		////BARCOS ENEMIGOS TOCADOS
		BarcosEnemigosTocados = new JLabel("BARCOS ENEMIGOS TOCADOS: "+contadorbarcosenemigostocados+"/20");
		BarcosEnemigosTocados.setForeground(Color.RED);
		BarcosEnemigosTocados.setFont(new Font("tahoma", Font.BOLD, 20));
		BarcosEnemigosTocados.setBounds(1420, 880 , 400, 30);
		imagen.add(BarcosEnemigosTocados);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		////BOTON NUEVA PARTIDA
		if(e.getSource()==jmiNuevaPartida){
			NuevaPartida();
		}
		////BOTON SALIR
		if (e.getSource()==jmiSalir){
			Salir();
		}
			
		////ATAQUE A FLOTA ENEMIGA
		for (int i=0; i<10;i++){
			for(int j=0;j<10;j++){
				if (e.getSource()==botonesEnemigos[i][j]){
					if(objBarcosEnemigos.matriz[i][j]==1){	
						botonesEnemigos[i][j].setSize(80,40);
						ImageIcon barco = new ImageIcon(getClass().getResource("Imagenes/enemigo.jpg"));
						ImageIcon ImagenAjustada = new ImageIcon(barco.getImage().getScaledInstance(botonesEnemigos[i][j].getWidth(),
								botonesEnemigos[i][j].getHeight(),Image.SCALE_DEFAULT));
						botonesEnemigos[i][j].setIcon(ImagenAjustada);
						botonesEnemigos[i][j].removeActionListener(this);
						contadorbarcosenemigostocados++;
						BarcosEnemigosTocados.setText(String.valueOf("BARCOS ENEMIGOS TOCADOS: "+contadorbarcosenemigostocados+"/20"));
					}
					if(objBarcosEnemigos.matriz[i][j]==0 || objBarcosEnemigos.matriz[i][j]==2){
						AtaqueBaseAliada();
						botonesEnemigos[i][j].setIcon(new ImageIcon(getClass().getResource("Imagenes/agua.png")));	
						botonesEnemigos[i][j].removeActionListener(this);						
					}
				}
			}
		}
		////COMPROBAR GANADOR
		if(contadorbarcosaliadostocados==20){
			JOptionPane.showMessageDialog(null, "La flota enemiga a ganado", "Has Perdido", JOptionPane.PLAIN_MESSAGE);
		}
		if(contadorbarcosenemigostocados==20){
			JOptionPane.showMessageDialog(null, "Tu flota ha destruido al enemigo", "Has Ganado", JOptionPane.PLAIN_MESSAGE);
		}
	}	
		
	////ACCION NUEVA PARTIDA
	public void NuevaPartida(){
		respuesta=JOptionPane.showConfirmDialog(null,"�Quieres empezar nueva partida?","Aviso",JOptionPane.YES_NO_CANCEL_OPTION);		
		if(respuesta==0){
			new Juego();
		}			
	}
	////ACCION SALIR
	public void Salir(){
		respuesta=JOptionPane.showConfirmDialog(null,"�Quieres salir?","Salir",JOptionPane.YES_NO_CANCEL_OPTION);		
		if(respuesta==0)
			System.exit(0);	
	}
	
	////ATAQUE A FLOTA ALIADA
	public void AtaqueBaseAliada(){
		Random posRandom=new Random();
		int i=posRandom.nextInt(10);
		int j=posRandom.nextInt(10);
		if(objBarcosAliados.matriz[i][j]==1){
			AtaqueBaseAliada();
			botonesAliados[i][j].setSize(50,40);
			ImageIcon barco = new ImageIcon(getClass().getResource("Imagenes/aliado.png"));
			ImageIcon ImagenAjustada = new ImageIcon(barco.getImage().getScaledInstance(botonesAliados[i][j].getWidth(),
					botonesAliados[i][j].getHeight(),Image.SCALE_DEFAULT));
			botonesAliados[i][j].setIcon(ImagenAjustada);
			botonesAliados[i][j].removeActionListener(this);
			contadorbarcosaliadostocados++;
			BarcosAliadosTocados.setText(String.valueOf("BARCOS ALIADOS TOCADOS: "+contadorbarcosaliadostocados+"/20"));				
		}
		if(objBarcosAliados.matriz[i][j]==0 || objBarcosAliados.matriz[i][j]==2){
			botonesAliados[i][j].setIcon(new ImageIcon(getClass().getResource("Imagenes/agua.png")));	
			botonesAliados[i][j].removeActionListener(this);	
		}
	}
}	