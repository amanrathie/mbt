package br.gov.cgu.mbt.aplicacao.unidade;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.unidade.GerenciadorDeOrgao;
import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoForm;
import br.gov.cgu.mbt.negocio.unidade.OrgaoRepository;
import br.gov.cgu.mbt.negocio.unidade.TipoOrgao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GerenciadorDeOrgaoTest {

    private OrgaoRepository repository = mock(OrgaoRepository.class);
    private GerenciadorDeOrgao gerenciadorDeOrgao = new GerenciadorDeOrgao(repository);

    @Test
    public void alternarAtivacao__deve_ativar_se_inativo() {
        Orgao u = new Orgao();
        u.setAtivo(false);
        when(repository.get(123)).thenReturn(u);

        gerenciadorDeOrgao.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(true));
    }

    @Test
    public void alternarAtivacao__deve_inativar_se_ativo() {
        Orgao u = new Orgao();
        u.setAtivo(true);
        when(repository.get(123)).thenReturn(u);

        gerenciadorDeOrgao.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(false));
    }

    @Test
    public void salvar__deve_retornar_orgao_do_repositorio_ao_se_salvar_novo_orgao_com_sucesso () throws Exception{
        Orgao orgao = mockOrgao();
        when(repository.put(any())).thenAnswer(invocation -> invocation.getArgumentAt(0, Orgao.class));

        Orgao resultado = gerenciadorDeOrgao.salvar(mockOrgaoFormDTO());

        assertThat(resultado.getNome(), is(orgao.getNome()));
        assertThat(resultado.getSigla(), is(orgao.getSigla()));
        assertThat(resultado.getTipo(), is(orgao.getTipo()));
        assertThat(resultado.getCodigo(), is(orgao.getCodigo()));
    }

    @Test
    public void salvar__deve_retornar_orgao_do_repositorio_ao_se_salvar_orgao_ja_existente_com_sucesso () throws Exception{
        Orgao orgao = mockOrgao();
        when(repository.put(any())).thenAnswer(invocation -> invocation.getArgumentAt(0, Orgao.class));

        orgao.setId(5);
        when(repository.getEagerLoaded(3)).thenReturn(orgao);

        OrgaoForm orgaoForm = mockOrgaoFormDTO();
        orgaoForm.setId(3);

        Orgao resultado = gerenciadorDeOrgao.salvar(orgaoForm);

        assertThat(resultado.getId(), is(orgao.getId()));
        assertThat(resultado.getNome(), is(orgao.getNome()));
        assertThat(resultado.getSigla(), is(orgao.getSigla()));
        assertThat(resultado.getTipo(), is(orgao.getTipo()));
        assertThat(resultado.getCodigo(), is(orgao.getCodigo()));
    }

    private Orgao mockOrgao(){
        Orgao orgao = new Orgao();

        orgao.setId(3);
        orgao.setNome("Teste");
        orgao.setSigla("tst");
        orgao.setTipo(TipoOrgao.ADMINISTRACAO_DIRETA);
        orgao.setCodigo("1234");

        return orgao;
    }

    private OrgaoForm mockOrgaoFormDTO () {
        OrgaoForm orgaoForm = new OrgaoForm();

        orgaoForm.setNome("Teste");
        orgaoForm.setSigla("tst");
        orgaoForm.setTipo(TipoOrgao.ADMINISTRACAO_DIRETA);
        orgaoForm.setCodigo("1234");

        return orgaoForm;
    }

}