package integracao.admin.usuario;

import integracao.AdminPage;
import integracao.TestadorDeAutocomplete;

import static io.github.seleniumquery.SeleniumQuery.$;

public class CadastrarUsuarioPage extends AdminPage {

    public static CadastrarUsuarioPage ir() { return ir("auth/usuario/novo", CadastrarUsuarioPage.class); }
    public static CadastrarUsuarioPage ir(int idConteudo) { return ir("auth/usuario/" + idConteudo, CadastrarUsuarioPage.class); }

    public String getNome() { return $("#nome").val(); }
    public String getCpf() { return $("#cpf").val(); }
    public String getLogin() { return $("#login").val(); }
    public String getSiape() { return $("#siape").val(); }
    public String getUnidades() { return $("#unidades").val(); }
    public String getEmail() { return $("#email").val(); }
    public String getTelefone() { return $("#telefone").val(); }
    public boolean getAdmin() { return $("#cbPerfil0").is(":checked"); }
    public String getChaveApiUsuarioLogado() { return $("#chaveApiUsuarioLogado").val(); }
    public String esperaEGetChaveApiUsuarioLogado() { return $("#chaveApiUsuarioLogado").waitUntil().val().not().isEqualTo("").then().val(); }

    public void clicarEmSalvar() { $("#btnSalvar").waitViewClick(); }

    public void clicarEmAdmin() { $("#cbPerfil0").waitViewClick(); }
    
    public void clicarEmBtnChaveApi() { $("#btnChaveApiUsuarioLogado").waitViewClick();}
    
    public void clicarEmBtnSalvarUsuarioLogado() { $("#btnSalvarUsuarioLogado").waitViewClick();}

    public void setLogin(String value) {
        $("#login").val(value);
    }

    public boolean isLoginDisabled() {
        return $("#login").is(":disabled");
    }

    public void clicarBtnAtivar() {
        $("#btnAtivar").waitViewClick();
    }
    
    public void clicarBtnDesativar() {
        $("#btnDesativar").waitViewClick();
    }
    
	public void setNome(String value) {
		 $("#nome").val(value);
	}
	
	public void setCpf(String value) {
		 $("#cpf").val(value);
	}
	
	public void setSiape(String value) {
		 $("#siape").val(value);
	}
	
	public void setEmail(String value) {
		 $("#email").val(value);
	}
	
	public void setUnidades(String value) {
		TestadorDeAutocomplete.buscarESelecionarPrimeiro("unidades", value);
	}
	
	public void setTelefone(String value) {
		 $("#telefone").val(value);
		
	}
	
	public void setEmailUsuarioLogado(String value) {
        $("#emailUsuarioLogado").val(value);
   }
   
   public void setTelefoneUsuarioLogado(String value) {
        $("#telefoneUsuarioLogado").val(value);
       
   }
   
	public boolean chaveApiPresente() {
		return $("#chaveApiUsuarioLogado") != null; 
	}
	
}
