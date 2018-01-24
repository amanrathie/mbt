/* exported CadastroUsuario */

const CadastroUsuario = {
    configurarValidacao: function () {
        //Validação
        $("#form").validate({
            rules: {
                nome: {required: true, maxlength: 1000}
                , cpf: {required: true, maxlength: 14, cpf: true}
                , siape: {required: false, maxlength: 10}
                , login: {required: true, maxlength: 500}
                , email: {required: false, maxlength: 200, email: true}
                , telefone: {required: false, maxlength: 25, telefone: true}
            }
        });
    },

    configurarMascaras: function () {
        $("#cpf").focusout(function () {
            let element;
            element = $(this);
            element.unmask();
            element.mask("999.999.999-99");
        }).trigger("focusout");
    },

    configurarSubmit: function () {
        $("#btnSalvar").click(function () {
            const $form = $("#form");
            if (!$form.valid()) {
                return;
            }

            const form = $form.serializeObject();

            if (!cgu.isNullOuUndefined(form.perfis)) {
                form.perfis = JSON.parse("[" + form.perfis + "]");
            }

            if (!cgu.isNullOuUndefined(form.unidades)) {
                form.unidades = JSON.parse("[" + form.unidades + "]");
            }

            $.ajax({
                type: "POST",
                url: springUrl + "api/auth/usuario/salvar",
                data: JSON.stringify(form),
                contentType: "application/json",
                dataType: "json",
                success: function (id) {
                    $("#id").val(id);
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/usuario/" + id;
                }
            });
        });
    },

    configurarBotoesAlternarAtivacao: function() {
        $("#btnAtivar, #btnDesativar").on("click", function() {
            let idUsuario = $(this).data("id");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "text",
                url: springUrl + "auth/usuario/alternar-ativacao/" + idUsuario,
                success: () => {
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/usuario/" + idUsuario;
                }
            });
        });
    },

    init: function () {
        this.configurarValidacao();
        this.configurarMascaras();
        this.configurarSubmit();
        this.configurarBotoesAlternarAtivacao()
    }
};

$(function(){
    CadastroUsuario.init();
});
