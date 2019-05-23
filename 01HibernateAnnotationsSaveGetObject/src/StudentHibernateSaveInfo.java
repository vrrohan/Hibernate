import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class StudentHibernateSaveInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// you can create sessionfactory object inside try-with-resources block also
		// here main hibernate exception class is - HibernateException, every exception
		// related to hibernate gets caught in this top level class
		// also no need to close resources for every sessionfactory object, it will be
		// closed automatically
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
			Student newStudent = new Student("Dave", "Scott", "dave.scott@yahoo.com", "it");
			// you have to use beginTransaction() with save(object), else it will throw and
			// exception:- save exception caught
			// org.hibernate.HibernateException: save is not valid without active
			// transaction
			session.beginTransaction();
			session.save(newStudent);
			// if you dont commit transaction with save(object) - programs runs successfully
			// but no object is persisted in database
			session.getTransaction().commit();
			System.out.println("New Student saved successfully");
		} catch (HibernateException hibernateException) {
			System.out.println("save exception caught");
			hibernateException.printStackTrace();
		}

	}

}
