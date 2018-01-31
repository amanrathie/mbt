$(window).load(function() {
	base.init();
});

var base = {
	init: function() {
		base.form.show();
		base.menu.show();
		base.profile.show();
		base.radioCheckbox.change();
		base.radioCheckbox.verify();
		base.blockLogin.change();
		base.modal.close();
		base.modal.open('.button-login','.modal-login');
		base.tabs.noObstrusive();
		base.tabs.graphicSolution();
		base.tooltip.iniciar();
		base.abas.trocarAtivo();
		base.print.iniciar();
	},

	/**
	 * Aumenta/Diminui o formulário de busca do topo da página
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */

	 form: {
	 	show: function() {
	 		$('.form-searh-top').on('click touchstart',function(e) {
	 			$(this).parents('.navigation-bar__form-search').removeClass('compact');
	 			e.stopPropagation();
	 		});

	 		$('body').on('click touchstart', function() {
	 			$('.navigation-bar__form-search').addClass('compact');
	 		});
	 	},

	 	validar: function() {
	 		$.validate({
	 			modules : 'security, brazil'
	 		});
	 	}
	 },

	/**
	 * Exibe o menu mobile ao clique
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */

	 menu: {
	 	show: function() {
			$('.navigation-menu').find('button').on('click',function() {
				$(this)
					.parent('.has-submenu')
					.toggleClass('active')
					.siblings('.has-submenu')
					.removeClass('active')
					.find('.has-submenu')
					.removeClass('active');
			});

			$('.navigation-bar__button').on('click touchstart',function() {
				$(this).siblings('.navigation-menu').addClass('active');
			});

			$('body').on('click touchstart', function() {
				$('.navigation-menu')
					.removeClass('active')
					.find('.has-submenu')
					.removeClass('active');
			});

			$('.navigation-menu,.navigation-bar__button').on('click touchstart',function(e) {
				e.stopPropagation();
			});

			// $('.navigation-menu').find('.has-submenu').find('a').on('click touchstart',function() {
			// 	$(this).parent('li').toggleClass('active');

			// 	return false;
			// });
		}
	 },

	/**
	 * Exibe o perfil ao clique
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */

	 profile: {
	 	show: function() {
	 		$('.block-my-profile').find('button').on('click touchstart',function(e) {
	 			$('.modal-my-profile').addClass('active');
	 			e.stopPropagation();
	 		});
	 	}
	 },

	/**
	 * Muda o status do radio button/checkbox quando ativados
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */

	 radioCheckbox: {
	 	change: function() {
	 		$('input[type=checkbox]').on('change',function() {
	 			$this = $(this);
	 			if ($this.is(':checked')) {
	 				$this.siblings('label').addClass('active');
	 				$this.parent('label').addClass('active');
	 			} else {
	 				$this.siblings('label').removeClass('active');
	 				$this.parent('label').removeClass('active');
	 			}
	 		});
	 		$('input[type=radio]').on('change',function() {
	 			$this = $(this);
	 			if ($this.is(':checked')) {
	 				$('input[name="' + $this.attr('name') + '"]').siblings('label').removeClass('active');
	 				$('input[name="' + $this.attr('name') + '"]').parent('label').removeClass('active');
	 				$this.siblings('label').addClass('active');
	 				$this.parent('label').addClass('active');
	 			}
	 		});
	 	},

		/**
		 * Verifica quais radio buttons/checkboxes estão ativados ao abrir a página
		 *
		 * @return void
		 * @author Alvino Rodrigues
		 */
		 verify: function() {
		 	$('input[type=checkbox],input[type=radio]').each(function() {
		 		$this = $(this);
		 		if ($this.is(':checked')) {
		 			$this.siblings('label').addClass('active');
		 			$this.parent('label').addClass('active');
		 		}
		 	});
		 }
		},

	/**
	 * Alterna entre os 3 passos da modal de login
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */
	 blockLogin: {
	 	change: function() {
	 		var $step = $('.step');

	 		$('.login-forgot').on('click touchstart',function() {
	 			$step.removeClass('active');
	 			$('.step-2').addClass('active');
	 		});
	 		$('.login-back').on('click touchstart',function() {
	 			$step.removeClass('active');
	 			$('.step-1').addClass('active');
	 		});
	 		$('.form-email').on('submit',function() {
	 			$step.removeClass('active');
	 			$('.step-3').addClass('active');
	 			return false;
	 		});
	 	}
	 },

	/**
	 * Fecha a modal
	 *
	 * @return void
	 * @author Alvino Rodrigues
	 */
	 modal: {

	 	close: function() {
	 		$('.button-close').on('click touchstart',function() {
	 			$(this).parents('.modal-block').removeClass('active');
	 			$('body').removeClass('modal-background');
	 		});
	 	},

		/**
		 * Abre a modal de login
		 * Parâmetros:
		 * - Botão que irá abrir a modal
		 * - Modal que será aberta
		 *
		 * @return void
		 * @author Alvino Rodrigues
		 */
		 open: function(button,modal) {
		 	$(button).on('click touchstart',function(e) {
		 		$(modal).addClass('active');
		 		$('body').addClass('modal-background');

		 		e.stopPropagation();
		 	});

		 	$('.modal-block').on('click touchstart', function(e) {
		 		e.stopPropagation();
		 	});

			//refazer a bind de tabs do bootstrap ao exibir o modal
			$('.modal-block [data-toggle="tab"]').on('click', function(e){
				e.preventDefault();
				$(this).tab("show");

			});
		}
	},

	grafico: {
		selecionar: function() {
			var $elemento = $('[data-chart]'),
			$this;

			if ($elemento.length > 0) {
				$elemento.each(function() {
					$this = $(this),
					d3this = d3.select(this);

					switch ($this.data('chart')) {
						case 'donut':
						base.grafico.construirDonut($this,d3this);
						break;
						case 'barras-horizontais-empilhadas':
						base.grafico.construirBarrasHorizontaisEmpilhadas($this,d3this);
						break;
						case 'progress-donut':
						base.grafico.construirBarraProgressoDonut($this,d3this);
						break;
					}
				});
			}
		},

		construirDonut: function($elemento,d3elemento) {
			var tabela = JSON.parse($elemento.attr('data-table-json')),
			lista = [],
			altura = 400;

			// Dados
			for (i = 0; i < tabela.length; i++) {
				lista[i] = {
					'label': base.label.formatar(tabela[i].label),
					'value': base.moeda.converterParaFloat(tabela[i].valor),
					'json': tabela[i],
					'tooltip': tabela[i].tooltip,
					'paleta': tabela[i].paleta
				};
			}

			// Localizando o idioma/moeda/data/etc para o Português BR
			var localizacaoBR = d3.locale({
				"decimal": ",",
				"thousands": ".",
				"grouping": [3],
				"currency": ["R$", ""],
				"dateTime": "%d/%m/%Y %H:%M:%S",
				"date": "%d/%m/%Y",
				"time": "%H:%M:%S",
				"periods": ["AM", "PM"],
				"days": ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
				"shortDays": ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
				"months": ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
				"shortMonths": ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"]
			}),
			pt_real = localizacaoBR.numberFormat("$,.2f"),
			pt = localizacaoBR.numberFormat(",");

			// Dados Gerais
			var margin = {
				top: 50,
				right: 20,
				bottom: 50,
				left: 150
			},
			width = parseInt(d3elemento.style('width'), 10),
			width = width - margin.right,
			height = altura,
			radius = height / 2,
			donutWidth = 75;

			// Insere o SVG no DOM
			if( base.crossBrowser.detectIE() ){
				var svgContainer = d3elemento
				.append("svg")
				.attr('height', height)
				.attr('width', '100%')
				.attr('viewBox', '-30 -30 460 460')
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			}else{
				var svgContainer = d3elemento
				.append("svg")
				.attr('preserveAspectRatio', 'xMinYMin meet')
				.attr('viewBox', '-30 -30 460 460')
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			}

			// Insere os valores e cria a circunferência
			var vis = d3elemento.select('svg')
			.data([lista])
			.append("svg:g")
			.attr("transform", "translate(" + radius + "," + radius + ")"),
			pie = d3.layout.pie().value(function(d) {
				return d.value;
			}),
			arc = d3.svg.arc().innerRadius(radius - donutWidth).outerRadius(radius),
			arcs = vis.selectAll("g.slice")
			.data(pie)
			.enter()
			.append("svg:g")
			.attr("class", "slice")
			.attr('stroke', '#fff')
			.attr('stroke-width', 1);

			// Insere as fatias
			arcs.append("svg:path")
			.attr("fill", function(d, i) {
				var cor = base.cor.selecionar(i, d.data.paleta);

				$elemento
				.parents('.block-rate__block')
				.find('.block-rate__list')
				.append('<li><span><i class="fa fa-circle" aria-hidden="true" style="color: ' + cor + ' !important;"></i> <strong>' + d.value + '</strong> ' + d.data.label + '</span></li>');

				return cor;
			})
				// Exibe o tooltip ao passar o mouse nas barras
				.on("mouseover", function(valor) {
					if (typeof(valor.data.tooltip) != 'undefined') {
						d3.select('body')
						.append('span')
						.attr('class', 'label-tooltip')
						.html(valor.data.tooltip);
					}

					base.tooltip.posicionar();
				})
				// Remove o tooltip ao retirar o mouse das barras
				.on("mouseout", function(valor) {
					d3.select('.label-tooltip').remove();
				})
				// Animação inicial
				.transition()
				.duration(1000)
				.attrTween('d', function(finish) {
					var start = {
						startAngle: 0,
						endAngle: 0
					};
					var i = d3.interpolate(start, finish);
					return function(d) {
						return arc(i(d));
					};
				});

			// Insere os textos
			arcs.append("svg:text")
			.attr('fill', '#fff')
			.attr('stroke', 'none')
			.attr('font-size', '30')
			.attr("transform", function(d) {
				d.innerRadius = 0;
				d.outerRadius = radius;
				return "translate(" + arc.centroid(d) + ")";
			}).attr("text-anchor", "middle").text(function(d, i) {
				// Calcula a porcentagem a ser exibida nas fatias
				var total = 0,
				porcentagem = 0;

				for (var inc = 0; inc < lista.length; inc++) {
					total = total + lista[inc].value;
				}

				porcentagem = parseFloat((lista[i].value * 100) / total).toFixed(0);

				return porcentagem + '%';
			});

			// Remove o tooltip na rolagem (Solução para mobile)
			d3.select(window).on('scroll', function() {
				d3.select('.label-tooltip').remove();
			});
		},

		construirBarrasHorizontaisEmpilhadas: function($elemento,d3elemento) {
			var lista = [],
			tabela = JSON.parse($elemento.attr('data-table-json')),
			alturaBarra = 32,
			larguraSVG = $elemento.parent().width();

			// Localizando o idioma/moeda/data/etc para o Português BR
			var localizacaoBR = d3.locale({
				"decimal": ",",
				"thousands": ".",
				"grouping": [3],
				"currency": ["R$", ""],
				"dateTime": "%d/%m/%Y %H:%M:%S",
				"date": "%d/%m/%Y",
				"time": "%H:%M:%S",
				"periods": ["AM", "PM"],
				"days": ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
				"shortDays": ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
				"months": ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
				"shortMonths": ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"]
			}),
			pt_real = localizacaoBR.numberFormat("$,.2f"),
			pt = localizacaoBR.numberFormat(",");

			var margin = {
				top: 0,
				right: 0,
				bottom: 0,
				left: 0,
				bars: 0
			};

			// lista de itens agrupados
			tabela = tabela.map(function(d) {
				return d.data.map(function(o, i) {
					return {
						y: o.valor,
						titulo: o.titulo,
						label: d.label,
						tooltip: o.tooltip,
						json: d
					};
				});
			});

			// transforma tabela em stack
			var stack = d3.layout.stack();
			stack(tabela);

			tabela = tabela.map(function(group, j) {
				return group.map(function(d, i) {
					return {
						titulo: d.titulo,
						x: d.y,
						x0: d.y0,
						label: d.label,
						tooltip: d.tooltip,
						json: d.json
					};
				});
			});

			var titulos = tabela[0].map(function(d) {
				return d.titulo;
			});

			var numeroBarras = titulos.length,
			alturaSVG = ((alturaBarra + margin.bars) * (numeroBarras) + margin.bars);


			// Define opções gerais
			var width = parseInt(d3elemento.style('width'), 10),
			height = alturaSVG;

			if (isNaN(width)) {
				width = $elemento.data('chart-width');
			}

			width = width - margin.left - margin.right;

			// Insere o SVG no DOM
			var svgContainer = d3elemento
			.append("svg")
			.attr("width", width + margin.left + margin.right)
			.attr("height", height + margin.top + margin.bottom)
			.append("g")
			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			//Confere o maior valor entre as fatias
			var valorMaximoLista = d3.max(tabela, function(group) {
				return d3.max(group, function(d) {
					return d.x + d.x0;
				});
			});
			var valorMaximoBarras = d3.max(lista, function(d) {
				return d.value;
			}),
			valorMaximoTotal = valorMaximoLista,
			porcentagem = (parseInt(valorMaximoLista, 10) * 10) / 100,
			valorMaximoPorcentagem = parseInt(valorMaximoLista, 10) + porcentagem,
			xScale = d3.scale.linear().range([0, width], .1).domain([0, valorMaximoPorcentagem]);

			var xScale = d3.scale.linear()
			.range([0, width])
			.domain([0, valorMaximoPorcentagem]);
			var yScale = d3.scale.linear().range([0, alturaSVG]),
			yScaleLabel = d3.scale.ordinal()
			.domain(titulos)
			.rangeRoundBands([0, height], .1),
			xAxis = d3.svg
			.axis()
			.scale(xScale)
			.tickFormat(function(d) {
				if ((d / 1000000000000000) >= 1) {
					d = (d / 1000000000000000) + ' qua';
				} else if ((d / 1000000000000) >= 1) {
					d = (d / 1000000000000) + ' tri';
				} else if ((d / 1000000000) >= 1) {
					d = (d / 1000000000) + ' bi';
				} else if ((d / 1000000) >= 1) {
					d = (d / 1000000) + ' mi';
				} else if (d == 0) {
					d = '0';
				}
				return d;
			})
			.ticks(3)
			.orient("bottom"),
			yAxis = d3.svg
			.axis()
			.scale(yScale)
			.orient("left");

			// Insere as barras
			var groups = svgContainer.selectAll('.fatia')
			.data(tabela)
			.enter()
			.append('g')
			.attr("class", "fatia")
			.style("fill", function(d, i) {
				var cor = base.cor.selecionar(i, d[0].json.paleta);

				if (typeof d[0] != "undefined" && typeof d[0].label != "undefined") {
					$elemento
					.parents('.block-rate__block')
					.find('.block-rate__list')
					.append('<li><span><i class="fa fa-circle" aria-hidden="true" style="color: ' + cor + ' !important;"></i> <strong>' + d[0].x + '</strong> ' + d[0].label + '</span></li>');
				}
				return cor;
			})
			.attr("data-json", function(d, i) {
				return JSON.stringify(d.json);
			})
			.classed("maximo", function(d) {
				if (valorMaximoLista == d.value) {
					return true;
				} else {
					return false;
				}
			});

			var rects = groups.selectAll('.pedaco')
			.data(function(d) {
				return d;
			})
			.enter()
			.append('rect')
			.attr("class", "pedaco")
			.attr('x', function(d) {
				return xScale(d.x0);
			})
			.attr("data-json", function(d, i) {
				return JSON.stringify(d.json);
			})
			.attr('y', function(d, i) {
				return (i * (alturaBarra + margin.bars) + margin.bars);
			})
			.attr('height', function(d) {
				return alturaBarra;
			})
			.transition()
			.delay(function(d, i) {
				return i * 100;
			})
			.duration(1000)
			.ease('quad-in-out')
			.attr('width', function(d) {
				return xScale(d.x);
			});

			/*** EVENTOS ***/

			// Exibe o tooltip ao passar o mouse nas barras
			d3elemento
			.selectAll('.pedaco')
			.on("mouseover", function(valor) {
				if (typeof(valor.tooltip) != 'undefined') {
					d3.select('body')
					.append('span')
					.attr('class', 'label-tooltip')
					.html(valor.tooltip);
				}
				base.tooltip.posicionar();
			});

			// Remove o tooltip ao retirar o mouse das barras
			d3elemento
			.selectAll('.pedaco')
			.on("mouseout", function(valor) {
				d3.select('.label-tooltip').remove();
			});

			// Remove o tooltip na rolagem (Solução para mobile)
			d3.select(window).on('scroll', function() {
				d3.select('.label-tooltip').remove();
			});

			/*** RESPONSIVIDADE ***/
			var throttleTimerHorizontalEmpilhada; // timer para limitar calculos
			d3.select(window).on('resize.barrasHorizontalEmpilhada', function() {
				// verifica se ja existe timer
				if (typeof throttleTimerHorizontalEmpilhada != "undefined" && throttleTimerHorizontalEmpilhada != null) {
					window.clearTimeout(throttleTimerHorizontalEmpilhada);
				}
				// seta timer para limitar calculos
				throttleTimerHorizontalEmpilhada = window.setTimeout(function() {
					d3.selectAll('.barras-horizontal-pilha')
					.each(function() {
							// Pegando e calculando a largura
							width = parseInt(d3.select(this.parentNode).style('width'), 10);
							width = width - margin.left - margin.right;
							// Atualização do eixo X
							xScale.range([0, width], .1);
							d3.select(this).select('.x.axis').call(xAxis.orient('bottom'));

							// Atualização da largura do SVG
							d3.select(this).attr('width', (width + margin.left + margin.right));

							// Atualização das barras
							d3.select(this).selectAll('.pedaco').attr("x", function(d) {
								return xScale(d.x0);
							});
							// Atualização das barras
							d3.select(this).selectAll('.pedaco').attr("width", function(d) {
								return xScale(d.x);
							});
						});
				}, 100);
			});
		},

		construirBarraProgressoDonut: function($elemento,d3elemento) {
			var tabela = JSON.parse($elemento.attr('data-table-json')),
			lista = [],
			altura = 400;

			// Dados
			lista[0] = {
				'label': base.label.formatar(tabela[0].label),
				'value': base.moeda.converterParaFloat(tabela[0].valor),
				'json': tabela[0],
				'tooltip': tabela[0].tooltip,
				'paleta': tabela[0].paleta
			}
			lista[1] = {
				'label': '',
				'value': 100 - base.moeda.converterParaFloat(tabela[0].valor),
				'json': tabela[0],
				'tooltip': tabela[0].tooltip,
				'paleta': tabela[0].paleta
			}

			// Localizando o idioma/moeda/data/etc para o Português BR
			var localizacaoBR = d3.locale({
				"decimal": ",",
				"thousands": ".",
				"grouping": [3],
				"currency": ["R$", ""],
				"dateTime": "%d/%m/%Y %H:%M:%S",
				"date": "%d/%m/%Y",
				"time": "%H:%M:%S",
				"periods": ["AM", "PM"],
				"days": ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
				"shortDays": ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
				"months": ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
				"shortMonths": ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"]
			}),
			pt_real = localizacaoBR.numberFormat("$,.2f"),
			pt = localizacaoBR.numberFormat(",");

			// Dados Gerais
			var margin = {
				top: 50,
				right: 20,
				bottom: 50,
				left: 150
			},
			width = parseInt(d3elemento.style('width'), 10),
			width = width - margin.right,
			height = altura,
			radius = height / 2,
			donutWidth = 30;

			// Insere o SVG no DOM
			if( base.crossBrowser.detectIE() ){
				var svgContainer = d3elemento
				.append("svg")
				.attr('height', height)
				.attr('width', '100%')
				.attr('viewBox', '-30 -30 460 460')
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			} else {
				var svgContainer = d3elemento
				.append("svg")
				.attr('preserveAspectRatio', 'xMinYMin meet')
				.attr('viewBox', '-30 -30 460 460')
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			}

			// Insere os valores e cria a circunferência
			var vis = d3elemento.select('svg')
			.data([lista])
			.append("svg:g")
			.attr("transform", "translate(" + radius + "," + radius + ")"),
			pie = d3.layout.pie().value(function(d) {
				return d.value;
			}),
			arc = d3.svg.arc().innerRadius(radius - donutWidth).outerRadius(radius),
			arcs = vis.selectAll("g.slice")
			.data(pie)
			.enter()
			.append("svg:g")
			.attr("class", "slice")
			.attr('stroke', '#fff')
			.attr('stroke-width', 1);

			// Insere as fatias
			arcs.append("svg:path")
			.attr("fill", function(d, i) {
				var cor;
				if (i == 0) {
					cor = "#1c6ab2";
				} else {
					cor = "#ededed";
				}

				$elemento.append('<span>' + d.data.label + '</span>');

				return cor;
			})
				// Animação inicial
				.transition()
				.duration(1000)
				.attrTween('d', function(finish) {
					var start = {
						startAngle: 0,
						endAngle: 0
					};
					var i = d3.interpolate(start, finish);
					return function(d) {
						return arc(i(d));
					};
				});

			// Insere os textos
			arcs.append("svg:text")
			.attr('fill', '#416aad')
			.attr('stroke', 'none')
			.attr('font-size', '70')
			.attr("dominant-baseline", "central")
			.attr("text-anchor", "middle").text(function(d, i) {
				if (i == 0) {
						// Calcula a porcentagem a ser exibida nas fatias
						var total = 0,
						porcentagem = 0;

						for (var inc = 0; inc < lista.length; inc++) {
							total = total + lista[inc].value;
						}

						porcentagem = parseFloat((lista[i].value * 100) / total).toFixed(0);

						return porcentagem + '%';
					} else {
						return '';
					}
				});
		}
	},

	label: {
		formatar: function(textLabel) {
			textoCorrigido = textLabel.split('<small>');
			return textoCorrigido[0];
		}
	},

	moeda: {
		converterParaFloat: function(currency) {
			if (typeof currency == "string") {
				currency = currency.replace(/\./g, '');
				return parseFloat(currency.replace(/\,/g, '.'));
			} else {
				return 0;
			}
		},

		converterParaReal: function(c, d, t) {
			var n = this,
			c = isNaN(c = Math.abs(c)) ? 2 : c,
			d = d == undefined ? "." : d,
			t = t == undefined ? "," : t,
			s = n < 0 ? "-" : "",
			i = parseInt(n = Math.abs(+n || 0).toFixed(c), 10) + "",
			j = (j = i.length) > 3 ? j % 3 : 0;
			return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
		},

		FloadParaReal: function(numero, c, d, t) {
			var n = numero,
			c = isNaN(c = Math.abs(c)) ? 2 : c,
			d = d == undefined ? "," : d,
			t = t == undefined ? "." : t,
			s = n < 0 ? "-" : "",
			i = parseInt(n = Math.abs(+n || 0).toFixed(c), 10) + "",
			j = (j = i.length) > 3 ? j % 3 : 0;
			return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
		}
	},

	crossBrowser: {
		/**
		 * Detect IE
		 * returns version of IE or false, if browser is not Internet Explorer
		 */
		 detectIE: function () {
		 	var ua = window.navigator.userAgent;var msie = ua.indexOf('MSIE ');
		 	if (msie > 0) {
				// IE 10 or older => return version number
				return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
			}

			var trident = ua.indexOf('Trident/');
			if (trident > 0) {
				// IE 11 => return version number
				var rv = ua.indexOf('rv:');
				return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
			}

			var edge = ua.indexOf('Edge/');
			if (edge > 0) {
				// Edge (IE 12+) => return version number
				// return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
			}

			// other browser
			return false;
		},

		wrapText: function (text, width) {
			text.each(function () {
				var textEl = d3.select(this),
				words = textEl.text().split(/\s+/).reverse(),
				word,
				line = [],
				linenumber = 0,
				lineHeight = 1.1,
				x = textEl.attr('x'),
				y = textEl.attr('y'),
				dx = parseFloat(textEl.attr('dx') || 0),
				dy = parseFloat(textEl.attr('dy') || 0),
				tspan = textEl.text(null).append('tspan').attr('x', x).attr('y', y).attr('dy', dy + 'em');
				while (word = words.pop()) {
					line.push(word);
					tspan.text(line.join(' '));
					if (tspan.node().getComputedTextLength() > width) {
						line.pop();
						tspan.text(line.join(' '));
						line = [word];
						tspan = textEl.append('tspan').attr('x', x).attr('y', y).attr('dx', dx).attr('dy', ++linenumber * lineHeight + dy + 'em').text(word);
					}
				}
			});
		}
	},

	cor: {
		selecionar: function(contador, paleta) {
			switch (paleta) {
				case 'azul':
				var cores = ["#2369b3","#4dcfff","#c4e8f5"];
				break;
				default:
				var cores = ["#45b8dd", "#db0d54", "#791a81", "#168135", "#bdcc1e",
				"#2484c1", "#65a620", "#7b6888", "#a05d56", "#961a1a",
				"#d8d23a", "#e98125", "#d0743c", "#635222", "#6ada6a",
				"#0c6197", "#7d9058", "#207f33", "#44b9b0", "#bca44a",
				"#e4a14b", "#a3acb2", "#8cc3e9", "#69a6f9", "#5b388f",
				"#546e91", "#8bde95", "#d2ab58", "#273c71", "#98bf6e",
				"#4daa4b", "#98abc5", "#cc1010", "#31383b", "#006391",
				"#c2643f", "#b0a474", "#a5a39c", "#a9c2bc", "#22af8c",
				"#7fcecf", "#987ac6", "#3d3b87", "#b77b1c", "#c9c2b6",
				"#807ece", "#8db27c", "#be66a2", "#9ed3c6", "#00644b",
				"#005064", "#77979f", "#77e079", "#9c73ab", "#1f79a7"
				];
			}

			var corAtual = contador > (cores.length - 1) ? contador - cores.length : contador;

			return cores[corAtual];
		}
	},

	tooltip: {
		posicionar: function() {
			var limite = (parseInt(d3.select('body').style('width'), 10));
			window.onmousemove = function(e) {
				var x = e.clientX,
				y = e.clientY,
				tooltip = $('.label-tooltip').width();

				if ((x + tooltip) >= limite) {
					$('.label-tooltip').css({
						'transform': 'translate(' + ((limite - tooltip) - 35) + 'px,' + (y + 20) + 'px)',
						'-webkit-transform': 'translate(' + ((limite - tooltip) - 35) + 'px,' + (y + 20) + 'px)'
					});
				} else {
					$('.label-tooltip').css({
						'transform': 'translate(' + (x - 20) + 'px,' + (y + 30) + 'px)',
						'-webkit-transform': 'translate(' + (x - 20) + 'px,' + (y + 30) + 'px)'
					});
				}
			};
		},

		iniciar: function() {
			$("[data-toggle=tooltip]").tooltip();

			$('.container').on('mouseover',function(e) {
				e.stopPropagation();
			});

			$('body').on("mouseover", function() {
				$('.label-tooltip').remove();
			});
		}
	},

	/**
	 * Insere as máscaras de entrada nos campos especificados
	 * Para funcionar, é preciso:
	 * - Chamar a função abaixo
	 * - Inserir as classes correspondentes à máscara desejada no campo. (Veja a lista completa na página forms do guia de estilo)
	 *
	 * @return	void
	 * @author	Alvino Rodrigues
	 */
	 mask: {
	 	init: function() {
	 		/* Conteúdo de jquery.maskinput.min.js */
	 		!function(e){function t(){var e=document.createElement("input"),t="onpaste";return e.setAttribute(t,""),"function"==typeof e[t]?"paste":"input"}var n,a=t()+".mask",r=navigator.userAgent,i=/iphone/i.test(r),o=/android/i.test(r);e.mask={definitions:{9:"[0-9]",a:"[A-Za-z]","*":"[A-Za-z0-9]"},dataName:"rawMaskFn",placeholder:"_"},e.fn.extend({caret:function(e,t){var n;return 0===this.length||this.is(":hidden")?void 0:"number"==typeof e?(t="number"==typeof t?t:e,this.each(function(){this.setSelectionRange?this.setSelectionRange(e,t):this.createTextRange&&(n=this.createTextRange(),n.collapse(!0),n.moveEnd("character",t),n.moveStart("character",e),n.select())})):(this[0].setSelectionRange?(e=this[0].selectionStart,t=this[0].selectionEnd):document.selection&&document.selection.createRange&&(n=document.selection.createRange(),e=0-n.duplicate().moveStart("character",-1e5),t=e+n.text.length),{begin:e,end:t})},unmask:function(){return this.trigger("unmask")},mask:function(t,r){var c,l,s,u,f,h;return!t&&this.length>0?(c=e(this[0]),c.data(e.mask.dataName)()):(r=e.extend({placeholder:e.mask.placeholder,completed:null},r),l=e.mask.definitions,s=[],u=h=t.length,f=null,e.each(t.split(""),function(e,t){"?"==t?(h--,u=e):l[t]?(s.push(RegExp(l[t])),null===f&&(f=s.length-1)):s.push(null)}),this.trigger("unmask").each(function(){function c(e){for(;h>++e&&!s[e];);return e}function d(e){for(;--e>=0&&!s[e];);return e}function m(e,t){var n,a;if(!(0>e)){for(n=e,a=c(t);h>n;n++)if(s[n]){if(!(h>a&&s[n].test(R[a])))break;R[n]=R[a],R[a]=r.placeholder,a=c(a)}b(),x.caret(Math.max(f,e))}}function p(e){var t,n,a,i;for(t=e,n=r.placeholder;h>t;t++)if(s[t]){if(a=c(t),i=R[t],R[t]=n,!(h>a&&s[a].test(i)))break;n=i}}function v(e){var t,n,a,r=e.which;8===r||46===r||i&&127===r?(t=x.caret(),n=t.begin,a=t.end,0===a-n&&(n=46!==r?d(n):a=c(n-1),a=46===r?c(a):a),k(n,a),m(n,a-1),e.preventDefault()):27==r&&(x.val(S),x.caret(0,y()),e.preventDefault())}function g(t){var n,a,i,l=t.which,u=x.caret();t.ctrlKey||t.altKey||t.metaKey||32>l||l&&(0!==u.end-u.begin&&(k(u.begin,u.end),m(u.begin,u.end-1)),n=c(u.begin-1),h>n&&(a=String.fromCharCode(l),s[n].test(a)&&(p(n),R[n]=a,b(),i=c(n),o?setTimeout(e.proxy(e.fn.caret,x,i),0):x.caret(i),r.completed&&i>=h&&r.completed.call(x))),t.preventDefault())}function k(e,t){var n;for(n=e;t>n&&h>n;n++)s[n]&&(R[n]=r.placeholder)}function b(){x.val(R.join(""))}function y(e){var t,n,a=x.val(),i=-1;for(t=0,pos=0;h>t;t++)if(s[t]){for(R[t]=r.placeholder;pos++<a.length;)if(n=a.charAt(pos-1),s[t].test(n)){R[t]=n,i=t;break}if(pos>a.length)break}else R[t]===a.charAt(pos)&&t!==u&&(pos++,i=t);return e?b():u>i+1?(x.val(""),k(0,h)):(b(),x.val(x.val().substring(0,i+1))),u?t:f}var x=e(this),R=e.map(t.split(""),function(e){return"?"!=e?l[e]?r.placeholder:e:void 0}),S=x.val();x.data(e.mask.dataName,function(){return e.map(R,function(e,t){return s[t]&&e!=r.placeholder?e:null}).join("")}),x.attr("readonly")||x.one("unmask",function(){x.unbind(".mask").removeData(e.mask.dataName)}).bind("focus.mask",function(){clearTimeout(n);var e;S=x.val(),e=y(),n=setTimeout(function(){b(),e==t.length?x.caret(0,e):x.caret(e)},10)}).bind("blur.mask",function(){y(),x.val()!=S&&x.change()}).bind("keydown.mask",v).bind("keypress.mask",g).bind(a,function(){setTimeout(function(){var e=y(!0);x.caret(e),r.completed&&e==x.val().length&&r.completed.call(x)},0)}),y()}))}})}(jQuery);
	 		/* Conteúdo de jquery.maskmoney.min.js */
	 		!function(e){"use strict";e.browser||(e.browser={},e.browser.mozilla=/mozilla/.test(navigator.userAgent.toLowerCase())&&!/webkit/.test(navigator.userAgent.toLowerCase()),e.browser.webkit=/webkit/.test(navigator.userAgent.toLowerCase()),e.browser.opera=/opera/.test(navigator.userAgent.toLowerCase()),e.browser.msie=/msie/.test(navigator.userAgent.toLowerCase()));var t={destroy:function(){return e(this).unbind(".maskMoney"),e.browser.msie&&(this.onpaste=null),this},mask:function(t){return this.each(function(){var n,a=e(this);return"number"==typeof t&&(a.trigger("mask"),n=e(a.val().split(/\D/)).last()[0].length,t=t.toFixed(n),a.val(t)),a.trigger("mask")})},unmasked:function(){return this.map(function(){var t,n=e(this).val()||"0",a=-1!==n.indexOf("-");return e(n.split(/\D/).reverse()).each(function(e,n){return n?(t=n,!1):void 0}),n=n.replace(/\D/g,""),n=n.replace(new RegExp(t+"$"),"."+t),a&&(n="-"+n),parseFloat(n)})},init:function(t){return t=e.extend({prefix:"",suffix:"",affixesStay:!0,thousands:",",decimal:".",precision:2,allowZero:!1,allowNegative:!1},t),this.each(function(){function n(){var e,t,n,a,r,o=b.get(0),i=0,s=0;return"number"==typeof o.selectionStart&&"number"==typeof o.selectionEnd?(i=o.selectionStart,s=o.selectionEnd):(t=document.selection.createRange(),t&&t.parentElement()===o&&(a=o.value.length,e=o.value.replace(/\r\n/g,"\n"),n=o.createTextRange(),n.moveToBookmark(t.getBookmark()),r=o.createTextRange(),r.collapse(!1),n.compareEndPoints("StartToEnd",r)>-1?i=s=a:(i=-n.moveStart("character",-a),i+=e.slice(0,i).split("\n").length-1,n.compareEndPoints("EndToEnd",r)>-1?s=a:(s=-n.moveEnd("character",-a),s+=e.slice(0,s).split("\n").length-1)))),{start:i,end:s}}function a(){var e=!(b.val().length>=b.attr("maxlength")&&b.attr("maxlength")>=0),t=n(),a=t.start,r=t.end,o=t.start!==t.end&&b.val().substring(a,r).match(/\d/)?!0:!1,i="0"===b.val().substring(0,1);return e||o||i}function r(e){b.each(function(t,n){if(n.setSelectionRange)n.focus(),n.setSelectionRange(e,e);else if(n.createTextRange){var a=n.createTextRange();a.collapse(!0),a.moveEnd("character",e),a.moveStart("character",e),a.select()}})}function o(e){var n="";return e.indexOf("-")>-1&&(e=e.replace("-",""),n="-"),n+t.prefix+e+t.suffix}function i(e){var n,a,r,i=e.indexOf("-")>-1&&t.allowNegative?"-":"",s=e.replace(/[^0-9]/g,""),l=s.slice(0,s.length-t.precision);return l=l.replace(/^0*/g,""),l=l.replace(/\B(?=(\d{3})+(?!\d))/g,t.thousands),""===l&&(l="0"),n=i+l,t.precision>0&&(a=s.slice(s.length-t.precision),r=new Array(t.precision+1-a.length).join(0),n+=t.decimal+r+a),o(n)}function s(e){var t,n=b.val().length;b.val(i(b.val())),t=b.val().length,e-=n-t,r(e)}function l(){var e=b.val();b.val(i(e))}function c(){var e=b.val();return t.allowNegative?""!==e&&"-"===e.charAt(0)?e.replace("-",""):"-"+e:e}function u(e){e.preventDefault?e.preventDefault():e.returnValue=!1}function v(t){t=t||window.event;var r,o,i,l,v,g=t.which||t.charCode||t.keyCode;return void 0===g?!1:48>g||g>57?45===g?(b.val(c()),!1):43===g?(b.val(b.val().replace("-","")),!1):13===g||9===g?!0:!e.browser.mozilla||37!==g&&39!==g||0!==t.charCode?(u(t),!0):!0:a()?(u(t),r=String.fromCharCode(g),o=n(),i=o.start,l=o.end,v=b.val(),b.val(v.substring(0,i)+r+v.substring(l,v.length)),s(i+1),!1):!1}function g(e){e=e||window.event;var a,r,o,i,l,c=e.which||e.charCode||e.keyCode;return void 0===c?!1:(a=n(),r=a.start,o=a.end,8===c||46===c||63272===c?(u(e),i=b.val(),r===o&&(8===c?""===t.suffix?r-=1:(l=i.split("").reverse().join("").search(/\d/),r=i.length-l-1,o=r+1):o+=1),b.val(i.substring(0,r)+i.substring(o,i.length)),s(r),!1):9===c?!0:!0)}function f(){w=b.val(),l();var e,t=b.get(0);t.createTextRange&&(e=t.createTextRange(),e.collapse(!1),e.select())}function d(){setTimeout(function(){l()},0)}function p(){var e=parseFloat("0")/Math.pow(10,t.precision);return e.toFixed(t.precision).replace(new RegExp("\\.","g"),t.decimal)}function h(n){if(e.browser.msie&&v(n),""===b.val()||b.val()===o(p()))b.val(t.allowZero?t.affixesStay?o(p()):p():"");else if(!t.affixesStay){var a=b.val().replace(t.prefix,"").replace(t.suffix,"");b.val(a)}b.val()!==w&&b.change()}function m(){var e,t=b.get(0);t.setSelectionRange?(e=b.val().length,t.setSelectionRange(e,e)):b.val(b.val())}var w,b=e(this);t=e.extend(t,b.data()),b.unbind(".maskMoney"),b.bind("keypress.maskMoney",v),b.bind("keydown.maskMoney",g),b.bind("blur.maskMoney",h),b.bind("focus.maskMoney",f),b.bind("click.maskMoney",m),b.bind("cut.maskMoney",d),b.bind("paste.maskMoney",d),b.bind("mask.maskMoney",l)})}};e.fn.maskMoney=function(n){return t[n]?t[n].apply(this,Array.prototype.slice.call(arguments,1)):"object"!=typeof n&&n?void e.error("Method "+n+" does not exist on jQuery.maskMoney"):t.init.apply(this,arguments)}}(window.jQuery||window.Zepto);
	 		/* Conteúdo de jquery.onlynumber.min.js */
	 		$.fn.onlyNumber=function(e){for(var h={except:"-.A"},e=$.extend({},h,e||{}),c=[],n=0;n<e.except.length;n++)c.push(e.except.charCodeAt(n));return this.each(function(){$(this).keypress(function(e){return 8!=e.which&&0!=e.which&&(e.which<48||e.which>57)&&-1==$.inArray(e.which,c)?!1:void 0})})};

	 		var $cnpj = $('.mask-cnpj'),
	 		$cpf = $('.mask-cpf'),
	 		$cep = $('.mask-cep'),
	 		$date = $('.mask-date'),
	 		$real = $('.mask-real'),
	 		$phone = $('.mask-phone'),
	 		$number = $('.mask-number'),
	 		$field = $('.mask-custom');

	 		if ($cnpj.length) { $cnpj.mask('99.999.999/9999-99',{placeholder:' '}); }
	 		if ($cpf.length) { $cpf.mask('999.999.999-99',{placeholder:' '}); }
	 		if ($cep.length) { $cep.mask('99.999-999',{placeholder:' '}); }
	 		if ($date.length) { $date.mask('99/99/9999',{placeholder:' '}); }
	 		if ($real.length) { $real.maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: true}); }
	 		if ($phone.length) { $phone.mask("(99) 9999?9-9999",{placeholder:' '}); }
	 		if ($number.length) { $number.onlyNumber(); }
	 		if ($field.length) {
	 			var mascara = $field.data("mask");
	 			$field.mask(mascara,{placeholder:' '});
	 		}
	 		/* fix 9º digito - Marcos Furquim */
	 		$phone.on("blur", function() {
	 			var first = $(this).val().substr( 0,$(this).val().indexOf("-"));
	 			var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );
	 			if( last.length == 3 ) {

	 				var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
	 				var lastfour = move + last;
	 				var first = $(this).val().substr(0, 9);

	 				$(this).val( first + '-' + lastfour );
	 				$phone.mask("(99) 9999-9999?9",{placeholder:' '});
	 			} else {
	 				if(last.length == 5) {
	 					var move = $(this).val().substr( $(this).val().indexOf("-") + 1, 1 );
	 					var first = first + move;
	 					var lastfour = last.substr(1);

	 					$(this).val( first + '-' + lastfour );
	 					$phone.mask("(99) 9999?9-9999",{placeholder:' '});
	 				}
	 			}

	 		});
	 	}
	 },

	/**
	* Alterna entre mostrar ou esconder senha
	*
	* @return void
	* @author Marcos Furquim
	*/
	showHidePass: {
		click: function () {

			$("input + .input-group-addon:has(\"i[class*='fa-eye']\")")
			.css("cursor","pointer")
			.click(function () {
				$(this).each(function() {
						if($(this).children("i").hasClass("fa-eye")) { //olho aberto, fechar
							$(this).prev().attr("type","password");
							$(this).children("i").removeClass("fa-eye").addClass("fa-eye-slash");
						} else { //olho fechado, abrir
							$(this).prev().attr("type","text");
							$(this).children("i").removeClass("fa-eye-slash").addClass("fa-eye");
						}
					});
			});
		}
	},

	tabs: {
		noObstrusive: function() {
			$('.tab-content,.block-tabs-carousel').addClass('no-obstrusive');
		},
		graphicSolution: function() {
			$('a[data-toggle="tab"],button[data-toggle="tab"]').on('click',function() {
				d3.select(window).on('resize.linhas');
			});
		},
		carouselSolution: function() {
			$('.block-tabs-carousel__item')
			.find('a')
			.on('click',function() {
				$(this)
				.parent('.block-tabs-carousel__item')
				.addClass('active')
				.siblings('.block-tabs-carousel__item')
				.removeClass('active');
			});
		}
	},

	perfil: {
		trocarPassos: function() {
			$('.block-profile__step').addClass('no-obstrusive');

			var $step1 = $('.block-profile__step-1'),
			$step2 = $('.block-profile__step-2'),
			$step3 = $('.block-profile__step-3');

			$('.step-2-button').on('click',function() {
				$step1.removeClass('active');
				$step2.addClass('active');
			});

			$('.step-1-button').on('click',function() {
				$step2.removeClass('active');
				$step1.addClass('active');
				return false;
			});

			$('.points__button').on('click',function() {
				$step1.removeClass('active');
				$step2.removeClass('active');
				$step3.addClass('active');

				$(window).resize();
			});

			$('.points__close').on('click',function() {
				$step3.removeClass('active');
				$step2.removeClass('active');
				$step1.addClass('active');
			});
		}
	},

	statusAvaliacaoFiltro: {
		click: function () {
			$('.btn-mostrar-filtro').on('click',function() {
				$('.status-do-filtro').toggleClass('ativo');
			});
		}
	},

	formulario: {
		
		/**
		 * Exibe/ esconde divs dependentes
		 *
		 * @return void
		 * @author Marcos Furquim
		 */
		 radioEvent: function() {
		 	$("[data-show]").each(function() {
		 		$($(this).attr("data-show")).hide();
		 	})
		 	.click(function() {
		 		$("[data-show]").each(function() {
		 			$($(this).attr("data-show")).hide();
		 		})
		 		$($(this).attr("data-show")).hide();
		 		$($(this).attr("data-show")).show();
					//$("[data-show]").not("[data-show='"+$(this).attr('data-show')+"']").hide();
					
				});
		 },


		/**
		 * exibe mensagem de sucesso em form
		 *
		 * @return void
		 * @author Marco Malaquias
		 */
		 enviarSucesso: function(form, divSucesso, texto) {
		 	var $divSucesso = $(divSucesso);
		 	$divSucesso.hide();

		 	$(form).submit(function(){
		 		$divSucesso.text(texto);
		 		$divSucesso.show();
		 		return false;
		 	});
		 },

		/**
		 * Exibe imagem após fazer upload (navegador)
		 *
		 * @return void
		 * @author Marcos Furquim
		 */
		 previewImage: function () {
		 	function readURL(input) {

		 		if (input.files && input.files[0]) {
		 			var reader = new FileReader();

		 			reader.onload = function (e) {
		 				$(input).prev(".previewImage").attr('src', e.target.result);
		 			}

		 			reader.readAsDataURL(input.files[0]);
		 		}
		 	}

		 	$(".entidade-marca").change(function(){
		 		readURL(this);
		 	});
		 },

		/**
		 * Toggle do popup de comentário
		 *
		 * @return void
		 * @author Marcos Furquim
		 */
		 commentPopUp: function () {
		 	$(".card__comment-box > a").click(function() {
		 		if(!$(this).parent().hasClass("active")) {
		 			$(this).parent().addClass("active");

		 		} else {
		 			$(this).parent().removeClass("active");
		 		}
		 	});
		 },

		/**
		 * Toggle do popup de comentário
		 *
		 * @return void
		 * @author Marcos Furquim
		 */
		numberOnly: function(){
			$("[data-validate=number]").keydown(function (e) {
			    // Allow: backspace, delete, tab, escape, enter and .
			    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
			         // Allow: Ctrl/cmd+A
			        (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
			         // Allow: Ctrl/cmd+C
			        (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
			         // Allow: Ctrl/cmd+X
			        (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
			         // Allow: home, end, left, right
			        (e.keyCode >= 35 && e.keyCode <= 39)) {
			             // let it happen, don't do anything
			             return;
			    }
			    // Ensure that it is a number and stop the keypress
			    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
			        e.preventDefault();
			    }
			});
		},


		 /**
		 * Toggle para edição de campo, pront para update via ajax
		 *
		 * @return void
		 * @author Marcos Furquim
		 */
		editButton: function() {
		 	function eventsTrigger() {
		 		$(".i-editar").unbind().click(function() {
		 			if($(this).parent().parent().attr("data-editable") == "true") {
		 				$pai = $(this).parent().parent();
		 				var emailTmp = $pai.children("span").text().trim();
		 				var $input = $("<input type='text' class='form-control' placeholder='E-Mail'>")
		 				.css(
		 					{display: "inline-block",
		 					width: "75%"}
		 					)
		 				.val(emailTmp);
		 				var $buttons = $("<i class='fa fa-check i-save' aria-hidden='true'></i><i class='fa fa-times i-cancelar' aria-hidden='true'></i>");
		 				$pai.empty().append($input).append($buttons);
		 				eventsTriggerEditMode();
		 			}
		 		});
		 	}
		 	function eventsTriggerEditMode() {
		 		$(".i-save").unbind().click(function() {
		 			if($(this).parent().attr("data-editable") == "true") {
		 				$pai = $(this).parent();
		 				$span = $("<span></span>").text($pai.children("input").val().trim());
		 				$btnGroup = $("<div class='btn-group-edit'><i class='fa fa-pencil i-editar' aria-hidden='true'></i></div>");
		 				$pai.html($span).append($btnGroup);

		 				/*
		 				* MOMENTO AJAX UPDATE AQUI
		 				*/


		 				eventsTrigger();
		 			}
		 		});

		 		$(".i-cancelar").unbind().click(function() {
		 			if($(this).parent().attr("data-editable") == "true") {
		 				$pai = $(this).parent();
		 				$span = $("<span></span>").text($pai.children("input").val().trim());
		 				$btnGroup = $("<div class='btn-group-edit'><i class='fa fa-pencil i-editar' aria-hidden='true'></i></div>");
		 				$pai.html($span).append($btnGroup);
		 				eventsTrigger();
		 			}
		 		});
		 	}
		 	eventsTrigger();

		 }
	},

	drag: {
		ativar: function() {
			$(".form-quiz__block-list").sortable();
		}
	},

	componente: {
		adicionar: function() {
			var increment1 = 0,
			increment3 = 0;

			$('.add-component-1__button').on('click',function() {
				increment1++;
				var estrutura = '<div class="form-row">\
				<div class="col-xs-12 col-sm-5">\
				<div class="form-row">\
				<div class="col-xs-12 col-sm-1">\
				<div class="checkbox">\
				<input type="checkbox" id="component-added-' + increment1 + '" name="component-added-' + increment1 + '">\
				<label for="component-added-' + increment1 + '"></label>\
				</div>\
				</div>\
				<div class="col-xs-12 col-sm-11">\
				<input type="text" class="form-control" placeholder="Opção">\
				</div>\
				</div>\
				</div>\
				<div class="col-xs-12 col-sm-1">\
				<input type="text" class="form-control" placeholder="00%">\
				</div>\
				<div class="col-xs-12 col-sm-2">\
				<button type="button" class="form-quiz__button">ADICIONAR<br>DEPENDENTE</button>\
				<button type="button" class="form-quiz__button"><i class="fa fa-times" aria-hidden="true"></i></button>\
				</div>\
				</div>';

				$(this).parents('.add-component-1').before(estrutura);
				base.radioCheckbox.change();
			});

			$('.add-component-2__button').on('click',function() {
				var estrutura = '<div class="form-row form-group">\
				<div class="col-xs-12 col-sm-8">\
				<input type="text" class="form-control" placeholder="Sub-tópico">\
				</div>\
				<div class="col-xs-12 col-sm-2">\
				<button type="button" class="form-quiz__button"><i class="fa fa-times" aria-hidden="true"></i></button>\
				</div>\
				</div>';

				$(this).parents('.add-component-2').before(estrutura);
				base.radioCheckbox.change();
			});

			$('.add-component-3__button').on('click',function() {
				increment3++;
				var estrutura = '<div class="form-row">\
				<div class="col-xs-12 col-sm-5">\
				<div class="form-row">\
				<div class="col-xs-12 col-sm-1"></div>\
				<div class="col-xs-12 col-sm-1">\
				<div class="checkbox">\
				<input type="checkbox" id="component-added-' + increment3 + '" name="component-added-' + increment3 + '">\
				<label for="component-added-' + increment3 + '"></label>\
				</div>\
				</div>\
				<div class="col-xs-12 col-sm-10">\
				<input type="text" class="form-control" placeholder="Opção">\
				</div>\
				</div>\
				</div>\
				<div class="col-xs-12 col-sm-1">\
				<input type="text" id="bloco-4-componente-8-3" class="form-control" placeholder="00%">\
				</div>\
				<div class="col-xs-12 col-sm-2">\
				<button type="button" class="form-quiz__button"><i class="fa fa-times" aria-hidden="true"></i></button>\
				</div>\
				</div>';

				$(this).parents('.add-component-3').before(estrutura);
				base.radioCheckbox.change();
			});

			$('.add-component-4__button').on('click',function() {
				var estrutura = '<div class="form-row form-group">\
				<div class="col-xs-12 col-sm-8">\
				<input type="text" class="form-control" placeholder="Sub-tópico">\
				</div>\
				<div class="col-xs-12 col-sm-2">\
				<button type="button" class="form-quiz__button"><i class="fa fa-times" aria-hidden="true"></i></button>\
				</div>\
				</div>';

				$(this).parents('.add-component-4').before(estrutura);
				base.radioCheckbox.change();
			});

			$('.add-component-5__button').on('click',function() {
				var estrutura = '<div class="form-row form-group">\
				<div class="col-xs-12 col-sm-7">\
				<div class="form-row">\
				<div class="col-xs-12 col-sm-1">\
				<a href="#" class="button--icon-a button--icon-only"><i class="fa fa-file-image-o" aria-hidden="true"></i>&nbsp;</a>\
				</div>\
				<div class="col-xs-12 col-sm-3">\
				<a href="#" class="button--icon-a button--icon-form"><i class="fa fa-upload" aria-hidden="true"></i> Enviar arquivo</a>\
				</div>\
				<div class="col-xs-12 col-sm-2">\
				<input type="text" class="form-control" placeholder="00%">\
				</div>\
				<div class="col-xs-12 col-sm-3">\
				<button type="button" class="form-quiz__button">ADICIONAR<br>DEPENDENTE</button>\
				<button type="button" class="form-quiz__button"><i class="fa fa-times" aria-hidden="true"></i></button>\
				</div>\
				</div>\
				</div>\
				</div>';

				$(this).parents('.add-component-5').before(estrutura);
				base.radioCheckbox.change();
			});
		}
	},

	autoComplete: {
		paginaPainelMinhasAvaliacoes: function() {
			var options = {
				url: "./assets/inc/ajax_dados/ipsum.json",
				getValue: "Nome",
				list: {
					maxNumberOfElements: 20,
					match: {
						enabled: true
					}
				}
			};
			$("#block-filter__form-search__text").easyAutocomplete(options);
		},

		autoCompleteSimplesSelecao: function(campo, url) {
			var options = {
				url: url,
				getValue: "Nome",
				list: {
					maxNumberOfElements: 20,
					match: {
						enabled: true
					}
				},
				categories: [
					{
						listLocation: 'Cidades',
						header: '<span>Cidades</span>'
					},
					{
						listLocation: 'Estados',
						header: '<span">Estados</span>'
					}
				],
				template: {
					type: 'custom',
					method: function(value, item) {
						if(item.Estado) { //se tiver estado no objeto
							return '<a class="option-ajax-localidade" data-tipo="municipio" data-nome="' + item.Nome + '">' + value + ' - ' + item.Sigla + '</span>';
						} else {
							return '<a class="option-ajax-localidade" data-tipo="estado" data-nome="' + item.Nome + '" data-uf="' + item.Sigla + '">' + value + ' (' + item.Sigla + ')</span>';
						}
					}
				}

			};
			$(campo).easyAutocomplete(options);
		},
		autoCompleteSimplesSelecaoIpsum: function(campo, url) {
			var options = {
				url: url,
				getValue: "Nome",
				list: {
					maxNumberOfElements: 20,
					match: {
						enabled: true
					}
				}

			};
			$(campo).easyAutocomplete(options);
		},

		/**
		 * autocomplemeMultiplaSelecao
		 *
		 * @return void
		 */
		autocomplemeMultiplaSelecao: function(id, target, url) {
			var options = {
				url: url,
				getValue: 'Nome',
				list: {
					maxNumberOfElements: 5,
					match: {
						enabled: true
					},
					onClickEvent: function() {
						var $obj = $(id).getSelectedItemData();
						var $button = $('<button></button>').addClass('block-rate__button button--icon-a  dmr-5 dmb-5').attr('button', 'button');
						var $i = $('<i></i>').addClass('fa fa-trash').attr('aria-hidden', 'true');
						var $hidden = $('<input></input>').attr('type', 'hidden').attr('name', 'localidade_' + $obj.ID).val($obj.ID);

						$button.append($i).append($obj.Nome + ' - ' + $obj.Sigla)
								.append($hidden);
						$button.click(function(){
							$(this).remove();
						});

						$(target).append($button);
						$(id).val('');
					}
				},
				categories: [
					{
						listLocation: 'Cidades',
						header: '<span>Cidades</span>'
					},
					{
						listLocation: 'Estados',
						header: '<span">Estados</span>'
					}
				],
				template: {
					type: 'custom',
					method: function(value, item) {
						if(item.Estado) { //se tiver estado no objeto
							return '<a class="option-ajax-localidade" data-tipo="municipio" data-nome="' + item.Nome + '">' + value + ' - ' + item.Sigla + '</span>';
						} else {
							return '<a class="option-ajax-localidade" data-tipo="estado" data-nome="' + item.Nome + '" data-uf="' + item.Sigla + '">' + value + ' (' + item.Sigla + ')</span>';
						}
					}
				}
			};
			$(id).easyAutocomplete(options);
		}
	},

	print: {
		iniciar: function() {
			$('.button-print').on('click',function() {
				window.print();

				return false;
			});
		}
	},

	popup: {
		open: function() {
			$(".lista--autoavaliacao__botao-popup").on("click",function() {
				$(this)
					.text($(this).text() == "VER COMENTÁRIO" ? "OCULTAR COMENTÁRIO" : "VER COMENTÁRIO")
					.siblings(".lista--autoavaliacao__popup")
					.toggleClass("active");
			});
		}
	},

	/**
	 * checkboxSelecionarTodos
	 *
	 * @return void
	 */
	checkboxSelecionarTodos: function(id) {
		$(id).each(function(){
			var $this = $(this);

			$this.click(function(){
				var $label = $($this.data('label'));
				var $target = $($this.data('target'));

				if ( $this.is(':checked') == true ) {
					$label.text('Deselecionar todos');
					$target.prop('checked', true);
				} else {
					$label.text('Selecionar todos');
					$target.prop('checked', false);
				}					
			});
		});
	},

	/**
	 * checkboxSelecionarTodos
	 *
	 * @return void
	 */
	habilitarNotificar: function(checks, btn) {
		var $btn = $(btn);
		var $checks = $(checks);
		$btn.hide();

		$checks.click(function() {
			var qtdSelecionados = $(checks + ':checked').length;

			if ( qtdSelecionados > 0 ) {
				$btn.show();
			} else {
				$btn.hide();
			}
		});

	},


	/**
	* Compara a posição atual do scroll da página com a distância do elemento1 até o topo.
	* O elemento2 recebe uma classe modificadora.
	*
	* @return void
	* @param string identificador,elemento1,classe,elemento2
	* @author Alvino Rodrigues
	*/
	scroll: {
		obj: {},
		mapear: function(identificador,elemento1,classe,elemento2) {
			var top = $(elemento1).offset().top;

			base.scroll.obj[identificador] = {
				scrollPagina: false,
				distanciaElemento: top,
				distanciaPagina: 0,
				elemento1: elemento1,
				elemento2: elemento2,
				classe: classe
			};
		},

		scroll: function() {
			scrollPagina = false;
			window.onscroll = function() {
				scrollPagina = true;
			}

			// Recurso utilizado para diminuir o impacto do evento scroll na performance
			setInterval(function() {
				if(scrollPagina) {
					scrollPagina = false;

					for(var identificador in base.scroll.obj) {
						var distanciaPagina = base.scroll.obj[identificador]['distanciaPagina'];
						var distanciaElemento = base.scroll.obj[identificador]['distanciaElemento'];
						var elemento1 = base.scroll.obj[identificador]['elemento1'];
						var elemento2 = base.scroll.obj[identificador]['elemento2'];
						var classe = base.scroll.obj[identificador]['classe'];

						distanciaPagina = parseInt(window.pageYOffset, 10);
						distanciaElemento = (parseInt($(elemento1).offset().top, 10) - 100);

						distanciaPagina >= distanciaElemento ? $(elemento2).addClass(classe).siblings().removeClass(classe) : $(elemento2).removeClass(classe);
					}
				}
			}, 100);
		}
	},

	blocosQuestionario: {
		manipular: function() {
			$('.block-quiz-edit').on('click',function() {
				var $this = $(this),
				$parent = $this.parents('.block-quiz');

				$parent.find('.block-quiz__step').removeClass('active');
				$parent.find('.block-quiz__step-2').addClass('active');
			});

			$('.block-quiz-remove').on('click',function() {
				$(this).parents('.block-quiz').remove();
			});

			$('.form-block-quiz__cancel').on('click',function() {
				var $this = $(this),
				$parent = $this.parents('.block-quiz');

				$parent.find('.block-quiz__step').removeClass('active');
				$parent.find('.block-quiz__step-1').addClass('active');
			});
		}
	},	


	remover: function() {
		$('[data-action=remover-bloco]').each(function(){
			var $this = $(this);
			var $target = $( $this.data('target') );

			$this.unbind('click').bind('click', function(){
				$target.remove();
			});
		});
	},

    /**
	* uploadPreviewAjax
	*
	* @author Marco Malaquias
	*/
	uploadPreviewAjax: function() {
		$('[data-action=upload-preview-image]').each(function(){
			var $campo = $(this);

			$.uploadPreview({
				input_field: $campo.data('targetFile'),   // Default: .image-upload
				preview_box: $campo.data('target'),  // Default: .image-preview
				label_field: $campo.data('targetLabel'),    // Default: .image-label
				label_default: "Escolher",   // Default: Choose File
				label_selected: "Alterar",  // Default: Change File
				no_label: true                 // Default: false
			});
		});
	},

    /**
	* Funções usadas para controlar o questionário de "criando o questionário"
	*
	* @author Marco Malaquias
	*/
	questionarioAvaliacao: {


	    /**
		* Funções usadas para controlar o questionário de "criando o questionário"
		*
		* @author Marco Malaquias
		*/
		habilitarMostrarOcultar: function() {
			$('input[type=checkbox][data-action=habilitar-mostrar-ocultar').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$target.hide();

				$this.unbind('click').bind('click', function(){
					$(this).is(':checked') ? $target.show() : $target.hide();
				})

			});
		},

	    /**
		* Funções usadas para adicionar dependente
		*
		* @author Marco Malaquias
		*/
		adicionarDependente: function() {
			$('[data-action=adicionar-dependente').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){
					if ( $target.hasClass('hide') ) {
						$target.removeClass('hide');
						$this.html('REMOVER<br>DEPENDENTE');
					
					} else {
						$target.addClass('hide');
						$this.html('ADICIONAR<br>DEPENDENTE');
					}
				});
			});
		},

	    /**
		* Funções usadas para controlar o questionário de "criando o questionário"
		*
		* @author Marco Malaquias
		*/
		adicionarPergunta: function(form, box) {
			var $form = $(form);
			var $tipo = $form.find('select[name=tipo]');
			var $box = $(box);

			$form.submit(function(){
				$(this).attr('action', $tipo.val());
			});

			$form.ajaxForm({
				success: function(html){
					$box.prepend(html);
					base.questionarioAvaliacao.iniciarAposAjax();
				}
			});
		},

	    /**
		* inicializa a validação dos formulários
		*
		* @author Marco Malaquias
		*/
		iniciarValidacaoForms: function() {
			$.validate({modules : 'security'});
		},

	    /**
		* inicializa a validação dos formulários
		*
		* @author Marco Malaquias
		*/
		adicionarOpcaoMultiplaEscolha: function() {
			$('[data-action=adicionar-opcao-multipla-escolha').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){				
					$.get('assets/inc/box/questionario/box-dependente.php', function(html){
						$target.append(html);

						base.remover();	
						base.questionarioAvaliacao.adicionarDependente();
						base.radioCheckbox.change();
						base.radioCheckbox.verify();
						base.formulario.numberOnly();	
					});
				});
			});

			$('[data-action=adicionar-opcao-por-icone').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){				
					$.get('assets/inc/box/questionario/box-por-icone.php', function(html){
						$target.append(html);

						base.remover();	
						base.questionarioAvaliacao.adicionarDependente();
						base.radioCheckbox.change();
						base.radioCheckbox.verify();
						base.uploadPreviewAjax();
						base.formulario.numberOnly();	
					});
				});
			});
		},

	    /**
		* habilita envio via ajax
		*
		* @author Marco Malaquias
		*/
		enviarFormQuestaoAjax: function() {
			$('form[data-action=envio-questao-ajax]').ajaxForm({
				beforeSubmit: function(formData, $form, options) {
					var $box = $( $form.data('boxMensagem') );
					$box.hide();
				},
				success: function(responseText, statusText, xhr, $form){
					var $box = $( $form.data('boxMensagem') );
					$box.text( $form.data('mensagem') );
					$box.show();
				}				
			});
		},

	    /**
		* habilita envio via ajax
		*
		* @author Marco Malaquias
		*/
		ativarDragAndDop: function() {
			$(".form-quiz__block-list.sortable").sortable();
		},

	    /**
		* habilita envio via ajax
		*
		* @author Marco Malaquias
		*/
		removerBlocoConfirmacao: function() {
			$('[data-action=remover-bloco-confirmacao]').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){
					if ( confirm('Deseja realmente excluir?') ) {
						$target.remove();						
					}
				});
			});
		},

	    /**
		* adicionarSubtopicoMatriz
		*
		* @author Marco Malaquias
		*/
		adicionarSubtopicoMatriz: function() {
			$('[data-action=adicionar-subtopico-multipla]').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){				
					$.get('assets/inc/box/questionario/box-matriz-multipla-escolha.php', function(html){
						$target.append(html);

						base.remover();	
						base.radioCheckbox.change();
						base.radioCheckbox.verify();
						base.questionarioAvaliacao.adicionarOpcaoComPeso();
						base.questionarioAvaliacao.habilitarMostrarOcultar();
						base.formulario.numberOnly();	
					});
				});
			});

			$('[data-action=adicionar-subtopico-escala]').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){				
					$.get('assets/inc/box/questionario/box-matriz-escala.php', function(html){
						$target.append(html);

						base.remover();	
						base.radioCheckbox.change();
						base.radioCheckbox.verify();
						base.questionarioAvaliacao.habilitarMostrarOcultar();
						base.formulario.numberOnly();	
					});
				});
			});
		},

	    /**
		* dispararRespostaPorIcone
		*
		* @author Marco Malaquias
		*/
		dispararRespostaPorIcone: function() {
			$('[data-action=resposta-por-icone]').each(function(){
				var $this = $(this);
				var $targetIcone = $( $this.data('targetIcone') );
				var $targetTexto = $( $this.data('targetTexto') );

				$this.unbind('click').bind('click', function(){				
					if ( $this.is(':checked') ) {
						$targetIcone.removeClass('hide');
						$targetTexto.addClass('hide');
					
					} else {
						$targetIcone.addClass('hide');
						$targetTexto.removeClass('hide');
					}
				});
			});

		},

	    /**
		* adicionarOpcaoComPeso
		*
		* @author Marco Malaquias
		*/
		adicionarOpcaoComPeso: function() {
			$('[data-action=adicionar-opcao-peso]').each(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$this.unbind('click').bind('click', function(){				
					$.get('assets/inc/box/questionario/adicionar-opcao-peso.php', function(html){
						$target.append(html);

						base.remover();	
						base.radioCheckbox.change();
						base.radioCheckbox.verify();
						base.formulario.numberOnly();	
					});
				});
			});
		},		

	    /**
		* Funções usadas para controlar o questionário de "criando o questionário"
		*
		* @author Marco Malaquias
		*/
		iniciarAposAjax: function() {
			base.componente.adicionar();
			base.remover();
			base.radioCheckbox.change();
			base.radioCheckbox.verify();
			base.uploadPreviewAjax();	

			base.formulario.numberOnly();			
			
			base.questionarioAvaliacao.ativarDragAndDop();
			base.questionarioAvaliacao.removerBlocoConfirmacao();
			base.questionarioAvaliacao.habilitarMostrarOcultar();
			base.questionarioAvaliacao.iniciarValidacaoForms();
			base.questionarioAvaliacao.enviarFormQuestaoAjax();
			base.questionarioAvaliacao.adicionarDependente();
			base.questionarioAvaliacao.adicionarOpcaoMultiplaEscolha();
			base.questionarioAvaliacao.adicionarSubtopicoMatriz();
			base.questionarioAvaliacao.dispararRespostaPorIcone();			
		}



	},

	 /**
		* Monta slider"
		*
		* @author Marcos Furquim
		*/

	slider: {
		init: function () {
			$(".slider-box").each(function() {
				$sliderBox = $(this);
				
				var min = parseInt($sliderBox.attr("data-slider-min"));
				var max = parseInt($sliderBox.attr("data-slider-max"));
				var minActual = parseInt($sliderBox.attr("data-slider-min-actual"));
				var maxActual = parseInt($sliderBox.attr("data-slider-max-actual"));

				$sliderBox.children(".slider").slider({
				      range: true,
				      min: min,
				      max: max,
				      values: [ minActual, maxActual ],
				      slide: function( event, ui ) {
				        $(this).parent().find(".range-min").html("<b>" + ui.values[ 0 ] + "</b>");
				         $(this).parent().find(".range-max").html("<b>" + ui.values[ 1 ] + "</b>");
				    }
				});
				$sliderBox.children(".range-slider").find(".range-min").html( "<b>" + $sliderBox.children(".slider").slider( "values", 0 ) + "</b>");
				$sliderBox.children(".range-slider").find(".range-max").html( "<b>" + $sliderBox.children(".slider").slider( "values", 1 ) + "</b>");
			});
		}
	},

	kanban: {

	    /**
		* kanban de revisão
		*
		* @author Marco Malaquias
		*/		
		iniciarRevisao: function() {
			$('[data-action=ativar-kanban-revisao]').click(function(){
				var $this = $(this);
				var $sibling = $this.siblings(':first');
				var $target = $( $this.data('target') );

				$this.data('data-on', true);
				$sibling.data('data-on', false);

				$this.find('i').removeClass( $this.find('i').data('classOff') ).addClass( $this.find('i').data('classOn') );
				$sibling.find('i').removeClass( $sibling.find('i').data('classOn') ).addClass( $sibling.find('i').data('classOff') );

				if ( $this.data('up') ) {
					$target.find('input[type=text]').removeAttr('data-validation').removeAttr('data-validation-error-msg');
					if ( ! $target.hasClass('hide') ) {
						$target.addClass('hide');
					}

				} else {
					$target.find('input[type=text]')
						.attr('data-validation', 'required')
						.attr('data-validation-error-msg', 'Resposta obrigatória');

					if ( $target.hasClass('hide') ) {
						$target.removeClass('hide');
					}
				}

				return false;
			});
		},

	    /**
		* kanban de revisão
		*
		* @author Marco Malaquias
		*/		
		iniciarValidacao: function() {
			$('[data-action=ativar-kanban-validacao]').click(function(){
				var campos = 'input[type=text], input[type=checkbox], input[type=radio], select';
				var $this = $(this);
				var $sibling = $this.siblings(':first');
				var $target = $( $this.data('target') );
				var isCheckbox = $target.find(campos).attr('type') == 'checkbox';
				var isRadio = $target.find(campos).attr('type') == 'radio'

				var $divResposta2 = $this.parents('[data-type=resposta]').siblings('[data-type=resposta]:first');
				var $thumUp2 = $divResposta2.find('[data-action=ativar-kanban-validacao][data-up=true]');
				var $thumDown2 = $divResposta2.find('[data-action=ativar-kanban-validacao][data-up=false]');

				$this.data('data-on', true);
				$sibling.data('data-on', false);

				$this.find('i').removeClass( $this.find('i').data('classOff') ).addClass( $this.find('i').data('classOn') );
				$sibling.find('i').removeClass( $sibling.find('i').data('classOn') ).addClass( $sibling.find('i').data('classOff') );

				$target.find(campos)
					.removeAttr('data-validation')
					.removeAttr('data-validation-error-msg')
					.removeAttr('data-validation-qty');
				
				if ( ! $target.hasClass('hide') ) {
					$target.addClass('hide');
				}

				if ( $this.data('up') ) {
					$thumUp2.find('i').removeClass( $thumUp2.find('i').data('classOn') ).addClass( $thumUp2.find('i').data('classOff') );
					$thumDown2.find('i').removeClass( $thumDown2.find('i').data('classOff') ).addClass( $thumDown2.find('i').data('classOn') );
				}

				if ( ! $this.data('up') && $thumDown2.find('i').hasClass( $thumDown2.find('i').data('classOn') ) ) {
					if ( isCheckbox ) {
						$target.removeClass('hide')	
							.find(campos + ':first')
							.attr('data-validation', 'checkbox_group')
							.attr('data-validation-qty', 'min1')
							.attr('data-validation-error-msg', ' ');

					} else if ( isRadio ) {
						$target.removeClass('hide')	
							.find('input[type=radio]')
							.attr('data-validation', 'required')
							.attr('data-validation-error-msg', ' ');							

					} else {
						$target.removeClass('hide')	
							.find(campos)
							.attr('data-validation', 'required')
							.attr('data-validation-error-msg', 'Resposta obrigatória');				
					}
				}

				return false;
			});
		}

	},
	abas: {
		trocarAtivo: function() {
			$('.nav-tabs--type-b')
				.find('a')
				.on('click',function() {
					$(this).addClass('active').siblings('a').removeClass('active');
			});
		}
	},
	feedback: {
		sucesso: function(mensagem) {
			var div = $("<div class='alert alert-success'><span></span></div>").css({
		        "position": "fixed",
		        "top": "10%",
		        "left": "45%",
		        "z-index": "9999",
		        "display": "none"
		    }).appendTo("body");
		    div.children("span").html(mensagem);
		    div.fadeIn();
		    setTimeout(function () { div.fadeOut("", function () { div.remove(); }); }, 5000);
		},
		erro: function(mensagem) {
			var div = $("<div class='alert alert-danger'><span></span></div>").css({
		        "position": "fixed",
		        "top": "10%",
		        "left": "45%",
		        "z-index": "9999",
		        "display": "none"
		    }).appendTo("body");
		    div.children("span").html(mensagem);
		    div.fadeIn();
		    setTimeout(function () { div.fadeOut("", function () { div.remove(); }); }, 5000);

		}
	},
	carousel: {
		paginaCidadaoesDetalhe: function() {
			$('.block-profile__carousel').slick({
				infinite: false,
				slidesToShow: 1,
				slidesToScroll: 1,
				prevArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-previous"><i class="fa fa-angle-double-left" aria-hidden="true"></i><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-next"><i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="sr-only">Próximo</span></button>'
			});
		}

	}
}