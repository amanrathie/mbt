/* exported PesquisaPerfil */

const PesquisaPerfil = {
    configurarFiltrador: () => {}
    , configurarGrid: () => {
        const gridOptions = dataTables.defaultOptions();
        gridOptions.ajax.url = springUrl + "api/auth/perfil";
        gridOptions.filtrador = PesquisaPerfil.configurarFiltrador;
        gridOptions.columns = [
            {name: "nome", data: "nome"},
            {name: "externo", data: "externo",  sortable:false, className:"dt-center", width: "100px", render:(data)=>{
                return dataTables.checkboxRender(data, "", "disabled='disabled'");
            }},
            {name: "id", data: "id", sortable:false, className:"dt-center", width: "100px", render: (data) => {
                return `<span class='btn-group btn-group-sm'>
                            <a href='${springUrl}auth/perfil/${data}' class='btn btn-warning' data-toggle='tooltip' title='Editar'><i class='far fa-edit'></i></a>
                        </span>`;
            }}
        ];
        gridOptions.order = [[0, "asc"]];
        gridOptions.initComplete = function(settings) {
            dataTables.defaultOptions().initComplete(settings);
            $("[data-toggle='tooltip']").tooltip();
        };
        $("#lista").DataTable(gridOptions);
    }

    , filtrar: function() {
        $("#lista").dataTable().fnPageChange(0);
    }

    , init: function() {
        this.configurarGrid();
    }
};

$(function() {
    PesquisaPerfil.init();
});