import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ron.Entity.Student;

public class StudentDelete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();
				Session session = sessionFactory.getCurrentSession();
				Scanner sc = new Scanner(System.in);) {
			session.beginTransaction();

			System.out.print("Enter Id to delete : ");
			int idToDelete = sc.nextInt();
			Student student = session.get(Student.class, idToDelete);
			if (student != null) {
				session.delete(student);
				System.out.println("student deleted successfully");
				// commit to make sure delete object is reflected into db
				session.getTransaction().commit();
			} else {
				System.out.println("Unable to delete. Id " + idToDelete + " not present in db");
			}

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

		// above is delete student object, so first we need to retrieve student object,
		// then delete that object
		// now deleting student object on the fly in a single step
		try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class)
				.buildSessionFactory();
				Session session = sessionFactory.getCurrentSession();
				Scanner sc = new Scanner(System.in)) {
			session.beginTransaction();
			System.out.print("Enter Id to delete : ");
			int idToDelete = sc.nextInt();
			int rowsDeleted = session.createQuery("delete from Student where id=" + idToDelete + "").executeUpdate();
			if (rowsDeleted == 0) {
				System.out.println("unable to delete. Id not present.");
			} else {
				System.out.println("student deleted successfully. Deleted records : " + rowsDeleted);
			}
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

	}

}
