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

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Consulta;
import Logico.Enfermedad;
import Logico.Medico;
import Logico.Paciente;
import Logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;

	private Image imagenConsulta= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Consulta.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenConsulta2= new ImageIcon(FrmConsulta.class.getResource("Imagenes/Consulta.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoConsulta;
	private JTextField txtNombrePaciente;
	private JTextField txtEnfermedad;
	public static Enfermedad enfermedad;
	private JTextArea txtSintomas;
	private JTextArea txtDiagnostico;
	private JButton btnHistorial;
	private JButton btnDiagnosticar;
	private JButton btnGuardar;

	public static void main(String[] args) {
		try {
			Enfermedad enfermedad = new Enfermedad("E-1", "Covid", "Contagiosa", "Virus contagioso covid");
			Clinica.getInstance().insertarEnfermedades(enfermedad);
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			Medico medico = new Medico("1", "302", 1, "med", "med", "Antonio", "829", "RD", "algo@", "Hombre", "Cirugia");
			Clinica.getInstance().insertarUsuario(medico);
			CitaMedica cita = new CitaMedica("C-1", "Marc", "402", "829", medico, medico, new Date());
			Clinica.getInstance().insertarCitasMedicas(cita);
			FrmConsulta dialog = new FrmConsulta(paciente1, medico, cita);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmConsulta(Paciente paciente, Usuario medico, CitaMedica cita) {
		//Para controlar el boton de close.
		enfermedad = null;
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
		setBounds(100, 100, 600, 650);
		setLocationRelativeTo(null);
		setIconImage(imagenConsulta);
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new LineBorder(new Color(0,0,0), 1));
		panelHeader.setBackground(UIManager.getColor("Button.light"));
		panelHeader.setBounds(10, 10, 563, 113);
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
		panelHeader.add(lblDescripcionFormulario);
		
		JPanel panelBody = new JPanel();
		panelBody.setBackground(UIManager.getColor("CheckBox.light"));
		panelBody.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBody.setBounds(10, 139, 563, 377);
		contentPanel.add(panelBody);
		panelBody.setLayout(null);
		
		JLabel lblCodigoConsulta = new JLabel("C\u00F3digo Consulta:");
		lblCodigoConsulta.setBounds(15, 27, 138, 20);
		panelBody.add(lblCodigoConsulta);
		
		txtCodigoConsulta = new JTextField();
		txtCodigoConsulta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoConsulta.setEditable(false);
		txtCodigoConsulta.setBounds(154, 26, 165, 23);
		panelBody.add(txtCodigoConsulta);
		txtCodigoConsulta.setColumns(10);
		
		JLabel lblNombrePaciente = new JLabel("Nombre Paciente:");
		lblNombrePaciente.setBounds(15, 66, 138, 20);
		panelBody.add(lblNombrePaciente);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombrePaciente.setColumns(10);
		txtNombrePaciente.setBounds(154, 63, 394, 23);
		panelBody.add(txtNombrePaciente);
		
		JLabel lblSintomas = new JLabel("Sintomas:");
		lblSintomas.setBounds(15, 142, 138, 20);
		panelBody.add(lblSintomas);;
		
		JScrollPane scrlpSintomas = new JScrollPane();
		scrlpSintomas.setBounds(154, 102, 394, 100);
		panelBody.add(scrlpSintomas);
		
		txtSintomas = new JTextArea();
		scrlpSintomas.setViewportView(txtSintomas);
		
		JScrollPane scrlpDiagnostico = new JScrollPane();
		scrlpDiagnostico.setBounds(154, 218, 394, 100);
		panelBody.add(scrlpDiagnostico);
		
		txtDiagnostico = new JTextArea();
		scrlpDiagnostico.setViewportView(txtDiagnostico);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setBounds(15, 258, 138, 20);
		panelBody.add(lblDiagnostico);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setBounds(15, 341, 138, 20);
		panelBody.add(lblEnfermedad);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setEnabled(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtEnfermedad.setBounds(154, 338, 394, 23);
		panelBody.add(txtEnfermedad);
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(10, 532, 563, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmHistorialPaciente frmAux = new FrmHistorialPaciente(paciente);
				frmAux.setVisible(true);
			}
		});
		btnHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHistorial.setBackground(UIManager.getColor("Button.light"));
		btnHistorial.setBounds(29, 16, 142, 29);
		panelFooter.add(btnHistorial);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta consulta = new Consulta(txtCodigoConsulta.getText(), txtSintomas.getText(), txtDiagnostico.getText(),
						enfermedad, (Medico)medico);
				try {
					Clinica.getInstance().ingresarConsultaPaciente(paciente, consulta, cita);
					if(enfermedad!=null) {
						Clinica.getInstance().ingresarConsultaPacienteHistorial(paciente, consulta);
					}
					JOptionPane.showMessageDialog(null, "Se ha ingresado de manera correcta la consulta", "INGRESO DE CONSULTA", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setBounds(393, 17, 142, 29);
		panelFooter.add(btnGuardar);
		
		btnDiagnosticar = new JButton("Diagnosticar");
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
		btnDiagnosticar.setBounds(216, 16, 142, 29);
		panelFooter.add(btnDiagnosticar);
		
		txtCodigoConsulta.setText("C-P-"+ (paciente.getMisConsultas().size()+1) );
		txtNombrePaciente.setText(paciente.getNombre());
		comprobarCampos(txtDiagnostico);
		comprobarCampos(txtSintomas);
		
	}
	
	private void comprobarCampos(JTextArea text) {
		text.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if( comprobarCampos() ) {
					btnGuardar.setEnabled(true);
				}else {
					btnGuardar.setEnabled(false);
				}
			}	
			@Override
			public void removeUpdate(DocumentEvent e) {
				if( comprobarCampos() ) {
					btnGuardar.setEnabled(true);
				}else {
					btnGuardar.setEnabled(false);
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if( comprobarCampos() ) {
					btnGuardar.setEnabled(true);
				}else {
					btnGuardar.setEnabled(false);
				}
			}
		});		
	}
	
	public boolean comprobarCampos() {
		boolean aux = false;

		if ( !(txtSintomas.getText().equalsIgnoreCase("")) && !(txtDiagnostico.getText().equalsIgnoreCase(""))) {
			aux = true;
		}
		
		return aux;
	}
}
