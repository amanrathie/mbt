<#ftl output_format="HTML">
<#include "../_formulario.ftl"/>
<#include "../_datatables.ftl"/>

<#assign scriptContent>
    <@datatableScripts/>
    <@autocompleteScripts/>
    <script src="<@spring.url "/static/js/index/CaixaDeEntrada.js"/>"></script>
    <script src="<@spring.url "/static/js/instancia/AlternarInstanciaFavorita.js"/>"></script>
    <script src="<@spring.url "/static/js/instancia/PesquisaInstancia.js"/>"></script>
</#assign>
<#assign breadcrumb>
<i class="fa fa-inbox"></i> Minha Caixa de Entrada
</#assign>
<@layout.portal titulo = "Início" script=scriptContent breadcrumb=breadcrumb>

    <div class="panel panel-default" data-intro="Essas são as notificações em instâncias compartilhadas com você. Uma notificação é gerada quando você exerce um <b>papel</b> que necessita realizar alguma ação.">
        <div class="panel-heading clearfix">Notificações
            <span class="pull-right">
                <@campoCheckbox label="Mostrar Notificações Resolvidas" id="mostrarResolvidas" isChecked=false />
            </span>
        </div>
        <@datatableHtml id="notificacoes" titulos=["Resolvida?", "Notificação"] tableClass="hide-headers"/>
    </div>

    <@bs.panelTable titulo="Instâncias Favoritas" intro="Lista de instâncias que você marcou como favoritas. Clique na <i class='fa fa-star favorito fa-lg'></i> para remover da lista de favoritas.">
        <@datatableHtml id="instanciasFavoritas" tableClass="favoritar" titulos=["", "Id", "Assunto", "Atividade", "Situação", "Última Modificação"] />
    </@bs.panelTable>


</@layout.portal>