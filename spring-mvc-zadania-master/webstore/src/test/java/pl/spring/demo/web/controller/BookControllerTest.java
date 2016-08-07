package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Test class to book controller.
 * 
 * @author OORZECHO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "controller-test-configuration.xml")
@WebAppConfiguration
public class BookControllerTest {

	@Autowired
	private BookService bookService;

	private MockMvc mockMvc;

	/**
	 * Before function called before every test.
	 */
	@Before
	public final void setup() {
		Mockito.reset(bookService);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		BookController bookController = new BookController();
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();
		// Due to fact, that We are trying to construct real Bean - Book
		// Controller, we have to use reflection to mock existing field book
		// service
		ReflectionTestUtils.setField(bookController, "bookService", bookService);
	}

	/**
	 * Checks an operation of adding a new book. Correct view name is result,
	 * correct attribute is the added book.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testAddBookPagePost() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		Mockito.when(bookService.saveBook(Mockito.any())).thenReturn(testBook);

		// when
		ResultActions resultActions = mockMvc.perform(post("/books/add").flashAttr("newBook", testBook));
		// then
		resultActions.andExpect(view().name("result"))
				.andExpect(model().attribute(ModelConstants.NEW_BOOK, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						BookTo book = (BookTo) argument;
						return null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks if add form is presented to the user. Correct view name is add.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testAddBookPageGet() throws Exception {

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/add"));
		// then
		resultActions.andExpect(view().name("add"));

	}

	/**
	 * Checks an operation of deleting book by its id. Correct view name is
	 * delete, correct attribute is the deleted book.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testDeleteBookPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		Mockito.when(bookService.findBookById(Mockito.any())).thenReturn(testBook);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/delete").param("id", "1"));
		// then
		resultActions.andExpect(view().name("delete"))
				.andExpect(model().attribute(ModelConstants.BOOK, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						BookTo book = (BookTo) argument;
						return null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks an operation of showing all books. Correct view name is books,
	 * correct attribute is the list of all books.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testAllBooksPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		List<BookTo> books = new ArrayList<>();
		books.add(testBook);
		Mockito.when(bookService.findAllBooks()).thenReturn(books);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/all"));
		// then
		resultActions.andExpect(view().name("books"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						@SuppressWarnings("unchecked")
						List<BookTo> books = (ArrayList<BookTo>) argument;
						BookTo book = books.get(0);
						return null != book && testBook.equals(book);
					}
				}));
	}

	/**
	 * Checks an operation of finding books by its author. Correct view name is
	 * books, correct attribute is the list of books with the author.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBookByAuthorPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		List<BookTo> books = new ArrayList<>();
		books.add(testBook);
		Mockito.when(bookService.findBooksByAuthor(Mockito.anyString())).thenReturn(books);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/books-by-author").param("author", "Test Author"));
		// then
		resultActions.andExpect(view().name("books"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						@SuppressWarnings("unchecked")
						List<BookTo> books = (ArrayList<BookTo>) argument;
						BookTo book = books.get(0);
						return books.size() == 1 && null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks an operation of finding books by its title. Correct view name is
	 * books, correct attribute is the list of books with the title.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBookByTitlePage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		List<BookTo> books = new ArrayList<>();
		books.add(testBook);
		Mockito.when(bookService.findBooksByTitle(Mockito.anyString())).thenReturn(books);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/books-by-title").param("title", "Test title"));
		// then
		resultActions.andExpect(view().name("books"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						@SuppressWarnings("unchecked")
						List<BookTo> books = (ArrayList<BookTo>) argument;
						BookTo book = books.get(0);
						return null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks an operation of finding books by its title and author. Correct
	 * view name is books, correct attribute is the list of books with given
	 * title and author.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBookByTitleAndAuthorPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		List<BookTo> books = new ArrayList<>();
		books.add(testBook);
		Mockito.when(bookService.findBooksByAuthorAndTitle(Mockito.anyString(), Mockito.anyString())).thenReturn(books);

		// when
		ResultActions resultActions = mockMvc
				.perform(get("/books/books-by-many").param("title", "Test title").param("author", "Test Author"));
		// then
		resultActions.andExpect(view().name("books"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						@SuppressWarnings("unchecked")
						List<BookTo> books = (ArrayList<BookTo>) argument;
						BookTo book = books.get(0);
						return null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks an operation of presenting book with a given id. Correct view name
	 * is book, correct attribute is the book with a given id.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBookByIdPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		Mockito.when(bookService.findBookById(Mockito.anyLong())).thenReturn(testBook);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/book").param("id", "1"));
		// then
		resultActions.andExpect(view().name("book"))
				.andExpect(model().attribute(ModelConstants.BOOK, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(final Object argument) {
						BookTo book = (BookTo) argument;
						return null != book && testBook.equals(book);
					}
				}));

	}

	/**
	 * Checks if find form is presented to the user. Correct view name is find.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testFindPage() throws Exception {

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/find"));
		// then
		resultActions.andExpect(view().name("find"));

	}

	/**
	 * Sample method which converts any object from Java to String
	 * 
	 * @param obj
	 *            object to convert
	 * @return JSON string
	 */
	@SuppressWarnings("unused")
	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
