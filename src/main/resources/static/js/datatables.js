const dataTables = window.dataTables || {};

dataTables.prepararRequest = function(data) {
    data.colunaOrdenacao = data.columns[data.order[0].column].name;
    data.direcaoOrdenacao = data.order[0].dir.toUpperCase();
    data.tamanhoPagina = data.length;
    data.offset = data.start;

    delete data.length;
    delete data.start;
    delete data.columns;
    delete data.order;
    delete data.search;
    delete data.draw;
};

dataTables.bindPesquisaDatatables = function() {
    $("[data-datatables-refresh]").click(function () {
        dataTables.click($(this).data("datatables-refresh"));
    });
};

dataTables.click = function(id) {
    $(id).dataTable().fnPageChange(0);
    const queryString = $.param($(id).DataTable().ajax.params());
    window.history.pushState(queryString, window.title, window.location.href.split("?")[0] + "?" + queryString);
    window.location.href = id;
};

/* Function necessária pra que a ordenação do Datatables clientside funcione para colunas com input's.
 Adicionar orderDataType: "dom-input"  na definição da column.
 */
$.fn.dataTable.ext.order["data-src"] = function () {
    const that = this;
    return this.api().data().map(function (v) { return v[that.api().column().dataSrc()]; });
};

dataTables.defaultOptions = function () {
    const options = {
        "searching": false,
        "lengthChange": false,
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Página _PAGE_ de _PAGES_",
            "sInfoEmpty": "",
            "sInfoFiltered": "",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "Exibir _MENU_",
            "sLoadingRecords": `Carregando... 
                        <div class="sk-cube-grid">
                           <div class="sk-cube sk-cube1"></div>
                           <div class="sk-cube sk-cube2"></div>
                           <div class="sk-cube sk-cube3"></div>
                           <div class="sk-cube sk-cube4"></div>
                           <div class="sk-cube sk-cube5"></div>
                           <div class="sk-cube sk-cube6"></div>
                           <div class="sk-cube sk-cube7"></div>
                           <div class="sk-cube sk-cube8"></div>
                           <div class="sk-cube sk-cube9"></div>
                        </div>`,
            "sProcessing": `Carregando... 
                        <div class="sk-cube-grid">
                           <div class="sk-cube sk-cube1"></div>
                           <div class="sk-cube sk-cube2"></div>
                           <div class="sk-cube sk-cube3"></div>
                           <div class="sk-cube sk-cube4"></div>
                           <div class="sk-cube sk-cube5"></div>
                           <div class="sk-cube sk-cube6"></div>
                           <div class="sk-cube sk-cube7"></div>
                           <div class="sk-cube sk-cube8"></div>
                           <div class="sk-cube sk-cube9"></div>
                        </div>`,
            "sZeroRecords": "Nenhum registro encontrado",
            "oPaginate": {
                "sNext": "Próxima <i class=\"fa fa-fw fa-chevron-right\"></i> ",
                "sPrevious": "<i class=\"fa fa-fw fa-chevron-left\"></i> Anterior</a>",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            },
            select: {rows: {0: "", _: "%d itens selecionados", 1: "1 item selecionado"}}
        },
        "serverSide": true,
        processing: true,
        "orderMulti": false,
        "lengthMenu": [[15, 30, 50], ["15 resultados", "30 resultados", "50 restultados"]],
        "deferRender": true,
        autoWidth: true,
        dom: "tr<'row'<'col-sm-4'i><'col-sm-8'p>>",
        filtrador: function () {
        },
        initComplete: function () {
            dataTables.bindPesquisaDatatables();
        }
    };
    options.ajax = {
        data: function(data) {
            dataTables.prepararRequest(data);
            options.filtrador(data);
        }
    };
    
    return options;
};

dataTables.eventoSelectAll = function (idDaTabela, element) {
    const selecionado = $(element).hasClass("fa-check-square");
    if (selecionado) {
        $(element).removeClass("fa-check-square");
        $("#" + idDaTabela).DataTable().rows().deselect();
        $(element).addClass("fa-square");
    } else {
        $(element).removeClass("fa-square");
        $("#" + idDaTabela).DataTable().rows().select();
        $(element).addClass("fa-check-square");
    }
};

dataTables.defaultOptionsSemAjax = function () {
    const options = dataTables.defaultOptions();
    options.serverSide = false; //Não queremos ajax nessa tabela
    delete options.ajax;
    delete options.initComplete; //faz nada. Não queremos que esse datatables atualize a url.
    options.data = [];
    options.order = [[0, "asc"]];
    options.paging = false;
    options.dom = "<'row'<'col-sm-12'l><'col-sm-12'f>><'row'<'col-sm-12'tr>>";
    return options;
};


