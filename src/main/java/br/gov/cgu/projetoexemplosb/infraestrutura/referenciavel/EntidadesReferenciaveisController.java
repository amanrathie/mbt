package br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel;

import br.gov.cgu.componentes.pojo.AutocompleteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CacheConfig(cacheNames = "Autocomplete")
public class EntidadesReferenciaveisController {

    private final EntidadesReferenciaveis entidadesReferenciaveis;

    @Autowired
    public EntidadesReferenciaveisController(EntidadesReferenciaveis entidadesReferenciaveis) {
        this.entidadesReferenciaveis = entidadesReferenciaveis;
    }

    @RequestMapping(value = "/{clazzName}/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AutocompleteOption> autocomplete(@PathVariable String clazzName, @RequestParam String q) {
        return entidadesReferenciaveis.getAutocompletePorTermo(clazzName, q);
    }

    @RequestMapping(value = "/{clazzName}/autocomplete/ids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AutocompleteOption> autocompletePorIds(@PathVariable String clazzName, @RequestParam List<Integer> ids) {
        return entidadesReferenciaveis.getAutocompletePorIds(clazzName, ids);
    }

}