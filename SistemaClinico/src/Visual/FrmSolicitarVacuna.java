package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Dialog.ModalityType;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Logico.Clinica;
import Logico.Vacuna;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmSolicitarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int sizeIcon = 35;
	private Image imagenVacuna = new ImageIcon(FrmCita.class.getResource("Imagenes/Vacunas3.png")).getImage().getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);
	private Image imagenVacuna2 = new ImageIcon(FrmCita.class.getResource("Imagenes/Vacunas3.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtTipo;
	private JTextField txtCantActual;
	private JButton btnSalir;
	private Vacuna vacunaSolicitar;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public FrmSolicitarVacuna(Vacuna vacuna) {
		vacunaSolicitar=vacuna;
		setTitle("Solicitar Vacuna");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea solicitar vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo de solicitar vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setIconImage(imagenVacuna);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 462, 421);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(12, 121, 418, 190);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo de la vacuna:");
		lblNewLabel.setBounds(12, 13, 128, 16);
		contentPanel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(158, 10, 128, 22);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		{
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(158, 45, 194, 22);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblNombreDeLa = new JLabel("Nombre de la vacuna:");
			lblNombreDeLa.setBounds(12, 48, 128, 16);
			contentPanel.add(lblNombreDeLa);
		}
		{
			txtTipo = new JTextField();
			txtTipo.setEditable(false);
			txtTipo.setColumns(10);
			txtTipo.setBounds(158, 80, 194, 22);
			contentPanel.add(txtTipo);
		}
		{
			JLabel lblTipoDeLa = new JLabel("Tipo de la vacuna:");
			lblTipoDeLa.setBounds(12, 83, 128, 16);
			contentPanel.add(lblTipoDeLa);
		}
		{
			txtCantActual = new JTextField();
			txtCantActual.setEditable(false);
			txtCantActual.setBounds(158, 115, 128, 22);
			contentPanel.add(txtCantActual);
			txtCantActual.setColumns(10);
		}
		{
			JLabel lblCantidadActualDe = new JLabel("Cantidad actual:");
			lblCantidadActualDe.setBounds(12, 118, 105, 16);
			contentPanel.add(lblCantidadActualDe);
		}
		
		JSpinner spnSolicitar = new JSpinner();
		spnSolicitar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnSolicitar.setBounds(158, 150, 128, 22);
		contentPanel.add(spnSolicitar);
		
		JLabel lblCantidadSolicitar = new JLabel("Cantidad  solicitar:");
		lblCantidadSolicitar.setBounds(12, 153, 128, 16);
		contentPanel.add(lblCantidadSolicitar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setBounds(84, 324, 288, 35);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnSolicitar = new JButton("Solicitar");
				btnSolicitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Clinica.getInstance().solicitarVacuna(vacunaSolicitar, Integer.valueOf(spnSolicitar.getValue().toString()));
							JOptionPane.showMessageDialog(null, "Solicitud aprobada", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Error en guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						dispose();
					}
				});
				btnSolicitar.setBounds(45, 5, 89, 25);
				btnSolicitar.setActionCommand("OK");
				buttonPane.add(btnSolicitar);
				getRootPane().setDefaultButton(btnSolicitar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de que no desea solicitar vacunas?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							JOptionPane.showMessageDialog(null, "Saliendo de solicitar vacunas", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
						}else if(opcion==1) {
							setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						}
					}
				});
				btnSalir.setBounds(175, 5, 71, 25);
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(12, 13, 418, 95);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(22, 6, 91, 82);
		lblImagen.setIcon(new ImageIcon(imagenVacuna2));
		panel.add(lblImagen);
		{
			JLabel lblSolicitudDeVacunas = new JLabel("SOLICITUD DE VACUNAS");
			lblSolicitudDeVacunas.setFont(new Font("Arial", Font.BOLD, 16));
			lblSolicitudDeVacunas.setBounds(115, 13, 212, 20);
			panel.add(lblSolicitudDeVacunas);
		}
		{
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
			int dia = LocalDate.now().getDayOfMonth();
			Month mes = LocalDate.now().getMonth();
			String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year = LocalDate.now().getYear();
			JLabel lblFechaActualFormulario = new JLabel(dia+" de "+nombreMes+" del "+year);
			lblFechaActualFormulario.setBounds(141, 36, 161, 25);
			panel.add(lblFechaActualFormulario);
		}
		{
			JLabel lblFormularioParaSolicitar = new JLabel("Formulario para solicitar vacunas");
			lblFormularioParaSolicitar.setBounds(115, 62, 212, 20);
			panel.add(lblFormularioParaSolicitar);
		}
		loadVacuna();
	}

	private void loadVacuna() {
		txtCodigo.setText(vacunaSolicitar.getCodigoVacunacion());
		txtNombre.setText(vacunaSolicitar.getNombreVacunacion());
		txtTipo.setText(vacunaSolicitar.getTipoVacuna());
		txtCantActual.setText(""+vacunaSolicitar.getCantidadVacunas());
	}
}
