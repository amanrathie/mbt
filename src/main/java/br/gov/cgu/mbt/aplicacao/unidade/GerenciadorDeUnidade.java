package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.negocio.nucleo.Municipio;
import br.gov.cgu.mbt.negocio.nucleo.MunicipioRepository;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.mbt.negocio.unidade.UnidadeForm;
import br.gov.cgu.mbt.negocio.unidade.UnidadeRepository;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GerenciadorDeUnidade {

    private UnidadeRepository repository;
    private MunicipioRepository municipioRepository;
    private final BuscadorDeUsuario buscadorDeUsuario;

    @Autowired
    public GerenciadorDeUnidade(UnidadeRepository repository, MunicipioRepository municipioRepository, BuscadorDeUsuario buscadorDeUsuario) {
        this.repository = repository;
        this.municipioRepository = municipioRepository;
        this.buscadorDeUsuario = buscadorDeUsuario;
    }

    @Transactional
    public void alternarAtivacao(Integer id) {
        Unidade unidade = repository.getEagerLoaded(id);
        unidade.setAtivo(!unidade.isAtivo());
        if (unidade.isAtivo() && unidade.getUnidadeSuperior() != null && !unidade.getUnidadeSuperior().isAtivo()) {
            throw new UnidadeNaoPodeSerAtivadaException(unidade);
        }
        repository.put(unidade);
        unidade.getUnidadesFilhas().forEach(filha -> alterarAtivacao(filha, unidade.isAtivo()));
    }

    private void alterarAtivacao(Unidade unidade, boolean ativo) {
        unidade.setAtivo(ativo);
        repository.put(unidade);
        unidade.getUnidadesFilhas().forEach(filha -> alterarAtivacao(filha, ativo));
    }

    @Transactional
    public Unidade salvar(UnidadeForm unidadeForm) {
        Unidade unidade;

        if (unidadeForm.getId() == null) {
            unidade = new Unidade();
            unidade.setAtivo(true);
        } else {
            unidade = repository.getEagerLoaded(unidadeForm.getId());
        }

        unidade.setTipo(unidadeForm.getTipo());
        unidade.setIdOrgao(unidadeForm.getIdOrgao());
        unidade.setNome(unidadeForm.getNome());
        unidade.setSigla(unidadeForm.getSigla());
        unidade.setCodigo(unidadeForm.getCodigo());
        unidade.setEmail(unidadeForm.getEmail());
        unidade.setTelefone(unidadeForm.getTelefone());

        if (unidadeForm.getIdMunicipio() == null) {
            throw new EntidadeInvalidaException("Município da unidade é obrigatório");
        }

        Municipio municipio = municipioRepository.get(unidadeForm.getIdMunicipio());
        unidade.setMunicipio(municipio);

        Integer idUnidadeSuperior = unidadeForm.getIdUnidadeSuperior();
        if (idUnidadeSuperior != null) {
            unidade.setUnidadeSuperior(repository.get(idUnidadeSuperior));
        }

        definirGestoresDaUnidade(unidade, unidadeForm);

        unidade = repository.put(unidade);
        atualizarHierarquia(unidade);
        return unidade;
    }

    private void definirGestoresDaUnidade(Unidade unidade, UnidadeForm unidadeForm) {
        unidade.getGestores().clear();
        unidade.getGestores().addAll(buscadorDeUsuario.getPorIds(unidadeForm.getGestores()));
    }

    private void atualizarHierarquia(Unidade unidade) {
        unidade.setHierarquia(unidade.construirHierarquia());
        repository.put(unidade);
        unidade.getUnidadesFilhas().forEach(this::atualizarHierarquia);
    }
}
