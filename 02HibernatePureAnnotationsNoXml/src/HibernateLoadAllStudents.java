import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ron.Entity.Student;

public class HibernateLoadAllStudents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
				Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			List<Student> studentList = session.createQuery("from Student").getResultList();
			session.getTransaction().commit();
			studentList.forEach(x -> System.out.println(x));
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

	}

}
