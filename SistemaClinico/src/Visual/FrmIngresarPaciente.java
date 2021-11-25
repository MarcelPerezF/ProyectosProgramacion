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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class FrmIngresarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
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
	private JRadioButton btnHombre;
	private JRadioButton btnMujer;
	private JComboBox cbxEstadoCivil;
	private JComboBox cbxTipoSangre;
	private JCalendar calNacimiento;
	private JButton btnSalir;
	private JTextField txtCodigoPaciente;

	public static void main(String[] args) {
		try {
			FrmIngresarPaciente dialog = new FrmIngresarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmIngresarPaciente() {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Paciente");
		setModal(true);
		setResizable(false);
		setIconImage(imagenPaciente);
		setBounds(100, 100, 760, 680);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 13, 710, 124);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblImagenPaciente = new JLabel("");
			lblImagenPaciente.setBounds(15, 19, 80, 80);
			lblImagenPaciente.setIcon(new ImageIcon(imagenPaciente2));
			contentPanel.add(lblImagenPaciente);
		}
		{
			JLabel lblIngresoDePacientes = new JLabel("Ingreso de Pacientes");
			lblIngresoDePacientes.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblIngresoDePacientes.setBounds(310, 19, 196, 26);
			contentPanel.add(lblIngresoDePacientes);
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
			FechaFormulario.setBounds(320, 54, 177, 16);
			contentPanel.add(FechaFormulario);
		}
		{
			JLabel lblFormularioParaIngresar = new JLabel("Formulario para ingresar pacientes al sistema");
			lblFormularioParaIngresar.setBounds(259, 83, 299, 16);
			contentPanel.add(lblFormularioParaIngresar);
		}
		{
			JPanel pnBotones = new JPanel();
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnBotones.setBounds(12, 564, 710, 60);
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				JButton btnNuevo = new JButton("Nuevo");
				btnNuevo.setBounds(15, 16, 100, 28);
				btnNuevo.setActionCommand("OK");
				pnBotones.add(btnNuevo);
				getRootPane().setDefaultButton(btnNuevo);
			}
			{
				JButton cancelButton = new JButton("Registrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(305, 16, 100, 28);
				cancelButton.setActionCommand("Cancel");
				pnBotones.add(cancelButton);
			}
			
			btnSalir = new JButton("Salir");
			btnSalir.setActionCommand("Cancel");
			btnSalir.setBounds(595, 16, 100, 28);
			pnBotones.add(btnSalir);
		}
		{
			JPanel pnBody = new JPanel();
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
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"}));
			cbxTipoSangre.setBounds(162, 240, 200, 22);
			pnBody.add(cbxTipoSangre);
			
			JLabel lblEstadoCivil = new JLabel("Estado Civil:");
			lblEstadoCivil.setBounds(15, 284, 94, 22);
			pnBody.add(lblEstadoCivil);
			
			cbxEstadoCivil = new JComboBox();
			cbxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Soltero(a)", "Casado(a)", "Divorciado(a)", "Viudo(a)", "Union Libre"}));
			cbxEstadoCivil.setBounds(162, 284, 200, 22);
			pnBody.add(cbxEstadoCivil);
			
			JLabel lblReligion = new JLabel("Religion:");
			lblReligion.setBounds(377, 126, 94, 22);
			pnBody.add(lblReligion);
			
			txtReligion = new JTextField();
			txtReligion.setColumns(10);
			txtReligion.setBounds(521, 126, 174, 23);
			pnBody.add(txtReligion);
			
			JLabel lblProfecion = new JLabel("Profesion:");
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
			
			JLabel lblDireccion = new JLabel("Direccion");
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
			
			JPanel pnGeneroPaciente = new JPanel();
			pnGeneroPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Genero", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnGeneroPaciente.setBounds(15, 320, 347, 59);
			pnBody.add(pnGeneroPaciente);
			pnGeneroPaciente.setLayout(null);
			
			btnHombre = new JRadioButton("Hombre");
			btnHombre.setBounds(50, 22, 100, 25);
			pnGeneroPaciente.add(btnHombre);
			
			btnMujer = new JRadioButton("Mujer");
			btnMujer.setBounds(190, 22, 73, 25);
			pnGeneroPaciente.add(btnMujer);
			
			JPanel pnFechaNacimiento = new JPanel();
			pnFechaNacimiento.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fecha de Nacimiento:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnFechaNacimiento.setBounds(374, 194, 321, 188);
			pnBody.add(pnFechaNacimiento);
			pnFechaNacimiento.setLayout(null);
			
			calNacimiento = new JCalendar();
			calNacimiento.setBorder(new LineBorder(new Color(0, 0, 0)));
			calNacimiento.setBounds(50, 30, 228, 150);
			pnFechaNacimiento.add(calNacimiento);
		}
	}
}
