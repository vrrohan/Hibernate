import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ron.Entity.Student;

public class HibernateLoadStudentWithGmailId {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
				Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			String gmailStudentsQuery = "from Student where email LIKE '%gmail.com'";
			List<Student> studentList = session.createQuery(gmailStudentsQuery).getResultList();
			session.getTransaction().commit();
			studentList.forEach(x -> System.out.println(x));
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
	}

}
