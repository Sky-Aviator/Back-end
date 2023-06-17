package dev.patricksilva.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Photos")
public class Photo {

	@Id
	private String id;

	private String filename;

	private String contentType;

	// Getters and Setters
	
	/**
	 * Retrieves the photo's Id.
	 * 
	 * @return The photo's Id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the photo's Id.
	 * 
	 * @param id - The photo's Id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the photo's file name.
	 * 
	 * @return The photo's file name.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the photo's file name.
	 * 
	 * @param id - The photo's file name.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Retrieves the photo's content type.
	 * 
	 * @return The photo's content type.
	 */
	public String getContentType() {
		return contentType;
	}
	
	/**
	 * Sets the photo's content type.
	 * 
	 * @param id - The photo's content type.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}