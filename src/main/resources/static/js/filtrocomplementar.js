/* global CamposDinamicos, Autocomplete */
/* exported FiltroComplementar */
/* jshint esversion:6*/
const FiltroComplementar = {};

FiltroComplementar.optionsPadrao = {
      idFiltro: 1,
      numeroCamposRecarregar: 0,
      numeroCamposRecarregados: 0,
      optionsCampo: {
          "TEXTO_CURTO"     :"<option value='0'>Igual</option><option value='3'>Diferente</option><option value='4'>Contém</option>",
          "TEXTO_LONGO"     :"<option value='0'>Igual</option><option value='3'>Diferente</option><option value='4'>Contém</option>",
          "NUMERICO"        :"<option value='0'>Igual</option><option value='1'>Maior</option><option value='2'>Menor</option><option value='3'>Diferente</option>",
          "VALOR"           :"<option value='0'>Igual</option><option value='1'>Maior</option><option value='2'>Menor</option><option value='3'>Diferente</option>",
          "DATA"            :"<option value='0'>Igual</option><option value='1'>Maior</option><option value='2'>Menor</option><option value='3'>Diferente</option>",
          "DATA_HORA"       :"<option value='0'>Igual</option><option value='1'>Maior</option><option value='2'>Menor</option><option value='3'>Diferente</option>",
          "BOOLEANO"        :"<option value='0'>Igual</option><option value='3'>Diferente</option>",
          "LISTA"           :"<option value='0'>Igual</option><option value='3'>Diferente</option>",
          "LISTA_MULTIVALOR":"<option value='4'>Contém</option><option value='3'>Diferente</option>",
          "REFERENCIA"      :"<option value='0'>Igual</option><option value='3'>Diferente</option>",
          "REFERENCIAS"     :"<option value='4'>Contém</option><option value='3'>Diferente</option>"
      }
};

FiltroComplementar.removerFiltro = (indice) => {
    $("#filtroComplementar" + indice).remove();
};

FiltroComplementar.serializarFiltrosDinamicos = () => {
    let filtroCamposDinamicos = {};
    $("[data-campo-dinamico]").each(function(){
        if ($(this).val()) {
            let identificador = $(this).attr("id").match(/\d+/g);
            let operador = $(`#operadorFiltro${identificador}`).val();
            let campo = $(`#campoFiltro${identificador}`).val();
            let valor = $(this).val().toString();
            if ($(this).is(":checkbox")) {
                valor = $(this).prop("checked");
            }
            filtroCamposDinamicos[$(this).attr("id")] = {campo: campo, operador: operador, valor: valor};
        }
    });
    return filtroCamposDinamicos;
};

FiltroComplementar.atualizarValorCampo = (campo, formato, valor) => {
    let valorConvertido = "";
    if (valor) {
        if (formato === "LISTA" || formato === "LISTA_MULTIVALOR") {
            valorConvertido = JSON.parse("[" + valor + "]");
            campo.val(valorConvertido);
        } else if (formato === "BOOLEANO") {
            valorConvertido = (valor === "true");
            campo.prop("checked", valorConvertido);
        } else {
            campo.val(valor);
        }
    }
};

