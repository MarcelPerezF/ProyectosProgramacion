package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
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

import Logico.Clinica;
import Logico.Enfermedad;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmListadoEnfermedad extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenEnfermedad= new ImageIcon(FrmListadoEnfermedad.class.getResource("Imagenes/Enfermedad2.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenEnfermedad2= new ImageIcon(FrmListadoEnfermedad.class.getResource("Imagenes/Enfermedad2.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTable tblListadoEnfermedades;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField txtCantEnfermedades;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JPanel pnListadoEnfermedades;
	private boolean lista = false;
	private String code="";
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JComboBox cbxTipo;
	
	
	//el parametro listado es falso cuando solo quiere ver la lista, de lo contrario es verdadero
	public FrmListadoEnfermedad(boolean listado, int opcion) {
		lista = listado;
		//Si la opcion es 2 es porque desde la consulta se diagnosticara
		
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de enfermedades?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo del listado de enfermedades", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setTitle("Lista de Enfermedades");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 780, 742);
		setIconImage(imagenEnfermedad);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 23, 730, 112);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagenPaciente = new JLabel("");
		lblImagenPaciente.setBounds(33, 13, 88, 86);
		lblImagenPaciente.setIcon(new ImageIcon(imagenEnfermedad2));
		contentPanel.add(lblImagenPaciente);
		{
			JLabel lblListaDePacientes = new JLabel("Lista de Enfermedades");
			lblListaDePacientes.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblListaDePacientes.setBounds(295, 13, 195, 26);
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
			FechaFormulario.setBounds(313, 37, 158, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblListadoDeEnfermedades = new JLabel("Listado de enfermedades en el sistema");
		lblListadoDeEnfermedades.setBounds(264, 70, 238, 26);
		contentPanel.add(lblListadoDeEnfermedades);
		{
			JPanel bnBotones = new JPanel();
			bnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			bnBotones.setBounds(12, 614, 730, 60);
			getContentPane().add(bnBotones);
			bnBotones.setLayout(null);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Enfermedad aux = Clinica.getInstance().buscarEnfermedad(code);
						if(opcion==1) {
							FrmIngresarEnfermedad auxAct = new FrmIngresarEnfermedad(aux);
							dispose();
							auxAct.setVisible(true);
						}else if(opcion==2) {
							FrmConsulta.enfermedad = aux;
							dispose();
						}
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setBounds(487, 16, 111, 28);
				btnSeleccionar.setActionCommand("OK");
				bnBotones.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			{
				btnCancelar = new JButton("Salir");
				btnCancelar.setBounds(634, 16, 81, 28);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de enfermedades?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de enfermedades", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnCancelar.setActionCommand("Cancel");
				bnBotones.add(btnCancelar);
			}
			{
				JLabel lblCantidadEnfermedades = new JLabel("Cantidad de Enfermedades:");
				lblCantidadEnfermedades.setBounds(15, 20, 201, 20);
				bnBotones.add(lblCantidadEnfermedades);
			}
			
			txtCantEnfermedades = new JTextField();
			txtCantEnfermedades.setEditable(false);
			txtCantEnfermedades.setBounds(205, 19, 213, 23);
			txtCantEnfermedades.setText(""+Clinica.getInstance().getMisEnfermedades().size()+" enfermedades en el sistema");
			bnBotones.add(txtCantEnfermedades);
			txtCantEnfermedades.setColumns(10);
		}
		
		pnListadoEnfermedades = new JPanel();
		pnListadoEnfermedades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListadoEnfermedades.setBounds(12, 148, 730, 453);
		getContentPane().add(pnListadoEnfermedades);
		pnListadoEnfermedades.setLayout(null);
		{
			JScrollPane scrlpnListadoEnfermedades = new JScrollPane();
			scrlpnListadoEnfermedades.setBounds(0, 103, 728, 350);
			pnListadoEnfermedades.add(scrlpnListadoEnfermedades);
			
			tblListadoEnfermedades = new JTable();
			tblListadoEnfermedades.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(lista == true) {
						int aux = tblListadoEnfermedades.getSelectedRow();
						if(aux!=-1){
							btnSeleccionar.setEnabled(true);
							code = (String) tblListadoEnfermedades.getValueAt(aux, 0);
						}
					}
				}
			});
			
			model = new DefaultTableModel();
			String[] headers = {"Codigo","Nombre","Tipo","Decripcion"};
			model.setColumnIdentifiers(headers);
			tblListadoEnfermedades.setRowHeight(25);
			tblListadoEnfermedades.setModel(model);
			tblListadoEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlpnListadoEnfermedades.setViewportView(tblListadoEnfermedades);
			
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 706, 75);
		pnListadoEnfermedades.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Tipo de busqueda:");
		label.setBounds(12, 26, 133, 23);
		panel.add(label);
		
		cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEnfermedades(1,"");
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Nombre", "Tipo"}));
		cbxTipo.setBounds(138, 26, 140, 23);
		panel.add(cbxTipo);
		
		txtBusqueda = new JTextField();
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
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(356, 26, 202, 23);
		panel.add(txtBusqueda);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEnfermedades(cbxTipo.getSelectedIndex()+2, txtBusqueda.getText());
			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(570, 22, 124, 30);
		panel.add(btnBuscar);
		loadEnfermedades(1,"");
	}
	public void loadEnfermedades(int tipo, String busqueda) {
		int i=0;
		model.setRowCount(0);
		for(Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			switch (tipo) {
			case 1:
				cargarFilas(enfermedad);
				i++;
				break;
			case 2:
				if(enfermedad.getCodigoEnfermedad().equalsIgnoreCase(busqueda)) {
					cargarFilas(enfermedad);
					i++;
				}
				break;
			case 3:
				if(enfermedad.getNombreEnfermedad().equalsIgnoreCase(busqueda)) {
					cargarFilas(enfermedad);
					i++;
				}
				break;
			case 4:
				if(enfermedad.getTipoEnfermedad().equalsIgnoreCase(busqueda)) {
					cargarFilas(enfermedad);
					i++;
				}
				break;
			}
		}
		txtCantEnfermedades.setText(""+i+" usuarios");
	}
	public void cargarFilas(Enfermedad enf) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		try {
			row[0] = enf.getCodigoEnfermedad();
			tblListadoEnfermedades.getColumnModel().getColumn(0).setCellRenderer(tcr);
			row[1] = enf.getNombreEnfermedad();
			tblListadoEnfermedades.getColumnModel().getColumn(1).setCellRenderer(tcr);
			row[2] = enf.getTipoEnfermedad();
			tblListadoEnfermedades.getColumnModel().getColumn(2).setCellRenderer(tcr);
			row[3] = enf.getDescripcionEnfermedad();
			tblListadoEnfermedades.getColumnModel().getColumn(3).setCellRenderer(tcr);
			model.addRow(row);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
