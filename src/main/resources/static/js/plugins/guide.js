$(window).load(function() {
	guia.iniciar();
});

var guia = {
	iniciar: function() {
		/* FUNÇÃO PARA EXIBIR O CÓDIGO DO ELEMENTO */
		guia.codigo.exibir();
		/* FUNÇÃO PARA EXIBIR/ESCONDER A BARRA DE LINKS */
		guia.lista.manipular();
	},

	codigo: {
		exibir: function() {
			$('.show-code').on('click',function() {
				$(this).siblings('.syntaxhighlighter ').slideToggle(200);
			});
		}
	},

	lista: {
		manipular: function() {
			$('.page-list__control-view').on('click',function() {
				var $this = $(this),
					conteudo = $this.text();

				$this.parent().toggleClass('closed');
				$this.toggleClass('active');

				if (conteudo == '<') {
					$this.text('>');
				} else {
					$this.text('<');
				}
			});
		}
	}
}