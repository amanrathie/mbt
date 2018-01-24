<#macro collapsiblePanel id titulo >
<div class="panel panel-default" id="${id}" >
    <div class="panel-heading" data-toggle="collapse" href="#panel_${id}" aria-expanded="false" class="collapsed">${titulo}</div>
    <div class="panel-body collapse" id="panel_${id}">
        <#nested />
    </div>
</div>
</#macro>

<#macro panel id="" titulo="" class="panel-default" footer="" intro="" style="">
    <div class="panel ${class}" id="${id}" style="${style}"
         <#if intro!= "">
         data-intro="${intro}"
         </#if>
    >
        <#if (titulo?is_string && titulo != "") || !titulo?is_string >
        <div class="panel-heading">${titulo}</div>
        </#if>
        <div class="panel-body">
            <#nested />
        </div>
        <#if (footer?is_string && footer != "") || !footer?is_string >
        <div class="panel-footer">${footer}</div>
        </#if>
    </div>
</#macro>


<#macro panelTable id="" titulo="" class="panel-default" footer="" intro="" style="" body="">
<div class="panel ${class}" id="${id}" style="${style}"
    <#if intro!= "">
     data-intro="${intro}"
    </#if>
>
    <#if (titulo?is_string && titulo != "") || !titulo?is_string >
        <div class="panel-heading">${titulo}</div>
    </#if>
    <#if (body?is_string && body != "") || !body?is_string >
    <div class="panel-body">
        ${body}
    </div>
    </#if>
    <#if (footer?is_string && footer != "") || !footer?is_string >
        <div class="panel-footer">${footer}</div>
    </#if>
    <#nested />
</div>
</#macro>