package interfaces;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.Monedero;
import clases.Usuario;
import enumeraciones.Estado;
import excepciones.AliasVacioException;
import excepciones.CorreoVacioException;
import excepciones.NombreVacioException;
import excepciones.PasswordMuyCortaException;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		final JPanel panel = new JPanel();
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
		dineroAñadir.setBackground(new Color(153, 153, 204));
		dineroAñadir.setBounds(153, 150, 96, 19);
		panel.add(dineroAñadir);
		dineroAñadir.setColumns(10);
		
		
		
		JButton botonPagar = new JButton("Pagar");
		botonPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonPagar.setBackground(new Color(153, 153, 204));
		botonPagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//esto es un comentario para probar github
				try {
					
					ventana.monederoActual.setSaldo((float)((ventana.monederoActual.getSaldo())+(Float.parseFloat(dineroAñadir.getText()))));
					Connection conexion=
							DriverManager.getConnection(
										"jdbc:mysql://127.0.0.1:3306/dropgames","root","admin"
									);
					
					Statement smta=conexion.createStatement();
					
					
					smta.executeUpdate(
							"UPDATE monedero SET saldo = '"+ventana.monederoActual.getSaldo()+"' WHERE nombreUsuario = '"+ventana.usuarioLogado.getNombre()+"'");
					
					JLabel labelSaldoActual = new JLabel(String.valueOf(ventana.monederoActual.getSaldo()));
					labelSaldoActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
					labelSaldoActual.setBounds(61, 16, 55, 14);
					panel.add(labelSaldoActual);
					
					
					
					ventana.actualizarPantallaMonedero();
					
					smta.close();
					conexion.close();
					
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
				//ventana.actualizarPantallaMonedero();
				
				
				
			}
		});
		
		botonPagar.setBounds(199, 179, 85, 26);
		panel.add(botonPagar);
		
		JLabel labelSaldoActual = new JLabel(String.valueOf(ventana.monederoActual.getSaldo()));
		labelSaldoActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelSaldoActual.setBounds(61, 16, 55, 14);
		panel.add(labelSaldoActual);
		
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.irAZonaUsuario();
			}
		});
		botonVolver.setBackground(new Color(153, 153, 204));
		botonVolver.setBounds(222, 12, 85, 26);
		panel.add(botonVolver);
		
		
		
	}
}