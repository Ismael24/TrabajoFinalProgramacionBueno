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
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		botonBiblioteca.setBounds(116, 90, 125, 125);
		panel.add(botonBiblioteca);
		
		JButton botonAjustes = new JButton("Monedero");
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
		botonAjustes.setBounds(254, 232, 86, 66);
		panel.add(botonAjustes);
		
		JButton botonDeseados = new JButton("Deseados");
		botonDeseados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonDeseados.setBounds(20, 232, 86, 66);
		panel.add(botonDeseados);
		
		JLabel labelEstado = new JLabel("Estado");
		labelEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelEstado.setBounds(20, 10, 45, 13);
		panel.add(labelEstado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Estado.values()));
		comboBox.setBounds(75, 7, 86, 21);
		panel.add(comboBox);
	}
	

}
