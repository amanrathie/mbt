package br.gov.cgu.mbt.web;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CacheConfig(cacheNames = "Principal")
public class HomeController {


    @RequestMapping(value = "/", method= RequestMethod.GET)
    @Cacheable
    public ModelAndView paginaInicial() {
        return new ModelAndView("publico/index");
    }

    @RequestMapping(value = "/_exception", method= RequestMethod.GET)
    public ModelAndView produzirException() {
        throw new RuntimeException("Exception de teste provocada de propósito.");
    }

}