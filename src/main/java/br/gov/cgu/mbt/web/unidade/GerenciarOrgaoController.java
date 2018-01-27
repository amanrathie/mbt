package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeOrgao;
import br.gov.cgu.mbt.aplicacao.unidade.GerenciadorDeOrgao;
import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoForm;
import br.gov.cgu.mbt.negocio.unidade.TipoOrgao;
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
public class GerenciarOrgaoController {

    private final GerenciadorDeOrgao gerenciadorDeOrgao;
    private final BuscadorDeOrgao buscadorDeOrgao;

    @Autowired
    public GerenciarOrgaoController(GerenciadorDeOrgao gerenciadorDeOrgao, BuscadorDeOrgao buscadorDeOrgao) {
        this.gerenciadorDeOrgao = gerenciadorDeOrgao;
        this.buscadorDeOrgao = buscadorDeOrgao;
    }

    @RequestMapping(value = {"/auth/orgao/alternar-ativacao/{id}", "/auth/orgao/{id}/ativar", "/auth/orgao/{id}/inativar"}, method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    public ResponseEntity<String> alterarAtivacao(@PathVariable Integer id) {
        gerenciadorDeOrgao.alternarAtivacao(id);
        return new ResponseEntity<>(Constantes.OPERACAO_REALIZADA_COM_SUCESSO, HttpStatus.OK);
    }

    @RequestMapping(value="/auth/orgao/novo", method= RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    public String exibirTelaCadastro(Model m) {
        return viewCadastrar(m, new Orgao());
    }

    @RequestMapping(value="/auth/orgao/{id}", method= RequestMethod.GET)
    public String exibirTelaEdicao(Model m, @PathVariable Integer id) {
        return viewCadastrar(m, buscadorDeOrgao.getParaEdicao(id));
    }

    @ApiOperation(value = "Salvar Órgão", notes = "Salvar um órgão novo ou editar um existente.", tags = Constantes.SWAGGER_TAG_UNIDADES)
    @RequestMapping(value="/api/auth/orgao/salvar", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority(T(br.gov.cgu.projetoexemplosb.negocio.auth.Permissao).GERENCIAR_UNIDADES)")
    @ResponseBody
    public ResponseEntity<String> salvarOrgao(@RequestBody OrgaoForm orgaoForm) {
        try {
            Orgao orgaoSalvo = gerenciadorDeOrgao.salvar(orgaoForm);
            return new ResponseEntity<>(orgaoSalvo.getId().toString(), HttpStatus.OK);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<>(StringUtils.join(e.getMensagens(), "<br/>"), HttpStatus.BAD_REQUEST);
        }
    }

    private String viewCadastrar(Model m, Orgao dto) {
        m.addAttribute("dto", dto);
        m.addAttribute("tipos", TipoOrgao.values());
        return "orgao/orgao";
    }

}