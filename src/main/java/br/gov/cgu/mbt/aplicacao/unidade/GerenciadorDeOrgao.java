package br.gov.cgu.mbt.aplicacao.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoForm;
import br.gov.cgu.mbt.negocio.unidade.OrgaoRepository;

@Service
public class GerenciadorDeOrgao {

    private OrgaoRepository repository;

    @Autowired
    public GerenciadorDeOrgao(OrgaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void alternarAtivacao(Integer id) {
        Orgao orgao = repository.get(id);
        orgao.setAtivo(!orgao.isAtivo());
        repository.put(orgao);
    }

    @Transactional
    public Orgao salvar(OrgaoForm orgaoForm){
        Orgao orgao;

        if (orgaoForm.getId() == null) {
            orgao = new Orgao();
            orgao.setAtivo(true);
        } else {
            orgao = repository.getEagerLoaded(orgaoForm.getId());
        }

        orgao.setNome(orgaoForm.getNome());
        orgao.setSigla(orgaoForm.getSigla());
        orgao.setTipo(orgaoForm.getTipo());
        orgao.setCodigo(orgaoForm.getCodigo());

        return repository.put(orgao);
    }
}
