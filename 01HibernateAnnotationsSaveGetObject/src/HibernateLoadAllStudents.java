import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class HibernateLoadAllStudents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		/*
		 * You don't always need to commit transaction, but always need to
		 * beginTransaction() else with createQuery() if you don't begin transaction it
		 * will throw an exception:- Exception in thread "main"
		 * org.hibernate.HibernateException: createQuery is not valid without active
		 * transaction
		 */
		session.beginTransaction();
		// here "Student" is class Name
		Query getAllStudentsQuery = session.createQuery("from Student");
		List<Student> studentsList = getAllStudentsQuery.getResultList();
		studentsList.forEach(x -> System.out.println(x));
		sessionFactory.close();
	}

}
