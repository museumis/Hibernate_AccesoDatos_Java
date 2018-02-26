package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

public class FramePrincipal {
	/**
	 * Variables de la clase
	 */
	JFrame frame;
	JPanel panelBorderLayout, panelArbol,panelDerecha;

	JMenuItem itemTextoSimple;
	JButton btnTextoSimple;
	BufferedImage canvas;

	JPanel panelInteraccion, panelTabla;
	JTable tabla;
	MiModelo miModelo;

	/**
	 * Contructor
	 */
	public FramePrincipal() {
		frame = new JFrame("");
		frame.setBounds(200, 200, 1100, 600);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Procedimiento que inicia la interfaz
	 */
	public void iniciar() {
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}

	/**
	 * Procedimiento que genera los componentes de la interfaz
	 */
	public void iniciarComponentes() {

		gestionFrame();

		generarNemotecnicosBase();
		generarArbol();
		generarTabla();

	}

	/**
	 * Procedimiento que gestiona la funcionalidad de los componentes
	 */
	public void iniciarListened() {
		listenedNemotecnicosBasicos();
	}

	public void gestionFrame() {
		// Frame
		frame.setLayout(new BorderLayout());
		GridBagConstraints data;
		panelBorderLayout = new JPanel();
		panelBorderLayout.setLayout(new GridBagLayout());

		// Panel arbol
		panelArbol = new JPanel();
		panelArbol.setLayout(new GridLayout());
		panelArbol.setBackground(Color.RED);
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.ipadx = 300;
		data.gridheight=2;
		data.fill = GridBagConstraints.BOTH;
		panelBorderLayout.add(panelArbol, data);

		// Panel Derecha
		panelDerecha = new JPanel();
		panelDerecha.setLayout(new GridBagLayout());
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.weightx = 1;
		data.weighty = 1;
		data.fill = GridBagConstraints.BOTH;
		panelBorderLayout.add(panelDerecha,data);
		
		// PanelIntereccion
			
		panelInteraccion = new JPanel();
		panelInteraccion.setLayout(new GridBagLayout());
		panelInteraccion.setBackground(Color.BLUE);
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.weightx = 1;
		data.weighty = 1;
		data.fill = GridBagConstraints.BOTH;
		panelDerecha.add(panelInteraccion, data);	
		
		JLabel borrame = new JLabel("Comming soon!!");
		borrame.setFont(new Font(Font.SERIF, 10, 30));
		borrame.setForeground(Color.WHITE);
		panelInteraccion.add(borrame);
		
		// PanelTabla
		panelTabla = new JPanel();
		panelTabla.setLayout(new GridLayout());
		panelTabla.setBackground(Color.GREEN);
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 1;		
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(32, 32, 32,32);
		panelDerecha.add(panelTabla, data);		
		

		frame.add(panelBorderLayout);

	}

	public void generarNemotecnicosBase() {
		// Barra d menu
		JMenuBar menuBar = new JMenuBar();
		// Anadir barra al frame
		frame.setJMenuBar(menuBar);
		// -------------------------------------
		// MENU PRINCIPAL
		JMenu menu1 = new JMenu("File");
		menuBar.add(menu1);
		// -------------------------------------
		// ITEM MENU PRINCIPAL
		itemTextoSimple = new JMenuItem("Texto 1");
		itemTextoSimple.setEnabled(true);
		itemTextoSimple.setMnemonic(KeyEvent.VK_C);
		itemTextoSimple.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menu1.add(itemTextoSimple);
		// -------------------------------------
		// SEPARAR
		menu1.addSeparator();
		// -------------------------------------
		// CHECK BOX
		JCheckBoxMenuItem check1 = new JCheckBoxMenuItem("Soy checkbox 1");
		menu1.add(check1);
		JCheckBoxMenuItem check2 = new JCheckBoxMenuItem("Soy checkbox 2");
		menu1.add(check2);
		// SEPARAR
		menu1.addSeparator();
		// RADIO BUTTON
		JRadioButtonMenuItem radio1 = new JRadioButtonMenuItem("Soy un botón 1");
		menu1.add(radio1);
		JRadioButtonMenuItem radio2 = new JRadioButtonMenuItem("Soy un botón 2");
		menu1.add(radio2);
		ButtonGroup grupoRadios = new ButtonGroup();
		grupoRadios.add(radio1);
		grupoRadios.add(radio2);
		// SEPARAR
		menu1.addSeparator();
		// SUBMENU
		JMenu submenu = new JMenu("SubMenu");
		JMenuItem textoSubmenu = new JMenuItem("Texto submenu");
		submenu.add(textoSubmenu);
		menu1.add(submenu);
		// -------------------------------------
		// ToolBar Fuente
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);

		btnTextoSimple = new JButton("Texto");
		btnTextoSimple.setEnabled(true);
		try {
			this.canvas = ImageIO.read(new File("img/ir.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnTextoSimple.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		toolbar.add(btnTextoSimple);

		frame.add(toolbar, BorderLayout.NORTH);
	}

	public void listenedNemotecnicosBasicos() {

		btnTextoSimple.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemTextoSimple.doClick();
			}
		});

		itemTextoSimple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				chooser.setFileFilter(new FileNameExtensionFilter("Ficheros de csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					System.out.println("Aprobado");
					// manejador.setFicheroActual(chooser.getSelectedFile());
				}
			}
		});
	}

	public void generarArbol() {
		DefaultMutableTreeNode raiz, categoria, letra, nombre;
		// RAIZ Y ARBOL
		raiz = new DefaultMutableTreeNode("VENTAS");
		JTree arbol = new JTree(raiz);

		// -------------------------------------------------------------------
		// AMIGOS - Nodo 1, hijo raiz
		categoria = new DefaultMutableTreeNode("Clientes");
		raiz.add(categoria);
		// LETRA - nodo 1
		letra = new DefaultMutableTreeNode("Tratamiento");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Insert");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Update");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Delete");
		letra.add(nombre);
		// LETRA - nodo 2
		letra = new DefaultMutableTreeNode("Consulta");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Consulta 01");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Consulta 02");
		letra.add(nombre);

		// -------------------------------------------------------------------
		// AMIGOS - Nodo 1, hijo raiz
		categoria = new DefaultMutableTreeNode("Producto");
		raiz.add(categoria);
		// LETRA - nodo 1
		letra = new DefaultMutableTreeNode("Tratamiento");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Insert");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Update");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Delete");
		letra.add(nombre);
		// LETRA - nodo 2
		letra = new DefaultMutableTreeNode("Consulta");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Consulta 01");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Consulta 02");
		letra.add(nombre);
		// ------------------------------------------------
		// AMIGOS - Nodo 1, hijo raiz
		categoria = new DefaultMutableTreeNode("Ventas");
		raiz.add(categoria);
		// LETRA - nodo 1
		letra = new DefaultMutableTreeNode("Tratamiento");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Insert");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Update");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Delete");
		letra.add(nombre);
		// LETRA - nodo 2
		letra = new DefaultMutableTreeNode("Consulta");
		categoria.add(letra);
		// NOMBRES
		nombre = new DefaultMutableTreeNode("Consulta 01");
		letra.add(nombre);
		nombre = new DefaultMutableTreeNode("Consulta 02");
		letra.add(nombre);
		// ANADIR ARBOL
		panelArbol.add(new JScrollPane(arbol));
	}

	public void generarTabla() {
		
		// frame.setLayout(new BorderLayout());

		Object datos[][] = { { "Ismael", "Calle Sol", "999999", "false" },
				{ "Row2-Column1", "Row2-Column2", "000000", "false" } };
		Object titulos[] = { "Nombre", "Direccion", "Telefono", "Casado" };

		// JTable tabla = new JTable(datos, titulos);
		tabla = new JTable(10, 4);
		miModelo = new MiModelo(datos, titulos);
		tabla.setModel(miModelo);

		panelTabla.add(new JScrollPane(tabla));

		
	}

}
