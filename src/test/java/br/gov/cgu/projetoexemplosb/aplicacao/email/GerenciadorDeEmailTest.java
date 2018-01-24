package br.gov.cgu.projetoexemplosb.aplicacao.email;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.email.client.EmailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GerenciadorDeEmailTest {

    @Mock Environment env;
    @InjectMocks private GerenciadorDeEmail gerenciadorDeEmail = new GerenciadorDeEmail();

    @Test
    public void envio_de_email_sem_template() {
        String destinatario = "email@email.gov.br";
        String assunto = "Assunto do Email de Teste";
        String mensagem = "Mensagem para teste de envio de email sem template";

        when(env.getActiveProfiles()).thenReturn(new String[]{"h2"});

        EmailClient emailClientMock = mock(EmailClient.class);
        gerenciadorDeEmail.setEmailClient(emailClientMock);
        doNothing().when(gerenciadorDeEmail.getEmailClient()).salvar(any());

        gerenciadorDeEmail.gravarEmail(destinatario, assunto, mensagem);
    }

    @Test
    public void envio_de_email_sem_template_com_profile_de_envio() {
        String destinatario = "email@email.gov.br";
        String assunto = "Assunto do Email de Teste";
        String mensagem = "Mensagem para teste de envio de email sem template";

        when(env.getActiveProfiles()).thenReturn(new String[]{"teste"});

        EmailClient emailClientMock = mock(EmailClient.class);
        gerenciadorDeEmail.setEmailClient(emailClientMock);
        doNothing().when(gerenciadorDeEmail.getEmailClient()).salvar(any());

        gerenciadorDeEmail.gravarEmail(destinatario, assunto, mensagem);
    }

    @Test
    public void envio_de_email_com_template() {
        String destinatario = "email@email.gov.br";
        String template = Constantes.TEMPLATE_EMAIL_NOTIFICACAO_INTERACAO_INDIVIDUAL;
        Map<String, Object> model = new HashMap<>();
        when(env.getActiveProfiles()).thenReturn(new String[]{"h2"});

        EmailClient emailClientMock = mock(EmailClient.class);
        gerenciadorDeEmail.setEmailClient(emailClientMock);
        doNothing().when(gerenciadorDeEmail.getEmailClient()).salvar(any());

        gerenciadorDeEmail.gravarEmailComTemplate(destinatario, template, model);
    }


    @Test(expected = EmailNaoEnviadoException.class)
    public void envio_de_email_com_template__deve_lancar_exception_em_caso_de_erro() throws Exception {
        String destinatario = "email@email.gov.br";
        String template = Constantes.TEMPLATE_EMAIL_NOTIFICACAO_INTERACAO_INDIVIDUAL;
        Map<String, Object> model = new HashMap<>();

        EmailClient emailClientMock = mock(EmailClient.class);

        doThrow(new RuntimeException("xpto")).when(emailClientMock).salvar(any());
        gerenciadorDeEmail.setEmailClient(emailClientMock);

        gerenciadorDeEmail.gravarEmailComTemplate(destinatario, template, model);
    }

    @Test(expected = EmailNaoEnviadoException.class)
    public void envio_de_email_sem_template__deve_lancar_exception_em_caso_de_erro() throws Exception {
        String destinatario = "email@email.gov.br";
        String assunto = "Assunto do Email de Teste";
        String mensagem = "Mensagem para teste de envio de email sem template";

        EmailClient emailClientMock = mock(EmailClient.class);
        gerenciadorDeEmail.setEmailClient(emailClientMock);
        doThrow(new RuntimeException("xpto")).when(emailClientMock).salvar(any());
        gerenciadorDeEmail.setEmailClient(emailClientMock);

        gerenciadorDeEmail.gravarEmail(destinatario, assunto, mensagem);
    }
}