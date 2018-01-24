const Autocomplete = {};

Autocomplete.optionsPadrao = {
      searchDelay: 1000
    , minChars: 3
    , hintText: "Comece a digitar para pesquisar"
    , noResultsText: "Nenhum resultado encontrado"
    , searchingText: "Carregando..."
    , preventDuplicates: true
    , buscaAvancada: false
};

Autocomplete.initAutocomplete = function (input, options) {
    Autocomplete.prepararBuscaAvancada(input, options);

    let url = input.data("autocomplete-url");
    if (cgu.isNullOuUndefined(url) || url == "") {
        url = springUrl + input.data("autocomplete-tipo") + "/autocomplete";
    } else {
        url = springUrl + url;
    }

    const idsSelecionados = input.val();

    if (idsSelecionados === "") {
        input.tokenInput(url, options);
        return;
    } else {
        if (idsSelecionados.split(",").length > 10) {
            cgu.exibirAlerta("Máximo 10 parâmetros do tipo " + input.data("autocomplete-tipo"));
            input.val("");
            return;
        }
    }

    $.get(url + "/ids", {ids: idsSelecionados}, function (data) {
        options.prePopulate = data;
        input.tokenInput(url, options);
    });
};

Autocomplete.criarAutocomplete = function(input) {
    let options = {};
    $.extend(options, Autocomplete.optionsPadrao);

    let onSelect = input.data("autocomplete-on-select");
    if (!cgu.isNullOuUndefined(onSelect) && onSelect !== "") {
        /* jshint -W061*/
        let callback = eval(onSelect);
        if (typeof callback === "function") {
            options.onAdd = callback;
        } else {
            console.error("Callback do autocomplete inválido!");
        }
    }

    let onDelete = input.data("autocomplete-on-delete");
    if (!cgu.isNullOuUndefined(onDelete) && onDelete !== "") {
        /* jshint -W061*/
        let callback = eval(onDelete);
        if (typeof callback === "function") {
            options.onDelete = callback;
        } else {
            console.error("Callback do autocomplete inválido!");
        }
    }

    options.tokenLimit = input.data("autocomplete-maximo-itens");
    options.placeholder = input.attr("placeholder");
    options.buscaAvancada = input.data("busca-avancada");
    options.allowFreeTagging = input.data("autocomplete-permite-criar-valores");

    Autocomplete.initAutocomplete(input, options);
};

Autocomplete.criarAutocompletes = function () {
    $("input[data-autocomplete-tipo]").each(function () {
        Autocomplete.criarAutocomplete($(this));
    });
};

Autocomplete.prepararBuscaAvancada = function (input, options) {
    if (options.buscaAvancada === "") {
        return;
    }

    input.parent().find(".input-group-btn").click(function(){
        const windowName = "popUpBuscaAvancada" + Math.random().toString(36).substring(5);

       window.open(springUrl + "popup/" + options.buscaAvancada, windowName, "height=700,width=1024,location=no,menubar=no,resizable=yes,status=no,toolbar=no");
       window["callback" + windowName] = function(idsSelecionados){
           const url = springUrl + input.data("autocomplete-tipo") + "/autocomplete";
           $.get(url + "/ids", {ids:idsSelecionados.join(",")}, function (data) {
               $.each(data, function(i, v) {
                   console.log(v);
                   input.tokenInput("add", v);
               });
           });
       };
    });
};

window.addEventListener("message", function(event){
    window["callback" + event.data.windowName](event.data.idsSelecionados);
}, false);