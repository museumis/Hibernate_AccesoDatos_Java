package query;

import java.util.Iterator;

import org.hibernate.Query;

import jdbc.Productos;

public class QueryProductos {

	public static void productosSinExistencias() {
		
		String hql = "from  Productos as p where p.existencias <2";
		Query q = Main.sesion.createQuery(hql);
		
		Iterator<Productos> productos = q.iterate();
		System.out.println("Productos sin existencia");
		while (productos.hasNext()) {
			Productos producto = (Productos) productos.next();
			System.out.println("id: "+ producto.getRef() + " con " + producto.getExistencias() + " en stock");
		}
		
	}
	
	public static void productoMasCaro() {
		
		String hql = "from Productos as p where p.precio = (select max(precio) from Productos)";
		Query q = Main.sesion.createQuery(hql);
		
		Iterator iter = q.iterate();
		
		Productos producto;
		System.out.println("El producto más caro es:");
		while(iter.hasNext()) {
			producto = (Productos) iter.next();
			String info = "Productos [ref=" + producto.getRef() + ", nombre=" + producto.getNombre() + ", precio=" + producto.getPrecio() + ", existencias=" + producto.getExistencias()
						+ ", ventas=" + producto.getVentases().size() + "]";
			System.out.println(info);
			
		}
		
	}
	
	public static void productoMaxMinExistencias() {
		
		String hql = "from Productos as p where p.existencias =(select min(existencias)from Productos) OR p.existencias=(select max(existencias)from Productos)";
		Query q = Main.sesion.createQuery(hql);
		
		Iterator iter = q.iterate();
		
		Productos producto;
		System.out.println("El producto con mayor y menor existencias son:");
		while(iter.hasNext()) {
			producto = (Productos) iter.next();
			String info = "Productos [ref=" + producto.getRef() + ", nombre=" + producto.getNombre() + ", precio=" + producto.getPrecio() + ", existencias=" + producto.getExistencias()
						+ ", ventas=" + producto.getVentases().size() + "]";
			System.out.println(info);
			
		}
		
	}
	
}
