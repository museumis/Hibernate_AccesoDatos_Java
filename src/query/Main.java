package query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jdbc.Clientes;
import jdbc.Productos;
import jdbc.Ventas;

/**
 * 
 * @author Ismael Martin
 * 
 * Clase para la gestion de BaseDatos en Hibernate mediante consola.
 *
 */
public class Main {

	public static Scanner entrada;

	public static Session sesion;

	public static void main(String[] args) {
		org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
		System.out.println("pensando . . . \n");

		SessionFactory s = MiSessionFactory.getSessionFactory();
		sesion = s.openSession();

		gestionMenuPrincipal();

		sesion.close();
		s.close();

	}

	// ---------------------------------------------------------------------------------------------------------------
	// TRATAMIENTO DE TABLAS
	// ---------------------------------------------------------------------------------------------------------------
	// INSERT
	public static void insertarTabla(String nombreTabla) {
		Transaction tx = sesion.beginTransaction();

		switch (nombreTabla) {
		case "clientes": {
			Clientes cliente = new Clientes(pedirInt("\tId: "), pedirTexto("\tNombre: "), pedirTexto("\tDirección: "),
					pedirTexto("\tPoblación: "), pedirTexto("\tTelefono: "), pedirTexto("\tNif: "), null);
			sesion.save(cliente);
			break;
		}
		case "productos": {

			Set<Ventas> ventas = new HashSet<Ventas>(0);
			Productos producto = new Productos(pedirInt("\tRef: "), pedirTexto("\tNombre: "),
					pedirDecimal("\tPrecio: "), pedirInt("\tExistencias: "), ventas);

			sesion.save(producto);
			break;
		}
		case "ventas": {
			Clientes cliente = new Clientes();
			Productos productos = new Productos();

			cliente = (Clientes) sesion.get(Clientes.class, pedirInt("\tCódigo del cliente: "));
			productos = (Productos) sesion.get(Productos.class, pedirInt("\tRef del producto: "));

			// fecha hoy
			LocalDate ld = LocalDate.now();
			Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Ventas venta = new Ventas(pedirInt("\tId: "), cliente, productos, date, pedirInt("\tCantidad: "));
			sesion.save(venta);
			break;
		}
		}
		tx.commit();
	}

	public static void modificarTabla(String nombreTabla, int id) {

		Transaction tx = sesion.beginTransaction();

		switch (nombreTabla) {
		case "clientes": {
			Clientes cliente = new Clientes(id, pedirTexto("\tNombre: "), pedirTexto("\tDirección: "),
					pedirTexto("\tPoblación: "), pedirTexto("\tTelefono: "), pedirTexto("\tNif: "), null);
			sesion.update(cliente);
			break;
		}
		case "productos": {
			// Recuperar el array de ventas, no funcional
			Ventas v = new Ventas();
			v = (Ventas) sesion.get(Ventas.class, id);
			Set<Ventas> ventas;
			ventas = v.getProductos().getVentases();
			// ------------
			Set<Ventas> ventases = new HashSet<Ventas>(0);

			Productos producto = new Productos(id, pedirTexto("\tNombre: "), pedirDecimal("\tPrecio: "),
					pedirInt("\tExistencias: "), ventases);

			sesion.update(producto);
			break;
		}
		case "ventas": {
			Clientes cliente = new Clientes();
			Productos productos = new Productos();

			cliente = (Clientes) sesion.get(Clientes.class, id);
			productos = (Productos) sesion.get(Productos.class, id);

			// fecha hoy
			LocalDate ld = LocalDate.now();
			Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Ventas venta = new Ventas(id, cliente, productos, date, pedirInt("\tCantidad: "));
			sesion.update(venta);
			break;
		}
		}
		tx.commit();

	}

	public static void borrarTabla(String nombreTabla, int id) {

		Transaction tx = sesion.beginTransaction();

		switch (nombreTabla) {
		case "clientes": {
			Clientes cliente = new Clientes();
			cliente = (Clientes) sesion.get(Clientes.class, id);
			sesion.delete(cliente);
			break;
		}
		case "productos": {
			Productos producto = new Productos();
			producto = (Productos) sesion.get(Productos.class, id);
			sesion.delete(producto);
			break;
		}
		case "ventas": {
			Ventas venta = new Ventas();
			venta = (Ventas) sesion.get(Ventas.class, id);
			sesion.delete(venta);
			break;
		}
		}
		tx.commit();

	}

	// ------------------------------------------------------------------------------------------------------
	// GESTION MENUS
	// ----------------------------------------------------------------------------------------------------------------
	public static void gestionMenuPrincipal() {
		String[] opcionesMenuPrincipal = { "\n--- MENU PRINCIPAL ---", " 1. Tratar Clientes", " 2. Tratar Productos",
				" 3. Tratar Ventas", " 4. Consultar BaseDatos", " 5. Salir" };

		switch (generarMenu(opcionesMenuPrincipal)) {
		case 1: {

			gestionMenuClientes();
			break;
		}
		case 2: {
			gestionMenuProductos();
			break;
		}
		case 3: {
			gestionMenuVentas();
			break;
		}
		case 4: {
			gestionConsultas();
			break;
		}
		case 5: {
			System.out.println("¡Hasta la próxima! ~ Comming soon with interface!");
			break;
		}
		}

	}

