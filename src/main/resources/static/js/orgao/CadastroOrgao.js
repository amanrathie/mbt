/* exported CadastroOrgao */

const CadastroOrgao = {
    configurarValidacao: function () {
        $("#form").validate({
            rules: {
                nome: {required: true, maxlength: 1000}
                , tipo: {required: true}
                , codigo: {required: true, maxlength: 20}
                , sigla: {required: true, maxlength: 20}
                , email: {maxlength: 200, email: true}
                , telefone: {maxlength: 25, telefone: true}
            }
        });

        $("#telefone").focusout(function(){
            let phone, element;
            element = $(this);
            element.unmask();
            phone = element.val().replace(/\D/g, "");
            if(phone.length > 10) {
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
            $.ajax({
                type: "POST",
                url: springUrl + "api/auth/orgao/salvar",
                data: JSON.stringify(form),
                contentType: "application/json",
                dataType: "json",
                success: function (idDoOrgaoSalvo) {
                    $("#id").val(idDoOrgaoSalvo);
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/orgao/" + idDoOrgaoSalvo;
                }
            });
        });
    }

    , configurarBotoesAlternarAtivacao: function() {
        $("#btnAtivar, #btnInativar").on("click", function() {
            let idOrgao = $(this).data("id");
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "text",
                url: springUrl + "auth/orgao/alternar-ativacao/" + idOrgao,
                success: () => {
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/orgao/" + idOrgao;
                }
            });
        });
    }

    , init: function() {
        this.configurarValidacao();
        this.configurarSubmit();
        this.configurarBotoesAlternarAtivacao();
    }
};

$(function() {
    CadastroOrgao.init();
});