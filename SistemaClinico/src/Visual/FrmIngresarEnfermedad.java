package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Logico.Clinica;
import Logico.Enfermedad;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmIngresarEnfermedad extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenEnfermedad= new ImageIcon(FrmIngresarEnfermedad.class.getResource("Imagenes/Enfermedad.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenEnfermedad2= new ImageIcon(FrmIngresarEnfermedad.class.getResource("Imagenes/Enfermedad.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JButton btnSalir;
	private JButton btnIngresar;
	private JTextField txtCodigoEnfermedad;
	private JTextField txtNombreEnfermedad;
	private JTextField txtTipoEnfermedad;
	private JEditorPane txtDescripcion;
	private JButton btnLimpiar;
	private boolean b1=false;
	private boolean b2=false;
	private boolean b3=false;
	private Enfermedad nuevo=null;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	//se ingresa una enfermedad para modificar
	public FrmIngresarEnfermedad(Enfermedad ne) {
		nuevo=ne;
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar m\u00e1s enfermedades?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de ingresar enfermedades", "Saliendo", JOptionPane.OK_OPTION);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}		
		});
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Enfermedad");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 500, 530);
		setIconImage(imagenEnfermedad);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(12, 13, 455, 130);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnEncabezado = new JPanel();
		pnEncabezado.setBackground(Color.WHITE);
		contentPanel.add(pnEncabezado, BorderLayout.CENTER);
		pnEncabezado.setLayout(null);
		
		JLabel lblImagenEnfermedad = new JLabel("");
		lblImagenEnfermedad.setBounds(15, 19, 80, 80);
		lblImagenEnfermedad.setIcon(new ImageIcon(imagenEnfermedad2));
		pnEncabezado.add(lblImagenEnfermedad);
		
		JLabel lblNewLabel = new JLabel("");
		if(nuevo==null) {
			lblNewLabel = new JLabel("Ingreso de Enfermedades");
		}else {
			lblNewLabel = new JLabel("Actualizacion de Enfermedades");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(168, 19, 262, 26);
		pnEncabezado.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		if(nuevo==null) {
			lblNewLabel_1 = new JLabel("Formulario para ingresar enfermedades al sistema");
		}else {
			lblNewLabel_1 = new JLabel("Formulario para actualizar enfermedades al sistema");
		}
		lblNewLabel_1.setBounds(131, 83, 299, 16);
		pnEncabezado.add(lblNewLabel_1);
		
		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int dia = LocalDate.now().getDayOfMonth();
		Month mes = LocalDate.now().getMonth();
		String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int year = LocalDate.now().getYear();
		
		JLabel FechaFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
		FechaFormulario.setBounds(192, 54, 177, 16);
		pnEncabezado.add(FechaFormulario);
		{
			JPanel pnBotones = new JPanel();
			pnBotones.setBounds(12, 418, 455, 62);
			pnBotones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			getContentPane().add(pnBotones);
			pnBotones.setLayout(null);
			{
				btnIngresar = new JButton("");
				if(nuevo==null) {
					btnIngresar = new JButton("Ingresar");
				}else {
					btnIngresar = new JButton("Actualizar");
				}
				btnIngresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Enfermedad auxEnf = new Enfermedad(txtCodigoEnfermedad.getText(),txtNombreEnfermedad.getText()
								,txtTipoEnfermedad.getText(),txtDescripcion.getText());
						if(nuevo==null) {
							Clinica.getInstance().insertarEnfermedades(auxEnf);
							clean();
							JOptionPane.showMessageDialog(null, "La enfermedad se ingreso", "Información",JOptionPane.INFORMATION_MESSAGE);
						}else {
							Clinica.getInstance().actualizarEnfermedad(auxEnf);
							JOptionPane.showMessageDialog(null, "La enfermedad se actualizo", "Información",JOptionPane.INFORMATION_MESSAGE);
							FrmListadoEnfermedad auxLis = new FrmListadoEnfermedad(true);
							dispose();
							auxLis.setVisible(true);
						}
					}
				});
				btnIngresar.setEnabled(false);
				btnIngresar.setBounds(180, 16, 94, 30);
				btnIngresar.setActionCommand("OK");
				pnBotones.add(btnIngresar);
				getRootPane().setDefaultButton(btnIngresar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setBounds(346, 16, 94, 30);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion=0;
						if(nuevo==null) {
							opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea ingresar m\u00e1s enfermedades?", "Confirmar", JOptionPane.YES_NO_OPTION);
						}else {
							opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea actualizar la enfermedad?", "Confirmar", JOptionPane.YES_NO_OPTION);
						}
						if(opcion==0) {
							JOptionPane.showMessageDialog(null, "Saliendo de ingresar enfermedades", "Saliendo", JOptionPane.OK_OPTION);
							dispose();
						}
					}
				});
				btnSalir.setActionCommand("Cancel");
				pnBotones.add(btnSalir);
			}
			
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clean();
				}
			});
			btnLimpiar.setEnabled(false);
			btnLimpiar.setActionCommand("OK");
			btnLimpiar.setBounds(15, 14, 94, 30);
			pnBotones.add(btnLimpiar);
		}
		
		JPanel pnCampos = new JPanel();
		pnCampos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnCampos.setBounds(12, 159, 455, 243);
		getContentPane().add(pnCampos);
		pnCampos.setLayout(null);
		
		JLabel lblCodigoEnfermedad = new JLabel("Codigo de la Enfermedad:");
		lblCodigoEnfermedad.setBounds(12, 13, 162, 22);
		pnCampos.add(lblCodigoEnfermedad);
		
		txtCodigoEnfermedad = new JTextField();
		txtCodigoEnfermedad.setEditable(false);
		txtCodigoEnfermedad.setBounds(192, 13, 116, 22);
		txtCodigoEnfermedad.setText(Clinica.getInstance().generarCodigoEnfermedad());
		pnCampos.add(txtCodigoEnfermedad);
		txtCodigoEnfermedad.setColumns(10);
		
		JLabel lblNombreEnfermedad = new JLabel("Nombre de la Enfermedad:");
		lblNombreEnfermedad.setBounds(12, 57, 162, 22);
		pnCampos.add(lblNombreEnfermedad);
		
		txtNombreEnfermedad = new JTextField();
		txtNombreEnfermedad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b1=true;
				if((b1&&b2&&b3)||nuevo!=null) {
					btnIngresar.setEnabled(true);
				}
			}
		});
		txtNombreEnfermedad.setColumns(10);
		txtNombreEnfermedad.setBounds(192, 57, 251, 22);
		pnCampos.add(txtNombreEnfermedad);
		
		JLabel lblTipoEnfermedad = new JLabel("Tipo de la Enfermedad:");
		lblTipoEnfermedad.setBounds(12, 103, 162, 22);
		pnCampos.add(lblTipoEnfermedad);
		
		txtTipoEnfermedad = new JTextField();
		txtTipoEnfermedad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b2=true;
				if(b1&&b2&&b3||nuevo!=null) {
					btnIngresar.setEnabled(true);
				}
			}
		});
		txtTipoEnfermedad.setColumns(10);
		txtTipoEnfermedad.setBounds(192, 103, 251, 22);
		pnCampos.add(txtTipoEnfermedad);
		
		JLabel lblDescripcionEnfermedad = new JLabel("Descripcion de la Enfermedad:");
		lblDescripcionEnfermedad.setBounds(12, 145, 175, 22);
		pnCampos.add(lblDescripcionEnfermedad);
		
		JPanel pnDescripcionEnfermedad = new JPanel();
		pnDescripcionEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnDescripcionEnfermedad.setBounds(192, 145, 251, 87);
		pnCampos.add(pnDescripcionEnfermedad);
		pnDescripcionEnfermedad.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrlpnDescripcionEnfermedad = new JScrollPane();
		scrlpnDescripcionEnfermedad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnDescripcionEnfermedad.add(scrlpnDescripcionEnfermedad, BorderLayout.CENTER);
		
		txtDescripcion = new JEditorPane();
		txtDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnLimpiar.setEnabled(true);
				b3=true;
				if(b1&&b2&&b3||nuevo!=null) {
					btnIngresar.setEnabled(true);
				}
			}
		});
		txtDescripcion.setSize(201, 80);
		scrlpnDescripcionEnfermedad.setViewportView(txtDescripcion);
		if(nuevo !=null) {
			loadEnfermedad();
		}
	}
	
	private void loadEnfermedad() {
		txtCodigoEnfermedad.setText(nuevo.getCodigoEnfermedad());
		txtDescripcion.setText(nuevo.getDescripcionEnfermedad());
		txtNombreEnfermedad.setText(nuevo.getNombreEnfermedad());
		txtTipoEnfermedad.setText(nuevo.getTipoEnfermedad());
		btnIngresar.setEnabled(false);
		btnLimpiar.setEnabled(false);
		
	}

	public void clean() {
		if(nuevo !=null) {
			txtCodigoEnfermedad.setText(nuevo.getCodigoEnfermedad());
		}else {
			txtCodigoEnfermedad.setText(Clinica.getInstance().generarCodigoEnfermedad());
		}
		txtDescripcion.setText("");
		txtNombreEnfermedad.setText("");
		txtTipoEnfermedad.setText("");
		b1=false;
		b2=false;
		b3=false;
		btnIngresar.setEnabled(false);
		btnLimpiar.setEnabled(false);
	}
}
