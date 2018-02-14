<#include "layout/layout.ftl">
<#include "../_formulario.ftl">
<#include "../_breadcrumb.ftl">

<#assign scriptContent>
<script>	
	
	 $( document ).ready(function() {	 	
	 	buscaAvaliacoes();       	       
    });
    
    function configuraFiltro(){
    	var filtro = {};
    	filtro.tipo = $("#tipo").val(); 
    	filtro.fase = $("#fase").val();
    	filtro.poder = $("#poder").val();
    	filtro.ativo = $("#ativo").val();
    	console.log(filtro.ativo);
    	return filtro;	
    }

	function montaDivAvaliacoes(avaliacao){
		return "<div class='dmb-30 block-painel'>" +
					"<div class='block-painel__top'>" +
						"<div class='row'>" +
							"<div class='col-xs-7'>" +
								"<a href='#' class='title-section'>" + avaliacao.nome + "</a>" +
								"<div class='row dmt-5'>" +
								 	"<div class='col-xs-3'>" +
								 		"<strong>" + avaliacao.poder + "</strong>" +
								 	"</div>" + 
									 "<div class='col-xs-4'>" +
										"<strong>" + "Entidade responsável" + "</strong>" +
									"</div>" +
								"</div>" +
							"</div>" + 
							"<div class='col-xs-5 text-right'>" +
									"<a href='#' class='button--primary-a dmt-10 dmr-10'>Ver minhas atividades</a>" +
									"<a href='#' class='button--primary-a dmt-10'>Acompanhar</a>" +
							"</div>" +
						"</div>" + 
					"</div>" +
					
					"<div class='dpt-20 dpb-20 dpl-20 dpr-20'>" +
						"<div class='row'>" +
							"<div class='col-xs-2'>" +
								"<div class='block-painel__calendario'>" +
									"<p><small>Início 00/00/0000</small></p>" +
									"<p><small>Término 00/00/0000</small></p>" +
								"</div>" +
							"</div>" +
							"<div class='col-xs-2 dpt-10'>" +
								"<div class='toggle-wrapper'>" +
									"<p class='toggle-text'>Ativa</p>" +
									"<label class='toggle'>" +
										"<input type='checkbox'/><div></div>" +
									"</label>" +
									"<p class='toggle-text'>Inativa</p>" +
								"</div>" +
							"</div>" +
							"<div class='col-xs-3 text-center'>" +
								"<p class='block-painel__avaliacao block-painel__avaliacao-back-yellow'>" + avaliacao.tipo + "</p>" +
								"<p><small><i>" + avaliacao.fase + "</i></small></p>" +
							"</div>" +
						"</div>" +
					"</div>" + 
			"</div>"		
				;
	};
	
	function exibeAvaliacoesIniciais(avaliacoes){	
		$('#avaliador-3').empty();	
		if(avaliacoes.recordsTotal > 0){	
			$.each(avaliacoes.data, function(index, avaliacao){
				$('#avaliador-3').append(montaDivAvaliacoes(avaliacao));
			});
		
			configuraPaginacao(avaliacoes.recordsTotal);
		}else{
				exibeMensagemVazio();			
		}			
	};		
	
	function exibeAvaliacoes(avaliacoes){		
		$('#avaliador-3').empty();
		
		if(avaliacoes.recordsTotal > 0){
			$.each(avaliacoes.data, function(index, avaliacao){
				$('#avaliador-3').append(montaDivAvaliacoes(avaliacao));
			});	
			reconfiguraPaginacao(avaliacoes.recordsTotal);
		}else{
			exibeMensagemVazio();			
		}
						
	};	
	
	function exibeMensagemVazio(){
		var mensagem = "Nenhum registro encontrado";
		$('#avaliador-3').html("<p>"+mensagem+"</p>");
		$('#block-pagination').empty();
	};
	
	function reconfiguraPaginacao(numRegistros){
		$('#block-pagination').bootpag({		
			<#-- 15 é a quantidade default de itens por pagina -->		
            total: Math.ceil(numRegistros/15)
        });
	}
	
	function configuraPaginacao(numRegistros){
		$('#block-pagination').bootpag({		
			<#-- 15 é a quantidade default de itens por pagina -->		
            total: Math.ceil(numRegistros/15)
        }).on("page", function(event, num){
        	recarregaAvaliacoes(num, configuraFiltro())        	        	         
        });
	};
	
	function recarregaAvaliacoes(num, filtro){
		$.ajax({
			type:'GET',
			url: '/mbt/admin/api/auth/painel_geral_avaliacoes',
			data: {
					numPagina: num, 
					tipo: filtro.tipo, 
					fase: filtro.fase, 
					poder: filtro.poder,
					ativo: filtro.ativo},			
			success: exibeAvaliacoes						
		});
		
	};	
	
	function buscaAvaliacoes(){
		$.ajax({
   			type:'GET',
   			dataType: 'json',
			url:'/mbt/admin/api/auth/painel_geral_avaliacoes',			
			success: exibeAvaliacoesIniciais		
		});
	};
   	
	
	$('#btnFiltrar').click(function(event){
		event.preventDefault();
		recarregaAvaliacoes(1, configuraFiltro());		
	});
	
