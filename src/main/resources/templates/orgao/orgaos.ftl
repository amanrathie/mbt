<#-- @ftlvariable name="filtro" type="br.gov.cgu.projetoexemplosb.aplicacao.unidade.OrgaoFiltro" -->
<#-- @ftlvariable name="tipos" type="br.gov.cgu.projetoexemplosb.negocio.unidade.TipoOrgao[]" -->
<#include "../_formulario.ftl"/>
<#include "../_datatables.ftl"/>
<#assign scriptContent>
<@datatableScripts/>

<script type="text/javascript" src="<@spring.url "/static/js/orgao/PesquisaOrgao.js"/>"></script>

<script>
    PesquisaOrgao.init(${(modoPopup??)?c});
</script>
</#assign>

<#assign breadcrumb>
<i class="fa fa-building"></i> Órgãos
</#assign>

<#assign botoesFlutuantes>
    <@botaoFiltros/>
    <#if !modoPopup??>
    <a class="btn btn-info btn-floating-action botao-flutuante z-depth-3" href="<@spring.url '/auth/orgao/novo'/>"
       data-toggle="tooltip" title="Novo Órgão"><i class="fa fa-plus"></i></a>
    </#if>
    <@botaoUsarSelecionados idTabela="lista"/>
</#assign>
<#assign offside>
    <@boxFiltros>
        <@campoTextoFormulario label="Nome" id="nome" value=filtro.nome!/>
        <div class="form-group">
            <label for="ativo">Situação </label>
            <select class="form-control" id="ativo" name="ativo">
                <option value="" <#if !(filtro.ativo??)>selected="selected"</#if>>Todos</option>
                <option value="true" <#if filtro.ativo?? && filtro.ativo>selected="selected"</#if>>Ativos</option>
                <option value="false" <#if filtro.ativo?? && !filtro.ativo>selected="selected"</#if>>Inativos</option>
            </select>

        <@campoSelectFormulario label="Tipo" id="tipo" opcoes=tipos valorSelecionado=filtro.tipo opcaoPadrao="Todos" />
        <@campoTextoFormulario label="Código" id="codigo" value=filtro.codigo!/>
        <@campoTextoFormulario label="Sigla" id="sigla" value=filtro.sigla!/>
    </@boxFiltros>

</#assign>
<@layout.portal script=scriptContent titulo="Órgãos" breadcrumb=breadcrumb botoesFlutuantes=botoesFlutuantes offside=offside>
    <@bs.panelTable>
        <@datatableHtml id="lista" titulos=["Tipo","Nome","Sigla","Código","Ações"] colunaDeSelecao=true/>
    </@bs.panelTable>

</@layout.portal>