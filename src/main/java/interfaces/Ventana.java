package interfaces;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import clases.Monedero;
import clases.Usuario;
import excepciones.NombreVacioException;


public class Ventana extends JFrame{
	
	private PantallaInicioSesion pantallaInicioSesion;
	private PantallaRegistro pantallaRegistro;
	private PantallaInicial pantallaInicial;
	private ZonaUsuario pantallaZonaUsuario;
	private MonederoUsuario pantallaMonederoUsuario;
	private PantallaTienda pantallaTienda;
	private BibliotecaUsuario bibliotecaUsuario;
	private DeseadosUsuario deseadosUsuario;
	
	
	protected Usuario usuarioLogado;
	protected Monedero monederoActual;
	
	
	public Ventana() {
		this.setSize(350,400);
		this.setTitle("DropGames");
		
		
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        this.setLocation(x, y);
        
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pantallaInicioSesion=new PantallaInicioSesion(this);
		this.setContentPane(pantallaInicioSesion);
		this.setVisible(true);
	}
	
	public void irAPantallaRegistro() {
		if(this.pantallaRegistro==null) {
			this.pantallaRegistro=new PantallaRegistro(this);
		}
		if(this.pantallaInicioSesion!=null) {
			this.pantallaInicioSesion.setVisible(false);
		}
		this.setContentPane(this.pantallaRegistro);
		this.pantallaRegistro.setVisible(true);
	}
	
	public void irAPantallaInicioSesion() {
		if(this.pantallaInicioSesion==null) {
			pantallaInicioSesion=new PantallaInicioSesion(this);
		}
		if(this.pantallaRegistro!=null) {
			this.pantallaRegistro.setVisible(false);
		}
		this.setContentPane(this.pantallaInicioSesion);
		this.pantallaInicioSesion.setVisible(true);
		}
	
	public void irAPantallaInicial() {
		if(this.pantallaInicial==null) {
			this.pantallaInicial=new PantallaInicial(this);
		}
		if(this.pantallaRegistro!=null) {
			this.pantallaRegistro.setVisible(false);
		}
		if(this.pantallaTienda!=null) {
			this.pantallaTienda.setVisible(false);
			this.setSize(350,400);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = this.getSize().width;
	        int h = this.getSize().height;
	        int x = (dim.width-w)/2;
	        int y = (dim.height-h)/2;
			 this.setLocation(x, y);
		}
		if(this.pantallaZonaUsuario!=null) {
			this.pantallaZonaUsuario.setVisible(false);
			
		}
		
		if(this.pantallaInicioSesion!=null) {
			this.pantallaInicioSesion.setVisible(false);
			
		}
		
		this.setContentPane(this.pantallaInicial);
		this.pantallaInicial.setVisible(true);
	}
	
	public void irAZonaUsuario() {
		if(this.pantallaZonaUsuario==null) {
			this.pantallaZonaUsuario=new ZonaUsuario(this);
		}
		if(this.pantallaInicial!=null) {
			this.pantallaInicial.setVisible(false);
			this.setSize(350,400);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = this.getSize().width;
	        int h = this.getSize().height;
	        int x = (dim.width-w)/2;
	        int y = (dim.height-h)/2;
			 this.setLocation(x, y);
		}
		if(this.bibliotecaUsuario!=null) {
			this.bibliotecaUsuario.setVisible(false);
			this.setSize(350,400);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = this.getSize().width;
	        int h = this.getSize().height;
	        int x = (dim.width-w)/2;
	        int y = (dim.height-h)/2;
			 this.setLocation(x, y);
		}
		if(this.pantallaMonederoUsuario!=null) {
			this.pantallaMonederoUsuario.setVisible(false);
			this.setSize(350,400);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = this.getSize().width;
	        int h = this.getSize().height;
	        int x = (dim.width-w)/2;
	        int y = (dim.height-h)/2;
			 this.setLocation(x, y);
		}
		this.setContentPane(this.pantallaZonaUsuario);
		this.pantallaZonaUsuario.setVisible(true);
	}
	
	public void irAMonederoUsuario() {
		if(this.pantallaMonederoUsuario==null) {
			this.pantallaMonederoUsuario=new MonederoUsuario(this);
		}
		if(this.pantallaZonaUsuario!=null) {
			this.pantallaZonaUsuario.setVisible(false);
		}
		this.setContentPane(this.pantallaMonederoUsuario);
		this.pantallaMonederoUsuario.setVisible(true);
	}
	
	public void actualizarPantallaMonedero(){
		this.pantallaMonederoUsuario.setVisible(false);
		this.pantallaMonederoUsuario.setVisible(true);
		
	}
	
	public void irAPantallaTienda() {
		if(this.pantallaTienda==null) {
			this.pantallaTienda=new PantallaTienda(this);
		}
		if(this.pantallaInicial!=null) {
			this.pantallaInicial.setVisible(false);
			this.setSize(1300,450);
			this.setLocation(300, 200);
		}
		this.setContentPane(this.pantallaTienda);
		this.pantallaTienda.setVisible(true);
	}
	
	public void irABibliotecaUsuario() {
		if(this.bibliotecaUsuario==null) {
			this.bibliotecaUsuario=new BibliotecaUsuario(this);
		}
		if(this.pantallaZonaUsuario!=null) {
			this.pantallaZonaUsuario.setVisible(false);
			this.setSize(1300,450);
			this.setLocation(300, 200);
		}
		this.setContentPane(this.bibliotecaUsuario);
		this.bibliotecaUsuario.setVisible(true);
	}
	
	public void irADeseadosUsuario() {
		if(this.deseadosUsuario==null) {
			this.deseadosUsuario=new DeseadosUsuario(this);
		}
		if(this.pantallaZonaUsuario!=null) {
			this.pantallaZonaUsuario.setVisible(false);
			this.setSize(1300,450);
			this.setLocation(300, 200);
		}
		this.setContentPane(this.deseadosUsuario);
		this.deseadosUsuario.setVisible(true);
	}
	
	
	
	
	
	
	
}


