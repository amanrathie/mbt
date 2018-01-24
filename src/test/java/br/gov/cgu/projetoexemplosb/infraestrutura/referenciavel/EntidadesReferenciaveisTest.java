package br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel;

import br.gov.cgu.componentes.pojo.AutocompleteOption;
import br.gov.cgu.componentes.pojo.MultiselectOption;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.negocio.auth.UsuarioRepository;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.projetoexemplosb.negocio.unidade.UnidadeRepository;
import br.gov.cgu.persistencia.jpa.Entidade;
import org.junit.Test;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntidadesReferenciaveisTest {

    private ApplicationContext applicationContext = mock(ApplicationContext.class);
    private EntidadesReferenciaveis entidadesReferenciaveis = new EntidadesReferenciaveis(applicationContext);

    @Test
    public void getEntidadesReferenciaveis__deve_retornar_todas_entidades_anotadas() throws Exception {
        List<MultiselectOption> entidadesReferenciaveis = this.entidadesReferenciaveis.getEntidadesReferenciaveis();
        assertThat(entidadesReferenciaveis, hasSize(2));
    }

    @Test
    public void get__deve_retornar_entidade_anotada_unidade() throws Exception {
        Unidade unidadeDeTeste = new Unidade();
        unidadeDeTeste.setId(1);
        unidadeDeTeste.setNome("Unidade de Teste");

        UnidadeRepository unidadeRepositoryMock = mock(UnidadeRepository.class);

        when(applicationContext.getBean(UnidadeRepository.class)).thenReturn(unidadeRepositoryMock);
        when(unidadeRepositoryMock.get(1)).thenReturn(unidadeDeTeste);

        Unidade unidadeResposta =  (Unidade) entidadesReferenciaveis.get(Unidade.class.getName(), 1);

        assertThat(unidadeResposta.getId(), is(unidadeDeTeste.getId()));
        assertThat(unidadeResposta.getNome(), is(unidadeDeTeste.getNome()));
    }

    @Test (expected = RepositoryNaoEncontradoException.class)
    public void get__deve_lancar_exception_se_repository_nao_for_encontrado() throws Exception {
        entidadesReferenciaveis.get("EntidadeNaoExistente", 1);
    }

    @Test (expected = MetodoDoRepositoryException.class)
    public void get__deve_lancar_exception_se_metodo_get_do_repository_nao_for_encontrado() throws Exception {
        entidadesReferenciaveis.get(Unidade.class.getName(), asList(1));
    }

    private List<Usuario> mockUsuarios() {
        return asList(mockUsuario());
    }

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin("Teste");
        usuario.setId(123);
        return usuario;
    }

    @Test
    public void getAutocompletePorTermo__deve_chamar_getPorTermo_do_repository_adequado() throws Exception {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        when(applicationContext.getBean(UsuarioRepository.class)).thenReturn(usuarioRepository);
        List<Usuario> resultadoMock = mockUsuarios();
        when(usuarioRepository.getPorTermo("teste")).thenReturn(resultadoMock);

        List<AutocompleteOption> resultado = entidadesReferenciaveis.getAutocompletePorTermo(Usuario.class.getName(), "teste");

        assertThat(resultado.size(), is(resultadoMock.size()));
        assertThat(resultado.get(0).getId(), is(resultadoMock.get(0).getId().toString()));
        assertThat(resultado.get(0).getName(), is(resultadoMock.get(0).toString()));
    }
    @Test
    public void getPorTermo__deve_chamar_getPorTermo_do_repository_adequado() throws Exception {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        when(applicationContext.getBean(UsuarioRepository.class)).thenReturn(usuarioRepository);
        List<Usuario> resultadoMock = mockUsuarios();
        when(usuarioRepository.getPorTermo("teste")).thenReturn(resultadoMock);

        List<Entidade> resultado = entidadesReferenciaveis.getPorTermo(Usuario.class.getName(), "teste");

        assertThat(resultado.size(), is(resultadoMock.size()));
        assertThat(resultado.get(0).getId(), is(resultadoMock.get(0).getId()));
        assertThat(resultado.get(0).toString(), is(resultadoMock.get(0).toString()));
    }

    @Test(expected = RepositoryNaoEncontradoException.class)
    public void getPorTermo__deve_lancar_exception_se_nao_encontrar_a_classe() throws Exception {
        entidadesReferenciaveis.getAutocompletePorTermo("XXXXXClasseInexistenteXXXX", "teste");
    }

    @Test(expected = RepositoryNaoEncontradoException.class)
    public void getPorTermo__deve_lancar_exception_se_classe_nao_for_um_bean_spring() throws Exception {
        when(applicationContext.getBean(UsuarioRepository.class)).thenThrow(new FatalBeanException("erro"));
        entidadesReferenciaveis.getAutocompletePorTermo(Usuario.class.getName(), "teste");
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorTermo__deve_lancar_exception_se_nao_tiver_o_metodo_no_repostorio() throws Exception {
        entidadesReferenciaveis.getAutocompletePorTermo(this.getClass().getPackage().getName() + ".RepositorioSemOMetodo", "teste");
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorTermo__deve_lancar_exception_se_o_metodo_for_inacessivel() throws Exception {
        entidadesReferenciaveis.getAutocompletePorTermo(this.getClass().getPackage().getName() + ".RepositorioComMetodoInacessivel", "teste");
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorTermo__deve_lancar_exception_se_assinatura_do_metodo_for_diferente() throws Exception {
        entidadesReferenciaveis.getAutocompletePorTermo(this.getClass().getPackage().getName() + ".RepositorioComAssinaturaDiferente", "teste");
    }


    @Test
    public void getAutocompletePorIds__deve_chamar_getPorIds_do_repository_adequado() throws Exception {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        when(applicationContext.getBean(UsuarioRepository.class)).thenReturn(usuarioRepository);
        List<Usuario> resultadoMock = mockUsuarios();
        when(usuarioRepository.getPorIds(asList(1,2))).thenReturn(resultadoMock);

        List<AutocompleteOption> resultado = entidadesReferenciaveis.getAutocompletePorIds(Usuario.class.getName(), asList(1,2));

        assertThat(resultado.size(), is(resultadoMock.size()));
        assertThat(resultado.get(0).getId(), is(resultadoMock.get(0).getId().toString()));
        assertThat(resultado.get(0).getName(), is(resultadoMock.get(0).toString()));
    }

    @Test
    public void getPorIds__deve_chamar_getPorIds_do_repository_adequado() throws Exception {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        when(applicationContext.getBean(UsuarioRepository.class)).thenReturn(usuarioRepository);
        List<Usuario> resultadoMock = mockUsuarios();
        when(usuarioRepository.getPorIds(asList(1,2))).thenReturn(resultadoMock);

        List<Entidade> resultado = entidadesReferenciaveis.getPorIds(Usuario.class.getName(), asList(1,2));

        assertThat(resultado.size(), is(resultadoMock.size()));
        assertThat(resultado.get(0).getId(), is(resultadoMock.get(0).getId()));
        assertThat(resultado.get(0).toString(), is(resultadoMock.get(0).toString()));
    }

    @Test(expected = RepositoryNaoEncontradoException.class)
    public void getPorIds__deve_lancar_exception_se_nao_encontrar_a_classe() throws Exception {
        entidadesReferenciaveis.getAutocompletePorIds("XXXXXClasseInexistenteXXXX", asList(1,2));
    }

    @Test(expected = RepositoryNaoEncontradoException.class)
    public void getPorIds__deve_lancar_exception_se_classe_nao_for_um_bean_spring() throws Exception {
        when(applicationContext.getBean(UsuarioRepository.class)).thenThrow(new FatalBeanException("erro"));
        entidadesReferenciaveis.getAutocompletePorIds(Usuario.class.getName(), asList(1,2));
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorIds__deve_lancar_exception_se_nao_tiver_o_metodo_no_repostorio() throws Exception {
        entidadesReferenciaveis.getAutocompletePorIds(this.getClass().getPackage().getName() + ".RepositorioSemOMetodo", asList(1,2));
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorIds__deve_lancar_exception_se_o_metodo_for_inacessivel() throws Exception {
        entidadesReferenciaveis.getAutocompletePorIds(this.getClass().getPackage().getName() + ".RepositorioComMetodoInacessivel", asList(1,2));
    }

    @Test(expected = MetodoDoRepositoryException.class)
    public void getPorIds__deve_lancar_exception_se_assinatura_do_metodo_for_diferente() throws Exception {
        entidadesReferenciaveis.getAutocompletePorIds(this.getClass().getPackage().getName() + ".RepositorioComAssinaturaDiferente", asList(1,2));
    }
}

