jQuery.extend(jQuery.validator.messages, {
    required: "Obrigatório",
    remote: "Dados inválidos",
    email: "E-mail inválido",
    url: "URL inválida",
    date: "Data inválida",
    dateISO: "Data inválida. Formato: aaaa-mm-dd",
    number: "Número inválido",
    digits: "Informe apenas dígitos",
    creditcard: "Cartão de crédito inválido",
    equalTo: "Repita o mesmo valor do campo anterior",
    accept: "Extensão inválida",
    maxlength: jQuery.validator.format("Tamanho máximo: {0} caracteres"),
    minlength: jQuery.validator.format("Tamanho mínimo: {0} caracteres"),
    rangelength: jQuery.validator.format("Tamanho deve ser entre {0} e {1} caracteres"),
    range: jQuery.validator.format("Informe um número entre {0} e {1}"),
    max: jQuery.validator.format("Valor máximo: {0}"),
    min: jQuery.validator.format("Valor mínimo: {0}")
});

const dataMinima = new Date(1901, 0, 1);
const dataMaxima = new Date(2051, 0, 1);

$.validator.methods.date = function (value, element) {
    if (value === "") {
        return true;
    }
    if (value.length !== 10) {
        return false;
    }
    let dataValida = true;
    try {
        const tokens = value.split("/");
        const mes = tokens[1] - 1;
        if (mes > 11) {
            throw "Data inválida";
        }

        const date = new Date(tokens[2], mes, tokens[0]);
        if (date <= dataMinima) {
            dataValida = false;
        }
        if (date >= dataMaxima) {
            dataValida = false;
        }
    } catch(e) {
        dataValida = false;
    }
    return dataValida;
};


jQuery.validator.addMethod("cpf", function(cpf, element) {

	cpf = cpf.replace(/[^\d]+/g, "");

	if (cpf == "")
		return true;

	// Elimina CPFs invalidos conhecidos
	if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111"
			|| cpf == "22222222222" || cpf == "33333333333"
			|| cpf == "44444444444" || cpf == "55555555555"
			|| cpf == "66666666666" || cpf == "77777777777"
			|| cpf == "88888888888" || cpf == "99999999999")
		return false;

	// Valida 1o digito
	add = 0;
	for (i = 0; i < 9; i++)
		add += parseInt(cpf.charAt(i)) * (10 - i);
	rev = 11 - (add % 11);
	if (rev == 10 || rev == 11)
		rev = 0;
	if (rev != parseInt(cpf.charAt(9)))
		return false;

	// Valida 2o digito
	add = 0;
	for (i = 0; i < 10; i++)
		add += parseInt(cpf.charAt(i)) * (11 - i);
	rev = 11 - (add % 11);
	if (rev == 10 || rev == 11)
		rev = 0;
	if (rev != parseInt(cpf.charAt(10)))
		return false;

	return true;

}, "CPF inválido");

jQuery.validator.addMethod("cnpj", function(cnpj, element) {
	cnpj = jQuery.trim(cnpj);// retira espaços em branco
	cnpj = cnpj.replace(/[^\d]+/g, '');

	if (cnpj == '')
		return true;

	if (cnpj.length != 14)
		return false;

	// Elimina CNPJs invalidos conhecidos
	if (cnpj == "00000000000000" || cnpj == "11111111111111"
			|| cnpj == "22222222222222" || cnpj == "33333333333333"
			|| cnpj == "44444444444444" || cnpj == "55555555555555"
			|| cnpj == "66666666666666" || cnpj == "77777777777777"
			|| cnpj == "88888888888888" || cnpj == "99999999999999")
		return false;

	// Valida DVs
	tamanho = cnpj.length - 2;
	numeros = cnpj.substring(0, tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(0))
		return false;

	tamanho = tamanho + 1;
	numeros = cnpj.substring(0, tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(1))
		return false;

	return true;
}, "CNPJ inválido");

jQuery.validator.addMethod("greaterThan",
function(value, element, params) {
    if (value === "") return true;
    if (!/Invalid|NaN/.test(new Date(value))) {
        return new Date(value) > new Date($(params).val());
    }
    return isNaN(value) && isNaN($(params).val())
        || (Number(value) > Number($(params).val()));
}, "Deve ser maior que a primeira data");

jQuery.validator.addMethod("dinheiro",
    function(value, element, params) {
        if (value === "") return true;

        const valorSemPontuacao = value.replace(/\./g, "").replace(/\,/g, ".");

        return !isNaN(valorSemPontuacao);
    }, "Valor inválido");

// override jquery validate plugin defaults because of bootstrap layout
$.validator.setDefaults({
    focusInvalid: false,
    highlight: function(element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function(element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    errorClass: 'help-block',
    errorPlacement: function(error, element) {
        if(element.parent().is("td") || element.parent().is(".box-simples")) {
            toastr.warning(error, element.closest(".box-simples").find("h2").text());
        } else if(element.parent('.input-group').length) {
            error.insertAfter(element.parent());
        } else {
            error.insertAfter(element);
        }
    },
    invalidHandler: function(form, validator) {
        if(validator.numberOfInvalids()) {
            $("html , body").animate({
                scrollTop: $(validator.errorList[0].element).offset().top - 100
            }, 250);
        }
    }
});

jQuery.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        return this.optional(element) || regexp.test(value);
    },
    "Formato inválido."
);

jQuery.validator.addMethod(
    "telefone",
    function(value, element) {
        return this.optional(element) || /^\([1-9]{2}\) [9]{0,1}[1-9]{1}[0-9]{3}\-[0-9]{4}$/.test(value);
    },
    "Telefone inválido."
);