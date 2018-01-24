<#macro campoTextoFormulario label id autofocus=false obrigatorio=false value="" disabled=false readonly=false class="" placeholder="" type="text">
    <div class="form-group">
        <label for="${id}">${label}</label>
        <#if obrigatorio>
        <div class="input-group">
        </#if>
        <input type="${type}" id="${id}" name="${id}" value="${value}" class="form-control ${class}" placeholder="${placeholder}"
               <#if autofocus>autofocus</#if> <#if disabled>disabled="disabled"</#if> <#if readonly>readonly="readonly"</#if>/>
        <#if obrigatorio>
            <span class="input-group-addon" data-toggle="tooltip" title="Obrigatório"><i class="fa fa-asterisk"></i></span>
        </div>
        </#if>
    </div>
</#macro>

<#--
    Lista de opções pode ser um array ou lista de ENUM.
    Se for outro tipo, tem que ser informado via tipoOpcao="X"
     Tipos possiveis:
     - lista de br.gov.cgu.componentes.pojo.MultiselectOption
 -->
<#macro campoSelectFormulario label id opcaoPadrao="" opcoes=[] tipoOpcao="ENUM" valorSelecionado=[] obrigatorio=false disabled=false readonly=false multiplo=false propriedades="">
    <div class="form-group">
        <label for="${id}">${label}</label>
        <#if obrigatorio>
        <div class="input-group">
        </#if>
        <select class="form-control" id="${id}" name="${id}"
                <#if disabled>disabled="disabled"</#if>
                <#if readonly>readonly="true"</#if>
                <#if multiplo>multiple</#if>${propriedades}>
            <#if opcaoPadrao != "">
            <option value="">${opcaoPadrao}</option>
            </#if>

            <#assign valoresSelecionadosString = []/>

            <#if multiplo>
                <#list valorSelecionado as v >
                    <#if v?is_number>
                        <#assign valoresSelecionadosString = valoresSelecionadosString + [v?c] />
                    <#else>
                        <#assign valoresSelecionadosString = valoresSelecionadosString + [v] />
                    </#if>
                </#list>
            <#else>
                <#assign valoresSelecionadosString = [valorSelecionado] />
            </#if>


            <#list opcoes as o>
                <#if tipoOpcao == "ENUM">
                    <option value="${o.name()}" <#if valoresSelecionadosString?seq_contains(o)>selected="selected"</#if>>${o.toString()}</option>
                <#else>
                    <option value="${o.id}" <#if valoresSelecionadosString?seq_contains(o.id) >selected="selected"</#if>>${o.value}</option>
                </#if>
            </#list>
        </select>
        <#if obrigatorio>
        <span class="input-group-addon" data-toggle="tooltip" title="Obrigatório"><i class="fa fa-asterisk"></i></span>
        </div>
        </#if>
    </div>
</#macro>

<#macro multiSelectScripts>
<script src="<@spring.url "/static/js/multiselect.js"/>"></script>
<script type="text/javascript" src="<@spring.url '/static/libs/bootstrap-multiselect/js/bootstrap-multiselect.js'/>"></script>
<link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap-multiselect/css/bootstrap-multiselect.css'/>" type="text/css"/>
<script>
    $(function(){
        Multiselect.criarMultiselects();
    });
</script>
</#macro>


<#--
tipoValores pode ser IDS ou ENTIDADE. Se for entidade, vai tentar pegar o .id de cada objeto da lista.
-->
<#macro autocomplete id tipo label valores=[] tipoValores="IDS" maximoItens=10 placeholder="Comece a digitar para ver sugestões" onSelect="" onDelete="" buscaAvancada="" obrigatorio=false url="" permiteCriarValores=false>
    <div class="form-group">
        <#if label != "">
            <label for="${id}">${label}</label>
        </#if>
        
        <#assign idValores = [] />

        <#if tipoValores == "ENTIDADE">
            <#list valores as v>
                <#if v != "">
                    <#assign idValores = idValores + [v.id] />
                </#if>
            </#list>
        <#else>
            <#list valores as v>
                <#assign idValores = idValores + [v] />
            </#list>
        </#if>

        <#if buscaAvancada != "" || obrigatorio>
            <div class="input-group">
        </#if>
            <input class="form-control" id="${id}" name="${id}" value="${idValores?join(",")}"
                   data-autocomplete-tipo="${tipo}"
                   data-autocomplete-on-select="${onSelect}"
                   data-autocomplete-on-delete="${onDelete}"
                   data-autocomplete-maximo-itens="${maximoItens}"
                   data-autocomplete-url="${url}"
                   data-autocomplete-permite-criar-valores="${permiteCriarValores?c}"
                   data-busca-avancada="${buscaAvancada}"
                   placeholder="${placeholder}"
            />

        <#if buscaAvancada != "">
            <span class="input-group-btn" data-toggle="tooltip" title="Busca Avançada">
                <a href="#" class="btn btn-warning"><i class="fa fa-filter"></i></a>
            </span>
        </#if>
        <#if obrigatorio>
            <span class="input-group-addon" data-toggle="tooltip" title="Obrigatório"><i class="fa fa-asterisk"></i></span>
        </#if>
        <#if buscaAvancada != "" || obrigatorio>
            </div>
        </#if>
    </div>
</#macro>

<#macro autocompleteScripts>
    <script src="<@spring.url "/static/js/autocomplete.js"/>"></script>
    <script src="<@spring.url "/static/libs/tokeninput/jquery.tokeninput.min.js"/>"></script>
    <link href="<@spring.url '/static/libs/tokeninput/token-input.css'/>" rel="stylesheet">
    <script>
        $(function(){
            Autocomplete.criarAutocompletes();
        });
    </script>
</#macro>


<#macro campoCheckbox label id isChecked dataName="" dataValue="" onChangeFunction="" labelAntes=false>
    <div class="form-group">
        <label class="switch" id="switchLabel_${id}">
            ${labelAntes?then(label,"")}
            <input type="checkbox" id="${id}" name="${dataName}" data-${dataName}="${dataValue}"
                   onchange="${onChangeFunction}" value="${dataValue}" ${isChecked?then("checked='checked'","")}>
            <span></span>
            ${labelAntes?then("",label)}
        </label>
    </div>
</#macro>

<#macro campoRadiobuttons id matriz valorAtual tamanho=12>
    <div class="row">
    <#list matriz as m>
        <div class="col-md-${tamanho}">
            <div class="radio c-radio c-radio-nofont">
                <label>
                    <input type="radio" id="${id}" name="${id}" value="${m[0]}" ${(m[0] == valorAtual?number)?then("checked","")}>
                    <span></span>${m[1]}
                </label>
            </div>
        </div>
    </#list>
    </div>
</#macro>

<#macro dialogConfirmacao mensagem dadosAdicionais=[] disposicao="left" titulo="Confirmação" okLabel="Ok" cancelaLabel="Cancelar">
    <a href="#" class="btnPopover" data-toggle="confirmation" data-popout="true" data-placement="${disposicao}" data-title="${titulo}"
       data-btn-ok-label="${okLabel}" data-btn-ok-icon="fas fa-share-alt" data-btn-ok-class="btn-success"
       data-btn-cancel-label="${cancelaLabel}" data-btn-cancel-icon="fas fa-times" data-btn-cancel-class="btn-danger"
       data-content="${mensagem}" ${dadosAdicionais}>
        <#nested />
    </a>
</#macro>