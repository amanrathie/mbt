package br.gov.cgu.projetoexemplosb.infraestrutura.ehcache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Gerador das chaves do Cache.
 */
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder key = new StringBuilder(o.getClass().getSimpleName());
        key.append(".").append(method.getName());
        for (Object object : objects) {
            if (object == null)  {
                key.append("#null");
            }
            else {
                key.append("#")
                   .append(object.hashCode());
            }
        }
        return key.toString();
    }

}
