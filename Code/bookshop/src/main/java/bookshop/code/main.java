package bookshop.code;

import java.util.List;

import org.hibernate.Session;

public class main
{
	public static void main(String[] args)
	{
		System.out.println("Hibernate + MySQL");
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		DAOoperations a = new DAOoperations();
		List<Book> books = a.getBookList();
		// d.printAllBooks();
		// a.deleteCustomer(a.getCustomerByEmail("test@test.test"));
		// System.out.println(a.getCustomerByEmail("test@test.test"));
		Book b = a.getBookById(1);
		// a.deleteBook(b);
		System.out.println(b.getTitle());
	}
}
