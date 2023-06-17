package dev.patricksilva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.patricksilva.model.entities.Photo;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.repository.UserRepository;
import dev.patricksilva.model.security.jwt.payload.response.MessageResponse;
import dev.patricksilva.model.services.FileService;
import dev.patricksilva.view.LoadFileResponse;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MS-USERS - Photo Profile", description="Endpoints Management.")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/users/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Upload a User's photo.
	 * 
	 * @param file
	 * @param id
	 * @return A message response about the file uploaded.
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		String fileId;
		try {
			fileId = fileService.addFile(file); // Adicionar o arquivo e obter seu identificador
		} catch (java.io.IOException e) {
			throw new IOException("Erro ao adicionar o arquivo", e);
		}
		Photo photo = new Photo();
		photo.setId(fileId);
		// Associar o objeto Photo ao usuário
		user.setPhoto(photo); 
		userRepository.save(user); 

		return ResponseEntity.ok(new MessageResponse("Upload realizado com sucesso!"));
	}

	/**
	 * Retrieves the User's photo storaged.
	 * 
	 * @param id
	 * @return The User's photo storaged.
	 * @throws IOException
	 * @throws java.io.IOException
	 */
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException, java.io.IOException {
		LoadFileResponse loadFile = fileService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFileName() + "\"")
				.body(new ByteArrayResource(loadFile.getFile()));
	}
}