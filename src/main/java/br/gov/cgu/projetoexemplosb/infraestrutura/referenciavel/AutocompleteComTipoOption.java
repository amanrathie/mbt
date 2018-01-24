package br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel;

import br.gov.cgu.componentes.pojo.AutocompleteOption;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AutocompleteComTipoOption extends AutocompleteOption {

    private String tipo;

    public AutocompleteComTipoOption(String id, String name, String tipo) {
        super(id, name);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AutocompleteComTipoOption)) {
            return false;
        }

        AutocompleteComTipoOption that = (AutocompleteComTipoOption) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(tipo, that.tipo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(tipo)
                .toHashCode();
    }
}
