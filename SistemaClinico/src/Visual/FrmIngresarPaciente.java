package Visual;

import java.awt.Image;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JCalendar;

import Logico.Clinica;
import Logico.Paciente;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class FrmIngresarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel pnCabezeraFormulario = new JPanel();
	private int sizeIcon = 35;
	private Image imagenPaciente= new ImageIcon(FrmIngresarPaciente.class.getResource("Imagenes/Paciente2.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenPaciente2= new ImageIcon(FrmIngresarPaciente.class.getResource("Imagenes/Paciente2.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtNacionalidad;
	private JTextField txtAlergia;
	private JTextField txtReligion;
	private JTextField txtProfesion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JComboBox cbxEstadoCivil;
	private JComboBox cbxTipoSangre;
	private JCalendar calNacimiento;
	private JTextField txtCodigoPaciente;
	private JLabel lblImagenPaciente;
	private JLabel lblTituloFormulario;
	private JLabel lblDescripcionFormulario;
	private JLabel FechaFormulario;
	private JPanel pnBotones;
	private JButton btnNuevo;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel pnBody;
	private JPanel pnGeneroPaciente;
	private JPanel pnFechaNacimiento;
	private String genero = "";
	private Date fechaNacimiento;
	private Paciente pacienteModificar;

	public static void main(String[] args) {
		try {
			FrmIngresarPaciente dialog = new FrmIngresarPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmIngresarPaciente(Paciente paciente) {
		pacienteModificar = paciente;
		//opcion:1 -->Ingresar paciente.
		//opcion:2 -->Modificar paciente.
		
		//Controlar boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String mensaje;
				if(pacienteModificar==null) {
					mensaje ="¿Est\u00e1s seguro de que no se debe ingresar el paciente al sistema?";
				}else {
					mensaje ="¿Est\u00e1s seguro de que no deseas modificar el paciente?";
				}
				
				int opcionAux = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcionAux==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				}else {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}		
		});
		
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Paciente");
		setModal(true);
		setResizable(false);
		setIconImage(imagenPaciente);
		setBounds(100, 100, 760, 680);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		pnCabezeraFormulario.setBackground(Color.WHITE);
		pnCabezeraFormulario.setBounds(12, 13, 710, 124);
		pnCabezeraFormulario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(pnCabezeraFormulario);
		pnCabezeraFormulario.setLayout(null);
		{
			lblImagenPaciente = new JLabel("");
			lblImagenPaciente.setBounds(15, 19, 80, 80);
			lblImagenPaciente.setIcon(new ImageIcon(imagenPaciente2));
			pnCabezeraFormulario.add(lblImagenPaciente);
		}
		{
			lblTituloFormulario = new JLabel("Ingreso de Pacientes");
			lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTituloFormulario.setBounds(310, 19, 196, 26);
			pnCabezeraFormulario.add(lblTituloFormulario);
		}
		{
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			
			FechaFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
			FechaFormulario.setBounds(320, 54, 177, 16);
			pnCabezeraFormulario.add(FechaFormulario);
		}
		{
			lblDescripcionFormulario = new JLabel("Formulario para ingresar pacientes al sistema");
			lblDescripcionFormulario.setBounds(259, 83, 299, 16);
			pnCabezeraFormulario.add(lblDescripcionFormulario);
		}
		{
			pnBotones = new JPanel();
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnBotones.setBounds(12, 564, 710, 60);
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				btnNuevo = new JButton("Limpiar");
				btnNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limpiarFormulario();
					}
				});
				btnNuevo.setEnabled(false);
				btnNuevo.setBounds(15, 16, 100, 28);
				btnNuevo.setActionCommand("OK");
				pnBotones.add(btnNuevo);
				getRootPane().setDefaultButton(btnNuevo);
			}
			{
				btnRegistrar = new JButton("Registrar");
				if(pacienteModificar!=null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.setEnabled(false);
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fechaNacimiento = calNacimiento.getDate();
						Paciente paciente = null;
						
						if(pacienteModificar==null) {
							try {
								paciente = new Paciente(txtCodigoPaciente.getText(), txtCedula.getText(), 
										txtNombre.getText(), genero, fechaNacimiento, txtDireccion.getText(), 
										txtTelefono.getText(), txtEmail.getText(), txtAlergia.getText(),
										txtNacionalidad.getText(), cbxEstadoCivil.getSelectedItem().toString(), 
										txtReligion.getText(), cbxTipoSangre.getSelectedItem().toString(), 
										txtProfesion.getText());
								Clinica.getInstance().insertarPaciente(paciente);
								JOptionPane.showMessageDialog(null, "El Paciente se ingreso en el sistema correctamente", "INGRESO DE PACIENTE", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Error al ingresar el paciente" ,"ERROR", JOptionPane.OK_OPTION);
							}
						}else {
							try {
								paciente = pacienteModificar;
								paciente.setCedula(txtCedula.getText());
								paciente.setNombre(txtNombre.getText());
								paciente.setNacionalidad(txtNacionalidad.getText());
								paciente.setTelefono(txtTelefono.getText());
								paciente.setProfesion(txtProfesion.getText());								
								paciente.setEmail(txtEmail.getText());
								paciente.setReligion(txtReligion.getText());
								paciente.setDireccion(txtDireccion.getText());
								paciente.setAlergia(txtAlergia.getText());
								paciente.setTipoSangre(cbxTipoSangre.getSelectedItem().toString());
								paciente.setEstadoCivil(cbxEstadoCivil.getSelectedItem().toString());
								Clinica.getInstance().modificarPaciente(paciente);
								JOptionPane.showMessageDialog(null, "El Paciente se modifico correctamente", "MODIFICACI\u00d3N DE PACIENTE", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
				});
				btnRegistrar.setBounds(305, 16, 100, 28);
				btnRegistrar.setActionCommand("Cancel");
				pnBotones.add(btnRegistrar);
			}
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mensaje;
					if(pacienteModificar==null) {
						mensaje ="¿Est\u00e1s seguro de que no se debe ingresar el paciente al sistema?";
					}else {
						mensaje ="¿Est\u00e1s seguro de que no deseas modificar el paciente?";
					}
					int opcionAux = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
					if(opcionAux==0) {
						dispose();
					}
				}
			});
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setBounds(595, 16, 100, 28);
			pnBotones.add(btnCancelar);
		}
		{
			pnBody = new JPanel();
			pnBody.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnBody.setBounds(12, 150, 710, 398);
			getContentPane().add(pnBody);
			pnBody.setLayout(null);
			{
				JLabel lblCedula = new JLabel("C\u00E9dula del paciente:");
				lblCedula.setBounds(377, 16, 150, 22);
				pnBody.add(lblCedula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setColumns(10);
				txtCedula.setBounds(521, 16, 174, 23);
				pnBody.add(txtCedula);
			}
			{
				JLabel lblNombrecedula = new JLabel("Nombre del paciente:");
				lblNombrecedula.setBounds(15, 50, 141, 22);
				pnBody.add(lblNombrecedula);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(162, 50, 200, 22);
				pnBody.add(txtNombre);
			}
			
			JLabel lblAlergia = new JLabel("Nacionalidad:");
			lblAlergia.setBounds(377, 50, 114, 22);
			pnBody.add(lblAlergia);
			
			txtNacionalidad = new JTextField();
			txtNacionalidad.setColumns(10);
			txtNacionalidad.setBounds(521, 50, 174, 23);
			pnBody.add(txtNacionalidad);
			
			JLabel lblAlergia_1 = new JLabel("Alergia:");
			lblAlergia_1.setBounds(15, 202, 94, 22);
			pnBody.add(lblAlergia_1);
			
			txtAlergia = new JTextField();
			txtAlergia.setColumns(10);
			txtAlergia.setBounds(162, 202, 200, 22);
			pnBody.add(txtAlergia);
			
			JLabel lblTipoDeSangre = new JLabel("Tipo de sangre:");
			lblTipoDeSangre.setBounds(15, 240, 94, 22);
			pnBody.add(lblTipoDeSangre);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarCampos()) {
						btnRegistrar.setEnabled(true);
					}else {
						btnRegistrar.setEnabled(false);
					}
				}
			});
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"}));
			cbxTipoSangre.setBounds(162, 240, 200, 22);
			pnBody.add(cbxTipoSangre);
			
			JLabel lblEstadoCivil = new JLabel("Estado Civil:");
			lblEstadoCivil.setBounds(15, 284, 94, 22);
			pnBody.add(lblEstadoCivil);
			
			cbxEstadoCivil = new JComboBox();
			cbxEstadoCivil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarCampos()) {
						btnRegistrar.setEnabled(true);
					}else {
						btnRegistrar.setEnabled(false);
					}
				}
			});
			cbxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"}));
			cbxEstadoCivil.setBounds(162, 284, 200, 22);
			pnBody.add(cbxEstadoCivil);
			
			JLabel lblReligion = new JLabel("Religi\u00F3n:");
			lblReligion.setBounds(377, 126, 94, 22);
			pnBody.add(lblReligion);
			
			txtReligion = new JTextField();
			txtReligion.setColumns(10);
			txtReligion.setBounds(521, 126, 174, 23);
			pnBody.add(txtReligion);
			
			JLabel lblProfecion = new JLabel("Profesi\u00F3n:");
			lblProfecion.setBounds(377, 88, 94, 22);
			pnBody.add(lblProfecion);
			
			txtProfesion = new JTextField();
			txtProfesion.setColumns(10);
			txtProfesion.setBounds(521, 88, 174, 23);
			pnBody.add(txtProfesion);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(15, 88, 94, 22);
			pnBody.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(162, 88, 200, 22);
			pnBody.add(txtTelefono);
			
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(15, 126, 94, 22);
			pnBody.add(lblEmail);
			
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(162, 126, 200, 22);
			pnBody.add(txtEmail);
			
			JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setBounds(15, 164, 94, 22);
			pnBody.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(162, 164, 533, 23);
			pnBody.add(txtDireccion);
			
			JLabel lblCodigoPaciente = new JLabel("C\u00F3digo del paciente:");
			lblCodigoPaciente.setBounds(15, 16, 150, 22);
			pnBody.add(lblCodigoPaciente);
			
			txtCodigoPaciente = new JTextField();
			txtCodigoPaciente.setEditable(false);
			txtCodigoPaciente.setColumns(10);
			txtCodigoPaciente.setBounds(162, 16, 200, 22);
			pnBody.add(txtCodigoPaciente);
			
			pnGeneroPaciente = new JPanel();
			pnGeneroPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Genero", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnGeneroPaciente.setBounds(15, 320, 347, 59);
			pnBody.add(pnGeneroPaciente);
			pnGeneroPaciente.setLayout(null);
			
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnHombre.isSelected()) {
						rdbtnMujer.setSelected(false);
						genero = "Hombre";
					}else {
						genero = "";
						btnRegistrar.setEnabled(true);
					}

					if(comprobarCampos()) {
						btnRegistrar.setEnabled(true);
					}else {
						btnRegistrar.setEnabled(false);
					}
				}
			});
			rdbtnHombre.setBounds(50, 22, 100, 25);
			pnGeneroPaciente.add(rdbtnHombre);
			
			rdbtnMujer = new JRadioButton("Mujer");
			rdbtnMujer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnMujer.isSelected()) {
						rdbtnHombre.setSelected(false);
						genero = "Mujer";
					}else {
						genero = "";
					}
					if(comprobarCampos()) {
						btnRegistrar.setEnabled(true);
					}else {
						btnRegistrar.setEnabled(false);
					}
				}
			});
			rdbtnMujer.setBounds(190, 22, 73, 25);
			pnGeneroPaciente.add(rdbtnMujer);
			
			pnFechaNacimiento = new JPanel();
			pnFechaNacimiento.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fecha de Nacimiento:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnFechaNacimiento.setBounds(374, 194, 321, 188);
			pnBody.add(pnFechaNacimiento);
			pnFechaNacimiento.setLayout(null);
			
			calNacimiento = new JCalendar();
			calNacimiento.setBorder(new LineBorder(new Color(0, 0, 0)));
			calNacimiento.setBounds(50, 30, 228, 150);
			pnFechaNacimiento.add(calNacimiento);
			
			txtCodigoPaciente.setText("P-"+Clinica.getInstance().getGeneradorCodigoPaciente());
			comprobarCampos(txtCedula);
			comprobarCampos(txtNombre);
			comprobarCampos(txtNacionalidad);
			comprobarCampos(txtTelefono);
			comprobarCampos(txtProfesion);
			comprobarCampos(txtEmail);
			comprobarCampos(txtReligion);
			comprobarCampos(txtDireccion);
			comprobarCampos(txtAlergia);
		}
	}
	
	private void comprobarCampos(JTextField text) {
		text.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void insertUpdate(DocumentEvent e) {
				btnNuevo.setEnabled(true);
				if(comprobarCampos()) {
					btnRegistrar.setEnabled(true);
				}else {
					btnRegistrar.setEnabled(false);
				}
			}			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(comprobarCampos()) {
					btnRegistrar.setEnabled(true);
				}else {
					btnRegistrar.setEnabled(false);
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(comprobarCampos()) {
					btnRegistrar.setEnabled(true);
				}else {
					btnRegistrar.setEnabled(false);
				}
			}
		});		
	}
	

	public boolean comprobarCampos() {
		boolean aux = false;
		if( !(txtCedula.getText().equalsIgnoreCase("")) && !(txtNombre.getText().equalsIgnoreCase(""))
				&& !(txtNacionalidad.getText().equalsIgnoreCase("")) && !(txtTelefono.getText().equalsIgnoreCase(""))
				&& !(txtProfesion.getText().equalsIgnoreCase("")) && !(txtEmail.getText().equalsIgnoreCase("")) 
				&& !(txtReligion.getText().equalsIgnoreCase("")) && !(txtDireccion.getText().equalsIgnoreCase(""))
				&& !(txtAlergia.getText().equalsIgnoreCase("")) && cbxTipoSangre.getSelectedIndex()!=0 
				&& cbxEstadoCivil.getSelectedIndex()!=0 && !(genero.equalsIgnoreCase(""))) {
			aux = true;
		}
		return aux;
	}
	
	public void limpiarFormulario() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtNacionalidad.setText("");
		txtTelefono.setText("");
		txtProfesion.setText("");
		txtEmail.setText("");
		txtReligion.setText("");
		txtDireccion.setText("");
		txtAlergia.setText("");
		cbxTipoSangre.setSelectedIndex(0);
		cbxEstadoCivil.setSelectedIndex(0);
		genero = "";
		rdbtnHombre.setSelected(false);
		rdbtnMujer.setSelected(false);
		btnNuevo.setEnabled(false);
		btnRegistrar.setEnabled(false);
		
	}

}
