package Visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Logico.Empleado;
import Logico.Medico;
import Logico.Usuario;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmListadoUsuariosDetallado extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenListadoUsuarios= new ImageIcon(FrmListadoUsuarios.class.getResource("Imagenes/ListadoUsuarios.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenListadoUsuarios2= new ImageIcon(FrmListadoUsuarios.class.getResource("Imagenes/ListadoUsuarios.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoUsuario;
	private JTextField txtIdUsuario;
	private JTextField txtGeneroUsuario;
	private JTextField txtEmailUsuario;
	private JTextField txtDireccionUsuario;
	private JTextField txtTelefonoUsuario;
	private JTextField txtCedulaUsuario;
	private JTextField txtNombreUsuario;
	private JTextField txtTipoUsuario;
	private JTextField txtPassword;
	private JTextField txtUsuario;
	private JTextField txtEspecialidad_Cargo;
	private JButton btnAceptar;
	private Usuario usuario;
	private JLabel lblEspecialidad_Cargo;
	
	public static void main(String[] args) {
		try {
			FrmListadoUsuariosDetallado dialog = new FrmListadoUsuariosDetallado(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrmListadoUsuariosDetallado(Usuario usuarioSeleccionado) {
		usuario = usuarioSeleccionado;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver la informaci\u00f3n detallada del usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de la vista detallada de la informaci\u00f3n del usuario", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(aux==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setTitle("Informaci\u00F3n detallada del usuario");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 600, 670);
		setIconImage(imagenListadoUsuarios);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 15, 567, 100);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(33, 10, 88, 86);
		lblImagen.setIcon(new ImageIcon(imagenListadoUsuarios2));
		contentPanel.add(lblImagen);
		{
			JLabel lblTituloFormulario = new JLabel("Informaci\u00F3n del Usuario");
			lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTituloFormulario.setBounds(199, 13, 195, 26);
			contentPanel.add(lblTituloFormulario);
		}
		{
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			
			JLabel FechaFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
			FechaFormulario.setBounds(222, 40, 158, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblDescripcionFormulario = new JLabel("Informaci\u00F3n del Usuario detallada");
		lblDescripcionFormulario.setBounds(176, 70, 238, 26);
		contentPanel.add(lblDescripcionFormulario);
		
		JPanel pnUsuarioDetallado = new JPanel();
		pnUsuarioDetallado.setBorder(new TitledBorder(null, "Informaci\u00F3n del usuario", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnUsuarioDetallado.setBounds(12, 130, 567, 484);
		getContentPane().add(pnUsuarioDetallado);
		pnUsuarioDetallado.setLayout(null);
		
		txtCodigoUsuario = new JTextField();
		txtCodigoUsuario.setEditable(false);
		txtCodigoUsuario.setColumns(10);
		txtCodigoUsuario.setBounds(168, 27, 85, 23);
		pnUsuarioDetallado.add(txtCodigoUsuario);
		
		JLabel lblCodigoUsuario = new JLabel("C\u00F3digo del usuario:");
		lblCodigoUsuario.setBounds(15, 28, 162, 22);
		pnUsuarioDetallado.add(lblCodigoUsuario);
		
		JLabel lblIdUsuario = new JLabel("ID del usuario:");
		lblIdUsuario.setBounds(322, 28, 121, 22);
		pnUsuarioDetallado.add(lblIdUsuario);
		
		txtIdUsuario = new JTextField();
		txtIdUsuario.setEditable(false);
		txtIdUsuario.setColumns(10);
		txtIdUsuario.setBounds(446, 27, 85, 23);
		pnUsuarioDetallado.add(txtIdUsuario);
		
		txtGeneroUsuario = new JTextField();
		txtGeneroUsuario.setEditable(false);
		txtGeneroUsuario.setColumns(10);
		txtGeneroUsuario.setBounds(168, 253, 363, 23);
		pnUsuarioDetallado.add(txtGeneroUsuario);
		
		JLabel lblGeneroUsuario = new JLabel("Genero del usuario:");
		lblGeneroUsuario.setBounds(15, 255, 162, 22);
		pnUsuarioDetallado.add(lblGeneroUsuario);
		
		JLabel lblEmailUsuario = new JLabel("Email del usuario:");
		lblEmailUsuario.setBounds(15, 220, 162, 22);
		pnUsuarioDetallado.add(lblEmailUsuario);
		
		txtEmailUsuario = new JTextField();
		txtEmailUsuario.setEditable(false);
		txtEmailUsuario.setColumns(10);
		txtEmailUsuario.setBounds(168, 220, 363, 23);
		pnUsuarioDetallado.add(txtEmailUsuario);
		
		txtDireccionUsuario = new JTextField();
		txtDireccionUsuario.setEditable(false);
		txtDireccionUsuario.setColumns(10);
		txtDireccionUsuario.setBounds(168, 182, 363, 23);
		pnUsuarioDetallado.add(txtDireccionUsuario);
		
		JLabel lblDireccionUsuario = new JLabel("Direcci\u00F3n del usuario:");
		lblDireccionUsuario.setBounds(15, 182, 162, 22);
		pnUsuarioDetallado.add(lblDireccionUsuario);
		
		JLabel lblTelefonoUsuario = new JLabel("Telefono del usuario:");
		lblTelefonoUsuario.setBounds(15, 144, 162, 22);
		pnUsuarioDetallado.add(lblTelefonoUsuario);
		
		txtTelefonoUsuario = new JTextField();
		txtTelefonoUsuario.setEditable(false);
		txtTelefonoUsuario.setColumns(10);
		txtTelefonoUsuario.setBounds(168, 144, 363, 23);
		pnUsuarioDetallado.add(txtTelefonoUsuario);
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario:");
		lblNombreUsuario.setBounds(15, 105, 162, 22);
		pnUsuarioDetallado.add(lblNombreUsuario);
		
		txtCedulaUsuario = new JTextField();
		txtCedulaUsuario.setEditable(false);
		txtCedulaUsuario.setColumns(10);
		txtCedulaUsuario.setBounds(168, 66, 363, 23);
		pnUsuarioDetallado.add(txtCedulaUsuario);
		
		JLabel lblCedulaUsuario = new JLabel("C\u00E9dula del usuario:");
		lblCedulaUsuario.setBounds(15, 67, 162, 22);
		pnUsuarioDetallado.add(lblCedulaUsuario);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setEditable(false);
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(168, 105, 363, 23);
		pnUsuarioDetallado.add(txtNombreUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(15, 292, 68, 22);
		pnUsuarioDetallado.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(15, 330, 85, 22);
		pnUsuarioDetallado.add(lblPassword);
		
		JLabel lblTipoUsuario = new JLabel("Tipo de Usuario:");
		lblTipoUsuario.setBounds(15, 368, 161, 22);
		pnUsuarioDetallado.add(lblTipoUsuario);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setEditable(false);
		txtTipoUsuario.setColumns(10);
		txtTipoUsuario.setBounds(168, 367, 363, 23);
		pnUsuarioDetallado.add(txtTipoUsuario);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setColumns(10);
		txtPassword.setBounds(168, 329, 363, 23);
		pnUsuarioDetallado.add(txtPassword);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(168, 291, 363, 23);
		pnUsuarioDetallado.add(txtUsuario);
		
		lblEspecialidad_Cargo = new JLabel("");
		lblEspecialidad_Cargo.setBounds(15, 407, 161, 22);
		pnUsuarioDetallado.add(lblEspecialidad_Cargo);
		
		txtEspecialidad_Cargo = new JTextField();
		txtEspecialidad_Cargo.setEditable(false);
		txtEspecialidad_Cargo.setColumns(10);
		txtEspecialidad_Cargo.setBounds(168, 406, 363, 23);
		pnUsuarioDetallado.add(txtEspecialidad_Cargo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver la informaci\u00f3n detallada del usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de la vista detallada de la informaci\u00f3n del usuario", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					FrmListadoUsuarios frmAux = new FrmListadoUsuarios(1,"");
					frmAux.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(226, 440, 115, 29);
		pnUsuarioDetallado.add(btnAceptar);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		try {
			txtCodigoUsuario.setText(usuario.getCodigoUsuario());
			txtIdUsuario.setText(""+usuario.getIdUsuario());
			txtNombreUsuario.setText(usuario.getNombre());
			txtCedulaUsuario.setText(usuario.getCedulaUsuario());
			txtTelefonoUsuario.setText(usuario.getTelefono());
			txtDireccionUsuario.setText(usuario.getDireccion());
			txtEmailUsuario.setText(usuario.getEmail());
			txtGeneroUsuario.setText(usuario.getGenero());
			txtUsuario.setText(usuario.getUsuario());
			txtPassword.setText(usuario.getPassword());
			if(usuario instanceof Medico) {
				Medico medico = (Medico)usuario;
				txtTipoUsuario.setText("Medico");
				lblEspecialidad_Cargo.setText("Especialidad:");
				txtEspecialidad_Cargo.setText(medico.getEspecialidad());
			}else {
				Empleado empleado = (Empleado)usuario;
				txtTipoUsuario.setText("Empleado");
				lblEspecialidad_Cargo.setText("Cargo de usuario:");
				txtEspecialidad_Cargo.setText(empleado.getPuestoLaboral());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la informaci\u00f3n del usuario seleccionado", "Error", JOptionPane.OK_OPTION);			
		}
		
	}
}
