package br.gov.cgu.projetoexemplosb.negocio.unidade;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel.Referenciavel;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.negocio.nucleo.Municipio;
import br.gov.cgu.persistencia.jpa.Entidade;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Audited
@Entity
@Table(name = "Unidade", schema = Constantes.SCHEMA_APLICACAO)
@Referenciavel(label = "Unidade")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Unidade implements Entidade<Integer>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdUnidade")
    private Integer id;

    @Column(name = "IdTipoUnidade")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Tipo é obrigatório")
    private TipoUnidade tipo;

    @Column(name="IdOrgao")
    @NotNull(message = "Órgão é obrigatório")
    private Integer idOrgao;

    @Column(name="NomUnidade")
    @NotBlank(message = "Nome da unidade não pode estar em branco")
    @Size(min = 1, max = 1000, message = "Tamanho máximo do Nome é de 1000 caracteres")
    private String nome;

    @Column(name="SigUnidade")
    @NotBlank(message = "Sigla não pode estar em branco")
    @Size(min = 1, max = 20, message = "Tamanho máximo da Sigla é de 20 caracteres")
    private String sigla;

    @Column(name="CodUnidade")
    @NotBlank(message = "Código não pode estar em branco")
    @Size(min = 1, max = 20, message = "Tamanho máximo do Código é de 20 caracteres")
    private String codigo;

    @Column(name="FlgAtivo")
    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name="IdUnidadeSuperior")
    private Unidade unidadeSuperior;

    @OneToMany(mappedBy="unidadeSuperior", fetch = FetchType.LAZY)
    private List<Unidade> unidadesFilhas = new ArrayList<>();

    @Column(name="EmlUnidade")
    @Size(max = 200, message = "Tamanho máximo do E-mail é de 200 caracteres")
    @Email(message = "E-mail inválido.")
    private String email;

    @Column(name="TelUnidade")
    @Size(max = 25, message = "Tamanho máximo do Telefone é de 25 caracteres")
    private String telefone;

    @NotNull(message = "Município da unidade é obrigatório")
    @ManyToOne
    @JoinColumn(name="IdMunicipio")
    private Municipio municipio;

    @Column(name="DescHierarquia")
    private String hierarquia;

    @ManyToMany
    @JoinTable(name = "GestoresDaUnidade", schema = Constantes.SCHEMA_APLICACAO,
            joinColumns = {@JoinColumn(name = "IdUnidade")},
            inverseJoinColumns = {@JoinColumn(name = "IdUsuario")})
    private List<Usuario> gestores = new ArrayList<>();

    @AssertTrue(message="Unidade pai não pode ser a própria unidade")
    boolean isUnidadeDiferenteDaUnidadePai() {
        return id == null || unidadeSuperior == null || !id.equals(unidadeSuperior.getId());
    }

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

    public TipoUnidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidade tipo) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Unidade getUnidadeSuperior() {
        return unidadeSuperior;
    }

    public void setUnidadeSuperior(Unidade unidadeSuperior) {
        this.unidadeSuperior = unidadeSuperior;
    }

    public List<Unidade> getUnidadesFilhas() {
        return unidadesFilhas;
    }

    public void setUnidadesFilhas(List<Unidade> unidadesFilhas) {
        this.unidadesFilhas = unidadesFilhas;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getIdOrgao() {
        return idOrgao;
    }

    public void setIdOrgao(Integer idOrgao) {
        this.idOrgao = idOrgao;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public List<Usuario> getGestores() {
        return gestores;
    }

    public void setGestores(List<Usuario> gestores) {
        this.gestores = gestores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Unidade unidade = (Unidade) o;

        return id.equals(unidade.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Boolean possuiUnidadeFilha(Unidade unidade) {
        return unidade != null && unidade.getHierarquia().endsWith(this.getHierarquia());
    }

    public List<Unidade> getUnidadesHierarquiaSuperior() {
        List<Unidade> unidadesSuperiores = new ArrayList<>();
        Unidade superior = this;
        do {
            unidadesSuperiores.add(superior);
            superior = superior.getUnidadeSuperior();
        } while (superior != null);
        return unidadesSuperiores;
    }

    public String construirHierarquia() {
        return unidadeSuperior != null?
                "|" + this.getId() + unidadeSuperior.construirHierarquia():
                "|" + this.getId() + "|";
    }
}