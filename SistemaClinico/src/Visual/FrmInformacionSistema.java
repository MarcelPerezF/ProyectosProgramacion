package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmInformacionSistema extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAceptar;
	private JTextArea txtDescripcion;
	private Image imagenInformacionSistema = new ImageIcon(FrmInformacionSistema.class.getResource("Imagenes/InfoSistema.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	
	public FrmInformacionSistema() {
		setTitle(".:. Informaci\u00F3n del Sistema .:.");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 563, 410);
		setIconImage(imagenInformacionSistema);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrlInfoSistema = new JScrollPane();
		scrlInfoSistema.setBounds(15, 16, 521, 294);
		contentPanel.add(scrlInfoSistema);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		txtDescripcion.setText("\tInformaci\u00F3n del Sistema Cl\u00EDnico\r\n+Para comenzar a utilizar el sistema, en primer lugar se deber\u00E1 crear un \r\nusuario que sera el administrador.\r\n\r\n+El sistema funciona para ingresar consultas y vacunas de pacientes.\r\n\r\n+Si una persona que no es Paciente va a consultar, esta debe ser ingresada\r\ncomo paciente antes de la consulta.\r\n\r\n+El administrador tendra acceso a todos los elementos del sistema.\r\n\r\n+La secretaria tendra acceso a crear nuevas citas e ingresar pacientes.\r\n\r\n+Los m\u00E9dicos tendran acceso a consultar y vacunar.\r\n\r\n+Si tiene cualquier duda respecto al sistema, debe contactarse con los\r\ndesarrolladores del sistema en cuesti\u00F3n.\r\n\r\n+Este sistema fue desarrollado como proyecto final para la materia \r\ncon nombre \"Programaci\u00F3n 1 - POO\".\r\n\r\n+El sistema fue desarrollado por Marcel P\u00E9rez y Orlando Moronta.");
		scrlInfoSistema.setViewportView(txtDescripcion);
		{
			JPanel pnBotones = new JPanel();
			pnBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnBotones, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				pnBotones.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}
	}
}
