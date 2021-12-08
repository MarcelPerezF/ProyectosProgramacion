package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Consulta;
import Logico.Enfermedad;
import Logico.Medico;
import Logico.Paciente;
import Logico.Usuario;
import Logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private int sizeIcon2 = 18;
	private Image imagenConsulta= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Consulta.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenConsulta2= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Consulta.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Image imagenConsulta3= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Informacion2.png")).getImage().getScaledInstance(sizeIcon2, sizeIcon2, Image.SCALE_SMOOTH);
	private Image imagenConsulta4= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Historial.png")).getImage().getScaledInstance(sizeIcon2, sizeIcon2, Image.SCALE_SMOOTH);
	private Image imagenConsulta5= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Diagnostico.png")).getImage().getScaledInstance(sizeIcon2, sizeIcon2, Image.SCALE_SMOOTH);
	private Image imagenPersona= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Persona.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	public static Enfermedad enfermedad;
	public static Paciente paciente;
	public static ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
	private JButton btnDiagnosticar;
	private JButton btnGuardar;
	private JTextField txtCodigoConsulta;
	private JTextField txtNombrePaciente3;
	private JTextField txtEnfermedad;
	private JEditorPane txtSintomas;
	private JEditorPane txtDiagnostico;
	private JTabbedPane tbpTabs;
	private JPanel pnlInformacion;
	private JPanel pnlHistorial;
	private JPanel pnlDiagnosticar;
	private JTextField txtCodigoPaciente;
	private JTextField txtNombrePaciente1;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtAlergia;
	private JTextField txtReligion;
	private JTextField txtProfesion;
	private JTextField txtNacionalidad;
	private JTextField txtCedulaPaciente;
	private JTextField txtTipoSangre;
	private JTextField txtEstadoCivil;
	private JTextField txtGenero;
	private JTextField txtNacimiento;
	private JTextField txtEdad;
	private Paciente auxPaciente;
	private CitaMedica auxCita;
	private JButton btnRegistrar;
	private JTextField txtNombrePaciente2;
	private JTable tblConsultas;
	private JTable tblVacunas;
	
	private static DefaultTableModel modeloConsultas;
	private static DefaultTableModel modeloVacunas;
	private static Object[] rowConsultas;
	private static Object[] rowVacunas;
	private JCheckBox chHistorial;
	/**
	 * Create the dialog.
	 */
	public FrmConsulta(Paciente pacien, Usuario medico, CitaMedica cita) {
		//Para controlar el boton de close.
		enfermedad = null;
		auxPaciente=pacien;
		auxCita=cita;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea guardar la consulta con el paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de consultas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		setModal(true);
		setTitle("Consultas");
		setBounds(100, 100, 686, 788);
		setLocationRelativeTo(null);
		setIconImage(imagenConsulta);
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new LineBorder(new Color(0,0,0), 1));
		panelHeader.setBackground(UIManager.getColor("Button.light"));
		panelHeader.setBounds(10, 10, 638, 113);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblImagenCitas = new JLabel("");
		lblImagenCitas.setBounds(25, 19, 80, 80);
		lblImagenCitas.setIcon(new ImageIcon(imagenConsulta2));
		panelHeader.add(lblImagenCitas);
		
		JLabel lblTituloFormulario = new JLabel("INGRESO DE CONSULTAS MEDICAS");
		lblTituloFormulario.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloFormulario.setBounds(188, 19, 293, 20);
		panelHeader.add(lblTituloFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();

		JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaActualFormulario.setBounds(241, 47, 187, 25);
		panelHeader.add(lblFechaActualFormulario);
		
		JLabel lblDescripcionFormulario = new JLabel("Formulario para consultas de los pacientes");
		lblDescripcionFormulario.setBounds(184, 79, 300, 20);
		panelHeader.add(lblDescripcionFormulario);;
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(227, 665, 226, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta consulta = new Consulta(txtCodigoConsulta.getText(), txtSintomas.getText(), txtDiagnostico.getText(),
						enfermedad, (Medico)medico);
				try {
					Clinica.getInstance().ingresarConsultaPaciente(paciente, consulta, cita);
					for(Vacuna v1:vacunas) {
						Clinica.getInstance().ingresarVacunaPacienteHistorial(paciente, v1);
					}
					if(chHistorial.isSelected()) {
						Clinica.getInstance().ingresarConsultaPacienteHistorial(paciente, consulta);
					}
					JOptionPane.showMessageDialog(null, "Se ha ingresado de manera correcta la consulta", "INGRESO DE CONSULTA", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setBounds(42, 16, 142, 29);
		panelFooter.add(btnGuardar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(12, 136, 638, 516);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tbpTabs = new JTabbedPane(JTabbedPane.TOP);
		tbpTabs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbpTabs.getSelectedIndex()==2) {
					btnGuardar.setEnabled(true);
				}else {
					btnGuardar.setEnabled(false);
				}
			}
		});
		tbpTabs.setEnabled(false);
		panel.add(tbpTabs, BorderLayout.CENTER);
		
		pnlInformacion = new JPanel();
		tbpTabs.addTab("Informacion", new ImageIcon(imagenConsulta3), pnlInformacion, null);
		pnlInformacion.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 13, 607, 456);
		pnlInformacion.add(panel_1);
		
		txtCodigoPaciente = new JTextField();
		txtCodigoPaciente.setText((String) null);
		txtCodigoPaciente.setEditable(false);
		txtCodigoPaciente.setColumns(10);
		txtCodigoPaciente.setBounds(148, 28, 114, 23);
		panel_1.add(txtCodigoPaciente);
		
		JLabel label_5 = new JLabel("C\u00F3digo del Paciente:");
		label_5.setBounds(15, 28, 162, 22);
		panel_1.add(label_5);
		
		JLabel txtNombre1 = new JLabel("Nombre del paciente:");
		txtNombre1.setBounds(15, 100, 162, 22);
		panel_1.add(txtNombre1);
		
		txtNombrePaciente1 = new JTextField();
		txtNombrePaciente1.setText((String) null);
		txtNombrePaciente1.setEditable(false);
		txtNombrePaciente1.setColumns(10);
		txtNombrePaciente1.setBounds(148, 101, 181, 22);
		panel_1.add(txtNombrePaciente1);
		
		JLabel label_7 = new JLabel("Telefono:");
		label_7.setBounds(341, 275, 94, 22);
		panel_1.add(label_7);
		
		txtTelefono = new JTextField();
		txtTelefono.setText((String) null);
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(414, 275, 181, 22);
		panel_1.add(txtTelefono);
		
		JLabel label_8 = new JLabel("Email:");
		label_8.setBounds(15, 386, 94, 22);
		panel_1.add(label_8);
		
		txtEmail = new JTextField();
		txtEmail.setText((String) null);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(148, 386, 447, 22);
		panel_1.add(txtEmail);
		
		JLabel label_9 = new JLabel("Direcci\u00F3n:");
		label_9.setBounds(15, 421, 94, 22);
		panel_1.add(label_9);
		
		txtDireccion = new JTextField();
		txtDireccion.setText((String) null);
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(148, 421, 447, 23);
		panel_1.add(txtDireccion);
		
		JLabel label_10 = new JLabel("Alergia:");
		label_10.setBounds(15, 135, 94, 22);
		panel_1.add(label_10);
		
		txtAlergia = new JTextField();
		txtAlergia.setText((String) null);
		txtAlergia.setEditable(false);
		txtAlergia.setColumns(10);
		txtAlergia.setBounds(148, 135, 181, 22);
		panel_1.add(txtAlergia);
		
		JLabel label_11 = new JLabel("Religi\u00F3n:");
		label_11.setBounds(15, 205, 94, 22);
		panel_1.add(label_11);
		
		txtReligion = new JTextField();
		txtReligion.setText((String) null);
		txtReligion.setEditable(false);
		txtReligion.setColumns(10);
		txtReligion.setBounds(148, 205, 181, 23);
		panel_1.add(txtReligion);
		
		txtProfesion = new JTextField();
		txtProfesion.setText((String) null);
		txtProfesion.setEditable(false);
		txtProfesion.setColumns(10);
		txtProfesion.setBounds(148, 310, 181, 23);
		panel_1.add(txtProfesion);
		
		JLabel label_12 = new JLabel("Profesi\u00F3n:");
		label_12.setBounds(15, 310, 94, 22);
		panel_1.add(label_12);
		
		JLabel label_13 = new JLabel("Nacionalidad:");
		label_13.setBounds(15, 240, 114, 22);
		panel_1.add(label_13);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setText((String) null);
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(148, 240, 181, 23);
		panel_1.add(txtNacionalidad);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setText((String) null);
		txtCedulaPaciente.setEditable(false);
		txtCedulaPaciente.setColumns(10);
		txtCedulaPaciente.setBounds(148, 63, 181, 23);
		panel_1.add(txtCedulaPaciente);
		
		JLabel label_14 = new JLabel("C\u00E9dula del paciente:");
		label_14.setBounds(15, 62, 150, 22);
		panel_1.add(label_14);
		
		JLabel label_15 = new JLabel("Tipo de sangre:");
		label_15.setBounds(341, 240, 94, 22);
		panel_1.add(label_15);
		
		JLabel label_16 = new JLabel("Estado Civil:");
		label_16.setBounds(15, 170, 94, 22);
		panel_1.add(label_16);
		
		JLabel label_17 = new JLabel("Fecha de Nacimiento:");
		label_17.setBounds(15, 346, 150, 22);
		panel_1.add(label_17);
		
		txtTipoSangre = new JTextField();
		txtTipoSangre.setText((String) null);
		txtTipoSangre.setEditable(false);
		txtTipoSangre.setColumns(10);
		txtTipoSangre.setBounds(447, 240, 114, 22);
		panel_1.add(txtTipoSangre);
		
		txtEstadoCivil = new JTextField();
		txtEstadoCivil.setText((String) null);
		txtEstadoCivil.setEditable(false);
		txtEstadoCivil.setColumns(10);
		txtEstadoCivil.setBounds(148, 170, 181, 23);
		panel_1.add(txtEstadoCivil);
		
		txtGenero = new JTextField();
		txtGenero.setText((String) null);
		txtGenero.setEditable(false);
		txtGenero.setColumns(10);
		txtGenero.setBounds(148, 275, 181, 22);
		panel_1.add(txtGenero);
		
		JLabel label_18 = new JLabel("Genero:");
		label_18.setBounds(15, 275, 94, 22);
		panel_1.add(label_18);
		
		txtNacimiento = new JTextField();
		txtNacimiento.setText((String) null);
		txtNacimiento.setEditable(false);
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(148, 346, 181, 22);
		panel_1.add(txtNacimiento);
		
		JLabel label_19 = new JLabel("Edad:");
		label_19.setBounds(341, 205, 65, 22);
		panel_1.add(label_19);
		
		txtEdad = new JTextField();
		txtEdad.setText("0");
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(447, 205, 114, 22);
		panel_1.add(txtEdad);
		
		JLabel lblImagenPersona = new JLabel("");
		lblImagenPersona.setIcon(new ImageIcon(imagenPersona));
		lblImagenPersona.setBounds(424, 77, 94, 102);
		panel_1.add(lblImagenPersona);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmIngresarPaciente aux = new FrmIngresarPaciente(null,auxCita);
				aux.setVisible(true);
				if(paciente.getCodigoPaciente()!=null) {
					txtCodigoPaciente.setText(paciente.getCodigoPaciente());
					txtCedulaPaciente.setText(paciente.getCedula());
					txtNombrePaciente1.setText(paciente.getNombre());
					txtAlergia.setText(paciente.getAlergia());
					txtEstadoCivil.setText(paciente.getEstadoCivil());
					txtTipoSangre.setText(paciente.getTipoSangre());
					txtTelefono.setText(paciente.getTelefono());
					txtReligion.setText(paciente.getReligion());
					txtNacionalidad.setText(paciente.getNacionalidad());
					txtGenero.setText(paciente.getGenero());
					txtProfesion.setText(paciente.getProfesion());
					txtDireccion.setText(paciente.getDireccion());
					txtEmail.setText(paciente.getEmail());
					SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
					txtNacimiento.setText(""+sf.format(paciente.getDiaNacimiento()));
					LocalDate fechaNacimiento = paciente.getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					Period periodo = Period.between(fechaNacimiento, LocalDate.now());
					int edad = periodo.getYears();
					txtEdad.setText(""+edad);
					btnRegistrar.setEnabled(false);
					tbpTabs.setEnabled(true);
					txtNombrePaciente2.setText(paciente.getNombre());
					txtNombrePaciente3.setText(paciente.getNombre());
					cargarTablaConsultas();
					cargarTablaVacunas();
				}
			}
		});
		btnRegistrar.setEnabled(false);
		btnRegistrar.setBounds(421, 39, 97, 25);
		panel_1.add(btnRegistrar);
		
		pnlHistorial = new JPanel();
		tbpTabs.addTab("Historial", new ImageIcon(imagenConsulta4), pnlHistorial, null);
		pnlHistorial.setLayout(null);
		
		txtNombrePaciente2 = new JTextField();
		txtNombrePaciente2.setText((String) null);
		txtNombrePaciente2.setEditable(false);
		txtNombrePaciente2.setColumns(10);
		txtNombrePaciente2.setBounds(145, 15, 181, 22);
		pnlHistorial.add(txtNombrePaciente2);
		
		JLabel label_6 = new JLabel("Nombre Paciente:");
		label_6.setBounds(12, 16, 138, 20);
		pnlHistorial.add(label_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(12, 49, 607, 218);
		pnlHistorial.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		tblConsultas = new JTable();
		modeloConsultas = new DefaultTableModel();
		String[] columnasConsultas = {"Fecha Consulta", "Sintomas", "Diagnostico", "Enfermedad", "Medico"};
		modeloConsultas.setColumnIdentifiers(columnasConsultas);
		tblConsultas.setModel(modeloConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblConsultas.setEnabled(false);
		scrollPane.setViewportView(tblConsultas);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(12, 280, 607, 192);
		pnlHistorial.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		tblVacunas = new JTable();
		modeloVacunas = new DefaultTableModel();
		String[] columnasVacunas = {"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		modeloVacunas.setColumnIdentifiers(columnasVacunas);
		tblVacunas.setModel(modeloVacunas);
		tblVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVacunas.setEnabled(false);
		scrollPane_1.setViewportView(tblVacunas);
		
		pnlDiagnosticar = new JPanel();
		pnlDiagnosticar.setLayout(null);
		pnlDiagnosticar.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDiagnosticar.setBackground(SystemColor.controlHighlight);
		tbpTabs.addTab("Diagnosticar", new ImageIcon(imagenConsulta5), pnlDiagnosticar, null);
		
		JLabel label = new JLabel("C\u00F3digo Consulta:");
		label.setBounds(15, 27, 138, 20);
		pnlDiagnosticar.add(label);
		
		txtCodigoConsulta = new JTextField();
		txtCodigoConsulta.setText(Clinica.getInstance().generarCodigoConsulta());
		txtCodigoConsulta.setEditable(false);
		txtCodigoConsulta.setColumns(10);
		txtCodigoConsulta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoConsulta.setBounds(154, 26, 165, 23);
		pnlDiagnosticar.add(txtCodigoConsulta);
		
		JLabel label_1 = new JLabel("Nombre Paciente:");
		label_1.setBounds(15, 66, 138, 20);
		pnlDiagnosticar.add(label_1);
		
		txtNombrePaciente3 = new JTextField();
		txtNombrePaciente3.setText((String) null);
		txtNombrePaciente3.setEditable(false);
		txtNombrePaciente3.setColumns(10);
		txtNombrePaciente3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombrePaciente3.setBounds(154, 63, 394, 23);
		pnlDiagnosticar.add(txtNombrePaciente3);
		
		JLabel label_2 = new JLabel("Sintomas:");
		label_2.setBounds(15, 142, 138, 20);
		pnlDiagnosticar.add(label_2);
		
		JScrollPane scpSintomas = new JScrollPane();
		scpSintomas.setBounds(154, 102, 394, 100);
		pnlDiagnosticar.add(scpSintomas);
		
		txtSintomas = new JEditorPane();
		scpSintomas.setViewportView(txtSintomas);
		
		JScrollPane scpDiagnosticar = new JScrollPane();
		scpDiagnosticar.setBounds(154, 218, 394, 100);
		pnlDiagnosticar.add(scpDiagnosticar);
		
		txtDiagnostico = new JEditorPane();
		scpDiagnosticar.setViewportView(txtDiagnostico);
		
		JLabel label_3 = new JLabel("Diagnostico:");
		label_3.setBounds(15, 258, 138, 20);
		pnlDiagnosticar.add(label_3);
		
		JLabel label_4 = new JLabel("Enfermedad:");
		label_4.setBounds(15, 341, 138, 20);
		pnlDiagnosticar.add(label_4);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtEnfermedad.setBounds(154, 338, 232, 23);
		pnlDiagnosticar.add(txtEnfermedad);
		
		btnDiagnosticar = new JButton("Diagnosticar");
		btnDiagnosticar.setBounds(398, 335, 142, 29);
		pnlDiagnosticar.add(btnDiagnosticar);
		btnDiagnosticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoEnfermedad frmAux = new FrmListadoEnfermedad(true, 2);
				frmAux.setVisible(true);
				if(enfermedad!=null) {
					txtEnfermedad.setText(enfermedad.getNombreEnfermedad());
				}
			}
		});
		btnDiagnosticar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDiagnosticar.setBackground(UIManager.getColor("Button.light"));
		
		chHistorial = new JCheckBox("Desea guarda la consulta en el historial");
		chHistorial.setBounds(15, 434, 253, 25);
		pnlDiagnosticar.add(chHistorial);
		
		JButton btnVacunar = new JButton("Vacunar");
		btnVacunar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmVacunar aux = new FrmVacunar(vacunas);
				aux.setVisible(true);
			}
		});
		btnVacunar.setBackground(UIManager.getColor("Button.light"));
		btnVacunar.setBounds(154, 376, 142, 29);
		pnlDiagnosticar.add(btnVacunar);
		
		JLabel lblSuministrarVacuna = new JLabel("Suministrar Vacuna:");
		lblSuministrarVacuna.setBounds(15, 380, 138, 20);
		pnlDiagnosticar.add(lblSuministrarVacuna);
		loadPaciente();
	}

	private void loadPaciente() {
		if(auxPaciente==null) {
			btnRegistrar.setEnabled(true);
			txtCedulaPaciente.setText(auxCita.getCedulaPersona());
			txtNombrePaciente1.setText(auxCita.getNombrePersona());
			txtTelefono.setText(auxCita.getTelefonoPersona());
		}else {
			paciente=auxPaciente;
			txtCodigoPaciente.setText("P-"+paciente.getCodigoPaciente());
			txtCedulaPaciente.setText(paciente.getCedula());
			txtNombrePaciente1.setText(paciente.getNombre());
			txtAlergia.setText(paciente.getAlergia());
			txtEstadoCivil.setText(paciente.getEstadoCivil());
			txtTipoSangre.setText(paciente.getTipoSangre());
			txtTelefono.setText(paciente.getTelefono());
			txtReligion.setText(paciente.getReligion());
			txtNacionalidad.setText(paciente.getNacionalidad());
			txtGenero.setText(paciente.getGenero());
			txtProfesion.setText(paciente.getProfesion());
			txtDireccion.setText(paciente.getDireccion());
			txtEmail.setText(paciente.getEmail());
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			txtNacimiento.setText(""+sf.format(paciente.getDiaNacimiento()));
			LocalDate fechaNacimiento = paciente.getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period periodo = Period.between(fechaNacimiento, LocalDate.now());
			int edad = periodo.getYears();
			txtEdad.setText(""+edad);
			tbpTabs.setEnabled(true);
			txtNombrePaciente2.setText(paciente.getNombre());
			txtNombrePaciente3.setText(paciente.getNombre());
			if(paciente.getMisConsultas().size()!=0) {
				cargarTablaConsultas();
			}
			if(paciente.getHistorial().getMisVacunas().size()!=0) {
				cargarTablaVacunas();
			}
		}
		
	}

	private void cargarTablaConsultas() {
		modeloConsultas.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		rowConsultas = new Object[modeloConsultas.getColumnCount()];
		//{"Fecha Consulta", "Sintomas", "Diagnostico", "Enfermedad", "Medico"};
		for (int i = 0; i<paciente.getHistorial().getMisConsultas().size(); i++) {
			
			SimpleDateFormat formatoFechaConsuta = new SimpleDateFormat("dd - MM - yyyy");
	        rowConsultas[0] = formatoFechaConsuta.format(paciente.getHistorial().getMisConsultas().get(i).getFechaConsulta());
	        tblConsultas.getColumnModel().getColumn(0).setCellRenderer(tcr);
	        
	        rowConsultas[1] = paciente.getHistorial().getMisConsultas().get(i).getSintomas();
	        tblConsultas.getColumnModel().getColumn(1).setCellRenderer(tcr);
	        
	        rowConsultas[2] = paciente.getHistorial().getMisConsultas().get(i).getDiagnostico();
	        tblConsultas.getColumnModel().getColumn(2).setCellRenderer(tcr);
	        
	        if(paciente.getHistorial().getMisConsultas().get(i).getEnfermedad()!=null) {
	        	rowConsultas[3] = paciente.getHistorial().getMisConsultas().get(i).getEnfermedad().getNombreEnfermedad();
		        tblConsultas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        }else {
	        	rowConsultas[3] = "";
		        tblConsultas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        }
	        
	        rowConsultas[4] = paciente.getHistorial().getMisConsultas().get(i).getMedico().getNombre();
	        tblConsultas.getColumnModel().getColumn(4).setCellRenderer(tcr);
	        
	        modeloConsultas.addRow(rowConsultas);
		}
		
	}
	
	private void cargarTablaVacunas() {
		modeloVacunas.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		rowVacunas = new Object[modeloVacunas.getColumnCount()];
		//{"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		for (int i = 0; i < paciente.getHistorial().getMisVacunas().size(); i++) {
			
			rowVacunas[0] = paciente.getHistorial().getMisVacunas().get(i).getCodigoVacunacion();
	        tblVacunas.getColumnModel().getColumn(0).setCellRenderer(tcr);
	        
	        rowVacunas[1] = paciente.getHistorial().getMisVacunas().get(i).getNombreVacunacion();
	        tblVacunas.getColumnModel().getColumn(1).setCellRenderer(tcr);
	        
	        rowVacunas[2] = paciente.getHistorial().getMisVacunas().get(i).getTipoVacuna();
	        tblVacunas.getColumnModel().getColumn(2).setCellRenderer(tcr);
	        
	        rowVacunas[3] = paciente.getHistorial().getMisVacunas().get(i).getDescripcionVacuna();
	        tblVacunas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        
	        modeloVacunas.addRow(rowVacunas);
			}
	}
		

	public boolean comprobarCampos() {
		boolean aux = false;

		if ( !(txtSintomas.getText().equalsIgnoreCase("")) && !(txtDiagnostico.getText().equalsIgnoreCase(""))) {
			aux = true;
		}
		
		return aux;
	}
}