//renderer recebe (data, type, row, meta )
dataTables.dateRenderer = function (data) {
    if (cgu.isNullOuUndefined(data)) {
        return "";
    }
    const date = Date.parseExact(data[2] + "/" + data[1] + "/" + data[0], "d/M/yyyy");
    return date.toString("dd/MM/yyyy");
};

dataTables.dateTimeRenderer = function (data) {
    if (cgu.isNullOuUndefined(data)) {
        return "";
    }
    const date = Date.parseExact(data[2] + "/" + data[1] + "/" + data[0] + " " + data[3] + ":" + data[4] + ":" + data[5], "d/M/yyyy H:m:s");
    return date.toString("dd/MM/yyyy HH:mm:ss");
};

dataTables.timeRenderer = function (data) {
    if (cgu.isNullOuUndefined(data)) {
        return "";
    }
    const time = Date.parse(data.hourOfDay + ":" + data.minuteOfHour + ":" + data.secondOfMinute);
    return time.toString("HH:mm:ss");
};

dataTables.linkRenderer = function (link, descricao) {
     return "<a href='" + link + "'>" + descricao + "</a>";
};

dataTables.codigoEDescricaoRenderer = function (codigo, descricao) {
    if (codigo[0] === "-") {
        return descricao;
    }
    return codigo + " - " + descricao;
};

dataTables.valorComCifraoRender = function(data) {
    if (cgu.isNullOuUndefined(data) || data === "") {
        return "";
    }
    return "R$ " + cgu.formatarNumber(data);
};


dataTables.checkboxRender = function(data, onChange, atributos) {
    atributos = atributos || "";
    onChange = onChange || "";
    return `<span class="checkbox c-checkbox needsclick c-checkbox-no-font">
                <label class="needsclick">
                    <input class="needsclick" type="checkbox" ${data ? "checked='checked'" : ""} ${atributos} onchange="${onChange}">
                    <span class="glyphicon glyphicon-ok"></span>
                </label>                                
            </span>`;
};

dataTables.colunaDeSelecao = function(modoPopup) {
    return {name:"selecionar", sortable:false, visible:modoPopup, className:"select-checkbox dt-center", render:function(){return "";}};
};

dataTables.usarSelecionados = function(idDataTables){
    const idsSelecionados = $("#" + idDataTables).DataTable().rows({selected: true}).data().toArray().map(function (v) {
        return v.id;
    });
    window.opener.postMessage({windowName: window.name, idsSelecionados : idsSelecionados}, "*");
    window.close();
};

dataTables.marcacaoInstanciaFavoritaRender = function(data, type, row){
    return cgu.format(
        "<span id='alternar-favorita-{1}' title='Clique para {3}marcar como favorita' class='favorito' data-id-instancia='{1}' data-favorita='{2}' " +
        "data-toggle='tooltip' data-placement='right'><i aria-hidden='true' class='fa{4} fa-star fa-lg'></i></span>",
        springUrl, row.id, row.favorita, row.favorita ? "des" : "", row.favorita ? "s" : "r");
};

dataTables.idInstanciaComLinkRender = function(data, type, row){
    return cgu.format("<a href='{0}auth/instancia/{1}' title='Clique para ver os detalhes' data-toggle='tooltip' data-placement='right'><span class='label label-id'>#{1}</span></a>", springUrl, row.id);
};

dataTables.situacaoRender = function(data){
    return "<span class='label label-situacao'>" + data + "</span>";
};

dataTables.classeRender = function(data){
    return "<span class='label label-classe'>" + data + "</span>";
};

dataTables.prepararBtnSalvarFiltro = function() {
    let $btnSalvarFiltro = $("#btnSalvarFiltro");
    $btnSalvarFiltro.hide();
    $btnSalvarFiltro.click(function () {
        if (window.sidebar && window.sidebar.addPanel) {
            window.sidebar.addPanel(document.title, window.location.href, '');
        } else if (window.external && ('AddFavorite' in window.external)) {
            window.external.AddFavorite(location.href, document.title);
        } else if (window.opera && window.print) {
            this.title = document.title;
            return true;
        } else {
            alert("Pressione " + (navigator.userAgent.toLowerCase().indexOf('mac') !== -1 ? "Command/Cmd" : "CTRL") + " + D para adicionar aos favoritos.");
        }
    });
};