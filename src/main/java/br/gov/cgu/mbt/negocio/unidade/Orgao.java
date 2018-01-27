package br.gov.cgu.mbt.negocio.unidade;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema = Constantes.SCHEMA_APLICACAO)
public class Orgao implements Entidade<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdOrgao")
    private Integer id;

    @Column(name = "IdTipoOrgao")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Tipo é obrigatório")
    private TipoOrgao tipo;

    @Column(name="NomOrgao")
    @NotBlank(message = "Nome do Órgão não pode estar em branco")
    @Size(min = 1, max = 1000, message = "Tamanho máximo do Nome é de 1000 caracteres")
    private String nome;

    @Column(name="SigOrgao")
    @NotBlank(message = "Sigla não pode estar em branco")
    @Size(min = 1, max = 20, message = "Tamanho máximo da Sigla é de 20 caracteres")
    private String sigla;

    @Column(name="CodOrgao")
    @NotBlank(message = "Código não pode estar em branco")
    @Size(min = 1, max = 20, message = "Tamanho máximo do Código é de 20 caracteres")
    private String codigo;

    @Column(name="FlgAtivo")
    private boolean ativo = true;

    @Override
    public String toString() {
        if (codigo == null || nome == null || sigla == null) {
            return "";
        }

        return codigo + " - " + nome + " (" + sigla + ")";
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoOrgao getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrgao tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}