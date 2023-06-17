package dev.patricksilva.model.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import dev.patricksilva.view.LoadFileResponse;

public interface FileService {

	public String addFile(MultipartFile upload) throws IOException;

	public LoadFileResponse downloadFile(String id) throws IOException;
}