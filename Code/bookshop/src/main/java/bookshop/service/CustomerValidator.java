package bookshop.service;

import bookshop.code.Customer;
import bookshop.code.DAOoperations;

public class CustomerValidator
{
	private DAOoperations dao = new DAOoperations();

	public String validate(Customer customer)
	{

		if (customer.getFName().length() == 0)
		{
			return "FirstName is empty!";
		}
		if (customer.getLName().length() == 0)
		{
			return "LastName is empty!";
		}
		if (customer.getContactNumber().length() == 0)
		{
			return "ContactNumber is empty!";
		}
		if (customer.getAddress().length() == 0)
		{
			return "Address is empty!";
		}
		if (dao.getCustomerByEmail(customer.getEmail()) != null)
		{
			return "Customer with email - " + customer.getEmail() + " already exists!";
		}

		if (customer.getPassword().length() < 8 || customer.getPassword().length() > 20)
		{
			return "Your password is shorter than 8 chars or longer than 20 chars";
		}
		return null;
	}

	public Customer login(Customer c)
	{
		Customer customer = dao.getCustomerByEmail(c.getEmail());
		if (customer != null)
		{
			if (customer.getPassword().equals(c.getPassword()))
			{
				// System.out.println(c.getPassword() + " " +
				// customer.getPassword());
				return customer;
			} else
			{
				return null;
			}
		}
		return null;
	}
}
