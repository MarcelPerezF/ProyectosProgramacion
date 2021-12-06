package Visual;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import Logico.Clinica;
import Logico.Paciente;
import Logico.Vacuna;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListadoVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenVacuna= new ImageIcon(FrmListadoVacuna.class.getResource("Imagenes/Vacuna.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacuna2= new ImageIcon(FrmListadoVacuna.class.getResource("Imagenes/Vacuna.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private boolean lista;
	private JTextField txtNumeroVacunas;
	private JTextField txtBusqueda;
	private JTable tblListadoVacuna;
	private JComboBox cbxTipoBusqueda;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnBuscar;
	private Vacuna vacunaSeleccionada;
	private JButton btnSelecciona;
	private JButton btnSalir;
	private int opcion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmListadoVacuna dialog = new FrmListadoVacuna(true,1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	//el parametro listado es falso cuando solo quiere ver la lista, de lo contrario es verdadero
	//la opcion 1 es para vacunar, la opcion 2 es para solicitar mas vacuna;
	public FrmListadoVacuna(boolean listado,int op) {
		opcion=op;
		lista=listado;
		vacunaSeleccionada=null;
		//Para controlar el boton de close.
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							JOptionPane.showMessageDialog(null, "Saliendo del listado de vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							if(vacunaSeleccionada==null) {
								FrmVacunar.vacunaAplicar=null;
							}
						}else if(opcion==1) {
							setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						}
					}
				});
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Listado de Vacuna");
		setBounds(100, 100, 754, 587);
		setLocationRelativeTo(null);
		setIconImage(imagenVacuna);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 13, 710, 117);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblImagenVacuna = new JLabel("");
			lblImagenVacuna.setBounds(12, 13, 88, 87);
			lblImagenVacuna.setIcon(new ImageIcon(imagenVacuna2));
			contentPanel.add(lblImagenVacuna);
		}
		{
			JLabel lblNewLabel = new JLabel("Lista de Vacunas");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(282, 23, 145, 27);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblListadoDeVacunas = new JLabel("Listado de vacunas en el sistema");
			lblListadoDeVacunas.setBounds(257, 78, 195, 26);
			contentPanel.add(lblListadoDeVacunas);
		}
		{
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			
			JLabel label = new JLabel(dia+" de "+nombreMes+" del "+year);
			label.setBounds(276, 51, 158, 26);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setBounds(12, 481, 710, 51);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnSelecciona = new JButton("Selecciona");
				btnSelecciona.setEnabled(false);
				btnSelecciona.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(vacunaSeleccionada!=null) {
							if(opcion==1) {
								if(vacunaSeleccionada.getCantidadVacunas()>1) {
									FrmVacunar.vacunaAplicar = vacunaSeleccionada;
									dispose();
								}else {
									FrmVacunar.vacunaAplicar = null;
									JOptionPane.showMessageDialog(null, "No se puede aplicar esta vacuna debido a que no hay suficientes!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								}
							}else {
								dispose();
								FrmSolicitarVacuna aux = new FrmSolicitarVacuna(vacunaSeleccionada);
								aux.setVisible(true);
							}
						}
					}
				});
				btnSelecciona.setBounds(451, 11, 111, 28);
				btnSelecciona.setActionCommand("OK");
				buttonPane.add(btnSelecciona);
				getRootPane().setDefaultButton(btnSelecciona);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBounds(574, 11, 111, 28);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
							if(vacunaSeleccionada==null) {
								FrmVacunar.vacunaAplicar=null;
							}
							dispose();
						}
					}
				});
				buttonPane.add(btnSalir);
			}
			{
				JLabel lblCantidadDeVacunas = new JLabel("Cantidad de vacunas:");
				lblCantidadDeVacunas.setBounds(12, 15, 201, 20);
				buttonPane.add(lblCantidadDeVacunas);
			}
			{
				txtNumeroVacunas = new JTextField();
				txtNumeroVacunas.setText("");
				txtNumeroVacunas.setEditable(false);
				txtNumeroVacunas.setColumns(10);
				txtNumeroVacunas.setBounds(154, 14, 146, 23);
				buttonPane.add(txtNumeroVacunas);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel.setBounds(12, 143, 710, 325);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(12, 13, 686, 66);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel label = new JLabel("Tipo de busqueda:");
					label.setBounds(12, 21, 133, 23);
					panel_1.add(label);
				}
				
				cbxTipoBusqueda = new JComboBox();
				cbxTipoBusqueda.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						loadVacuna(0,"");
					}
				});
				cbxTipoBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Nombre", "Tipo"}));
				cbxTipoBusqueda.setBounds(131, 21, 140, 23);
				panel_1.add(cbxTipoBusqueda);
				
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
				txtBusqueda.setBounds(320, 21, 202, 23);
				panel_1.add(txtBusqueda);
				
				btnBuscar = new JButton("Buscar");
				btnBuscar.setEnabled(false);
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadVacuna(cbxTipoBusqueda.getSelectedIndex()+1, txtBusqueda.getText());
					}
				});
				btnBuscar.setBounds(534, 18, 140, 30);
				panel_1.add(btnBuscar);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 92, 710, 233);
			panel.add(scrollPane);
			
			tblListadoVacuna = new JTable();
			tblListadoVacuna.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = tblListadoVacuna.getSelectedRow();
					if(selectRow!=-1) {
						vacunaSeleccionada = Clinica.getInstance().buscarVacuna(String.valueOf(tblListadoVacuna.getValueAt(selectRow, 0)));
					}
					if(vacunaSeleccionada!=null) {
						btnSelecciona.setEnabled(true);
					}
				}
			});
			tblListadoVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tblListadoVacuna);
			model = new DefaultTableModel();
			String[] headers = {"Codigo","Nombre","Tipo", "Cantidad", "Descripcion"};
			model.setColumnIdentifiers(headers);
			tblListadoVacuna.setRowHeight(25);
			tblListadoVacuna.setModel(model);
		}
		loadVacuna(0,"");
		
	}

	private void loadVacuna(int opcion, String busqueda) {
		model.setRowCount(0);
		int i=0;
		boolean noPuesta=true;
		for(Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			noPuesta=true;//Para que no le salga una vacuna ya puesta al paciente
			if(FrmConsulta.paciente!=null) {
				for(Vacuna vacunita : FrmConsulta.paciente.getHistorial().getMisVacunas()) {
					if(vacuna==vacunita) {
						noPuesta=false;
					}
				}
			}
			for(Vacuna vacunita : FrmVacunar.vacuna) {
				if(vacuna==vacunita) {
					noPuesta=false;
				}
			}
			if(noPuesta) {
				switch(opcion) {
				case 0:
					cargafilas(vacuna);
					i++;
					break;
				case 1:
					if(vacuna.getCodigoVacunacion().equalsIgnoreCase(busqueda)) {
						cargafilas(vacuna);
					}
					i++;
					break;
				case 2:
					if(vacuna.getNombreVacunacion().equalsIgnoreCase(busqueda)) {
						cargafilas(vacuna);
					}
					i++;
					break;
				case 3:
					if(vacuna.getTipoVacuna().equalsIgnoreCase(busqueda)) {
						cargafilas(vacuna);
					}
					i++;
					break;
			}
			}
		}
		txtNumeroVacunas.setText(""+i+" vacunas");
	}

	private void cargafilas(Vacuna vacuna) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		try {
			row[0] = vacuna.getCodigoVacunacion();
			tblListadoVacuna.getColumnModel().getColumn(0).setCellRenderer(tcr);
			
			row[1] = vacuna.getNombreVacunacion();
			tblListadoVacuna.getColumnModel().getColumn(1).setCellRenderer(tcr);
			
			row[2] = vacuna.getTipoVacuna();
			tblListadoVacuna.getColumnModel().getColumn(2).setCellRenderer(tcr);
			
			row[3] = vacuna.getCantidadVacunas();
			tblListadoVacuna.getColumnModel().getColumn(3).setCellRenderer(tcr);
			
			row[4] = vacuna.getDescripcionVacuna();
			tblListadoVacuna.getColumnModel().getColumn(4).setCellRenderer(tcr);

			model.addRow(row);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
