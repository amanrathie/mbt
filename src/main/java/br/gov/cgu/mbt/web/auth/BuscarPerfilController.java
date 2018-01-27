package br.gov.cgu.mbt.web.auth;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.auth.BuscadorDePerfil;
import br.gov.cgu.mbt.aplicacao.auth.PerfilDTO;
import br.gov.cgu.mbt.aplicacao.auth.PerfilFiltro;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.web.datatables.DataTablesResponseFactory;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping
public class BuscarPerfilController {
    private final BuscadorDePerfil buscador;

    @Autowired
    public BuscarPerfilController(BuscadorDePerfil buscador) {this.buscador = buscador;}

    @RequestMapping(value = "/auth/perfil", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    public String listar(PerfilFiltro filtro, Model m) {
        m.addAttribute("filtro", filtro);
        return "usuario/perfis";
    }

    @ApiOperation(value = "Consulta de Perfis", tags = Constantes.SWAGGER_TAG_USUARIOS)
    @RequestMapping(value = "/api/auth/perfil", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_USUARIOS)")
    @ResponseBody
    public DataTablesResponse<PerfilDTO> grid(PerfilFiltro filtro) {
        RespostaConsulta<PerfilDTO> resposta = buscador.buscar(filtro);
        return DataTablesResponseFactory.build(resposta);
    }

    @ApiOperation(value = "Perfis disponíveis via Cadu", hidden = true)
    @RequestMapping(value = "/api/auth/perfil/publico-externo", method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity listarPerfisPublicoExterno() {
        try {
            List<Perfil> perfis = buscador.recuperarTodosPublicoExterno();
            HashMap<String, String> lista = new HashMap<>();
            perfis.forEach(p -> lista.put(p.getId().toString(), p.getNome()));
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Perfis disponíveis via Sistema Acesso", hidden = true)
    @RequestMapping(value = "/api/auth/perfis/publico-interno", method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity listarPerfisPublicoInterno() {
        try {
            List<Perfil> perfis = buscador.recuperarTodos();
            HashMap<String, String> lista = new HashMap<>();
            perfis.forEach(p -> lista.put(p.getId().toString(), p.getNome()));
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }
}