package Visual;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Logico.CitaMedica;
import Logico.Clinica;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmCita extends JDialog {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> misEspecialidades;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;

	private Image imagenCitas = new ImageIcon(FrmCita.class.getResource("Imagenes/Citas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenCitas2 = new ImageIcon(FrmCita.class.getResource("Imagenes/Citas2.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoCita;
	private JTextField txtNombrePaciente;
	private JTextField txtCedulaPaciente;
	private JTextField txtNombreMedico;
	private JComboBox cbxEspecialidad;
	private JButton btnGuardarCita;
	private JButton btnMedico;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JTextField txtTelefonoPaciente;
	private JButton btnBuscar;
	private JLabel lblInformacion;
	private String especialidad;
	private JDateChooser dcFecha;
	private JComboBox cbxTiempo;
	private Usuario creador;
	
	//variable estatica publica
	public static Medico medicoCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmCita dialog = new FrmCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmCita(Usuario usuarioCreador) {
		creador = usuarioCreador;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar la cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de ingresar citas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		misEspecialidades = new ArrayList<String>();
		misEspecialidades.add("<<Selecciona>>");
		String nuevo="";
		Medico aux=null;
		boolean agregar=true;
		for(Usuario usuario: Clinica.getInstance().getMisUsuarios()) {
			if(usuario instanceof Medico) {
				aux=(Medico) usuario;
				nuevo = aux.getEspecialidad();
				for(String especial:misEspecialidades) {
					if(especial.equalsIgnoreCase(nuevo)) {
						agregar=false;
					}
				}
				if(agregar) {
					misEspecialidades.add(nuevo);
				}
			}
		}
		
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		setModal(true);
		setTitle("Cita");
		setBounds(100, 100, 600, 627);
		setLocationRelativeTo(null);
		setIconImage(imagenCitas);
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
		lblImagenCitas.setIcon(new ImageIcon(imagenCitas2));
		panelHeader.add(lblImagenCitas);
		
		JLabel lblTituloFormulario = new JLabel("INGRESO DE NUEVAS CITAS MEDICAS");
		lblTituloFormulario.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloFormulario.setBounds(178, 19, 310, 20);
		panelHeader.add(lblTituloFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();

		JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaActualFormulario.setBounds(240, 47, 187, 25);
		panelHeader.add(lblFechaActualFormulario);
		
		JLabel lblDescripcionFormulario = new JLabel("Formulario para ingresar las citas de pacientes");
		lblDescripcionFormulario.setBounds(164, 79, 338, 20);
		panelHeader.add(lblDescripcionFormulario);
		
		JPanel panelBody = new JPanel();
		panelBody.setBackground(UIManager.getColor("CheckBox.light"));
		panelBody.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBody.setBounds(10, 139, 563, 348);
		contentPanel.add(panelBody);
		panelBody.setLayout(null);
		
		JLabel lblCodigoCita = new JLabel("C\u00F3digo de la cita:");
		lblCodigoCita.setBounds(15, 27, 138, 20);
		panelBody.add(lblCodigoCita);
		
		txtCodigoCita = new JTextField();
		txtCodigoCita.setText(Clinica.getInstance().generarCodigoCita());
		txtCodigoCita.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoCita.setEditable(false);
		txtCodigoCita.setBounds(154, 26, 165, 23);
		panelBody.add(txtCodigoCita);
		txtCodigoCita.setColumns(10);
		
		JLabel lblCedulaPaciente = new JLabel("Cedula Paciente:");
		lblCedulaPaciente.setBounds(15, 68, 138, 20);
		panelBody.add(lblCedulaPaciente);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!txtCedulaPaciente.getText().equalsIgnoreCase("")&&!txtNombrePaciente.getText().equalsIgnoreCase("")&&!txtTelefonoPaciente.getText().equalsIgnoreCase("")) {
					cbxEspecialidad.setEnabled(true);
				}else {
					cbxEspecialidad.setEnabled(false);
				}
			}
		});
		txtCedulaPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCedulaPaciente.setColumns(10);
		txtCedulaPaciente.setBounds(154, 65, 165, 23);
		panelBody.add(txtCedulaPaciente);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(15, 203, 92, 20);
		panelBody.add(lblEspecialidad);
		
		JLabel lblMedicoCita = new JLabel("Medico:");
		lblMedicoCita.setBounds(15, 239, 69, 20);
		panelBody.add(lblMedicoCita);
		
		txtNombreMedico = new JTextField();
		txtNombreMedico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombreMedico.setForeground(Color.BLACK);
		txtNombreMedico.setEditable(false);
		txtNombreMedico.setColumns(10);
		txtNombreMedico.setBounds(154, 238, 394, 23);
		panelBody.add(txtNombreMedico);
		
		JLabel lblFechaCita = new JLabel("Fecha de la cita:");
		lblFechaCita.setBounds(15, 275, 138, 20);
		panelBody.add(lblFechaCita);;
		
		JLabel lblHorarioCita = new JLabel("Horario de la cita:");
		lblHorarioCita.setBounds(15, 311, 138, 20);
		panelBody.add(lblHorarioCita);
		
		cbxEspecialidad = new JComboBox(misEspecialidades.toArray());
		cbxEspecialidad.setEnabled(false);
		cbxEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxEspecialidad.getSelectedIndex()!=0) {
					btnMedico.setEnabled(true);
					especialidad = cbxEspecialidad.getSelectedItem().toString();
				}else {
					btnMedico.setEnabled(false);
				}
			}
		});
		cbxEspecialidad.setBounds(154, 202, 394, 23);
		panelBody.add(cbxEspecialidad);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paciente aux = Clinica.getInstance().buscarPaciente(txtCedulaPaciente.getText());
				if(aux==null) {
					lblInformacion.setVisible(true);
					txtNombrePaciente.setText("");
					txtTelefonoPaciente.setText("");
					txtNombrePaciente.setEditable(true);
					txtTelefonoPaciente.setEditable(true);
				}else {
					txtNombrePaciente.setText(aux.getNombre());
					txtTelefonoPaciente.setText(aux.getTelefono());
					cbxEspecialidad.setEnabled(true);
					lblInformacion.setVisible(false);
					btnLimpiar.setEnabled(true);
					txtCedulaPaciente.setEditable(false);
					btnBuscar.setEnabled(false);
					txtNombrePaciente.setEditable(false);
					txtTelefonoPaciente.setEditable(false);
				}
			}
		});
		btnBuscar.setBackground(SystemColor.controlHighlight);
		btnBuscar.setBounds(339, 65, 77, 23);
		panelBody.add(btnBuscar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(12, 101, 536, 88);
		panelBody.add(panel);
		panel.setLayout(null);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!txtCedulaPaciente.getText().equalsIgnoreCase("")&&!txtNombrePaciente.getText().equalsIgnoreCase("")&&!txtTelefonoPaciente.getText().equalsIgnoreCase("")) {
					cbxEspecialidad.setEnabled(true);
				}else {
					cbxEspecialidad.setEnabled(false);
				}
			}
		});
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setBounds(142, 13, 382, 23);
		panel.add(txtNombrePaciente);
		txtNombrePaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombrePaciente.setColumns(10);
		
		JLabel lblNombrePaciente = new JLabel("Nombre Paciente:");
		lblNombrePaciente.setBounds(12, 14, 138, 20);
		panel.add(lblNombrePaciente);
		
		JLabel lblTelefonoPaciente = new JLabel("Telefono Paciente:");
		lblTelefonoPaciente.setBounds(12, 49, 138, 20);
		panel.add(lblTelefonoPaciente);
		
		txtTelefonoPaciente = new JTextField();
		txtTelefonoPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isDigit(c))&&(c!='-'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
				if(!txtCedulaPaciente.getText().equalsIgnoreCase("")&&!txtNombrePaciente.getText().equalsIgnoreCase("")&&!txtTelefonoPaciente.getText().equalsIgnoreCase("")) {
					cbxEspecialidad.setEnabled(true);
				}else {
					cbxEspecialidad.setEnabled(false);
				}
			}
		});
		txtTelefonoPaciente.setEditable(false);
		txtTelefonoPaciente.setColumns(10);
		txtTelefonoPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtTelefonoPaciente.setBounds(142, 49, 382, 23);
		panel.add(txtTelefonoPaciente);
		
		lblInformacion = new JLabel("Paciente no encontrado");
		lblInformacion.setVisible(false);
		lblInformacion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInformacion.setBounds(339, 29, 170, 16);
		panelBody.add(lblInformacion);
		
		Date sd = new Date();
		dcFecha = new JDateChooser();
		dcFecha.setDate(sd);
		dcFecha.setBounds(154, 274, 138, 23);
		panelBody.add(dcFecha);
		
		cbxTiempo = new JComboBox();
		cbxTiempo.setEditable(true);
		cbxTiempo.setModel(new DefaultComboBoxModel(new String[] {"08:00 am", "09:00 am", "10:00 am", "11:00 am", "12:00 pm", "03:00 pm", "04:00 pm", "05:00 pm", "06:00 pm"}));
		cbxTiempo.setBounds(154, 310, 138, 23);
		panelBody.add(cbxTiempo);
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(10, 504, 563, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setEnabled(false);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBackground(UIManager.getColor("Button.light"));
		btnLimpiar.setBounds(15, 16, 77, 29);
		panelFooter.add(btnLimpiar);
		
		btnGuardarCita = new JButton("Guardar");
		btnGuardarCita.setEnabled(false);
		btnGuardarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean aprobado=true;
				boolean aprobado2=true;
				int hora=0;
				Date fechaActual = new Date();
				String tiempo=cbxTiempo.getSelectedItem().toString();
				Date de = dcFecha.getDate();
				if(tiempo.substring(6,8).equalsIgnoreCase("am")||tiempo.substring(0,2).equalsIgnoreCase("12")) {
					hora=Integer.valueOf(tiempo.substring(0,2));
				}else {
					hora=Integer.valueOf(tiempo.substring(0,2));
					hora+=12;
				}
				de.setHours(hora);
				de.setMinutes(0);
				de.setSeconds(0);
				for(CitaMedica cita : Clinica.getInstance().getMisCitasMedicas()) {
					if(cita.getFechaCita().getHours()==de.getHours()&&(cita.getCedulaPersona()==txtCedulaPaciente.getText()
							||cita.getMedico()==medicoCita)&&cita.getEstadoCita().equalsIgnoreCase("En espera")) {
						aprobado=false;
					}
				}
				if((de.getDay()<fechaActual.getDay()&&de.getYear()==fechaActual.getYear()&&de.getMonth()==fechaActual.getMonth())
						||(de.getYear()==fechaActual.getYear()&&de.getMonth()<fechaActual.getMonth())||(de.getYear()<fechaActual.getYear())) {
					aprobado2=false;
				}else {
					aprobado2=true;
				}
				if(aprobado&&aprobado2) {
					CitaMedica aux = new CitaMedica(txtCodigoCita.getText(),txtNombrePaciente.getText(),txtCedulaPaciente.getText(),txtTelefonoPaciente.getText(),medicoCita,creador,de);
					try {
						Clinica.getInstance().insertarCitasMedicas(aux);
						JOptionPane.showMessageDialog(null, "La cita se registro", "Información",JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					clean();
				}else if(aprobado2){
					JOptionPane.showMessageDialog(null, "Esta hora esta ocupada", "Información",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "No se puede hacer cita antes del dia actual", "Información",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGuardarCita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardarCita.setBackground(UIManager.getColor("Button.light"));
		btnGuardarCita.setBounds(293, 16, 89, 29);
		panelFooter.add(btnGuardarCita);
		
		btnMedico = new JButton("Medico");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoUsuarios aux = new FrmListadoUsuarios(4,especialidad);
				aux.setVisible(true);
				btnGuardarCita.setEnabled(true);
				txtNombreMedico.setText(medicoCita.getNombre());
			}
		});
		btnMedico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMedico.setEnabled(false);
		btnMedico.setBackground(UIManager.getColor("Button.light"));
		btnMedico.setBounds(133, 16, 94, 29);
		panelFooter.add(btnMedico);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar la cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de ingresar citas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBackground(UIManager.getColor("Button.light"));
		btnSalir.setBounds(445, 16, 103, 29);
		panelFooter.add(btnSalir);
	}

	private void clean() {
		Date sd = new Date();
		txtCedulaPaciente.setText("");
		txtCodigoCita.setText(Clinica.getInstance().generarCodigoCita());
		cbxTiempo.setSelectedIndex(0);
		txtNombreMedico.setText("");
		txtNombrePaciente.setText("");
		txtTelefonoPaciente.setText("");
		dcFecha.setDate(sd);
		cbxEspecialidad.setSelectedIndex(0);
		btnLimpiar.setEnabled(false);
		btnGuardarCita.setEnabled(false);
		btnMedico.setEnabled(false);
		txtCedulaPaciente.setEditable(true);
		btnBuscar.setEnabled(true);
	}
}
