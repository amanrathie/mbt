/* exported PesquisaUnidade */

const PesquisaUnidade = {
    configurarFiltrador: function(data) {
        data.nome = $("#nome").val();
        data.tipo = $("#tipo").val();
        data.codigo = $("#codigo").val();
        data.sigla = $("#sigla").val();
        data.uf = $("#uf").val();
        data.idsMunicipio = $("#idsMunicipio").val();
        data.ativo = $("#ativo").val();
    }

    , configurarGrid: function(modoPopup) {
        const gridOptions = dataTables.defaultOptions();
        gridOptions.ajax.url = springUrl + "api/auth/unidade";
        gridOptions.filtrador = this.configurarFiltrador;
        gridOptions.columns = [
            dataTables.colunaDeSelecao(modoPopup)
            , {name: "tipo", data: "tipo"}
            , {name: "nome", data: "nome"}
            , {name: "sigla", data: "sigla"}
            , {name: "codigo", data: "codigo"}
            , {name: "uf", data: "uf"}
            , {name: "municipio", data: "municipio"}
            , {
                name: "id",
                data: "id",
                visible: !(modoPopup),
                sortable: false,
                className: "dt-center",
                width: "100px",
                render: function(data, type, row) {
                    let botaoAtivarDesativar;
                    if (row.ativo) {
                        botaoAtivarDesativar = cgu.format("<button id='btnAlternar__{0}' data-id='{0}' class='btn btn-danger' " +
                            "data-toggle='tooltip' title='Inativar'><i class='fa fa-power-off'></i></button>", data);
                    } else {
                        botaoAtivarDesativar = cgu.format("<button id='btnAlternar__{0}' data-id='{0}' class='btn btn-success' " +
                            "data-toggle='tooltip' title='Ativar'><i class='fa fa-power-off'></i></button>", data);
                    }

                    return `<span class='btn-group btn-group-sm'>${botaoAtivarDesativar}&nbsp;<a href='${springUrl}auth/unidade/${data}'
                            class='btn btn-warning' data-toggle='tooltip' title='Editar'><i class='far fa-edit'></i></a></span>`;
                }
            }

        ];
        gridOptions.order = [[1, "asc"]];
        gridOptions.initComplete = function(settings) {
            dataTables.defaultOptions().initComplete(settings);
            $("[data-toggle='tooltip']").tooltip();
        };

        if(modoPopup) {
            gridOptions.rowId = "id";
            gridOptions.select = {style: "multi"};
        }

        $("#lista").DataTable(gridOptions);
    }

    , configurarBotoesAlternarAtivacao: function() {
        $("#lista").on("click", "button[id^=btnAlternar__]", function() {
            let idUnidade = $(this).data("id");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "text",
                url: springUrl + "auth/unidade/alternar-ativacao/" + idUnidade,
                success: (data) => {
                    toastr.success(data);
                    $("#lista").dataTable().api().ajax.reload(null, false);
                }
            });
        });
    }

    , init: function(modoPopup) {
        this.configurarGrid(modoPopup);
        this.configurarBotoesAlternarAtivacao();
    }
};