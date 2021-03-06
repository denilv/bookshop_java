package bookshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import bookshop.code.Customer;
import bookshop.code.DAOoperations;
import bookshop.code.OrderItems;
import bookshop.service.CustomerValidator;

@Controller
public class CustomerController
{
	private DAOoperations dao = new DAOoperations();
	private CustomerValidator customerValidator = new CustomerValidator();
	private Customer currentCustomer;
	List<OrderItems> cartItems = new ArrayList<OrderItems>();
	// private Customer customer;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model)
	{
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("customer") Customer customer, Model model)
	{

		String error = customerValidator.validate(customer);

		if (error != null)
		{
			model.addAttribute("error", error);
			return "registration";
		}
		customer.setAdmin(0);
		dao.addCustomer(customer);
		return "redirect:/books";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(WebRequest request, Model model)
	{
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("customer") Customer customer, Model model)
	{

		Customer resultCustomer = customerValidator.login(customer);

		if (resultCustomer == null)
		{
			String error = "Invalid email or password";
			model.addAttribute("error", error);
			return "login";
		}
		currentCustomer = resultCustomer;
		return "redirect:/books";
	}

	@RequestMapping(value = "/logout")
	public String logout(WebRequest request, Model model)
	{
		currentCustomer = null;
		// ModelAndView mav = new ModelAndView("logout");
		// return mav;
		return "logout";
	}

	@RequestMapping(value = "/cart/addbook")
	public ModelAndView showCart(WebRequest request, Model model)
	{
		ModelAndView mav = new ModelAndView("cart");

		return mav;
	}

	@RequestMapping(value = "/admin")
	public String admin(WebRequest request, Model model)
	{
		if (currentCustomer == null || currentCustomer.getAdmin() == 0)
		{
			String error = "You do not have admin priveleges!";
			model.addAttribute("error", error);
		}
		return "admin";
	}
}
