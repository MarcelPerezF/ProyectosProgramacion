package Visual;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Enfermedad;
import Logico.Paciente;
import Logico.Usuario;
import Logico.Vacuna;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLoginSistema extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image imagenLogin = new ImageIcon(FrmLoginSistema.class.getResource("Imagenes/Usuario.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Image imagenLoginTitulo = new ImageIcon(FrmLoginSistema.class.getResource("Imagenes/ClinicaTitulo.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	
	private Image imagenLoginLimpiar = new ImageIcon(FrmLoginSistema.class.getResource("Imagenes/Limpiar.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image imagenLoginIngresar = new ImageIcon(FrmLoginSistema.class.getResource("Imagenes/Ingresar.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image imagenLoginSalir = new ImageIcon(FrmLoginSistema.class.getResource("Imagenes/Salir.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private JLabel lblImagnLogin;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JLabel lblReloj;
	private JLabel lblTitulo;
	private JButton btnSalir;
	private JButton btnIngresar;
	private JButton btnLimpiar;
	private JLabel lblImagenTitulo;
	private final String ficheroGuardar = "src/Fichero/SistemaClinico.dat";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					FrmLoginSistema frame = new FrmLoginSistema(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLoginSistema(int opcion) {
		//opcion:1--> Iniciando el programa.
		//opcion:2--> Se ingreso al login por medio del principal cerrando la sesion
		
		if(opcion==1) {
			cargarDatosSistema();
		}
		setIconImage(imagenLogin);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(61, 176, 227));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(7, 139, 197));
		panel.setBounds(15, 105, 477, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblImagnLogin = new JLabel("");
		lblImagnLogin.setBounds(54, 19, 80, 80);
		panel.add(lblImagnLogin);
		lblImagnLogin.setIcon(new ImageIcon(imagenLogin));
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(imagenLoginLimpiar));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuario.setText("");
				txtPassword.setText("");
				btnLimpiar.setEnabled(false);
			}
		});
		btnLimpiar.setEnabled(false);
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setBounds(54, 115, 115, 37);
		panel.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(imagenLoginSalir));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(318, 115, 115, 37);
		panel.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(160, 17, 69, 20);
		panel.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
			}
		});
		txtUsuario.setBounds(236, 16, 198, 23);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setBounds(149, 77, 93, 20);
		panel.add(lblContrasea);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
			}
		});
		txtPassword.setBounds(236, 76, 198, 23);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usurio = Clinica.getInstance().comprobarLogin(txtUsuario.getText(), txtPassword.getText());
				if( usurio!=null) {
					JOptionPane.showMessageDialog(null, "Usuario y Contraseña correctos", "Credenciales Correctas", JOptionPane.INFORMATION_MESSAGE);
					FrmPrincipal frmAux = new FrmPrincipal(usurio);
					frmAux.setVisible(true);
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "Credenciales Incorrectas", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setIcon(new ImageIcon(imagenLoginIngresar));
		btnIngresar.setEnabled(false);
		btnIngresar.setBackground(Color.WHITE);
		btnIngresar.setBounds(188, 115, 115, 37);
		panel.add(btnIngresar);
		
		lblReloj = new JLabel("");
		lblReloj.setForeground(Color.BLACK);
		lblReloj.setBounds(197, 60, 247, 20);
		contentPane.add(lblReloj);
		
		lblTitulo = new JLabel("SISTEMA CLINICO");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 22));
		lblTitulo.setBounds(197, 33, 194, 20);
		contentPane.add(lblTitulo);
		
		lblImagenTitulo = new JLabel("");
		lblImagenTitulo.setIcon(new ImageIcon(imagenLoginTitulo));
		lblImagenTitulo.setBounds(117, 24, 65, 65);
		contentPane.add(lblImagenTitulo);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro que deseas salir del sistema?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					JOptionPane.showMessageDialog(null, "Saliendo del sistema", "SALIENDO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		
		cursorBotones(btnLimpiar);
		cursorBotones(btnIngresar);
		cursorBotones(btnSalir);
		
		comprobarCampos(txtUsuario);
		comprobarCampos(txtPassword);
		Thread reloj;
		reloj = new Thread(this);
		reloj.start();
	}
	
	public void cursorBotones(JButton boton) {
		
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(boton.isEnabled()) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	
	public void comprobarCampos(JTextField text) {
		text.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txtUsuario.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txtUsuario.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txtUsuario.getText().equalsIgnoreCase("") && !txtPassword.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}
				
			}
		});
	}

	@Override
	public void run() {
		Date hora = new Date();
		@SuppressWarnings("deprecation")
		int horas=hora.getHours(), minutos=hora.getMinutes(), segundos=hora.getSeconds();
		
		while(true) {
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			
			lblReloj.setText(dia+" de "+nombreMes+" del "+year+" - "+horas+":"+minutos+":"+segundos);
			
			lblReloj.setText(lblReloj.getText()+"");
			segundos++;
			if(segundos==60) {
				segundos=0;
				minutos++;
				if(minutos==60) {
					minutos=0;
					horas++;
					if(horas==24) {
						horas=0;
					}
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

		}
		
	}
	
	public void cargarDatosSistema() {
		try {
			
			//Creamos el archivo donde esta la informacion.
			FileInputStream recibiendoDatos = new FileInputStream(ficheroGuardar);
			
			//Creamos el fichero por el cual leeremos y guardaremos la informacion
			ObjectInputStream oosLectura = new ObjectInputStream(recibiendoDatos);
				
			Clinica temporal = (Clinica)oosLectura.readObject();
			
			Clinica.setClinica(temporal);
			
			//Cerramos el fichero
			oosLectura.close();
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "BIENVENIDO A TU SISTEMA CLINICO", "BIENVENIDOS", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "A CONTINUACION INGRESE EL PRIMER USUARIO PARA COMENZAR A \nUTILIZAR EL SISTEMA "
					+ "Y ACCEDER A TODAS SUS FUNCIONALIDADES", "BIENVENIDOS", JOptionPane.INFORMATION_MESSAGE);
			FrmIngresarUsuario frmAux = new FrmIngresarUsuario(1, null, 1);
			frmAux.setVisible(true);
			if(!(Clinica.getInstance().getMisUsuarios().size()==0)) {
				JOptionPane.showMessageDialog(null, "A CONTINUACI\u00d3N INGRESE SUS CREDENCIALES", "INFORMACI\u00d3N", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fichero no encontrado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Clase no encontrada");

		}
	}
}
