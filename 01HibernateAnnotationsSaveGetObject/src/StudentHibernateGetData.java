import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class StudentHibernateGetData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		int studentIdToGet = 105;
		session.beginTransaction();
		// get() returns null, if no pk is present in database
		// get() reads or retrieves data using primary key
		Student studGet = session.get(Student.class, studentIdToGet);
		session.getTransaction().commit();
		if (studGet != null) {
			System.out.println("Student retrieved successfully");
			System.out.println(studGet);
		} else {
			System.out.println("No Student with id " + studentIdToGet + " is present in database");
		}
		sessionFactory.close();
	}

}
