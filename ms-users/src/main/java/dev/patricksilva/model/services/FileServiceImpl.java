package dev.patricksilva.model.services;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

import dev.patricksilva.view.LoadFileResponse;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	/**
	 * Adiciona um arquivo no sistema.
	 *
	 * @param upload O arquivo a ser adicionado.
	 * @return O ID do arquivo adicionado.
	 * @throws IOException Em caso de erro de leitura do arquivo.
	 */
	@Override
	public String addFile(MultipartFile upload) throws IOException {
		DBObject metadata = new BasicDBObject();

		metadata.put("fileSize", upload.getSize());

		Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
				metadata);

		return fileID.toString();
	}

	/**
	 * Faz o download de um arquivo do sistema.
	 *
	 * @param id O ID do arquivo a ser baixado.
	 * @return Um objeto LoadFileResponse contendo informações do arquivo baixado.
	 */
	@Override
	public LoadFileResponse downloadFile(String id) {
		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

		LoadFileResponse loadFile = new LoadFileResponse();

		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			loadFile.setFileName(gridFSFile.getFilename());
			loadFile.setFileType(gridFSFile.getMetadata().get("_contentType").toString());
			loadFile.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());

			try {
				loadFile.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return loadFile;
	}
}