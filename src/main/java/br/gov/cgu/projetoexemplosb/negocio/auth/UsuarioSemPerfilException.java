package br.gov.cgu.projetoexemplosb.negocio.auth;

public class UsuarioSemPerfilException extends RuntimeException {
    public UsuarioSemPerfilException() {
        super("Usuário sem perfil para realizar a ação. Solicite a regularização a um administrador.");
    }
    
    public UsuarioSemPerfilException(Perfil perfil) {
        super("Somente usuários com a permissão " + perfil.getNome() + " podem realizar essa ação.");
    }
}
