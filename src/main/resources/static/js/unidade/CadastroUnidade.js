/* exported CadastroUnidade */
const CadastroUnidade = {
    configurarValidacao: function () {
        $("#form").validate({
            rules: {
                nome: {required: true, maxlength: 1000}
                , tipo: {required: true}
                , codigo: {required: true, maxlength: 20}
                , idOrgao: {required: true}
                , sigla: {required: true, maxlength: 20}
                , email: {maxlength: 200, email: true}
                , telefone: {maxlength: 25, telefone: true}
            }
        });

        $("#telefone").focusout(function () {
            let phone, element;
            element = $(this);
            element.unmask();
            phone = element.val().replace(/\D/g, "");
            if (phone.length > 10) {
                element.mask("(99) 99999-9999");
            } else {
                element.mask("(99) 9999-99999");
            }
        }).trigger("focusout");
    }

    , configurarSubmit: function () {
        $("#btnSalvar").click(function () {
            const $form = $("#form");
            if (!$form.valid()) {
                return;
            }

            const form = $form.serializeObject();

            if (form.gestores != null) {
                form.gestores = JSON.parse("[" + form.gestores + "]");
            }

            $.ajax({
                type: "POST",
                url: springUrl + "api/auth/unidade/salvar",
                data: JSON.stringify(form),
                contentType: "application/json",
                dataType: "json",
                success: function (idDaEntidadeSalva) {
                    $("#id").val(idDaEntidadeSalva);
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/unidade/" + idDaEntidadeSalva;
                }
            });
        });
    }

    , configurarBotoesAlternarAtivacao: function() {
        $("#btnAtivar, #btnInativar").on("click", function() {
            let idUnidade = $(this).data("id");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "text",
                url: springUrl + "auth/unidade/alternar-ativacao/" + idUnidade,
                success: () => {
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/unidade/" + idUnidade;
                }
            });
        });
    }

    , init: function () {
        this.configurarValidacao();
        this.configurarSubmit();
        this.configurarBotoesAlternarAtivacao();
    }
};

$(function(){
    CadastroUnidade.init();
});
