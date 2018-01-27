package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.mbt.aplicacao.unidade.GerenciadorDeUnidade;
import br.gov.cgu.mbt.aplicacao.unidade.UnidadeNaoPodeSerAtivadaException;
import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.mbt.negocio.unidade.UnidadeForm;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class GerenciarUnidadeController {


    private final GerenciadorDeUnidade gerenciadorDeUnidade;
    private final BuscadorDeUnidade buscadorDeUnidade;

    @Autowired
    public GerenciarUnidadeController(GerenciadorDeUnidade gerenciadorDeUnidade, BuscadorDeUnidade buscadorDeUnidade) {
        this.gerenciadorDeUnidade = gerenciadorDeUnidade;
        this.buscadorDeUnidade = buscadorDeUnidade;
    }

    @RequestMapping(value = {"/auth/unidade/alternar-ativacao/{id}", "/auth/unidade/{id}/ativar", "/auth/unidade/{id}/inativar"}, method = RequestMethod.POST)
    @PreAuthorize("@buscadorDeUnidade.isUsuarioLogadoGestorDaUnidade(#id) || hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    public ResponseEntity<String> alterarAtivacao(@PathVariable Integer id) {
        try {
            gerenciadorDeUnidade.alternarAtivacao(id);
            return new ResponseEntity<>(Constantes.OPERACAO_REALIZADA_COM_SUCESSO, HttpStatus.OK);
        } catch (UnidadeNaoPodeSerAtivadaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/auth/unidade/novo", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    public String exibirTelaCadastro(Model m) {
        return viewCadastrar(m, new Unidade());
    }

    @RequestMapping(value="/auth/unidade/{id}", method= RequestMethod.GET)
    public String exibirTelaEdicao(Model m, @PathVariable Integer id) {
        return viewCadastrar(m, buscadorDeUnidade.getParaEdicao(id));
    }

    @ApiOperation(value = "Salvar Unidade", notes = "Salvar uma unidade nova ou editar um existente.", tags = Constantes.SWAGGER_TAG_UNIDADES)
    @RequestMapping(value="/api/auth/unidade/salvar", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@buscadorDeUnidade.isUsuarioLogadoGestorDaUnidade(#id) || hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    @ResponseBody
    public ResponseEntity<String> salvarUnidade(@RequestBody UnidadeForm unidadeForm) {
        try {
            Unidade unidadeSalva = gerenciadorDeUnidade.salvar(unidadeForm);
            return new ResponseEntity<>(unidadeSalva.getId().toString(), HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }

    private String viewCadastrar(Model m, Unidade dto) {
        m.addAttribute("dto", dto);
        m.addAttribute("tipos", TipoUnidade.values());
        return "unidade/unidade";
    }

}