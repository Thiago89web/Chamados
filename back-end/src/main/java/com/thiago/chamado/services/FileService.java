package com.thiago.chamado.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thiago.chamado.entity.FileEntity;
import com.thiago.chamado.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	public FileEntity  store(MultipartFile file) throws IOException {		
		
		String fileNome = StringUtils.cleanPath(file.getOriginalFilename());		
		FileEntity fileEntity = new FileEntity(fileNome, file.getContentType(), file.getBytes());		
		fileEntity = fileRepository.save(fileEntity);						
		return fileEntity;		
	}
	
	public FileEntity getFileId(Long id) {
	  return fileRepository.findById(id).get();
	}
	  
	public Stream<FileEntity> getAllFiles() {
	  return fileRepository.findAll().stream();
	}

}
