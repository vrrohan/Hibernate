import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import com.ron.Entity.Student;

public class HibernateSessionFactoryUtils {
	private static SessionFactory sessionFactory;

	// private constructor so that no one can create object of this class
	private HibernateSessionFactoryUtils() {
	}

	// static factory method to get singleton object of Session Factory
	// making method thread safe
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// double check locking to make ensure that object is thread safe
			synchronized (HibernateSessionFactoryUtils.class) {
				if (sessionFactory == null) {
					Configuration configHibernate = new Configuration();
					configHibernate.setProperties(configHibernateJDBCProperties());
					ServiceRegistry registry = new StandardServiceRegistryBuilder()
							.applySettings(configHibernate.getProperties()).build();
					sessionFactory = new Configuration().addAnnotatedClass(Student.class).buildSessionFactory(registry);
				} // inner if ends...
			} // synchronized class level
		} // outer if ends...
		return sessionFactory;
	}// method ends...

	// method to configure hibernate jdbc configuration properties
	private static synchronized Properties configHibernateJDBCProperties() {
		Properties properties = new Properties();
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "root");
		properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		properties.put(Environment.SHOW_SQL, "true");
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		return properties;
	}
}
