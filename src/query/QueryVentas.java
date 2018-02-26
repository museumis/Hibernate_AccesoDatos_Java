package query;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import jdbc.Productos;
import jdbc.Ventas;

public class QueryVentas {

	public static void totalVentas(int mes) {

		String hql = "select count(v.id) from Ventas as v where month(v.fechaventa) =?";
		Query q = Main.sesion.createQuery(hql);
		q.setInteger(0, mes);

		Long total = (Long) q.uniqueResult();
		System.out.println("El mes " + mes + " tuvo " + total + " ventas");

	}

	public static void ventasMes(int mes) {

		String hql = "from Ventas as v where month(v.fechaventa) =?";
		Query q = Main.sesion.createQuery(hql);
		q.setInteger(0, mes);

		Iterator<Ventas> iter = q.iterate();

		System.out.println("Ventas del mes " + mes);
		Ventas venta;
		while (iter.hasNext()) {
			venta = (Ventas) iter.next();
			String info = "Ventas [idventa=" + venta.getIdventa() + ", clientes=" + venta.getClientes().getId()
					+ ", productos=" + venta.getProductos().getRef() + ", fechaventa=" + venta.getFechaventa()
					+ ", cantidad=" + venta.getCantidad() + "]";
			System.out.println(info);
		}

	}

	public static void ventasPorCliente() {

		String hql = "select count(idventa) as total ,v.clientes.id as cliente from Ventas as v group by v.clientes.id";
		Query q = Main.sesion.createQuery(hql);

		List<Object[]> columnas = (List<Object[]>) q.list();

		for (Object[] objects : columnas) {

			Long ventaTotal = (Long) objects[0];
			Integer idCliente = (Integer) objects[1];

			System.out.println("Cliente: " + idCliente + " -> ventas: " + ventaTotal);
		}
	}

	public static void productoMasVendido() {
		String hql = "select v.productos as Ref, sum(v.cantidad) as Total from Ventas as v group by v.productos.ref";
		Query q = Main.sesion.createQuery(hql);
		q.setMaxResults(1);

		List<Object[]> columnas = (List<Object[]>) q.list();

		System.out.println("El producto más vendido fué ->");
		for (Object[] obj : columnas) {
			Productos producto = (Productos) obj[0];
			Long cantidadVentas = (Long) obj[1];

			String info = "Productos [ref=" + producto.getRef() + ", nombre=" + producto.getNombre() + ", precio="
					+ producto.getPrecio() + ", existencias=" + producto.getExistencias() + ", ventas="
					+ producto.getVentases().size() + "]";
			System.out.println(info + "\n Con " + cantidadVentas + " ventas");

		}

	}

}
