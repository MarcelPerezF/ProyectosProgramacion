package Visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Clinica;
import Logico.Empleado;
import Logico.Medico;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class FrmInformacionClinica extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Image imagenInformacionClinica = new ImageIcon(FrmInformacionClinica.class.getResource("Imagenes/InfoSistema.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Image imagenInformacionClinica2 = new ImageIcon(FrmInformacionClinica.class.getResource("Imagenes/InfoSistema.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private JPanel pnInformacion;
	private JLabel lblCantidadUsuarios;
	private JLabel lblCantidadDeMedicos;
	private JTextField txtCantidadUsuarios;
	private JTextField txtCantidadMedicos;
	private JTextField txtCantidadAdministradores;
	private JTextField txtCantidadSecretarias;
	private JLabel lblCantidadSecretarias;
	private JLabel lblCantidadAdministradores;
	private JLabel lblCantidadPacientesHombre;
	private JTextField txtCantidadPacientesHombre;
	private JPanel pnPacientes;
	private JPanel pnOtros;
	private JLabel lblCantidadVacunas;
	private JTextField txtCantidadVacunas;
	private JTextField txtCantidadVacunasAplicadas;
	private JLabel lblCantidadVacunasAplicadas;
	private JLabel lblCantidadCitas;
	private JTextField txtCantidadCitas;
	private JLabel lblCantidadConsultas;
	private JTextField txtCantidadConsultas;
	private JLabel lblCantidadPacientesMujer;
	private JTextField txtCantidadPacientesMujer;
	private JLabel lblCantidadEnfermedades;
	private JTextField txtCantidadEnfermedades;

	public FrmInformacionClinica() {
		//Para controlar el boton de close.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Est\u00e1s seguro de deseas salir de ver la información de la clínica?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					JOptionPane.showMessageDialog(null, "Saliendo del formulario de la información de la clínica", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
				}else if(opcion==1) {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		setTitle(".:. Informaci\u00F3n de la Clinica .:.");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 600, 600);
		setIconImage(imagenInformacionClinica);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0,0,0), 1));
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(15, 16, 557, 86);
		contentPanel.add(panel);
		
		JLabel label = new JLabel(new ImageIcon(imagenInformacionClinica2));
		label.setBounds(15, 10, 60, 60);
		panel.add(label);
		
		JLabel lblInformacinDeLa = new JLabel("INFORMACI\u00D3N DE LA CL\u00CDNICA");
		lblInformacinDeLa.setFont(new Font("Arial", Font.BOLD, 16));
		lblInformacinDeLa.setBounds(175, 10, 241, 20);
		panel.add(lblInformacinDeLa);
		
		JLabel label_2 = new JLabel("29 de noviembre del 2021");
		label_2.setBounds(220, 28, 187, 25);
		panel.add(label_2);
		
		JLabel lblFormularioDeInformacin = new JLabel("Formulario de informaci\u00F3n de la cl\u00EDnica");
		lblFormularioDeInformacin.setBounds(180, 50, 280, 20);
		panel.add(lblFormularioDeInformacin);
		
		pnInformacion = new JPanel();
		pnInformacion.setBorder(new TitledBorder(null, "Informaci\u00F3n de Cl\u00EDnica", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnInformacion.setBounds(15, 110, 557, 434);
		contentPanel.add(pnInformacion);
		pnInformacion.setLayout(null);
		
		JPanel pnUsuarios = new JPanel();
		pnUsuarios.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnUsuarios.setBounds(15, 27, 527, 135);
		pnInformacion.add(pnUsuarios);
		pnUsuarios.setLayout(null);
		
		lblCantidadUsuarios = new JLabel("Cantidad de usuarios:");
		lblCantidadUsuarios.setBounds(15, 36, 154, 20);
		pnUsuarios.add(lblCantidadUsuarios);
		
		lblCantidadDeMedicos = new JLabel("Cantidad de medicos:");
		lblCantidadDeMedicos.setBounds(277, 36, 154, 20);
		pnUsuarios.add(lblCantidadDeMedicos);
		
		txtCantidadUsuarios = new JTextField();
		txtCantidadUsuarios.setEditable(false);
		txtCantidadUsuarios.setBounds(190, 35, 72, 23);
		pnUsuarios.add(txtCantidadUsuarios);
		txtCantidadUsuarios.setColumns(10);
		
		txtCantidadMedicos = new JTextField();
		txtCantidadMedicos.setEditable(false);
		txtCantidadMedicos.setColumns(10);
		txtCantidadMedicos.setBounds(423, 35, 72, 23);
		pnUsuarios.add(txtCantidadMedicos);
		
		lblCantidadAdministradores = new JLabel("Cantidad de Administradores:");
		lblCantidadAdministradores.setBounds(15, 75, 173, 20);
		pnUsuarios.add(lblCantidadAdministradores);
		
		txtCantidadAdministradores = new JTextField();
		txtCantidadAdministradores.setEditable(false);
		txtCantidadAdministradores.setColumns(10);
		txtCantidadAdministradores.setBounds(190, 74, 72, 23);
		pnUsuarios.add(txtCantidadAdministradores);
		
		lblCantidadSecretarias = new JLabel("Cantidad de secretarias:");
		lblCantidadSecretarias.setBounds(277, 75, 154, 20);
		pnUsuarios.add(lblCantidadSecretarias);
		
		txtCantidadSecretarias = new JTextField();
		txtCantidadSecretarias.setEditable(false);
		txtCantidadSecretarias.setColumns(10);
		txtCantidadSecretarias.setBounds(423, 74, 72, 23);
		pnUsuarios.add(txtCantidadSecretarias);
		
		pnPacientes = new JPanel();
		pnPacientes.setLayout(null);
		pnPacientes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnPacientes.setBounds(15, 173, 527, 68);
		pnInformacion.add(pnPacientes);
		
		lblCantidadPacientesHombre = new JLabel("Pacientes masculinos:");
		lblCantidadPacientesHombre.setBounds(15, 29, 173, 20);
		pnPacientes.add(lblCantidadPacientesHombre);
		
		txtCantidadPacientesHombre = new JTextField();
		txtCantidadPacientesHombre.setEditable(false);
		txtCantidadPacientesHombre.setColumns(10);
		txtCantidadPacientesHombre.setBounds(190, 28, 72, 23);
		pnPacientes.add(txtCantidadPacientesHombre);
		
		lblCantidadPacientesMujer = new JLabel("Pacientes: Femeninos");
		lblCantidadPacientesMujer.setBounds(277, 27, 173, 20);
		pnPacientes.add(lblCantidadPacientesMujer);
		
		txtCantidadPacientesMujer = new JTextField();
		txtCantidadPacientesMujer.setEditable(false);
		txtCantidadPacientesMujer.setColumns(10);
		txtCantidadPacientesMujer.setBounds(423, 26, 72, 23);
		pnPacientes.add(txtCantidadPacientesMujer);
		
		pnOtros = new JPanel();
		pnOtros.setLayout(null);
		pnOtros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Otros", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnOtros.setBounds(15, 257, 527, 161);
		pnInformacion.add(pnOtros);
		
		lblCantidadVacunas = new JLabel("Cantidad de Vacunas:");
		lblCantidadVacunas.setBounds(15, 39, 173, 20);
		pnOtros.add(lblCantidadVacunas);
		
		txtCantidadVacunas = new JTextField();
		txtCantidadVacunas.setEditable(false);
		txtCantidadVacunas.setColumns(10);
		txtCantidadVacunas.setBounds(190, 38, 72, 23);
		pnOtros.add(txtCantidadVacunas);
		
		txtCantidadVacunasAplicadas = new JTextField();
		txtCantidadVacunasAplicadas.setEditable(false);
		txtCantidadVacunasAplicadas.setColumns(10);
		txtCantidadVacunasAplicadas.setBounds(423, 39, 72, 23);
		pnOtros.add(txtCantidadVacunasAplicadas);
		
		lblCantidadVacunasAplicadas = new JLabel("Vacunas aplicadas:");
		lblCantidadVacunasAplicadas.setBounds(277, 40, 173, 20);
		pnOtros.add(lblCantidadVacunasAplicadas);
		
		lblCantidadCitas = new JLabel("Cantidad de Citas:");
		lblCantidadCitas.setBounds(15, 76, 173, 20);
		pnOtros.add(lblCantidadCitas);
		
		txtCantidadCitas = new JTextField();
		txtCantidadCitas.setEditable(false);
		txtCantidadCitas.setColumns(10);
		txtCantidadCitas.setBounds(190, 75, 72, 23);
		pnOtros.add(txtCantidadCitas);
		
		lblCantidadConsultas = new JLabel("Cantidad Consultas:");
		lblCantidadConsultas.setBounds(277, 77, 173, 20);
		pnOtros.add(lblCantidadConsultas);
		
		txtCantidadConsultas = new JTextField();
		txtCantidadConsultas.setEditable(false);
		txtCantidadConsultas.setColumns(10);
		txtCantidadConsultas.setBounds(423, 76, 72, 23);
		pnOtros.add(txtCantidadConsultas);
		
		lblCantidadEnfermedades = new JLabel("Cantidad de Enfermedades:");
		lblCantidadEnfermedades.setBounds(15, 113, 173, 20);
		pnOtros.add(lblCantidadEnfermedades);
		
		txtCantidadEnfermedades = new JTextField();
		txtCantidadEnfermedades.setEditable(false);
		txtCantidadEnfermedades.setColumns(10);
		txtCantidadEnfermedades.setBounds(190, 112, 72, 23);
		pnOtros.add(txtCantidadEnfermedades);
		loadDatos();
	}
	
	public void loadDatos() {
		
		txtCantidadUsuarios.setText(""+Clinica.getInstance().getMisUsuarios().size());
		int cantidadMedicos=0, cantidadAdministradores=0, cantidadSecretarias=0;
		for (int i = 0; i < Clinica.getInstance().getMisUsuarios().size(); i++) {
			if(Clinica.getInstance().getMisUsuarios().get(i) instanceof Medico) {
				cantidadMedicos++;
			}
			if(Clinica.getInstance().getMisUsuarios().get(i) instanceof Empleado) {
				Empleado empleadoAux = (Empleado) Clinica.getInstance().getMisUsuarios().get(i);
				if(empleadoAux.getPuestoLaboral().equalsIgnoreCase("Administrador")) {
					cantidadAdministradores++;
				}else {
					cantidadSecretarias++;
				}
			}			
		}
		txtCantidadMedicos.setText(""+cantidadMedicos);
		txtCantidadAdministradores.setText(""+cantidadAdministradores);
		txtCantidadSecretarias.setText(""+cantidadSecretarias);
		txtCantidadPacientesHombre.setText(""+Clinica.getInstance().getMisPacientes().size());

		int cantidadConsultas = 0, cantidadVacunasAplicadas = 0, cantidadPacienteHombre=0, cantidadPacienteMujer=0;
		for (int i = 0; i < Clinica.getInstance().getMisPacientes().size(); i++) {
			cantidadConsultas+=Clinica.getInstance().getMisPacientes().get(i).getMisConsultas().size();
			cantidadVacunasAplicadas+=Clinica.getInstance().getMisPacientes().get(i).getHistorial().getMisVacunas().size();
			if(Clinica.getInstance().getMisPacientes().get(i).getGenero().equalsIgnoreCase("Hombre")) {
				cantidadPacienteHombre++;
			}else {
				cantidadPacienteMujer++;
			}
		}
		txtCantidadPacientesHombre.setText(""+cantidadPacienteHombre);
		txtCantidadPacientesMujer.setText(""+cantidadPacienteMujer);
		
		int cantidadVacunas=0;
		for(int i=0; i<Clinica.getInstance().getMisVacunas().size(); i++) {
			cantidadVacunas=Clinica.getInstance().getMisVacunas().get(i).getCantidadVacunas();
		}
	
		txtCantidadVacunas.setText(""+cantidadVacunas);
		txtCantidadVacunasAplicadas.setText(""+cantidadVacunasAplicadas);
		txtCantidadCitas.setText(""+Clinica.getInstance().getMisCitasMedicas().size());
		txtCantidadConsultas.setText(""+cantidadConsultas);
		txtCantidadEnfermedades.setText(""+Clinica.getInstance().getMisEnfermedades().size());
		
	}
	
}
