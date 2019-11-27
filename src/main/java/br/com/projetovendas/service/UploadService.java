package br.com.projetovendas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
	
	@Value("${upload.file.path}")
	private String destino;
	
	public void salvar(MultipartFile file) {
		this.salvarUpload(file);
	}
	
	private void salvarUpload(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path caminho = Paths.get(this.destino + file.getOriginalFilename());
			Files.write(caminho, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}