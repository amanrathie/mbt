<#-- @ftlvariable name="filtro" type="br.gov.cgu.projetoexemplosb.aplicacao.unidade.UnidadeFiltro" -->
<#-- @ftlvariable name="tipos" type="br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade[]" -->
<#include "../_formulario.ftl"/>
<#include "../_datatables.ftl"/>
<#assign scriptContent>
<@autocompleteScripts/>
<@datatableScripts/>

<script type="text/javascript" src="<@spring.url "/static/js/unidade/PesquisaUnidade.js"/>"></script>

<script>
    PesquisaUnidade.init(${(modoPopup??)?c});
</script>
</#assign>

<#assign breadcrumb>
<i class="fa fa-home"></i> Unidades
</#assign>
<#assign botoesFlutuantes>
    <@botaoUsarSelecionados idTabela="lista"/>
    <@botaoFiltros/>
    <#if !modoPopup??>
    <a class="btn btn-info btn-floating-action botao-flutuante z-depth-3" href="<@spring.url '/auth/unidade/novo'/>"
       data-toggle="tooltip" title="Nova Unidade"><i class="fa fa-plus"></i></a>
    </#if>
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
        </div>
        <@campoSelectFormulario label="Tipo" id="tipo" opcoes=tipos valorSelecionado=filtro.tipo opcaoPadrao="Todos" />
        <@campoTextoFormulario label="Código" id="codigo" value=filtro.codigo!/>
        <@campoTextoFormulario label="Sigla" id="sigla" value=filtro.sigla!/>
        <@campoSelectFormulario label="UF" id="uf" opcaoPadrao="Todas" valorSelecionado=filtro.uf opcoes=ufs disabled=disabled />
        <@autocomplete label="Município" id="idsMunicipio" tipo="br.gov.cgu.projetoexemplosb.negocio.nucleo.Municipio" valores=[filtro.idsMunicipio!] tipoValores="IDS" />
    </@boxFiltros>
</#assign>

<@layout.portal script=scriptContent titulo="Unidades" breadcrumb=breadcrumb botoesFlutuantes=botoesFlutuantes offside=offside>
    <@bs.panelTable>
        <@datatableHtml id="lista" titulos=["Tipo","Nome","Sigla","Código","UF","Município","Ações"] colunaDeSelecao=true/>
    </@bs.panelTable>

</@layout.portal>