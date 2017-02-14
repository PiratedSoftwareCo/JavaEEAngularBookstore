package com.qa.student.bookstore.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.student.bookstore.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookEndPointTest {

	@InjectMocks
	private BookEndPoint subject;

	private static final String MOCKSTRING = "[{\"id\": 1,\"author\": \"JK Rowling\",\"title\": \"Harry Potter\",\"genre\": \"Fantasy\",\"yearPublished\": \"1996\"}]";

	private static final String MOCK_DELETE_MESSAGE = "{\"message\": \"book sucessfully removed\"}";

	@Mock
	private BookService mockService;

	@Test
	public void testGetAllBooksAreReturnedCorrectly() {
		Mockito.when(mockService.getAllBooks()).thenReturn(MOCKSTRING);
		String bookString = subject.getBooksAsJson();
		Mockito.verify(mockService).getAllBooks();
		Assert.assertEquals(MOCKSTRING, bookString);
	}

	@Test
	public void testAddBookCallsServiceWithGivenBook() {
		Mockito.when(mockService.addNewBook(MOCKSTRING)).thenReturn(MOCKSTRING);
		String bookString = subject.addNewBookToMap(MOCKSTRING);
		Mockito.verify(mockService).addNewBook(MOCKSTRING);
		Assert.assertEquals(MOCKSTRING, bookString);
	}

	@Test
	public void testDeleteBookCallsDeleteServiceWithGivenId() {

		Mockito.when(mockService.deleteBook(Mockito.anyInt())).thenReturn(MOCK_DELETE_MESSAGE);
		String deleteMessage = subject.deleteBookFromBookStore(1);
		Mockito.verify(mockService).deleteBook(1);
		Assert.assertEquals(MOCK_DELETE_MESSAGE, deleteMessage);
	}

	@Test
	public void testReplaceBookCallsEditServiceWithGivenBookAndId() {
		Mockito.when(mockService.replaceBook(1, MOCKSTRING)).thenReturn(MOCKSTRING);
		String bookString = subject.replaceBookFromBookStore(1, MOCKSTRING);
		Mockito.verify(mockService).replaceBook(1, MOCKSTRING);
		Assert.assertEquals(MOCKSTRING, bookString);
	}

}
