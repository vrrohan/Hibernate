import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ron.Entity.Student;

public class StudentGetData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();

			// get All students
			System.out.println("Loading all students");
			List<Student> studentList = session.createQuery("from Student").getResultList();
			studentList.forEach(x -> System.out.println(x));

			// students with course cse
			System.out.println("\nStudents with course = cse");
			List<Student> cseStudents = session.createQuery("from Student where course='cse'").getResultList();
			cseStudents.forEach(x -> System.out.println(x));

			// students with course cse & mailid yahoo.com
			System.out.println("\nstudents with course=cse and email=yahoo.com");
			List<Student> cseYahooStudents = session
					.createQuery("from Student where course='cse' and email LIKE '%yahoo%'").getResultList();
			cseYahooStudents.forEach(x -> System.out.println(x));
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

	}

}
