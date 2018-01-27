package br.gov.cgu.mbt.aplicacao.email;

import br.gov.cgu.email.client.EmailClient;
import br.gov.cgu.email.client.Envio;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class GerenciadorDeEmail {
    private static  final Integer MODULO_SISTEMA = 3030;

    @Value("${email.service}")
    private String enderecoServico;

    @Value("${email.token}")
    private String tokenModulo;

    @Autowired private Environment environment;

    private EmailClient emailClient;

    public void gravarEmail(String destinatario, String assunto, String mensagem) {
        gravarEmail(Arrays.asList(destinatario.split("\\s*,\\s*")), assunto, mensagem);
    }

    public void gravarEmailComTemplate(String destinatario, String idTemplate, Map<String, Object> model) {
        gravarEmailComTemplate(Arrays.asList(destinatario.split("\\s*,\\s*")), idTemplate, model);
    }

    public void gravarEmail(List<String> destinatarios, String assunto, String mensagem){
        Envio envio = criarEnvio(destinatarios);
        envio.setAssunto(assunto);
        envio.setMensagem(mensagem);
        enviarEmail(envio);
    }

    public void gravarEmailComTemplate(List<String> destinatarios, String template, Map<String, Object> model){
        Envio envio = criarEnvio(destinatarios);
        envio.setTemplate(template);
        envio.setModel(model);
        enviarEmail(envio);
    }

    private void enviarEmail(Envio envio) {
        try{
            if (!ArrayUtils.contains(environment.getActiveProfiles(), "teste")) {
                getEmailClient().salvar(envio);
            }
        }catch (Exception e){
            throw new EmailNaoEnviadoException(e);
        }
    }

    private Envio criarEnvio(List<String> destinatarios) {
        Envio envio = new Envio();
        envio.setDestinatarios(new HashSet<>(destinatarios));
        envio.setModulo(MODULO_SISTEMA);
        envio.setToken(tokenModulo);
        return envio;
    }

    public EmailClient getEmailClient() {
        if (this.emailClient == null) {
            this.emailClient = new EmailClient(enderecoServico);
        }
        return emailClient;
    }

    public void setEmailClient(EmailClient emailClient) {
        this.emailClient = emailClient;
    }
}
