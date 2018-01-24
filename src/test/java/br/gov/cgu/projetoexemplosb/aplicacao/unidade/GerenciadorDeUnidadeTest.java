package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.negocio.nucleo.Municipio;
import br.gov.cgu.projetoexemplosb.negocio.nucleo.MunicipioRepository;
import br.gov.cgu.projetoexemplosb.negocio.nucleo.UF;
import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.projetoexemplosb.negocio.unidade.UnidadeForm;
import br.gov.cgu.projetoexemplosb.negocio.unidade.UnidadeRepository;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GerenciadorDeUnidadeTest {

    private UnidadeRepository repository = mock(UnidadeRepository.class);
    private MunicipioRepository municipioRepository = mock(MunicipioRepository.class);
    private BuscadorDeUsuario buscadorDeUsuario = mock(BuscadorDeUsuario.class);

    private GerenciadorDeUnidade gerenciadorDeUnidade = new GerenciadorDeUnidade(repository, municipioRepository, buscadorDeUsuario);

    @Test
    public void alternarAtivacao__deve_ativar_se_inativo() {
        Unidade u = new Unidade();
        u.setAtivo(false);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(true));
    }

    @Test
    public void alternarAtivacao__deve_ativar_se_inativo_e_ativar_filhas() {
        Unidade u = new Unidade();
        u.setAtivo(false);
        Unidade filha = new Unidade();
        filha.setAtivo(false);
        u.getUnidadesFilhas().add(filha);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(true));
        assertThat(filha.isAtivo(), is(true));
    }

    @Test(expected = UnidadeNaoPodeSerAtivadaException.class)
    public void alternarAtivacao__deve_lancar_erro_ao_tentar_ativar_unidade_com_pai_inativo() {
        Unidade u = new Unidade();
        u.setAtivo(false);
        Unidade superior = new Unidade();
        superior.setAtivo(false);
        u.setUnidadeSuperior(superior);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(false));
    }

    @Test
    public void alternarAtivacao__deve_ativar_unidade_com_pai_ativo() {
        Unidade u = new Unidade();
        u.setAtivo(false);
        Unidade superior = new Unidade();
        superior.setAtivo(true);
        u.setUnidadeSuperior(superior);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(true));
    }

    @Test
    public void alternarAtivacao__deve_inativar_se_ativo() {
        Unidade u = new Unidade();
        u.setAtivo(true);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(false));
    }

    @Test(expected = UnidadeNaoPodeSerAtivadaException.class)
    public void alternarAtivacao__deve_lancar_excecao_se_unidade_pai_inativa_e_unidade_ativa() {
        Unidade u = new Unidade();
        u.setAtivo(false);
        Unidade superior = new Unidade();
        superior.setAtivo(false);
        u.setUnidadeSuperior(superior);
        when(repository.getEagerLoaded(123)).thenReturn(u);

        gerenciadorDeUnidade.alternarAtivacao(123);

        assertThat(u.isAtivo(), is(false));
    }

    @Test
    public void salvar__deve_retornar_unidade_do_repositorio_ao_se_salvar_nova_unidade_com_sucesso() {
        // Given
        Unidade unidade = mockUnidade();
        when(repository.put(any())).thenAnswer(invocation -> invocation.getArgumentAt(0, Unidade.class));

        Unidade unidadeSuperior = new Unidade();
        unidadeSuperior.setId(5);
        when(repository.get(5)).thenReturn(unidadeSuperior);

        Municipio municipio = mockMunicipio();
        when(municipioRepository.get(municipio.getId())).thenReturn(municipio);

        when(buscadorDeUsuario.getPorIds(any())).thenAnswer(GerenciadorDeUnidadeTest::gestores);

        // When
        Unidade resultado = gerenciadorDeUnidade.salvar(mockUnidadeFormDTO());

        // Then
        assertThat(resultado.getTipo(), is(unidade.getTipo()));
        assertThat(resultado.getIdOrgao(), is(unidade.getIdOrgao()));
        assertThat(resultado.getNome(), is(unidade.getNome()));
        assertThat(resultado.getSigla(), is(unidade.getSigla()));
        assertThat(resultado.getCodigo(), is(unidade.getCodigo()));
        assertThat(resultado.getEmail(), is(unidade.getEmail()));
        assertThat(resultado.getTelefone(), is(unidade.getTelefone()));
        assertThat(resultado.getMunicipio().getId(), is(unidade.getMunicipio().getId()));
        assertThat(resultado.getUnidadeSuperior(), is(unidade.getUnidadeSuperior()));
        assertThat(resultado.getGestores().size(), is(2));
        resultado.getGestores().forEach(u -> Assert.assertThat(u.getId(), isIn(mockUnidadeFormDTO().getGestores())));
    }

    @Test
    public void salvar__deve_retornar_unidade_do_repositorio_ao_se_salvar_nova_unidade_sem_unidade_superior_com_sucesso() {
        // Given
        Unidade unidade = mockUnidade();
        unidade.setUnidadeSuperior(null);
        when(repository.put(any())).thenAnswer(invocation -> invocation.getArgumentAt(0, Unidade.class));
        UnidadeForm unidadeDTO = mockUnidadeFormDTO();
        unidadeDTO.setIdUnidadeSuperior(null);

        Municipio municipio = mockMunicipio();
        when(municipioRepository.get(municipio.getId())).thenReturn(municipio);

        when(buscadorDeUsuario.getPorIds(any())).thenAnswer(GerenciadorDeUnidadeTest::gestores);

        // When
        Unidade resultado = gerenciadorDeUnidade.salvar(unidadeDTO);

        // Then
        assertThat(resultado.getTipo(), is(unidade.getTipo()));
        assertThat(resultado.getIdOrgao(), is(unidade.getIdOrgao()));
        assertThat(resultado.getNome(), is(unidade.getNome()));
        assertThat(resultado.getSigla(), is(unidade.getSigla()));
        assertThat(resultado.getCodigo(), is(unidade.getCodigo()));
        assertThat(resultado.getEmail(), is(unidade.getEmail()));
        assertThat(resultado.getTelefone(), is(unidade.getTelefone()));
        assertThat(resultado.getMunicipio().getId(), is(unidade.getMunicipio().getId()));
        assertThat(resultado.getUnidadeSuperior(), is(unidade.getUnidadeSuperior()));
        assertThat(resultado.getGestores().size(), is(2));
        resultado.getGestores().forEach(u -> Assert.assertThat(u.getId(), isIn(mockUnidadeFormDTO().getGestores())));
    }

    @Test
    public void salvar__deve_retornar_unidade_do_repositorio_ao_se_salvar_unidade_ja_existente_com_sucesso() {
        // Given
        Unidade unidade = mockUnidade();
        when(repository.put(any())).thenAnswer(invocation -> invocation.getArgumentAt(0, Unidade.class));

        Unidade unidadeSuperior = new Unidade();
        unidadeSuperior.setId(5);
        when(repository.get(5)).thenReturn(unidadeSuperior);

        unidade.setId(5);
        when(repository.getEagerLoaded(3)).thenReturn(unidade);

        UnidadeForm unidadeForm = mockUnidadeFormDTO();
        unidadeForm.setId(3);

        Municipio municipio = mockMunicipio();
        when(municipioRepository.get(municipio.getId())).thenReturn(municipio);

        when(buscadorDeUsuario.getPorIds(any())).thenAnswer(GerenciadorDeUnidadeTest::gestores);

        // When
        Unidade resultado = gerenciadorDeUnidade.salvar(unidadeForm);

        // Then
        assertThat(resultado.getId(), is(unidade.getId()));
        assertThat(resultado.getTipo(), is(unidade.getTipo()));
        assertThat(resultado.getIdOrgao(), is(unidade.getIdOrgao()));
        assertThat(resultado.getNome(), is(unidade.getNome()));
        assertThat(resultado.getSigla(), is(unidade.getSigla()));
        assertThat(resultado.getCodigo(), is(unidade.getCodigo()));
        assertThat(resultado.getEmail(), is(unidade.getEmail()));
        assertThat(resultado.getTelefone(), is(unidade.getTelefone()));
        assertThat(resultado.getMunicipio().getId(), is(unidade.getMunicipio().getId()));
        assertThat(resultado.getUnidadeSuperior(), is(unidade.getUnidadeSuperior()));
        assertThat(resultado.getGestores().size(), is(2));
        resultado.getGestores().forEach(u -> Assert.assertThat(u.getId(), isIn(mockUnidadeFormDTO().getGestores())));
    }

    @Test(expected = EntidadeInvalidaException.class)
    public void salvar__deve_lancar_excecao_ao_tentar_salvar_unidade_sem_municipio() {
        // Given
        UnidadeForm unidadeForm = mockUnidadeFormDTO();
        unidadeForm.setIdMunicipio(null);

        // When
        gerenciadorDeUnidade.salvar(unidadeForm);

        // Then
    }

    private Unidade mockUnidade() {
        Unidade unidade = new Unidade();
        unidade.setId(3);
        unidade.setTipo(TipoUnidade.UAIG);
        unidade.setIdOrgao(3);
        unidade.setNome("Diretoria de TI");
        unidade.setSigla("DTI");
        unidade.setCodigo("200003");
        unidade.setEmail("email@unidade.com.br");
        unidade.setTelefone("(61) 2222-2222");
        unidade.setMunicipio(mockMunicipio());

        Unidade unidadeSuperior = new Unidade();
        unidadeSuperior.setId(5);
        unidade.setUnidadeSuperior(unidadeSuperior);

        return unidade;
    }

    private UnidadeForm mockUnidadeFormDTO() {
        UnidadeForm unidadeForm = new UnidadeForm();
        unidadeForm.setTipo(TipoUnidade.UAIG);
        unidadeForm.setIdOrgao(3);
        unidadeForm.setNome("Diretoria de TI");
        unidadeForm.setSigla("DTI");
        unidadeForm.setCodigo("200003");
        unidadeForm.setEmail("email@unidade.com.br");
        unidadeForm.setTelefone("(61) 2222-2222");
        unidadeForm.setIdMunicipio(1);
        unidadeForm.setIdUnidadeSuperior(5);
        List<Integer> idGestores = asList(1, 2);
        unidadeForm.setGestores(idGestores);
        return unidadeForm;
    }

    private Municipio mockMunicipio() {
        Municipio municipio = new Municipio();
        municipio.setId(1);
        municipio.setDescricao("Município");
        municipio.setUf(UF.DF);
        return municipio;
    }

    private static Object gestores(InvocationOnMock invocationOnMock) {
        Usuario u1 = new Usuario();
        u1.setId(1);

        Usuario u2 = new Usuario();
        u2.setId(2);

        return asList(u1, u2);
    }
}