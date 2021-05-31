package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Usuario;
import enumeraciones.Estado;
import excepciones.AliasVacioException;
import excepciones.CorreoVacioException;
import excepciones.NombreVacioException;
import excepciones.PasswordMuyCortaException;

import java.awt.SystemColor;

public class PantallaInicioSesion extends JPanel {
	private JTextField campoUsuario;
	private JPasswordField campoContrasenia;
	private Ventana ventana;
	
	public PantallaInicioSesion(Ventana v) {
		
		setBackground(new Color(0, 0, 51));
		this.ventana=v;
		this.setSize(350,400);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setForeground(new Color(0, 0, 102));
		panelCentral.setBackground(new Color(153, 102, 204));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		campoUsuario = new JTextField();
		campoUsuario.setBackground(new Color(153, 153, 204));
		campoUsuario.setBounds(168, 13, 135, 30);
		panelCentral.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		JLabel labelUsuario = new JLabel("Usuario");
		labelUsuario.setForeground(SystemColor.desktop);
		labelUsuario.setBackground(new Color(0, 0, 0));
		labelUsuario.setLabelFor(campoUsuario);
		labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
		labelUsuario.setBounds(30, 10, 72, 30);
		panelCentral.add(labelUsuario);
		
		campoContrasenia = new JPasswordField();
		campoContrasenia.setBackground(new Color(153, 153, 204));
		campoContrasenia.setBounds(168, 63, 135, 30);
		panelCentral.add(campoContrasenia);
		
		JLabel password = new JLabel("Contrase\u00F1a");
		password.setForeground(SystemColor.desktop);
		password.setBackground(new Color(0, 0, 0));
		password.setFont(new Font("Arial", Font.PLAIN, 18));
		password.setBounds(30, 64, 100, 22);
		panelCentral.add(password);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VSPC-SaltMirror\\Desktop\\com.ismael.trabajo.final.bueno\\imgs\\mando.png"));
		lblNewLabel.setBounds(115, 121, 100, 94);
		panelCentral.add(lblNewLabel);
		
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(153, 102, 204));
		add(panelInferior, BorderLayout.SOUTH);
		
		JButton botonLogin = new JButton("Iniciar Sesi\u00F3n");
		botonLogin.setBackground(new Color(153, 153, 204));
		botonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Capturar datos de los campos
				String usuario=campoUsuario.getText();
				String password=
						new String(campoContrasenia.getPassword());
				BufferedImage imgUsuario=null;
			/*	try {
					imgUsuario = ImageIO.read(new File("mando.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}*/
				Estado estado= Estado.CONECTADO;
				//Verificar que usuario y contraseña existen
				try {
					Connection conexion=
							DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/dropgames","root","admin"
									);
					Statement smt=conexion.createStatement();
					//Statement smt=conexion.createStatement();
					
					ResultSet resultados=smt.executeQuery(
							"select * from usuario where nombreUsuario='"+usuario+"' and "
							+"password='"+password+"'"
							);
					/*ResultSet resultadoMonedero=smt.executeQuery(
							"select * from monedero where nombreUsuario='"+usuario+"'"
							);*/
					if(resultados.next()) {
						usuario= resultados.getString("nombreUsuario");
						String alias= resultados.getString("aliasUsuario");
						password= resultados.getString("password");
						byte nivel= resultados.getByte("nivel");
						String correo= resultados.getString("correoUsuario");
						//float saldoMonedero= resultadoMonedero.getFloat("saldo");
						//byte puntosMonedero= resultadoMonedero.getByte("puntos");
						String estadoactual = resultados.getString("estado");
						
						ventana.usuarioLogado = new Usuario(usuario,imgUsuario,alias,password,nivel,correo,Estado.fromString(estadoactual));
						JOptionPane.showMessageDialog(ventana, "Login Correcto","Éxito",JOptionPane.INFORMATION_MESSAGE);
						ventana.irAPantallaInicial();
					}else {
						JOptionPane.showMessageDialog(ventana, "Login Incorrecto","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					smt.close();
					conexion.close();
					
					
				} catch (SQLException  | NombreVacioException | PasswordMuyCortaException | AliasVacioException | CorreoVacioException e1) {
					JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		panelInferior.add(botonLogin);
		
		JButton botonRegistro = new JButton("Registrarse");
		botonRegistro.setBackground(new Color(153, 153, 204));
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.irAPantallaRegistro();
			}
			
		});
		panelInferior.add(botonRegistro);
		
		JLabel titulo = new JLabel("DropGames");
		titulo.setForeground(Color.LIGHT_GRAY);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Yu Gothic Medium", Font.BOLD, 33));
		titulo.setBackground(new Color(0, 0, 51));
		add(titulo, BorderLayout.NORTH);
	}
}
