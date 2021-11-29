package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Logico.Clinica;
import Logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FrmIngresarVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;

	private Image imagenVacuna= new ImageIcon(FrmIngresarVacuna.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacuna2= new ImageIcon(FrmIngresarVacuna.class.getResource("Imagenes/Vacunas.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigoVacuna;
	private JTextField txtNombreVacuna;
	private JTextField txtTipoVacuna;
	private JSpinner spnCantidadVacunas;
	private JButton btnSalir;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JTextArea txtDescripcionVacuna;
	private boolean b1=false,b2=false,b3=false,b4=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmIngresarVacuna dialog = new FrmIngresarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmIngresarVacuna() {
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de ingresar vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
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
		setBounds(100, 100, 500, 550);
		setLocationRelativeTo(null);
		setIconImage(imagenVacuna);
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new LineBorder(new Color(0,0,0), 1));
		panelHeader.setBackground(UIManager.getColor("Button.light"));
		panelHeader.setBounds(10, 10, 463, 113);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblImagenCitas = new JLabel("");
		lblImagenCitas.setBounds(15, 19, 80, 80);
		lblImagenCitas.setIcon(new ImageIcon(imagenVacuna2));
		panelHeader.add(lblImagenCitas);
		
		JLabel lblTituloFormulario = new JLabel("INGRESO DE VACUNAS");
		lblTituloFormulario.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloFormulario.setBounds(170, 19, 187, 20);
		panelHeader.add(lblTituloFormulario);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();

		JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		lblFechaActualFormulario.setBounds(183, 47, 187, 25);
		panelHeader.add(lblFechaActualFormulario);
		
		JLabel lblDescripcionFormulario = new JLabel("Formulario para ingresar vacunas al sistema");
		lblDescripcionFormulario.setBounds(137, 77, 311, 20);
		panelHeader.add(lblDescripcionFormulario);
		
		JPanel panelBody = new JPanel();
		panelBody.setBackground(UIManager.getColor("CheckBox.light"));
		panelBody.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBody.setBounds(10, 139, 463, 281);
		contentPanel.add(panelBody);
		panelBody.setLayout(null);
		
		JLabel lblCodigoVacuna = new JLabel("C\u00F3digo Vacuna:");
		lblCodigoVacuna.setBounds(15, 16, 138, 20);
		panelBody.add(lblCodigoVacuna);
		
		txtCodigoVacuna = new JTextField();
		txtCodigoVacuna.setText(Clinica.getInstance().generarCodigoVacuna());
		txtCodigoVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigoVacuna.setEditable(false);
		txtCodigoVacuna.setBounds(164, 16, 165, 23);
		panelBody.add(txtCodigoVacuna);
		txtCodigoVacuna.setColumns(10);
		
		JLabel lblNombreVacuna = new JLabel("Nombre de vacuna:");
		lblNombreVacuna.setBounds(15, 54, 150, 20);
		panelBody.add(lblNombreVacuna);
		
		txtNombreVacuna = new JTextField();
		txtNombreVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b1=true;
				if(b1&&b2&&b3&&b4) {
					btnGuardar.setEnabled(true);
				}
			}
		});
		txtNombreVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombreVacuna.setColumns(10);
		txtNombreVacuna.setBounds(164, 52, 284, 23);
		panelBody.add(txtNombreVacuna);;
		
		JLabel lblEnfermedad = new JLabel("Tipo de vacuna:");
		lblEnfermedad.setBounds(15, 129, 138, 20);
		panelBody.add(lblEnfermedad);
		
		txtTipoVacuna = new JTextField();
		txtTipoVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b3=true;
				if(b1&&b2&&b3&&b4) {
					btnGuardar.setEnabled(true);
				}
			}
		});
		txtTipoVacuna.setColumns(10);
		txtTipoVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtTipoVacuna.setBounds(164, 127, 284, 23);
		panelBody.add(txtTipoVacuna);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(15, 91, 138, 20);
		panelBody.add(lblCantidad);
		
		spnCantidadVacunas = new JSpinner();
		spnCantidadVacunas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btnLimpiar.setEnabled(true);
				b2=true;
				if(b1&&b2&&b3&&b4) {
					btnGuardar.setEnabled(true);
				}
			}
		});
		spnCantidadVacunas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantidadVacunas.setBounds(164, 90, 284, 23);
		panelBody.add(spnCantidadVacunas);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n Vacuna:");
		lblDescripcion.setBounds(15, 205, 150, 20);
		panelBody.add(lblDescripcion);
		
		JScrollPane scrlpDescripcion = new JScrollPane();
		scrlpDescripcion.setBounds(164, 165, 284, 100);
		panelBody.add(scrlpDescripcion);
		
		txtDescripcionVacuna = new JTextArea();
		txtDescripcionVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b4=true;
				if(b1&&b2&&b3&&b4) {
					btnGuardar.setEnabled(true);
				}
			}
		});
		scrlpDescripcion.setViewportView(txtDescripcionVacuna);
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFooter.setBackground(UIManager.getColor("CheckBox.light"));
		panelFooter.setBounds(10, 432, 463, 62);
		contentPanel.add(panelFooter);
		panelFooter.setLayout(null);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		btnLimpiar.setEnabled(false);
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBackground(UIManager.getColor("Button.light"));
		btnLimpiar.setBounds(15, 17, 122, 29);
		panelFooter.add(btnLimpiar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vacuna aux = new Vacuna(txtCodigoVacuna.getText(),txtNombreVacuna.getText()
						,Integer.valueOf(spnCantidadVacunas.getValue().toString()),txtTipoVacuna.getText(),txtDescripcionVacuna.getText());
				Clinica.getInstance().insertarVacuna(aux);
				JOptionPane.showMessageDialog(null, "La vacuna se guardo", "Información",JOptionPane.INFORMATION_MESSAGE);
				clean();
			}
		});
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setBounds(170, 17, 122, 29);
		panelFooter.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					JOptionPane.showMessageDialog(null, "Saliendo de ingresar vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBackground(UIManager.getColor("Button.light"));
		btnSalir.setBounds(326, 17, 122, 29);
		panelFooter.add(btnSalir);
	}

	private void clean() {
		txtCodigoVacuna.setText(Clinica.getInstance().generarCodigoVacuna());
		txtNombreVacuna.setText("");
		txtTipoVacuna.setText("");
		txtDescripcionVacuna.setText("");
		spnCantidadVacunas.setValue(1);
		btnGuardar.setEnabled(false);
		btnLimpiar.setEnabled(false);
		b1=false;
		b2=false;
		b3=false;
		b4=false;
	}
	
}
