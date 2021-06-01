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

public class PantallaInicial extends JPanel {
	private Ventana ventana;
	public PantallaInicial(Ventana v) {
		setBackground(new Color(0, 0, 51));
		this.ventana=v;
		v.setSize(350,400);
		
		v.setResizable(false);
		setLayout(new BorderLayout(0, 0));
		
		JLabel labelAccion = new JLabel("\u00BFA qu\u00E9 apartado deseas acceder?");
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
		botonUsuario.setForeground(new Color(0, 0, 0));
		botonUsuario.setBackground(new Color(153, 153, 204));
		botonUsuario.setBounds(10, 41, 147, 232);
		panel.add(botonUsuario);
		
		JButton botonTienda = new JButton("Tienda");
		botonTienda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaTienda();
			}
		});
		botonTienda.setBackground(new Color(153, 153, 204));
		botonTienda.setBounds(193, 41, 147, 232);
		panel.add(botonTienda);
		botonTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.irAZonaUsuario();
			}
		});
	}
	

}
