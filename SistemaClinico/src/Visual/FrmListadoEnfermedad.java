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
import javax.swing.JTextField;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmListadoEnfermedad dialog = new FrmListadoEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmListadoEnfermedad() {
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que desea salir del listado de enfermedades?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo del listado de enfermedades", "Saliendo", JOptionPane.OK_OPTION);
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
		setBounds(100, 100, 780, 650);
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
			bnBotones.setBounds(12, 534, 730, 60);
			getContentPane().add(bnBotones);
			bnBotones.setLayout(null);
			{
				btnSeleccionar = new JButton("Seleccionar");
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
							JOptionPane.showMessageDialog(null, "Saliendo del listado de enfermedades", "Saliendo", JOptionPane.OK_OPTION);
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
			txtCantEnfermedades.setText("4 enfermedades en el sistema");
			bnBotones.add(txtCantEnfermedades);
			txtCantEnfermedades.setColumns(10);
		}
		
		pnListadoEnfermedades = new JPanel();
		pnListadoEnfermedades.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnListadoEnfermedades.setBounds(12, 148, 730, 370);
		getContentPane().add(pnListadoEnfermedades);
		pnListadoEnfermedades.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrlpnListadoEnfermedades = new JScrollPane();
			pnListadoEnfermedades.add(scrlpnListadoEnfermedades, BorderLayout.CENTER);
			
			tblListadoEnfermedades = new JTable();
			model = new DefaultTableModel();
			String[] headers = {"Codigo","Nombre","Tipo","Decripcion"};
			model.setColumnIdentifiers(headers);
			tblListadoEnfermedades.setRowHeight(25);
			tblListadoEnfermedades.setModel(model);
			tblListadoEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrlpnListadoEnfermedades.setViewportView(tblListadoEnfermedades);
			
		}
		loadEnfermedades();
	}
	public void loadEnfermedades() {
		model.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		row = new Object[model.getColumnCount()];
		int i=0;
		for (i = 0; i < 5; i++) {
            row[0] = "E"+(i+1);
            tblListadoEnfermedades.getColumnModel().getColumn(0).setCellRenderer(tcr);
            
            row[1] = "Covid";
            tblListadoEnfermedades.getColumnModel().getColumn(1).setCellRenderer(tcr);

            row[2] = "Contagioso";
            tblListadoEnfermedades.getColumnModel().getColumn(2).setCellRenderer(tcr);

            row[3] = "Covid-19";
            tblListadoEnfermedades.getColumnModel().getColumn(3).setCellRenderer(tcr);
            model.addRow(row);
		}
		
	}
}
