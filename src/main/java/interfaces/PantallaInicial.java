package interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
/**
 * Clase que representa la ventana de la pantalla inicial , comentaré lo destacado, el resto es diseño visual de la ventana.
 * @author Ismael Paloma Narváez
 */
public class PantallaInicial extends JPanel {
	private Ventana ventana;

	public PantallaInicial(Ventana v) {
		setBackground(new Color(0, 0, 51));
		this.ventana = v;
		v.setSize(350, 400);

		v.setResizable(false);
		setLayout(new BorderLayout(0, 0));

		JLabel labelAccion = new JLabel("\u00BFA qu\u00E9 apartado desea acceder?");
		labelAccion.setBackground(Color.ORANGE);
		labelAccion.setForeground(Color.LIGHT_GRAY);
		labelAccion.setHorizontalAlignment(SwingConstants.CENTER);
		labelAccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(labelAccion, BorderLayout.NORTH);

		JLabel labelTitulo = new JLabel("DropGames");
		labelTitulo.setForeground(Color.LIGHT_GRAY);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		labelTitulo.setBackground(Color.PINK);
		add(labelTitulo, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 102, 204));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton botonUsuario = new JButton("Zona Usuario");
		botonUsuario.setIcon(new ImageIcon("imgs\\zonausuario.gif"));
		botonUsuario.setForeground(new Color(0, 0, 0));
		botonUsuario.setBackground(new Color(153, 153, 204));
		botonUsuario.setBounds(27, 75, 133, 150);
		panel.add(botonUsuario);

		JButton botonTienda = new JButton("");
		botonTienda.setIcon(new ImageIcon("imgs\\tienda.gif"));
		botonTienda.setForeground(new Color(0, 0, 0));
		botonTienda.addMouseListener(new MouseAdapter() {
			@Override
			//enlace a otra ventana, lo veremos amenudo en el programa
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaTienda();
			}
		});
		botonTienda.setBackground(new Color(153, 153, 204));
		botonTienda.setBounds(170, 75, 133, 150);
		panel.add(botonTienda);

		JLabel labelTituloZonaUsuario = new JLabel("Zona Usuario");
		labelTituloZonaUsuario.setForeground(new Color(0, 0, 51));
		labelTituloZonaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelTituloZonaUsuario.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		labelTituloZonaUsuario.setBounds(27, 47, 133, 24);
		panel.add(labelTituloZonaUsuario);

		JLabel labelTituloTienda = new JLabel("Tienda");
		labelTituloTienda.setForeground(new Color(0, 0, 51));
		labelTituloTienda.setHorizontalAlignment(SwingConstants.CENTER);
		labelTituloTienda.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		labelTituloTienda.setBounds(170, 47, 133, 22);
		panel.add(labelTituloTienda);
		botonTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		botonUsuario.addActionListener(new ActionListener() {
			//enlace a otra ventana, lo veremos amenudo en el programa
			public void actionPerformed(ActionEvent e) {
				ventana.irAZonaUsuario();
			}
		});
	}

}
