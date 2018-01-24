package br.gov.cgu.projetoexemplosb.web.unidade;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.projetoexemplosb.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.projetoexemplosb.aplicacao.unidade.UnidadeDTO;
import br.gov.cgu.projetoexemplosb.aplicacao.unidade.UnidadeFiltro;
import br.gov.cgu.projetoexemplosb.negocio.nucleo.UF;
import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade;
import br.gov.cgu.projetoexemplosb.web.datatables.DataTablesResponseFactory;
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
public class BuscarUnidadeController {

    private final BuscadorDeUnidade buscador;

    @Autowired
    public BuscarUnidadeController(BuscadorDeUnidade buscador) {this.buscador = buscador;}

    @RequestMapping(value = {"/popup/unidade", "/popup/br.gov.cgu.negocio.unidade.Unidade"}, method = RequestMethod.GET)
    public String popup(UnidadeFiltro filtro, Model m) {
        m.addAttribute("modoPopup", true);
        return listar(filtro, m);
    }

    @RequestMapping(value = "/auth/unidade", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).CONSULTAR_UNIDADES)")
    public String listar(UnidadeFiltro filtro, Model m) {
        m.addAttribute("filtro", filtro);
        m.addAttribute("tipos", TipoUnidade.values());
        m.addAttribute("ufs", UF.values());
        return "unidade/unidades";
    }

    @ApiOperation(value = "Consulta de Unidades", tags = Constantes.SWAGGER_TAG_UNIDADES)
    @RequestMapping(value = "/api/auth/unidade", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<UnidadeDTO> grid(UnidadeFiltro filtro) {
        RespostaConsulta<UnidadeDTO> resposta = buscador.buscar(filtro);
        return DataTablesResponseFactory.build(resposta);
    }
}