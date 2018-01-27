package br.gov.cgu.mbt.aplicacao.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.PerfilRepository;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenciadorDeUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final BuscadorDeUsuario buscadorDeUsuario;
    private final BuscadorDeUnidade buscadorDeUnidade;

    @Autowired
    public GerenciadorDeUsuario(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository,
                                BuscadorDeUsuario buscadorDeUsuario, BuscadorDeUnidade buscadorDeUnidade) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.buscadorDeUsuario = buscadorDeUsuario;
        this.buscadorDeUnidade = buscadorDeUnidade;
    }

    @Transactional
    public void alternarAtivacao(Integer id) {
        Usuario usuario = usuarioRepository.get(id);
        usuario.setAtivo(!usuario.isAtivo());
        usuarioRepository.put(usuario);
    }

    @Transactional
    public void alterarDadosUsuarioLogado(UsuarioLogadoForm usuarioLogadoForm) {
        Usuario usuarioLogado = buscadorDeUsuario.getUsuarioLogado();

        usuarioLogado.setEmail(usuarioLogadoForm.getEmailUsuarioLogado());
        usuarioLogado.setTelefone(usuarioLogadoForm.getTelefoneUsuarioLogado());
        usuarioLogado.setChaveApi(usuarioLogadoForm.getChaveApiUsuarioLogado());
        usuarioLogado.setFrequenciaRecebimentoEmail(usuarioLogadoForm.getFrequenciaRecebimentoEmail());

        usuarioRepository.put(usuarioLogado);
    }

    @Transactional
    public Usuario salvar(UsuarioForm form) {
        Usuario usuario;

        if (form.getId() == null) {
            usuario = new Usuario();
            usuario.setAtivo(true);
            usuario.setFrequenciaRecebimentoEmail((byte) 1);
        } else {
            usuario = buscadorDeUsuario.getParaEdicao(form.getId());
        }

        usuario.setNome(form.getNome());
        usuario.setCpf(form.getCpf());
        usuario.setLogin(form.getLogin());
        usuario.setSiape(form.getSiape());
        usuario.setEmail(form.getEmail());
        usuario.setTelefone(form.getTelefone());

        definirUnidadesUsuario(usuario, form);
        definirPerfis(usuario, form);

        return usuarioRepository.put(usuario);
    }

    @Transactional
    public Usuario criarUsuarioAcesso(UsuarioAcessoForm usuarioAcessoForm) {
        normalizaLoginDoUsuarioAcesso(usuarioAcessoForm);
        normalizaPerfisDoUsuarioAcesso(usuarioAcessoForm);

        Usuario usuario = salvar(new UsuarioForm(usuarioAcessoForm));
        usuario.setAtivo(true);
        usuario.setDataUltimoLogin(LocalDateTime.now());
        usuarioRepository.put(usuario);

        return usuario;
    }

    private void definirUnidadesUsuario(Usuario usuario, UsuarioForm usuarioForm) {
        usuario.getUnidades().clear();
        usuario.getUnidades().addAll(buscadorDeUnidade.getPorIds(usuarioForm.getUnidades()));
    }

    private void definirPerfis(Usuario usuario, UsuarioForm usuarioForm) {
        usuario.getPerfis().clear();
        usuario.getPerfis().addAll(perfilRepository.getPorIds(usuarioForm.getPerfis()));
    }

    private void normalizaLoginDoUsuarioAcesso(UsuarioAcessoForm usuarioAcessoForm) {
        usuarioAcessoForm.setLogin("CGU\\" + usuarioAcessoForm.getLogin());
    }

    private void normalizaPerfisDoUsuarioAcesso(UsuarioAcessoForm usuarioAcessoForm) {
        removeAspasColchetesDosPerfis(usuarioAcessoForm);

        List<Perfil> perfis = perfilRepository.getPorNomes(usuarioAcessoForm.getPerfil());
        List<Integer> idsDosPerfis = perfis.stream().map(Perfil::getId).collect(Collectors.toList());
        usuarioAcessoForm.setPerfis(idsDosPerfis);
    }

    private void removeAspasColchetesDosPerfis(UsuarioAcessoForm usuarioAcessoForm) {
        usuarioAcessoForm.setPerfil(
                usuarioAcessoForm.getPerfil()
                                 .stream()
                                 .map(perfil -> perfil.replaceAll("[\"\\[\\]]", ""))
                                 .collect(Collectors.toList())
        );
    }
}
