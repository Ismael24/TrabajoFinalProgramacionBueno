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
import java.util.HashSet;
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
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//pantalla de la tienda
public class PantallaTienda extends JPanel{
	private Ventana ventana;

	public PantallaTienda(Ventana v) {
		setBackground(new Color(0, 0, 51));
		
		this.ventana=v;
		v.setSize(1300,450);
		v.setResizable(false);
        v.setLocation(300, 200);
		setLayout(new BorderLayout(0, 0));
		
		final HashMap<String,Juego> todosJuegos = new HashMap<>();
		
		
		BufferedImage imgCaratula = null;
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 102, 204));
		add(panel, BorderLayout.WEST);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaInicial();
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
		
		JLabel labelJuegos = new JLabel("Juegos");
		labelJuegos.setBackground(new Color(0, 0, 51));
		labelJuegos.setForeground(Color.LIGHT_GRAY);
		labelJuegos.setHorizontalAlignment(SwingConstants.CENTER);
		labelJuegos.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		add(labelJuegos, BorderLayout.SOUTH);
		
		
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
				todosJuegos.put(nombre, juego);
				
				JLabel labelImgPrincipal = new JLabel();
				labelImgPrincipal.setIcon(new ImageIcon(imgCaratula));
				panelJuego.add(labelImgPrincipal, BorderLayout.CENTER);
				
				
				JButton botonComprarJuego = new JButton("Comprar");
				botonComprarJuego.setBackground(new Color(153, 153, 204));
				botonComprarJuego.addMouseListener(new MouseAdapter() {
					@Override
				
					public void mouseClicked(MouseEvent e) {
						//if(ventana.usuarioLogado.getBiblioteca().contains(juego)) {
							//JOptionPane.showMessageDialog(ventana,
									//"Ya tienes este juego","Error",
									//JOptionPane.INFORMATION_MESSAGE);
						//}else {
							//if(ventana.monederoActual.getSaldo()>=juego.getPrecio()) {
								
								try {
									
									ventana.monederoActual.setSaldo((float)(ventana.monederoActual.getSaldo()-juego.getPrecio()));
									Connection conexion=
											DriverManager.getConnection(
														"jdbc:mysql://127.0.0.1:3306/dropgames","root","admin"
													);
									
									Statement smta=conexion.createStatement();
									
									
									smta.executeUpdate(
											"UPDATE monedero SET saldo = '"+ventana.monederoActual.getSaldo()+"' WHERE nombreUsuario = '"+ventana.usuarioLogado.getNombre()+"'");
									
									
									
									
									
									
									
									smta.close();
									conexion.close();
									
									
									
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
								}
								//ventana.usuarioLogado.getBiblioteca().add(juego);
								JOptionPane.showMessageDialog(ventana,
										"¡Que lo disfrutes!","Éxito",
										JOptionPane.INFORMATION_MESSAGE);
									
								
							//}else {
								//JOptionPane.showMessageDialog(ventana,
									//	"No tienes saldo suficiente, acude a la zona usuario","Error",
								//		JOptionPane.INFORMATION_MESSAGE);
								
							//}
							
							
							
						//}
					}
				});
				
				
				
				panelJuego.add(botonComprarJuego, BorderLayout.NORTH);
				
				
				
				JPanel panelInferior = new JPanel();
				panelInferior.setBackground(new Color(153, 102, 204));
				panelJuego.add(panelInferior, BorderLayout.SOUTH);
				
				
				JButton botonDeseados = new JButton("+");
				botonDeseados.setBackground(new Color(153, 153, 204));
				botonDeseados.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(ventana,
								"Se ha añadido a la lista de deseados",
								"Deseados",
								JOptionPane.INFORMATION_MESSAGE); 
						 
					}
				});
				panelInferior.add(botonDeseados, BorderLayout.WEST);
				
				JButton botonDetallesJuego = new JButton("Detalles");
				botonDetallesJuego.setBackground(new Color(153, 153, 204));
				botonDetallesJuego.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(ventana,
								"Precio: "+String.valueOf(juego.getPrecio())+"\n"+
								juego.getDescripcion()+","+"\n"+
								juego.getGenero().toString()+","+
								//juego.getFechaLanzamiento().toString()+","+
								//juego.getLenguaje().toString()+","+
								
								String.valueOf(juego.getValoracion())+","+
								String.valueOf(juego.getVecesValorado()),
								"Todos los detalles",
								JOptionPane.INFORMATION_MESSAGE); 
						 
					}
				});
				panelInferior.add(botonDetallesJuego, BorderLayout.EAST);
				panelListaJuegos.add(panelJuego);
				
				
				
				}
			
			smt.close();
			conexion.close();
			
			
		} catch (SQLException  | NombreVacioException e1) {
			JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

