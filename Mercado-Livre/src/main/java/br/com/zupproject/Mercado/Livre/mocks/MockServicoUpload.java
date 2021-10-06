package br.com.zupproject.Mercado.Livre.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MockServicoUpload {

	public List<String> enviaImagens(java.util.List<MultipartFile> imagens) {
		List<String> links = new ArrayList<>();
		for(MultipartFile imagem : imagens) {
			links.add("www.realWebsite." + imagem.getName() + imagem.getSize() + ".com");
		}
		return links;
	}

}
