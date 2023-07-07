package dev.patricksilva.model.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PhotoTest {

	@Test
	public void testGetId() {
		String id = "123";
		Photo photo = new Photo();
		photo.setId(id);

		assertEquals(id, photo.getId());
	}

	@Test
	public void testGetFilename() {
		String filename = "photo.jpg";
		Photo photo = new Photo();
		photo.setFilename(filename);

		assertEquals(filename, photo.getFilename());
	}

	@Test
	public void testGetContentType() {
		String contentType = "image/jpeg";
		Photo photo = new Photo();
		photo.setContentType(contentType);

		assertEquals(contentType, photo.getContentType());
	}

	@Test
	public void testSetId() {
		String id = "456";
		Photo photo = new Photo();
		photo.setId(id);

		assertEquals(id, photo.getId());
	}

	@Test
	public void testSetFilename() {
		String filename = "photo.png";
		Photo photo = new Photo();
		photo.setFilename(filename);

		assertEquals(filename, photo.getFilename());
	}

	@Test
	public void testSetContentType() {
		String contentType = "image/png";
		Photo photo = new Photo();
		photo.setContentType(contentType);

		assertEquals(contentType, photo.getContentType());
	}

	@Test
	public void testSetIdNull() {
		Photo photo = new Photo();
		photo.setId(null);

		assertNull(photo.getId());
	}

	@Test
	public void testSetFilenameNull() {
		Photo photo = new Photo();
		photo.setFilename(null);

		assertNull(photo.getFilename());
	}

	@Test
	public void testSetContentTypeNull() {
		Photo photo = new Photo();
		photo.setContentType(null);

		assertNull(photo.getContentType());
	}

	@Test
	public void testEmptyConstructor() {
		Photo photo = new Photo();

		assertNull(photo.getId());
		assertNull(photo.getFilename());
		assertNull(photo.getContentType());
	}

}