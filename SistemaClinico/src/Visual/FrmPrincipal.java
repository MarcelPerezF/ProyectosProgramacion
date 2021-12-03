package Visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Empleado;
import Logico.Enfermedad;
import Logico.Medico;
import Logico.Paciente;
import Logico.Usuario;
import Logico.Vacuna;

import java.awt.Insets;

import javax.swing.JLabel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import java.awt.Cursor;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	//Variables publicas importantes:
	public static String nombreUsuario;
	public static String rolUsuario;
	public static Usuario usuarioActual;//Para saber quien esta usando el sistema
	private final static String ficheroGuardar = "Respaldo/SistemaClinico.dat";
	
	//Constantes:
	private final int sizeIcon = 35;
	private final int sizeIconAccesoDirecto = 30;
	private final int sizeIconMin = 25;
	private final Color colorSubMenuFondo = new Color(255, 255, 255);
	
	//Componentes del formulario
	private JMenuBar menuBar;//Barra de menu
	private JPanel Frmbody;	 //Todo el contenido: Barra de acceso rapido - Cuerpo - Footer.
	
	//Componentes visuales de menu:
	private JMenu mnArchivo;
	private JMenuItem mnListadoCitasHoy;
	private JMenuItem mnInfoClinica;
	private JMenuItem mnCerrarSesion;
	private JMenuItem mnSalisSistema;

	private JMenu mnConsulta;
	private JMenuItem mnConsultar;
	private JMenuItem mnListadoConsultas;
	
	private JMenu mnUsuarios;
	private JMenuItem mnNuevoUsuario;
	private JMenuItem mnModificarUsuario;
	private JMenuItem mnListadoUsuarios;
	
	private JMenu mnPacientes;
	private JMenuItem mnModificarPacientes;
	private JMenuItem mnHistorialPacientes;
	private JMenuItem mnListadoPacientes;

	private JMenu mnCitas;
	private JMenuItem mnNuevaCita;
	private JMenuItem mnPosponerCita;
	private JMenuItem mnCancelarCita;
	
	private JMenu mnEnfermedades;
	private JMenuItem mnIngresarEnfermedades;
	private JMenuItem mnActualizarEnfermedad;
	private JMenuItem mnListadoEnfermedades;

	private JMenu mnVacunas;
	private JMenuItem mnIngresarVacunas;
	private JMenuItem mnListadoVacunas;
	
	private JMenu mnAcercaSistema;
	private JMenuItem mnInformacionSistema;
		
	//Panel de acceso directo:
	private JPanel panelAccesoDirecto;
	private JPanel pnAccesoDirectoCita;
	private JPanel pnAccesoDirectoConsulta;
	private JPanel pnAccesoDirectoUsuario;	
	private JLabel lblImagenAccesoDirectoConsulta;
	private JLabel lblImagenAccesoDirectoUsuario;
	private JLabel lblImagenPanelCita;
	private JLabel lblUsuarioSesion;

	//Panel de pie de footer:
	private JPanel pnFooter;
	private JLabel lblFooter;
	private static DefaultTableModel modeloTabla;
	
	//Imagenes a utilizar
	//Menus:
	private Image imagenAmbulancia = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Ambulancia.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenConsulta = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Consulta.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenEnfermedades= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Enfermedades.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenUsuarios= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Usuarios.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacunas = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenCitas= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Citas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenAcercaSistema= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Informacion.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenPaciente= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Paciente.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	
	//Submenus:
	private Image imagenClinica = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Clinica.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenCerrarSesion = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/CerrarSesion.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenSalir = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Salir.jpg")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenConsultar = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/ConsultaMedica.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenListados = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Listados.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenNuevoUsuario = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/NuevoUsuario.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenModificarUsuario = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/ModificarUsuario.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenListadoUsuarios = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/ListadoUsuarios.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenHistorialPacientes = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/HistoriaPaciente.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenModificar = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/ModificarPaciente.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenNuevaCita = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Citas2.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenPosponerCita = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Posponer.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenCancelarCita = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Cancelar.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenIngresarEnfermedad = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Enfermedades2.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenIngresarVacuna = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/IngresarVacunas.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenInfoSistema = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/InfoSistema.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	private Image imagenSolicitarVacuna = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Vacunas3.png")).getImage().getScaledInstance(sizeIconMin, sizeIconMin, Image.SCALE_SMOOTH);
	
	//Acceso Directo:
	private Image imagenConsultaAccesoDirecto = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/ConsultaMedica.png")).getImage().getScaledInstance(sizeIconAccesoDirecto, sizeIconAccesoDirecto, Image.SCALE_SMOOTH);
	private Image imagenCitasAccesoDirecto= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Citas2.png")).getImage().getScaledInstance(sizeIconAccesoDirecto, sizeIconAccesoDirecto, Image.SCALE_SMOOTH);
	private Image imagenUsuarioAccesoDirecto= new ImageIcon(FrmPrincipal.class.getResource("Imagenes/NuevoUsuario.png")).getImage().getScaledInstance(sizeIconAccesoDirecto, sizeIconAccesoDirecto, Image.SCALE_SMOOTH);
	private JMenuItem mnSolicitarVacuna;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medico medico = new Medico("1", "301", 1, "med", "med", "Antonio", "829", "RD", "algo@", "Hombre", "Cirugia");
					Empleado administrador = new Empleado("2", "302", 2, "admin", "admin", "Orlando M", "849", "RD", "algo@", "Hombre", "Administrador");
					Empleado secretaria = new Empleado("3", "303", 3, "secre", "secre", "Ana Lopez", "859", "RD", "algo@", "Mujer", "Secretaria");
					
					Clinica.getInstance().insertarUsuario(medico);
					Clinica.getInstance().insertarUsuario(administrador);
					Clinica.getInstance().insertarUsuario(secretaria);
					FrmPrincipal frame = new FrmPrincipal(medico);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmPrincipal(Usuario usurioLogeado) {
		usuarioActual = usurioLogeado;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guardarDatosSistema();
				JOptionPane.showMessageDialog(null, "Saliendo del sistema", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
			}
		});		
		//Propiedades generales del JFrame (Menu Principal):
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension=getToolkit().getScreenSize();
		setResizable(false);
		setIconImage((imagenUsuarios));
		setTitle("Sistema Clinico");
		setBounds(100, 100, dimension.width, dimension.height-40);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setMargin(new Insets(10, 10, 10, 10));
		menuBar.setSize(new Dimension(0, 40));
		menuBar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setBorderPainted(true);
		mnArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnArchivo.setIcon(new ImageIcon(imagenAmbulancia));
		menuBar.add(mnArchivo);
		
		mnListadoCitasHoy = new JMenuItem("Listado Citas");
		mnListadoCitasHoy.setBackground(colorSubMenuFondo);
		mnListadoCitasHoy.setIcon(new ImageIcon(imagenListados));
		mnListadoCitasHoy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoCitas frmAux = new FrmListadoCitas(null, null);
				frmAux.setVisible(true);
			}
		});
		mnArchivo.add(mnListadoCitasHoy);
		
		JSeparator spArchivo1 = new JSeparator();
		mnArchivo.add(spArchivo1);
		
		mnInfoClinica = new JMenuItem("Informaci\u00F3n Clinica");
		mnInfoClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmInformacionClinica frmAux = new FrmInformacionClinica();
				frmAux.setVisible(true);
			}
		});
		mnInfoClinica.setBackground(colorSubMenuFondo);
		mnInfoClinica.setIcon(new ImageIcon(imagenClinica));
		mnArchivo.add(mnInfoClinica);
		
		JSeparator spArchivo2 = new JSeparator();
		mnArchivo.add(spArchivo2);
		
		mnCerrarSesion = new JMenuItem("Cerras Sesion");
		mnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatosSistema();
				JOptionPane.showMessageDialog(null, "Cerrando la sesion", "CERRANDO SESION", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				FrmLoginSistema frmAux = new FrmLoginSistema(2);
				frmAux.setVisible(true);
			}
		});
		mnCerrarSesion.setBackground(colorSubMenuFondo);
		mnCerrarSesion.setIcon(new ImageIcon(imagenCerrarSesion));
		mnArchivo.add(mnCerrarSesion);
		
		JSeparator spArchivo3 = new JSeparator();
		mnArchivo.add(spArchivo3);
		
		mnSalisSistema = new JMenuItem("Salir del sistema");
		mnSalisSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatosSistema();
				JOptionPane.showMessageDialog(null, "Saliendo del sistema", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		mnSalisSistema.setBackground(colorSubMenuFondo);
		mnSalisSistema.setIcon(new ImageIcon(imagenSalir));
		mnArchivo.add(mnSalisSistema);
		
		mnConsulta = new JMenu("   Consulta   ");
		mnConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnConsulta.setIcon(new ImageIcon(imagenConsulta));
		menuBar.add(mnConsulta);
		
		mnConsultar = new JMenuItem("Nueva Consulta");
		mnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario aux = Clinica.getInstance().buscarUsuario(usuarioActual.getCedulaUsuario());
				Date fecha = new Date();
				FrmListadoCitas frmAux = new FrmListadoCitas(aux, fecha);
				frmAux.setVisible(true);
			}
		});
		mnConsultar.setBackground(colorSubMenuFondo);
		mnConsultar.setIcon(new ImageIcon(imagenConsultar));
		mnConsulta.add(mnConsultar);
		
		JSeparator spConsulta2 = new JSeparator();
		mnConsulta.add(spConsulta2);
		
		mnListadoConsultas = new JMenuItem("Listado de consultas");
		mnListadoConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoConsultas frmAux = new FrmListadoConsultas();
				frmAux.setVisible(true);
			}
		});
		mnListadoConsultas.setIcon(new ImageIcon(imagenListados));
		mnListadoConsultas.setBackground(colorSubMenuFondo);
		mnConsulta.add(mnListadoConsultas);
		
		mnUsuarios = new JMenu("   Usuarios   ");
		mnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnUsuarios.setIcon(new ImageIcon(imagenUsuarios));
		menuBar.add(mnUsuarios);
		
		mnNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmIngresarUsuario frmAux = new FrmIngresarUsuario(1, null, 0);
				frmAux.setVisible(true);
			}
		});
		mnNuevoUsuario.setIcon(new ImageIcon(imagenNuevoUsuario));
		mnNuevoUsuario.setBackground(colorSubMenuFondo);
		mnUsuarios.add(mnNuevoUsuario);
		
		JSeparator spUsuario1 = new JSeparator();
		mnUsuarios.add(spUsuario1);
		
		mnModificarUsuario = new JMenuItem("Modificar Usuario");
		mnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(null, "Seleccione lo que desea modificar del usuario", "Selector de opciones", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, new ImageIcon(imagenInfoSistema), new Object[] {"informaci\u00f3n personal", "Credenciales"}, "informaci\u00f3nn personal");
				if(opcion!=-1) {
					opcion+=2;//Esto porque al listado llegara 2 o 3 si se desea modificar.
					FrmListadoUsuarios frmAux = new FrmListadoUsuarios(opcion,"");
					frmAux.setVisible(true);
				}
			}
		});
		mnModificarUsuario.setIcon(new ImageIcon(imagenModificarUsuario));
		mnModificarUsuario.setBackground(colorSubMenuFondo);
		mnUsuarios.add(mnModificarUsuario);		
		JSeparator spUsuario2 = new JSeparator();
		mnUsuarios.add(spUsuario2);
		
		mnListadoUsuarios = new JMenuItem("Listado Usuarios");
		mnListadoUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoUsuarios frmAux = new FrmListadoUsuarios(1,"");
				frmAux.setVisible(true);
			}
		});
		mnListadoUsuarios.setIcon(new ImageIcon(imagenListadoUsuarios));
		mnListadoUsuarios.setBackground(colorSubMenuFondo);
		mnUsuarios.add(mnListadoUsuarios);
		
		mnPacientes = new JMenu("Pacientes");
		mnPacientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnPacientes.setIcon(new ImageIcon(imagenPaciente));
		menuBar.add(mnPacientes);
		
		mnHistorialPacientes = new JMenuItem("Historial Pacientes");
		mnHistorialPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoPaciente frmAux = new FrmListadoPaciente(3);
				frmAux.setVisible(true);
			}
		});
		mnHistorialPacientes.setIcon(new ImageIcon(imagenHistorialPacientes));
		mnHistorialPacientes.setBackground(colorSubMenuFondo);
		mnPacientes.add(mnHistorialPacientes);
		
		JSeparator spPaciente1 = new JSeparator();
		mnPacientes.add(spPaciente1);
		
		mnModificarPacientes = new JMenuItem("Modificar Pacientes");
		mnModificarPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoPaciente frmAux = new FrmListadoPaciente(2);
				frmAux.setVisible(true);
			}
		});
		mnModificarPacientes.setIcon(new ImageIcon(imagenModificar));
		mnModificarPacientes.setBackground(colorSubMenuFondo);
		mnPacientes.add(mnModificarPacientes);
		
		JSeparator spPaciente2 = new JSeparator();
		mnPacientes.add(spPaciente2);
		
		mnListadoPacientes = new JMenuItem("Listado Pacientes");
		mnListadoPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoPaciente frmAux = new FrmListadoPaciente(1);
				frmAux.setVisible(true);
			}
		});
		mnListadoPacientes.setIcon(new ImageIcon(imagenListados));
		mnListadoPacientes.setBackground(colorSubMenuFondo);
		mnPacientes.add(mnListadoPacientes);
		
		mnCitas = new JMenu("  Citas   ");
		mnCitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnCitas.setIcon(new ImageIcon(imagenCitas));
		menuBar.add(mnCitas);
		
		mnNuevaCita = new JMenuItem("Nueva Cita");
		mnNuevaCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCita aux = new FrmCita(usuarioActual);
				aux.setVisible(true);
			}
		});
		mnNuevaCita.setIcon(new ImageIcon(imagenNuevaCita));
		mnNuevaCita.setBackground(colorSubMenuFondo);
		mnCitas.add(mnNuevaCita);
		
		JSeparator spCita1 = new JSeparator();
		mnCitas.add(spCita1);
		
		mnPosponerCita = new JMenuItem("Posponer Cita");
		mnPosponerCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoCitaModificar aux = new FrmListadoCitaModificar(1);
				aux.setVisible(true);
			}
		});
		mnPosponerCita.setIcon(new ImageIcon(imagenPosponerCita));
		mnPosponerCita.setBackground(colorSubMenuFondo);
		mnCitas.add(mnPosponerCita);
		
		JSeparator spCita2 = new JSeparator();
		mnCitas.add(spCita2);
		
		mnCancelarCita = new JMenuItem("Cancelar Cita");
		mnCancelarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoCitaModificar aux = new FrmListadoCitaModificar(2);
				aux.setVisible(true);
			}
		});
		mnCancelarCita.setIcon(new ImageIcon(imagenCancelarCita));
		mnCancelarCita.setBackground(colorSubMenuFondo);
		mnCitas.add(mnCancelarCita);
		
		mnEnfermedades = new JMenu("   Enfermedades   ");
		mnEnfermedades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnEnfermedades.setIcon(new ImageIcon(imagenEnfermedades));
		menuBar.add(mnEnfermedades);
		
		mnIngresarEnfermedades = new JMenuItem("Ingresar Enfermedad");
		mnIngresarEnfermedades.setIcon(new ImageIcon(imagenIngresarEnfermedad));
		mnIngresarEnfermedades.setBackground(colorSubMenuFondo);
		mnEnfermedades.add(mnIngresarEnfermedades);
		mnIngresarEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmIngresarEnfermedad aux = new FrmIngresarEnfermedad(null);
				aux.setVisible(true);
			}
		});
		
		JSeparator spEnfermedades1 = new JSeparator();
		mnEnfermedades.add(spEnfermedades1);
		
		mnActualizarEnfermedad = new JMenuItem("Actualizar Enfermedad");
		mnActualizarEnfermedad.setIcon(new ImageIcon(imagenModificar));
		mnActualizarEnfermedad.setBackground(colorSubMenuFondo);
		mnEnfermedades.add(mnActualizarEnfermedad);
		mnActualizarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoEnfermedad aux = new FrmListadoEnfermedad(true, 1);
				aux.setVisible(true);
			}
		});
		
		JSeparator spEnfermedad2 = new JSeparator();
		mnEnfermedades.add(spEnfermedad2);
		
		mnListadoEnfermedades = new JMenuItem("Listado Enfermedades");
		mnListadoEnfermedades.setIcon(new ImageIcon(imagenListados));
		mnListadoEnfermedades.setBackground(colorSubMenuFondo);
		mnEnfermedades.add(mnListadoEnfermedades);
		mnListadoEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoEnfermedad aux = new FrmListadoEnfermedad(false, 1);
				aux.setVisible(true);
			}
		});
		
		mnVacunas = new JMenu("   Vacunas   ");
		mnVacunas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnVacunas.setIcon(new ImageIcon(imagenVacunas));
		menuBar.add(mnVacunas);
		
		mnIngresarVacunas = new JMenuItem("Ingresar Vacuna");
		mnIngresarVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmIngresarVacuna aux = new FrmIngresarVacuna();
				aux.setVisible(true);
			}
		});
		mnIngresarVacunas.setIcon(new ImageIcon(imagenIngresarVacuna));
		mnIngresarVacunas.setBackground(colorSubMenuFondo);
		mnVacunas.add(mnIngresarVacunas);
		
		JSeparator spVacunas2 = new JSeparator();
		mnVacunas.add(spVacunas2);
		
		mnListadoVacunas = new JMenuItem("Listado Vacunas");
		mnListadoVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoVacuna aux = new FrmListadoVacuna(false,1);
				aux.setVisible(true);
			}
		});
		mnListadoVacunas.setIcon(new ImageIcon(imagenListados));
		mnListadoVacunas.setBackground(colorSubMenuFondo);
		mnVacunas.add(mnListadoVacunas);
		
		JSeparator separator = new JSeparator();
		mnVacunas.add(separator);
		
		mnSolicitarVacuna = new JMenuItem("Solicitar Vacunas");
		mnSolicitarVacuna.setBackground(Color.WHITE);
		mnSolicitarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoVacuna aux = new FrmListadoVacuna(true,2);
				aux.setVisible(true);
			}
		});
		mnSolicitarVacuna.setIcon(new ImageIcon(imagenSolicitarVacuna));
		mnVacunas.add(mnSolicitarVacuna);
		
		mnAcercaSistema = new JMenu("   Acerca del Sistema   ");
		mnAcercaSistema.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnAcercaSistema.setIcon(new ImageIcon(imagenAcercaSistema));
		menuBar.add(mnAcercaSistema);
		
		mnInformacionSistema = new JMenuItem("Informacion del Sistema");
		mnInformacionSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmInformacionSistema frmAux = new FrmInformacionSistema();
				frmAux.setVisible(true);
			}
		});
		mnInformacionSistema.setIcon(new ImageIcon(imagenInfoSistema));
		mnInformacionSistema.setBackground(colorSubMenuFondo);
		mnAcercaSistema.add(mnInformacionSistema);
		
		Frmbody = new JPanel();
		Frmbody.setBackground(Color.WHITE);
		Frmbody.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Frmbody);
		Frmbody.setLayout(null);
		modeloTabla =  new DefaultTableModel();
		String[] columnas = {"Codigo Cita", "Paciente", "Hora", "Medico", "Estado Cita"};
		modeloTabla.setColumnIdentifiers(columnas);
		
		panelAccesoDirecto = new JPanel();
		panelAccesoDirecto.setBounds(0, 0, 1920, 40);
		Frmbody.add(panelAccesoDirecto);
		panelAccesoDirecto.setLayout(null);
		
		pnAccesoDirectoCita = new JPanel();
		pnAccesoDirectoCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnAccesoDirectoCita.setBorder(new BevelBorder(BevelBorder.LOWERED));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnAccesoDirectoCita.setBorder(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmCita aux = new FrmCita(null);
				aux.setVisible(true);
			}
		});
		pnAccesoDirectoCita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnAccesoDirectoCita.setBounds(15, 5, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
		panelAccesoDirecto.add(pnAccesoDirectoCita);
		pnAccesoDirectoCita.setLayout(null);
		
		lblImagenPanelCita = new JLabel("");
		lblImagenPanelCita.setBounds(0, 0, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
		lblImagenPanelCita.setBackground(new Color(239, 239, 239));
		lblImagenPanelCita.setIcon(new ImageIcon(imagenCitasAccesoDirecto));
		pnAccesoDirectoCita.add(lblImagenPanelCita);
		
		pnAccesoDirectoConsulta = new JPanel();
		pnAccesoDirectoConsulta.setBackground(new Color(239, 239, 239));
		pnAccesoDirectoConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(lblImagenAccesoDirectoConsulta.isEnabled()) 
					pnAccesoDirectoConsulta.setBorder(new BevelBorder(BevelBorder.LOWERED));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(lblImagenAccesoDirectoConsulta.isEnabled()) 
					pnAccesoDirectoConsulta.setBorder(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				usuarioActual = Clinica.getInstance().buscarUsuario("402");
				FrmListadoCitas frmAux = new FrmListadoCitas(usuarioActual, new Date());
				frmAux.setVisible(true);
			}
		});
		pnAccesoDirectoConsulta.setLayout(null);
		pnAccesoDirectoConsulta.setBounds(70, 5, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
		panelAccesoDirecto.add(pnAccesoDirectoConsulta);
		
		lblImagenAccesoDirectoConsulta = new JLabel("");
		lblImagenAccesoDirectoConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagenAccesoDirectoConsulta.setBackground(new Color(239, 239, 239));
		lblImagenAccesoDirectoConsulta.setBounds(0, 0, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
		lblImagenAccesoDirectoConsulta.setIcon(new ImageIcon(imagenConsultaAccesoDirecto));
		pnAccesoDirectoConsulta.add(lblImagenAccesoDirectoConsulta);
		
		
		
		lblUsuarioSesion = new JLabel("Usuario: "+usuarioActual.getNombre()+" ("+Clinica.getInstance().tipoUsuario(usuarioActual)+")");
		lblUsuarioSesion.setBounds(dimension.width-320, 10, 300, 20);
		panelAccesoDirecto.add(lblUsuarioSesion);
		
		pnAccesoDirectoUsuario = new JPanel();
		pnAccesoDirectoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnAccesoDirectoUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnAccesoDirectoUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnAccesoDirectoUsuario.setBorder(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmIngresarUsuario frmAux = new FrmIngresarUsuario(1, null,0);
				frmAux.setVisible(true);
			}
		});
		pnAccesoDirectoUsuario.setLayout(null);
		pnAccesoDirectoUsuario.setBorder(null);
		pnAccesoDirectoUsuario.setBackground(new Color(239, 239, 239));
		pnAccesoDirectoUsuario.setBounds(130, 5, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
		panelAccesoDirecto.add(pnAccesoDirectoUsuario);
		
		lblImagenAccesoDirectoUsuario = new JLabel("");
		lblImagenAccesoDirectoUsuario.setBackground(new Color(239, 239, 239));
		lblImagenAccesoDirectoUsuario.setBounds(0, 0, 30, 30);
		lblImagenAccesoDirectoUsuario.setIcon(new ImageIcon(imagenUsuarioAccesoDirecto));
		pnAccesoDirectoUsuario.add(lblImagenAccesoDirectoUsuario);
		
		pnFooter = new JPanel();
		pnFooter.setBounds(0, dimension.height-169, dimension.width, 50);
		pnFooter.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		Frmbody.add(pnFooter);
		pnFooter.setLayout(null);
		
		lblFooter = new JLabel("Sistema Clinico -  @Todos los derechos son reservados");
		lblFooter.setBounds(dimension.width-(dimension.width/2)-200, 10, 500, 30);
		pnFooter.add(lblFooter);
		
		accesosSistema(usuarioActual);
		
	}
	
	public static void guardarDatosSistema() {
		try {
			
			//Creamos el archivo para guardar los datos.
			FileOutputStream guardandoDatos = new FileOutputStream(ficheroGuardar);
			
			//Creamos el fichero objeto del archivo para guardar.
			ObjectOutputStream oos = new ObjectOutputStream(guardandoDatos);
			
			//Guardamos la cantidad de usuarios y luego guardamos cada uno de los usuarios.
			oos.writeInt(Clinica.getInstance().getMisUsuarios().size());
			for (Usuario usuario: Clinica.getInstance().getMisUsuarios()) {
				oos.writeObject(usuario);
			}
			
			//Guardamos la cantidad de vacunas y luego guardamos cada uno de las vacunas.
			oos.writeInt(Clinica.getInstance().getMisVacunas().size());
			for (Vacuna vacuna: Clinica.getInstance().getMisVacunas()) {
				oos.writeObject(vacuna);
			}
			
			//Guardamos la cantidad de enfermedades y luego guardamos cada una de las enfermedades.
			oos.writeInt(Clinica.getInstance().getMisEnfermedades().size());
			for (Enfermedad enfermedad: Clinica.getInstance().getMisEnfermedades()) {
				oos.writeObject(enfermedad);
			}
			
			//Guardamos la cantidad de citas medicas y luego guardamos cada una de las citas medicas.
			oos.writeInt(Clinica.getInstance().getMisCitasMedicas().size());
			for (CitaMedica citamedica: Clinica.getInstance().getMisCitasMedicas()) {
				oos.writeObject(citamedica);
			}
			
			//Guardamos la cantidad de pacientes y luego guardamos cada uno de los pacientes.
			oos.writeInt(Clinica.getInstance().getMisPacientes().size());
			for (Paciente paciente: Clinica.getInstance().getMisPacientes()) {
				oos.writeObject(paciente);
			}
			
			//Cerramos el fichero
			oos.close();
		}catch (IOException e2) {
			System.out.println(e2.getMessage());
		}
		
	}
	
	public void accesosSistema(Usuario usuarioSistema) {
		
		String tipoUsuario = Clinica.getInstance().tipoUsuario(usuarioSistema);
		
		if(tipoUsuario.equalsIgnoreCase("Administrador")) {
			
			mnNuevaCita.setEnabled(false);
			mnPosponerCita.setEnabled(false);
			mnCancelarCita.setEnabled(false);
			mnConsultar.setEnabled(false);
			pnAccesoDirectoCita.setVisible(false);
			pnAccesoDirectoConsulta.setVisible(false);
			pnAccesoDirectoUsuario.setBounds(15, 5, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
			
		}else if(tipoUsuario.equalsIgnoreCase("Secretaria")){
			
			mnInfoClinica.setEnabled(false);
			mnConsulta.setEnabled(false);
			mnUsuarios.setEnabled(false);
			mnPacientes.setEnabled(false);
			mnEnfermedades.setEnabled(false);
			mnVacunas.setEnabled(false);
			pnAccesoDirectoConsulta.setVisible(false);
			pnAccesoDirectoUsuario.setVisible(false);
			
		}else if(tipoUsuario.equalsIgnoreCase("Medico")) {
			
			mnInfoClinica.setEnabled(false);
			mnUsuarios.setEnabled(false);
			mnCitas.setEnabled(false);
			pnAccesoDirectoCita.setVisible(false);
			pnAccesoDirectoUsuario.setVisible(false);
			pnAccesoDirectoConsulta.setBounds(15, 5, sizeIconAccesoDirecto, sizeIconAccesoDirecto);
			
		}
		
	}
}
