package Visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
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

import Logico.Paciente;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class FrmListadoPacientesDetallado extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenListadoPacientes= new ImageIcon(FrmListadoPacientesDetallado.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenListadoPacientes2= new ImageIcon(FrmListadoPacientesDetallado.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoPaciente;
	private JButton btnAceptar;
	private Paciente paciente;
	private JTextField txtNombrePaciente;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtAlergia;
	private JTextField txtReligion;
	private JTextField txtProfesion;
	private JTextField txtNacionalidad;
	private JTextField txtCedulaPaciente;
	private JTextField txtTipoSangre;
	private JTextField txtEstadoCivil;
	private JTextField txtFechaNacimiento;
	private JTextField txtGenero;
	private JTextField txtEdadPaciente;
	private JTextField txtDireccion;
	
	public static void main(String[] args) {
		try {
			FrmListadoPacientesDetallado dialog = new FrmListadoPacientesDetallado(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrmListadoPacientesDetallado(Paciente pacienteSeleccionado) {
		paciente = pacienteSeleccionado;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver la informaci\u00f3n detallada del paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de la vista detallada de la informaci\u00f3n del paciente", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(aux==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setTitle("Informaci\u00F3n detallada del paciente");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 684, 560);
		setIconImage(imagenListadoPacientes);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 15, 648, 100);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(33, 10, 88, 86);
		lblImagen.setIcon(new ImageIcon(imagenListadoPacientes2));
		contentPanel.add(lblImagen);
		{
			JLabel lblTituloFormulario = new JLabel("Informaci\u00F3n del Paciente");
			lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTituloFormulario.setBounds(217, 16, 216, 26);
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
			FechaFormulario.setBounds(245, 45, 158, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblDescripcionFormulario = new JLabel("Informaci\u00F3n del Paciente detallada");
		lblDescripcionFormulario.setBounds(220, 70, 238, 26);
		contentPanel.add(lblDescripcionFormulario);
		
		JPanel pnPacienteDetallado = new JPanel();
		pnPacienteDetallado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnPacienteDetallado.setBounds(12, 130, 648, 374);
		getContentPane().add(pnPacienteDetallado);
		pnPacienteDetallado.setLayout(null);
		
		txtCodigoPaciente = new JTextField();
		txtCodigoPaciente.setEditable(false);
		txtCodigoPaciente.setColumns(10);
		txtCodigoPaciente.setBounds(148, 28, 85, 23);
		pnPacienteDetallado.add(txtCodigoPaciente);
		
		JLabel lblCodigoPaciente = new JLabel("C\u00F3digo del Paciente:");
		lblCodigoPaciente.setBounds(15, 28, 162, 22);
		pnPacienteDetallado.add(lblCodigoPaciente);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver la informaci\u00f3n detallada del paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de la vista detallada de la informaci\u00f3n del paciente", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					FrmListadoPaciente frmAux = new FrmListadoPaciente(1);
					frmAux.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(270, 333, 115, 29);
		pnPacienteDetallado.add(btnAceptar);
		
		JLabel lblNombrePaciente = new JLabel("Nombre del paciente:");
		lblNombrePaciente.setBounds(15, 100, 162, 22);
		pnPacienteDetallado.add(lblNombrePaciente);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setColumns(10);
		txtNombrePaciente.setBounds(148, 101, 181, 22);
		pnPacienteDetallado.add(txtNombrePaciente);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(15, 138, 94, 22);
		pnPacienteDetallado.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(148, 139, 181, 22);
		pnPacienteDetallado.add(txtTelefono);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(15, 176, 94, 22);
		pnPacienteDetallado.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(148, 177, 181, 22);
		pnPacienteDetallado.add(txtEmail);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(15, 214, 94, 22);
		pnPacienteDetallado.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(148, 215, 465, 23);
		pnPacienteDetallado.add(txtDireccion);
		
		JLabel lblAlergia = new JLabel("Alergia:");
		lblAlergia.setBounds(359, 101, 94, 22);
		pnPacienteDetallado.add(lblAlergia);
		
		txtAlergia = new JTextField();
		txtAlergia.setEditable(false);
		txtAlergia.setColumns(10);
		txtAlergia.setBounds(439, 99, 174, 22);
		pnPacienteDetallado.add(txtAlergia);
		
		JLabel lblReligion = new JLabel("Religi\u00F3n:");
		lblReligion.setBounds(359, 63, 94, 22);
		pnPacienteDetallado.add(lblReligion);
		
		txtReligion = new JTextField();
		txtReligion.setEditable(false);
		txtReligion.setColumns(10);
		txtReligion.setBounds(439, 61, 174, 23);
		pnPacienteDetallado.add(txtReligion);
		
		txtProfesion = new JTextField();
		txtProfesion.setEditable(false);
		txtProfesion.setColumns(10);
		txtProfesion.setBounds(439, 175, 174, 23);
		pnPacienteDetallado.add(txtProfesion);
		
		JLabel lblProfesion = new JLabel("Profesi\u00F3n:");
		lblProfesion.setBounds(359, 177, 94, 22);
		pnPacienteDetallado.add(lblProfesion);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setBounds(359, 139, 114, 22);
		pnPacienteDetallado.add(lblNacionalidad);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(439, 137, 174, 23);
		pnPacienteDetallado.add(txtNacionalidad);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setEditable(false);
		txtCedulaPaciente.setColumns(10);
		txtCedulaPaciente.setBounds(148, 63, 181, 23);
		pnPacienteDetallado.add(txtCedulaPaciente);
		
		JLabel lblCedulaPaciente = new JLabel("C\u00E9dula del paciente:");
		lblCedulaPaciente.setBounds(15, 62, 150, 22);
		pnPacienteDetallado.add(lblCedulaPaciente);
		
		JLabel bllTipoSangre = new JLabel("Tipo de sangre:");
		bllTipoSangre.setBounds(15, 252, 94, 22);
		pnPacienteDetallado.add(bllTipoSangre);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(359, 252, 94, 22);
		pnPacienteDetallado.add(lblEstadoCivil);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaNacimiento.setBounds(15, 297, 150, 22);
		pnPacienteDetallado.add(lblFechaNacimiento);
		
		txtTipoSangre = new JTextField();
		txtTipoSangre.setEditable(false);
		txtTipoSangre.setColumns(10);
		txtTipoSangre.setBounds(148, 252, 181, 22);
		pnPacienteDetallado.add(txtTipoSangre);
		
		txtEstadoCivil = new JTextField();
		txtEstadoCivil.setEditable(false);
		txtEstadoCivil.setColumns(10);
		txtEstadoCivil.setBounds(439, 252, 174, 23);
		pnPacienteDetallado.add(txtEstadoCivil);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setEditable(false);
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(148, 295, 181, 22);
		pnPacienteDetallado.add(txtFechaNacimiento);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(359, 292, 94, 22);
		pnPacienteDetallado.add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setEditable(false);
		txtGenero.setColumns(10);
		txtGenero.setBounds(439, 290, 174, 22);
		pnPacienteDetallado.add(txtGenero);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(359, 29, 65, 22);
		pnPacienteDetallado.add(lblEdad);
		
		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setEditable(false);
		txtEdadPaciente.setColumns(10);
		txtEdadPaciente.setBounds(439, 27, 174, 22);
		pnPacienteDetallado.add(txtEdadPaciente);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		try {
			txtCodigoPaciente.setText(paciente.getCodigoPaciente());
			txtNombrePaciente.setText(paciente.getNombre());
			txtCedulaPaciente.setText(paciente.getCedula());
			txtReligion.setText(paciente.getReligion());
			txtAlergia.setText(paciente.getAlergia());
			txtTelefono.setText(paciente.getTelefono());
			txtNacionalidad.setText(paciente.getNacionalidad());
			txtEmail.setText(paciente.getEmail());
			txtProfesion.setText(paciente.getProfesion());
			txtDireccion.setText(paciente.getDireccion());
			txtTipoSangre.setText(paciente.getTipoSangre());
			txtEstadoCivil.setText(paciente.getEstadoCivil());
			txtFechaNacimiento.setText(new SimpleDateFormat("dd-MM-yyyy").format(paciente.getDiaNacimiento()));
			txtGenero.setText(paciente.getGenero());			
			LocalDate fechaNacimiento = paciente.getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period periodo = Period.between(fechaNacimiento, LocalDate.now());
			int edad = periodo.getYears();
			txtEdadPaciente.setText(""+edad);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la informaci\u00f3n del paciente seleccionado", "Error", JOptionPane.OK_OPTION);			
			dispose();
			//FrmListadoPaciente frmAux = new FrmListadoPaciente(1);
			//frmAux.setVisible(true);
		}
		
	}
}
