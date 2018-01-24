package br.gov.cgu.projetoexemplosb.web.auth;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.projetoexemplosb.aplicacao.auth.*;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class GerenciarUsuarioController {
    public static final String OPERACAO_COM_SUCESSO = "Operação realizada com sucesso.";

    private final GerenciadorDeUsuario gerenciadorDeUsuario;
    private final BuscadorDeUsuario buscadorDeUsuario;
    private final BuscadorDePerfil buscadorDePerfil;

    @Autowired
    public GerenciarUsuarioController(GerenciadorDeUsuario gerenciadorDeUsuario, BuscadorDeUsuario buscadorDeUsuario,
    								  BuscadorDePerfil buscadorDePerfil) {
        this.gerenciadorDeUsuario = gerenciadorDeUsuario;
        this.buscadorDeUsuario = buscadorDeUsuario;
        this.buscadorDePerfil = buscadorDePerfil;
    }

    @RequestMapping(value="/auth/usuario/novo", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    public String exibirTelaCadastro(Model m) {
        return viewCadastrar(m, new Usuario());
    }
    
    @RequestMapping(value="/auth/usuario/{id}", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    public String exibirTelaEdicao(Model m, @PathVariable Integer id) {
        return viewCadastrar(m, buscadorDeUsuario.getParaEdicao(id));
    }

    @ApiOperation(value = "Salvar Usuário", notes = "Salvar um usuário novo ou editar um existente.", tags = Constantes.SWAGGER_TAG_USUARIOS)
    @RequestMapping(value="/api/auth/usuario/salvar", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    @ResponseBody
    public ResponseEntity<String> salvar(@RequestBody UsuarioForm usuario) {
        try {
            Usuario usuarioBD = gerenciadorDeUsuario.salvar(usuario);
            return new ResponseEntity<>(usuarioBD.getId().toString(), HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), Constantes.QUEBRA_DE_LINHA_DE_MENSAGEM), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/auth/usuarioLogado/salvar", method= RequestMethod.POST)
    @ResponseBody
    public String salvarUsuarioLogado(@P("form") UsuarioLogadoForm usuarioLogadoForm) {
        gerenciadorDeUsuario.alterarDadosUsuarioLogado(usuarioLogadoForm);
        return OPERACAO_COM_SUCESSO;
    }

    @ResponseBody 
    @RequestMapping(value="/auth/usuarioLogado/gerarChaveApi", method= RequestMethod.GET)
    public String gerarChaveApi(@ModelAttribute("usuarioLogado") Usuario usuarioLogado) {
    	return usuarioLogado.sugerirChaveApi();
    }

    @RequestMapping(value = {"/auth/usuario/alternar-ativacao/{id}", "/auth/usuario/ativar/{id}", "/auth/usuario/inativar/{id}"}, method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    public ResponseEntity<String> alterarAtivacao(@PathVariable Integer id) {
        gerenciadorDeUsuario.alternarAtivacao(id);
        return new ResponseEntity<>(Constantes.OPERACAO_REALIZADA_COM_SUCESSO, HttpStatus.OK);
    }

    private String viewCadastrar(Model m, Usuario usuario) {
        m.addAttribute("usuario", usuario);
        m.addAttribute("perfis", buscadorDePerfil.recuperarTodos());
        
        return "usuario/usuario";
    }

    @ApiOperation(value = "Salvar usuário repassado pelo Cadu", hidden = true)
    @RequestMapping(value="/api/auth/usuariocadu/salvar", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    @ResponseBody
    public ResponseEntity<String> salvarUsuarioCadu(UsuarioCaduForm usuarioCaduForm) {
        try {
            String idUsuario = null;
            Usuario usuarioBD = buscadorDeUsuario.buscarPorLogin(usuarioCaduForm.getLogin());

            if (usuarioBD == null && usuarioCaduForm.isAtivo()) {

                UsuarioForm usuarioForm = new UsuarioForm(usuarioCaduForm);
                usuarioForm.setId(null);
                usuarioBD = gerenciadorDeUsuario.salvar(usuarioForm);
                idUsuario = usuarioBD.getId().toString();

            } else if (usuarioBD != null) {

                gerenciadorDeUsuario.alternarAtivacao(usuarioBD.getId());
                idUsuario = usuarioBD.getId().toString();

            }

            return new ResponseEntity<>(idUsuario, HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Salvar usuário repassado pelo Sistema Acesso", hidden = true)
    @RequestMapping(value="/api/auth/usuario/acesso/salvar", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    @ResponseBody
    public ResponseEntity<String> salvarUsuarioAcesso(UsuarioAcessoForm usuarioAcessoForm) {
        try {
            gerenciadorDeUsuario.criarUsuarioAcesso(usuarioAcessoForm);
            return new ResponseEntity<>(Constantes.OPERACAO_REALIZADA_COM_SUCESSO, HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }
}