package br.gov.cgu.mbt.negocio.unidade;

import java.util.ArrayList;
import java.util.List;

import br.gov.cgu.mbt.negocio.unidade.Unidade;

public final class ArvoreUnidadesBuilder {

    private static ArvoreUnidadesBuilder instance;
    private List<Unidade> ramos = null;

    private ArvoreUnidadesBuilder() {
        Unidade cgu = criarUnidade(1, "CGU", null);
        Unidade dti = criarUnidade(2, "DTI", cgu);
        Unidade cgtec = criarUnidade(3, "CGTEC", dti);
        Unidade cgsis = criarUnidade(4, "CGSIS", dti);
        Unidade cosis = criarUnidade(5, "COSIS", cgsis);
        Unidade ministerioX = criarUnidade(6, "Ministerio X", null);
        Unidade sfc = criarUnidade(7, "SFC", cgu);

        List<Unidade> filhas = new ArrayList<>();
        filhas.add(cosis);
        cgsis.setUnidadesFilhas(filhas);

        filhas = new ArrayList<>();
        filhas.add(cgtec);
        filhas.add(cgsis);
        dti.setUnidadesFilhas(filhas);

        filhas = new ArrayList<>();
        filhas.add(dti);
        filhas.add(sfc);
        cgu.setUnidadesFilhas(filhas);

        ramos = new ArrayList<Unidade>();
        ramos.add(cgu);
        ramos.add(ministerioX);
    }

    private Unidade criarUnidade(int id, String nome, Unidade unidadeSuperior) {
        Unidade dti = new Unidade();
        dti.setId(id);
        dti.setNome(nome);
        dti.setUnidadeSuperior(unidadeSuperior);
        dti.setHierarquia(dti.construirHierarquia());
        return dti;
    }

    public static synchronized ArvoreUnidadesBuilder getInstance() {
        if (instance == null) {
            instance = new ArvoreUnidadesBuilder();
        }

        return instance;
    }

    public Unidade getUnidadeId(List<Unidade> ramos, Integer id) {
        for (Unidade u : ramos) {
            if (u.getId() == id) {
                return u;
            } else {
                if (u.getUnidadesFilhas() != null && !u.getUnidadesFilhas().isEmpty()) {
                    Unidade retorno = getUnidadeId(u.getUnidadesFilhas(), id);
                    if (retorno != null) {
                        return retorno;
                    }
                }
            }
        }

        return null;
    }

    public Unidade getUnidadeId(Integer id) {
        return getUnidadeId(this.ramos, id);
    }
}