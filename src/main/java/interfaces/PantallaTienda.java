package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import clases.Juego;
import clases.Tienda;
import clases.Usuario;
import enumeraciones.Estado;
import enumeraciones.Genero;
import excepciones.NombreVacioException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
//pantalla de la tienda
public class PantallaTienda extends JPanel{
	private Ventana ventana;

	public PantallaTienda(Ventana v) {
		
		this.ventana=v;
		this.setSize(1200,1000);
        this.setLocation(0, 0);
		setLayout(new BorderLayout(0, 0));
		
		final HashMap<String,Juego> todosJuegos = new HashMap<>();
		BufferedImage imgCaratula = null;
		
		
		
		
		
		JLabel labelTitulotop = new JLabel("Juegos");
		labelTitulotop.setFont(new Font("Tahoma", Font.PLAIN, 30));
		labelTitulotop.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelTitulotop, BorderLayout.NORTH);
		
		JLabel labelDropgames = new JLabel("Drop Games");
		labelDropgames.setHorizontalAlignment(SwingConstants.CENTER);
		labelDropgames.setFont(new Font("Tahoma", Font.PLAIN, 34));
		add(labelDropgames, BorderLayout.SOUTH);
		
		
		JPanel panelListaJuegos = new JPanel();
		panelListaJuegos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		

		JScrollPane scrollListaJuegos = new JScrollPane(panelListaJuegos);
		add(scrollListaJuegos, BorderLayout.CENTER);
		
		try {
			Connection conexion=
					DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/dropgames","root","admin"
							);
			Statement smt=conexion.createStatement();
			
			ResultSet resultSetJuegos=smt.executeQuery(
					"select * from juego"
					);
			while(resultSetJuegos.next()) {

				JPanel panelJuego = new JPanel();
				panelJuego.setLayout(new BorderLayout(0, 0));
				try {
					imgCaratula = ImageIO.read(new File("C:\\Users\\VSPC-SaltMirror\\Desktop\\com.ismael.trabajo.final.bueno\\imgs\\"+resultSetJuegos.getString("nombre")+".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String nombre=resultSetJuegos.getString("nombre");
				String descripcion=resultSetJuegos.getString("descripcion");
				float precio=resultSetJuegos.getFloat("precio");
				int vecesValorado=resultSetJuegos.getInt("vecesValorado");
				float valoracion=resultSetJuegos.getFloat("valoracion");
				String lenguaje=resultSetJuegos.getString("lenguaje");
				String genero=resultSetJuegos.getString("genero");
				short duracion=resultSetJuegos.getShort("duracion");
				
				
				final Juego juego=new Juego(nombre,imgCaratula,descripcion,precio,vecesValorado,valoracion,lenguaje,Genero.fromString(genero),duracion);
				todosJuegos.put(nombre, juego);
				
				JLabel labelImgPrincipal = new JLabel();
				labelImgPrincipal.setIcon(new ImageIcon(imgCaratula));
				panelJuego.add(labelImgPrincipal, BorderLayout.CENTER);
				
				
				JButton botonComprarJuego = new JButton("Comprar");
				botonComprarJuego.addMouseListener(new MouseAdapter() {
					@Override
				
					public void mouseClicked(MouseEvent e) {
						if(ventana.usuarioLogado.getBiblioteca().contains(juego)) {
							JOptionPane.showMessageDialog(ventana,
									"Ya tienes este juego","Error",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							ventana.usuarioLogado.comprarJuegos((float)(juego.getPrecio()), juego);
							JOptionPane.showMessageDialog(ventana,
									"Juego comprado con éxito","Juego añadido a la biblioteca",
									JOptionPane.INFORMATION_MESSAGE); 
							
						}
					}
				});
				panelJuego.add(botonComprarJuego, BorderLayout.SOUTH);
				
				JButton botonDetallesJuego = new JButton("Detalles");
				botonDetallesJuego.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(ventana,
								juego.getDescripcion()+","+"\n"+
								juego.getGenero().toString(),//+","+
								//juego.getFechaLanzamiento().toString()+","+
								//juego.getLenguaje().toString()+","+
								//String.valueOf(juego.getPrecio())+","+
								//String.valueOf(juego.getValoracion())+","+
								//String.valueOf(juego.getVecesValorado()),
								"Todos los detalles",
								JOptionPane.INFORMATION_MESSAGE); 
						 
					}
				});
				panelJuego.add(botonDetallesJuego, BorderLayout.NORTH);
				
				
				

				panelListaJuegos.add(panelJuego);
				
				
				
				}
			
			smt.close();
			conexion.close();
			
			
		} catch (SQLException  | NombreVacioException e1) {
			JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

