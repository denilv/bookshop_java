package bookshop.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Set;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bookshop.code.Author;
import bookshop.code.Book;
import bookshop.code.BookSearchCriteria;
import bookshop.code.DAOoperations;
import bookshop.code.Genre;
import bookshop.code.PublHouse;

@Controller
public class BookController
{

	private DAOoperations dao = new DAOoperations();

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception
	{
		binder.registerCustomEditor(Set.class, "authors", new CustomCollectionEditor(Set.class)
		{
			protected Object convertElement(Object element)
			{
				if (element instanceof String)
				{
					Author author = dao.getAuthorById(Integer.parseInt(element.toString()));
					return author;
				}
				return null;
			}
		});

		binder.registerCustomEditor(Genre.class, new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				if (text.isEmpty())
				{
					this.setValue(null);
				} else
				{
					Integer genreid = Integer.parseInt(text);
					Genre genre = dao.getGenreById(genreid);
					this.setValue(genre);
				}
			}

			public String getAsText()
			{
				if (getValue() == null)
				{
					return null;
				} else
				{
					return Integer.toString(((Genre) getValue()).getId());
				}
			}
		});

		binder.registerCustomEditor(Author.class, new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				if (text.isEmpty())
				{
					this.setValue(null);
				} else
				{
					Integer id = Integer.parseInt(text);
					Author author = dao.getAuthorById(id);
					this.setValue(author);
				}
			}

			public String getAsText()
			{
				if (getValue() == null)
				{
					return null;
				} else
				{
					return Integer.toString(((Author) getValue()).getId());
				}
			}
		});

		binder.registerCustomEditor(PublHouse.class, new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				Integer id = Integer.parseInt(text);
				PublHouse publHouse = dao.getPublHouseById(id);
				this.setValue(publHouse);
			}

			public String getAsText()
			{
				if (getValue() == null)
				{
					return null;
				} else
				{
					return Integer.toString(((PublHouse) getValue()).getId());
				}
			}
		});
	}

	@RequestMapping("/hello")
	public ModelAndView helloWorld()
	{

		String message = "Hello World, Spring 3.0!";
		System.out.println(message);
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("message", message);
		return mav;
	}

	@RequestMapping("/books")
	public ModelAndView home()
	{
		List<Book> books = dao.getBookList();
		ModelAndView model = new ModelAndView("books");
		model.addObject("books", books);
		return model;
	}

	@RequestMapping(value = "/books/delete", method = RequestMethod.GET)
	public String deleteBook(@ModelAttribute("bookAttribute") Book book,
			@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		dao.deleteBook(dao.getBookById(id));
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		List<Author> allauthors = dao.getAuthorList();// dao.getAuthorListByLastname("");
		List<Genre> allgenres = dao.getGenreList();
		List<PublHouse> allpublhouses = dao.getPublHouseList();
		model.addAttribute("bookAttribute", dao.getBookById(id));
		model.addAttribute("allauthors", allauthors);
		model.addAttribute("allgenres", allgenres);
		model.addAttribute("allpublhouses", allpublhouses);
		return "editbook";
	}

	@RequestMapping(value = "/books/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("bookAttribute") Book book,
			@RequestParam(value = "id", required = true) Integer id, Model model)
	{
		book.setId(id);
		dao.editBook(book);
		model.addAttribute("id", id);
		return "editedbook";
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public String getAdd(Model model)
	{
		Book book = new Book();
		List<Genre> allgenres = dao.getGenreList();
		List<Author> allauthors = dao.getAuthorList();
		List<PublHouse> allpublhouses = dao.getPublHouseList();
		model.addAttribute("bookAttribute", book);
		model.addAttribute("allauthors", allauthors);
		model.addAttribute("allgenres", allgenres);
		model.addAttribute("allpublhouses", allpublhouses);
		return "addbook";
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public String saveAdd(@ModelAttribute("bookAttribute") Book book, Model model)
	{
		dao.addBook(book);
		return "addedbook";
	}

	@RequestMapping(value = "/books/search", method = RequestMethod.GET)
	public String getSearch(Model model)
	{
		List<Genre> allgenres = dao.getGenreList();
		List<Author> allauthors = dao.getAuthorList();
		model.addAttribute("allauthors", allauthors);
		model.addAttribute("allgenres", allgenres);
		BookSearchCriteria bookCriteria = new BookSearchCriteria();
		model.addAttribute("bookCriteria", bookCriteria);
		return "searchbook";
	}

	@RequestMapping(value = "/books/search", method = RequestMethod.POST)
	public String searchResult(@ModelAttribute("bookCriteria") BookSearchCriteria bookCriteria, Model model)
	{

		List<Book> books = dao.searchBook(bookCriteria);
		model.addAttribute("books", books);
		return "searchresult";
	}

}
