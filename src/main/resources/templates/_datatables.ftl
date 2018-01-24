<#macro datatableHtml id="lista" titulos=[] tableClass="" colunaDeSelecao=false>
    <#--<div class="table-responsive">-->
        <table id="${id}" class="table table-hover table-striped table-bordered ${tableClass}" width="100%">
            <thead>
            <tr>
                <#if colunaDeSelecao>
                <th id="colSelecionar" scope="col"><i id="btnSelectAll" onclick="dataTables.eventoSelectAll('${id}', this);" class="far fa-square"></i></th>
                </#if>
                <#list titulos as t>
                <th>${t}</th>
                </#list>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    <#--</div>-->
</#macro>

<#macro datatableScripts>
    <script type="text/javascript" src="<@spring.url '/static/libs/dataTables/jquery.dataTables.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/static/libs/dataTables/dataTables.bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/static/libs/dataTables/dataTables.select.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/static/js/datatables.js?v=#VERSAO-SISTEMA#'/>"></script>
    <link rel="stylesheet" href="<@spring.url '/static/libs/dataTables/dataTables.bootstrap.min.css'/>" type="text/css"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/dataTables/select.bootstrap.min.css'/>" type="text/css"/>
</#macro>

<#macro boxFiltros titulo="Filtros" idTabelaAlvo="lista" botaoFiltrarExecutaRefresh=true mostrarBtnSalvarFiltro=false mostrarBtnLimparFiltros=true>
    <form id="formFiltros">
        <a class="pull-right text-muted text-thin" data-toggle-state="offsidebar-open" data-no-persist="true"><i class="fas fa-times fa-2x"></i></a>
        <h3 class="text-center text-thin">${titulo}</h3>
        <#nested />

        <button id="btnFiltrar" type="button" class="btn btn-primary btn-labeled"
            <#if botaoFiltrarExecutaRefresh >
                data-datatables-refresh="#${idTabelaAlvo}"
            </#if>

        ><span class="btn-label"><i class="fa fa-search"></i></span>Filtrar</button>

        <#if mostrarBtnSalvarFiltro>
            <button type="button" id="btnSalvarFiltro" class="btn btn-warning btn-labeled"><span class="btn-label"><i class="fa fa-star"></i></span>Salvar</button>
        </#if>

        <#if mostrarBtnLimparFiltros>
            <button id="btnLimparFiltros" type="reset" class="btn btn-default btn-labeled"><span class="btn-label"><i class="fa fa-eraser"></i></span>Limpar</button>
        </#if>

    </form>
</#macro>

<#macro botaoFiltros target="filtros">
    <span data-toggle="tooltip" title="Exibir/Esconder Filtros" data-toggle-state="offsidebar-open" data-no-persist="true">
        <button class="btn btn-warning btn-floating-action botao-filtros botao-flutuante z-depth-3 collapsed"
       data-toggle="collapse" href="#${target}" aria-expanded="false" data-intro="Clique para abrir/fechar a caixa de filtros"><i class="fa fa-filter"></i> </button>
    </span>
</#macro>

<#macro botaoUsarSelecionados idTabela>
    <#if modoPopup??>
        <a class="btn btn-primary" href="#" onclick="dataTables.usarSelecionados('${idTabela}')"><i class="fa fa-check"></i> Usar Selecionados</a>
    </#if>
</#macro>


