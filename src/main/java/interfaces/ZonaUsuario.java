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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import enumeraciones.Estado;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class ZonaUsuario extends JPanel {
	private Ventana ventana;
	public ZonaUsuario(Ventana v) {
		setBackground(new Color(0, 0, 51));
		this.ventana=v;
		this.setSize(350,400);
		setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitle = new JLabel("Zona Usuario");
		labelTitle.setBackground(Color.ORANGE);
		labelTitle.setForeground(Color.LIGHT_GRAY);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Yu Gothic Medium", Font.BOLD, 18));
		add(labelTitle, BorderLayout.NORTH);
		
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
		
		JButton botonBiblioteca = new JButton("Biblioteca");
		botonBiblioteca.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		botonBiblioteca.setBackground(new Color(153, 153, 204));
		botonBiblioteca.setBounds(52, 119, 102, 35);
		panel.add(botonBiblioteca);
		
		JButton botonAjustes = new JButton("Monedero");
		botonAjustes.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		botonAjustes.setBackground(new Color(153, 153, 204));
		botonAjustes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAMonederoUsuario();
			}
		});
		botonAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonAjustes.setBounds(52, 195, 102, 35);
		panel.add(botonAjustes);
		
		JButton botonDeseados = new JButton("Deseados");
		botonDeseados.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		botonDeseados.setBackground(new Color(153, 153, 204));
		botonDeseados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonDeseados.setBounds(52, 157, 102, 35);
		panel.add(botonDeseados);
		
		JLabel labelEstado = new JLabel("Estado");
		labelEstado.setForeground(new Color(0, 0, 0));
		labelEstado.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		labelEstado.setBounds(20, 10, 45, 21);
		panel.add(labelEstado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		comboBox.setBackground(new Color(153, 153, 204));
		comboBox.setModel(new DefaultComboBoxModel(Estado.values()));
		comboBox.setBounds(75, 8, 102, 21);
		panel.add(comboBox);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setVerticalAlignment(SwingConstants.TOP);
		botonVolver.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		botonVolver.setBackground(new Color(153, 153, 204));
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaInicial();
				
			}
		});
		botonVolver.setBounds(232, 7, 89, 23);
		panel.add(botonVolver);
		
		int aleatorio;
		aleatorio = (int) (Math.random() *3);
		
		JLabel labelImgUsuario = new JLabel("");
		labelImgUsuario.setIcon(new ImageIcon("imgs\\avatar"+String.valueOf(aleatorio)+".png"));
		labelImgUsuario.setBounds(192, 93, 110, 151);
		panel.add(labelImgUsuario);
		
		JLabel labelNombreUsuario = new JLabel(ventana.usuarioLogado.getAlias());
		labelNombreUsuario.setForeground(new Color(0, 0, 0));
		labelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreUsuario.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		labelNombreUsuario.setBounds(192, 82, 110, 21);
		panel.add(labelNombreUsuario);
	}
}
