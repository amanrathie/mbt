<#-- @ftlvariable name="filtro" type="br.gov.cgu.projetoexemplosb.aplicacao.auth.PerfilFiltro" -->
<#include "../_formulario.ftl"/>
<#include "../_datatables.ftl"/>
<#assign scriptContent>
<@autocompleteScripts/>
<@datatableScripts/>

<script type="text/javascript" src="<@spring.url "/static/js/auth/PesquisaPerfil.js"/>"></script>

</#assign>

<#assign breadcrumb>
<i class="far fa-id-card"></i> Perfil
</#assign>
<#assign botoesFlutuantes>
    <a class="btn btn-info btn-floating-action botao-flutuante z-depth-3" href="<@spring.url '/auth/perfil/novo'/>"
       data-toggle="tooltip" title="Novo Perfil"><i class="fa fa-plus"></i></a>
</#assign>
<@layout.portal script=scriptContent titulo="Usuários" breadcrumb=breadcrumb botoesFlutuantes=botoesFlutuantes>

    <@bs.panelTable>
        <@datatableHtml id="lista" titulos=["Nome", "Oferecido no CADU?","Ações"]/>
    </@bs.panelTable>

</@layout.portal>