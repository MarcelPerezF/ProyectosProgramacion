package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Logico.Clinica;
import Logico.Paciente;
import Logico.Vacuna;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmVacunar extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;

	private Image imagenVacuna= new ImageIcon(FrmVacunar.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacuna2= new ImageIcon(FrmVacunar.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoVacuna;
	private JTextField txtNombrePaciente;
	private JTextField txtTipoVacuna;
	private JButton btnSalir;
	private JButton btnGuardar;
	private JButton btnPaciente;
	private JButton btnVacuna;
	private JTextField txtCedulaPaciente;
	private JTextField txtNombreVacuna;
	public static Paciente pacienteVacunar;
	public static Vacuna vacunaAplicar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			Clinica.getInstance().insertarPaciente(paciente1);
			Vacuna vacuna = new Vacuna("V-1", "COVID", 2, "CONTAGIOSA", "DI");
			Clinica.getInstance().insertarVacuna(vacuna);
			
			FrmVacunar dialog = new FrmVacunar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmVacunar() {
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea vacunar el paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de vacunar pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Vacunas");
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setIconImage(imagenVacuna);
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new LineBorder(new Color(0,0,0), 1));
		panelHeader.setBackground(UIManager.getColor("Button.light"));
		panelHeader.setBounds(10, 10, 463, 113);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblImagenCitas = new JLabel("");
		lblImagenCitas.setBounds(15, 19, 80, 80);
		lblImagenCitas.setIcon(new ImageIcon(imagenVacuna2));
		panelHeader.add(lblImagenCitas);
		
		JLabel lblTituloFormulario = new JLabel("VACUNACI\u00D3N DE PACIENTES");
		lblTituloFormulario.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloFormulario.setBounds(154, 19, 233, 20);
		panelHeader.add(lblTituloFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();

		JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaActualFormulario.setBounds(177, 47, 187, 25);
		panelHeader.add(lblFechaActualFormulario);
		
		JLabel lblDescripcionFormulario = new JLabel("Formulario para vacunaci\u00F3n de pacientes");
		lblDescripcionFormulario.setBounds(134, 77, 288, 20);
		panelHeader.add(lblDescripcionFormulario);
		
		JPanel panelBody = new JPanel();
		panelBody.setBackground(UIManager.getColor("CheckBox.light"));
		panelBody.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBody.setBounds(10, 139, 463, 221);
		contentPanel.add(panelBody);
		panelBody.setLayout(null);
		
		JLabel lblCodigoVacuna = new JLabel("C\u00F3digo Vacuna:");
		lblCodigoVacuna.setBounds(15, 100, 138, 20);
		panelBody.add(lblCodigoVacuna);
		
		txtCodigoVacuna = new JTextField();
		txtCodigoVacuna.setEditable(false);
		txtCodigoVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoVacuna.setBounds(164, 100, 165, 23);
		panelBody.add(txtCodigoVacuna);
		txtCodigoVacuna.setColumns(10);
		
		JLabel lblNombreVacuna = new JLabel("Nombre de Paciente:");
		lblNombreVacuna.setBounds(15, 21, 150, 20);
		panelBody.add(lblNombreVacuna);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombrePaciente.setColumns(10);
		txtNombrePaciente.setBounds(164, 20, 284, 23);
		panelBody.add(txtNombrePaciente);;
		
		JLabel lblEnfermedad = new JLabel("Tipo de vacuna:");
		lblEnfermedad.setBounds(15, 164, 138, 20);
		panelBody.add(lblEnfermedad);
		
		txtTipoVacuna = new JTextField();
		txtTipoVacuna.setEditable(false);
		txtTipoVacuna.setColumns(10);
		txtTipoVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtTipoVacuna.setBounds(164, 163, 284, 23);
		panelBody.add(txtTipoVacuna);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setEditable(false);
		txtCedulaPaciente.setColumns(10);
		txtCedulaPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCedulaPaciente.setBounds(164, 54, 284, 23);
		panelBody.add(txtCedulaPaciente);
		
		JLabel lblNombreDePaciente = new JLabel("C\u00E9dula de Paciente:");
		lblNombreDePaciente.setBounds(15, 56, 150, 20);
		panelBody.add(lblNombreDePaciente);
		
		JLabel lblNombreDeVacuna = new JLabel("Nombre Vacuna:");
		lblNombreDeVacuna.setBounds(15, 132, 138, 20);
		panelBody.add(lblNombreDeVacuna);
		
		txtNombreVacuna = new JTextField();
		txtNombreVacuna.setEditable(false);
		txtNombreVacuna.setColumns(10);
		txtNombreVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombreVacuna.setBounds(164, 130, 284, 23);
		panelBody.add(txtNombreVacuna);
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(10, 382, 463, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnPaciente = new JButton("Paciente");
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoPaciente frmAux = new FrmListadoPaciente(4);
				frmAux.setVisible(true);
				if(pacienteVacunar!=null) {
					txtNombrePaciente.setText(pacienteVacunar.getNombre());
					txtCedulaPaciente.setText(pacienteVacunar.getCedula());
					btnVacuna.setEnabled(true);
				}
			}
		});
		btnPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaciente.setBackground(UIManager.getColor("Button.light"));
		btnPaciente.setBounds(15, 17, 90, 29);
		panelFooter.add(btnPaciente);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vacunaAplicar.getCantidadVacunas()>1) {
					Clinica.getInstance().ingresarVacunaPacienteHistorial(pacienteVacunar, vacunaAplicar);

					JOptionPane.showMessageDialog(null, "Se ha ingresado la vacuna al paciente de manera satisfactoria!", "VACUNACI\u00d3N EXITOSA",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "No se puede aplicar esta vacuna debido a que no hay suficientes!", "ERROR EN VACUNACI\u00d3N ",
							JOptionPane.OK_OPTION);
					dispose();
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setBounds(244, 17, 89, 29);
		panelFooter.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea vacunar al paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de vacunar pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBackground(UIManager.getColor("Button.light"));
		btnSalir.setBounds(359, 17, 89, 29);
		panelFooter.add(btnSalir);
		
		btnVacuna = new JButton("Vacuna");
		btnVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoVacuna frmAux = new FrmListadoVacuna(true);
				frmAux.setVisible(true);
				if(vacunaAplicar!=null) {
					txtCodigoVacuna.setText(vacunaAplicar.getCodigoVacunacion());
					txtNombreVacuna.setText(vacunaAplicar.getNombreVacunacion());
					txtTipoVacuna.setText(vacunaAplicar.getTipoVacuna());
					btnGuardar.setEnabled(true);
				}
			}
		});
		btnVacuna.setEnabled(false);
		btnVacuna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVacuna.setBackground(UIManager.getColor("Button.light"));
		btnVacuna.setBounds(129, 17, 90, 29);
		panelFooter.add(btnVacuna);
		
	}
}
