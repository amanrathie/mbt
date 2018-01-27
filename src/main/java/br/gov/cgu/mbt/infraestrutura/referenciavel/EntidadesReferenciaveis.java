package br.gov.cgu.mbt.infraestrutura.referenciavel;

import br.gov.cgu.componentes.pojo.AutocompleteOption;
import br.gov.cgu.componentes.pojo.MultiselectOption;
import br.gov.cgu.persistencia.jpa.Entidade;
import org.apache.commons.lang.reflect.MethodUtils;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntidadesReferenciaveis {

    private final ApplicationContext applicationContext;
    private List<MultiselectOption> options = new Reflections("br.gov.cgu")
            .getTypesAnnotatedWith(Referenciavel.class)
            .stream()
            .filter(aClass -> aClass.getAnnotation(Referenciavel.class) != null)
            .map(aClass -> new MultiselectOption(aClass.getName(), aClass.getAnnotation(Referenciavel.class).label()))
            .collect(Collectors.toList());

    @Autowired
    public EntidadesReferenciaveis(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<MultiselectOption> getEntidadesReferenciaveis() {
        return options;
    }

    public List<AutocompleteOption> getAutocompletePorTermo(String clazz, String termo) {
        return buscarNoRepositoryETransformarEmAutocomplete(clazz, "getPorTermo", termo);
    }

    public List<AutocompleteOption> getAutocompletePorIds(String clazz, List<Integer> ids) {
        return buscarNoRepositoryETransformarEmAutocomplete(clazz, "getPorIds", ids);
    }

    public List<Entidade> getPorTermo(String clazz, String termo) {
        return getEntidadesReferenciaveis(clazz, "getPorTermo", termo);
    }

    public List<Entidade> getPorIds(String clazz, List ids) {

        return getEntidadesReferenciaveis(clazz, "getPorIds", ids);
    }

    public Entidade get(String clazz, Object id) {
        return getEntidadeReferenciavel(clazz, id);
    }

    private List<AutocompleteOption> buscarNoRepositoryETransformarEmAutocomplete(String clazz, String nomeDoMetodo, Object parametro) {
        return getEntidadesReferenciaveis(clazz, nomeDoMetodo, parametro)
                .stream()
                .map(x -> new AutocompleteOption(x.getId().toString(), x.toString()))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<Entidade> getEntidadesReferenciaveis(String clazz, String nomeDoMetodo, Object parametro)  {
        try {
            Class<?> repositoryClazz = Class.forName(clazz + "Repository");
            Object repository = applicationContext.getBean(repositoryClazz);
            Method method = MethodUtils.getMatchingAccessibleMethod(repositoryClazz, nomeDoMetodo, new Class[]{parametro.getClass()});
            return (List<Entidade>) method.invoke(repository, parametro);
        } catch (ClassNotFoundException|BeansException e) {
            throw new RepositoryNaoEncontradoException(clazz, e);
        } catch (NullPointerException | InvocationTargetException | IllegalAccessException e) {
            throw new MetodoDoRepositoryException(clazz, nomeDoMetodo, e);
        }
    }

    @SuppressWarnings("unchecked")
    private Entidade getEntidadeReferenciavel(String clazz, Object parametro)  {
        try {
            Class<?> repositoryClazz = Class.forName(clazz + "Repository");
            Object repository = applicationContext.getBean(repositoryClazz);
            Method method = MethodUtils.getMatchingAccessibleMethod(repositoryClazz, "get", new Class[]{parametro.getClass()});
            return (Entidade) method.invoke(repository, parametro);
        } catch (ClassNotFoundException|BeansException e) {
            throw new RepositoryNaoEncontradoException(clazz, e);
        } catch (NullPointerException | InvocationTargetException | IllegalAccessException e) {
            throw new MetodoDoRepositoryException(clazz, "get", e);
        }
    }

}
