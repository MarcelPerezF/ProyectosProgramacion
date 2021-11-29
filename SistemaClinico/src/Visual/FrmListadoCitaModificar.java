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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logico.CitaMedica;
import Logico.Clinica;
import Logico.Medico;
import Logico.Usuario;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmListadoCitaModificar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenCitas = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Listados.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenCitas2 = new ImageIcon(FrmListadoCitas.class.getResource("Imagenes/Listados.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCantCitas;
	private JTable tblListaCitas;
	private static DefaultTableModel model;
	private static Object[] row;
	private int opcion;
	private JButton btnSalir;
	private JButton btnSeleccionar;
	private String code;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private CitaMedica citaMed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmListadoCitaModificar dialog = new FrmListadoCitaModificar(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	//se pasa el paramtero 1 para posponer y 2 para cancelar
	public FrmListadoCitaModificar(int op) {
		opcion=op;
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
		setModal(true);
		setResizable(false);
		setTitle("Listado de Citas");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(imagenCitas);
		setBounds(100, 100, 859, 681);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(23, 13, 799, 113);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagenCita = new JLabel("");
		lblImagenCita.setBounds(33, 13, 88, 86);
		lblImagenCita.setIcon(new ImageIcon(imagenCitas2));
		contentPanel.add(lblImagenCita);
		{
			JLabel label = new JLabel("Listado de Citas");
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setBounds(330, 13, 138, 26);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Listado de citas");
			label.setBounds(349, 82, 101, 26);
			contentPanel.add(label);
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
			lblFechaFormulario.setBounds(320, 52, 158, 26);
			contentPanel.add(lblFechaFormulario);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setBounds(23, 567, 799, 53);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrmModifcarCita aux = new FrmModifcarCita(opcion,citaMed);
						aux.setVisible(true);
						dispose();
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setBounds(546, 11, 115, 30);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de citas?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de citas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnSalir.setBounds(705, 11, 82, 30);
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
			{
				JLabel lblNewLabel = new JLabel("Cantidad de citas:");
				lblNewLabel.setBounds(12, 15, 125, 23);
				buttonPane.add(lblNewLabel);
			}
			
			txtCantCitas = new JTextField();
			txtCantCitas.setEditable(false);
			txtCantCitas.setBounds(126, 15, 135, 23);
			buttonPane.add(txtCantCitas);
			txtCantCitas.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(23, 139, 799, 415);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 107, 798, 308);
		panel.add(scrollPane);
		
		tblListaCitas = new JTable();
		tblListaCitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int aux = tblListaCitas.getSelectedRow();
				if(aux!=-1){
					btnSeleccionar.setEnabled(true);
					code = (String) tblListaCitas.getValueAt(aux, 0);
					citaMed = Clinica.getInstance().buscarCitaMedicaPorCodigo(code);
					if(citaMed.getEstadoCita().equalsIgnoreCase("En espera")) {
						btnSeleccionar.setEnabled(true);
					}else {
						btnSeleccionar.setEnabled(false);
					}
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Codigo","Nombre","Cedula","Medico","Fecha","Hora","Estado"};
		model.setColumnIdentifiers(headers);
		tblListaCitas.setRowHeight(25);
		tblListaCitas.setModel(model);
		tblListaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblListaCitas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 775, 81);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Tipo de busqueda:");
		label.setBounds(12, 29, 133, 23);
		panel_1.add(label);
		
		JComboBox cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Paciente", "Cedula", "Medico"}));
		cbxTipo.setBounds(141, 29, 140, 23);
		panel_1.add(cbxTipo);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(407, 29, 202, 23);
		txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txtBusqueda.getText().equalsIgnoreCase(""))
					btnBuscar.setEnabled(true);
				else
					btnBuscar.setEnabled(false);				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txtBusqueda.getText().equalsIgnoreCase(""))
					btnBuscar.setEnabled(true);
				else
					btnBuscar.setEnabled(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txtBusqueda.getText().equalsIgnoreCase(""))
					btnBuscar.setEnabled(true);
				else
					btnBuscar.setEnabled(false);				
			}
		});
		panel_1.add(txtBusqueda);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCitas(cbxTipo.getSelectedIndex(), txtBusqueda.getText());
			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(639, 25, 124, 30);
		panel_1.add(btnBuscar);
		
		loadCitas(1,"");
	}

	private void loadCitas(int select, String busqueda) {
		int i=0;
		for (CitaMedica cita : Clinica.getInstance().getMisCitasMedicas()) {
			switch (select) {
			case 1:
				cargarFilas(cita);	
				i++;
				break;
			
			case 2:
				if(cita.getCodigoCita().equalsIgnoreCase(busqueda)) {
					cargarFilas(cita);	
					i++;
				}
				break;
			
			case 3:
				if(cita.getNombrePersona().equalsIgnoreCase(busqueda)) {
					cargarFilas(cita);	
					i++;
				}
				break;
				
			case 4:
				if(cita.getCedulaPersona().equalsIgnoreCase(busqueda)) {
					cargarFilas(cita);	
					i++;
				}
				break;
			
			case 5:
				if(cita.getMedico().getNombre().equalsIgnoreCase(busqueda)) {
					cargarFilas(cita);	
					i++;
				}
				break;
			}
		}
		txtCantCitas.setText(""+i+" usuarios");
	}
			
		public void cargarFilas(CitaMedica cita) {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
			row = new Object[model.getColumnCount()];
			try {
				row[0] = cita.getCodigoCita();
				tblListaCitas.getColumnModel().getColumn(0).setCellRenderer(tcr);
		        
		        row[1] = cita.getNombrePersona();
		        tblListaCitas.getColumnModel().getColumn(1).setCellRenderer(tcr);

		        row[2] = cita.getCedulaPersona();
		        tblListaCitas.getColumnModel().getColumn(2).setCellRenderer(tcr);

		        row[3] = cita.getMedico().getNombre();
		        tblListaCitas.getColumnModel().getColumn(3).setCellRenderer(tcr);
		        
		        row[4] = sd.format(cita.getFechaCita());
		        tblListaCitas.getColumnModel().getColumn(4).setCellRenderer(tcr);
		        
		        row[5] = sf.format(cita.getFechaCita());
		        tblListaCitas.getColumnModel().getColumn(5).setCellRenderer(tcr);
		        
		        row[6] = cita.getEstadoCita();
		        tblListaCitas.getColumnModel().getColumn(6).setCellRenderer(tcr);
		        model.addRow(row);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		
		}
}
