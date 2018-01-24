const Multiselect = {};

Multiselect.optionsPadrao = {
      enableFiltering: true
    , filterPlaceholder: "Digite para começar a filtrar"
    , nonSelectedText: "Nenhum selecionado"
    , nSelectedText: " selecionados"
    , allSelectedText: "Todos selecionados"
    , maxHeight: 300
    , buttonWidth: "100%"
    , buttonClass: "btn"
    , enableCaseInsensitiveFiltering: true
    , templates : {
        filter : `<li class="multiselect-item filter"><input class="form-control multiselect-search" type="text"></li>`,
        filterClearBtn: `<span class="input-group-btn"><button class="btn btn-default multiselect-clear-filter" type="button">
                        <i class="fas fa-eraser"></i></button></span>`

    }
};

Multiselect.criarMultiselects = function () {
    $("select[multiple]").each(function () {
        $("#" + this.id).multiselect(Multiselect.optionsPadrao);
        $("#" + this.id).siblings().filter(".btn-group").attr('id',   this.id + "_btn-group");
    });
};

