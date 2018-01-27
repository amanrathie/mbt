package br.gov.cgu.mbt.web.auth;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.auth.BuscadorDePerfil;
import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.auth.GerenciadorDeUsuario;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioSemPerfilException;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.auth.GerenciarUsuarioController;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static br.gov.cgu.mbt.web.auth.GerenciarUsuarioController.OPERACAO_COM_SUCESSO;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GerenciarUsuarioControllerTest extends ControllerTest {

    public static final Perfil ADMINISTRADOR = new Perfil(0, "Administrador");

    private GerenciadorDeUsuario gerenciador = mock(GerenciadorDeUsuario.class);
    private BuscadorDeUsuario buscador = mock(BuscadorDeUsuario.class);
    private BuscadorDePerfil buscadorDePerfil = mock(BuscadorDePerfil.class);
    private GerenciarUsuarioController controller = new GerenciarUsuarioController(gerenciador, buscador, buscadorDePerfil);

    private BuscadorDeUsuario usuarioService = mock(BuscadorDeUsuario.class);
    private ControllerAdvice advice = new ControllerAdvice(usuarioService);

    @Override
    public Object getController() { return controller; }

    @Override
    protected Object getControllerAdvice() {
        return advice;
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(usuarioService.getUsuarioLogado()).thenReturn(mock(Usuario.class));
    }

    @Test
    public void getNovo__retorna_a_view_com_dto_vazio() throws Exception {
        mockMvc.perform(get("/auth/usuario/novo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("usuario"))
                .andExpect(view().name("usuario/usuario"));
    }

    @Test
    public void getId__retorna_a_view_com_dto_do_banco() throws Exception {
        Usuario mock = mock(Usuario.class);
        when(buscador.getParaEdicao(3)).thenReturn(mock);

        mockMvc.perform(get("/auth/usuario/3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("usuario", mock))
                .andExpect(view().name("usuario/usuario"));
    }

    @Test
    public void post__salva_com_sucesso_e_exibe_mensagem() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(10);

        String jsonUsuario = "{\"id\": 10}";

        // when
        when(gerenciador.salvar(any())).thenReturn(usuario);

        // then
        mockMvc.perform(post("/api/auth/usuario/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonUsuario))
                .andExpect(status().isOk());
    }

    @Test
    public void get__exibe_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(get("/auth/usuario/1")
                .param("showMsg", "Minha msg de sucesso!"))
                .andExpect(status().isOk());
    }

    @Test
    public void post__exibe_erro_de_validacao() throws Exception {
        when(gerenciador.salvar(any())).thenThrow(new EntidadeInvalidaException("x"));

        mockMvc.perform(post("/auth/usuario/1")
                .param("usuario", "x"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void get__exibe_erro_de_permissao() throws Exception {
        when(buscador.getParaEdicao(any())).thenThrow(new UsuarioSemPerfilException(ADMINISTRADOR));

        mockMvc.perform(get("/auth/usuario/1")
                .param("usuario", "x"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void post__deve_informar_que_a_entidade_eh_invalida() throws Exception {
        String jsonUsuario = "{\"id\": 10}";

        EntidadeInvalidaException e = new EntidadeInvalidaException("Nome é campo obrigatório.");
        // when
        when(gerenciador.salvar(any())).thenThrow(e);

        // then
        mockMvc.perform(post("/api/auth/usuario/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonUsuario))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nome é campo obrigatório."));
    }

    @Test
    public void alterarAtivacao__deve_retornar_msg_de_sucesso_ao_ativar() throws Exception {
        mockMvc.perform(post("/auth/usuario/ativar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(Constantes.OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_retornar_msg_de_sucesso_ao_inativar() throws Exception {
        mockMvc.perform(post("/auth/usuario/inativar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(Constantes.OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_retornar_msg_de_sucesso_ao_alternar() throws Exception {
        mockMvc.perform(post("/auth/usuario/alternar-ativacao/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(Constantes.OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void gerarChaveApi__deve_retornar_chaveapi_sugerida()  throws Exception {
        // given
        Usuario userMock = mock(Usuario.class);

        // when
        when(usuarioService.getUsuarioLogado()).thenReturn(userMock);
        when(userMock.sugerirChaveApi()).thenReturn("chave_sugerida");

        mockMvc.perform(get("/auth/usuarioLogado/gerarChaveApi"))
                .andExpect(status().isOk())
                .andExpect(content().string("chave_sugerida"));
    }

    @Test
    public void salvarUsuarioLogado__deve_alterar_dados_do_usuario_logado() throws Exception {
        // given
        Usuario userMock = mock(Usuario.class);

        // when
        when(usuarioService.getUsuarioLogado()).thenReturn(userMock);

        mockMvc.perform(post("/auth/usuarioLogado/salvar")
                .param("emailUsuarioLogado", "email@cgu.gov.br")
                .param("telefoneUsuarioLogado", "(61) 9999-9999")
                .param("chaveApiUsuarioLogado", "chave_sugerida")
                .param("frequenciaRecebimentoEmail", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string(OPERACAO_COM_SUCESSO));
    }

    @Test
    public void salvarUsuarioCadu__deve_receber_o_formulario_e_devolver_id_usuario_ativado() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // when
        when(buscador.buscarPorLogin(any())).thenReturn(usuario);
        when(gerenciador.salvar(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("perfis", "1")
                .param("perfis", "2")
                .param("ativo", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void salvarUsuarioCadu__deve_receber_o_formulario_encaminhar_para_desativar_e_devolver_id() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // when
        when(buscador.buscarPorLogin(any())).thenReturn(usuario);
        when(gerenciador.salvar(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("ativo", "false"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void salvarUsuarioCadu__deve_receber_o_formulario_de_usuario_novo_e_devolver_id() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // when
        when(gerenciador.salvar(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("perfis", "1")
                .param("perfis", "2")
                .param("ativo", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void salvarUsuarioCadu__deve_retornar_bad_request() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // when
        when(gerenciador.salvar(any())).thenThrow(EntidadeInvalidaException.class);

        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("perfis", "1")
                .param("perfis", "2")
                .param("ativo", "true"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarUsuarioCadu__deve_ignorar_usuario_inexiste_inativo() throws Exception {
        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("perfis", "1")
                .param("perfis", "2")
                .param("ativo", "false"))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarUsuarioCadu__deve_retornar_Ok_ignorando_inativacao_de_usuario_inativo() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setAtivo(true);

        // when
        when(buscador.buscarPorLogin(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/auth/usuariocadu/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("login", "rodrigovfs")
                .param("cargo", "cargo")
                .param("email", "rodrigo.v.souza@cgu.gov.br")
                .param("cpf", "28057098880")
                .param("nome", "Rodrigo Vilela Fonseca de Souza")
                .param("telefone", "(61) 9999-9999")
                .param("codUciLotacao", "1")
                .param("descUciLotacao", "")
                .param("idUgLotacao", "1")
                .param("descUgLotacao", "")
                .param("perfis", "1")
                .param("perfis", "2")
                .param("ativo", "false"))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarUsuarioAcesso__deve_criar_usuario_com_sucesso_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/api/auth/usuario/acesso/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("cpf", "28057098880")
                .param("nome", "Mark Lilla")
                .param("login", "marklilla")
                .param("idUnidadeLotacao", "1")
                .param("email", "mark.lilla@cgu.gov.br")
                .param("telefone", "(61) 9999-9999")
                .param("Perfil", "Gerenciar Trilhas")
                .param("Perfil", "Gerenciar Subclasses"))
                .andExpect(status().isOk())
                .andExpect(content().string(Constantes.OPERACAO_REALIZADA_COM_SUCESSO));
    }


    @Test
    public void salvarUsuarioAcesso__deve_lancar_excecao_e_retornar_mensagem_de_erro() throws Exception {
        doThrow(EntidadeInvalidaException.class).when(gerenciador).criarUsuarioAcesso(any());

        mockMvc.perform(post("/api/auth/usuario/acesso/salvar")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("cpf", "28057098880")
                .param("nome", "Mark Lilla")
                .param("login", "marklilla")
                .param("idUnidadeLotacao", "1")
                .param("email", "mark.lilla@cgu.gov.br")
                .param("telefone", "(61) 9999-9999")
                .param("Perfil", "Gerenciar Trilhas")
                .param("Perfil", "Gerenciar Subclasses"))
                .andExpect(status().isBadRequest());
    }
}