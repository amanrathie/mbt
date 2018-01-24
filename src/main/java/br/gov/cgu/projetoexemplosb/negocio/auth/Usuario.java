package br.gov.cgu.projetoexemplosb.negocio.auth;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel.Referenciavel;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.jpa.Entidade;
import br.gov.cgu.utils.CpfCnpjUtils;
import br.gov.cgu.utils.PasswordUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Audited
@Entity
@Table(name = "Usuario", schema = Constantes.SCHEMA_APLICACAO)
@Referenciavel(label = "Usuário")
public class Usuario implements Entidade<Integer>, Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer id;

    @Column(name = "NomUsuario")
    @NotBlank(message = "Nome do usuário não pode estar em branco")
    @Size(min = 1, max = 1000, message = "Tamanho máximo do Nome é de 1000 caracteres")
    private String nome;

    @Column(name = "CpfUsuario")
    @NotBlank(message = "Cpf é obrigatório")
    private String cpf;

    @Column(name = "SiapeUsuario")
    private String siape;

    @Column(name = "DescLogin")
    @NotBlank(message = "Login é obrigatório")
    private String login;

    @Column(name = "EmlUsuario")
    @Size(max = 200, message = "Tamanho máximo do E-mail é de 200 caracteres")
    @Email(message = "E-mail inválido.")
    private String email;

    @Column(name = "TelUsuario")
    @Size(max = 25, message = "Tamanho máximo do Telefone é de 25 caracteres")
    private String telefone;

    @Column(name = "ChaveApi")
    private String chaveApi;

    @NotAudited
    @Column(name = "DthUltimoLogin")
    private LocalDateTime dataUltimoLogin;

    @Column(name = "FlgAtivo")
    private boolean ativo = true;

    @Column(name = "FreqRecebEmail")
    private byte frequenciaRecebimentoEmail;

    @Audited(targetAuditMode = NOT_AUDITED)
    @ManyToMany
    @JoinTable(name = "PerfisDoUsuario", schema = Constantes.SCHEMA_APLICACAO,
            joinColumns = {@JoinColumn(name = "IdUsuario")},
            inverseJoinColumns = {@JoinColumn(name = "IdPerfil")})
    private List<Perfil> perfis = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "LotacoesDoUsuario", schema = Constantes.SCHEMA_APLICACAO,
            joinColumns = {@JoinColumn(name = "IdUsuario")},
            inverseJoinColumns = {@JoinColumn(name = "IdUnidade")})
    private List<Unidade> unidades = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissao> permissoes = new ArrayList<>();
        getPerfis().forEach(p -> permissoes.addAll(p.getPermissoes()));
        return permissoes;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return getLogin();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return isAtivo();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean hasPerfil(Perfil perfil) {
        return isNotEmpty(perfis) && perfis.stream().anyMatch(p -> p.equals(perfil));
    }

    public boolean hasPermissao(Permissao permissao) {
        return isNotEmpty(getAuthorities()) && getAuthorities().stream().anyMatch(p -> p.equals(permissao));
    }

    public boolean hasPermissao(String permissaoName) {
        Permissao permissao = Permissao.valueOf(permissaoName);
        return isNotEmpty(getAuthorities()) && getAuthorities().stream().anyMatch(p -> p.equals(permissao));
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = CpfCnpjUtils.desformatarCpf(cpf);
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
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

    public String getChaveApi() {
        return chaveApi;
    }

    public void setChaveApi(String chaveApi) {
        this.chaveApi = chaveApi;
    }

    public LocalDateTime getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(LocalDateTime dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public String sugerirChaveApi() {
        return PasswordUtils.hashMD5(getLogin() + LocalDateTime.now().toString());
    }

    public byte getFrequenciaRecebimentoEmail() {
        return frequenciaRecebimentoEmail;
    }

    public void setFrequenciaRecebimentoEmail(byte frequenciaRecebimentoEmail) {
        this.frequenciaRecebimentoEmail = frequenciaRecebimentoEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean estaLotadoNaUnidadeOuLotadoEmUnidadeSuperior(Unidade unidade) {
        return this.unidades.stream().anyMatch(u -> unidade != null && (u.equals(unidade) || u.possuiUnidadeFilha(unidade)));
    }

    @Override
    public String toString() {
        if (cpf == null || nome == null || login == null) {
            return "";
        }

        return CpfCnpjUtils.formatarCpf(cpf) + " - " + nome + " (" + login + ")";
    }
}