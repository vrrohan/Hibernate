import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ron.Entity.Student;

public class StudentUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();
				Session session = sessionFactory.getCurrentSession();
				Scanner sc = new Scanner(System.in)) {
			session.beginTransaction();
			System.out.print("Enter Id to Update : ");
			int idToUpdate = sc.nextInt();

			// load student object with required id
			/*
			 * Return the persistent instance of the given entity class with the given
			 * identifier, assuming that the instance exists. This method might return a
			 * proxied instance that is initialized on-demand, when a non-identifier method
			 * is accessed. You should not use this method to determine if an instance
			 * exists (use get() instead). Use this only to retrieve an instance that you
			 * assume exists, where non-existence would be an actual error.
			 */
			// So before loading lets first check if id present in db or not using
			// get(object)
			// remeber get(object) used to return null if object with pk is not present in
			// db
			if (session.get(Student.class, idToUpdate) != null) {
				// load() will throw an exception if required id is not present in db, so better
				// to check if id is present in db or not using get(object)
				Student stud = session.load(Student.class, idToUpdate);

				stud.setEmail("newEmail@yahoo.com");
				stud.setCourse("newCourse");

				// changed stud object is still in memory not persisted in db till now
				session.getTransaction().commit();
				System.out.println("student updated successfully with id : " + idToUpdate);
			} else {
				System.out.println("student with id " + idToUpdate + " is not present in db");
			}
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
	}

}
