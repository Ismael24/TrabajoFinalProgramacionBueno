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

/**
 * Clase que representa la ventana de la pantalla tienda , comentar� lo destacado, el resto es dise�o visual de la ventana.
 * @author Ismael Paloma Narv�ez
 */
public class PantallaTienda extends JPanel {
	private Ventana ventana;

	public PantallaTienda(Ventana v) {
		setBackground(new Color(0, 0, 51));

		this.ventana = v;
		v.setResizable(false);
		setLayout(new BorderLayout(0, 0));

		final HashMap<String, Juego> todosJuegos = new HashMap<>();

		BufferedImage imgCaratula = null;

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 102, 204));
		add(panel, BorderLayout.WEST);

		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
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

		JLabel labelJuegos = new JLabel("Tienda");
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
			//conectamos con la base de datos para sacar todos los datos de la clase juego y poder mostrar lo que nos interese gr�ficamente en la ventana
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dropgames", "root", "admin");
			Statement smt = conexion.createStatement();

			ResultSet resultSetJuegos = smt.executeQuery("select * from juego");
			//todo se realizar� mediante un bucle para que nos muestre tanto los juegos como sus botones y acciones de forma individual
			while (resultSetJuegos.next()) {

				JPanel panelJuego = new JPanel();
				panelJuego.setLayout(new BorderLayout(0, 0));
				try {
					imgCaratula = ImageIO.read(new File("imgs\\" + resultSetJuegos.getString("nombre") + ".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String nombre = resultSetJuegos.getString("nombre");
				String descripcion = resultSetJuegos.getString("descripcion");
				float precio = resultSetJuegos.getFloat("precio");
				String lenguaje = resultSetJuegos.getString("lenguaje");
				String genero = resultSetJuegos.getString("genero");
				short duracion = resultSetJuegos.getShort("duracion");
				String fecha = resultSetJuegos.getString("fechaLanzamiento");

				final Juego juego = new Juego(nombre, imgCaratula, descripcion, precio, lenguaje,
						Genero.fromString(genero), duracion, fecha);
				todosJuegos.put(nombre, juego);

				JLabel labelImgPrincipal = new JLabel();
				labelImgPrincipal.setIcon(new ImageIcon(imgCaratula));
				panelJuego.add(labelImgPrincipal, BorderLayout.CENTER);

				JButton botonComprarJuego = new JButton("Comprar");
				botonComprarJuego.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
				botonComprarJuego.setBackground(new Color(153, 153, 204));
				botonComprarJuego.addMouseListener(new MouseAdapter() {
					@Override

					public void mouseClicked(MouseEvent e) {
//acci�n para comprar un juego, comprobamos si ya lo tiene y si no es el caso procedemos a restarle el saldo y a�adirlo a la biblioteca
						//, a su vez realizamos un update para guardar el nuevo saldo y demas, si el usuario no tiene saldo suficiente no le dejar� efectuar el pago
						try {

							if (ventana.usuarioLogado.getBiblioteca().containsKey(juego.getNombre())) {
								JOptionPane.showMessageDialog(ventana, "Ya tienes este juego", "Error",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								if (ventana.monederoActual.getSaldo() >= juego.getPrecio()) {
									ventana.monederoActual
											.setSaldo((float) (ventana.monederoActual.getSaldo() - juego.getPrecio()));
									Connection conexion = DriverManager
											.getConnection("jdbc:mysql://127.0.0.1:3306/dropgames", "root", "admin");

									Statement smta = conexion.createStatement();

									smta.executeUpdate("UPDATE monedero SET saldo = '"
											+ ventana.monederoActual.getSaldo() + "' WHERE nombreUsuario = '"
											+ ventana.usuarioLogado.getNombre() + "'");

									smta.close();
									conexion.close();

									Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dropgames",
											"root", "admin");
									Statement smt = c.createStatement();

									smt.executeUpdate("insert into juegos_biblioteca " + "values('"
											+ ventana.usuarioLogado.getNombre() + "','" + juego.getNombre() + "');");
									ventana.usuarioLogado.getBiblioteca().put(juego.getNombre(), juego);

									smt.close();

									c.close();
									ventana.usuarioLogado.getBiblioteca().put(juego.getNombre(), juego);
									JOptionPane.showMessageDialog(ventana,
											"�Que lo disfrutes!, recuerda reiniciar el programa para que te aparezca",
											"�xito", JOptionPane.INFORMATION_MESSAGE);

								} else {
									JOptionPane.showMessageDialog(ventana,
											"No tienes saldo suficiente, acude a la zona usuario", "Error",
											JOptionPane.INFORMATION_MESSAGE);
								}
							}

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}

					}
				});

				panelJuego.add(botonComprarJuego, BorderLayout.NORTH);

				JPanel panelInferior = new JPanel();
				panelInferior.setBackground(new Color(153, 102, 204));
				panelJuego.add(panelInferior, BorderLayout.SOUTH);

				JButton botonDeseados = new JButton("+");
				botonDeseados.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
				botonDeseados.setBackground(new Color(153, 153, 204));
				botonDeseados.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							//acci�n para a�adir nuestro juego a la parte de deseados del usuario, mismo funcionamiento que a�adir a la biblioteca pero sin restar saldo

							if (ventana.usuarioLogado.getBiblioteca().containsKey(juego.getNombre())) {
								JOptionPane.showMessageDialog(ventana, "Tienes este juego, no puedes desearlo", "Error",
										JOptionPane.INFORMATION_MESSAGE);
							} else {

								Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dropgames",
										"root", "admin");
								Statement smt = c.createStatement();

								smt.executeUpdate("insert into juegos_deseados " + "values('"
										+ ventana.usuarioLogado.getNombre() + "','" + juego.getNombre() + "');");
								ventana.usuarioLogado.getBiblioteca().put(juego.getNombre(), juego);

								smt.close();

								c.close();

								JOptionPane.showMessageDialog(ventana,
										"Se ha a�adido a la lista de deseados, recuerda reiniciar el programa para que te aparezca",
										"Deseados", JOptionPane.INFORMATION_MESSAGE);

							}

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				panelInferior.add(botonDeseados, BorderLayout.WEST);

				JButton botonDetallesJuego = new JButton("Detalles");
				botonDetallesJuego.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
				botonDetallesJuego.setBackground(new Color(153, 153, 204));
				botonDetallesJuego.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//accion que abre una ventana emergente con algunos datos interesantes sobre el juego
						JOptionPane.showMessageDialog(ventana,
								"Precio: " + String.valueOf(juego.getPrecio()) + "\n" + juego.getDescripcion() + "."
										+ "\n" + "Genero: " + juego.getGenero().toString() + "." + "\n"
										+ juego.getFechaLanzamiento() + "\n" + juego.getLenguaje(),

								"Todos los detalles", JOptionPane.INFORMATION_MESSAGE);

					}
				});
				panelInferior.add(botonDetallesJuego, BorderLayout.EAST);
				panelListaJuegos.add(panelJuego);

			}

			smt.close();
			conexion.close();

		} catch (SQLException | NombreVacioException e1) {
			JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
