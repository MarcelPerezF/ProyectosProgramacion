package Visual;

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
import javax.swing.table.TableCellEditor;

import Logico.Clinica;
import Logico.Empleado;
import Logico.Medico;
import Logico.Usuario;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmListadoUsuarios extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenListadoUsuarios= new ImageIcon(FrmListadoUsuarios.class.getResource("Imagenes/ListadoUsuarios.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenListadoUsuarios2= new ImageIcon(FrmListadoUsuarios.class.getResource("Imagenes/ListadoUsuarios.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTable tblListadoUsuarios;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField txtCantidadUsuarios;
	private JButton btnSalir;
	private JPanel pnListadoUsuarios;
	private int opcion;
	
	//btnAuxiliar para saber si se quiere ver detallado o si se quiere ver listado para seleccionar.
	private JButton btnAuxiliar;
	private Usuario usuarioSeleccionado;
	private JButton btnBuscar;
	private JTextField txtBusqueda;
	private JComboBox cbxTipoBusqueda;
	private JComboBox cbxBusqueda;
	private String especialidad;

	public FrmListadoUsuarios(int opcionListado, String especial) {
		opcion = opcionListado;
		especialidad = especial;
		//El parametro especialidad es para buscar el medico
		//El parametro opcion es para saber si se desea ver el listado o si se desea seleccionar
		//Opcion 1: Ver listado.
		//Opcion 2: Ver listado (PARA SELECCIONAR).
		//Opcion 3: Ver listado (PARA SELECCIONAR).
		//Opcion 4: Ver listado (PARA SELECCIONAR MEDICO).
		
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(opcion==1||opcion==4) {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de usuarios?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}else {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea no desea modificar al usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}
			}
		});
		setTitle("Listado de Usuarios");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 780, 650);
		setIconImage(imagenListadoUsuarios);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(12, 23, 730, 112);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(33, 13, 88, 86);
		lblImagen.setIcon(new ImageIcon(imagenListadoUsuarios2));
		contentPanel.add(lblImagen);
		{
			JLabel lblTituloFormulario = new JLabel("Listado de Usuarios");
			lblTituloFormulario.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTituloFormulario.setBounds(307, 13, 195, 26);
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
			FechaFormulario.setBounds(313, 45, 158, 26);
			contentPanel.add(FechaFormulario);
		}
		
		JLabel lblDescripcionFormulario = new JLabel("Listado de usuarios en el sistema");
		lblDescripcionFormulario.setBounds(289, 73, 238, 26);
		contentPanel.add(lblDescripcionFormulario);
		{
			JPanel bnBotones = new JPanel();
			bnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			bnBotones.setBounds(12, 534, 730, 60);
			getContentPane().add(bnBotones);
			bnBotones.setLayout(null);
			{
				btnAuxiliar = new JButton("");
				//Boton de seleccionar o ver mas.
				btnAuxiliar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(opcion==1) {
							dispose();
							FrmListadoUsuariosDetallado frmAux = new FrmListadoUsuariosDetallado(usuarioSeleccionado);
							frmAux.setVisible(true);
						}else if(opcion==4){
							FrmCita.medicoCita = (Medico) usuarioSeleccionado;
							dispose();
						}else{
							dispose();
							FrmIngresarUsuario frmAux = new FrmIngresarUsuario(opcionListado, usuarioSeleccionado, 0);
							frmAux.setVisible(true);
						}
					}
				});
				
				if(opcion==1) {
					btnAuxiliar.setText("Ver m\u00e1s");
				}else if(opcion!=1){
					btnAuxiliar.setText("Seleccionar");
				}
				btnAuxiliar.setEnabled(false);
				btnAuxiliar.setBounds(500, 16, 111, 28);
				bnBotones.add(btnAuxiliar);
				getRootPane().setDefaultButton(btnAuxiliar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBounds(634, 16, 81, 28);
				if(opcion!=1&&opcion!=4) {
					btnSalir.setEnabled(false);
				}
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(opcion==1||opcion==4) {
							int opcion2 = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de usuarios?", "Confirmar", JOptionPane.YES_NO_OPTION);
							if(opcion2==0) {
								JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}
					}
				});
				bnBotones.add(btnSalir);
			}
			{
				JLabel lblCantidadUsuarios = new JLabel("Cantidad de usuarios:");
				lblCantidadUsuarios.setBounds(15, 20, 201, 20);
				bnBotones.add(lblCantidadUsuarios);
			}
			
			txtCantidadUsuarios = new JTextField();
			txtCantidadUsuarios.setEditable(false);
			txtCantidadUsuarios.setBounds(205, 19, 146, 23);
			bnBotones.add(txtCantidadUsuarios);
			txtCantidadUsuarios.setColumns(10);
		}
		
		pnListadoUsuarios = new JPanel();
		pnListadoUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListadoUsuarios.setBounds(12, 148, 730, 370);
		getContentPane().add(pnListadoUsuarios);
		pnListadoUsuarios.setLayout(null);
		{
			JScrollPane scrlpnListadoUsuarios = new JScrollPane();
			scrlpnListadoUsuarios.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			scrlpnListadoUsuarios.setBounds(1, 97, 728, 272);
			pnListadoUsuarios.add(scrlpnListadoUsuarios);
			
			tblListadoUsuarios = new JTable();
			tblListadoUsuarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = tblListadoUsuarios.getSelectedRow();
					usuarioSeleccionado = null;
					if(selectRow!=-1) {
						if(opcion!=4) {
							usuarioSeleccionado = Clinica.getInstance().buscarUsuario(String.valueOf(tblListadoUsuarios.getValueAt(selectRow, 2)));
						}else {
							usuarioSeleccionado = Clinica.getInstance().buscarUsuarioPorCodigo(String.valueOf(tblListadoUsuarios.getValueAt(selectRow, 0)));
						}
					}
					if(usuarioSeleccionado!=null) {
						btnAuxiliar.setEnabled(true);
					}
				}
			});
			model = new DefaultTableModel();
			if(opcion==1) {
				String[] headers = {"Codigo","Nombre","Cedula", "Genero", "Tipo", "Usuario", "Contrase\u00f1a"};
				model.setColumnIdentifiers(headers);
			}else if(opcion==4) {
				String[] headers = {"Codigo","Nombre","Especialidad","Telefono", "Genero"};
				model.setColumnIdentifiers(headers);
			}else if(opcion!=1){
				String[] headers = {"Codigo","Nombre","Cedula", "Genero", "Tipo"};
				model.setColumnIdentifiers(headers);
			}
			tblListadoUsuarios.setRowHeight(25);
			tblListadoUsuarios.setModel(model);
			tblListadoUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlpnListadoUsuarios.setViewportView(tblListadoUsuarios);
			
		}
		
		JPanel pnBusqueda = new JPanel();
		pnBusqueda.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnBusqueda.setBounds(15, 10, 700, 75);
		pnListadoUsuarios.add(pnBusqueda);
		pnBusqueda.setLayout(null);
		
		JLabel lblBusqueda = new JLabel("Tipo de busqueda:");
		lblBusqueda.setBounds(15, 29, 133, 23);
		pnBusqueda.add(lblBusqueda);
		
		cbxTipoBusqueda = new JComboBox();
		if(opcion!=4) {
			cbxTipoBusqueda.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre", "C\u00E9dula", "Tipo Usuario", "Genero"}));
			cbxTipoBusqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( cbxTipoBusqueda.getSelectedIndex()==0 || cbxTipoBusqueda.getSelectedIndex()==1 || cbxTipoBusqueda.getSelectedIndex()==2 ) {
						txtBusqueda.setVisible(true);
						cbxBusqueda.setVisible(false);
						txtBusqueda.setText("");
						btnBuscar.setEnabled(false);
					}
					if(cbxTipoBusqueda.getSelectedIndex()==3) {
						txtBusqueda.setVisible(false);
						cbxBusqueda.setVisible(true);
						cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Medico", "Administrador", "Secretaria"}));
						btnBuscar.setEnabled(true);
					}
					if(cbxTipoBusqueda.getSelectedIndex()==4) {
						txtBusqueda.setVisible(false);
						cbxBusqueda.setVisible(true);
						cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
						btnBuscar.setEnabled(true);
					}
					loadTablaUsuarios(1, "");

				}
			});
		}else {
			cbxTipoBusqueda.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Nombre", "Genero"}));
			cbxTipoBusqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( cbxTipoBusqueda.getSelectedIndex()==0 || cbxTipoBusqueda.getSelectedIndex()==1) {
						txtBusqueda.setVisible(true);
						cbxBusqueda.setVisible(false);
						txtBusqueda.setText("");
						btnBuscar.setEnabled(false);
					}
					if(cbxTipoBusqueda.getSelectedIndex()==2) {
						txtBusqueda.setVisible(false);
						cbxBusqueda.setVisible(true);
						cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
						btnBuscar.setEnabled(true);
					}
					loadTablaUsuarios(1, "");
				}
			});
		}
		cbxTipoBusqueda.setBounds(150, 29, 140, 23);
		pnBusqueda.add(cbxTipoBusqueda);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(330, 29, 202, 23);
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
		pnBusqueda.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		if(opcion!=4) {
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcionAux = cbxTipoBusqueda.getSelectedIndex();
					switch (opcionAux) {
					case 0:
						loadTablaUsuarios(2, txtBusqueda.getText());
						break;
					case 1:
						loadTablaUsuarios(3, txtBusqueda.getText());
						break;
					case 2:
						loadTablaUsuarios(4, txtBusqueda.getText());
						break;
					case 3:
						loadTablaUsuarios(5, cbxBusqueda.getSelectedItem().toString());
						break;
					case 4:
						loadTablaUsuarios(6, cbxBusqueda.getSelectedItem().toString());
						break;

					default:
						break;
					}
				}
			});
		}else {
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcionAux = cbxTipoBusqueda.getSelectedIndex();
					switch (opcionAux) {
					case 0:
						loadTablaUsuarios(2, txtBusqueda.getText());
						break;
					case 1:
						loadTablaUsuarios(3, txtBusqueda.getText());
						break;
					case 2:
						loadTablaUsuarios(4, cbxBusqueda.getSelectedItem().toString());
						break;
					}
				}
			});
		}
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(545, 25, 140, 30);
		pnBusqueda.add(btnBuscar);
		
		cbxBusqueda = new JComboBox();
		cbxBusqueda.setVisible(false);
		cbxBusqueda.setBounds(330, 29, 202, 23);
		pnBusqueda.add(cbxBusqueda);
		loadTablaUsuarios(1, "");
	}
	
	public void loadTablaUsuarios(int opcionBusqueda, String busqueda) {
		model.setRowCount(0);
		int cantidadUsuarios = 0;
		Medico aux = null;
		if(opcion!=4) {
			for (Usuario usuario : Clinica.getInstance().getMisUsuarios()) {
				switch (opcionBusqueda) {
				case 1:
					cargarFilas(opcion, usuario);	
					cantidadUsuarios++;
					break;
				
				case 2:
					if(usuario.getCodigoUsuario().equalsIgnoreCase(busqueda)) {
						cargarFilas(opcion, usuario);	
						cantidadUsuarios++;
					}
					break;
				
				case 3:
					if(usuario.getNombre().equalsIgnoreCase(busqueda)) {
						cargarFilas(opcion, usuario);	
						cantidadUsuarios++;
					}
					break;
				
				case 4:
					if(usuario.getCedulaUsuario().equalsIgnoreCase(busqueda)) {
						cargarFilas(opcion, usuario);	
						cantidadUsuarios++;
					}
					break;
				
				case 5:
					if(Clinica.getInstance().tipoUsuario(usuario).equalsIgnoreCase(busqueda)) {
						cargarFilas(opcion, usuario);	
						cantidadUsuarios++;
					}
					break;
				
				case 6:
					if(usuario.getGenero().equalsIgnoreCase(busqueda)) {
						cargarFilas(opcion, usuario);	
						cantidadUsuarios++;
					}
					break;
				}
			}
		}else {
			for (Usuario usuario : Clinica.getInstance().getMisUsuarios()) {
				if(usuario instanceof Medico) {
					aux = (Medico) usuario;
					switch (opcionBusqueda) {
					case 1:
						if(Clinica.getInstance().tipoUsuario(usuario).equalsIgnoreCase("medico")) {
							if(aux.getEspecialidad().equalsIgnoreCase(especialidad)) {
								cargarFilas(opcion, usuario);	
								cantidadUsuarios++;
							}
						}
						break;
					
					case 2:
						if(Clinica.getInstance().tipoUsuario(usuario).equalsIgnoreCase("medico")) {
							if(aux.getEspecialidad().equalsIgnoreCase(especialidad)) {
								if(usuario.getCodigoUsuario().equalsIgnoreCase(busqueda)) {
									cargarFilas(opcion, usuario);	
									cantidadUsuarios++;
								}
							}
							break;
						}
						break;
						
					case 3:
						if(Clinica.getInstance().tipoUsuario(usuario).equalsIgnoreCase("medico")) {
							if(aux.getEspecialidad().equalsIgnoreCase(especialidad)) {
								if(usuario.getNombre().equalsIgnoreCase(busqueda)) {
									cargarFilas(opcion, usuario);	
									cantidadUsuarios++;
								}
							}
						}
						break;
						
					case 4:
						if(Clinica.getInstance().tipoUsuario(usuario).equalsIgnoreCase("medico")) {
							if(aux.getEspecialidad().equalsIgnoreCase(especialidad)) {
								if(usuario.getGenero().equalsIgnoreCase(busqueda)) {
									cargarFilas(opcion, usuario);	
									cantidadUsuarios++;
								}
							}
						}
						break;
					}
				}
			}
		}
		txtCantidadUsuarios.setText(""+cantidadUsuarios+" usuarios");
	}

	public void cargarFilas(int opcion2, Usuario usuario) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		Medico aux = null; 
		try {
			if(opcion!=4) {
				row[0] = usuario.getCodigoUsuario();
		        tblListadoUsuarios.getColumnModel().getColumn(0).setCellRenderer(tcr);
		        
		        row[1] = usuario.getNombre();
		        tblListadoUsuarios.getColumnModel().getColumn(1).setCellRenderer(tcr);

		        row[2] = usuario.getCedulaUsuario();
		        tblListadoUsuarios.getColumnModel().getColumn(2).setCellRenderer(tcr);

		        row[3] = usuario.getGenero();
		        tblListadoUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr);
		        
		        row[4] = Clinica.getInstance().tipoUsuario(usuario);
		        tblListadoUsuarios.getColumnModel().getColumn(4).setCellRenderer(tcr);
		        
		        if(opcion==1) {
		            row[5] = usuario.getUsuario();
		            tblListadoUsuarios.getColumnModel().getColumn(5).setCellRenderer(tcr);
		            
		            row[6] = usuario.getPassword();
		            tblListadoUsuarios.getColumnModel().getColumn(6).setCellRenderer(tcr);
		        }
			}else {
				aux = (Medico) usuario;
				row[0] = aux.getCodigoUsuario();
		        tblListadoUsuarios.getColumnModel().getColumn(0).setCellRenderer(tcr);
		        
		        row[1] = aux.getNombre();
		        tblListadoUsuarios.getColumnModel().getColumn(1).setCellRenderer(tcr);

		        row[2] = aux.getEspecialidad();
		        tblListadoUsuarios.getColumnModel().getColumn(2).setCellRenderer(tcr);

		        row[3] = aux.getTelefono();
		        tblListadoUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr);
		        
		        row[4] = usuario.getGenero();
		        tblListadoUsuarios.getColumnModel().getColumn(4).setCellRenderer(tcr);
			}
	        model.addRow(row);	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	
	}
}
