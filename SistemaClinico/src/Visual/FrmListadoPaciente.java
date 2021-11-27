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
	private JTextField textField;
	private int cantidadPacientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmListadoPaciente dialog = new FrmListadoPaciente(1);
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
				}else {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea no desea modificar el paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
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
			FechaFormulario.setBounds(322, 41, 158, 26);
			pnEncabezadoFormulario.add(FechaFormulario);
		}
		
		JLabel lblDetalleFormulario = new JLabel("Listado de Pacientes ingresados en el sistema");
		lblDetalleFormulario.setBounds(216, 70, 332, 26);
		pnEncabezadoFormulario.add(lblDetalleFormulario);
		{
			pnBotones = new JPanel();
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnBotones.setBounds(12, 554, 730, 58);
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				btnAuxiliar = new JButton("Ver m\u00E1s");
				btnAuxiliar.setEnabled(false);
				if(opcion==2) {
					btnAuxiliar.setText("Seleccionar");
				}
				btnAuxiliar.setBounds(482, 16, 120, 25);
				pnBotones.add(btnAuxiliar);
				getRootPane().setDefaultButton(btnAuxiliar);
			}
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(644, 16, 71, 25);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre", "C\u00E9dula", "Edad", "Genero", "Tipo de Sangre"}));
		comboBox.setBounds(150, 31, 140, 23);
		pnFiltroBusqueda.add(comboBox);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(330, 31, 202, 23);
		pnFiltroBusqueda.add(textField);
		
		JButton button = new JButton("Buscar");
		button.setEnabled(false);
		button.setBounds(545, 27, 140, 30);
		pnFiltroBusqueda.add(button);
		loadPacientes(1, "");
	}

	public void loadPacientes(int opcionLista, String busqueda) {
		model.setRowCount(0);
		cantidadPacientes = 0;
		row = new Object[model.getColumnCount()];
		int i=0;
		for (i = 0; i <5; i++) {
            row[0] = "";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            model.addRow(row);
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
	        
	        int edad=0;
	        row[3] = edad;
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
