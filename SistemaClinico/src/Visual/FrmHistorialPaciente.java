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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmHistorialPaciente extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenListadoPacientes= new ImageIcon(FrmHistorialPaciente.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenListadoPacientes2= new ImageIcon(FrmHistorialPaciente.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Paciente pacienteHistorial;
	private JPanel pnConsultas;
	private JTable tblConsultas;
	private JTable tblVacunas;
	private JScrollPane scrlConsultas;
	private JScrollPane scrlVacunas;
	private JLabel lblNombrePaciente;
	private JTextField txtNombrePaciente;
	private JTextField txtEdad;
	private JTextField txtTipoSangre;
	private JTextField txtReligion;
	private JButton btnAceptar;
	
	private static DefaultTableModel modeloConsultas;
	private static DefaultTableModel modeloVacunas;
	private static Object[] rowConsultas;
	private static Object[] rowVacunas;
	
	public static void main(String[] args) {
		try {
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			FrmHistorialPaciente dialog = new FrmHistorialPaciente(paciente1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrmHistorialPaciente(Paciente pacienteAux) {
		pacienteHistorial = pacienteAux;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver el historial del paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo del historial del paciente", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(aux==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setTitle("Historial de Pacientes");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 684, 680);
		setIconImage(imagenListadoPacientes);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 15, 648, 80);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(33, 5, 80, 70);
		lblImagen.setIcon(new ImageIcon(imagenListadoPacientes2));
		contentPanel.add(lblImagen);
		{
			JLabel lblTituloFormulario = new JLabel("Historial de Pacientes");
			lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTituloFormulario.setBounds(250, 5, 186, 26);
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
			FechaFormulario.setBounds(265, 27, 153, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblDescripcionFormulario = new JLabel("Historial de los Pacientes del sistema");
		lblDescripcionFormulario.setBounds(235, 49, 248, 26);
		contentPanel.add(lblDescripcionFormulario);
		
		JPanel pnHistorialPaciente = new JPanel();
		pnHistorialPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historial de Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnHistorialPaciente.setBounds(12, 111, 648, 520);
		getContentPane().add(pnHistorialPaciente);
		pnHistorialPaciente.setLayout(null);
		
		JPanel pnPaciente = new JPanel();
		pnPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnPaciente.setBounds(15, 27, 618, 118);
		pnHistorialPaciente.add(pnPaciente);
		pnPaciente.setLayout(null);
		
		lblNombrePaciente = new JLabel("Paciente:");
		lblNombrePaciente.setBounds(15, 28, 69, 23);
		pnPaciente.add(lblNombrePaciente);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setEnabled(false);
		txtNombrePaciente.setBounds(111, 28, 257, 23);
		pnPaciente.add(txtNombrePaciente);
		txtNombrePaciente.setColumns(10);
		
		txtEdad = new JTextField();
		txtEdad.setEnabled(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(488, 27, 115, 23);
		pnPaciente.add(txtEdad);
		
		JLabel lblEdadPaciente = new JLabel("Edad:");
		lblEdadPaciente.setBounds(419, 28, 41, 23);
		pnPaciente.add(lblEdadPaciente);
		
		JLabel lblTipoDeSangre = new JLabel("Tipo de Sangre:");
		lblTipoDeSangre.setBounds(15, 68, 99, 23);
		pnPaciente.add(lblTipoDeSangre);
		
		txtTipoSangre = new JTextField();
		txtTipoSangre.setEnabled(false);
		txtTipoSangre.setColumns(10);
		txtTipoSangre.setBounds(111, 68, 257, 23);
		pnPaciente.add(txtTipoSangre);
		
		JLabel lblReligio = new JLabel("Religi\u00F3n:");
		lblReligio.setBounds(419, 68, 63, 23);
		pnPaciente.add(lblReligio);
		
		txtReligion = new JTextField();
		txtReligion.setEnabled(false);
		txtReligion.setColumns(10);
		txtReligion.setBounds(488, 67, 115, 23);
		pnPaciente.add(txtReligion);
		
		pnConsultas = new JPanel();
		pnConsultas.setBorder(new TitledBorder(null, "Consultas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnConsultas.setBounds(15, 150, 618, 150);
		pnHistorialPaciente.add(pnConsultas);
		pnConsultas.setLayout(new BorderLayout(0, 0));
		
		scrlConsultas = new JScrollPane();
		pnConsultas.add(scrlConsultas, BorderLayout.CENTER);
		
		tblConsultas = new JTable();
		modeloConsultas = new DefaultTableModel();
		String[] columnasConsultas = {"Fecha Consulta", "Sintomas", "Diagnostico", "Enfermedad", "Medico"};
		modeloConsultas.setColumnIdentifiers(columnasConsultas);
		tblConsultas.setModel(modeloConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlConsultas.setViewportView(tblConsultas);
		
		JPanel pnVacunas = new JPanel();
		pnVacunas.setBounds(15, 320, 618, 150);
		pnHistorialPaciente.add(pnVacunas);
		pnVacunas.setLayout(new BorderLayout(0, 0));
		
		scrlVacunas = new JScrollPane();
		pnVacunas.add(scrlVacunas, BorderLayout.CENTER);
		
		tblVacunas = new JTable();
		modeloVacunas = new DefaultTableModel();
		String[] columnasVacunas = {"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		modeloVacunas.setColumnIdentifiers(columnasVacunas);
		tblVacunas.setModel(modeloVacunas);
		tblVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlVacunas.setViewportView(tblVacunas);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir de ver el historial del paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(aux==0) {
					JOptionPane.showMessageDialog(null, "Saliendo del historial del paciente", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnAceptar.setBounds(286, 480, 115, 29);
		pnHistorialPaciente.add(btnAceptar);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		try {
			LocalDate fechaNacimiento = pacienteHistorial.getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period periodo = Period.between(fechaNacimiento, LocalDate.now());
			int edad = periodo.getYears();
			txtNombrePaciente.setText(pacienteHistorial.getNombre());
			txtEdad.setText(edad+" a\u00f1os");
			txtTipoSangre.setText(pacienteHistorial.getTipoSangre());
			txtReligion.setText(pacienteHistorial.getReligion());
			cargarTablaConsultas();
			cargarTablaVacunas();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la informaci\u00f3n del paciente seleccionado", "Error", JOptionPane.OK_OPTION);			
			dispose();
		}
	}
	
	public void cargarTablaConsultas() {
		modeloConsultas.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		rowConsultas = new Object[modeloConsultas.getColumnCount()];
		//{"Fecha Consulta", "Sintomas", "Diagnostico", "Enfermedad", "Medico"};
		for (int i = 0; i<pacienteHistorial.getHistorial().getMisConsultas().size(); i++) {
			
			SimpleDateFormat formatoFechaConsuta = new SimpleDateFormat("dd - MM - yyyy");
	        rowConsultas[0] = formatoFechaConsuta.format(pacienteHistorial.getHistorial().getMisConsultas().get(i).getFechaConsulta());
	        tblConsultas.getColumnModel().getColumn(0).setCellRenderer(tcr);
	        
	        rowConsultas[1] = pacienteHistorial.getHistorial().getMisConsultas().get(i).getSintomas();
	        tblConsultas.getColumnModel().getColumn(1).setCellRenderer(tcr);
	        
	        rowConsultas[2] = pacienteHistorial.getHistorial().getMisConsultas().get(i).getDiagnostico();
	        tblConsultas.getColumnModel().getColumn(2).setCellRenderer(tcr);
	        
	        if(pacienteHistorial.getHistorial().getMisConsultas().get(i).getEnfermedad()!=null) {
	        	rowConsultas[3] = pacienteHistorial.getHistorial().getMisConsultas().get(i).getEnfermedad().getNombreEnfermedad();
		        tblConsultas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        }else {
	        	rowConsultas[3] = "";
		        tblConsultas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        }
	        
	        rowConsultas[4] = pacienteHistorial.getHistorial().getMisConsultas().get(i).getMedico().getNombre();
	        tblConsultas.getColumnModel().getColumn(4).setCellRenderer(tcr);
	        
	        modeloConsultas.addRow(rowConsultas);
	        
		}        
		
	}
	
	public void cargarTablaVacunas() {
		modeloVacunas.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		rowVacunas = new Object[modeloVacunas.getColumnCount()];
		//{"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		for (int i = 0; i < pacienteHistorial.getHistorial().getMisVacunas().size(); i++) {
			
			rowVacunas[0] = pacienteHistorial.getHistorial().getMisVacunas().get(i).getCodigoVacunacion();
	        tblVacunas.getColumnModel().getColumn(0).setCellRenderer(tcr);
	        
	        rowVacunas[1] = pacienteHistorial.getHistorial().getMisVacunas().get(i).getNombreVacunacion();
	        tblVacunas.getColumnModel().getColumn(1).setCellRenderer(tcr);
	        
	        rowVacunas[2] = pacienteHistorial.getHistorial().getMisVacunas().get(i).getTipoVacuna();
	        tblVacunas.getColumnModel().getColumn(2).setCellRenderer(tcr);
	        
	        rowVacunas[3] = pacienteHistorial.getHistorial().getMisVacunas().get(i).getDescripcionVacuna();
	        tblVacunas.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        
	        modeloVacunas.addRow(rowVacunas);
		}
		
	}
}
