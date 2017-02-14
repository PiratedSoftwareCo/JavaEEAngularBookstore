package com.qa.bookstore.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.bookstore.model.Book;

public class JSONUtilTest {

	private JSONUtil util;
	private Book testBook;

	@Before
	public void init() {
		util = new JSONUtil();
		testBook = new Book("Stephen King", "IT", "Horror", "1998");
	}

	@Test
	public void testBookIsConvertedToJSONCorrectly() {
		String bookToJson = util.getJSONForObject(testBook);
		String assertionString = "{\"author\":\"Stephen King\",\"title\":\"IT\",\"genre\":\"Horror\",\"yearPublished\":\"1998\"}";
		Assert.assertEquals(assertionString, bookToJson);
	}

	@Test
	public void testJsonIsConvertedToBooklyCorrectly() {
		String bookString = "{\"author\":\"Stephen King\",\"title\":\"IT\",\"genre\":\"Horror\",\"yearPublished\":\"1998\"}";
		Book jsonToBook = util.getObjectForJSON(bookString, Book.class);
		Assert.assertEquals(jsonToBook.getAuthor(), "Stephen King");
		Assert.assertEquals(jsonToBook.getTitle(), "IT");
	}

}
