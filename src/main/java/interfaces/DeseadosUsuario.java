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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import clases.Juego;
import excepciones.NombreVacioException;
/**
 * Clase que representa la ventana del apartado de deseados del usuario, comentaré lo destacado, el resto es diseño visual de la ventana.
 * @author Ismael Paloma Narváez
 */
public class DeseadosUsuario extends JPanel {
	private Ventana ventana;

	public DeseadosUsuario(Ventana v) {
		setBackground(new Color(0, 0, 51));

		this.ventana = v;
		v.setSize(1300, 450);
		// v.setResizable(false);
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
		//el funcionamiento es basicamente el mismo que el de la biblioteca, exceptuando un boton que comentaré más abajo
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dropgames", "root", "admin");
			Statement smt = conexion.createStatement();

			final ResultSet resultSetJuegosUsuario = smt.executeQuery(
					"select * from juegos_deseados where usuario='" + ventana.usuarioLogado.getNombre() + "'");
			while (resultSetJuegosUsuario.next()) {

				try {

					Statement smta = conexion.createStatement();

					final ResultSet resultSetJuegos = smta.executeQuery("select * from juego");
					while (resultSetJuegos.next()) {
						if (resultSetJuegosUsuario.getString("juego").equals(resultSetJuegos.getString("nombre"))) {
							JPanel panelJuego = new JPanel();
							panelJuego.setLayout(new BorderLayout(0, 0));
							try {
								imgCaratula = ImageIO
										.read(new File("imgs\\" + resultSetJuegos.getString("nombre") + ".jpg"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							final String nombre = resultSetJuegos.getString("nombre");

							final Juego juego = new Juego(nombre, imgCaratula);

							JLabel labelImgPrincipal = new JLabel();
							labelImgPrincipal.setIcon(new ImageIcon(juego.getImgPrincipal()));
							panelJuego.add(labelImgPrincipal, BorderLayout.CENTER);

							JPanel panelInferior = new JPanel();
							panelInferior.setBackground(new Color(153, 102, 204));
							panelJuego.add(panelInferior, BorderLayout.SOUTH);
							
							JButton botonQuitarDeseados = new JButton("-");
							botonQuitarDeseados.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
							botonQuitarDeseados.setBackground(new Color(153, 153, 204));
							botonQuitarDeseados.addMouseListener(new MouseAdapter() {
								@Override
								//este boton va a borrar el juego que queramos de la lista de deseados conectando con la base de datos y haciendo un delete
								public void mouseClicked(MouseEvent e) {
									try {

										Connection conexion = DriverManager.getConnection(
												"jdbc:mysql://127.0.0.1:3306/dropgames", "root", "admin");

										Statement smta = conexion.createStatement();

										smta.executeUpdate(
												"DELETE FROM juegos_deseados WHERE juego = '" + nombre + "'");

										smta.close();
										conexion.close();
										JOptionPane.showMessageDialog(ventana,
												"El juego se ha quitado de la lista de deseados", "Éxito",
												JOptionPane.INFORMATION_MESSAGE);

									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error",
												JOptionPane.ERROR_MESSAGE);
									}

								}
							});
							panelInferior.add(botonQuitarDeseados, BorderLayout.EAST);

							panelListaJuegos.add(panelJuego);

						}

					}
					smta.close();

				} catch (SQLException | NombreVacioException e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

			smt.close();
			conexion.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
