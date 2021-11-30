package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Logico.Clinica;
import Logico.Empleado;
import Logico.Medico;
import Logico.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmIngresarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenUsuario = new ImageIcon(FrmIngresarUsuario.class.getResource("Imagenes/NuevoUsuario.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenUsuario2= new ImageIcon(FrmIngresarUsuario.class.getResource("Imagenes/NuevoUsuario.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JButton btnIngresar;
	private JTextField txtCodigoUsuario;
	private JTextField txtCedulaUsuario;
	private JButton btnNuevo;
	private JTextField txtIdUsuario;
	private JTextField txtNombreUsuario;
	private JTextField txtTelefonoUsuario;
	private JTextField txtDireccionUsuario;
	private JTextField txtEmailUsuario;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JTextField txtPasswordConfirmar;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JPanel pnCredenciales;
	private JPanel pnInformacionPersonal;
	private JTextField txtTipoUsuario;
	private JLabel lblTipoUsuario;
	private JRadioButton rdbtnEmpleado;
	private JRadioButton rdbtnMedico;
	private JButton btnComprobar;
	private JPanel pnTipoUsuario;
	private JButton btnSiguiente;
	private JButton btnAtras;
	
	private String genero = "";
	private String tipoUsuario = "";
	private JComboBox cbxTipoEmpleado;
	private JLabel lblTituloFormulario;
	private JLabel lblDescripcionFormulario;
	
	private Usuario usuarioModificar = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmIngresarUsuario dialog = new FrmIngresarUsuario(1, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FrmIngresarUsuario(int opcion, Usuario usuario) {
		//Para controlar el boton de close.
		usuarioModificar = usuario;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(opcion==1) {
					int opcionAux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar el usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(opcionAux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo de ingresar usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(opcionAux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}else{
					int opcionAux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea modificar el usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(opcionAux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo de modificar usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
						FrmListadoUsuarios frmAux = new FrmListadoUsuarios(opcion,"");
						frmAux.setVisible(true);
					}else if(opcionAux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}
			}		
		});
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Ingreso de Usuarios");
		if(opcion!=1) {
			setTitle("Modificaci\u00f3n de Usuarios");
		}
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 620, 600);
		setIconImage(imagenUsuario);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(12, 13, 576, 110);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnEncabezado = new JPanel();
		pnEncabezado.setBackground(Color.WHITE);
		contentPanel.add(pnEncabezado, BorderLayout.CENTER);
		pnEncabezado.setLayout(null);
		
		JLabel lblImagenEnfermedad = new JLabel("");
		lblImagenEnfermedad.setBounds(15, 10, 80, 80);
		lblImagenEnfermedad.setIcon(new ImageIcon(imagenUsuario2));
		pnEncabezado.add(lblImagenEnfermedad);
		
		lblTituloFormulario = new JLabel("Ingreso de Usuarios");
		lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTituloFormulario.setBounds(229, 10, 162, 26);
		pnEncabezado.add(lblTituloFormulario);
		
		lblDescripcionFormulario = new JLabel("Formulario para ingresar usuarios al sistema");
		lblDescripcionFormulario.setBounds(180, 74, 320, 16);
		pnEncabezado.add(lblDescripcionFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();
		
		JLabel lblFechaFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaFormulario.setBounds(230, 42, 216, 16);
		pnEncabezado.add(lblFechaFormulario);
		{
			JPanel pnBotones = new JPanel();
			pnBotones.setBounds(12, 478, 576, 62);
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				btnIngresar = new JButton("Ingresar");
				btnIngresar.setEnabled(false);
				btnIngresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(opcion==1) {
							Usuario usuarioAux = null;
							if(rdbtnEmpleado.isSelected()) {
								usuarioAux = new Empleado(txtCodigoUsuario.getText(), txtCedulaUsuario.getText(),
										Integer.valueOf(txtIdUsuario.getText()), txtUsuario.getText(), txtPassword.getText(), 
										txtNombreUsuario.getText(), txtTelefonoUsuario.getText(), txtDireccionUsuario.getText(), 
										txtEmailUsuario.getText(), genero, cbxTipoEmpleado.getSelectedItem().toString());
							}else if(rdbtnMedico.isSelected()) {
								usuarioAux = new Medico(txtCodigoUsuario.getText(), txtCedulaUsuario.getText(),
										Integer.valueOf(txtIdUsuario.getText()), txtUsuario.getText(), txtPassword.getText(), 
										txtNombreUsuario.getText(), txtTelefonoUsuario.getText(), txtDireccionUsuario.getText(), 
										txtEmailUsuario.getText(), genero, txtTipoUsuario.getText());
							}
							try {
								Clinica.getInstance().insertarUsuario(usuarioAux);
								JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente", "INGRESO DE USUARIO", JOptionPane.INFORMATION_MESSAGE);
								limpiarFormulario();
							} catch (NullPointerException exception) {
								JOptionPane.showMessageDialog(null, "No se pudo ingresar el usuario", "ERROR AL INGRESAR EL USUARIO", JOptionPane.OK_OPTION);
								limpiarFormulario();
							}
						}else {
							if(opcion==2) {
								usuarioModificar.setNombre(txtNombreUsuario.getText());
								usuarioModificar.setCedulaUsuario(txtCedulaUsuario.getText());
								usuarioModificar.setTelefono(txtTelefonoUsuario.getText());
								usuarioModificar.setDireccion(txtDireccionUsuario.getText());
								usuarioModificar.setEmail(txtEmailUsuario.getText());
								usuarioModificar.setGenero(genero);
							}else if(opcion==3) {
								usuarioModificar.setUsuario(txtUsuario.getText());
								usuarioModificar.setPassword(txtPassword.getText());
							}
							try {
								Clinica.getInstance().modificarUsuario(usuarioModificar);
								JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "MODIFICACI\u00d3N DE USUARIO", JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							dispose();
							FrmListadoUsuarios frmAux = new FrmListadoUsuarios(opcion,"");
							frmAux.setVisible(true);
							
						}
					}
				});
				btnIngresar.setBounds(467, 14, 94, 30);
				btnIngresar.setActionCommand("OK");
				pnBotones.add(btnIngresar);
				getRootPane().setDefaultButton(btnIngresar);
			}
			
			btnNuevo = new JButton("Nuevo");
			btnNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarFormulario();
				}
			});
			btnNuevo.setEnabled(false);
			btnNuevo.setBounds(15, 14, 94, 30);
			pnBotones.add(btnNuevo);
			
			btnAtras = new JButton("Atras");
			btnAtras.setEnabled(false);btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnInformacionPersonal.setVisible(true);
					pnCredenciales.setVisible(false);
					pnTipoUsuario.setVisible(false);
					btnAtras.setEnabled(false);
					btnSiguiente.setEnabled(true);
					cambiarPagina(2);
				}
			});
			btnAtras.setBounds(161, 14, 94, 30);
			pnBotones.add(btnAtras);
			
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarPagina(1);
				}
			});
			btnSiguiente.setBounds(310, 14, 110, 30);
			pnBotones.add(btnSiguiente);
		}
		
		JPanel pnCampos = new JPanel();
		pnCampos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnCampos.setBounds(12, 139, 576, 323);
		getContentPane().add(pnCampos);
		pnCampos.setLayout(null);
		
		pnTipoUsuario = new JPanel();
		pnTipoUsuario.setVisible(false);
		
		pnCredenciales = new JPanel();
		pnCredenciales.setVisible(false);
		pnCredenciales.setBounds(15, 45, 546, 143);
		pnCampos.add(pnCredenciales);
		pnCredenciales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Credenciales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCredenciales.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(15, 34, 68, 22);
		pnCredenciales.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(15, 72, 85, 22);
		pnCredenciales.add(lblPassword);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(150, 34, 234, 23);
		comprobarCampos(txtUsuario, 2);
		pnCredenciales.add(txtUsuario);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean correcto = true;

				if(opcion==1) {
					if( !(Clinica.getInstance().usuarioRepetido(txtUsuario.getText()))) {
						JOptionPane.showMessageDialog(null, "El usuario ya existe!", "Usuario Existente", JOptionPane.OK_OPTION);
						correcto = false;
					}
					if( !(txtPassword.getText().equalsIgnoreCase(txtPasswordConfirmar.getText()))) {
						JOptionPane.showMessageDialog(null, "Las contrase\u00f1as no coinciden!", "Contrase\u00f1as Diferentes", JOptionPane.OK_OPTION);
						correcto =false;
					}
					if(correcto) {
						JOptionPane.showMessageDialog(null, "Las credenciales ingresadas son correctas!", "Credenciales correctas", JOptionPane.INFORMATION_MESSAGE);
						txtUsuario.setEnabled(false);
						txtPassword.setEnabled(false);
						txtPasswordConfirmar.setEnabled(false);
						btnComprobar.setEnabled(false);
						rdbtnEmpleado.setEnabled(true);
						rdbtnMedico.setEnabled(true);
						if(opcion==3) {
							btnIngresar.setEnabled(true);
						}
					}
				}else {
					if( !(txtPassword.getText().equalsIgnoreCase(txtPasswordConfirmar.getText()))) {
						JOptionPane.showMessageDialog(null, "Las contrase\u00f1as no coinciden!", "Contrase\u00f1as Diferentes", JOptionPane.OK_OPTION);
						correcto =false;
					}
					if(correcto) {
						btnIngresar.setEnabled(true);
					}else {
						btnIngresar.setEnabled(false);
					}
				}
			}
		});
		btnComprobar.setEnabled(false);
		btnComprobar.setActionCommand("OK");
		btnComprobar.setBounds(399, 62, 118, 43);
		pnCredenciales.add(btnComprobar);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(150, 71, 234, 23);
		comprobarCampos(txtPassword, 2);
		pnCredenciales.add(txtPassword);
		
		txtPasswordConfirmar = new JPasswordField();
		txtPasswordConfirmar.setColumns(10);
		txtPasswordConfirmar.setBounds(150, 109, 234, 23);
		comprobarCampos(txtPasswordConfirmar, 2);
		pnCredenciales.add(txtPasswordConfirmar);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a:");
		lblConfirmarContrasea.setBounds(15, 110, 161, 22);
		pnCredenciales.add(lblConfirmarContrasea);
		pnTipoUsuario.setBounds(15, 190, 546, 110);
		pnCampos.add(pnTipoUsuario);
		pnTipoUsuario.setBorder(new TitledBorder(null, "Tipo de usuario", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnTipoUsuario.setLayout(null);
		
		cbxTipoEmpleado = new JComboBox();
		cbxTipoEmpleado.setVisible(false);
		cbxTipoEmpleado.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Secretaria"}));
		cbxTipoEmpleado.setBounds(188, 72, 309, 23);
		pnTipoUsuario.add(cbxTipoEmpleado);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setBounds(15, 31, 162, 22);
		pnTipoUsuario.add(lblTipoDeUsuario);
		
		rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEmpleado.isSelected()) {
					tipoUsuario = "Empleado";
					rdbtnMedico.setSelected(false);
					lblTipoUsuario.setText("Puesto Laboral:");
					lblTipoUsuario.setVisible(true);
					txtTipoUsuario.setVisible(false);
					cbxTipoEmpleado.setVisible(true);
					btnIngresar.setEnabled(true);
				}else {
					tipoUsuario = "";
					lblTipoUsuario.setVisible(false);
					cbxTipoEmpleado.setVisible(false);
					btnIngresar.setEnabled(false);
				}
			}
		});
		rdbtnEmpleado.setEnabled(false);
		rdbtnEmpleado.setBounds(188, 31, 102, 23);
		pnTipoUsuario.add(rdbtnEmpleado);
		
		rdbtnMedico = new JRadioButton("Medico");
		rdbtnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnMedico.isSelected()) {
					tipoUsuario = "Medico";
					rdbtnEmpleado.setSelected(false);
					lblTipoUsuario.setText("Especialidad:");
					lblTipoUsuario.setVisible(true);
					txtTipoUsuario.setVisible(true);
					cbxTipoEmpleado.setVisible(false);
					if(!txtTipoUsuario.getText().equalsIgnoreCase("")) {
						btnIngresar.setEnabled(true);	
					}else {
						btnIngresar.setEnabled(false);	
					}
				}else {
					tipoUsuario = "";
					lblTipoUsuario.setVisible(false);
					txtTipoUsuario.setVisible(false);
					btnIngresar.setEnabled(false);
				}
			}
		});
		rdbtnMedico.setEnabled(false);
		rdbtnMedico.setBounds(416, 31, 81, 23);
		pnTipoUsuario.add(rdbtnMedico);
		
		lblTipoUsuario = new JLabel("Especialidad:");
		lblTipoUsuario.setVisible(false);
		lblTipoUsuario.setBounds(15, 72, 152, 22);
		pnTipoUsuario.add(lblTipoUsuario);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setVisible(false);
		txtTipoUsuario.setColumns(10);
		txtTipoUsuario.setBounds(188, 72, 309, 23);
		txtTipoUsuario.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txtTipoUsuario.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txtTipoUsuario.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}			
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txtTipoUsuario.getText().equalsIgnoreCase("")) {
					btnIngresar.setEnabled(true);
				}else {
					btnIngresar.setEnabled(false);
				}			
			}
		});
		pnTipoUsuario.add(txtTipoUsuario);
		
		pnInformacionPersonal = new JPanel();
		pnInformacionPersonal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n Personal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnInformacionPersonal.setBounds(15, 45, 546, 265);
		pnCampos.add(pnInformacionPersonal);
		pnInformacionPersonal.setLayout(null);
		
		JLabel lblCedulaUsuario = new JLabel("C\u00E9dula del usuario:");
		lblCedulaUsuario.setBounds(15, 28, 162, 22);
		pnInformacionPersonal.add(lblCedulaUsuario);
		
		txtCedulaUsuario = new JTextField();
		txtCedulaUsuario.setBounds(168, 28, 363, 23);
		comprobarCampos(txtCedulaUsuario, 1);
		pnInformacionPersonal.add(txtCedulaUsuario);
		txtCedulaUsuario.setColumns(10);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(168, 67, 363, 23);
		comprobarCampos(txtNombreUsuario, 1);
		pnInformacionPersonal.add(txtNombreUsuario);
		
		JLabel lblNombreUsuario = new JLabel("Nombre del usuario:");
		lblNombreUsuario.setBounds(15, 67, 162, 22);
		pnInformacionPersonal.add(lblNombreUsuario);
		
		JLabel lblTelefonoUsuario = new JLabel("Telefono del usuario:");
		lblTelefonoUsuario.setBounds(15, 106, 162, 22);
		pnInformacionPersonal.add(lblTelefonoUsuario);
		
		txtTelefonoUsuario = new JTextField();
		txtTelefonoUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isDigit(c))&&(c!='-'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtTelefonoUsuario.setColumns(10);
		txtTelefonoUsuario.setBounds(168, 106, 363, 23);
		comprobarCampos(txtTelefonoUsuario, 1);
		pnInformacionPersonal.add(txtTelefonoUsuario);
		
		JLabel lblDireccionUsuario = new JLabel("Direcci\u00F3n del usuario:");
		lblDireccionUsuario.setBounds(15, 144, 162, 22);
		pnInformacionPersonal.add(lblDireccionUsuario);
		
		txtDireccionUsuario = new JTextField();
		txtDireccionUsuario.setColumns(10);
		comprobarCampos(txtDireccionUsuario, 1);
		txtDireccionUsuario.setBounds(168, 144, 363, 23);
		pnInformacionPersonal.add(txtDireccionUsuario);
		
		JLabel lblGeneroUsuario = new JLabel("Genero del usuario:");
		lblGeneroUsuario.setBounds(15, 217, 162, 22);
		pnInformacionPersonal.add(lblGeneroUsuario);
		
		JLabel lblEmailUsuario = new JLabel("Email del usuario:");
		lblEmailUsuario.setBounds(15, 182, 162, 22);
		pnInformacionPersonal.add(lblEmailUsuario);
		
		txtEmailUsuario = new JTextField();
		txtEmailUsuario.setColumns(10);
		txtEmailUsuario.setBounds(168, 182, 363, 23);
		pnInformacionPersonal.add(txtEmailUsuario);
		
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnHombre.isSelected()) {
					genero = "Hombre";
					rdbtnMujer.setSelected(false);
				}else {
					genero="";
				}
				if(camposLlenos(1)) {
					btnSiguiente.setEnabled(true);
				}else {
					btnSiguiente.setEnabled(false);
				}
			}
		});
		rdbtnHombre.setBounds(248, 217, 102, 23);
		pnInformacionPersonal.add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnMujer.isSelected()) {
					genero = "Mujer";
					rdbtnHombre.setSelected(false);				
				}else {
					genero = "";
				}
				if(camposLlenos(1)) {
					btnSiguiente.setEnabled(true);
				}else {
					btnSiguiente.setEnabled(false);
				}
			}
		});
		rdbtnMujer.setBounds(397, 217, 102, 23);
		pnInformacionPersonal.add(rdbtnMujer);
		
		JLabel lblCodigoUsuario = new JLabel("C\u00F3digo del usuario:");
		lblCodigoUsuario.setBounds(15, 13, 162, 22);
		pnCampos.add(lblCodigoUsuario);
		
		txtCodigoUsuario = new JTextField();
		txtCodigoUsuario.setEditable(false);
		txtCodigoUsuario.setBounds(149, 12, 127, 23);
		pnCampos.add(txtCodigoUsuario);
		txtCodigoUsuario.setColumns(10);
		
		txtIdUsuario = new JTextField();
		txtIdUsuario.setEditable(false);
		txtIdUsuario.setColumns(10);
		txtIdUsuario.setBounds(434, 12, 127, 23);
		pnCampos.add(txtIdUsuario);
		
		JLabel lblIdUsuario = new JLabel("ID del usuario:");
		lblIdUsuario.setBounds(318, 13, 121, 22);
		pnCampos.add(lblIdUsuario);
		
		if(opcion!=1){
			modificarUsuario(opcion);
		}else {
			txtCodigoUsuario.setText("U"+Clinica.getInstance().getGeneradorCodigoUsuario());
			txtIdUsuario.setText(""+Clinica.getInstance().getGeneradorCodigoUsuario());
		}
	}
	
	public void modificarUsuario(int opcion) {
		btnAtras.setVisible(false);
		btnSiguiente.setVisible(false);
		btnNuevo.setVisible(false);
		pnTipoUsuario.setVisible(false);
		lblTituloFormulario.setText("Modificaci\u00f3n de Usuarios");
		lblDescripcionFormulario.setText("Formulario para modificar usuarios en el sistema");
		btnIngresar.setText("Modificar");
		txtUsuario.setEditable(false);
		
		if(opcion==2) {
			pnInformacionPersonal.setVisible(true);
			pnCredenciales.setVisible(false);
			try {
				txtCodigoUsuario.setText(usuarioModificar.getCodigoUsuario());
				txtIdUsuario.setText(""+usuarioModificar.getIdUsuario());
				txtNombreUsuario.setText(usuarioModificar.getNombre());
				txtCedulaUsuario.setText(usuarioModificar.getCedulaUsuario());
				txtTelefonoUsuario.setText(usuarioModificar.getTelefono());
				txtDireccionUsuario.setText(usuarioModificar.getDireccion());
				txtEmailUsuario.setText(usuarioModificar.getEmail());
				if(usuarioModificar.getGenero().equalsIgnoreCase("Hombre")) {
					rdbtnHombre.setSelected(true);
					genero="Hombre";

				}else {
					rdbtnMujer.setSelected(true);
					genero="Mujer";
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se puedo cargar la informaci\u00f3n del usuario", "ERROR", JOptionPane.OK_OPTION);
			
			}

		}
		if(opcion==3) {
			pnInformacionPersonal.setVisible(false);
			pnCredenciales.setVisible(true);
			pnCredenciales.setBounds(15, 100, 546, 143);
			try {
				txtCodigoUsuario.setText(usuarioModificar.getCodigoUsuario());
				txtIdUsuario.setText(""+usuarioModificar.getIdUsuario());
				txtUsuario.setText(usuarioModificar.getUsuario());
				txtPassword.setText(usuarioModificar.getPassword());
				txtPasswordConfirmar.setText(usuarioModificar.getPassword());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se puedo cargar la informaci\u00f3n del usuario", "ERROR", JOptionPane.OK_OPTION);
			}
		}
	}

	private void comprobarCampos(JTextField text, int opcion) {
		text.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(usuarioModificar==null) {
					if(opcion==1) {					
						if(camposLlenos(1)) {
							btnSiguiente.setEnabled(true);
						}else {
							btnSiguiente.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}else {
					if(opcion==1) {
						if(camposLlenos(1)) {
							btnIngresar.setEnabled(true);
						}else {
							btnIngresar.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}
			}			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(usuarioModificar==null) {
					if(opcion==1) {					
						if(camposLlenos(1)) {
							btnSiguiente.setEnabled(true);
						}else {
							btnSiguiente.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}else {
					if(opcion==1) {
						if(camposLlenos(1)) {
							btnIngresar.setEnabled(true);
						}else {
							btnIngresar.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}	
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(usuarioModificar==null) {
					if(opcion==1) {					
						if(camposLlenos(1)) {
							btnSiguiente.setEnabled(true);
						}else {
							btnSiguiente.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}else {
					if(opcion==1) {
						if(camposLlenos(1)) {
							btnIngresar.setEnabled(true);
						}else {
							btnIngresar.setEnabled(false);
						}
					}else if(opcion==2) {
						if(camposLlenos(2)) {
							btnComprobar.setEnabled(true);
						}else {
							btnComprobar.setEnabled(false);
						}
					}
				}	
			}
		});
		
	}

	public boolean camposLlenos(int opcion) {
		boolean aux = false;
		if(opcion==1) {	
			if( !(txtCedulaUsuario.getText().equalsIgnoreCase("")) && !(txtNombreUsuario.getText().equalsIgnoreCase("")) 
					&& !(txtTelefonoUsuario.getText().equalsIgnoreCase("")) && !(txtDireccionUsuario.getText().equalsIgnoreCase("")) 
					&& !(genero.equals(""))) {
				aux = true;
			}		
		}else if(opcion==2) {
			if( !(txtUsuario.getText().equalsIgnoreCase("")) && !(txtPassword.getText().equalsIgnoreCase("")) 
					&& !(txtPasswordConfirmar.getText().equalsIgnoreCase(""))) {
				aux = true;
			}		
		}
		
		return aux;
	}
	


	private void cambiarPagina(int opcion) {
		if(opcion==1) {
			pnInformacionPersonal.setVisible(false);
			pnCredenciales.setVisible(true);
			pnTipoUsuario.setVisible(true);
			btnSiguiente.setEnabled(false);
			btnAtras.setEnabled(true);
			btnNuevo.setEnabled(true);
		}else if(opcion==2) {
			pnInformacionPersonal.setVisible(true);
			pnCredenciales.setVisible(false);
			pnTipoUsuario.setVisible(false);
			btnAtras.setEnabled(false);
			btnSiguiente.setEnabled(true);
			btnNuevo.setEnabled(false);
		}
		
	}
	
	public void limpiarFormulario(){
		genero = "";
		tipoUsuario = "";
		pnCredenciales.setVisible(false);
		pnTipoUsuario.setVisible(false);
		pnInformacionPersonal.setVisible(true);
		txtCodigoUsuario.setText("U"+Clinica.getInstance().getGeneradorCodigoUsuario());
		txtIdUsuario.setText(""+Clinica.getInstance().getGeneradorCodigoUsuario());
		txtNombreUsuario.setText("");
		txtCedulaUsuario.setText("");
		txtTelefonoUsuario.setText("");
		txtDireccionUsuario.setText("");
		txtEmailUsuario.setText("");
		rdbtnHombre.setSelected(false);
		rdbtnMujer.setSelected(false);
		txtUsuario.setText("");
		txtUsuario.setEnabled(true);
		txtPassword.setText("");
		txtPassword.setEnabled(true);
		txtPasswordConfirmar.setText("");
		txtPasswordConfirmar.setEnabled(true);
		rdbtnEmpleado.setSelected(false);
		rdbtnEmpleado.setEnabled(false);
		rdbtnMedico.setSelected(false);
		rdbtnMedico.setEnabled(false);
		lblTipoUsuario.setVisible(false);
		txtTipoUsuario.setText("");
		txtTipoUsuario.setVisible(false);
		cbxTipoEmpleado.setVisible(false);
		cbxTipoEmpleado.setSelectedIndex(0);
		btnNuevo.setEnabled(false);
		btnAtras.setEnabled(false);
		btnSiguiente.setEnabled(false);
		btnIngresar.setEnabled(false);		
	}
}
