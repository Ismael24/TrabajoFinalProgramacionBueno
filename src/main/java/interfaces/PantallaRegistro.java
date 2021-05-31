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
		this.ventana=v;
		this.setSize(350,400);
		setLayout(new BorderLayout(0, 0));
		
		JLabel titulo = new JLabel("Registrar Usuario");
		titulo.setForeground(new Color(46, 139, 87));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		add(titulo, BorderLayout.NORTH);
		
		final JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		campoAlias = new JTextField();
		campoAlias.setBounds(173, 86, 86, 20);
		panelCentral.add(campoAlias);
		campoAlias.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelNombre.setBounds(47, 37, 53, 14);
		panelCentral.add(labelNombre);
		
		JLabel labelAlias = new JLabel("Alias");
		labelAlias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelAlias.setHorizontalAlignment(SwingConstants.TRAILING);
		labelAlias.setBounds(47, 86, 29, 14);
		panelCentral.add(labelAlias);
		
		ButtonGroup grupoGenero=new ButtonGroup();
		
		final JButton botonRegistrarse = new JButton("Registrarse");
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
					Monedero monederoActual=new Monedero(saldoActual,puntosActual);
					ventana.usuarioLogado=new Usuario(nombreUsuario,imgUsuario,aliasUsuario,
							password,nivel,correoUsuario,estado,monederoActual);
					Connection c= DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/dropgames",
							"root","admin");
							Statement smt=c.createStatement();
				
							smt.executeUpdate(
					"insert into usuario "
	+ "values('"+nombreUsuario+"','"+aliasUsuario+"'"
			+ ",'"+password+"','"+nivel+"','"+correoUsuario+"','"+estado+"','"+monederoActual+"');");
							
							smt.close();
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
		botonRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botonRegistrarse.setBackground(
						new Color(200,0,0));
				botonRegistrarse.setForeground(
						new Color(255,255,255)
						);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botonRegistrarse.setBackground(null);
				botonRegistrarse.setForeground(null);
			}
		});
		botonRegistrarse.setBounds(254, 301, 86, 63);
		panelCentral.add(botonRegistrarse);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantallaInicioSesion();
			}
		});
		botonVolver.setBounds(10, 301, 89, 63);
		panelCentral.add(botonVolver);
		
		JLabel labelPassword = new JLabel("Contrase\u00F1a");
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelPassword.setBounds(47, 130, 76, 13);
		panelCentral.add(labelPassword);
		
		JLabel labelCorreo = new JLabel("Correo electr\u00F3nico");
		labelCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelCorreo.setBounds(39, 182, 114, 13);
		panelCentral.add(labelCorreo);
		
		campoCorreo = new JTextField();
		campoCorreo.setBounds(173, 180, 86, 20);
		panelCentral.add(campoCorreo);
		campoCorreo.setColumns(10);
		
		campoPassword = new JPasswordField();
		campoPassword.setBounds(173, 128, 86, 20);
		panelCentral.add(campoPassword);
		
		campoUsuario = new JTextField();
		campoUsuario.setBounds(173, 36, 86, 20);
		panelCentral.add(campoUsuario);
		campoUsuario.setColumns(10);

	}
}