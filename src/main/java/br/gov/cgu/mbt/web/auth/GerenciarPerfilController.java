package br.gov.cgu.mbt.web.auth;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.auth.BuscadorDePerfil;
import br.gov.cgu.mbt.aplicacao.auth.GerenciadorDePerfil;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.Permissao;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class GerenciarPerfilController {

    private final GerenciadorDePerfil gerenciadorDePerfil;
    private final BuscadorDePerfil buscadorDePerfil;

    public GerenciarPerfilController(GerenciadorDePerfil gerenciadorDePerfil, BuscadorDePerfil buscadorDePerfil) {
        this.gerenciadorDePerfil = gerenciadorDePerfil;
        this.buscadorDePerfil = buscadorDePerfil;
    }

    @RequestMapping(value="/auth/perfil/novo", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    public String exibirTelaCadastro(Model m) {
        return viewCadastrar(m, new Perfil());
    }
    
    @RequestMapping(value="/auth/perfil/{id}", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    public String exibirTelaEdicao(Model m, @PathVariable Integer id) {
        return viewCadastrar(m, buscadorDePerfil.getParaEdicao(id));
    }

    @ApiOperation(value = "Salvar Usuário", notes = "Salvar um perfil novo ou editar um existente.", tags = Constantes.SWAGGER_TAG_USUARIOS)
    @RequestMapping(value="/api/auth/perfil/salvar", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_USUARIOS)")
    @ResponseBody
    public ResponseEntity<String> salvar(@RequestBody Perfil perfil) {
        try {
            Perfil perfilBD = gerenciadorDePerfil.salvar(perfil);
            return new ResponseEntity<>(perfilBD.getId().toString(), HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), Constantes.QUEBRA_DE_LINHA_DE_MENSAGEM), HttpStatus.BAD_REQUEST);
        }
    }

    private String viewCadastrar(Model m, Perfil perfil) {
        m.addAttribute("perfil", perfil);
        m.addAttribute("permissoes", Permissao.values());
        
        return "usuario/perfil";
    }

}