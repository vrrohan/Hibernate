import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class StudentUpdateAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// if you want to update all column data, it is not best to load every object
		// from db
		// better to update all email addresses on the go
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();

			// executeUpdate() returns number of records that were update in form of int
			// executeUpdate() used only with update and delete statement, not with select
			// statements
			int numberOfRowsUpdated = session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			System.out.println("All " + numberOfRowsUpdated + " rows updated Successfully");
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
	}

}
