<#setting number_format="computer">
<#macro admin titulo="" header="" breadcrumb="" script="">
<!DOCTYPE HTML>
	<html lang="pt-br">
		<head>
			<meta charset="utf-8">
			<title>${titulo} - Mapa Brasil Transparente</title>
	
			<!-- meta -->
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width,initial-scale=1.0">
			<meta name="description" content="">
			<meta name="author" content="" />
	
			<!-- BARRA DO TOPO DO GOVERNO -->
			<meta property="creator.productor" content="http://estruturaorganizacional.dados.gov.br/id/unidade-organizacional/52303">		
			
			<link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap/css/bootstrap.css'/>" media="all" />
			<link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap-datepicker/bootstrap-datepicker3.min.css'/>" media="all" />
			<link rel="stylesheet" href="<@spring.url '/static/libs/font-awesome-4.7.0/css/font-awesome.min.css'/>" media="all" />
			<link rel="stylesheet" href="<@spring.url '/static/css/admin/base.css'/>" media="all" />
			<link rel="stylesheet" href="<@spring.url '/static/libs/easy-autocomplete-1.3.5/easy-autocomplete.min.css'/>" media="screen" />
			<link rel="stylesheet" href="<@spring.url '/static/css/admin/print.css'/>" media="all" />		
		</head>
		<body>
				
			<header class="header" role="banner">
				<div class="container">
					<div class="logo">
						<h1><a href="home.php">Administrativo</a></h1>
					</div>
	
					<div class="block-my-profile">
						<strong>R <span>25</span></strong>
						<button type="button">Rafael <i class="fa fa-caret-down" aria-hidden="true"></i></button>
	
						<div class="modal-block modal-my-profile">
							<button type="button" class="button-close"><i class="fa fa-times" aria-hidden="true"></i></button>
							<div class="modal-my-profile__header">
								<div class="avatar"><strong>R</strong></div>
								<strong>Rafael Garcia Moreira</strong>
								<span>Recife PE</span>
								<button class="button--exit" type="button"><i class="fa fa-sign-out" aria-hidden="true"></i> Sair</button>
								<button class="button--secondary-a" type="button">Meu perfil</button>
								<button class="button--primary-a" type="button">Admin</button>
							</div>
							<a href="#"><span>25</span> Notificações</a>
							<span>Nome da Entidade</span>
							<span>Número de Registro</span>
						</div>
					</div>
				</div>
	
				<div class="navigation-bar">
					<div class="container">
						<button class="navigation-bar__button" type="button"><i class="fa fa-bars" aria-hidden="true"></i></button>
						<nav id="navigation-menu" class="navigation-menu" role="navigation">
							<ul role="menubar">
								<li role="menuitem" class="has-submenu" aria-haspopup="true">
									<button type="button">Avaliações</button>
	
									<ul role="menu" aria-hidden="true">
										<li><a href="painel_minhas_avaliacoes_pagina_inicial.php">Painel Minhas Avaliações</a></li>
										<li><a href="painel_geral_de_avaliacoes.php">Painel Geral de Avaliações</a></li>
									</ul>
								</li>
								<li role="menuitem" class="has-submenu" aria-haspopup="true">
									<button type="button">Entidades Avaliadas</button>
	
									<ul role="menu" aria-hidden="true">
										<li><a href="entidade_avaliada_lista.php">Painel de Entidades</a></li>
										<li><a href="entidade_avaliada_cadastro.php">Cadastro de Nova Entidade</a></li>
									</ul>
								</li>
								<li role="menuitem" class="has-submenu" aria-haspopup="true">
									<button type="button">Entidades Avaliadoras</button>
	
									<ul role="menu" aria-hidden="true">
										<li><a href="entidade_avaliadora_lista.php">Painel de Entidades</a></li>
										<li><a href="entidade_avaliadora_cadastro.php">Cadastro de Nova Entidade</a></li>
										<li><a href="entidade_avaliadora_respondentes.php">Gerenciar Membros</a></li>
									</ul>
								</li>
								<li role="menuitem" class="has-submenu" aria-haspopup="true">
									<button type="button">Avaliadores CGU</button>
	
									<ul role="menu" aria-hidden="true">
										<li><a href="entidade_avaliada_lista.php">Painel de Avaliadores</a></li>
										<li><a href="avaliadores_cgu_cadastro.php">Cadastro de Novo Avaliador</a></li>
									</ul>
								</li>
								<li role="menuitem">
									<a href="cidadaos_pesquisa.php">Cidadãos</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>

			<main id="main" class="main">
				${breadcrumb}
			
				<div class="container">
					<#nested/>		
				</div>
			</main>
	
	
			
	
			<script src="<@spring.url '/static/js/plugins/jquery-1.10.1.min.js'/>"></script>
			<script src="<@spring.url '/static/libs/bootstrap/js/bootstrap.min.js'/>"></script>
			<script src="<@spring.url '/static/js/plugins/jquery.form-validator.min.js'/>"></script>
			<script src="<@spring.url '/static/js/plugins/d3.v3.min.js'/>" charset="utf-8"></script>
			<script src="<@spring.url '/static/js/plugins/jquery.bootpag.min.js'/>" charset="utf-8"></script>
	
			<script src="<@spring.url '/static/js/admin/base.js'/>"></script>
	
			
			<script src="<@spring.url '/static/js/plugins/moment-with-locales.js'/>"></script>
			<script src="<@spring.url '/static/libs/bootstrap-datepicker/bootstrap-datetimepicker.js'/>"></script>
			<script src="<@spring.url '/static/js/plugins/jquery.form-validator.min.js'/>"></script>
			
			<!-- Inclusao dos arquivos JavaScript definidos em cada view -->
			${script!}
						
			<script>
				$(window).load(function() {
					base.mask.init();
					$.validate({
						modules : 'security'
					});
					$(".date-picker").datetimepicker({
						locale: "pt-br",
						format: "DD/MM/YYYY"
					});
					base.formulario.radioEvent();
				});
			</script>
			<noscript>O seu navegador não suporta javaScript. Por favor, atualize.</noscript>
		</body>
	</html>
</#macro>