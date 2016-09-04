package test.code;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import bookshop.code.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.testng.Assert;

public class AllTests
{
	private DAOoperations a = new DAOoperations();
	@Test()
	public void test01()
	{
		List<Book> books = a.getBookListByAuthor((a.getAuthorByLastname("Kernighan")));
		AssertJUnit.assertEquals(a.getBookById(1).getTitle() , books.get(0).getTitle());
		//System.out.println("Test 1 is done!");
	}
	
	@Test()
	public void test01err()
	{
		List<Author> authors = a.getAuthorListByLastname("TestError");
		AssertJUnit.assertEquals(authors, null);
		//System.out.println("Test 1 is done!");
	}
	
	@Test()
	public void test02()
	{
		Author author = a.getAuthorById(1);
		AssertJUnit.assertEquals(a.getAuthorListByLastname("Kernighan").get(0).getLName(), author.getLName());
	}
	
	@Test()
	public void test02err()
	{
		AssertJUnit.assertEquals(a.getAuthorListByLastname("TestErr"), null);
	}
	
	@Test()
	public void test03()
	{
		Book book = a.getBookListByTitle("Programming").get(0);
		AssertJUnit.assertEquals(a.getBookById(1).getTitle(), book.getTitle());
	}
	
	@Test()
	public void test03err()
	{
		AssertJUnit.assertEquals(a.getBookListByTitle("TestErr"), null);
	}
	
	@Test()
	public void test04()
	{
		a.changeOrderStatus(a.getOrderById(1), "TestTest");
		AssertJUnit.assertEquals(a.getOrderById(1).getStatus(), "TestTest");
		a.changeOrderStatus(a.getOrderById(1), "Preparation");
	}
	
	@Test()
	public void test05()
	{
		List<Book> books = a.getBookListByGenre(a.getGenreByName("Novel"));
		AssertJUnit.assertEquals(books.size(), a.getGenreByName("Novel").getBooks().size());
	}
	
	@Test()
	public void test05err()
	{
		AssertJUnit.assertEquals(a.getGenreByName("TestErr"), null);
		
	}
	
	@Test()
	public void test06()
	{
		List<Orders> orders = a.getOrderListByCustomer(a.getCustomerById(1));
		AssertJUnit.assertEquals(orders.size(), 1);
	}

	@Test()
	public void test07()
	{
		Book tmp = a.addBook("Test", null, 100,  new Date(2010,10,10), null, 100, null,
				a.getGenreById(1), 1000, a.getPublHouseById(1));
		Book b = a.getBookListByTitle("test").get(0);
		AssertJUnit.assertEquals(b.getTitle(), "Test");
		a.deleteBook(b);
		AssertJUnit.assertEquals(a.getBookListByTitle("test"), null);
	}
	
	@Test()
	public void test08()
	{
		Book tmp = a.addBook("Test", null, 100,  new Date(2010,10,10), null, 100, null,
				a.getGenreById(1), 1000, a.getPublHouseById(1));
		Book b = a.getBookListByTitle("test").get(0);
		AssertJUnit.assertEquals(b.getTitle(), "Test");
		a.deleteBook(b);
		AssertJUnit.assertEquals(a.getBookListByTitle("test"), null);
	}
	
	@Test
	public void test09()
	{
		Book tmp = a.addBook("Test", null, 100,  new Date(2010,10,10), null, 100, null,
				a.getGenreById(1), 1000, a.getPublHouseById(1));
		Book b = a.getBookListByTitle("test").get(0);
		AssertJUnit.assertEquals(b.getTitle(), "Test");
		a.deleteBook(b);
		AssertJUnit.assertEquals(a.getBookListByTitle("test"), null);
	}
	
	@Test
	public void test10()
	{
		Customer c = a.getCustomerByEmail("denilv@mail.ru");
		AssertJUnit.assertEquals(a.getCustomerById(1).getId(), c.getId());
	}
	
	@Test
	public void test11()
	{
		PublHouse p = a.getPublHouseByName("aist");
		AssertJUnit.assertEquals(a.getPublHouseById(1).getId(), p.getId());
	}
	
	@Test
	public void test12()
	{
		PublHouse p = a.getPublHouseByName("aist");
		AssertJUnit.assertEquals(a.getPublHouseById(1).getId(), p.getId());
		//a.addCustomer(f, l, m, email, number, addr);
	}
	
	@Test
	public void test13()
	{
		Customer tmp = a.addCustomer("f", "l", "m", "test@test.test", "test", "addr");
		Customer c = a.getCustomerByEmail("test@test.test");
		AssertJUnit.assertEquals("test@test.test", c.getEmail());
		a.deleteCustomer(c);
		c = a.getCustomerByEmail("test@test.test");
		AssertJUnit.assertEquals(null, c);
	}
	
	@Test
	public void test14()
	{
		Orders order = a.addOrder("test", "test", a.getCustomerById(1), a.getShippingMethodById(1));
		Orders o = a.getOrderById(order.getId());
		AssertJUnit.assertEquals("test", o.getShippingAddress());
		a.deleteOrder(o);
		List<Orders> orderlist = a.getOrderListByCustomer(a.getCustomerById(1));
		AssertJUnit.assertEquals(1, orderlist.size());
	}
	
	@Test
	public void test15()
	{
		OrderItems tmp = a.addOrderItemses(a.getOrderById(1), a.getBookById(1), 1);
		Orders o = a.getOrderById(1);
		Set<OrderItems> oiset = o.getOrderItemses();
		AssertJUnit.assertEquals(3, oiset.size());
		a.deleteOrderItems(tmp);
		o = a.getOrderById(1);
		oiset = o.getOrderItemses();
		AssertJUnit.assertEquals(2, oiset.size());
	}
	
	@Test
	public void test16()
	{
		List<Book> booklist = a.getBookList();
		AssertJUnit.assertEquals(10, booklist.size());
	}
	
	@Test
	public void test17()
	{
		Book b = a.getBookById(1);
		AssertJUnit.assertEquals((Integer) 10, (Integer) b.getAvailableAmount());
		
		AssertJUnit.assertEquals((Integer) 0, a.incrementBookAvAm(b, 10));
		Assert.assertEquals((Integer) 20, b.getAvailableAmount());
		
		AssertJUnit.assertEquals((Integer) 0, a.incrementBookAvAm(b, -10));
		Assert.assertEquals((Integer) 10, b.getAvailableAmount());
		
		AssertJUnit.assertEquals(true, a.incrementBookAvAm(b, -20) == -1);
		Assert.assertEquals((Integer) 10, b.getAvailableAmount());
		
	}
}