FiltroComplementar.atualizarValor = (indice, valorOperador, valorFiltro, callBackCarregarGrid) => {
    return () => {
        let ids = $("#campoFiltro" + indice).val();
        if (ids.length === 0) {
            alert("Nenhum campo associado ainda.");
            return;
        }

        let camposAssociados = [];
        $.get(springUrl + "api/auth/campo-dinamico/" + ids , function(data){
            for(let i = 0; i < data.length; i++) {
                camposAssociados.push({
                    obrigatorio : false,
                    ordem : 1,
                    tamanhoFormulario : 5,
                    id : { campo : data[i] }
                });
            }
            let campo = camposAssociados[0].id.campo;
            let obrigatorio = camposAssociados[0].obrigatorio;
            let tamanhoFormulario = camposAssociados[0].tamanhoFormulario;

            $("#campo_" + indice).remove();

            try {
                //possibilita a exclusão do campo, caso contrário será criado um campo com o id do Campo selecionado
                campo.id = indice;

                let html = CamposDinamicos.gerarCampo[campo.formato](campo, obrigatorio, tamanhoFormulario, true);
                $(`#campo_${indice}Div`).append(html);

                let $campo = $(`#campo_${indice}` );
                $campo.append(html);
                FiltroComplementar.atualizarValorCampo($campo, campo.formato, valorFiltro);

                let $operador = $(`#operadorFiltro${indice}`);
                $operador.find("option").remove().end().append(FiltroComplementar.optionsPadrao.optionsCampo[campo.formato]);
                if (valorOperador) {
                    $operador.val(valorOperador);
                }

                $("[data-toggle='tooltip']").tooltip();
                $("[id=campo_" + indice + "][data-campo-dinamico=true][data-autocomplete-tipo]").each(function(){Autocomplete.criarAutocomplete($(this));});
                cgu.aplicarMascaraMoney();

                //Controle para aplicação dos filtros de campos dinamicos na grid de resultado
                FiltroComplementar.optionsPadrao.numeroCamposRecarregados++;
                if (FiltroComplementar.optionsPadrao.numeroCamposRecarregar === FiltroComplementar.optionsPadrao.numeroCamposRecarregados) {
                    callBackCarregarGrid();
                }
            } catch(err) {
                console.log(camposAssociados[0]);
                cgu.exibirErro("Não foi possível renderizar o campo dinâmico do formato: " + campo.formato);
            }
        });

    };
};

FiltroComplementar.inicializar = (local, callBackCarregarGrid) => {
    //Inclui o botão para adicionar novos filtros
    let html =
    `<div class='row'>
        <div class='col-md-12' id='filtros_${local}'>
        </div>
    </div>
    <div class='row'>
    <div class='col-md-12'>
        <div class='form-group'>
            <button type="button" onclick='FiltroComplementar.adicionarFiltro("filtros_${local}");' 
            title="Adicionar filtro complementar" class="btn btn-primary btn-labeled">
            <span class="btn-label"><i class="fa fa-plus"></i></span>Incluir campo</button>
        </div>            
    </div>
    </div>
    `;
    $("#" + local).append(html);

    //Recarregando os filtros pela querystring
    let params = $.deparam(window.location.search);
    jQuery.each(params.campos, function() {
        FiltroComplementar.optionsPadrao.numeroCamposRecarregar++;
    });
    jQuery.each(params.campos, function(i, campo) {
        FiltroComplementar.adicionarFiltro("filtros_"+local, false);
        let indice = FiltroComplementar.optionsPadrao.idFiltro-1;
        let idCampo    = `campoFiltro${indice}`;

        $(`#${idCampo}`).val(campo.campo);
        Autocomplete.criarAutocomplete($("#" + idCampo));

        FiltroComplementar.atualizarValor(indice, campo.operador, campo.valor, callBackCarregarGrid)();
    });

    //Adiciona campo inicial caso não existam filtros prévios
    if (!params.campos) {
        FiltroComplementar.adicionarFiltro("filtros_" + local);
        callBackCarregarGrid();
    }

};

