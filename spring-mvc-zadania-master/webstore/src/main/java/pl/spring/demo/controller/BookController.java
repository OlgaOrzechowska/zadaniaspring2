package pl.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * Method enables a default books view
	 * 
	 * @return string with a books view name
	 */
	@RequestMapping
	public final String list() {
		return ViewNames.BOOKS;
	}

	/**
	 * Method enables to see find-a-book view
	 * 
	 * @return string with a view name
	 */
	@RequestMapping(value = "/find")
	public final String findBooks(final Model model) {
		return ViewNames.FIND;
	}

	/**
	 * Method collects info about all books
	 * 
	 * @return ModelAndView list of all books and view to show
	 */
	@RequestMapping("/all")
	public final ModelAndView allBooks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findAllBooks());
		return modelAndView;
	}

	/**
	 * Method collects info about books found by title
	 * 
	 * @param title
	 *            title of a book to find
	 * @return ModelAndView list of books with the title and view to show
	 */
	@RequestMapping("/books-by-title")
	public final ModelAndView booksByTitle(@RequestParam("title") final String title) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findBooksByTitle(title));
		return modelAndView;
	}

	/**
	 * Method collects info about books found by author
	 * 
	 * @param author
	 *            author of a book to find
	 * @return ModelAndView list of books with the author and view to show
	 */
	@RequestMapping("/books-by-author")
	public final ModelAndView booksByAuthor(@RequestParam("author") final String author) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findBooksByAuthor(author));
		return modelAndView;
	}

	/**
	 * Method collects info about books found by author and title
	 * 
	 * @param author
	 *            author of a book to find
	 * @param title
	 *            title of a book to find
	 * @return ModelAndView list of books with the author and view to show
	 */
	@RequestMapping("/books-by-many")
	public final ModelAndView booksByMany(@RequestParam("author") final String author,
			@RequestParam("title") final String title) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findBooksByAuthorAndTitle(author, title));
		return modelAndView;
	}

	/**
	 * Method collects info about one book by id
	 * 
	 * @param id
	 *            id of a book to find
	 * @return ModelAndView a book with specific id and view to show
	 */
	@RequestMapping("/book")
	public final ModelAndView bookById(@RequestParam("id") final long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOK);
		modelAndView.addObject(ModelConstants.BOOK, bookService.findBookById(id));
		return modelAndView;
	}

	/**
	 * Deletes the book with specific id
	 * 
	 * @param id
	 *            id of a book to delete
	 * @return view to show
	 */
	@RequestMapping("/delete")
	public final ModelAndView deleteBookById(@RequestParam("id") final long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.DELETE);
		modelAndView.addObject(ModelConstants.BOOK, bookService.findBookById(id));
		bookService.deleteBook(id);
		return modelAndView;
	}

	/**
	 * Creates a book to be added to the database
	 * 
	 * @param model
	 *            model that contains the book object
	 * @return view with the form for title and authors of the book to add
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public final String book(final Model model) {
		model.addAttribute(ModelConstants.NEW_BOOK, new BookTo());
		return ViewNames.ADD_BOOK;
	}

	/**
	 * Adds a book with authors and title specified by user
	 * 
	 * @param newBook
	 *            is a book to add
	 * @param model
	 *            model for a book to add
	 * @return model with new book and view with result
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public final ModelAndView addBook(@ModelAttribute("newBook") BookTo newBook, final Model model) {
		String authors = newBook.getAuthors();
		String title = newBook.getTitle();
		ModelAndView modelAndView = new ModelAndView();
		if (title.isEmpty() || authors.isEmpty()) {
			modelAndView.addObject(ModelConstants.ANNOUNCEMENT, "All parameters must be filled!");
			modelAndView.setViewName(ViewNames.ADD_BOOK);
			return modelAndView;
		}
		newBook = bookService.saveBook(newBook);
		modelAndView.addObject(newBook);
		modelAndView.setViewName(ViewNames.RESULT);
		model.addAttribute(ModelConstants.NEW_BOOK, newBook);
		return modelAndView;
	}

	/**
	 * Binder initialization
	 */
	@InitBinder
	public final void initialiseBinder(final WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
