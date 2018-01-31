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
		base.acessibilidade.manipularFonte();
		base.acessibilidade.altoContraste();
		base.acessibilidade.suporteGestos();
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
				modules : 'security, brazil, date'
			});
		},

	    /**
		* uploadPreview
		*
		* @author Marco Malaquias
		*/
		uploadPreview: function() {
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
		}
	},

	/**
	* Aumenta/Diminui o formulário de busca do topo da página
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

			$('.login-forgot').on('click',function() {
				$step.removeClass('active');
				$('.step-2').addClass('active');
			});
			$('.login-back').on('click',function() {
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
			var $body = $('body');

			$(button).on('click touchstart',function(e) {
				$(modal).addClass('active');
				$body.addClass('modal-background');

				e.stopPropagation();
			});

			$('.modal-block').on('click touchstart', function(e) {
				e.stopPropagation();
			});

			$body.on('click',function() {
				$('.modal-block').removeClass('active');
				$body.removeClass('modal-background');
			});
		}
	},

	grafico: {
		selecionar: function(alvo) {
			var $this;

			if (alvo == undefined) {
				var $elemento = $('[data-chart]');
			} else {
				var $elemento = $(alvo).find('[data-chart]');
			}

			if ($elemento.length > 0) {
				$elemento.each(function() {
					$this = $(this),
					d3this = d3.select(this);

					switch ($this.data('chart')) {
						case 'donut':
							base.grafico.construirDonut($this, d3this);
							break;
						case 'barras-horizontais-empilhadas':
							base.grafico.construirBarrasHorizontaisEmpilhadas($this, d3this);
							break;
						case 'barras-verticais':
							base.grafico.construirBarrasVerticais($this, d3this);
							break;
						case 'progress-donut':
							base.grafico.construirBarraProgressoDonut($this, d3this);
							break;
						case 'value-center-donut':
							base.grafico.construirValueCenterDonut($this, d3this);
							break;
						case 'barra-horizontal-nota':
							base.grafico.construirBarraHorizontalNota($this, d3this);
							break;
						case 'linhas':
							base.grafico.construirLinhas($this, d3this);
							break;
					}
				});
			}
		},

		construirBarrasVerticais: function($elemento,d3elemento) {
			lista = [];
			var $this = $elemento,
				altura = $this.attr('data-height'),
				moeda = $this.attr('data-moeda'),
				labelY = $this.attr('data-label-y'),
				tabela = JSON.parse($this.attr('data-table-json'));

			// Define a altura
			if (altura == null) {
				altura = base.isMobile ? 350: 500;
			}

			// Cria uma lista com os dados
			if (typeof(tabela.items[0].meta) != 'undefined' && tabela.items[0].meta != null && tabela.items[0].meta != '') {
				for (i = 0; i < tabela.items.length; i++) {
					lista[i] = {
						'label': tabela.items[i].label,
						'value': base.moeda.converterParaFloat(tabela.items[i].valor),
						'meta': base.moeda.converterParaFloat(tabela.items[i].meta),
						'paleta': tabela.items[i].paleta,
						'json': tabela.items[i],
						'tooltip': tabela.items[i].tooltip,
						'urlAjax': tabela.items[i].hasOwnProperty('urlAjax') ? tabela.items[i].urlAjax : ''
					}
				}
			} else {
				for (i = 0; i < tabela.items.length; i++) {
					lista[i] = {
						'label': tabela.items[i].label,
						'value': base.moeda.converterParaFloat(tabela.items[i].valor),
						'paleta': tabela.items[i].paleta,
						'json': tabela.items[i],
						'tooltip': tabela.items[i].tooltip,
						'urlAjax': tabela.items[i].hasOwnProperty('urlAjax') ? tabela.items[i].urlAjax : ''
					};
				}
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

			// Calcula se o valor das metas excede o valor das barras. O maior valor será calculado para a largura dos elementos do gráfico
			var valorMaximoBarras,
				valorMaximoMetas,
				valorMaximoTotal,
				porcentagem,
				valorMaximoPorcentagem;

			valorMaximoBarras = d3.max(lista, function(d) {
				return d.value;
			});
			valorMaximoMetas = d3.max(lista, function(d) {
				return d.meta;
			});
			if (valorMaximoMetas != undefined) {
				valorMaximoTotal = valorMaximoMetas > valorMaximoBarras ? valorMaximoMetas : valorMaximoBarras;
			} else if (tabela.meta_unica != undefined) {
				valorMaximoTotal = base.moeda.converterParaFloat(tabela.meta_unica) > valorMaximoBarras ? base.moeda.converterParaFloat(tabela.meta_unica) : valorMaximoBarras;
			} else {
				valorMaximoTotal = valorMaximoBarras;
			}
			porcentagem = (parseInt(valorMaximoTotal, 10) * 10) / 100;
			valorMaximoPorcentagem = parseInt(valorMaximoTotal, 10) + porcentagem;

			// Define opções gerais
			var width = parseInt(d3elemento.style('width'), 10);
			var margin = {
					top: 20,
					right: 30,
					bottom: 50,
					left: 60
				},
				height = altura,
				width = width - margin.left - margin.right,
				height = height - margin.top - margin.bottom,
				xScale = d3.scale.ordinal().rangeRoundBands([0, width], .1).domain(lista.map(function(d) {
					return d.label;
				})),
				yScale = d3.scale.linear()
					.range([height, 0])
					.domain([0, valorMaximoPorcentagem]),
				xAxis = d3.svg
					.axis()
					.scale(xScale)
					.orient("bottom"),
				labelY = labelY != undefined ? labelY : 'R$';

			yAxis = d3.svg
				.axis()
				.scale(yScale)
				.ticks(5)
				.orient("left");

			// Insere o SVG no DOM
			var svgContainer = d3elemento
				.append("svg")
				.attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom)
				.attr("class", "grafico-barras-verticais")
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			// Insere os eixos X e Y
			var xAxis_g = svgContainer
				.append("g")
				.attr("class", "x axis")
				.attr("transform", "translate(0," + (height) + ")")
				.call(xAxis);
			var yAxis_g = svgContainer
				.append("g")
				.attr("class", "y axis")
				.call(yAxis)
				.append("text")
				.attr("transform", "rotate(-90)")
				.attr("y", 6)
				.attr("dy", ".71em")
				.style("text-anchor", "end");

			//Confere o maior valor entre as fatias
			var valorMaximoLista = d3.max(lista, function(d) {
				return d.value;
			});

			// Insere as barras
			svgContainer.selectAll(".fatia")
				.data(lista)
				.enter()
				.append("rect")
				.attr("class", "fatia")
				.attr("fill", function(d, i) {
					var cor = base.cor.selecionar(i, d.paleta);

					$elemento
						.parents('.block-graphic')
						.find('.block-graphic__footer')
						.find('ul')
						.append('<li><i aria-hidden="true" style="background-color: ' + cor + ' !important;"></i><strong>' + d.label + '</strong></li>');

					return cor;
				})
				.attr("stroke", "#000")
				.attr("stroke-width", ".5")
				.attr("data-json", function(d, i) {
					return JSON.stringify(d.json);
				})
				.attr("width", function(d) {
					// Calculando as margens de cada lado de cada barra
					var larguraMargens = lista.length * 20;

					// Divide a largura total do eixo X pelo número de barras e de margens
					larguraBarrasX = (width - larguraMargens) / lista.length;

					larguraBarrasX = larguraBarrasX > 90 ? 90 : larguraBarrasX;

					return larguraBarrasX;
				})
				.attr("x", function(d,i) {
					var posicaoX = 0;

					// Se a largura for a mínima, centraliza as barras
					if (larguraBarrasX <= 90) {
						var margemAutomatica = (width - ((lista.length * larguraBarrasX) + ((lista.length - 1) * 10))) / 2;

						if (i == 0) {
							posicaoX = margemAutomatica;
						} else if(i > 0) {

							posicaoX = margemAutomatica + (i * larguraBarrasX) + (i * 10);
						}
					} else {
						if (i == 0) {
							posicaoX = 10;
						} else if(i > 0) {
							posicaoX = (i * larguraBarrasX) + (i * 20);
						}
					}

					return posicaoX;
				})
				.attr("y", height)
				.attr("height", 0)
				// Exibe o tooltip ao passar o mouse nas barras
				.on("mouseover", function(valor) {
					if (typeof(valor.tooltip) != 'undefined') {
						d3.select('body')
							.append('span')
							.attr('class', 'label-tooltip')
							.html(valor.json.tooltip);
					}

					base.tooltip.posicionar();
				})
				// Remove o tooltip ao retirar o mouse das barras
				.on("mouseout", function(valor) {
					d3.select('.label-tooltip').remove();
				})
				.transition()
				.delay(function(d, i) {
					return i * 100;
				})
				.duration(1000)
				.ease('quad-in-out')
				.attr("y", function(d) {
					return yScale(d.value);
				})
				.attr("height", function(d) {
					return height - yScale(d.value);
				});

			// // Insere a meta unica
			// if ((tabela.meta_unica != null) && (tabela.meta_unica != '')) {
			// 	svgContainer.selectAll(".meta-unica")
			// 		.data(tabela.meta_unica)
			// 		.enter()
			// 		.append("rect")
			// 		.attr("class", "meta-unica")
			// 		.attr("fill", "#666")
			// 		.attr("border", 1)
			// 		.style("stroke", '#fff')
			// 		.attr("width", width)
			// 		.attr("height", 6)
			// 		.attr("x", 0)
			// 		.attr("y", function(d) {
			// 			return yScale(base.moeda.converterParaFloat(tabela.meta_unica));
			// 		})
			// 		// Exibe o tooltip ao passar o mouse na meta única
			// 		.on("mouseover", function(valor) {
			// 			if (tabela.metaLabel != null && typeof(tabela.metaLabel) != 'undefined') {
			// 				var metaText = tabela.metaLabel;
			// 			}else{
			// 				var metaText = '<span>Valor</span><strong>' + tabela.meta_unica + '</strong>';
			// 			}


			// 			d3.select(this)
			// 				.attr("fill", "#fff")
			// 				.style("stroke", '#000');
			// 			d3.select('body')
			// 				.append('span')
			// 				.attr('class', 'label-tooltip')
			// 				.html(metaText);

			// 			base.tooltip.posicionar(metaText);
			// 		})
			// 		// Remove o tooltip ao retirar o mouse das metas
			// 		.on("mouseout", function(valor) {
			// 			d3.select(this)
			// 				.attr("fill", "#666")
			// 				.style("stroke", '#fff');
			// 			d3.select('.label-tooltip').remove();
			// 		});
			// }

			// // Insere as linhas das metas
			// if ((tabela.items[0].meta != null) && (tabela.items[0].meta != '')) {
			// 	svgContainer.selectAll(".meta")
			// 		.data(lista)
			// 		.enter()
			// 		.append("rect")
			// 		.attr("class", "meta")
			// 		.attr("fill", "#666")
			// 		.attr("border", 1)
			// 		.style("stroke", '#fff')
			// 		.attr("height", 6)
			// 		.attr("width", function(d) {
			// 			// Calculando as margens de cada lado de cada barra
			// 			var larguraMargens = lista.length * 20;

			// 			// Divide a largura total do eixo X pelo número de barras e de margens
			// 			larguraBarrasX = (width - larguraMargens) / lista.length;

			// 			larguraBarrasX = larguraBarrasX > 90 ? 90 : larguraBarrasX;

			// 			return (larguraBarrasX + 6);
			// 		})
			// 		.attr("x", function(d,i) {
			// 			var posicaoX = 0;

			// 			// Se a largura for a mínima, centraliza as barras
			// 			if (larguraBarrasX <= 90) {
			// 				var margemAutomatica = (width - ((lista.length * larguraBarrasX) + ((lista.length - 1) * 10))) / 2;

			// 				if (i == 0) {
			// 					posicaoX = margemAutomatica;
			// 				} else if(i > 0) {
			// 					posicaoX = margemAutomatica + (i * larguraBarrasX) + (i * 10);
			// 				}
			// 			} else {
			// 				if (i == 0) {
			// 					posicaoX = 10;
			// 				} else if(i > 0) {
			// 					posicaoX = (i * larguraBarrasX) + (i * 20);
			// 				}
			// 			}

			// 			return (posicaoX - 3);
			// 		})
			// 		.attr("y", function(d,i) {
			// 			return yScale(base.moeda.converterParaFloat(tabela.items[i].meta));
			// 		})
			// 		// Exibe o tooltip ao passar o mouse na meta única
			// 		.on("mouseover", function(valor,i) {

			// 			if (valor.json.metaLabel != null && typeof(valor.json.metaLabel) != 'undefined') {
			// 				var metaText = valor.json.metaLabel;
			// 			}else{
			// 				var metaText = '<span>Situação</span><strong>Inicial (L.O.A.)</strong><span>Valor inicial</span><strong>' + tabela.items[i].meta + '</strong>';
			// 			}


			// 			d3.select(this)
			// 				.attr("fill", "#fff")
			// 				.style("stroke", '#000');
			// 			d3.select('body')
			// 				.append('span')
			// 				.attr('class', 'label-tooltip')
			// 				.html(metaText);

			// 			base.tooltip.posicionar(metaText);
			// 		})
			// 		// Remove o tooltip ao retirar o mouse das metas
			// 		.on("mouseout", function(valor) {
			// 			d3.select(this)
			// 				.attr("fill", "#666")
			// 				.style("stroke", '#fff');
			// 			d3.select('.label-tooltip').remove();
			// 		});
			// }

			// Remove o tooltip na rolagem (Solução para mobile)
			d3.select(window).on('scroll', function() {
				d3.select('.label-tooltip').remove();
			});

			/*** RESPONSIVIDADE ***/
			var throttleTimerVertical; // timer para limitar calculos
			d3.select(window).on('resize.barrasVerticais', function() {
				// // verifica se ja existe timer
				// if (typeof throttleTimerVertical != "undefined" && throttleTimerVertical != null) {
				// 	window.clearTimeout(throttleTimerVertical);
				// }
				// // seta timer para limitar calculos
				// throttleTimerVertical = window.setTimeout(function() {
					d3.selectAll('.grafico-barras-verticais')
						.each(function() {
							// Pegando e calculando a largura
							var width = parseInt(d3.select(this.parentNode).style('width'), 10);
							width = width - margin.left - margin.right;

							// Atualização do eixo X
							var xScale = d3.scale.ordinal().rangeRoundBands([0, width], .1).domain(
								d3.range(d3.select(this).selectAll('.fatia').size())
							);
							var xAxis = d3.svg
								.axis()
								.scale(xScale)
								.orient("bottom");

							d3.select(this).select('.x.axis').call(xAxis.orient('bottom'));

							// Atualização da largura do SVG
							d3.select(this).attr('width', (width + margin.left + margin.right) + 'px');

							// // Atualização da meta única
							// d3.select(this).selectAll('.meta-unica')
							// 	.attr("width", width);

							// // Atualização da meta
							// d3.select(this).selectAll('.meta')
							// 	.attr("width", function(d) {
							// 		numeroMetas = $(this).parents('.grafico-barras-verticais').find('.meta').length;
							// 		// Calculando as margens de cada lado de cada barra
							// 		var larguraMargens = numeroMetas * 20;

							// 		// Divide a largura total do eixo X pelo número de barras e de margens
							// 		larguraBarrasX = (width - larguraMargens) / numeroMetas;

							// 		larguraBarrasX = larguraBarrasX > 90 ? 90 : larguraBarrasX;

							// 		return larguraBarrasX;
							// 	})
							// 	.attr("x", function(d,i) {
							// 		var posicaoX = 0;

							// 		// Se a largura for a mínima, centraliza as barras
							// 		if (larguraBarrasX <= 90) {
							// 			var margemAutomatica = (width - ((numeroMetas * larguraBarrasX) + ((numeroMetas - 1) * 10))) / 2;

							// 			if (i == 0) {
							// 				posicaoX = margemAutomatica;
							// 			} else if(i > 0) {
							// 				posicaoX = margemAutomatica + (i * larguraBarrasX) + (i * 10);
							// 			}
							// 		} else {
							// 			if (i == 0) {
							// 				posicaoX = 10;
							// 			} else if(i > 0) {
							// 				posicaoX = (i * larguraBarrasX) + (i * 20);
							// 			}
							// 		}

							// 		return posicaoX;
							// 	});

							// Atualização das barras
							d3.select(this).selectAll('.fatia')
								.attr("width", function(d) {
									numeroFatias = $(this).parents('.grafico-barras-verticais').find('.fatia').length;
									// Calculando as margens de cada lado de cada barra
									var larguraMargens = numeroFatias * 20;

									// Divide a largura total do eixo X pelo número de barras e de margens
									larguraBarrasX = (width - larguraMargens) / numeroFatias;

									larguraBarrasX = larguraBarrasX > 90 ? 90 : larguraBarrasX;

									return larguraBarrasX;
								})
								.attr("x", function(d,i) {
									var posicaoX = 0;

									// Se a largura for a mínima, centraliza as barras
									if (larguraBarrasX <= 90) {
										var margemAutomatica = (width - ((numeroFatias * larguraBarrasX) + ((numeroFatias - 1) * 10))) / 2;

										if (i == 0) {
											posicaoX = margemAutomatica;
										} else if(i > 0) {
											posicaoX = margemAutomatica + (i * larguraBarrasX) + (i * 10);
										}
									} else {
										if (i == 0) {
											posicaoX = 10;
										} else if(i > 0) {
											posicaoX = (i * larguraBarrasX) + (i * 20);
										}
									}

									return posicaoX;
								});
						});
				//}, 100);
			});
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

		construirValueCenterDonut: function($elemento,d3elemento) {
			var tabela = JSON.parse($elemento.attr('data-table-json')),
			lista = [],
			altura = 400;

			// Dados
			for (i = 0; i < tabela.length; i++) {
				lista[i] = {
					'label': base.label.formatar(tabela[i].label),
					'value': base.moeda.converterParaFloat(tabela[i].valor),
					'json': tabela[i],
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
			donutWidth = 36;

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
			}).sort(null),
			arc = d3.svg.arc().innerRadius(radius - donutWidth).outerRadius(radius),
			arcs = vis.selectAll("g.slice")
			.data(pie)
			.enter()
			.append("svg:g")
			.attr("class", "slice")
			.attr('stroke', '#ddd')
			.attr('stroke-width', 1);

			// Insere as fatias
			arcs.append("svg:path")
			.attr("fill", function(d, i) {
				var cor = base.cor.selecionar(i, d.data.paleta);

				$elemento
					.parents('.block-graphic')
					.find('.block-graphic__footer')
					.find('ul')
					.append('<li><i aria-hidden="true" style="background-color: ' + cor + ' !important;"></i><strong>' + d.data.label + '</strong></li>');

				return cor;
			})
			// Exibe o tooltip ao passar o mouse nas barras
			.on("mouseover", function(valor,i) {
				if (typeof(valor.data.label) != 'undefined') {
					// Calcula a porcentagem a ser exibida nas fatias
					var total = 0,
					porcentagem = 0;

					for (var inc = 0; inc < lista.length; inc++) {
						total = total + lista[inc].value;
					}

					porcentagem = parseFloat((lista[i].value * 100) / total).toFixed(0);

					d3.select('body')
					.append('span')
					.attr('class', 'label-tooltip')
					.html('<b>' + valor.data.label + ':</b> ' + porcentagem + '%');
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
			.attr('fill', '#416aad')
			.attr('stroke', 'none')
			.attr('font-size', '73')
			.attr('text-anchor', 'middle')
			.attr('alignment-baseline', 'middle')
			.style('font-weight','bold')
			.text(function(d, i) {
				if (d.data.label == "Possuem") {
					// Calcula a porcentagem a ser exibida nas fatias
					var total = 0,
					porcentagem = 0;

					for (var inc = 0; inc < lista.length; inc++) {
						total = total + lista[inc].value;
					}

					porcentagem = parseFloat((lista[i].value * 100) / total).toFixed(0);

					return porcentagem + '%';
				}
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
			width = width - margin.left - margin.right,
			height = alturaSVG;

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
				.attr('height', 'auto')
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
			}).sort(null),
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
					$elemento.append('<span>' + d.data.label + '</span>');
				} else {
					cor = "#ededed";
				}


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
		},

		construirBarraHorizontalNota: function($elemento,d3elemento) {
			var lista = [];
			var tabela = JSON.parse($elemento.attr('data-table-json'));

			// Cria uma lista com os dados
			for (i = 0; i < tabela.length; i++) {
				lista[i] = {
					'value': base.moeda.converterParaFloat(tabela[i].valor,1),
					'json': tabela[i],
				};
			}

			// Define opções gerais
			var margin = {
				top: 1,
				right: 10,
				bottom: 20,
				left: 5
			},
			width = parseInt(d3elemento.style('width'), 10),
			height = 24;

			width = width - margin.left - margin.right;

			// se escondido nao pode ser negativo
			if (width <= 0)
				width = 0;

			var xScale = d3.scale.linear().range([0, width], .1).domain([0, 10]),
			yScale = d3.scale.linear().range([0, 24]),
			xAxis = d3.svg
			.axis()
			.scale(xScale)
			.ticks(6)
			.orient("bottom"),
			yAxis = d3.svg
			.axis()
			.scale(yScale)
			.orient("left");

			// Apaga o conteúdo da div pai
			//d3elemento.html('');

			// Insere o SVG no DOM
			var svgContainer = d3elemento
			.append("svg")
			.attr("width", width + margin.left + margin.right)
			.attr("height", height + margin.top + margin.bottom)
			.attr("class","barra-horizontal-nota")
			.append("g")
			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			// Insere os eixos X e Y
			var xAxis_g = svgContainer
			.append("g")
			.attr("class", "x axis")
			.attr("transform", "translate(0," + height + ")")
			.call(xAxis);
			var yAxis_g = svgContainer
			.append("g")
			.attr("class", "y axis")
			.call(yAxis);

			// Insere a borda
			svgContainer
			.append("rect")
			.attr("class", "borda")
			.attr("width", (width + 10))
			.attr("height", 22)
			.attr("x", 1)
			.attr("y", 1)
			.attr("rx", 13)
			.attr("ry", 13)
			.attr("transform", "translate(-6,0)");

			// Insere a barra
			svgContainer.selectAll(".fatia")
			.data(lista)
			.enter()
			.append("rect")
			.attr("class", "fatia")
			.attr("data-json", function(d, i) {
				return JSON.stringify(d.json);
			})
			.attr("width", 0)
			.attr("height", 24)
			.attr("x", 1)
			.attr("y", 0)
			.attr("rx", 13)
			.attr("ry", 13)
			.attr("transform", "translate(-6,0)")
			.transition()
			.delay(function(d, i) {
				return i * 100;
			})
			.duration(1000)
			.ease('quad-in-out')
			.attr("width", function(d) {
				return (xScale(d.value) + 10);
			});

			// Insere a nota
			//d3elemento.append("div").attr("class","block-rating__nota").html('<span>Nota</span><strong>' + lista[0].value + '</strong>');

			/*** RESPONSIVIDADE ***/
			var throttleTimerHorizontal; // timer para limitar calculos
			d3.select(window).on('resize.barraHorizontalNota', function() {
				// verifica se ja existe timer
				if (typeof throttleTimerHorizontal != "undefined" && throttleTimerHorizontal != null) {
					window.clearTimeout(throttleTimerHorizontal);
				}
				// seta timer para limitar calculos
				throttleTimerHorizontal = window.setTimeout(function() {
					d3.selectAll('.barra-horizontal-nota')
					.each(function() {
							// Pegando e calculando a largura
							var width = parseInt(d3.select(this.parentNode).style('width'), 10);

							if (width) {
								if (width <= 100) {
									width = $(this.parentNode).parents('div').width();
								}
								width = width - margin.left - margin.right;
								// se escondido nao pode ser negativo
								if (width <= 0)
									width = 0;

								// Atualização do eixo X
								var xScale = d3.scale.linear().range([0, width], .1).domain([0, 10]),
								xAxis = d3.svg
								.axis()
								.scale(xScale)
								.ticks(6)
								.orient("bottom");
								d3.select(this).select('.x.axis').call(xAxis);

								// Atualização da largura do SVG
								d3.select(this).attr('width', (width + margin.left + margin.right));

								// Atualização da borda
								d3.select(this).selectAll(".borda").attr("width", width + 10);

								// Atualização da barra
								d3.select(this).selectAll('.fatia').attr("width", function(d) {
									return (xScale(d.value) + 10);
								});
							}
						});
				}, 100);
			});
		},

		construirLinhas: function($elemento,d3elemento) {
			var tabela = JSON.parse($elemento.attr('data-table-json')),
				iniciado = false,
				lista = [],
				altura = 350,
				contador = 0;

			// Cria uma lista com os dados
			var parametrosEixoX = new Array();
			for (i in tabela) {
				for (j = 0; j < tabela[i].length; j++) {
					var textoLinha = i.split("linha");

					lista[contador] = {
						'linha': parseInt(textoLinha[1]),
						'label': tabela[i][j].label,
						'x': parseInt(tabela[i][j].x),
						'y': parseFloat(tabela[i][j].y),
						'labelx': tabela[i][j].labelx,
						'tooltip': tabela[i][j].tooltip
					};

					contador = contador + 1;

					if (jQuery.inArray(tabela[i][j].x, parametrosEixoX) == -1) {
						parametrosEixoX.push(tabela[i][j].x);
					}
				}
			}

			// Reorganiza os dados de acordo com o linha
			var dataGroup = d3
			.nest()
			.key(function(d) {
				return d.linha;
			})
			.entries(lista);

			// Dados Gerais
			var margin = {
				top: 50,
				right: 20,
				bottom: 50,
				left: 50
			},
			width = parseInt(d3elemento.style('width'), 10),
			height = altura;

			if ((isNaN(width)) || (width <= 100)) {
				width = $elemento.parents('.tab-content').width();
			}

			width = width - margin.right;

			// Insere o SVG no DOM
			var svgContainer = d3elemento
								.append("svg")
								.attr("width", width)
								.attr("height", height)
								.attr('class', 'grafico-linhas-svg');

			// Se já foi criado, o gráfico não faz mais a animação inicial
			if (iniciado == true) { svgContainer.attr('class','grafico-linhas-svg iniciado'); }

			var xScale = d3.scale.linear().range([margin.left, width - margin.right]).domain([d3.min(lista, function(d) {
				return parseInt(d.x, 10);
			}), d3.max(lista, function(d) {
				return parseInt(d.x, 10);
			})]);

			var yScale = d3.scale.linear().range([height - margin.top, margin.bottom]).domain([0, d3.max(lista, function(d) {
				var porcentagem = (parseInt(d.y, 10) * 10) / 100,
				valorMaximo = parseInt(d.y, 10) + porcentagem;

				return valorMaximo;
			})]),
			xAxis = d3.svg
			.axis()
			.scale(xScale)
			.ticks(dataGroup[0].values.length-1)
			.tickFormat(function(i) {
				return dataGroup[0].values[i-1].labelx;
			}),
			yAxis = d3.svg
			.axis()
			.scale(yScale)
			.ticks(dataGroup[0].values.length/2)
			.orient("left");

			// Largura do SVG
			var svgWidth = $elemento.width();

			if ((isNaN(svgWidth)) || (svgWidth <= 100)) {
				svgWidth = $elemento.parents('.tab-content').width();
			}

			$elemento.find('.grafico-linhas-svg').attr("width", svgWidth);

			// Eixos
			svgContainer.append("svg:g").attr("class", "axis xAxis").attr("transform", "translate(0," + (height - margin.bottom) + ")").call(xAxis);
			svgContainer.append("svg:g").attr("class", "axis yAxis").attr("transform", "translate(" + (margin.left) + ",0)").call(yAxis);

			// Linhas
			var lineGen = d3
			.svg
			.line()
			.x(function(d) {
				return xScale(d.x);
			}).y(function(d) {
				return yScale(d.y);
			});

			$elemento
				.find('.grafico-linhas-svg')
				.find('.yAxis')
				.find('.tick')
				.find('line')
				.each(function() {
					d3.select(this)
						.attr('x1',width-margin.left-margin.right)
						.attr('y1','0');
			});

			var estrutura = '<div class="block-graphic__footer"><ul>';
			dataGroup.forEach(function(d, i) {
				var cor = base.cor.selecionar(i);
				var display = 'block';

				// Inserindo a legenda
				estrutura += '<li><i aria-hidden="true" style="background-color: ' + cor + ' !important;"></i><strong>' + d.values[i].label + '</strong></li>';

				// Adicionando as linhas
				svgContainer
				.append('svg:path')
				.attr("d", lineGen(d.values))
				.attr('stroke', cor)
				.attr('stroke-width', 2)
				.attr('id', 'grafico-linha-' + i)
				.attr('class', 'linhas-valoradas')
				.attr('data-linha', d.values[i].linha)
				.style("display", display)
				.attr('fill', 'none');

				// Adicionando os pontos e exibindo/removendo o tooltip ao passar o mouse nos pontos
				for (inc = 0; inc < d.values.length; inc++) {
					svgContainer
					.append("circle")
					.attr("cx", xScale(d.values[inc].x))
					.attr("cy", yScale(d.values[inc].y))
					.attr("r", 5)
					.attr('id', 'grafico-ponto-' + i + '-' + d.values[inc].x)
					.attr('class', 'pontos-valorados ponto-' + i)
					.attr('data-linha', d.values[inc].linha)
					.attr('data-tooltip', d.values[inc].tooltip)
					.attr('data-json', JSON.stringify( d.values[inc].json ))
					.attr("fill", cor)
					.style("display", display)
					.on('mouseover', function() {
						var id = d3.select(this)[0][0].id,
						$alvo = $('#' + id),
						linha = $alvo.attr('data-linha'),
						tooltip = $alvo.attr('data-tooltip');

						d3.select('body')
						.append('span')
						.attr('class', 'label-tooltip')
						.html(tooltip);

						base.tooltip.posicionar();
					})
					.on('mouseout', function() {
						d3.select('.label-tooltip').remove();
					});
				}
			});

			estrutura += '</ul></div>';
			$elemento.append(estrutura);

			// Na primeira vez, executa a animação
			if (iniciado == false) {
				// cortina branca
				var curtain = svgContainer.append('rect')
				.attr('x', -1 * (width))
				.attr('y', -1 * (altura-margin.bottom))
				.attr('height', altura-margin.bottom)
				.attr('width', width-margin.left)
				.attr('class', 'curtain')
				.attr('transform', 'rotate(180)')
				.style('fill', '#ffffff')

				//transicao
				var t = svgContainer.transition()
				.delay(550)
				.duration(1200)
				.ease('linear')
				.each('end', function() {
					svgContainer.selectAll("svg circle, svg .linha-valorada, svg .linha-fixa")
					.transition()
					.style('opacity', 1)
				})

				t.select('rect.curtain')
				.attr('width', 0).
				remove();

				iniciado = true;
			}

			// Efeito hover linhas
			$('.linhas-valoradas').on('mouseover',function() {
				var $this = $(this),
					idlinha = $this.attr('data-linha');

				$this
					.parents('svg')
					.find('.pontos-valorados[data-linha="' + idlinha + '"]')
					.siblings('.pontos-valorados')
					.attr('fill-opacity','.1')
					.end()
					.attr('fill-opacity','1');

				$this
					.siblings('.linhas-valoradas')
					.attr('opacity','.1')
					.end()
					.attr('opacity','1');
			}).on('mouseout',function() {
				$('.pontos-valorados').removeAttr('fill-opacity');
				$('.linhas-valoradas').removeAttr('opacity');
			});

			// Efeito hover bolinhas
			$('.pontos-valorados').on('mouseover',function() {
				var $this = $(this),
					idlinha = $this.attr('data-linha');

				$this
					.parents('svg')
					.find('.pontos-valorados[data-linha="' + idlinha + '"]')
					.siblings('.pontos-valorados')
					.attr('fill-opacity','.1')
					.end()
					.attr('fill-opacity','1');

				$this
					.parents('svg')
					.find('.linhas-valoradas[data-linha="' + idlinha + '"]')
					.siblings('.linhas-valoradas')
					.attr('opacity','.1')
					.end()
					.attr('opacity','1');
			}).on('mouseout',function() {
				$('.pontos-valorados').removeAttr('fill-opacity');
				$('.linhas-valoradas').removeAttr('opacity');
			});

			// Remove o tooltip na rolagem (Solução para mobile)
			d3.select(window).on('scroll', function() {
				d3.select('.label-tooltip').remove();
			});

			var largura = 0;
			d3.select(window).on('resize.linhas', function() {
				d3.selectAll('.grafico-linhas-svg').each(function() {
					var $this = $(this).parent(),
					d3this = d3.select(this.parentNode);

					$this.html('');

					var tabela = JSON.parse($this.attr('data-table-json')),
						iniciado = false,
						lista = [],
						altura = 350,
						contador = 0;

					// Cria uma lista com os dados
					var parametrosEixoX = new Array();
					for (i in tabela) {
						for (j = 0; j < tabela[i].length; j++) {
							var textoLinha = i.split("linha");

							lista[contador] = {
								'linha': parseInt(textoLinha[1]),
								'label': tabela[i][j].label,
								'x': parseInt(tabela[i][j].x),
								'y': parseFloat(tabela[i][j].y),
								'labelx': tabela[i][j].labelx,
								'tooltip': tabela[i][j].tooltip
							};

							contador = contador + 1;

							if (jQuery.inArray(tabela[i][j].x, parametrosEixoX) == -1) {
								parametrosEixoX.push(tabela[i][j].x);
							}
						}
					}

					// Reorganiza os dados de acordo com o linha
					var dataGroup = d3
					.nest()
					.key(function(d) {
						return d.linha;
					})
					.entries(lista);

					// Dados Gerais
					var margin = {
						top: 50,
						right: 20,
						bottom: 50,
						left: 50
					},
					width = parseInt(d3this.style('width'), 10),
					height = altura;

					if ((isNaN(width)) || (width <= 100)) {
						width = $this.parents('.tab-content').width();
					}

					width = width - margin.right;

					// Insere o SVG no DOM
					var svgContainer = d3this
										.append("svg")
										.attr("width", width)
										.attr("height", height)
										.attr('class', 'grafico-linhas-svg');

					var xScale = d3.scale.linear().range([margin.left, width - margin.right]).domain([d3.min(lista, function(d) {
						return parseInt(d.x, 10);
					}), d3.max(lista, function(d) {
						return parseInt(d.x, 10);
					})]);

					var yScale = d3.scale.linear().range([height - margin.top, margin.bottom]).domain([0, d3.max(lista, function(d) {
						var porcentagem = (parseInt(d.y, 10) * 10) / 100,
						valorMaximo = parseInt(d.y, 10) + porcentagem;

						return valorMaximo;
					})]),
					xAxis = d3.svg
					.axis()
					.scale(xScale)
					.ticks(dataGroup[0].values.length-1)
					.tickFormat(function(i) {
						return dataGroup[0].values[i-1].labelx;
					}),
					yAxis = d3.svg
					.axis()
					.scale(yScale)
					.ticks(dataGroup[0].values.length/2)
					.orient("left");

					// Largura do SVG
					var svgWidth = $this.width();

					if ((isNaN(svgWidth)) || (svgWidth <= 100)) {
						svgWidth = $this.parents('.tab-content').width();
					}

					$this.find('.grafico-linhas-svg').attr("width", svgWidth);

					// Eixos
					svgContainer.append("svg:g").attr("class", "axis xAxis").attr("transform", "translate(0," + (height - margin.bottom) + ")").call(xAxis);
					svgContainer.append("svg:g").attr("class", "axis yAxis").attr("transform", "translate(" + (margin.left) + ",0)").call(yAxis);

					// Linhas
					var lineGen = d3
					.svg
					.line()
					.x(function(d) {
						return xScale(d.x);
					}).y(function(d) {
						return yScale(d.y);
					});

					$this
						.find('.grafico-linhas-svg')
						.find('.yAxis')
						.find('.tick')
						.find('line')
						.each(function() {
							d3.select(this)
								.attr('x1',width-margin.left-margin.right)
								.attr('y1','0');
					});

					var estrutura = '<div class="block-graphic__footer"><ul>';
					dataGroup.forEach(function(d, i) {
						var cor = base.cor.selecionar(i);
						var display = 'block';

						// Inserindo a legenda
						estrutura += '<li><i aria-hidden="true" style="background-color: ' + cor + ' !important;"></i><strong>' + d.values[i].label + '</strong></li>';

						// Adicionando as linhas
						svgContainer
							.append('svg:path')
							.attr("d", lineGen(d.values))
							.attr('stroke', cor)
							.attr('stroke-width', 2)
							.attr('id', 'grafico-linha-' + i)
							.attr('class', 'linhas-valoradas')
							.attr('data-linha', d.values[i].linha)
							.style("display", display)
							.attr('fill', 'none');

						// Adicionando os pontos e exibindo/removendo o tooltip ao passar o mouse nos pontos
						for (inc = 0; inc < d.values.length; inc++) {
							svgContainer
								.append("circle")
								.attr("cx", xScale(d.values[inc].x))
								.attr("cy", yScale(d.values[inc].y))
								.attr("r", 5)
								.attr('id', 'grafico-ponto-' + i + '-' + d.values[inc].x)
								.attr('class', 'pontos-valorados ponto-' + i)
								.attr('data-linha', d.values[inc].linha)
								.attr('data-tooltip', d.values[inc].tooltip)
								.attr('data-json', JSON.stringify( d.values[inc].json ))
								.attr("fill", cor)
								.style("display", display)
								.style('opacity', 1)
								.on('mouseover', function() {
									var id = d3.select(this)[0][0].id,
									$alvo = $('#' + id),
									linha = $alvo.attr('data-linha'),
									tooltip = $alvo.attr('data-tooltip');

									d3.select('body')
									.append('span')
									.attr('class', 'label-tooltip')
									.html(tooltip);

									base.tooltip.posicionar();
								})
								.on('mouseout', function() {
									d3.select('.label-tooltip').remove();
								});
						}
					});

					estrutura += '</ul></div>';
					$this.append(estrutura);

					// Efeito hover linhas
					$('.linhas-valoradas').on('mouseover',function() {
						var $thisLinha = $(this),
							idlinha = $thisLinha.attr('data-linha');

						$thisLinha
							.parents('svg')
							.find('.pontos-valorados[data-linha="' + idlinha + '"]')
							.siblings('.pontos-valorados')
							.attr('fill-opacity','.1')
							.end()
							.attr('fill-opacity','1');

						$thisLinha
							.siblings('.linhas-valoradas')
							.attr('opacity','.1')
							.end()
							.attr('opacity','1');
					}).on('mouseout',function() {
						$('.pontos-valorados').removeAttr('fill-opacity');
						$('.linhas-valoradas').removeAttr('opacity');
					});

					// Efeito hover bolinhas
					$('.pontos-valorados').on('mouseover',function() {
						var $thisBolinha = $(this),
							idlinha = $thisBolinha.attr('data-linha');

						$thisBolinha
							.parents('svg')
							.find('.pontos-valorados[data-linha="' + idlinha + '"]')
							.siblings('.pontos-valorados')
							.attr('fill-opacity','.1')
							.end()
							.attr('fill-opacity','1');

						$thisBolinha
							.parents('svg')
							.find('.linhas-valoradas[data-linha="' + idlinha + '"]')
							.siblings('.linhas-valoradas')
							.attr('opacity','.1')
							.end()
							.attr('opacity','1');
					}).on('mouseout',function() {
						$('.pontos-valorados').removeAttr('fill-opacity');
						$('.linhas-valoradas').removeAttr('opacity');
					});

					// Remove o tooltip na rolagem (Solução para mobile)
					d3.select(window).on('scroll', function() {
						d3.select('.label-tooltip').remove();
					});
				});
			});
		},

		construirTooltipMapa: function() {
			/* Captura o JSON de todos dos mapas  (Estados)*/
			var $elemento = $('[data-mapa-estado-json]');

			if ($elemento.length > 0) {
				$elemento.each(function() {
					var $this = $(this),
					d3this = d3.select(this),
					json = $this.data('mapa-estado-json'),
					$path = '';

					if (json) {
						// Insere os valores nos estados do mapa
						for (var i = 0; i < json.length; i++) {
							var uf = json[i].uf,
							tooltip = json[i].tooltip;

							$path = $this.find(".estado."+ uf);
							$path.attr('data-tooltip', tooltip);
							// Insere os eventos do tooltip no mapa de regiões
							$path
							.on("mouseover", function() {
								var texto = d3.select(this).attr('data-tooltip');
								d3.select('body')
								.append('span')
								.attr('class', 'label-tooltip')
								.html(texto);
							})
								// Remove o tooltip ao retirar o mouse das barras
								.on("mouseout", function(valor) {
									d3.select('.label-tooltip').remove();
								});
							}
							base.tooltip.posicionar();
						} else {
						// Reseta todos os estados e eventos
						$this.find('.estado').find('path').removeAttr('data-tooltip');
						d3this.selectAll(".estado").on('mouseover', null).on('mouseout', null);
						$this.find('span.simbolo').removeAttr('data-tooltip');
					}
				});
			}

			/* Captura o JSON de todos dos mapas  (Cidades)*/
			var $elemento = $('[data-mapa-cidade-json]');
			if ($elemento.length > 0) {
				$elemento.each(function() {
					var $this = $(this),
					d3this = d3.select(this),
					json = $this.data('mapa-cidade-json'),
					$path = '';
					if (json) {
						// Insere os valores nos estados do mapa
						for (var i = 0; i < json.length; i++) {
							var cidade = json[i].cidade,
							tooltip = json[i].tooltip;

							$path = $this.find(".cidade."+ cidade);
							if($path.length ==0) {
								$path  = $this.find("#"+cidade+" path");
							}
							$path.attr('data-tooltip', tooltip);
							// Insere os eventos do tooltip no mapa de regiões
							$path
							.on("mouseover", function() {
								var texto = d3.select(this).attr('data-tooltip');
								d3.select('body')
								.append('span')
								.attr('class', 'label-tooltip')
								.html(texto);
							})
								// Remove o tooltip ao retirar o mouse das barras
								.on("mouseout", function(valor) {
									d3.select('.label-tooltip').remove();
								});
							}
							base.tooltip.posicionar();
						} else {
						// Reseta todas as cidades e eventos
						$this.find('.cidade').find('path').removeAttr('data-tooltip');
						d3this.selectAll(".cidade").on('mouseover', null).on('mouseout', null);
						$this.find('span.simbolo').removeAttr('data-tooltip');
					}
				});
			}
			//FIX MAPAS SVG MUITO PEQUENO EM IE
			if( base.crossBrowser.detectIE() ) {
				$('.svg-mapa-brasil--localidade, .svg-mapa-cidades')
				.attr('style', 'left: 0px; top: 0px; position: absolute;')
				.attr('viewBox', '-30 -30 460 460')
				.parent().attr('style', 'height: 0px; padding-top: 110%; position: relative;');
			}
		},

		construirEscalaMapa: function() {
			$('.block-map-scale__scale')
			.find('i')
			.on('click',function(e) {
				e.stopPropagation();

				var $this = $(this),
					color = $this.data('color');

				if ($this.parent('li').hasClass('active')) {
					$this
						.parent('li')
						.removeClass('active')
						.parents('.block-map-scale')
						.find('.block-map-scale__map')
						.removeAttr('class')
						.addClass('block-map-scale__map');
				} else {
					$this
						.parent('li')
						.addClass('active')
						.siblings('li')
						.removeClass('active')
						.parents('.block-map-scale')
						.find('.block-map-scale__map')
						.removeAttr('class')
						.addClass('block-map-scale__map fixed inactive ' + color);
				}

				$('body').on('click',function() {
					$('.block-map-scale__map')
						.removeAttr('class')
						.addClass('block-map-scale__map');
					$('.block-map-scale__scale')
						.find('li')
						.removeClass('active');
				});

			}).on('mouseover',function() {
				var $this = $(this),
				color = $this.data('color'),
				$parents = $this.parents('.block-map-scale').find('.block-map-scale__map');

				if (!$parents.hasClass('fixed')) {
					$parents.addClass(color + ' inactive');
				}
			}).on('mouseout',function() {
				var $this = $(this),
				color = $this.data('color'),
				$parents = $this.parents('.block-map-scale').find('.block-map-scale__map');

				if (!$parents.hasClass('fixed')) {
					$parents.removeClass(color + ' inactive');
				}
			});
		},

		construirMapaMunicipios: function(mapa,params) {
			var $estados = $(mapa).find('.estado'),
			cidade = $estados.attr('xlink:href');

			if (cidade != undefined) {
				$estados.on('click', function() {
					var $this = $(this),
					estadoSelecionado = $this.attr('xlink:href'),
					$elementoAvo = $this.parents('.block-map-scale'),
					$elementoPai = $elementoAvo.find('.block-map-scale__map')
					$elementoMapaPrincipal = $elementoAvo.find('.block-map-scale__main'),
					$elementoMapaExtra = $elementoAvo.find('.block-map-scale__extra');

					$elementoAvo.addClass('ajax-loading');
					$elementoMapaPrincipal.hide();

					$.ajax({
						url: estadoSelecionado,
						cache: false
					})
					.done(function(html) {
						$elementoMapaExtra.html(html);

						// Depois de inserido o mapa, os municípios são coloridos de acordo com seu valor
						var faixa = $elementoAvo.find('.svg-mapa-cidades').data('mapa-cidade-json');
						for (i in faixa) {
							var valorCidade = parseInt(faixa[i].valor, 10),
							corCidade;

							if (valorCidade < params.faixa1) {
								var corCidade = 'color-red';
							} else if (valorCidade < params.faixa2) {
								var corCidade = 'color-orange';
							} else if (valorCidade < params.faixa3) {
								var corCidade = 'color-yellow';
							} else if (valorCidade < params.faixa4) {
								var corCidade = 'color-light-green';
							} else if (valorCidade < params.faixa5) {
								var corCidade = 'color-green';
							} else {
								var corCidade = 'color-dark-green';
							}

							$elementoAvo
							.find('#' + faixa[i].cidade)
							.attr('class','cidade ' + corCidade)
							.find('path')
							.attr('data-tooltip',faixa[i].tooltip)
								// Exibe o tooltip ao passar o mouse nas barras
								.on("mouseover", function() {
									var texto = d3.select(this).attr('data-tooltip');
									d3.select('body')
									.append('span')
									.attr('class', 'label-tooltip')
									.html(texto);

									base.tooltip.posicionar();
								})
								// Remove o tooltip ao retirar o mouse das barras
								.on("mouseout", function(valor) {
									d3.select('.label-tooltip').remove();
								});
							}

						// Solução para os IEs
						if( base.crossBrowser.detectIE() ) {
							$('.svg-mapa-cidades')
							.attr('height', '840')
							.attr('width', '100%')
							.attr('viewBox', '-30 -30 460 460')
						}

						$elementoAvo.removeClass('ajax-loading');
						$('.label-tooltip').remove();

						// Depois de inseridos os dados no DOM, executa novamente as funções de construção de gráficos
						base.grafico.construirEscalaMapa();
						base.grafico.selecionar('.block-map-scale__extra');

						// Botão voltar exibe de volta o mapa principal e apaga o mapa extra
						$('.voltar-mapa').on('click',function() {
							$elementoMapaExtra.html('');
							$elementoMapaPrincipal.show();
						});
					});

					return false;
				});
			} else {
				$('#' + grafico).find('.estado').on('click', function() {
					return false;
				});
			}
		},

		construirMapaEstadosMunicipios: function(mapa,params) {
			var $estados = $(mapa).find('.estado'),
			cidade = $estados.attr('xlink:href');

			if (cidade != undefined) {
				$estados.on('click', function() {
					var $this = $(this),
					estadoSelecionado = $this.attr('xlink:href'),
					$elementoRaiz = $this.parents('#box-cidadao, #box-gestao');

					$elementoRaiz.find('.box-cidadao__principal').hide();

					//$elementoRaiz.addClass('ajax-loading');
					// $elementoMapaPrincipal.hide();

					$.ajax({
						url: estadoSelecionado,
						cache: false
					})
					.done(function(html) {
						$elementoRaiz.find('.box-cidadao__extra').html(html);

						// Depois de inserido o mapa, os municípios são coloridos de acordo com seu valor
						var faixa = $elementoRaiz.find('.svg-mapa-cidades').data('mapa-cidade-json');
						for (i in faixa) {
							var valorCidade = parseInt(faixa[i].valor, 10),
							corCidade;

							if (valorCidade < params.faixa1) {
								var corCidade = 'color-red';
							} else if (valorCidade < params.faixa2) {
								var corCidade = 'color-orange';
							} else if (valorCidade < params.faixa3) {
								var corCidade = 'color-yellow';
							} else if (valorCidade < params.faixa4) {
								var corCidade = 'color-light-green';
							} else if (valorCidade < params.faixa5) {
								var corCidade = 'color-green';
							} else {
								var corCidade = 'color-dark-green';
							}

							$elementoRaiz
							.find('#' + faixa[i].cidade)
							.attr('class','cidade ' + corCidade)
							.find('path')
							.attr('data-tooltip',faixa[i].tooltip)
								// Exibe o tooltip ao passar o mouse nas barras
								.on("mouseover", function() {
									var texto = d3.select(this).attr('data-tooltip');
									d3.select('body')
									.append('span')
									.attr('class', 'label-tooltip')
									.html(texto);

									base.tooltip.posicionar();
								})
								// Remove o tooltip ao retirar o mouse das barras
								.on("mouseout", function(valor) {
									d3.select('.label-tooltip').remove();
								});
							}

						// Solução para os IEs
						if( base.crossBrowser.detectIE() ) {
							$('.svg-mapa-cidades')
							.attr('height', '840')
							.attr('width', '100%')
							.attr('viewBox', '-30 -30 460 460')
						}

						//$elementoAvo.removeClass('ajax-loading');
						$('.label-tooltip').remove();

						// Depois de inseridos os dados no DOM, executa novamente as funções de construção de gráficos
						base.grafico.construirEscalaMapa();
						base.grafico.selecionar($elementoRaiz.find('.box-cidadao__extra'));
						base.grafico.jsNaoObstrutivo();

						// Botão voltar exibe de volta o mapa principal e apaga o mapa extra
						$('.voltar-mapa').on('click',function() {
							$elementoRaiz.find('.box-cidadao__extra').html('');
							$elementoRaiz.find('.box-cidadao__principal').show();
						});
					});

					return false;
				});
			} else {
				$('#' + grafico).find('.estado').on('click', function() {
					return false;
				});
			}
		},
		/**
		* Muda o mapa Brasil para mapas de estados, a partir do select correlacionado
		*
		* @param {string} selectElementSelector - seletor do elemento select de estados
		* @param {string} containerMapaSelector - seletor do container dos mapas
		* @return void
		* @author Marcos Furquim
		*/
		mudaMapaEstado: function(selectElementSelector,containerMapaSelector) {
			/* VARIAVEIS */
			var $containerMapa = $(containerMapaSelector);
			var $containerMapaEstados = $containerMapa.find("#mapa-estados");
			var $svgBrasil = $containerMapa.find('.svg-mapa-brasil');

			function mudaMapaMain(siglaEstado,ajaxLink) {
				var $estadoSelecionado = (siglaEstado !== '')?$svgBrasil.find('.estado.'+siglaEstado):null;
				var ajaxLink = (typeof ajaxLink === 'undefined')? $estadoSelecionado.attr('xlink:href') : ajaxLink;
				$containerMapa.addClass('ajax-loading');

				$.ajax({
					url: ajaxLink,
					cache: false,
					success: function(data) {
						$containerMapa.removeClass('ajax-loading');
						$svgBrasil.hide();
						$containerMapaEstados.html(data);
					},

				})
				.done(function(html) {

						// Solução para os IEs
						if( base.crossBrowser.detectIE() ) {
							$('.svg-mapa-cidades')
							.attr('height', '840')
							.attr('width', '100%')
							.attr('viewBox', '-30 -30 460 460')
						}

						$('.voltar-mapa').on('click',function() {
							$svgBrasil.show();
							$containerMapaEstados.html("");
						});
						base.grafico.construirTooltipMapa();
					});
			}

			//eventos
			$(selectElementSelector).change(function() { 
				var selectValue = $(this).val().toLowerCase();
				if(selectValue != "") {
					mudaMapaMain(selectValue);
				} else {

				}

			});

			$svgBrasil.find('a.estado').click(function(e) {
				e.preventDefault();
				mudaMapaMain('',$(this).attr('xlink:href')); 
			});

		},

		/**
		* Esconder a tabela e mostra o mapa, caso o js estiver habilitado (js não obstrutivo)
		*
		* @return void
		* @author Marcos Furquim
		*/
		jsNaoObstrutivo: function() {
			$(".table-acessibilidade").parent().addClass("sr-only");
			$(".table-acessibilidade").parent().prev(".svg").show();
			$(".svg.mapa-brasil.ativo").show();
		},

		/**
		* contagem de questões preenchidas
		*
		* @return void
		* @author Marco Malaquias
		*/
		iniciarContarQuestoes: function() {
			var quantidade = $('[data-question="question"]').length;
			base.grafico.construirGraficoContarQuestoes(quantidade, 0);
			base.grafico.contarQuestoes();

			$('[data-question="question"]').find('input[type=text]:first').blur(function(){
				base.grafico.contarQuestoes();
			});

			$('[data-question="question"]').find('input[type=checkbox], input[type=radio], select').change(function(){
				base.grafico.contarQuestoes();
			});
		},


		/**
		* contagem de questões preenchidas
		*
		* @return void
		* @author Marco Malaquias
		*/
		contarQuestoes: function() {
			var $boxQuestao = $('[data-question="question"]');
			var quantidade = $boxQuestao.length;
			var qtdPreenchido = 0;

			$boxQuestao.each(function() {
				var $this = $(this);
				var type = $this.data('type');

				if ( type == 'text' ) {
					qtdPreenchido =  $this.find('input[type=text]:first').val() == '' ? qtdPreenchido : qtdPreenchido + 1;
				
				} else if ( type == 'checkbox' ) {
					qtdPreenchido =  $this.find('input[type=checkbox]:checked').length == 0 ? qtdPreenchido : qtdPreenchido + 1;

				} else if ( type == 'radio' ) {
					qtdPreenchido =  $this.find('input[type=radio]:checked').length == 0 ? qtdPreenchido : qtdPreenchido + 1;
				}



			});
			base.grafico.construirGraficoContarQuestoes(quantidade, qtdPreenchido);
		},


		construirGraficoContarQuestoes: function(questions, answers) {
			var $elemento = $('.block-questions-counter'),
				d3elemento = d3.select('.block-questions-counter'),
				questoes = questions,
				respostas = answers,
				resto = questoes - respostas,
				paleta = 'escala1',
				altura = 400,
				lista = [];

			$elemento.html(''); 

			// Dados
			lista[0] = {
				'label': '',
				'value': respostas,
				'paleta': paleta
			};
			lista[1] = {
				'label': '',
				'value': resto,
				'paleta': paleta
			};

			var frase = respostas + '/' + questoes;

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
				}).sort(null),
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
			.attr('font-size', '73')
			.attr('text-anchor', 'middle')
			.attr('alignment-baseline', 'middle')
			.style('font-weight','bold')
			.text(function(d, i) {
				if (i == 0) {
					return frase;
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
		converterParaFloat: function(currency,decimal) {
			if (typeof currency == "string") {
				currency = currency.replace(/\./g, '');

				if (decimal != undefined) {
					return parseFloat(currency.replace(/\,/g, '.')).toFixed(decimal);
				} else {
					return parseFloat(currency.replace(/\,/g, '.'));
				}
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
				case 'escala1':
				var cores = ["#3ab7ea","#ededed","#ffffff"];
				break;
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
						'transform': 'translate(' + ((limite - tooltip) - 35) + 'px,' + (y + 520) + 'px)',
						'-webkit-transform': 'translate(' + ((limite - tooltip) - 35) + 'px,' + (y + 520) + 'px)'
					});
				} else {
					$('.label-tooltip').css({
						'transform': 'translate(' + (x - 20) + 'px,' + (y + 530) + 'px)',
						'-webkit-transform': 'translate(' + (x - 20) + 'px,' + (y + 530) + 'px)'
					});
				}
			};
		},

		iniciar: function() {
			$("[data-toggle=\'tooltip\']").tooltip();
			$("[data-toggletooltip=\'tooltip\']").tooltip({container: 'body'});

			$('.container').on('mouseover',function(e) {
				e.stopPropagation();
			});

			$('body').on("mouseover", function() {
				$('.label-tooltip').remove();
			});
		}
	},

	carousel: {
		paginaComoParticipar: function() {
			$('.slideshow-list').slick({
				dots: true,
				infinite: true,
				slidesToShow: 1,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>'
			});

			$('.block-tabs-carousel__item').find('a').on('click',function() {
				$(this)
					.parents('.block-tabs-carousel__item')
					.addClass('active')
					.siblings('.block-tabs-carousel__item')
					.removeClass('active');
			});

			$('.slider-list').on('afterChange', function(event, slick, currentSlide){
				if ( slick.$slides.last().hasClass('slick-active') ){
					slick.$slider.addClass('is-last');
				} else {
					slick.$slider.removeClass('is-last');
				}
			});

			$('.slider-list').slick({
				infinite: false,
				slidesToShow: 2.3,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>',
				responsive: [{
					breakpoint: 995,
					settings: {
						slidesToShow: 1,
					}
				}]
			});
		},

		paginaAvaliacoes: function() {
			$('.block-thumb-video-carousel').slick({
				infinite: false,
				slidesToShow: 4,
				slidesToScroll: 1,
				arrows: true,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>',
				dots: false,
				responsive: [
					{
						breakpoint: 800,
						settings: { slidesToShow: 2 }
					},
					{
						breakpoint: 500,
						settings: { slidesToShow: 1 }
					}
				]
			});

			// Amplia o vídeo da thumb clicada
			var $carrosselVideos = $('.block-thumb-video-carousel');
			$carrosselVideos
				.find('.thumb-video')
				.on('click',function() {
					var $this = $(this),
						video = $this.data('video');

					$this
						.parents('.block-player-carousel')
						.find('.block-player__player')
						.html('<iframe src="https://www.youtube.com/embed/' + video + '" frameborder="0" allowfullscreen></iframe>');
				});

			// Exibe o vídeo da primeira thumb assim que a página é clicada
			$carrosselVideos
				.find('.thumb-video:eq(0)')
				.click();
		},

		paginaLocalidade: function() {
			$('.notas-slider').slick({
				slidesToShow: 2,
				slidesToScroll: 1,
				infinite: false,
				arrows: true,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>',
				responsive: [
				{
					breakpoint: 1200,
					settings: {
						slidesToShow: 1,
						slidesToScroll: 1,
						infinite: false,
					}
				}
				]
			});

			$('.notas-slider').on('afterChange', function(event, slick, currentSlide){
				if ( slick.$slides.last().hasClass('slick-active') ){
					slick.$slider.addClass('is-last');
				} else {
					slick.$slider.removeClass('is-last');
				}
			});

			$('.block-tabs-carousel').slick({
				infinite: true,
				slidesToShow: 3,
				slidesToScroll: 1,
				prevArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-previous"><i class="fa fa-angle-double-left" aria-hidden="true"></i><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-next"><i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="sr-only">Próximo</span></button>',
				responsive: [
				{
					breakpoint: 1200,
					settings: { slidesToShow: 2 }
				},
				{
					breakpoint: 600,
					settings: { slidesToShow: 1 }
				}
				]
			});

			base.tabs.carouselSolution();
		},

		paginaMeuPerfil: function() {
			$('.block-profile__carousel').slick({
				infinite: false,
				slidesToShow: 1,
				slidesToScroll: 1,
				prevArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-previous"><i class="fa fa-angle-double-left" aria-hidden="true"></i><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slider-wrapper__button-control slider-wrapper__button-next"><i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="sr-only">Próximo</span></button>'
			});
		},

		paginaHome: function() {
			$('.graphic-slider').slick({
				slidesToShow: 2,
				slidesToScroll: 1,
				arrows: true,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>',
				dots: true,
				focusOnSelect: true,
				responsive: [
				{
					breakpoint: 1024,
					settings: {
						slidesToShow: 2,
						slidesToScroll: 1,
						infinite: true,
						dots: false
					}
				},
				{
					breakpoint: 600,
					settings: {
						slidesToShow: 2,
						slidesToScroll: 1
					}
				},
				{
					breakpoint: 480,
					settings: {
						slidesToShow: 1,
						slidesToScroll: 1
					}
				}
				]
			});

			$('.slider-notas').slick({
				slidesToShow: 1,
				slidesToScroll: 1,
				arrows: false,
				dots: true
			});
		},

		paginaTransparencia: function() {
			$('.slider-notas').slick({
				slidesToShow: 1,
				slidesToScroll: 1,
				arrows: false,
				dots: true
			});
		},

		paginaEstudosAnalises: function() {
			$('.estudos-analises-carrousel').slick({
				infinite: false,
				slidesToShow: 1,
				slidesToScroll: 1,
				arrows: true,
				prevArrow: '<button type="button" class="slick-prev"><span class="fa fa-angle-left" aria-hidden="true"></span><span class="sr-only">Anterior</span></button>',
				nextArrow: '<button type="button" class="slick-next"><span class="fa fa-angle-right" aria-hidden="true"></span><span class="sr-only">Próximo</span></button>',
				dots: false
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

	filtro : {
		/**
		* Funcionalidades para o glossário - filtro das palavras e collapse
		*
		* @return void
		* @author Ricardo Carvalho
		*/
		filtroGlossario: function(form, listContainer) {
			$(form).on('submit',function() {
				return false;
			});
			/* Filtro de acordo com as palavras digitadas */
			$(form).find('input[type="text"]').on('keyup click input', function () {
				var val = this.value;
				if (this.value.length > 0) {
					$(listContainer).find('ul').addClass('expanded');
					$('.expand-list').hide();
					$('.clear-input').removeClass('hidden');
					$(listContainer).find('ul li').hide().filter(function () {
						return $(this).text().toLowerCase().indexOf( val.toLowerCase() ) != -1;
					}).show();
				}else {
					$(listContainer).find('ul li').show();
					$(listContainer).find('ul').removeClass('expanded');
					$('.expand-list').show();
					$('.clear-input').addClass('hidden');
				}
			});

			$(form).find('.clear-input').on('click', function(){
				$(form).find('input[type="text"]').val('');
				$(listContainer).find('ul li').show();
				$(listContainer).find('ul').removeClass('expanded');
				$('.expand-list').show();
				$(this).addClass('hidden');
			});

			/* Insere um link expandir a visualizar da lista de índices */
			$(listContainer).find('ul').each(function(){
				if( $(this).find('li').length > 4 ){
					var length = $(this).find('li').length;
					$(this).append('<li><div class="expand-list"><a href="#" class="open-list"><i class="fa fa-chevron-down" aria-hidden="true"></i> <span>Ver mais</span></a><a href="#" class="close-list"><i class="fa fa-chevron-up" aria-hidden="true"></i> <span>Fechar</span></a></div></li>');
					$(this).find('li').each(function(i) {
						if(i>4 && i < length) {
							$(this).toggleClass("escondido");
						}
					});
				}
			});

			/* Expande/Retrai lista de índices */
			$('.expand-list > a').on('click', function(e){
				e.preventDefault();
				$(this).closest('ul').toggleClass('expanded');
				var length = $(this).closest('ul').find('li').length;
				$(this).closest('ul').find('li').each(function(i) {
					if(i > 4 && i < length) {
						$(this).toggleClass("escondido");
					}
				});
			});

			$(listContainer).find('ul li > a').on('click', function() {
				if( !$(this).next('.collapse').hasClass('in') ){
					$(this).addClass('ativo');
					$(this).closest('ul').addClass('expanded');
				}else{
					$(this).removeClass('ativo');
				}
			});
		}
	},

	collapses: {
		/**
		* Fecha as outras accordions
		*
		* @return void
		* @author Marcos Furquim
		*/
		closeOthers: function() {
			/* codigo para evitar js obstrutivo */
			$('button.close-others')
				.addClass("collapsed")
				.next('div.in')
				.removeClass("in");
			/* fechar outras accordions */
			$('.collapsed.close-others')
				.on('click', function() {
					var parent = $(this).data('parent');
					$(parent).find('.collapse').collapse('hide');
			});

			$('.close-others')
				.find('input[type="checkbox"],label')
				.on('click',function(e) {
					e.stopPropagation();
				});
		}
	},

	resultadosAvaliacao: {
		blockGrafico: {
			/**
			* Exibe os gráficos de resultados (JS não obstrutivo)
			*
			* @return void
			* @author Marcos Furquim
			*/
			show: function () {
				$(".resultados-avaliacao__block-grafico").show();
				$(".table-acessibilidade").parent().addClass("sr-only");
			}


		},

		/**
		* js para mapas e ajax
		*
		* @return void
		* @author Marco Malaquias
		*/
		iniciarPaginaAutoAvaliacaoAvaliacaoCidada: function (faixas) {
			$('[data-action=atualizar-resultado-avaliacao]').click(function(){
				var $this = $(this);
				var $target = $( $this.data('target') );

				$.get($this.attr('href'), {}, function(html){
					$target.html(html);

					base.grafico.selecionar($this.data('target'));
					base.grafico.construirTooltipMapa();
					base.grafico.construirEscalaMapa();
					base.grafico.construirMapaMunicipios("#mapa-municipios-brasil,#mapa-municipios-brasil-2", faixas);
					base.grafico.jsNaoObstrutivo();
					base.autoComplete.paginaAutoAvaliacao();
				});

				$this.siblings('a').removeClass('button--primary-a').addClass('button--secondary-a');
				$this.removeClass('button--secondary-a').addClass('button--primary-a');

				return false;
			});
		},

		playTimeout: null,


		/**
		* play
		*
		* @return void
		* @author Marco Malaquias
		*/
		play: function () {
			$('[data-action=avaliacao-play]').click(function(){
				var $this = $(this);
				var $i = $(this).find('i');
				var $mesAtual = $this.parents(':first').siblings('div').find('[data-action=atualizar-resultado-avaliacao].button--primary-a');

				if ( $mesAtual.length == 0 ) {
					$mesAtual = $this.parents(':first').siblings('div').find('[data-action=atualizar-resultado-avaliacao]:first');
					$mesAtual.addClass('button--primary-a');
					$mesAtual.trigger('click');
				}

				if ( $i.hasClass('fa-play') ) {
					$i.addClass('fa-pause').removeClass('fa-play');
					$i.parents('.tab-content').find('[data-action=avaliacao-play]').find('i').addClass('fa-pause').removeClass('fa-play');
					base.resultadosAvaliacao.next($this, $mesAtual);

				} else {
					$i.addClass('fa-play').removeClass('fa-pause');
					$i.parents('.tab-content').find('[data-action=avaliacao-play]').find('i').addClass('fa-play').removeClass('fa-pause');
					clearTimeout(base.resultadosAvaliacao.playTimeout);
				}

				return false;
			});
		},

		/**
		* next
		*
		* @return void
		* @author Marco Malaquias
		*/
		next: function ($play, $mesAtual) {
			var $next = $mesAtual.next();

			if ( $next.length == 0 ) {
				var nomeAbaAno = $mesAtual.parents('.tab-pane:first').data('aba');
				var $ano = $( nomeAbaAno );
				var $proximoAno = $ano.parents('[role=presentation]:first').next();

				if ( $proximoAno.length == 0 ) {
					$proximoAno = $ano.parents('[role=presentation]:first').siblings(':first');
				}

				$proximoAno.find('a').trigger('click');
				$next = $( $proximoAno.find('a').attr('href') ).find('[data-action=atualizar-resultado-avaliacao]:first');
			}

			$next.trigger('click');

			base.resultadosAvaliacao.playTimeout = setTimeout(function(){
				base.resultadosAvaliacao.next($play, $next);
			}, 3000);
		}
	},

	dadosAbertos: {
		/**
		* accordions pagina dados abertos
		*
		* @return void
		* @author Marcos Furquim
		*/
		accordions: function() {
			//evitar js não obstrutivo
			$(".bloco-download-poderes").addClass("collapse");
			//evento
			$(".bloco-download__btn-group button:last-child").click(function () {
				if($(this).children("i").hasClass("fa-chevron-down")) {
					$(this).children("i").removeClass("fa-chevron-down").addClass("fa-times");
					//fecha outros accordions
					var outros = $(this).closest(".bloco-download").siblings(".bloco-download")
					outros.find(".bloco-download__btn-group button:last-child").children("i").removeClass("fa-times").addClass("fa-chevron-down");
					outros.find(".bloco-download-poderes").collapse("hide");
				} else {
					$(this).children("i").removeClass("fa-times").addClass("fa-chevron-down");
				}
			});
		},
		filtro: function() {
			$(".download-demanda").show();
			var options = {
				url: "./assets/inc/ajax_dados/cidades_estados.json",
				getValue: "Nome",
				list: {
					maxNumberOfElements: 20,
					match: {
						enabled: true
					}
				},
				categories: [
				{
					listLocation: "Cidades",
					header: "<span>Cidades</span>"
				},
				{
					listLocation: "Estados",
					header: "<span>Estados</span>"
				}
				],
				template: {
					type: "custom",
					method: function(value, item) {
						if(item.Estado) { //se tiver estado no objeto
							return "<a href=\"javascript: void(0)\" data-estado=\""+item.Sigla+"\" data-nome=\""+item.Nome+"\">"+ value +"</span>";
						} else {
							return "<a href=\"javascript: void(0)\" data-nome=\""+item.Nome+"\">"+ value +"</span>";
						}
					}
				}
			};
			$("#busca-cidades-estados").easyAutocomplete(options);

			$("#select-filter-avaliacao").multiselect({

				nonSelectedText: "Nenhum selecionado",
				nSelectedText: "Selecionado",
				allSelectedText: "Todos selecionados",
				numberDisplayed: 3,
				buttonText: function(options, select) {
					return $("#select-filter-avaliacao").attr("data-placeholder");
				},
				onChange: function(element, checked) {
					if (checked === true) {
	                    //adiciona nos filtros
	                    toggleFiltro(element,"#select-filter-avaliacao");
	                }
	                else if (checked === false) {
						//remove dos filtros
						toggleFiltro(element,"#select-filter-avaliacao",true);
					}
				}
			});
			$("#select-filter-poder").multiselect({

				nonSelectedText: "Nenhum selecionado",
				nSelectedText: "Selecionado",
				allSelectedText: "Todos selecionados",
				numberDisplayed: 3,
				buttonText: function(options, select) {
					return $("#select-filter-poder").attr("data-placeholder");
				},
				enableClickableOptGroups: false,
				onChange: function(element, checked) {
					if (checked === true) {
	                    //adiciona nos filtros
	                    toggleFiltro(element,"#select-filter-poder");
	                }
	                else if (checked === false) {
						//remove dos filtros
						toggleFiltro(element,"#select-filter-poder",true);
					}
				}
			});

			/*
			* easyAutoComplete modificado para que cidades e Estados sejam agrupados lado a lado (colunas) pagina dados abertos
			*
			* @return void
			* @author Marcos Furquim
			*/
			// function categoriaColuna() {
			// 	var listaCategorias = "";
			// 	$("li > div.eac-category").each(function () {
			// 		var $categoria = $(this);
			// 		var $proximos = $categoria.closest("li").nextAll("li");
			// 		var builderCategoria = "";
			// 		$proximos.each(function() {
			// 			if ($(this).children("div").hasClass("eac-item")) { // é item, faz parte da categoria pai (acima na DOM)
			// 				builderCategoria += "<li>" + $(this).html() + "</li>";
			// 				$(this).remove();
			// 			} else {
			// 				return false;
			// 			}
			// 		});
			// 		$categoria.append("<ul>" + builderCategoria + "</ul>");
			// 		listaCategorias += "<li><div class=\"eac-category\">" + $categoria.html() + "</div></li>";
			// 	});

			// 	/* event listers clique cidade */
			// 	$(".download-demanda__block-detalhes > div.dados-abertos__download-demanda__filtro-detalhes  .eac-category > ul li").click(function () {
			// 		toggleFiltro(this,"#busca-cidades-estados");
			// 	});
			// }
			/*
			* adicao e remoção dos filtros
			*
			* @param {elemento DOM} elemento - elemento option / li
			* @param {string} source - id do select filtro / id da input text da easycomplete cidade-estado
			* @param {boolean} remove - bool remove ou não (adiciona) *opcional
			* @return void
			* @author Marcos Furquim
			*/
			function toggleFiltro(elemento,source,remove) {
				remove = (typeof remove === "undefined") ? false : remove;
				
				switch (source) {
					case "#select-filter-avaliacao":
					if(!remove) { /* adiciona */
						$divFiltro = $("<div>").addClass("filtro-block__filters-block__filter").prepend("<span data-srcFilter=\""+elemento.val()+"\">"+elemento.text()+"</span><i class=\"fa fa-times\" aria-hidden=\"true\"></i>");
						$("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block").append($divFiltro);
					} else { /* remove */
						$("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block span[data-srcfilter=\""+elemento.val()+"\"]").parent().remove();
					}
					//contagem
					$("div[data-srcFilter=\""+source+"\"] > h4.filtro-block__title > span.counter")
					.html($("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block > .filtro-block__filters-block__filter").length);
					break;

					case "#select-filter-poder":
					var esfera = (elemento.val().charAt(0) == 'e')? 'estadual': 'municipal';
					if(!remove) { /* adiciona */
						$divFiltro = $("<div>").addClass("filtro-block__filters-block__filter").prepend("<span data-srcFilter=\""+elemento.val()+"\">"+elemento.text()+" ("+esfera+")</span><i class=\"fa fa-times\" aria-hidden=\"true\"></i>");
						$("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block").append($divFiltro);
					} else { /* remove */
						$("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block span[data-srcfilter=\""+elemento.val()+"\"]").parent().remove();
					}
					//contagem
					$("div[data-srcFilter=\""+source+"\"] > h4.filtro-block__title > span.counter")
					.html($("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block > .filtro-block__filters-block__filter").length);
					break;

					case "#busca-cidades-estados":
					var objLocalidade = {
						nome: $(elemento).find("a").attr("data-nome"),
						estado: $(elemento).find("a").attr("data-estado"),
						srcFilterNivel: ''
					}
					
					var $divFiltro = "";
					if (typeof(objLocalidade.estado) != 'undefined') { //caso seja municipio, deve adicionar na area de municipios
						objLocalidade.srcFilterNivel = "municipio";
						$divFiltro = $("<div>").addClass("filtro-block__filters-block__filter").prepend("<span>"+objLocalidade.nome+" ("+objLocalidade.estado+")</span><i class=\"fa fa-times\" aria-hidden=\"true\"></i>");
					} else { // caso nao, adiciona na area de estado
						objLocalidade.srcFilterNivel = "estado";
						$divFiltro = $("<div>").addClass("filtro-block__filters-block__filter").prepend("<span>"+objLocalidade.nome+"</span><i class=\"fa fa-times\" aria-hidden=\"true\"></i>");
					}
					if(!remove) { /* adiciona */
						$("div[data-srcFilter=\""+source+"\"][data-srcFilterNivel=\""+objLocalidade.srcFilterNivel+"\"] > .filtro-block__filters-block").append($divFiltro);
					} else { /* remove */
						/*$("div[data-srcFilter=\""+source+"\"] > .filtro-block__filters-block span[data-srcfilter=\""+elemento.val()+"\"]").parent().remove();*/
					}
					//contagem
					$("div[data-srcFilter=\""+source+"\"][data-srcFilterNivel=\""+objLocalidade.srcFilterNivel+"\"] > h4.filtro-block__title > span.counter")
					.html($("div[data-srcFilter=\""+source+"\"][data-srcFilterNivel=\""+objLocalidade.srcFilterNivel+"\"] > .filtro-block__filters-block > .filtro-block__filters-block__filter").length);
					break;
					
					default:
					break;
				}
				eventsTrigger();
			}	
			//eventos
			function eventsTrigger() {
				// remove filtro clicando no 'x'
				$(".filtro-block__filters-block__filter > i ").unbind().click(function() {
					//recontagem
					$(this).closest(".download-demanda__filtro-block").find("h4.filtro-block__title > span.counter")
					.html($(this).closest(".download-demanda__filtro-block").find(".filtro-block__filters-block__filter").length-1);
					
					//deseleciona do multiselect
					var multiselectSrcFilter = $(this).closest(".download-demanda__filtro-block").attr("data-srcFilter");
					var optionSrcFilter = $(this).siblings("span").attr("data-srcFilter");
					if (multiselectSrcFilter.indexOf("#select") > -1) {
						$(multiselectSrcFilter).multiselect('deselect',optionSrcFilter);
					}
					 //remove elemento da DOM
					 $(this).parent(".filtro-block__filters-block__filter").remove();
					});

			}
		}
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

	metodologia: {
		/*
		* Monta o accordion para evitar JS obstrutivo
		* 
		* @return void
		* @author Marcos Furquim
		*/
		accordion: function () {
			$(".metodologia__accordion-collapser").addClass("collapse");
			$(".metodologia__accordion-button").addClass("collapsed");
			//evento click, fechar outros
			$(".metodologia__accordion-button").click(function() {
				var $outros = $(this).closest(".metodologia__accordion-group").siblings(".metodologia__accordion-group");
				$outros.find(".metodologia__accordion-collapser").collapse("hide");
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

	autoComplete: {
		show: function() {
			$('.auto-complete-button').on('click',function(e) {
				$(this).parents('.block-title-search').toggleClass('active');

				e.stopPropagation();
			});

			$('.block-title-search').on('click',function(e) {
				e.stopPropagation();
			});

			$('body').on('click',function() {
				$('.block-title-search').removeClass('active');
			});
		},

		init: function() {
			$('.auto-complete-button').each(function() {
				var $this = $(this),
				uf = $this.data('uf');

				var options = {
					url: './assets/inc/ajax_autocomplete_estados/' + uf + '.json',
					getValue: 'nome',
					list: {
						maxNumberOfElements: 20,
						match: {
							enabled: true
						}
					},
					template: {
						type: 'custom',
						method: function(value, item) {
							return '<a href="' + item.url + '">'+ value +'</a>';
						}
					}
				};

				$this
				.parents('.block-title-search')
				.find('.busca-cidades')
				.easyAutocomplete(options);
			});
		},

		paginaMeuPerfil: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$('#busca-localidade').easyAutocomplete(options);
		},

		paginaLocalidade: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$('#block-filter-county__text').easyAutocomplete(options);
		},

		paginaAutoAvaliacao: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$("#busca-cidades,#busca-cidades-cidadaos").easyAutocomplete(options);
		},

		paginaDestaques: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$('#busca-indice').easyAutocomplete(options);
		},

		paginaEscala: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="resultados_avaliacao_independente.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="resultados_avaliacao_independente.php">' + value + '</a>';
						}
					}
				}
			};
			$('#busca-cidades,#busca-localidade-especifica').easyAutocomplete(options);
			$("a[href='#ranking'").click(function() {
				var filtroTipo = $(this).data("filtrotipo");
				console.log(filtroTipo);
				$("#select-localidade").val(filtroTipo);
			});
		},

		paginaHome: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$("#busca-cidades").easyAutocomplete(options);
		},

		paginaTransparencia: function() {
			var options = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
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
							return '<a href="localidade_municipio.php">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="localidade_estado.php">' + value + '</a>';
						}
					}
				}
			};
			$('#busca-cidades').easyAutocomplete(options);

			var options2 = {
				url: './assets/inc/ajax_dados/cidades_estados.json',
				getValue: 'Nome',
				list: {
					maxNumberOfElements: 20,
					match: {
						enabled: true
					},
					onLoadEvent: function() {
						base.ajax.construirEstadosMunicipios();
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
							return '<a href="#" class="option-ajax-localidade" data-tipo="municipio" data-nome="' + item.Nome + '">' + value + ' - ' + item.Sigla + '</a>';
						} else {
							return '<a href="#" class="option-ajax-localidade" data-tipo="estado" data-nome="' + item.Nome + '" data-uf="' + item.Sigla + '">' + value + '</a>';
						}
					}
				}
			};
			$('#busca-localidade').easyAutocomplete(options2);
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
			if ($phone.length) { $phone.mask("(99) 9999-9999?9",{placeholder:' '}); }
			if ($number.length) { $number.onlyNumber(); }
			if ($field.length) {
				var mascara = $field.data("mask");
				$field.mask(mascara,{placeholder:' '});
			}
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

			// $('.step-1-button').on('click',function() {
			// 	$step2.removeClass('active');
			// 	$step1.addClass('active');
			// 	return false;
			// });

			$('button.points__button').on('click',function() {
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

	lista: {
		removerItens: function() {
			$(".block-filter__list").find("button").on("click",function() {
				$(this).parents("li").remove();
			});
		}
	},

	acessibilidade: {
		suporteGestos: function() {
			var supportTouch = 'ontouchend' in document;

			$.event.special.swipe = $.event.special.swipe || {
					scrollSupressionThreshold: 10, // More than this horizontal displacement, and we will suppress scrolling.
					durationThreshold: 1000, // More time than this, and it isn't a swipe.
					horizontalDistanceThreshold: 30, // Swipe horizontal displacement must be more than this.
					verticalDistanceThreshold: 75, // Swipe vertical displacement must be less than this.

					setup: function() {
						var $this = $(this);

						$this.bind('touchstart', function(event) {
							var data = event.originalEvent.touches ? event.originalEvent.touches[0] : event;
							var stop, start = {
								time: (new Date()).getTime(),
								coords: [data.pageX, data.pageY],
								origin: $(event.target)
							};

							function moveHandler(event) {
								if (!start)
									return;

								var data = event.originalEvent.touches ? event.originalEvent.touches[0] : event;

								stop = {
									time: (new Date()).getTime(),
									coords: [data.pageX, data.pageY]
								};

								// prevent scrolling
								if (Math.abs(start.coords[0] - stop.coords[0]) > $.event.special.swipe.scrollSupressionThreshold) {
									event.preventDefault();
								}
							}

							$this.bind('touchmove', moveHandler)
								.one('touchend', function(event) {
									$this.unbind('touchmove', moveHandler);

									if (start && stop) {
										if (stop.time - start.time < $.event.special.swipe.durationThreshold &&
											Math.abs(start.coords[0] - stop.coords[0]) > $.event.special.swipe.horizontalDistanceThreshold &&
											Math.abs(start.coords[1] - stop.coords[1]) < $.event.special.swipe.verticalDistanceThreshold) {

											start.origin.trigger("swipe")
												.trigger(start.coords[0] > stop.coords[0] ? "swipeleft" : "swiperight");
										}
									}
									start = stop = undefined;
								});
						});
					}
				};

			$.event.special.swipeleft = $.event.special.swipeleft || {
					setup: function() {
						$(this).bind('swipe', $.noop);
					}
				};
			$.event.special.swiperight = $.event.special.swiperight || $.event.special.swipeleft;
		},
		manipularFonte: function() {
			var fonte = base.acessibilidade.getCookie('fonteManipulada') ? base.acessibilidade.getCookie('fonteManipulada') : 14;

			// Checa se a fonte já foi manipulada e altera o tamanho dos textos de acordo
			if( typeof base.acessibilidade.getCookie('fonteManipulada') !== null ){
				$('body').css('fontSize', parseInt(fonte));
			}

			$('#aumentar-fonte').on('click', function() {
				fonte++;
				$('body').css('fontSize', fonte);
				document.cookie = 'fonteManipulada=' + fonte;

				return false;
			});

			$('#diminuir-fonte').on('click', function() {
				fonte--;
				$('body').css('fontSize', fonte);
				document.cookie = 'fonteManipulada=' + fonte;
				return false;
			});
		},

		altoContraste: function() {
			// Checa se o autocontraste já está ativo
			var altoContrasteAtivo = base.acessibilidade.getCookie('altoContrasteAtivo');
			if( altoContrasteAtivo === 'true' ){
				$('body').addClass('contraste');
				$('#alto-contraste').addClass('ativo');
			}

			$('#alto-contraste').on('click', function() {
				$('body').toggleClass('contraste');
				$(this).toggleClass('ativo');

				if( altoContrasteAtivo === 'true' ){
					document.cookie = 'altoContrasteAtivo=false';
				}else{
					// Adiciona um cookie com a informação do contraste
					document.cookie = 'altoContrasteAtivo=true';
				}

				return false;
			});

		},

		getCookie: function(cookieName) {
			var re = new RegExp(cookieName + "=([^;]+)");
			var value = re.exec(document.cookie);
			return (value != null) ? unescape(value[1]) : null;
		}
	},

	comparativo: {
		manipular: function() {
			// Seleciona o bloco de estado ao clique
			$('.list-comparative__border').on('click',function() {
				var $this = $(this),
					$elementoPai = $this.parents('.block-tabs-main'),
					$lista = $elementoPai.find('.list-comparative');

				if (!$lista.hasClass('comparative-on')) {
						var $contador = $elementoPai.find('.block-filter-county__counter').find('strong'),
						quantidade = parseInt($contador.text());

					if ($this.parent('li').hasClass('active')) {
						quantidade = quantidade - 1;
					} else {
						quantidade = quantidade + 1;
					}

					$contador.text(quantidade);
					$this.parent('li').toggleClass('active');
				}
			});

			// Botão de comparar estados
			$('.block-filter-county__compare').on('click',function() {
				var $this = $(this);

				$('.list-comparative').toggleClass('active');
				$('.list-comparative--results').toggleClass('active');

				$this
					.toggleClass('block-filter-county__back')
					.html($this.html() == '<i class="fa fa-files-o" aria-hidden="true"></i> Comparar' ? '<i class="fa fa-fw fa-chevron-left"></i> Voltar' : '<i class="fa fa-files-o" aria-hidden="true"></i> Comparar');
			});
		},

		reorganizar: function() {
			$('.block-filter-county__reorganize').on('click',function() {
				var $this = $(this);

				$this
					.parents('.block-tabs-main')
					.find('.list-comparative')
					.toggleClass('ten-columns');

				if ($this.html() == '<i class="fa fa-th" aria-hidden="true"></i> 5') {
					$this.html('<i class="fa fa-th" aria-hidden="true"></i> 10');
				} else {
					$this.html('<i class="fa fa-th" aria-hidden="true"></i> 5');
				}
			});
		}
	},

	ajax: {
		construirEstadosMunicipios: function() {
			$('.option-ajax-localidade').on('click',function() {
				var $this = $(this),
					$elementoPai = $this.parents('.resultados-avaliacao').find('.resultados-avaliacao__graficos')
					tipo = $this.data('tipo'),
					localidade = '';

				if (tipo == 'estado') {
					localidade = $this.data('uf').toLowerCase();
				} else {
					localidade = 'municipio';
				}

				$elementoPai
					.empty()
					.addClass('ajax-loading');

				$.ajax({
					url: 'assets/inc/ajax_dados_estados_municipios/' + localidade + '.php',
					cache: false
				})
				.done(function(html) {
					$elementoPai.html(html);

					base.resultadosAvaliacao.blockGrafico.show();
					base.grafico.selecionar($elementoPai);
					base.carousel.paginaTransparencia();
					$elementoPai.removeClass('ajax-loading');
				});

				return false;
			});
		}
	},



	flipCard: {
		iniciar: function() {
			$(".flip-container__button").on("click",function(e) {
				$(this).parents(".flip-container").addClass("active");

				e.stopPropagation();
			});

			$(".flip-container__back").on("click",function(e) {
				$(this).parents(".flip-container").removeClass("active");

				e.stopPropagation();
			});

			$("body").on("click",function() {
				$(".flip-container").removeClass("active");
			});
		}
	},

	botao: {
		ativarFavorito: function() {
			$('[data-action="favoritar-opcao"]').on('click',function() {
				$(this).toggleClass('active');
			});
		},

		ativarResposta: function() {
			$('[data-action="ativar-resposta"]').on('click',function() {
				$(this)
					.addClass('active')
					.siblings('[data-action="ativar-resposta"]')
					.removeClass('active');
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
	}
}
