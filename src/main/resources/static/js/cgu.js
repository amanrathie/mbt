/*exported cgu, sortByKey*/
/* global getSlug, toastr */

/* jshint -W079 */
const cgu = {

    /**
     * Converte uma data em formato MM/YYYY para o formato DD/MM/YYYY
     * Ex: "12/2014" -> "01/12/2014"
     * @param data {string} string da data em formato MM/YYYY
     * @returns {string} string da data em formato DD/MM/YYYY
     */
    converterMMYYYYparaDDMMYYYY: function (data) {
        if (/^\d{2}\/\d{4}$/.test(data)) {
            return "01/" + data;
        }
        throw "Entrada inválida! Esperado: MM/YYYY.";
    }

    /**
     * Converte uma data em formato MM/YYYY para o ultimo dia do mês no formato DD/MM/YYYY
     * Ex: "12/2014" -> "31/12/2014"
     * @param data {string} string da data em formato MM/YYYY
     * @returns {string} string da data em formato DD/MM/YYYY
     */
    , converterMMYYYYparaUltimoDiaDoMesFormatoDDMMYYYY: function (data) {
        if (/^\d{2}\/\d{4}$/.test(data)) {
            const arrayData = data.split("/");
            const ultimoDia = new Date(arrayData[1], arrayData[0], 0);
            return (ultimoDia.getDate() + "/" + ("0" + (ultimoDia.getMonth() + 1)).slice(-2) + "/" + ultimoDia.getFullYear());
        }
        throw "Entrada inválida! Esperado: MM/YYYY.";
    }

    /**
     * Converte uma data do tipo Date para DD/MM/YYYY
     * Ex: "Sat Sep 09 2017 09:09:09 GMT-0300" -> "31/12/2014"
     * @param data {Date}
     * @returns {string} string da data em formato DD/MM/YYYY
     */
    , converterDataParaFormatoDDMMYYYY: function (data) {
        if (data instanceof Date) {
            return [cgu.preencherComZeroAEsquerda(data.getDate()),
                cgu.preencherComZeroAEsquerda(data.getMonth() + 1), data.getFullYear()].join("/");
        }
        throw "Parâmetro informado inválido! Esperado: Date.";
    }

    /**
     * Converte uma data do tipo LocalDate para DD/MM/YYYY
     * Ex: ""date":{"year":2009,"month":"SEPTEMBER","era":"CE","dayOfMonth":27,...}" -> "27/09/2009"
     * @param data {LocalDate}
     * @returns {string} string da data em formato DD/MM/YYYY
     */
    , converterLocalDateParaFormatoDDMMYYYY: function (data) {
        if (!cgu.isNullOuUndefined(data.year) && !cgu.isNullOuUndefined(data.monthValue) && !cgu.isNullOuUndefined(data.dayOfMonth)) {
            return [cgu.preencherComZeroAEsquerda(data.dayOfMonth),
                cgu.preencherComZeroAEsquerda(data.monthValue), data.year].join("/");
        }
        throw "Parâmetro informado inválido! Esperado: LocalDate.";
    }

    /**
     * Converte uma data do tipo Date para DD/MM/YYYY HH:MM:ss
     * Ex: "Sat Sep 27 2017 09:09:09 GMT-0300" -> "31/12/2014 08:39:51"
     * @param data {Date}
     * @returns {string} string da data em formato DD/MM/YYYY HH:MM:ss
     */
    , converterDataParaFormatoDDMMYYYYHHMMSS: function (data) {
        if (data instanceof Date) {
            const dataString = cgu.converterDataParaFormatoDDMMYYYY(data);
            return dataString.concat(" ")
                .concat([cgu.preencherComZeroAEsquerda(data.getHours()), cgu.preencherComZeroAEsquerda(data.getMinutes()),
                    cgu.preencherComZeroAEsquerda(data.getSeconds())].join(":"));
        }
        throw "Parâmetro informado inválido! Esperado: Date.";
    }

    /**
     * Converte uma data do tipo LocalDate para DD/MM/YYYY
     * Ex: ""date":{"year":2009,"month":"SEPTEMBER","era":"CE","dayOfMonth":27,...}" -> "27/09/2009 11:03:47"
     * @param data {LocalDate}
     * @returns {string} string da data em formato DD/MM/YYYY HH:MM:SS
     */
    , converterLocalDateTimeParaFormatoDDMMYYYYHHMMSS: function (data) {
        if (!cgu.isNullOuUndefined(data.year) && !cgu.isNullOuUndefined(data.monthValue) && !cgu.isNullOuUndefined(data.dayOfMonth) &&
            !cgu.isNullOuUndefined(data.hour) && !cgu.isNullOuUndefined(data.minute) && !cgu.isNullOuUndefined(data.second)) {
            const dataString = [cgu.preencherComZeroAEsquerda(data.dayOfMonth), cgu.preencherComZeroAEsquerda(data.monthValue), data.year].join("/");
            return dataString.concat(" ")
                .concat([cgu.preencherComZeroAEsquerda(data.hour), cgu.preencherComZeroAEsquerda(data.minute),
                    cgu.preencherComZeroAEsquerda(data.second)].join(":"));
        }
        throw "Parâmetro informado inválido! Esperado: LocalDateTime.";
    }

    , preencherComZeroAEsquerda: function (s) {
        return (s < 10) ? "0" + s : s;
    }

    /**
     * Converte uma string de valor monetário em Real em um Number.
     * Ex: "R$ 192.123.000,21" -> 192123000.21
     * @param string {string}
     * @returns {number}
     */
    , converterStringParaNumber: function (string) {
        if (cgu.isNullOuUndefined(string) || string === "") {
            return 0;
        }
        return Number((string + "").replace(/[^0-9\,]+/g, "").replace(",", "."));
    }

    /**
     * Abrevia um valor númerico. Exemplo: de 1.000.000,00 para 1 milhão
     * @param valor {string} string com o valor a ser formatado
     * @returns {string} string com o valor formatado
     */
    , abreviarValor: function (valor) {
        let valorConvertido = valor;
        if (typeof valor === "string") {
            valorConvertido = cgu.converterStringParaNumber(valor);
        }

        let abreviacao = "";

        if ((valorConvertido / 1000000000000) >= 1) {
            valorConvertido = valorConvertido / 1000000000000;
            if (valorConvertido < 2) {
                abreviacao = " trilhão";
            } else {
                abreviacao = " trilhões";
            }
        } else if ((valorConvertido / 1000000000) >= 1) {
            valorConvertido = (valorConvertido / 1000000000);
            if (valorConvertido < 2) {
                abreviacao = " bilhão";
            } else {
                abreviacao = " bilhões";
            }
        } else if ((valorConvertido / 1000000) >= 1) {
            valorConvertido = valorConvertido / 1000000;
            if (valorConvertido < 2) {
                abreviacao = " milhão";
            } else {
                abreviacao = " milhões";
            }
        } else if ((valorConvertido / 1000) >= 1) {
            valorConvertido = valorConvertido / 1000;
            abreviacao = " mil";
        } else {
            return valorConvertido.toFixed(0);
        }

        const valorAbreviado = valorConvertido.toFixed(2) + abreviacao;
        return valorAbreviado.replace(".", ",");
    }

    /**
     * converterArrayParaStringParametro
     */
    , getValoresSelecionadosDoMultiSelect: function (seletor) {
        const valor = $(seletor).val();
        if (!cgu.isNullOuUndefined(valor)) {
            return valor.toString();
        }

        return "";
    }

    /**
     * Obtem todos os ids dos checkbox selecionados em uma sequência de string no formato : id,id,id:
     */
    , getValoresSelecionadosDoCheckbox: function (seletor) {
        const valores = $(seletor).children().find("input[type='checkbox']");
        if (!cgu.isNullOuUndefined(valores) && valores.length > 0) {
            let valoresConcatenados = "";
            for (let i = 0; i < valores.length; i++) {
                if (valores[i].checked === true) {
                    valoresConcatenados += valores[i].id + ",";
                }
            }

            if (/,$/.test(valoresConcatenados)) {
                // substituimos o ultimo caractere por um vazio
                valoresConcatenados = valoresConcatenados.replace(/,$/, "");

            }
            return valoresConcatenados;
        }

        return "";
    }


    /**
     * Transforma um código + nome pro formato de url amigavel:
     * Ex: 25000 e MINISTÉRIO DA FAZENDA -> 25000-ministerio-da-fazenda
     */
    , getSlugParaUrl: function (codigo, nome) {
        return codigo + "-" + getSlug(nome);
    }

    , isNullOuUndefined: function (v) {
        return v === undefined || v === null;
    }

    , isStringVaziaNullOuUndefined: function (v) {
        return this.isNullOuUndefined(v) || v === "";
    }

    , limparSelect: function (selectSelector, opcaoDefault) {
        const select = $(selectSelector);
        select.empty();

        if (opcaoDefault) {
            select.append($("<option>").val("").text(opcaoDefault));
        } else {
            select.append($("<option>").val("").text("-- Selecione --"));
        }
    }

    , isArrayVazioNullOuUndefined: function (v) {
        if (v instanceof Array) {
            return cgu.isNullOuUndefined(v) || v.length === 0;
        }
        throw "Parâmetro informado não é um array!";
    }

    , getParametrosDaConsultaParaURL: function (prepararRequest) {
        const parametros = {};
        prepararRequest(parametros);
        //Remove filtros vazios
        for (let p in parametros) {
            if (parametros.hasOwnProperty(p)) {
                if (parametros[p] === "") {
                    delete parametros[p];
                }
            }
        }
        //Remove dados do dataTables
        delete parametros.dataTablesRequest;
        return parametros;
    }

    , getQueryStringParam: function (nome) {
        const param = (new RegExp("[?&]" + encodeURIComponent(nome) + "=([^&]*)")).exec(location.search);
        if (param) {
            return decodeURIComponent(param[1]);
        }
    }
    , exibirErro: function (mensagem) {
        toastr.error(mensagem);
        console.error(mensagem);
    }
    , exibirAlerta: function (mensagem) {
        toastr.warning(mensagem);
        console.warn(mensagem);
    }
    , exibirSucesso: function (mensagem) {
        toastr.success(mensagem);
        console.info(mensagem);
    }

    , jqueryAjaxErrorHandler: (e, xhr, settings, exception) => {
        if (xhr.status === 0) {
            cgu.exibirAlerta("Você está offline. Verifique sua conexão com a internet.");
        } else if (xhr.status === 400) {
            if (cgu.isNullOuUndefined(xhr.responseText) || xhr.responseText.match(/.*html.*/)) {
                cgu.exibirAlerta("Solicitação inválida!");
            } else {
                cgu.exibirAlerta(xhr.responseText);
            }
        } else if (xhr.status === 401) {
            cgu.exibirAlerta("Sua sessão expirou. Faça login novamente.");
        } else if (xhr.status === 403) {
            if (!cgu.isNullOuUndefined(xhr.redirectUrl)) {
                window.location.href = xhr.redirectUrl;
            } else {
                cgu.exibirErro("Você não tem permissão para realizar essa ação.");
            }
        } else if (xhr.status === 404) {
            cgu.exibirAlerta("Não encontramos nada!");
            console.error("404: " + settings.url);
        } else if (exception === "parsererror") {
            cgu.exibirAlerta("Erro no parse do JSON.");
        } else if (exception === "timeout") {
            cgu.exibirAlerta("Não foi possível completar a sua solicitação. Tempo de resposta esgotado.");
        } else {
            cgu.exibirErro("Aconteceu um erro no nosso servidor. Tente novamente.");
            console.info("Erro em solicitação ajax: ");
            console.error(exception);
        }
    }

    , aplicarMascaraMoney: function () {
        $(".money-mask").mask("000.000.000.000.000,00", {reverse: true});
    }

    , criarDatePickers: function () {
        const dia = $(".datepicker-dia");
        dia.each(function () {
            const input = $(this);
            const value = input.val();
            input.mask("99/99/9999");
            input.val(value);
        });

        dia.datepicker({
            format: "dd/mm/yyyy",
            language: "pt-BR",
            autoclose: true
        });

        const mesAno = $(".datepicker-mesano");
        mesAno.each(function () {
            const input = $(this);
            const value = input.val();
            input.mask("99/9999");
            input.val(value);
        });
        mesAno.datepicker({
            format: "mm/yyyy",
            minViewMode: "months",
            language: "pt-BR",
            autoclose: true
        });

        const ano = $(".datepicker-ano");
        ano.each(function () {
            const input = $(this);
            const value = input.val();
            input.mask("9999");
            input.val(value);
        });
        ano.datepicker({
            format: "yyyy",
            minViewMode: "years",
            language: "pt-BR",
            autoclose: true
        });
    }

    // Formata valores numa String
    // cgu.format("Substituindo -> {0}", "variavel");
    // Resultado: "Substituindo -> variavel"
    , format: function (format) {
        const args = Array.prototype.slice.call(arguments, 1);
        return format.replace(/{(\d+)}/g, function (match, number) {
            return typeof cgu.isNullOuUndefined(args[number]) ? args[number] : match;
        });
    }

    , formatarNumber: function (numero) {
        if (cgu.isNullOuUndefined(numero) || numero === "") {
            return "0,00";
        }
        return numero.toFixed(2).replace(".", ",").replace(/(\d)(?=(\d{3})+,)/g, "$1.");
    }

    , formatarInteiro: function (numero) {

        if (cgu.isNullOuUndefined(numero)) {
            return "0";
        }
        const str = numero.toString().split(".");
        if (str[0].length >= 5) {
            str[0] = str[0].replace(/(\d)(?=(\d{3})+$)/g, "$1,");
        }
        if (str[1] && str[1].length >= 5) {
            str[1] = str[1].replace(/(\d{3})/g, "$1 ");
        }
        return str.join(".").replace(new RegExp(",", "g"), ".");
    }

    , formatarPercentual: function (valor, total) {
        return ((valor / total) * 100).toFixed(2) + "%";
    }

    , cookies: {
        create: function (name, value, days) {
            let expires;
            if (cgu.isNullOuUndefined(days)) {
                const date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = "; expires=" + date.toGMTString();
            } else {
                expires = "";
            }

            document.cookie = name + "=" + value + expires + "; path=/";
        },

        read: function (name) {
            const nameEQ = name + "=";
            const ca = document.cookie.split(";");
            for (let i = 0; i < ca.length; i++) {
                let c = ca[i];
                while (c.charAt(0) === " ") {
                    c = c.substring(1, c.length);
                }
                if (c.indexOf(nameEQ) === 0) {
                    return c.substring(nameEQ.length, c.length);
                }
            }
            return null;
        },

        erase: function (name) {
            cgu.cookies.create(name, "", -1);
        }
    }

    , equalsIgnoreCase: function (str1, str2) {
        return str1.toLowerCase() === str2.toLowerCase();
    }

    // Ordena array de acordo com o parâmetro sortOrder ["asc" ou "desc"].
    // Se este parâmetro não for informado, assumirá "asc"
    , sortByKey: function (array, key, sortOrder) {
        if (cgu.isNullOuUndefined(sortOrder) || sortOrder === "asc") {
            return array.sort(function (a, b) {
                const x = a[key];
                const y = b[key];
                return ((x < y) ? -1 : ((x > y) ? 1 : 0));
            });
        } else if (sortOrder === "desc") {
            return array.sort(function (a, b) {
                const x = a[key];
                const y = b[key];
                return ((x > y) ? -1 : ((x < y) ? 1 : 0));
            });
        } else {
            throw "Tipo de ordenação inválido! Valores possíveis: ['asc' | 'desc']";
        }
    }
};

