package br.gov.cgu.mbt.negocio.auth;

import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.Permissao;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.unidade.ArvoreUnidadesBuilder;
import br.gov.cgu.mbt.negocio.unidade.Unidade;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UsuarioTest {

    ArvoreUnidadesBuilder arvoreUnidades = ArvoreUnidadesBuilder.getInstance();

    @Test
    public void getAutorithies__deve_retornar_lista_de_permissoes() throws Exception {
        Usuario usuario = new Usuario();
        Perfil t = new Perfil();
        t.getPermissoes().add(Permissao.CONSULTAR_RISCOS);
        usuario.setPerfis(Collections.singletonList(t));

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        assertThat(authorities.toArray()[0], is(Permissao.CONSULTAR_RISCOS));
    }

    @Test
    public void getPassword__deve_ser_null_pois_nao_usamos() throws Exception {
        Usuario usuario = new Usuario();

        assertNull(usuario.getPassword());
    }

    @Test
    public void getUsername__eh_getLogin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setLogin("XXXXX");

        assertThat(usuario.getUsername(), is(usuario.getLogin()));
    }

    @Test
    public void metodos_da_interface__devem_ser_true() throws Exception {
        Usuario usuario = new Usuario();

        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled__inverso_da_flag_ativo() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setAtivo(false);
        assertFalse(usuario.isEnabled());

        usuario.setAtivo(true);
        assertTrue(usuario.isEnabled());
    }

    @Test
    public void toString_deve_retornar_login() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setLogin("XXXX");

        assertThat(usuario.getLogin(), is("XXXX"));
    }

    @Test
    public void hasPerfil__false_se_perfis_for_vazio() throws Exception {
        Usuario usuario = new Usuario();

        assertFalse(usuario.hasPerfil(new Perfil()));
    }

    @Test
    public void hasPerfil__false_se_nao_tem_perfil_na_lista() throws Exception {
        Usuario usuario = new Usuario();
        Perfil admin = new Perfil();
        admin.setId(1);

        Perfil outroPerfil = new Perfil();
        outroPerfil.setId(2);

        usuario.setPerfis(Collections.singletonList(admin));

        assertFalse(usuario.hasPerfil(outroPerfil));
    }

    @Test
    public void hasPerfil__true_se_tem_perfil_na_lista() throws Exception {
        Usuario usuario = new Usuario();
        Perfil admin = new Perfil();
        admin.setId(1);

        usuario.setPerfis(Collections.singletonList(admin));

        assertTrue(usuario.hasPerfil(admin));
    }

    @Test
    public void sugerirChaveApi__deve_gerar_md5_diferente_a_cada_execucao_com_tamanho_32() throws Exception {
        // given
        Usuario usuario = new Usuario();
        usuario.setLogin("Login");
        
        String md5_a = usuario.sugerirChaveApi();
        Thread.sleep(1);
        String md5_b = usuario.sugerirChaveApi();
        
        assertThat(md5_a, not(equalTo(md5_b)));
        assertThat(md5_a.length(), is(32));
        assertThat(md5_b.length(), is(32));
    }

    @Test
    public void estaLotadoNaUnidadeOuEhUnidadeAscendente__false_se_unidade_nao_foi_informada() throws Exception {
        Usuario usuario = criarUsuarioNaUnidade(arvoreUnidades.getUnidadeId(1));
        Unidade unidade = null;

        assertFalse(usuario.estaLotadoNaUnidadeOuLotadoEmUnidadeSuperior(unidade));
    }

    @Test
    public void estaLotadoNaUnidadeOuEhUnidadeAscendente__true_se_usuario_esta_lotado_na_unidade() throws Exception {
        Usuario usuario = criarUsuarioNaUnidade(arvoreUnidades.getUnidadeId(1));
        Unidade unidade = arvoreUnidades.getUnidadeId(1);

        assertTrue(usuario.estaLotadoNaUnidadeOuLotadoEmUnidadeSuperior(unidade));
    }

    @Test
    public void estaLotadoNaUnidadeOuEhUnidadeAscendente__true_se_usuario_nao_esta_lotado_na_unidade_mas_eh_lotado_em_unidade_superior() throws Exception {
        Usuario usuario = criarUsuarioNaUnidade(arvoreUnidades.getUnidadeId(2));
        Unidade unidade = arvoreUnidades.getUnidadeId(5);

        assertTrue(usuario.estaLotadoNaUnidadeOuLotadoEmUnidadeSuperior(unidade));
    }

    @Test
    public void estaLotadoNaUnidadeOuEhUnidadeAscendente__false_se_usuario_nao_esta_lotado_na_unidade_nem_eh_lotado_em_unidade_superior() throws Exception {
        Usuario usuario = criarUsuarioNaUnidade(arvoreUnidades.getUnidadeId(3));
        Unidade unidade = arvoreUnidades.getUnidadeId(5);

        assertFalse(usuario.estaLotadoNaUnidadeOuLotadoEmUnidadeSuperior(unidade));
    }

    private Usuario criarUsuarioNaUnidade(Unidade unidade) {
        Usuario usuario = new Usuario();

        List<Unidade> unidades = new ArrayList<>();
        unidades.add(unidade);
        usuario.setUnidades(unidades);

        return usuario;
    }

}