</script>
</#assign>


<#assign breadcrumb>
	<#assign path>
		<a href="#">Administrativo</a> > <a href="#">Painel Geral de Avaliações</a>		
	</#assign>
	<#assign buttonNovaAvaliacao>
		<a href="#" class="button--primary-a pull-right" role="button">Nova avaliação</a>
	</#assign>
	<@breadcrumb path=path title="Painel Geral de Avaliações" titleClass="title-page pull-left" hasTitleButtonDiv=false hasComplement=false hasSubtitle=false hasButtons=true buttons=[buttonNovaAvaliacao]>
		
	</@breadcrumb>>
</#assign>

<@admin titulo="Painel Geral de Avaliações" breadcrumb=breadcrumb script=scriptContent>
	<section class="status-do-filtro ativo dmb-30">
			<div class="status-do-filtro-inativo">
				<div class="block-rate position-relative block-rate--gray">
					<h4 class="block-rate__title-iconed btn-mostrar-filtro"><i class="fa fa-filter" aria-hidden="true"></i>Filtros de pesquisa</h4>
				</div>
			</div>		
			
			<div class="status-do-filtro-ativo">
				<div class="block-rate position-relative block-rate--gray">
					<h4 class="block-rate__title-iconed"><i class="fa fa-filter" aria-hidden="true"></i>Filtros de pesquisa</h4>
					<a class="btn-mostrar-filtro a--position-left dml-300 dmt-22"><b>Ocultar filtros</b></a>
					<div class="dmt-30 dmb-30 dml-20 dmr-20">					
						<form action="#" method="post">
							<fieldset>
								<div class="row">
									<div class="col-xs-3">
										<@campoSelectFormulario label="Tipo de avaliação" id="tipo" opcoes=tipos valorSelecionado=filtro.tipo opcaoPadrao="Selecione" opcaoTodos=true />
									</div>
									<div class="col-xs-3">
										<@campoSelectFormulario label="Fase de avaliação" id="fase" opcoes=fases valorSelecionado=filtro.fases opcaoPadrao="Selecione" opcaoTodos=true />
									</div>
									<div class="col-xs-3">										
										<@campoSelectFormulario label="Poder" id="poder" opcoes=poderes valorSelecionado=filtro.poder opcaoPadrao="Selecione" opcaoTodos=true opcaoNenhum=true />
									</div>
									<div class="col-xs-3">
										<label for="ativo">Situação </label>
										<select class="form-control" id="ativo" name="ativo">
                							<option value="" selected>Todos</option>
                							<option value="true">Ativos</option>
                							<option value="false">Inativos</option>
            							</select>
									</div>
								</div>
								<div class="row dmt-30">
									<div class="col-xs-6">
										<div class="block-filter__form-search full-width" action="#" method="post">
											<fieldset>

												<label for="block-filter__form-search__text" class="sr-only">Pesquise por entidade responsável</label>
												<input type="text" class="form-control" id="block-filter__form-search__text" placeholder="Pesquise por entidade responsável">
												<button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>

											</fieldset>
										</div>
									</div>
									<div class="col-xs-6">
										<input id="btnFiltrar" type="submit" class="button--secondary-a pull-right dml-10" value="Filtrar">
										<a href="#" class="button--icon-a button--bigger button-print pull-right"><i class="fa fa-print" aria-hidden="true"></i> Imprimir</a>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>	
			</div>
		</section>

		<section class="block-filter-chart">
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="avaliador-2">
					
					<div id="avaliador-3">
						
					</div>				
							
					<div class="block-pagination">
						<div id="block-pagination" class="block-pagination__list">
						
						</div>
						<#-- 
						<div class="block-pagination__form-show">
							<fieldset>
								<legend>Formulário de exibição de linhas</legend>
								<@campoSelectFormulario label="EXIBIR" id="itensPorPagina" tipoOpcao="ARRAY" opcoes=itensPorPagina valorSelecionado=filtro.tamanhoPagina opcaoPadrao="10" opcaoTodos=false />
								<label for="block-pagination__form-search__list-4">Exibir</label>
								<select id="block-pagination__form-search__list-4">									
									<option value="10">10 linhas</option>
									<option value="50">50 linhas</option>
									<option value="100">100 linhas</option>
								</select>
																
							</fieldset>
						</div>
						-->
						<#-- 
						<form class="block-pagination__form-search" action="#" method="post">
							<fieldset>
								<legend>Formulário de busca de páginas</legend>

								<label for="block-pagination__form-search__text-4">Ir para a página:</label>
								<input type="text" id="block-pagination__form-search__text-4">
								<input type="submit" value="IR">
							</fieldset>
						</form>
						 -->
					</div>
				</div>
			</div>
		</section>
</@admin>