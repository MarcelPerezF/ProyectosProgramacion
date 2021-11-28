package Visual;

import java.awt.Image;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logico.Clinica;
import Logico.Paciente;
import Logico.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListadoPaciente extends JDialog {

	private final JPanel pnEncabezadoFormulario = new JPanel();
	private int sizeIcon = 35;
	private Image imagenPaciente= new ImageIcon(FrmListadoPaciente.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenPaciente2= new ImageIcon(FrmListadoPaciente.class.getResource("Imagenes/Paciente3.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTable tblListadoPacientes;
	private static DefaultTableModel model;
	private static Object[] row;
	private JPanel pnBotones;
	private JPanel pnListadoPacientes;
	private JScrollPane scrlListadoPacientes;
	private JTextField txtCantidadPacientes;
	private JButton btnAuxiliar;
	private JButton cancelButton;
	private int opcionListado;
	private JPanel pnFiltroBusqueda;
	private JTextField txtBusqueda;
	private int cantidadPacientes;
	private JSpinner spnBusqueda;
	private JComboBox cbxFiltro;
	private JButton btnBuscar;
	private JComboBox cbxBusqueda;
	Paciente pacienteSeleccionado;

	public static void main(String[] args) {
		try {
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			Paciente paciente2 = new Paciente("2", "403", "Marc", "Mujer", new Date(), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero", "Catolico", "O-", "Estudiante");
			Clinica.getInstance().insertarPaciente(paciente1);
			Clinica.getInstance().insertarPaciente(paciente2);
			FrmListadoPaciente dialog = new FrmListadoPaciente(2);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrmListadoPaciente(int opcion) {
		opcionListado = opcion;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(opcionListado==1) {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de pacientes?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}else if(opcionListado==2){
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea no desea modificar el paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}else {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea no desea ver el historial del paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}
			}
		});
		
		setTitle("Lista de Pacientes");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 770, 668);
		setIconImage(imagenPaciente);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		pnEncabezadoFormulario.setBackground(Color.WHITE);
		pnEncabezadoFormulario.setBounds(12, 10, 730, 112);
		pnEncabezadoFormulario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(pnEncabezadoFormulario);
		pnEncabezadoFormulario.setLayout(null);
		
		JLabel lblImagenPaciente = new JLabel("");
		lblImagenPaciente.setBounds(33, 13, 88, 86);
		lblImagenPaciente.setIcon(new ImageIcon(imagenPaciente2));
		pnEncabezadoFormulario.add(lblImagenPaciente);
		{
			JLabel lblListaDePacientes = new JLabel("Listado de Pacientes");
			lblListaDePacientes.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblListaDePacientes.setBounds(293, 16, 174, 26);
			pnEncabezadoFormulario.add(lblListaDePacientes);
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
			FechaFormulario.setBounds(303, 41, 158, 26);
			pnEncabezadoFormulario.add(FechaFormulario);
		}
		
		JLabel lblDetalleFormulario = new JLabel("Listado de Pacientes ingresados en el sistema");
		lblDetalleFormulario.setBounds(240, 70, 332, 26);
		pnEncabezadoFormulario.add(lblDetalleFormulario);
		{
			pnBotones = new JPanel();
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnBotones.setBounds(12, 554, 730, 58);
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				btnAuxiliar = new JButton("Ver m\u00E1s");
				btnAuxiliar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(opcionListado==1) {
							dispose();
							FrmListadoPacientesDetallado frmAux = new FrmListadoPacientesDetallado(pacienteSeleccionado);
							frmAux.setVisible(true);
						}
						if(opcionListado==2) {
							dispose();
							FrmIngresarPaciente frmAux = new FrmIngresarPaciente(pacienteSeleccionado);
							frmAux.setVisible(true);
						}
						if(opcionListado==3) {
							FrmHistorialPaciente frmAux =  new FrmHistorialPaciente(pacienteSeleccionado);
							frmAux.setVisible(true);
						}
					}
				});
				if(opcionListado==2 || opcionListado==3) {
					btnAuxiliar.setText("Seleccionar");
				}
				btnAuxiliar.setEnabled(false);
				if(opcion==2) {
					btnAuxiliar.setText("Seleccionar");
				}
				btnAuxiliar.setBounds(482, 16, 120, 25);
				pnBotones.add(btnAuxiliar);
				getRootPane().setDefaultButton(btnAuxiliar);
			}
			{
				cancelButton = new JButton("Aceptar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de pacientes?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(aux==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				cancelButton.setBounds(617, 16, 98, 25);
				pnBotones.add(cancelButton);
			}
			
			JLabel lblFooter = new JLabel("Cantidad de pacientes:");
			lblFooter.setBounds(15, 19, 201, 20);
			pnBotones.add(lblFooter);
			
			txtCantidadPacientes = new JTextField();
			txtCantidadPacientes.setEditable(false);
			txtCantidadPacientes.setColumns(10);
			txtCantidadPacientes.setBounds(205, 18, 146, 23);
			pnBotones.add(txtCantidadPacientes);
		}
		
		pnListadoPacientes = new JPanel();
		pnListadoPacientes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListadoPacientes.setBounds(12, 148, 730, 390);
		getContentPane().add(pnListadoPacientes);
		pnListadoPacientes.setLayout(null);
		{
			scrlListadoPacientes = new JScrollPane();
			scrlListadoPacientes.setBounds(3, 116, 726, 270);
			pnListadoPacientes.add(scrlListadoPacientes);
			
			tblListadoPacientes = new JTable();
			tblListadoPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = tblListadoPacientes.getSelectedRow();
					pacienteSeleccionado = null;
					if(selectRow!=-1) {
						pacienteSeleccionado = Clinica.getInstance().buscarPaciente(String.valueOf(tblListadoPacientes.getValueAt(selectRow, 2)));
					}
					if(pacienteSeleccionado!=null) {
						btnAuxiliar.setEnabled(true);
					}
				}
			});
			model = new DefaultTableModel();
			String[] headers = {"C\u00f3digo", "Nombre", "C\u00e9dula", "Edad", "Genero", "Tipo de Sangre"};
			model.setColumnIdentifiers(headers);
			tblListadoPacientes.setModel(model);
			tblListadoPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlListadoPacientes.setViewportView(tblListadoPacientes);			
		}
		
		pnFiltroBusqueda = new JPanel();
		pnFiltroBusqueda.setBounds(15, 16, 700, 84);
		pnListadoPacientes.add(pnFiltroBusqueda);
		pnFiltroBusqueda.setLayout(null);
		
		JLabel label = new JLabel("Tipo de busqueda:");
		label.setBounds(15, 31, 133, 23);
		pnFiltroBusqueda.add(label);
		
		cbxFiltro = new JComboBox();
		cbxFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPacientes(1, "");
				txtBusqueda.setText("");
				spnBusqueda.setValue(1);
				if(cbxFiltro.getSelectedIndex()==3) {
					btnBuscar.setEnabled(true);
					spnBusqueda.setVisible(true);
					txtBusqueda.setVisible(false);
					cbxBusqueda.setVisible(false);
				}else if(cbxFiltro.getSelectedIndex()==4) {
					btnBuscar.setEnabled(true);
					spnBusqueda.setVisible(false);
					txtBusqueda.setVisible(false);
					cbxBusqueda.setVisible(true);
					cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
				}else if(cbxFiltro.getSelectedIndex()==5){
					btnBuscar.setEnabled(true);
					spnBusqueda.setVisible(false);
					txtBusqueda.setVisible(false);
					cbxBusqueda.setVisible(true);
					cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"}));
				}else {
					btnBuscar.setEnabled(false);
					spnBusqueda.setVisible(false);
					txtBusqueda.setVisible(true);
					cbxBusqueda.setVisible(false);
				}
			}
		});
		cbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre", "C\u00E9dula", "Edad", "Genero", "Tipo de Sangre"}));
		cbxFiltro.setBounds(150, 31, 140, 23);
		pnFiltroBusqueda.add(cbxFiltro);
		
		txtBusqueda = new JTextField();
		txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {					
				@Override
				public void removeUpdate(DocumentEvent e) {
					if( !(txtBusqueda.getText().equalsIgnoreCase(""))) {
						btnBuscar.setEnabled(true);
					}else {
						btnBuscar.setEnabled(false);
					}
				}					
				@Override
				public void insertUpdate(DocumentEvent e) {
					if( !(txtBusqueda.getText().equalsIgnoreCase(""))) {
						btnBuscar.setEnabled(true);
					}else {
						btnBuscar.setEnabled(false);
					}					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					if( !(txtBusqueda.getText().equalsIgnoreCase(""))) {
						btnBuscar.setEnabled(true);
					}else {
						btnBuscar.setEnabled(false);
					}						
				}
		});
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(330, 31, 202, 23);
		pnFiltroBusqueda.add(txtBusqueda);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcionAux = cbxFiltro.getSelectedIndex();
				switch (opcionAux) {
				case 0:
					loadPacientes(2, txtBusqueda.getText());
					break;
					
				case 1:
					loadPacientes(3, txtBusqueda.getText());
					break;
					
				case 2:
					loadPacientes(4, txtBusqueda.getText());
					break;
					
				case 3:
					loadPacientes(5, spnBusqueda.getValue().toString());
					break;
					
				case 4:
					loadPacientes(6, cbxBusqueda.getSelectedItem().toString());
					break;
					
				case 5:
					loadPacientes(7, cbxBusqueda.getSelectedItem().toString());
					break;
				}
				
			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(545, 27, 140, 30);
		pnFiltroBusqueda.add(btnBuscar);
		
		spnBusqueda = new JSpinner();
		spnBusqueda.setVisible(false);
		spnBusqueda.setBounds(330, 31, 202, 23);
		pnFiltroBusqueda.add(spnBusqueda);
		
		cbxBusqueda = new JComboBox();
		cbxBusqueda.setVisible(false);
		cbxBusqueda.setBounds(330, 31, 202, 23);
		pnFiltroBusqueda.add(cbxBusqueda);
		loadPacientes(1, "");
	}

	public void loadPacientes(int opcionLista, String busqueda) {
		model.setRowCount(0);
		cantidadPacientes = 0;
		for (int i = 0; i < Clinica.getInstance().getMisPacientes().size(); i++) {
			switch (opcionLista) {
			case 1:
				cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
				cantidadPacientes++;
				break;
			
			case 2:
				if(Clinica.getInstance().getMisPacientes().get(i).getCodigoPaciente().equalsIgnoreCase(busqueda)) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
				
			case 3:
				if(Clinica.getInstance().getMisPacientes().get(i).getNombre().equalsIgnoreCase(busqueda)) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
				
			case 4:
				if(Clinica.getInstance().getMisPacientes().get(i).getCedula().equalsIgnoreCase(busqueda)) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
				
			case 5:
				LocalDate fechaNacimiento = Clinica.getInstance().getMisPacientes().get(i).getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Period periodo = Period.between(fechaNacimiento, LocalDate.now());
				int edad = periodo.getYears();
				int edadBusqueda = Integer.valueOf(busqueda);
				if(edad==edadBusqueda) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
				
			case 6:
				if(Clinica.getInstance().getMisPacientes().get(i).getGenero().equalsIgnoreCase(busqueda)) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
				
			case 7:
				if(Clinica.getInstance().getMisPacientes().get(i).getTipoSangre().equalsIgnoreCase(busqueda)) {
					cargarFilas(Clinica.getInstance().getMisPacientes().get(i));	
					cantidadPacientes++;
				}
				break;
			}
			txtCantidadPacientes.setText(cantidadPacientes+" pacientes");
		}		
	}
	
	public void cargarFilas(Paciente paciente) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		try {
	        row[0] = paciente.getCodigoPaciente();
	        tblListadoPacientes.getColumnModel().getColumn(0).setCellRenderer(tcr);
	        
	        row[1] = paciente.getNombre();
	        tblListadoPacientes.getColumnModel().getColumn(1).setCellRenderer(tcr);

	        row[2] = paciente.getCedula();
	        tblListadoPacientes.getColumnModel().getColumn(2).setCellRenderer(tcr);
	        
			LocalDate fechaNacimiento = paciente.getDiaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period edad = Period.between(fechaNacimiento, LocalDate.now());
	        row[3] = edad.getYears();
	        tblListadoPacientes.getColumnModel().getColumn(3).setCellRenderer(tcr);
	        
	        row[4] = paciente.getGenero();
	        tblListadoPacientes.getColumnModel().getColumn(4).setCellRenderer(tcr);
	        
	        row[5] = paciente.getTipoSangre();
	        tblListadoPacientes.getColumnModel().getColumn(5).setCellRenderer(tcr);
	        
	        model.addRow(row);	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	
	}
}