	public static void gestionMenuClientes() {
		String[] opcionesTratamiento = { "\n--- TABLA CLIENTES ---", " 1. Insertar", " 2. Modificar", " 3. Borrar",
				" 4. Atrás" };

		switch (generarMenu(opcionesTratamiento)) {
		case 1: {
			insertarTabla("clientes");
			gestionMenuClientes();
			break;
		}
		case 2: {
			modificarTabla("clientes", pedirInt("\tId del Cliente: "));
			gestionMenuClientes();
			break;
		}
		case 3: {
			borrarTabla("clientes", pedirInt("\tId del Cliente: "));
			gestionMenuClientes();
			break;
		}
		case 4: {
			gestionMenuPrincipal();
			break;
		}
		}
	}

	public static void gestionMenuProductos() {
		String[] opcionesTratamiento = { "\n--- TABLA PRODUCTOS ---", " 1. Insertar", " 2. Modificar", " 3. Borrar",
				" 4. Atrás" };

		switch (generarMenu(opcionesTratamiento)) {
		case 1: {
			insertarTabla("productos");

			gestionMenuProductos();
			break;
		}
		case 2: {
			modificarTabla("productos", pedirInt("\tRef del Producto: "));

			gestionMenuProductos();
			break;
		}
		case 3: {
			borrarTabla("productos", pedirInt("\tRef del Producto: "));

			gestionMenuProductos();
			break;
		}
		case 4: {
			gestionMenuPrincipal();
			break;
		}
		}
	}

	public static void gestionMenuVentas() {
		String[] opcionesTratamiento = { "\n--- TABLA VENTAS ---", " 1. Insertar", " 2. Modificar", " 3. Borrar",
				" 4. Atrás" };

		switch (generarMenu(opcionesTratamiento)) {
		case 1: {
			insertarTabla("ventas");

			gestionMenuVentas();
			break;
		}
		case 2: {
			modificarTabla("ventas", pedirInt("\tId del Ventas: "));

			gestionMenuVentas();
			break;
		}
		case 3: {
			borrarTabla("ventas", pedirInt("\tId del Venta: "));

			gestionMenuVentas();
			break;
		}
		case 4: {
			gestionMenuPrincipal();
			break;
		}
		}
	}

	public static void gestionConsultas() {
		String[] opcionesTratamiento = { "\n--- CONSULTAS ---", " 1. Productos con existencia menor que 2",
				" 2. Clientes Extremeños ", " 3. Producto más caro ", " 4. Mayor y menor existencias ",
				" 5. Total ventas por mes ", " 6. Total de ventas por cliente ", " 7. Gasto total de clientes por mes", " 8. Producto más vendido ", " 9. Ir atrás" };

		switch (generarMenu(opcionesTratamiento)) {
		case 1: {
			QueryProductos.productosSinExistencias();
			gestionConsultas();
			break;
		}
		case 2: {
			QueryCliente.clientesExtremenios();
			gestionConsultas();
			break;
		}
		case 3: {
			QueryProductos.productoMasCaro();
			gestionConsultas();
			break;
		}
		case 4: {
			QueryProductos.productoMaxMinExistencias();
			gestionConsultas();
			break;
		}
		case 5: {
			int mes = pedirInt("\nMes que deseas ver: ");
			QueryVentas.totalVentas(mes);
			QueryVentas.ventasMes(mes);
			gestionConsultas();
			break;
		}
		case 6: {
			QueryVentas.ventasPorCliente();
			gestionConsultas();
			break;
		}
		case 7: {
			QueryCliente.gastoPorCliente(pedirInt("\nMes que deseas ver: "));
			gestionConsultas();
			break;
		}
		case 8: {
			QueryVentas.productoMasVendido();
			gestionConsultas();
			break;
		}
		case 9: {
			gestionConsultas();
			break;
		}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	// UTILIDADES
	// ------------------------------------------------------------------------------------------------------
	/**
	 * Metodo para generar menus
	 * 
	 * @param opciones
	 *            del menu
	 * @return respuesta
	 */
	public static int generarMenu(String[] opciones) {
		entrada = new Scanner(System.in);
		int respuesta = -1;
		for (int i = 0; i < opciones.length; i++) {
			System.out.println(opciones[i]);

		}
		System.out.print("Opcion: ");
		try {
			respuesta = entrada.nextInt();
		} catch (Exception e) {
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}

		if ((respuesta < 1) || (respuesta > opciones.length)) {
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}
		return respuesta;

	}// Fin de generar Menu

	/**
	 * Metodo para obtener texto por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static String pedirTexto(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		return teclado.nextLine();
	}// Fin de pedir texto

	/**
	 * Metodo para obtener enteros por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static int pedirInt(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		int numero = -1;
		try {
			numero = teclado.nextInt();
		} catch (Exception e) {
			System.out.println("*** Introduce un número.");
		}
		return numero;
	}// Fin de pedir texto

	public static BigDecimal pedirDecimal(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		BigDecimal numero = null;
		try {
			numero = teclado.nextBigDecimal();
		} catch (Exception e) {
			System.out.println("*** Introduce un número.");
		}
		return numero;
	}// Fin de pedir texto

}
