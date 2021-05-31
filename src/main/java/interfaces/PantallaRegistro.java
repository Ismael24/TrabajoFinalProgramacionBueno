package interfaces;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.ChangeListener;

import clases.Monedero;
import clases.Usuario;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import enumeraciones.Estado;
import excepciones.PasswordMuyCortaException;
import excepciones.AliasVacioException;
import excepciones.CorreoVacioException;
import excepciones.NombreVacioException;

public class PantallaRegistro extends JPanel {
	private JTextField campoAlias;
	private Ventana ventana;
	private JTextField campoCorreo;
	private JPasswordField campoPassword;
	private JTextField campoUsuario;
	
	public PantallaRegistro(Ventana v) {
		setBackground(new Color(0, 0, 51));
		this.ventana=v;
		this.setSize(350,400);
		setLayout(new BorderLayout(0, 0));
		
		JLabel titulo = new JLabel("DropGames");
		titulo.setBackground(new Color(0, 0, 51));
		titulo.setForeground(Color.LIGHT_GRAY);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		add(titulo, BorderLayout.NORTH);
		
		final JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(153, 102, 204));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		campoAlias = new JTextField();
		campoAlias.setBackground(new Color(153, 153, 204));
		campoAlias.setBounds(173, 86, 130, 20);
		panelCentral.add(campoAlias);
		campoAlias.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setForeground(new Color(0, 0, 0));
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		labelNombre.setBounds(10, 37, 76, 15);
		panelCentral.add(labelNombre);
		
		JLabel labelAlias = new JLabel("Alias");
		labelAlias.setForeground(new Color(0, 0, 0));
		labelAlias.setFont(new Font("Arial", Font.PLAIN, 18));
		labelAlias.setHorizontalAlignment(SwingConstants.TRAILING);
		labelAlias.setBounds(10, 87, 42, 14);
		panelCentral.add(labelAlias);
		
		ButtonGroup grupoGenero=new ButtonGroup();
		
		final JButton botonRegistrarse = new JButton("Registrarse");
		botonRegistrarse.setBackground(new Color(153, 153, 204));
		
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage imgUsuario=null;
				try {
					imgUsuario = ImageIO.read(new File("mando.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String nombreUsuario=campoUsuario.getText();
				String aliasUsuario=campoAlias.getText();
				String password=new String(campoPassword.getPassword());
				byte nivel=0;
				String correoUsuario=campoCorreo.getText();
				Estado estado= Estado.CONECTADO;
				short puntosActual=0;
				short saldoActual=0;
				
				try {
					ventana.monederoActual=new Monedero(saldoActual,puntosActual);
					ventana.usuarioLogado=new Usuario(nombreUsuario,imgUsuario,aliasUsuario,
							password,nivel,correoUsuario,estado,ventana.monederoActual);
					Connection c= DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/dropgames",
							"root","admin");
							Statement smt=c.createStatement();
							Statement smta=c.createStatement();
							
							
							smt.executeUpdate(
					"insert into usuario "
	+ "values('"+nombreUsuario+"','"+aliasUsuario+"'"
			+ ",'"+password+"','"+nivel+"','"+correoUsuario+"','"+estado+"');");
							
							smta.executeUpdate(
									"insert into monedero "
					+ "values('"+saldoActual+"','"+puntosActual+"'"
							+ ",'"+nombreUsuario+"');");
				
							
							smt.close();
							smta.close();
							
							c.close();
					JOptionPane.showMessageDialog(ventana,
							"Usuario registrado con éxito","Registro completo",
							JOptionPane.INFORMATION_MESSAGE); 
					ventana.irAPantallaInicial();
				} catch (PasswordMuyCortaException e1) {
					JOptionPane.showMessageDialog(ventana,
							e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					campoPassword.setBackground(new Color(255,220,220));
				} catch (NombreVacioException e1) {
					JOptionPane.showMessageDialog(ventana,
							e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					campoUsuario.setBackground(new Color(255,220,220));
				} catch (CorreoVacioException e1) {
					JOptionPane.showMessageDialog(ventana,
							e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					campoCorreo.setBackground(new Color(255,220,220));
				} catch (AliasVacioException e1) {
					JOptionPane.showMessageDialog(ventana,
							e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					campoAlias.setBackground(new Color(255,220,220));
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(ventana,
							e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		botonRegistrarse.setBounds(173, 276, 107, 23);
		panelCentral.add(botonRegistrarse);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBackground(new Color(153, 153, 204));
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaInicioSesion();
			}
		});
		botonVolver.setBounds(64, 276, 89, 23);
		panelCentral.add(botonVolver);
		
		JLabel labelPassword = new JLabel("Contrase\u00F1a");
		labelPassword.setForeground(new Color(0, 0, 0));
		labelPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		labelPassword.setBounds(10, 128, 107, 15);
		panelCentral.add(labelPassword);
		
		JLabel labelCorreo = new JLabel("Correo electr\u00F3nico");
		labelCorreo.setForeground(new Color(0, 0, 0));
		labelCorreo.setFont(new Font("Arial", Font.PLAIN, 18));
		labelCorreo.setBounds(10, 180, 155, 15);
		panelCentral.add(labelCorreo);
		
		campoCorreo = new JTextField();
		campoCorreo.setBackground(new Color(153, 153, 204));
		campoCorreo.setBounds(173, 180, 130, 19);
		panelCentral.add(campoCorreo);
		campoCorreo.setColumns(10);
		
		campoPassword = new JPasswordField();
		campoPassword.setBackground(new Color(153, 153, 204));
		campoPassword.setBounds(173, 127, 130, 20);
		panelCentral.add(campoPassword);
		
		campoUsuario = new JTextField();
		campoUsuario.setBackground(new Color(153, 153, 204));
		campoUsuario.setBounds(173, 36, 130, 20);
		panelCentral.add(campoUsuario);
		campoUsuario.setColumns(10);

	}
}