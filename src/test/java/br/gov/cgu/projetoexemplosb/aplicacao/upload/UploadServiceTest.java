package br.gov.cgu.projetoexemplosb.aplicacao.upload;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class UploadServiceTest {

    private UploadService service = new UploadService(System.getProperty("java.io.tmpdir"));

    @Test(expected = UploadException.class)
    public void lanca_exception_pra_arquivo_vazio() throws Exception {
        MultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "".getBytes());

        service.salvar(file);
    }

    @Test
    public void salva_com_sucesso() throws Exception {
        MultipartFile file = new MockMultipartFile("data", "fileme.txt", "image/jpeg", "aaaaa".getBytes());

        String nome = service.salvar(file);

        File file1 = service.get(nome);
        assertNotNull(file1);
    }

}