package br.gov.cgu.projetoexemplosb.web.auth;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.projetoexemplosb.aplicacao.auth.UsuarioDTO;
import br.gov.cgu.projetoexemplosb.aplicacao.auth.UsuarioFiltro;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.web.datatables.DataTablesResponseFactory;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class BuscarUsuarioController {

    private final BuscadorDeUsuario buscador;

    @Autowired
    public BuscarUsuarioController(BuscadorDeUsuario buscador) {this.buscador = buscador;}

    @RequestMapping(value = "/auth", method= RequestMethod.GET)
    @Cacheable
    public ModelAndView paginaInicial() {
        return new ModelAndView("auth/usuario");
    }

    @RequestMapping(value = "/auth/usuario", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    public String listar(UsuarioFiltro filtro, Model m) {
        m.addAttribute("filtro", filtro);

        return "usuario/usuarios";
    }

    @ApiOperation(value = "Consulta de Usuários", tags = Constantes.SWAGGER_TAG_USUARIOS)
    @RequestMapping(value = "/api/auth/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    @ResponseBody
    public DataTablesResponse<UsuarioDTO> grid(UsuarioFiltro filtro) {
        RespostaConsulta<UsuarioDTO> resposta = buscador.buscar(filtro);
        return DataTablesResponseFactory.build(resposta);
    }

    @ApiOperation(value = "Verificar situação do Usuário", hidden=true)
    @RequestMapping(value="/api/auth/usuario/{login}/ativo", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    @ResponseBody
    public ResponseEntity<String> verificarAtivo(@PathVariable String login) {
        try {
            Usuario usuarioBD = buscador.buscarPorLogin(login.replace("%5C", "\\"));
            return new ResponseEntity<>(String.valueOf(usuarioBD != null && usuarioBD.isAtivo()), HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }

}