/* jslint -W121 */
Array.prototype.removeIf = function(callback) {
    let i = this.length;
    while (i--) {
        if (callback(this[i], i)) {
            this.splice(i, 1);
        }
    }
};

$.fn.serializeObject = function() {
    const o = {};
    const a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || "");
        } else {
            o[this.name] = this.value || "";
        }
    });
    return o;
};

$(document).ajaxError(cgu.jqueryAjaxErrorHandler);

toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": false,
    "progressBar": true,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "500",
    "timeOut": "10000",
    "extendedTimeOut": "10000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "slideDown",
    "hideMethod": "fadeOut"
};

function startIntroJs() {
    introJs()
        .setOptions({nextLabel:">", prevLabel:"<", skipLabel:"Fechar", doneLabel:"OK!", showStepNumbers:false})
        .start();
}

$(function () {

    //Prepara o IntroJS
    let $btnAjuda = $("#btnAjuda");
    if (cgu.cookies.read("tutorial") !== "OK" && window.location.hostname.indexOf("localhost") === -1) {
        $btnAjuda.show();
        startIntroJs();
        cgu.cookies.create("tutorial", "OK", 90);
    } else {
        $btnAjuda.removeAttr("data-intro");
        if ($("[data-intro]").size() > 0) {
            $btnAjuda.on("click",()=> startIntroJs()).show();
        }
    }

    $("button[type='reset']").on("click", function(event) {
       event.preventDefault();

       // Limpa Autocompletes
       let autoCompletes = $(".token-input-list").closest(".input-group, .form-group").find("input.form-control");
       autoCompletes.each(function(indice, elemento) {
           $(elemento).tokenInput("clear");
       });

       // Limpa Multiselects
       let multiplosSelects = $("select[multiple]");
       if(multiplosSelects.size() > 0) {
           multiplosSelects.multiselect("clearSelection");
       }

       // Limpa Outros Campos
       let form = $(this).closest("form");
       $(":input", form)
           .removeAttr("checked")
           .removeAttr("selected")
           .not(":button, :submit, :reset, :hidden, :radio, :checkbox")
           .val("");

       // Retirar foco do Autcoplete
       setTimeout(function() {
           let primeiroCampo = $(form).find("input").get(0);
           $(primeiroCampo).focus();
       }, 50);
    });

    $("#formFiltros").on("keypress", function(event) {
        if(event.keyCode === 13) {
            $("#btnFiltrar").click();
            event.preventDefault();
            event.stopPropagation();
        }
    });
});