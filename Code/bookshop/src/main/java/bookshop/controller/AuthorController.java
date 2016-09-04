package bookshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bookshop.code.Author;
import bookshop.code.Book;
import bookshop.code.DAOoperations;

@Controller
public class AuthorController
{
	private DAOoperations dao = new DAOoperations();

	@RequestMapping("/authors")
	public ModelAndView home()
	{
		List<Author> authors = dao.getAuthorList();
		// System.out.println(authors.size());
		ModelAndView model = new ModelAndView("authors");
		model.addObject("authors", authors);
		return model;
	}

	@RequestMapping(value = "/authors/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("authorAttribute") Book book,
			@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		dao.deleteAuthor(dao.getAuthorById(id));
		return "redirect:/authors";
	}

	@RequestMapping(value = "/authors/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		List<Book> allbooks = dao.getBookList();// dao.getAuthorListByLastname("");
		model.addAttribute("authorAttribute", dao.getAuthorById(id));
		model.addAttribute("allbooks", allbooks);
		return "editauthor";
	}

	@RequestMapping(value = "/authors/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("authorAttribute") Author author,
			@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		author.setId(id);
		dao.editAuthor(author);
		model.addAttribute("id", id);
		return "editedbook";
	}

	@RequestMapping(value = "/authors/add", method = RequestMethod.GET)
	public String getAdd(Model model)
	{
		List<Book> allbooks = dao.getBookList();// dao.getAuthorListByLastname("");
		Author author = new Author();
		model.addAttribute("authorAttribute", author);
		model.addAttribute("allbooks", allbooks);
		return "addauthor";
	}

	@RequestMapping(value = "/authors/add", method = RequestMethod.POST)
	public String saveAdd(@ModelAttribute("authorAttribute") Author author, Model model)
	{
		dao.addAuthor(author);
		return "addedauthor";
	}
}
