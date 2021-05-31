package interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.Monedero;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class MonederoUsuario extends JPanel {
	private Ventana ventana;
	private JTextField dineroAñadir;
	public MonederoUsuario(Ventana v) {
		setBackground(new Color(0, 0, 51));
		this.ventana=v;
		this.setSize(350,400);
		setLayout(new BorderLayout(0, 0));
		JLabel labelAccion = new JLabel("Monedero");
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
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(153, 102, 204));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saldo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 39, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u00BFCu\u00E1nto deseas a\u00F1adir?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(30, 113, 184, 13);
		panel.add(lblNewLabel_1);
		
		dineroAñadir = new JTextField();
		dineroAñadir.setBounds(153, 150, 96, 19);
		panel.add(dineroAñadir);
		dineroAñadir.setColumns(10);
		
		//ResultSet resultSetMonedero = select monederoActual from usuario;
		
		JButton botonPagar = new JButton("Pagar");
		botonPagar.addMouseListener(new MouseAdapter() {
			//@Override
			//public void mouseClicked(MouseEvent e) {
				//ventana.usuarioLogado.getMonedero().setSaldo((short)(ventana.usuarioLogado.getMonedero().getSaldo()+(Short.parseShort(dineroAñadir.getText()))));
				//resultSetMonedero.updateInt("saldo", ventana.usuarioLogado.getMonedero().getSaldo());
			//}
		});
		botonPagar.setBounds(199, 179, 85, 21);
		panel.add(botonPagar);
		
		JLabel labelSaldoActual = new JLabel(String.valueOf(ventana.usuarioLogado.getMonedero().getSaldo()));
		labelSaldoActual.setBounds(59, 18, 55, 14);
		panel.add(labelSaldoActual);
	}
}