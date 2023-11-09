import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

class Botones extends JButton implements Interfaz {

	private static final long serialVersionUID = 1L;

	public Botones(){
		setFont(LETRA_BOTONES);
		setForeground(Color.black);
		setBackground(BOTONES_BLANCOS);
		setPreferredSize(new Dimension(50, 100));		
	}
	
	public Botones (String contenido){
		this();
		setText(contenido);
	}
	
	public Botones (String contenido,int ancho,int alto){
		this();
		setText(contenido);
		setPreferredSize(new Dimension(ancho, alto));
	}
	
	public void visualizar(){
		
	}
}
