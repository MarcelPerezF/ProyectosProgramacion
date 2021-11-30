package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Enfermedad;
import Logico.Medico;
import Logico.Paciente;
import Logico.Usuario;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListadoCitas extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenCitas = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Listados.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenCitas2 = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Listados.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTable tblListado;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField txtCantidad;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JPanel pnListado;
	private Usuario medico;
	private Paciente paciente;
	private CitaMedica cita;
	private Date fechaCita;
	private int cantidadConsultas;
	
	public static void main(String[] args) {
		try { 
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			Paciente paciente2 = new Paciente("1", "402", "Marcededeede", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			
			Clinica.getInstance().insertarPaciente(paciente1);
			Clinica.getInstance().insertarPaciente(paciente2);
			
			Usuario medico = new Medico("1", "302", 1, "med", "med", "Antonio", "829", "RD", "algo@", "Hombre", "Cirugia");
			Usuario medico2 = new Medico("1", "302", 1, "med", "med", "Jose", "829", "RD", "algo@", "Hombre", "Cirugia");
			Clinica.getInstance().insertarUsuario(medico);
			Clinica.getInstance().insertarUsuario(medico2);
			
			CitaMedica cita1 = new CitaMedica("1", "Marc", "402", "829", medico, medico, new Date(34, 3, 2));
			CitaMedica cita2 = new CitaMedica("2", "Marc", "402", "829", medico2, medico, new Date(45, 3, 1));
			Date fecha = new Date(121, 10, 28, 8, 4, 4);
			CitaMedica cita3 = new CitaMedica("3", "Marcededeede", "402", "829", medico, medico, fecha);
			CitaMedica cita4 = new CitaMedica("4", "Marc", "402", "829", medico2, medico, new Date(32, 8, 5));
			Clinica.getInstance().insertarCitasMedicas(cita1);
			Clinica.getInstance().insertarCitasMedicas(cita2);
			Clinica.getInstance().insertarCitasMedicas(cita3);
			Clinica.getInstance().insertarCitasMedicas(cita4);
			FrmListadoCitas dialog = new FrmListadoCitas(medico, fecha); 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
			dialog.setVisible(true); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}

	
	public FrmListadoCitas(Usuario medicoOpc, Date fechaCitaOpc) {
		medico = medicoOpc;
		fechaCita = fechaCitaOpc;
		cantidadConsultas = 0;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de citas?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo del listado de citas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setTitle("Listado de consultas");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 648, 650);
		setIconImage(imagenCitas);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 23, 611, 112);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagenPaciente = new JLabel("");
		lblImagenPaciente.setBounds(33, 13, 88, 86);
		lblImagenPaciente.setIcon(new ImageIcon(imagenCitas2));
		contentPanel.add(lblImagenPaciente);
		{
			JLabel lblListaDePacientes = new JLabel("Listado de Citas");
			lblListaDePacientes.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblListaDePacientes.setBounds(260, 13, 138, 26);
			contentPanel.add(lblListaDePacientes);
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
			FechaFormulario.setBounds(250, 43, 158, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblListadoDeEnfermedades = new JLabel("Listado de citas");
		lblListadoDeEnfermedades.setBounds(268, 73, 122, 26);
		contentPanel.add(lblListadoDeEnfermedades);
		{
			JPanel bnBotones = new JPanel();
			bnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			bnBotones.setBounds(12, 534, 611, 60);
			getContentPane().add(bnBotones);
			bnBotones.setLayout(null);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(medico!=null && fechaCita!=null && cita!=null) {
							dispose();
							FrmConsulta frmAux = new FrmConsulta(paciente, medico, cita);
							frmAux.setVisible(true);
						}
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setBounds(389, 16, 111, 28);
				btnSeleccionar.setActionCommand("OK");
				bnBotones.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			{
				btnCancelar = new JButton("Salir");
				btnCancelar.setBounds(515, 16, 81, 28);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de citas?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de citas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnCancelar.setActionCommand("Cancel");
				bnBotones.add(btnCancelar);
			}
			{
				JLabel lblCantidad = new JLabel("Cantidad de Citas:");
				lblCantidad.setBounds(15, 20, 201, 20);
				bnBotones.add(lblCantidad);
			}
			
			txtCantidad = new JTextField();
			txtCantidad.setEditable(false);
			txtCantidad.setBounds(190, 18, 170, 23);
			bnBotones.add(txtCantidad);
			txtCantidad.setColumns(10);
		}
		
		pnListado = new JPanel();
		pnListado.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListado.setBounds(12, 148, 611, 370);
		getContentPane().add(pnListado);
		pnListado.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrlpnListado = new JScrollPane();
			pnListado.add(scrlpnListado);
			
			tblListado = new JTable();			
			tblListado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = tblListado.getSelectedRow();
					paciente = null;
					if(selectRow!=-1) {
						cita = Clinica.getInstance().buscarCitaMedicaPorCodigo(String.valueOf(tblListado.getValueAt(selectRow, 0)));
						paciente = Clinica.getInstance().buscarPaciente(cita.getCedulaPersona());
					}
					if(paciente!=null) {
						btnSeleccionar.setEnabled(true);
					}
				}
			});
			model = new DefaultTableModel();
			String[] headers = {"C\u00f3digo","Fecha","Paciente","Medico", "Estado", "Hora"};
			model.setColumnIdentifiers(headers);
			tblListado.setRowHeight(25);
			tblListado.setModel(model);
			tblListado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlpnListado.setViewportView(tblListado);
			
		}
		if(medico==null && fechaCita==null) {
			loadListado(1);
			tblListado.setEnabled(false);
		}else if(medico!=null && fechaCita==null) {
			loadListado(2);
		}else if(medico==null && fechaCita!=null){
			loadListado(3);
		}else {
			loadListado(4);
		}
		txtCantidad.setText(cantidadConsultas+" citas");
		
	}
	public void loadListado(int opcion) {
		model.setRowCount(0);
		cantidadConsultas = 0;
		if(opcion==1) {
			for (int i = 0; i < Clinica.getInstance().getMisCitasMedicas().size(); i++) {
				cargarFilas(Clinica.getInstance().getMisCitasMedicas().get(i));
			}
		}else if(opcion==2) {
			for (int i = 0; i < Clinica.getInstance().getMisCitasMedicas().size(); i++) {
					if( (Clinica.getInstance().getMisCitasMedicas().get(i).getMedico().getNombre().equalsIgnoreCase(medico.getNombre())) ) {
						cargarFilas(Clinica.getInstance().getMisCitasMedicas().get(i));
				}
			}
		}else if(opcion==3) {
			for (int i = 0; i < Clinica.getInstance().getMisCitasMedicas().size(); i++) {
				if( (Clinica.getInstance().getMisCitasMedicas().get(i).getFechaCita().equals(fechaCita))) {
					cargarFilas(Clinica.getInstance().getMisCitasMedicas().get(i));
				}
			}
		}else {
			for (int i = 0; i < Clinica.getInstance().getMisCitasMedicas().size(); i++) {
				int dia = Clinica.getInstance().getMisCitasMedicas().get(i).getFechaCita().getDay();
				int mes = Clinica.getInstance().getMisCitasMedicas().get(i).getFechaCita().getMonth();
				int year = Clinica.getInstance().getMisCitasMedicas().get(i).getFechaCita().getYear();
				if( (dia==(fechaCita.getDay())) && mes==fechaCita.getMonth() && year==fechaCita.getYear() &&
						Clinica.getInstance().getMisCitasMedicas().get(i).getMedico().getNombre().equalsIgnoreCase(medico.getNombre())
						&& Clinica.getInstance().getMisCitasMedicas().get(i).getEstadoCita().equalsIgnoreCase("En espera")) {
					cargarFilas(Clinica.getInstance().getMisCitasMedicas().get(i));
				}
			}
		}
		
	}
	
	public void cargarFilas(CitaMedica cita) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		SimpleDateFormat formatoFechaConsuta = new SimpleDateFormat("dd - MM - yyyy");
		int hora = cita.getFechaCita().getHours();
		String horario = "";
		if(hora>=8 && hora<=11) {
			horario="AM";
		}else {
			horario="PM";
		}
		try {
			row[0] = cita.getCodigoCita();
			tblListado.getColumnModel().getColumn(0).setCellRenderer(tcr);
			row[1] = formatoFechaConsuta.format(cita.getFechaCita());
			tblListado.getColumnModel().getColumn(1).setCellRenderer(tcr);
			row[2] = cita.getNombrePersona();
			tblListado.getColumnModel().getColumn(2).setCellRenderer(tcr);
			row[3] = cita.getMedico().getNombre();
			tblListado.getColumnModel().getColumn(3).setCellRenderer(tcr);
			row[4] = cita.getEstadoCita();
			tblListado.getColumnModel().getColumn(4).setCellRenderer(tcr);
			row[5] = hora+" - "+horario;
			tblListado.getColumnModel().getColumn(5).setCellRenderer(tcr);
			
			cantidadConsultas++;
			model.addRow(row);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
