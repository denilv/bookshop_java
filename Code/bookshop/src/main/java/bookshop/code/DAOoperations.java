package bookshop.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DAOoperations
{

	// private static Map<Integer,Customer> users = new HashMap<Integer,
	// Customer>();

	public void printAllBooks()
	{
		System.out.println("Hibernate + MySQL");
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Transaction tx = s.getTransaction();
		List<Book> l = s.createQuery("FROM Book").list();
		for (Book b : l)
		{
			try
			{
				System.out.println("ID: " + b.getId() + "; Title: " + b.getTitle() + "; ISBN: " + b.getIsbn()
						+ "; Publisher: " + b.getPublHouse().getName());
				for (BookToAuthor a : b.getBookToAuthors())
				{
					Author auth = a.getAuthor();
					System.out.print(auth.toString() + "; ");
				}
				System.out.println();
			} catch (Exception e)
			{
				System.out.println(e);
				s.getTransaction().rollback();
			}
		}
		////////// s.close();
	}

	public Customer addCustomer(String f, String l, String m, String email, String number, String addr)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Customer c = new Customer();
		c.setAddress(addr);
		c.setFName(f);
		c.setMName(m);
		c.setLName(l);
		c.setEmail(email);
		c.setContactNumber(number);
		s.save(c);
		s.getTransaction().commit();
		////////// s.close();
		return c;
	}

	public Customer addCustomer(Customer customer)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.save(customer);
		s.getTransaction().commit();
		////////// s.close();
		return customer;
	}

	public Book addBook(String tit, Integer ann, Integer av_am, Date d, String isbn, Integer pages, Set<Author> authors,
			Genre genre, Integer price, PublHouse publHouse)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Book b = new Book();
		b.setGenre(genre);
		b.setTitle(tit);
		b.setAnnotation(ann);
		b.setIsbn(isbn);
		b.setPrice(price);
		b.setPages(pages);
		b.setAuthors(authors);
		b.setPublHouse(publHouse);
		b.setAvailableAmount(av_am);
		b.setPublicationDate(d);
		s.save(b);
		s.getTransaction().commit();
		////////// s.close();
		return b;
	}

	public Book addBook(Book book)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.save(book);
		s.getTransaction().commit();
		//////// s.close();
		return book;
	}

	public Author addAuthor(Author author)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.save(author);
		s.getTransaction().commit();
		//////// s.close();
		return author;
	}

	public Orders addOrder(String addr, String comm, Customer c, ShippingMethod sm)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		bookshop.code.Orders o = new bookshop.code.Orders();
		o.setComment(comm);
		o.setShippingAddress(addr);
		o.setCustomer(c);
		o.setStatus("Корзина");
		o.setShippingMethod(sm);
		// o.setOrderItemses(oi);
		s.save(o);
		s.getTransaction().commit();
		//////// s.close();
		return o;
	}

	public OrderItems addOrderItemses(bookshop.code.Orders o, Book b, Integer am)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		OrderItems oi = new OrderItems(b, o, am);
		s.save(oi);
		//////// s.close();
		return oi;
	}

	public void changeOrderStatus(bookshop.code.Orders o, String status)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		o.setStatus(status);
		s.update(o);
		s.getTransaction().commit();
		//////// s.close();
	}

	public Integer incrementBookAvAm(Book b, Integer am)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		if (b.getAvailableAmount() + am < 0)
		{
			//////// s.close();
			return -1;// Книг заказано больше, чем есть
		} else
		{
			b.setAvailableAmount(b.getAvailableAmount() + am);
			s.saveOrUpdate(b);
			s.getTransaction().commit();
			//////// s.close();
			return 0;
		}
	}

	public Book getBookById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Book b = (Book) s.get(Book.class, id);
		//////// s.close();
		return b;
	}

	public List<Book> getBookList()
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Book> books = s.createQuery("FROM Book").list();
		//////// s.close();
		return books;
	}

	public List<Book> getBookListByTitle(String title)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Book> a = s.createCriteria(Book.class).add(Restrictions.like("title", "%" + title + '%'))
				.addOrder(org.hibernate.criterion.Order.asc("title")).list();
		if (a.size() == 0)
		{
			// System.err.println("No such Book with Title like - " + title);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return a;
	}

	public List<Book> getBookListByGenre(Genre g)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Book> books = new ArrayList<Book>(g.getBooks());
		//////// s.close();
		return books;
	}

	public List<OrderItems> getCartItems(Customer c)
	{
		List<OrderItems> oi;
		return null;
	}

	public List<Book> getBookListByAuthor(Author author)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Book> books = new ArrayList<Book>(author.getBooks());
		//////// s.close();
		return books;
	}

	public List<Author> getAuthorListByLastname(String lname)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Author> a = s.createCriteria(Author.class).add(Restrictions.like("LName", lname))
				/* .addOrder(org.hibernate.criterion.Order.asc("LNname")) */.list();
		if (a.size() == 0)
		{
			// System.err.println("No such Author with lastname - " + lname);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return a;
	}

	public List<Author> getAuthorList()
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Author> authors = s.createQuery("FROM Author").list();
		//////// s.close();
		return authors;
	}

	public List<Genre> getGenreList()
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Genre> genres = s.createQuery("FROM Genre").list();
		//////// s.close();
		return genres;
	}

	public List<PublHouse> getPublHouseList()
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<PublHouse> publhouses = s.createQuery("FROM PublHouse").list();
		//////// s.close();
		return publhouses;
	}

	public Author getAuthorByLastname(String lname)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Author> a = s.createCriteria(Author.class).add(Restrictions.eq("LName", lname)).list();
		if (a.size() == 0)
		{
			// System.err.println("No such Author with lastname - " + lname);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return a.get(0);
	}

	public Customer getCustomerById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Customer c = (Customer) s.get(Customer.class, id);
		//////// s.close();
		return c;
	}

	public void editBook(Book book)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		DAOoperations dao = new DAOoperations();
		Book root = dao.getBookById(book.getId());
		root.setAnnotation(book.getAnnotation());
		root.setAuthors(book.getAuthors());
		root.setAvailableAmount(book.getAvailableAmount());
		root.setBookToAuthors(book.getBookToAuthors());
		root.setGenre(book.getGenre());
		root.setIsbn(book.getIsbn());
		root.setOrderItemses(book.getOrderItemses());
		root.setPages(book.getPages());
		root.setPrice(book.getPrice());
		root.setPublHouse(book.getPublHouse());
		root.setPublicationDate(book.getPublicationDate());
		root.setTitle(book.getTitle());
		root.setWritingDate(book.getWritingDate());
		s.update(root);
		s.getTransaction().commit();
		// ////////s.close();
	}

	public void editAuthor(Author author)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.saveOrUpdate(author);
		s.getTransaction().commit();
		//////// s.close();
	}

	public Author getAuthorById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Author c = (Author) s.get(Author.class, id);
		//////// s.close();
		return c;
	}

	public bookshop.code.Orders getOrderById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		bookshop.code.Orders o = (bookshop.code.Orders) s.get(bookshop.code.Orders.class, id);
		//////// s.close();
		return o;
	}

	public List<bookshop.code.Orders> getOrderListByCustomer(Customer c)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<bookshop.code.Orders> orders = new ArrayList<bookshop.code.Orders>(c.getOrderses());
		//////// s.close();
		return orders;
	}

	public ShippingMethod getShippingMethodById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		ShippingMethod sm = s.get(ShippingMethod.class, id);
		//////// s.close();
		return sm;
	}

	public Genre getGenreByName(String name)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Genre> g = s.createCriteria(Genre.class).add(Restrictions.eq("name", name)).list();
		if (g.size() == 0)
		{
			// System.err.println("No such genre like - " + name);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return g.get(0);
	}

	public Genre getGenreById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		Genre c = (Genre) s.load(Genre.class, id);
		//////// s.close();
		return c;
	}

	public PublHouse getPublHouseById(Integer id)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		PublHouse c = (PublHouse) s.load(PublHouse.class, id);
		//////// s.close();
		return c;
	}

	public Customer login(String email, String password)
	{
		DAOoperations dao = new DAOoperations();
		Customer customer = dao.getCustomerByEmail(email);
		if (customer.getPassword() == password)
		{
			return customer;
		}
		return null;
	}

	public PublHouse getPublHouseByName(String name)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<PublHouse> p = s.createCriteria(PublHouse.class).add(Restrictions.eq("name", name)).list();
		if (p.size() == 0)
		{
			// System.err.println("No such Publishing House like - " + name);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return p.get(0);
	}

	public Customer getCustomerByEmail(String email)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		List<Customer> a = s.createCriteria(Customer.class).add(Restrictions.eq("email", email)).list();
		if (a.size() == 0)
		{
			// System.err.println("No such Customer with email - " + email);
			//////// s.close();
			return null;
		}
		//////// s.close();
		return a.get(0);
	}

	public void deleteCustomer(Customer c)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.delete(c);
		s.getTransaction().commit();
		//////// s.close();
	}

	public void deleteBook(Book b)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.delete(b);
		s.getTransaction().commit();
		//////// s.close();
	}

	public void deleteAuthor(Author a)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.delete(a);
		s.getTransaction().commit();
		//////// s.close();
	}

	public void deleteOrder(bookshop.code.Orders o)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.delete(o);
		s.getTransaction().commit();
		//////// s.close();
	}

	public void deleteOrderItems(OrderItems oi)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();// .openSession();
		s.beginTransaction();
		s.delete(oi);
		s.getTransaction().commit();
		//////// s.close();
	}

	public List<Book> searchBook(BookSearchCriteria bookCriteria)
	{
		List<Book> books = null;
		DAOoperations dao = new DAOoperations();
		System.out.println(bookCriteria.getTitle());

		if (bookCriteria == null)
		{
			System.out.println("OOPS");
			return null;
		}
		books = dao.getBookList();

		if (!bookCriteria.getTitle().isEmpty())
		{
			books = dao.getBookListByTitle(bookCriteria.getTitle());
		}
		System.out.println(books.size());
		if (bookCriteria.getAuthor() != null)
		{
			Author author = bookCriteria.getAuthor();
			List<Book> tmp = new ArrayList<Book>();
			for (Book i : books)
			{
				if (i.getAuthors().contains(author))
				{
					if (!tmp.contains(i))
					{
						tmp.add(i);
					}
				}
			}
			books = tmp;
		}
		System.out.println(books.size());
		if (bookCriteria.getGenre() != null)
		{
			Genre genre = bookCriteria.getGenre();
			List<Book> tmp = new ArrayList<Book>();
			for (Book i : books)
			{
				if (i.getGenre().equals(genre))
				{
					if (!tmp.contains(i))
					{
						tmp.add(i);
					}
				}
			}
			books = tmp;
		}
		System.out.println(books.size());

		// price??
		return books;
	}
}
