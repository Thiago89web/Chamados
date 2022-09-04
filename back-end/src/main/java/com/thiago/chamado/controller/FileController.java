package com.thiago.chamado.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiago.chamado.entity.FileEntity;
import com.thiago.chamado.entity.response.ResponseFile;
import com.thiago.chamado.entity.response.ResponseMessage;
import com.thiago.chamado.services.ChamadoService;
import com.thiago.chamado.services.FileService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/files")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ChamadoService chamadoService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		FileEntity fileEntity = new FileEntity();
		String message = "";
		Long idFile;
		try {
			//fileService.store(file);
			
			fileEntity = fileService.store(file);
			idFile = fileEntity.getId();
			
			
			message = " Carregou o arquivo com sucesso: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, idFile));
		} catch (Exception e) {
			message = "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	/*@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
					.path(dbFile.getId().toString()).toUriString();
			return new ResponseFile(dbFile.getNome(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}*/
	
	@GetMapping("/chamadoFile/{id}")
	public ResponseEntity<List<ResponseFile>> getListFiles(@PathVariable Long id) {
			
		List<ResponseFile> files = chamadoService.getChamadoAndFileId(id).map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
					.path(dbFile.getFileEntity().getId().toString()).toUriString();
			return new ResponseFile(
					dbFile.getFileEntity().getNome(), 
					fileDownloadUri, 
					dbFile.getFileEntity().getType(), 
					dbFile.getFileEntity().getData().length);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}		

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		FileEntity fileEntity = fileService.getFileId(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getNome() + "\"")
				.body(fileEntity.getData());
	}
	

}
