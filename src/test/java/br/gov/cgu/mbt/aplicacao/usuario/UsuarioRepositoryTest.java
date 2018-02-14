package br.gov.cgu.mbt.aplicacao.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class UsuarioRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private static UsuarioRepository repository = new UsuarioRepository();

    @Test
    public void getPorId_retorna_dados() {
        Usuario admin = repository.get(Constantes.ID_ADMIN_CGU_PADRAO);

        assertThat(admin).isNotNull();
    }
}