package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import clases.Juego;
import enumeraciones.Genero;
import excepciones.NombreVacioException;

public class BibliotecaUsuario extends JPanel{
	
	private Ventana ventana;

	public BibliotecaUsuario(Ventana v) {
		setBackground(new Color(0, 0, 51));
		
		this.ventana=v;
		v.setSize(1300,450);
		v.setResizable(false);
        v.setLocation(300, 200);
		setLayout(new BorderLayout(0, 0));
		
		
		
	
		
		
		BufferedImage imgCaratula = null;
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 102, 204));
		add(panel, BorderLayout.WEST);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAZonaUsuario();
			}
		});
		botonVolver.setBackground(new Color(153, 153, 204));
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(botonVolver);
		
		
		
		
		
		JLabel labelDropGames = new JLabel("DropGames");
		labelDropGames.setBackground(new Color(0, 0, 51));
		labelDropGames.setForeground(Color.LIGHT_GRAY);
		labelDropGames.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		labelDropGames.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelDropGames, BorderLayout.NORTH);
		
		JLabel labelBiblioteca = new JLabel("Biblioteca");
		labelBiblioteca.setBackground(new Color(0, 0, 51));
		labelBiblioteca.setForeground(Color.LIGHT_GRAY);
		labelBiblioteca.setHorizontalAlignment(SwingConstants.CENTER);
		labelBiblioteca.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		add(labelBiblioteca, BorderLayout.SOUTH);
		
		
		JPanel panelListaJuegos = new JPanel();
		panelListaJuegos.setBorder(null);
		panelListaJuegos.setForeground(new Color(0, 0, 0));
		panelListaJuegos.setBackground(new Color(153, 102, 204));
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
				
				if(ventana.usuarioLogado.getBiblioteca().containsKey(juego.getNombre())) {
				JLabel labelImgPrincipal = new JLabel();
				labelImgPrincipal.setIcon(new ImageIcon(imgCaratula));
				panelJuego.add(labelImgPrincipal, BorderLayout.CENTER);
				
				
				JPanel panelInferior = new JPanel();
				panelInferior.setBackground(new Color(153, 102, 204));
				panelJuego.add(panelInferior, BorderLayout.SOUTH);
				

				
				panelListaJuegos.add(panelJuego);
				
				
				}
			}
			
			smt.close();
			conexion.close();
			
			
		} catch (SQLException  | NombreVacioException e1) {
			JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		
		
	}
	
	

}
