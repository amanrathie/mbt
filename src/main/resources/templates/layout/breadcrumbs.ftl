<#if Request.BREADCRUMBS_PAGINA_ATUAL??>
<section class="box-identificacao">
    <div class="container">
                <span class="breadcrumb" xmlns:v="http://rdf.registros-vocabulary.org/#">
                    <strong>Você está aqui:</strong>
                    <span typeof="v:Breadcrumb">
                        <a href="<@spring.url '/'/>" rel="v:url" property="v:title">Início</a>
                    </span>
                    <#list Request.BREADCRUMBS_PAGINA_ATUAL as breadcrumb>
                        <#if breadcrumb.paginaAtual>
                            » <strong class="breadcrumb-atual" property="v:title">${breadcrumb.titulo}</strong>
                        <#else>
                            » <a href="<@spring.url '${breadcrumb.url}'/>" rel="v:url"
                                 property="v:title">${breadcrumb.titulo}</a>
                        </#if>
                    </#list>
                </span>
    </div>
    <hr>

    <#if titulo?has_content>
        <div class="container">
            <h2>${titulo}</h2>
        </div>
    </#if>
</section>
</#if>