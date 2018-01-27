package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeOrgao;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoDTO;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoFiltro;
import br.gov.cgu.mbt.negocio.unidade.TipoOrgao;
import br.gov.cgu.mbt.web.datatables.DataTablesResponseFactory;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BuscarOrgaoController {

    private final BuscadorDeOrgao buscador;

    @Autowired
    public BuscarOrgaoController(BuscadorDeOrgao buscador) {this.buscador = buscador;}

    @RequestMapping(value = "/popup/orgao", method = RequestMethod.GET)
    public String popup(OrgaoFiltro filtro, Model m) {
        m.addAttribute("modoPopup", true);
        return listar(filtro, m);
    }

    @RequestMapping(value = "/auth/orgao", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_UNIDADES)")
    public String listar(OrgaoFiltro filtro, Model m) {
        m.addAttribute("filtro", filtro);
        m.addAttribute("tipos", TipoOrgao.values());
        return "orgao/orgaos";
    }

    @ApiOperation(value = "Consulta de Órgãos", tags = Constantes.SWAGGER_TAG_UNIDADES)
    @RequestMapping(value = "/api/auth/orgao", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<OrgaoDTO> grid(OrgaoFiltro filtro) {
        RespostaConsulta<OrgaoDTO> resposta = buscador.buscar(filtro);
        return DataTablesResponseFactory.build(resposta);
    }

}