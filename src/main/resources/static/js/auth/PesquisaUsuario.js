/* exported PesquisaUsuario */

const PesquisaUsuario = {
    configurarFiltrador: function(data) {
        data.nome = $("#nome").val();
        data.cpf = $("#cpf").val();
        data.siape = $("#siape").val();
        data.login = $("#login").val();
        data.ativo = $("#ativo").val();
        data.unidades = $("#unidade").val();
    }

    , configurarGrid: function() {
        const gridOptions = dataTables.defaultOptions();
        gridOptions.ajax.url = springUrl + "api/auth/usuarios";
        gridOptions.filtrador = this.configurarFiltrador;
        gridOptions.columns = [
            {name: "cpf", data: "cpf"},
            {name: "nome", data: "nome"},
            {name: "login", data: "login"},
            {name: "id", data: "id", sortable:false, className:"dt-center", width: "100px", render: function(data, type, row) {
                let botaoAtivarDesativar;
                if (row.ativo) {
                    botaoAtivarDesativar = cgu.format("<button id='btnAlternar__{0}' data-id='{0}' class='btn btn-danger' " +
                        "data-toggle='tooltip' title='Inativar'><i class='fa fa-power-off'></i></button>", data);
                } else {
                    botaoAtivarDesativar = cgu.format("<button id='btnAlternar__{0}' data-id='{0}' class='btn btn-success' " +
                        "data-toggle='tooltip' title='Ativar'><i class='fa fa-power-off'></i></button>", data);
                }

                return cgu.format("<span class='btn-group btn-group-sm'>{2}&nbsp;<a href='{0}auth/usuario/{1}' class='btn btn-warning' data-toggle='tooltip' title='Editar'>" +
                    "<i class='far fa-edit'></i></a></span>", springUrl, data, botaoAtivarDesativar);
            }}
        ];
        gridOptions.order = [[0, "asc"]];
        gridOptions.initComplete = function(settings) {
            dataTables.defaultOptions().initComplete(settings);
            $("[data-toggle='tooltip']").tooltip();
        };
        $("#lista").DataTable(gridOptions);
    }

    , definirFocoInicial: function() {
        $("#cpf").focusout(function(){
            let element;
            element = $(this);
            element.unmask();
            element.mask("999.999.999-99");
        }).trigger("focusout");
    }

    , filtrar: function() {
        $("#lista").dataTable().fnPageChange(0);
    }

    , configurarBotoesAlternarAtivacao: function() {
        $("#lista").on("click", "button[id^=btnAlternar__]", function() {
            let idUsuario = $(this).data("id");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "text",
                url: springUrl + "auth/usuario/alternar-ativacao/" + idUsuario,
                success: (data) => {
                    toastr.success(data);
                    $("#lista").dataTable().api().ajax.reload(null, false);
                }
            });
        });
    }

    , init: function() {
        this.configurarGrid();
        this.definirFocoInicial();
        this.configurarBotoesAlternarAtivacao();
    }
};

$(function() {
    PesquisaUsuario.init();
});