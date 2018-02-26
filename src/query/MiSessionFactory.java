package query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MiSessionFactory {

	public static SessionFactory miSesion;

	public static SessionFactory getSessionFactory() {
		try {

			if (miSesion == null) {
				Configuration confi = new Configuration().configure("hibernate.cfg.xml");
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
						.applySettings(confi.getProperties());
				miSesion = confi.buildSessionFactory(builder.build());
			}

		} catch (HibernateException e) {
			System.out.println("*** Error al configurar la clase MiSesionFactory");
		}

		return miSesion;

	}

}
