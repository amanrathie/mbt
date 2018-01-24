package br.gov.cgu.projetoexemplosb.aplicacao.upload;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class UploadService {

    private final String uploadPath;

    public UploadService(@Value("${upload.path}") String uploadPath) {
        this.uploadPath = uploadPath;
        new File(uploadPath).mkdirs();
    }

    public String salvar(MultipartFile arquivo) {
        validarArquivoVazio(arquivo);
        String nome = UUID.randomUUID().toString() + "_" + arquivo.getName();
        String diretorio = uploadPath;
        if (!diretorio.endsWith("/")) {
            diretorio += "/";
        }

        File f = new File(diretorio + nome);
        try (OutputStream outputStream = new FileOutputStream(f)) {
            IOUtils.copy(arquivo.getInputStream(), outputStream);
        } catch (IOException e) {
            throw new UploadException(e);
        }
        return nome;
    }

    private void validarArquivoVazio(MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            throw new UploadException("Arquivo vazio!");
        }
    }

    public File get(String uid) {
        return new File(uploadPath + uid);
    }

}
