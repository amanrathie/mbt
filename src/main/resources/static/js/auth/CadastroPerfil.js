/* exported CadastroPerfil */

const CadastroPerfil = {
    configurarValidacao: function () {
        //Validação
        $("#form").validate({
            rules: {
                nome: {required: true, maxlength: 1000}
            }
        });
    },

    configurarSubmit: function () {
        $("#btnSalvar").click(function () {
            const $form = $("#form");
            if (!$form.valid()) {
                return;
            }

            const form = $form.serializeObject();
            $.ajax({
                type: "POST",
                url: springUrl + "api/auth/perfil/salvar",
                data: JSON.stringify(form),
                contentType: "application/json",
                dataType: "json",
                success: function (id) {
                    $("#id").val(id);
                    cgu.cookies.create("showSuccessMessage", "Operação realizada com sucesso.", 1);
                    window.location.href = springUrl + "auth/perfil/" + id;
                }
            });
        });
    },

    init: function () {
        this.configurarValidacao();
        this.configurarSubmit();
    }
};

$(function(){
    CadastroPerfil.init();
});
