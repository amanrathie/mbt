package br.gov.cgu.mbt.negocio.unidade;

import org.junit.Test;

import br.gov.cgu.mbt.negocio.unidade.Unidade;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UnidadeTest {

    ArvoreUnidadesBuilder arvoreUnidades = ArvoreUnidadesBuilder.getInstance();

    @Test
    public void isUnidadeDiferenteDaUnidadePai__true_se_id_eh_null() throws Exception {
        Unidade unidade = new Unidade();
        unidade.setId(null);

        boolean isUnidadeDiferenteDaUnidadePai = unidade.isUnidadeDiferenteDaUnidadePai();

        assertTrue(isUnidadeDiferenteDaUnidadePai);
    }

    @Test
    public void isUnidadeDiferenteDaUnidadePai__false_se_ids_iguais() throws Exception {
        Unidade unidade = new Unidade();
        unidade.setId(1);
        unidade.setUnidadeSuperior(new Unidade());
        unidade.getUnidadeSuperior().setId(1);

        boolean isUnidadeDiferenteDaUnidadePai = unidade.isUnidadeDiferenteDaUnidadePai();

        assertFalse(isUnidadeDiferenteDaUnidadePai);
    }

    @Test
    public void isUnidadeDiferenteDaUnidadePai__true_se_ids_diferentes() throws Exception {
        Unidade unidade = new Unidade();
        unidade.setId(1);
        unidade.setUnidadeSuperior(new Unidade());
        unidade.getUnidadeSuperior().setId(2);

        boolean isUnidadeDiferenteDaUnidadePai = unidade.isUnidadeDiferenteDaUnidadePai();

        assertTrue(isUnidadeDiferenteDaUnidadePai);
    }

    @Test
    public void toString__retorna_vazio_se_codigo_sigla_ou_nome_forem_nulos() throws Exception {
        Unidade unidade = new Unidade();

        unidade.setCodigo(null);
        unidade.setNome("Nome");
        unidade.setSigla("Sigla");

        assertThat(unidade.toString(), is(""));

        unidade = new Unidade();

        unidade.setCodigo("Código");
        unidade.setNome(null);
        unidade.setSigla("Sigla");

        assertThat(unidade.toString(), is(""));

        unidade = new Unidade();

        unidade.setCodigo("Código");
        unidade.setNome("Nome");
        unidade.setSigla(null);

        assertThat(unidade.toString(), is(""));
    }

    @Test
    public void toString__retorna_string_esperada() throws Exception {
        // given
        Unidade unidade = new Unidade();

        unidade.setCodigo("Código");
        unidade.setNome("Nome");
        unidade.setSigla("Sigla");

        // when then
        assertThat(unidade.toString(), is("Código - Nome (Sigla)"));
    }

    @Test
    public void possuiUnidadeFilha__true_se_unidade_acima_na_hierarquia_tem_unidades_abaixo() {
        Unidade cgu = arvoreUnidades.getUnidadeId(1);
        Unidade dti = arvoreUnidades.getUnidadeId(2);
        Unidade cosis = arvoreUnidades.getUnidadeId(5);

        assertTrue(cgu.possuiUnidadeFilha(dti));
        assertTrue(dti.possuiUnidadeFilha(cosis));
        assertTrue(cgu.possuiUnidadeFilha(cosis));
    }

    @Test
    public void possuiUnidadeFilha__false_se_unidade_irma_e_sobrinha_esta_abaixo() {
        Unidade cgu = arvoreUnidades.getUnidadeId(1);
        Unidade dti = arvoreUnidades.getUnidadeId(2);
        Unidade cgtec = arvoreUnidades.getUnidadeId(3);
        Unidade cgsis = arvoreUnidades.getUnidadeId(4);
        Unidade cosis = arvoreUnidades.getUnidadeId(5);
        Unidade ministerioX = arvoreUnidades.getUnidadeId(6);
        Unidade sfc = arvoreUnidades.getUnidadeId(7);

        assertFalse(cgu.possuiUnidadeFilha(ministerioX));
        assertFalse(dti.possuiUnidadeFilha(sfc));
        assertFalse(cgtec.possuiUnidadeFilha(cgsis));
        assertFalse(cgtec.possuiUnidadeFilha(cosis));
    }

    @Test
    public void possuiUnidadeFilha__false_se_unidade_superior_esta_abaixo() {
        Unidade cgu = arvoreUnidades.getUnidadeId(1);
        Unidade dti = arvoreUnidades.getUnidadeId(2);
        Unidade cgtec = arvoreUnidades.getUnidadeId(3);
        Unidade cgsis = arvoreUnidades.getUnidadeId(4);
        Unidade cosis = arvoreUnidades.getUnidadeId(5);
        Unidade ministerioX = arvoreUnidades.getUnidadeId(6);
        Unidade sfc = arvoreUnidades.getUnidadeId(7);

        assertFalse(cosis.possuiUnidadeFilha(cgsis));
        assertFalse(cosis.possuiUnidadeFilha(dti));
        assertFalse(cosis.possuiUnidadeFilha(cgtec));
        assertFalse(sfc.possuiUnidadeFilha(cgu));
        assertFalse(cgtec.possuiUnidadeFilha(ministerioX));
    }

    @Test
    public void getUnidadesHierarquiaSuperior__lista_de_unidades_da_hierarquia_superior_da_unidade() throws Exception {
        Unidade cosis = arvoreUnidades.getUnidadeId(5);

        List<Unidade> unidadesHierarquiaSuperior = cosis.getUnidadesHierarquiaSuperior();

        assertThat(unidadesHierarquiaSuperior, hasSize(4));
    }

}