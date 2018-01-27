package br.gov.cgu.mbt.negocio.auth;

import org.springframework.security.core.GrantedAuthority;

/*
* Atenção! Não mudar a ordem das constantes! Caso contrário, associação Perfil/Permissão vai ficar inconsistente.
* */
public enum Permissao implements GrantedAuthority{
    GERENCIAR_TODAS_INSTANCIAS("Gerenciar todas instâncias do sistema"),
    MENU_ADMINISTRACAO("Visualiza o Menu Administração"),
    MENU_AUDITORIA_CONTINUA("Visualiza o Menu Auditoria Contínua"),
    MENU_PLANEJAMENTO("Visualiza o Menu Planejamento"),
    CONSULTAR_USUARIOS("Consulta de Usuários"),
    GERENCIAR_USUARIOS("Criar/Editar Usuários"),
    CONSULTAR_UNIDADES("Consulta de Unidades"),
    GERENCIAR_UNIDADES("Criar/Editar Unidades"),
    CONSULTAR_TRILHAS("Consulta de Trilhas"),
    GERENCIAR_TRILHAS("Criar/Editar Trilhas"),
    GERENCIAR_CAMPOS("Criar/Editar Campos"),
    GERENCIAR_SUBCLASSES("Criar/Editar Subclasses"),
    CONSULTAR_RISCOS("Consulta de Riscos"),
    GERENCIAR_RISCOS("Criar/Editar Riscos"),
    GERENCIAR_AREAS_DE_CONHECIMENTO("Criar/Editar Áreas de Conhecimento"),
    GERENCIAR_AVISOS("Gerenciar Avisos do Sistema");

    private final String nome;

    Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }


}