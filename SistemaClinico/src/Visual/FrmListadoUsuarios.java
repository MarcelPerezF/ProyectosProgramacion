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
import Logico.Medico;
import Logico.Usuario;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JPanel pnListadoEnfermedades;
	private int opcion;
	
	//btnAuxiliar para saber si se quiere ver detallado o si se quiere ver listado para seleccionar.
	private JButton btnAuxiliar;
	private Usuario usuarioSeleccionado;
	
	
	public static void main(String[] args) {
		try {
			Usuario usuario = new Medico("M1", "402", 1, "1", "2", "Carl", "829", "RD", "", "Masculino", "Cirujano");
			Clinica.getInstance().insertarUsuario(usuario);
			FrmListadoUsuarios dialog = new FrmListadoUsuarios(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrmListadoUsuarios(int opcionListado) {
		opcion = opcionListado;
		//El parametro opcion es para saber si se desea ver el listado o si se desea seleccionar
		//Opcion 1: Ver listado.
		//Opcion 2: Ver listado (PARA SELECCIONAR).
		
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(opcion!=2) {
					int aux = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de usuarios?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(aux==0) {
						setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.OK_OPTION);
					}else if(aux==1) {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el usuario", "Seleccionar", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		setTitle("Listado de Usuarios");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
				btnAuxiliar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						FrmListadoUsuariosDetallado frmAux = new FrmListadoUsuariosDetallado(usuarioSeleccionado);
						frmAux.setVisible(true);
					}
				});
				if(opcion==1) {
					btnAuxiliar.setText("Ver m\u00e1s");
				}else if(opcion==2){
					btnAuxiliar.setText("Seleccionar");
				}
				btnAuxiliar.setEnabled(false);
				btnAuxiliar.setBounds(487, 16, 111, 28);
				btnAuxiliar.setActionCommand("OK");
				bnBotones.add(btnAuxiliar);
				getRootPane().setDefaultButton(btnAuxiliar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBounds(634, 16, 81, 28);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de usuarios?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo del listado de usuarios", "Saliendo", JOptionPane.OK_OPTION);
							dispose();
						}
					}
				});
				btnSalir.setActionCommand("Cancel");
				bnBotones.add(btnSalir);
			}
			{
				JLabel lblCantidadUsuarios = new JLabel("Cantidad de usuarios:");
				lblCantidadUsuarios.setBounds(15, 20, 201, 20);
				bnBotones.add(lblCantidadUsuarios);
			}
			
			txtCantidadUsuarios = new JTextField();
			txtCantidadUsuarios.setEditable(false);
			txtCantidadUsuarios.setBounds(205, 19, 213, 23);
			bnBotones.add(txtCantidadUsuarios);
			txtCantidadUsuarios.setColumns(10);
		}
		
		pnListadoEnfermedades = new JPanel();
		pnListadoEnfermedades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListadoEnfermedades.setBounds(12, 148, 730, 370);
		getContentPane().add(pnListadoEnfermedades);
		pnListadoEnfermedades.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrlpnListadoUsuarios = new JScrollPane();
			pnListadoEnfermedades.add(scrlpnListadoUsuarios, BorderLayout.CENTER);
			
			tblListadoUsuarios = new JTable();
			tblListadoUsuarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectRow = tblListadoUsuarios.getSelectedRow();
					usuarioSeleccionado = null;
					if(selectRow!=-1) {
						usuarioSeleccionado = Clinica.getInstance().buscarUsuario(String.valueOf(tblListadoUsuarios.getValueAt(selectRow, 2)));
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
			}else if(opcion==2){
				String[] headers = {"Codigo","Nombre","Cedula", "Genero", "Tipo"};
				model.setColumnIdentifiers(headers);
			}
			tblListadoUsuarios.setRowHeight(25);
			tblListadoUsuarios.setModel(model);
			tblListadoUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlpnListadoUsuarios.setViewportView(tblListadoUsuarios);
			
		}
		loadTablaUsuarios();
	}
	public void loadTablaUsuarios() {
		model.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		for (int i = 0; i<Clinica.getInstance().getMisUsuarios().size(); i++) {
            row[0] = Clinica.getInstance().getMisUsuarios().get(i).getCodigoUsuario();
            tblListadoUsuarios.getColumnModel().getColumn(0).setCellRenderer(tcr);
            
            row[1] = Clinica.getInstance().getMisUsuarios().get(i).getNombre();
            tblListadoUsuarios.getColumnModel().getColumn(1).setCellRenderer(tcr);

            row[2] = Clinica.getInstance().getMisUsuarios().get(i).getCedulaUsuario();
            tblListadoUsuarios.getColumnModel().getColumn(2).setCellRenderer(tcr);

            row[3] = Clinica.getInstance().getMisUsuarios().get(i).getGenero();
            tblListadoUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr);
            
            row[4] = Clinica.getInstance().tipoUsuario(Clinica.getInstance().getMisUsuarios().get(i));
            tblListadoUsuarios.getColumnModel().getColumn(4).setCellRenderer(tcr);
            
            if(opcion==1) {
	            row[5] = Clinica.getInstance().getMisUsuarios().get(i).getUsuario();
	            tblListadoUsuarios.getColumnModel().getColumn(5).setCellRenderer(tcr);
	            
	            row[6] = Clinica.getInstance().getMisUsuarios().get(i).getPassword();
	            tblListadoUsuarios.getColumnModel().getColumn(6).setCellRenderer(tcr);
            }
            model.addRow(row);			
		}
		txtCantidadUsuarios.setText(""+Clinica.getInstance().getMisUsuarios().size());
		
	}
}