FiltroComplementar.adicionarFiltro = (local, criarAutoCompleteCampo = true) => {
    let idCampo    = "campoFiltro" + FiltroComplementar.optionsPadrao.idFiltro;
    let idOperador = "operadorFiltro" + FiltroComplementar.optionsPadrao.idFiltro;
    let idValor    = "campo_" + FiltroComplementar.optionsPadrao.idFiltro;

    let html = `
    <div id="filtroComplementar${FiltroComplementar.optionsPadrao.idFiltro}">
        <div class='form-group'>
            <input class='form-control' id="${idCampo}" name="${idCampo}"
                   data-autocomplete-tipo='br.gov.cgu.projetoexemplosb.negocio.instancia.Campo'
                   data-autocomplete-on-select='FiltroComplementar.atualizarValor("${FiltroComplementar.optionsPadrao.idFiltro}")'
                   data-autocomplete-maximo-itens='1'
                   placeholder='Informe o campo desejado'
            />
        </div>
        <div class='form-group'>
            <select class='form-control' id="${idOperador}" name="${idOperador}">
            </select>
        </div>
        <div class='form-group' id="${idValor}Div">
            <input placeholder='Valor' class='form-control' id="${idValor}" name="${idValor}"/>
        </div>            
        <div class='form-group'>
            <button onclick='FiltroComplementar.removerFiltro("${FiltroComplementar.optionsPadrao.idFiltro}");' 
            title="Remover filtro complementar" class="btn btn-danger"><i class="fas fa-trash"></i></button>
        </div>            
    </div>
    `;
    FiltroComplementar.optionsPadrao.idFiltro = FiltroComplementar.optionsPadrao.idFiltro+1;
    $("#" + local).append(html);

    if (criarAutoCompleteCampo) {
        Autocomplete.criarAutocomplete($("#" + idCampo));
    }
};

/* jshint ignore:start*/
//funcao extraída do BBQ para recuperar objetos complexos no formato JSON
(function ($) {
    $.deparam = function (params, coerce) {
        let obj = {},
            coerce_types = { "true": !0, "false": !1, "null": null };

        // Iterate over all name=value pairs.
        $.each(params.replace(/\+/g, " ").split("&"), function (j,v) {
            let param = v.split("="),
                key = decodeURIComponent(param[0]),
                val,
                cur = obj,
                i = 0,

                // If key is more complex than 'foo', like 'a[]' or 'a[b][c]', split it
                // into its component parts.
                keys = key.split("]["),
                keys_last = keys.length - 1;

            // If the first keys part contains [ and the last ends with ], then []
            // are correctly balanced.
            if (/\[/.test(keys[0]) && /\]$/.test(keys[keys_last])) {
                // Remove the trailing ] from the last keys part.
                keys[keys_last] = keys[keys_last].replace(/\]$/, "");

                // Split first keys part into two parts on the [ and add them back onto
                // the beginning of the keys array.
                keys = keys.shift().split("[").concat(keys);

                keys_last = keys.length - 1;
            } else {
                // Basic 'foo' style key.
                keys_last = 0;
            }

            // Are we dealing with a name=value pair, or just a name?
            if (param.length === 2) {
                val = decodeURIComponent(param[1]);

                // Coerce values.
                if (coerce) {
                    val = val && !isNaN(val)              ? +val              // number
                        : val === "undefined"             ? undefined         // undefined
                            : coerce_types[val] !== undefined ? coerce_types[val] // true, false, null
                                : val;                                                // string
                }

                if ( keys_last ) {
                    // Complex key, build deep object structure based on a few rules:
                    // * The 'cur' pointer starts at the object top-level.
                    // * [] = array push (n is set to array length), [n] = array if n is
                    //   numeric, otherwise object.
                    // * If at the last keys part, set the value.
                    // * For each keys part, if the current level is undefined create an
                    //   object or array based on the type of the next keys part.
                    // * Move the 'cur' pointer to the next level.
                    // * Rinse & repeat.
                    for (; i <= keys_last; i++) {
                        key = keys[i] === "" ? cur.length : keys[i];
                        cur = cur[key] = i < keys_last
                            ? cur[key] || (keys[i+1] && isNaN(keys[i+1]) ? {} : [])
                            : val;
                    }

                } else {
                    // Simple key, even simpler rules, since only scalars and shallow
                    // arrays are allowed.

                    if ($.isArray(obj[key])) {
                        // val is already an array, so push on the next value.
                        obj[key].push( val );

                    } else if (obj[key] !== undefined) {
                        // val isn't an array, but since a second value has been specified,
                        // convert val into an array.
                        obj[key] = [obj[key], val];

                    } else {
                        // val is a scalar.
                        obj[key] = val;
                    }
                }

            } else if (key) {
                // No value was defined, so set something meaningful.
                obj[key] = coerce
                    ? undefined
                    : '';
            }
        });

        return obj;
    };
})(jQuery);
/* jshint ignore:end*/