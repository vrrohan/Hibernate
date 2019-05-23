import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class StudentHibernateSave {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
//		Student newStudent = new Student("Wayne", "Rooney", "wayne10@gmail.com", "cse");
		Student newStudent = new Student("Paul", "Allen", "paul.allen@gmail.com", "cse");
		session.beginTransaction();
		session.save(newStudent);
		session.getTransaction().commit();
		System.out.println("New Student saved successfully");
		sessionFactory.close();
	}

}
