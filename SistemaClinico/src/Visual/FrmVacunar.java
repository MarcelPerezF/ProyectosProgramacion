package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Logico.Clinica;
import Logico.Paciente;
import Logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmVacunar extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;

	private Image imagenVacuna= new ImageIcon(FrmVacunar.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacuna2= new ImageIcon(FrmVacunar.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JButton btnSalir;
	private JButton btnGuardar;
	private JButton btnVacuna;
	public static Vacuna vacunaAplicar;
	private ArrayList<Vacuna> vacuna;
	private static DefaultTableModel modeloVacunas;
	private static Object[] rowVacunas;
	private JTable tblVacunas;
	private JButton btnEliminar;
	private Vacuna auxVacuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Paciente paciente1 = new Paciente("1", "402", "Marc", "Hombre", new Date(102, 8, 6), "RD", "829",
					"marc@", "Ninguna", "Dominicano", "Soltero(a)", "Catolico", "A+", "Estudiante");
			Clinica.getInstance().insertarPaciente(paciente1);
			Vacuna vacuna = new Vacuna("V-1", "COVID", 2, "CONTAGIOSA", "DI");
			Clinica.getInstance().insertarVacuna(vacuna);
			
			FrmVacunar dialog = new FrmVacunar(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmVacunar(ArrayList<Vacuna> v1) {
		if(v1!=null) {
			vacuna=v1;
		}
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea vacunar el paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de vacunar pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Vacunas");
		setBounds(100, 100, 595, 500);
		setLocationRelativeTo(null);
		setIconImage(imagenVacuna);
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new LineBorder(new Color(0,0,0), 1));
		panelHeader.setBackground(UIManager.getColor("Button.light"));
		panelHeader.setBounds(10, 10, 551, 113);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblImagenCitas = new JLabel("");
		lblImagenCitas.setBounds(15, 19, 80, 80);
		lblImagenCitas.setIcon(new ImageIcon(imagenVacuna2));
		panelHeader.add(lblImagenCitas);
		
		JLabel lblTituloFormulario = new JLabel("VACUNACI\u00D3N DE PACIENTES");
		lblTituloFormulario.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloFormulario.setBounds(154, 19, 233, 20);
		panelHeader.add(lblTituloFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();

		JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaActualFormulario.setBounds(177, 47, 187, 25);
		panelHeader.add(lblFechaActualFormulario);
		
		JLabel lblDescripcionFormulario = new JLabel("Formulario para vacunaci\u00F3n de pacientes");
		lblDescripcionFormulario.setBounds(134, 77, 288, 20);
		panelHeader.add(lblDescripcionFormulario);
		
		JPanel panelBody = new JPanel();
		panelBody.setBackground(UIManager.getColor("CheckBox.light"));
		panelBody.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBody.setBounds(10, 139, 551, 221);
		contentPanel.add(panelBody);
		panelBody.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Vacunas a suministrar:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 527, 195);
		panelBody.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tblVacunas = new JTable();
		tblVacunas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = tblVacunas.getSelectedRow();
				if(selectRow!=-1) {
					String code = String.valueOf(tblVacunas.getValueAt(selectRow, 0));
					auxVacuna=Clinica.getInstance().buscarVacuna(code);
					btnEliminar.setEnabled(true);
				}else {
					btnEliminar.setEnabled(false);
				}
			}
		});
		modeloVacunas = new DefaultTableModel();
		String[] columnasVacunas = {"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		modeloVacunas.setColumnIdentifiers(columnasVacunas);
		tblVacunas.setModel(modeloVacunas);
		tblVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblVacunas);;
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(56, 382, 476, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vacunaAplicar.getCantidadVacunas()>1) {
					try {
						FrmConsulta.vacunas=vacuna;
						JOptionPane.showMessageDialog(null, "Se ha ingresado las vacunas al paciente de manera satisfactoria!", "VACUNACI\u00d3N EXITOSA",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "No se puede aplicar esta vacuna debido a que no hay suficientes!", "ERROR EN VACUNACI\u00d3N ",
							JOptionPane.OK_OPTION);
					dispose();
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setBounds(258, 17, 89, 29);
		panelFooter.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea vacunar al paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de vacunar pacientes", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBackground(UIManager.getColor("Button.light"));
		btnSalir.setBounds(379, 17, 89, 29);
		panelFooter.add(btnSalir);
		
		btnVacuna = new JButton("Vacunas");
		btnVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoVacuna frmAux = new FrmListadoVacuna(true,1);
				frmAux.setVisible(true);
				if(vacunaAplicar!=null) {;
					btnGuardar.setEnabled(true);
				}
			}
		});
		btnVacuna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVacuna.setBackground(UIManager.getColor("Button.light"));
		btnVacuna.setBounds(12, 17, 90, 29);
		panelFooter.add(btnVacuna);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				boolean aprobado=false;
				for(Vacuna v3 : vacuna) {
					if(v3==auxVacuna) {
						aprobado=true;
					}
					if(!aprobado) {
						i++;
					}
				}
				vacuna.remove(i);
				btnEliminar.setEnabled(false);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBackground(SystemColor.controlHighlight);
		btnEliminar.setBounds(137, 17, 89, 29);
		panelFooter.add(btnEliminar);
		loadVacunas();
	}

	private void loadVacunas() {
		modeloVacunas.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		rowVacunas = new Object[modeloVacunas.getColumnCount()];
		//{"Código de Vacuna","Nombre de Vacuna", "Tipo de Vacuna", "Descripción"};
		if(vacuna!=null) {
			for (Vacuna v2 : vacuna) {
				
				rowVacunas[0] = v2.getCodigoVacunacion();
		        tblVacunas.getColumnModel().getColumn(0).setCellRenderer(tcr);
		        
		        rowVacunas[1] = v2.getNombreVacunacion();
		        tblVacunas.getColumnModel().getColumn(1).setCellRenderer(tcr);
		        
		        rowVacunas[2] = v2.getTipoVacuna();
		        tblVacunas.getColumnModel().getColumn(2).setCellRenderer(tcr);
		        
		        rowVacunas[3] = v2.getDescripcionVacuna();
		        tblVacunas.getColumnModel().getColumn(3).setCellRenderer(tcr);
		        
		        modeloVacunas.addRow(rowVacunas);
			}
		}
	}
}
