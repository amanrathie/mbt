package br.gov.cgu.projetoexemplosb.aplicacao.auth;

public class UsuarioLogadoForm {
    private String emailUsuarioLogado;
    private String telefoneUsuarioLogado;
    private String chaveApiUsuarioLogado;
    private Byte frequenciaRecebimentoEmail;

    public String getEmailUsuarioLogado() {
        return emailUsuarioLogado;
    }

    public void setEmailUsuarioLogado(String emailUsuarioLogado) {
        this.emailUsuarioLogado = emailUsuarioLogado;
    }

    public String getTelefoneUsuarioLogado() {
        return telefoneUsuarioLogado;
    }

    public void setTelefoneUsuarioLogado(String telefoneUsuarioLogado) {
        this.telefoneUsuarioLogado = telefoneUsuarioLogado;
    }

    public String getChaveApiUsuarioLogado() {
        return chaveApiUsuarioLogado;
    }

    public void setChaveApiUsuarioLogado(String chaveApiUsuarioLogado) {
        this.chaveApiUsuarioLogado = chaveApiUsuarioLogado;
    }

    public Byte getFrequenciaRecebimentoEmail() {
        return frequenciaRecebimentoEmail;
    }

    public void setFrequenciaRecebimentoEmail(Byte frequenciaRecebimentoEmail) {
        this.frequenciaRecebimentoEmail = frequenciaRecebimentoEmail;
    }
}
