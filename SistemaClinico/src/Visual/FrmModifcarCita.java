package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Dialog.ModalityType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.CitaMedica;
import Logico.Clinica;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class FrmModifcarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int opcion;
	private CitaMedica cita;
	private int sizeIcon = 35;
	private Image imagenCitas = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Citas3.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenCitas2 = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Citas3.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCedulaPaciente;
	private JTextField txtNombrePaciente;
	private JTextField txtNombreMedico;
	private JDateChooser dcFecha;
	private JComboBox cbxHora;
	private JRadioButton btnEspera;
	private JRadioButton btnCancelar;
	private ArrayList<String> hora;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmModifcarCita dialog = new FrmModifcarCita(1,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	//Se pasa el parametro 1 para posponer y el 2 para cancelar
	public FrmModifcarCita(int op, CitaMedica cm) {
		opcion=op;
		cita=cm;
		hora = new ArrayList<String>();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int select=0;
				if(opcion==1) {
					select = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del posponer cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
				}else {
					select = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del cancelar cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
				}
				if(select==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					if(opcion==1) {
						JOptionPane.showMessageDialog(null, "Saliendo del posponer cita", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Saliendo del cancelar cita", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}
				}else if(select==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		if(opcion==1) {
			setTitle("Posponer Cita");
		}else {
			setTitle("Cancelar Cita");
		}
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 593, 484);
		setLocationRelativeTo(null);
		setIconImage(imagenCitas);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 13, 552, 107);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagenCita = new JLabel("");
		lblImagenCita.setBounds(26, 13, 88, 86);
		lblImagenCita.setIcon(new ImageIcon(imagenCitas2));
		contentPanel.add(lblImagenCita);
		{
			JLabel lblTitulo = new JLabel("");
			if(opcion == 1) {
				lblTitulo = new JLabel("Posponer cita");
			}else {
				lblTitulo = new JLabel("Cancelar cita");
			}
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTitulo.setBounds(207, 13, 138, 26);
			contentPanel.add(lblTitulo);
		}
		{
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			
			JLabel lblFechaFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
			lblFechaFormulario.setBounds(197, 52, 158, 26);
			contentPanel.add(lblFechaFormulario);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setBounds(134, 367, 318, 48);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setEnabled(false);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(opcion==1) {
							boolean aprobado=true;
							int hora=0;
							String tiempo=cbxHora.getSelectedItem().toString();
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
								if(cita.getFechaCita()==de&&cita.getEstadoCita().equalsIgnoreCase("En espera")) {
									aprobado=false;
								}
							}
							if(aprobado) {
								CitaMedica aux = cita;
								aux.setFechaCita(de);
								JOptionPane.showMessageDialog(null, "Se cambio el momento de la cita", "Información",JOptionPane.INFORMATION_MESSAGE);
								Clinica.getInstance().modificarCitaMedica(aux);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Esta hora ya esta ocupada", "Información",JOptionPane.INFORMATION_MESSAGE);
							}
						}else {
							CitaMedica aux = cita;
							aux.setEstadoCita("Cancelada");
							JOptionPane.showMessageDialog(null, "Se cancelo la cita", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							Clinica.getInstance().modificarCitaMedica(aux);
						}
					}
				});
				btnGuardar.setBounds(39, 9, 102, 30);
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setBounds(196, 9, 75, 30);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int select = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea hacer cambios en la cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(select==0) {
							JOptionPane.showMessageDialog(null, "Saliendo sin cambios en la cita", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(12, 133, 552, 221);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedual del paciente:");
		lblNewLabel.setBounds(12, 13, 124, 16);
		panel.add(lblNewLabel);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setEditable(false);
		txtCedulaPaciente.setBounds(160, 10, 160, 22);
		panel.add(txtCedulaPaciente);
		txtCedulaPaciente.setColumns(10);
		
		JLabel lblNombreDelPaciente = new JLabel("Nombre del paciente:");
		lblNombreDelPaciente.setBounds(12, 48, 124, 16);
		panel.add(lblNombreDelPaciente);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setColumns(10);
		txtNombrePaciente.setBounds(160, 45, 364, 22);
		panel.add(txtNombrePaciente);
		
		txtNombreMedico = new JTextField();
		txtNombreMedico.setEditable(false);
		txtNombreMedico.setColumns(10);
		txtNombreMedico.setBounds(160, 80, 364, 22);
		panel.add(txtNombreMedico);
		
		JLabel lblNombreDelMedico = new JLabel("Nombre del medico:");
		lblNombreDelMedico.setBounds(12, 83, 124, 16);
		panel.add(lblNombreDelMedico);
		
		dcFecha = new JDateChooser();
		dcFecha.setEnabled(false);
		dcFecha.setBounds(160, 115, 114, 22);
		panel.add(dcFecha);
		
		JLabel lblFechaDeLa = new JLabel("Fecha de la cita:");
		lblFechaDeLa.setBounds(12, 121, 124, 16);
		panel.add(lblFechaDeLa);
		
		hora.add("08:00 am");
		hora.add("09:00 am");
		hora.add("10:00 am");
		hora.add("11:00 am");
		hora.add("12:00 pm");
		hora.add("03:00 pm");
		hora.add("04:00 pm");
		hora.add("05:00 pm");
		hora.add("06:00 pm");
		
		cbxHora = new JComboBox(hora.toArray());
		cbxHora.setEnabled(false);
		cbxHora.setBounds(160, 150, 160, 22);
		panel.add(cbxHora);
		
		btnEspera = new JRadioButton("En espera");
		btnEspera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEspera.isSelected()) {
					btnCancelar.setSelected(false);
					btnGuardar.setEnabled(false);
				}
			}
		});
		btnEspera.setEnabled(false);
		btnEspera.setBounds(160, 187, 127, 25);
		panel.add(btnEspera);
		
		btnCancelar = new JRadioButton("Cancelada");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCancelar.isSelected()) {
					btnEspera.setSelected(false);
					btnGuardar.setEnabled(true);
				}
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(334, 187, 127, 25);
		panel.add(btnCancelar);
		
		JLabel lblHoraDeLa = new JLabel("Hora de la cita:");
		lblHoraDeLa.setBounds(12, 153, 124, 16);
		panel.add(lblHoraDeLa);
		
		JLabel lblEstadoDeLa = new JLabel("Estado de la cita:");
		lblEstadoDeLa.setBounds(12, 191, 124, 16);
		panel.add(lblEstadoDeLa);
		
		loadCita();
	}

	private void loadCita() {
		SimpleDateFormat sf = new SimpleDateFormat("hh");
		boolean encontrado= false;
		int i=0;
		txtCedulaPaciente.setText(cita.getCedulaPersona());
		txtNombrePaciente.setText(cita.getNombrePersona());
		txtNombreMedico.setText(cita.getMedico().getNombre());
		dcFecha.setDate(cita.getFechaCita());
		for(String nuevo : hora) {
			if(nuevo.substring(0,2).equalsIgnoreCase(sf.format(cita.getFechaCita()))) {
				encontrado=true;
			}
			if(!encontrado) {
				i++;
			}
		}
		cbxHora.setSelectedIndex(i);
		btnEspera.setSelected(true);
		if(opcion==1) {
			dcFecha.setEnabled(true);
			cbxHora.setEnabled(true);
		}else {
			btnEspera.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}
}
