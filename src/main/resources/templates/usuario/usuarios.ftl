<#-- @ftlvariable name="filtro" type="br.gov.cgu.projetoexemplosb.aplicacao.auth.UsuarioFiltro" -->
<#include "../_formulario.ftl"/>
<#include "../_datatables.ftl"/>
<#assign scriptContent>
<@autocompleteScripts/>
<@datatableScripts/>

<script type="text/javascript" src="<@spring.url "/static/js/auth/PesquisaUsuario.js"/>"></script>

</#assign>

<#assign breadcrumb>
<i class="fa fa-users"></i> Usuários
</#assign>
<#assign botoesFlutuantes>
    <@botaoFiltros/>
    <#if !modoPopup??>
    <a class="btn btn-info btn-floating-action botao-flutuante z-depth-3" href="<@spring.url '/auth/usuario/novo'/>"
       data-toggle="tooltip" title="Novo Usuário"><i class="fa fa-plus"></i></a>
    </#if>
</#assign>
<#assign offside>
    <@boxFiltros>
        <@campoTextoFormulario label="Nome" id="nome" value=filtro.nome! />
        <@campoTextoFormulario label="CPF" id="cpf" value=filtro.cpf! />
        <@campoTextoFormulario label="SIAPE" id="siape" value=filtro.siape! />
        <@campoTextoFormulario label="Login" id="login" value=filtro.login! />
        <div class="form-group">
            <label for="ativo">Situação </label>
            <select class="form-control" id="ativo" name="ativo">
                <option value="" <#if !(filtro.ativo??)>selected="selected"</#if>>Todos</option>
                <option value="true" <#if filtro.ativo?? && !filtro.ativo>selected="selected"</#if>>Ativos</option>
                <option value="false" <#if filtro.ativo?? && filtro.ativo>selected="selected"</#if>>Inativos</option>
            </select>
        </div>

        <@autocomplete label="Unidade" id="unidade" tipo="br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade" valores=filtro.unidades maximoItens=1 buscaAvancada="unidade"/>

    </@boxFiltros>
</#assign>
<@layout.portal script=scriptContent titulo="Usuários" breadcrumb=breadcrumb botoesFlutuantes=botoesFlutuantes offside=offside>
    <@bs.panelTable>
        <@datatableHtml id="lista" titulos=["CPF", "Nome", "Login", "Ações"]/>
    </@bs.panelTable>


</@layout.portal>