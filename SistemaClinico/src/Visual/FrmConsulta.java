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
import javax.swing.border.MatteBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;

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
	public static Enfermedad enfermedad;
	private JButton btnDiagnosticar;
	private JButton btnGuardar;
	private JTextField txtCodigoConsulta;
	private JTextField textField_1;
	private JTextField txtEnfermedad;
	private JEditorPane txtSintomas;
	private JEditorPane txtDiagnostico;
	private JTabbedPane tabbedPane;
	private JPanel pnlInformacion;
	private JPanel pnlHistorial;
	private JPanel pnlDiagnosticar;

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
		setBounds(100, 100, 600, 788);
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
		panelHeader.add(lblDescripcionFormulario);;
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(184, 665, 226, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
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
		btnGuardar.setBounds(42, 16, 142, 29);
		panelFooter.add(btnGuardar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(12, 136, 561, 516);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		pnlInformacion = new JPanel();
		tabbedPane.addTab("Informacion", new ImageIcon(imagenConsulta3), pnlInformacion, null);
		
		pnlHistorial = new JPanel();
		tabbedPane.addTab("Historial", new ImageIcon(imagenConsulta4), pnlHistorial, null);
		
		pnlDiagnosticar = new JPanel();
		pnlDiagnosticar.setLayout(null);
		pnlDiagnosticar.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDiagnosticar.setBackground(SystemColor.controlHighlight);
		tabbedPane.addTab("Diagnosticar", new ImageIcon(imagenConsulta5), pnlDiagnosticar, null);
		
		JLabel label = new JLabel("C\u00F3digo Consulta:");
		label.setBounds(15, 27, 138, 20);
		pnlDiagnosticar.add(label);
		
		txtCodigoConsulta = new JTextField();
		txtCodigoConsulta.setText("C-P-1");
		txtCodigoConsulta.setEditable(false);
		txtCodigoConsulta.setColumns(10);
		txtCodigoConsulta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoConsulta.setBounds(154, 26, 165, 23);
		pnlDiagnosticar.add(txtCodigoConsulta);
		
		JLabel label_1 = new JLabel("Nombre Paciente:");
		label_1.setBounds(15, 66, 138, 20);
		pnlDiagnosticar.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField_1.setBounds(154, 63, 394, 23);
		pnlDiagnosticar.add(textField_1);
		
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
		
		JCheckBox chHistorial = new JCheckBox("Desea guarda la consulta en el historial");
		chHistorial.setBounds(15, 434, 253, 25);
		pnlDiagnosticar.add(chHistorial);
		
	}
	
	private void comprobarCampos(JTextArea text) {
	}
	
	public boolean comprobarCampos() {
		boolean aux = false;

		if ( !(txtSintomas.getText().equalsIgnoreCase("")) && !(txtDiagnostico.getText().equalsIgnoreCase(""))) {
			aux = true;
		}
		
		return aux;
	}
}
