package query;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import jdbc.Clientes;

public class QueryCliente {

	public static void clientesExtremenios() {
		
		String hql = "from Clientes as c where c.poblacion in ('CACERES','BADAJOZ')";
		Query q = Main.sesion.createQuery(hql);
		
		Iterator<Clientes> clientes = q.iterate();
		
		while (clientes.hasNext()) {
			Clientes cliente = (Clientes) clientes.next();
			
				String cadena= "Clientes [id=" + cliente.getId() + ", nombre=" +cliente.getNombre() + ", direccion=" + cliente.getDireccion()+ ", poblacion=" + cliente.getPoblacion()
						+ ", telef=" + cliente.getTelef() + ", nif=" + cliente.getNif() + ", ventases=" + cliente.getVentases().size()+ "]";
				System.out.println(cadena);

				
		}
		
	}
	
	public static void gastoPorCliente(int mes) {
		
		String hql = "select sum(v.cantidad * v.productos.precio) as gastado,v.clientes.id as cliente from Ventas as v where month(v.fechaventa)=? group by v.clientes.id";
		Query q = Main.sesion.createQuery(hql);
		q.setInteger(0, mes);
		
		List<Object[]>columnas = (List<Object[]>) q.list();
		System.out.println("El gasto de los clientes por meses es...");
		for (Object[] obj : columnas) {
			BigDecimal totalGastado = (BigDecimal) obj[0];
			Integer idCliente = (Integer) obj[1];
			System.out.println("El cliente " + idCliente + " gastó en el mes "+mes + " -> "+totalGastado);
		}
	}
}
