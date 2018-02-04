<#setting number_format="computer">
<#macro admin titulo="" header="" breadcrumb="">
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

		<link rel="shortcut icon" href="assets/media/img/ico/favicon.ico" type="image/x-icon" />
		
		<link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap/css/bootstrap.css'/>" media="all" />
		<link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap-datepicker/bootstrap-datepicker3.min.css'/>" media="all" />
		<link rel="stylesheet" href="<@spring.url '/static/libs/font-awesome-4.7.0/css/font-awesome.min.css'/>" media="all" />
		<link rel="stylesheet" href="<@spring.url '/static/css/admin/base.css'/>" media="all" />
		<link rel="stylesheet" href="<@spring.url '/static/libs/easy-autocomplete-1.3.5/easy-autocomplete.min.css'/>" media="screen" />
		<link rel="stylesheet" href="<@spring.url '/static/css/admin/print.css'/>" media="all" />
		<!--[if IE 8]><link rel="stylesheet" href="assets/media/css/ie8.css' media="all" /><![endif]-->



		<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i,800,800i" rel="stylesheet">  -->

		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js'></script>
			<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9;">
		<![endif]-->
	</head>
	<body>
		<!-- <div id="barra-brasil" style="background:#7F7F7F; height: 20px; padding:0 0 0 10px;display:block;"> 
			<ul id="menu-barra-temp" style="list-style:none;">
				<li style="display:inline; float:left;padding-right:10px; margin-right:10px; border-right:1px solid #EDEDED"><a href="http://brasil.gov.br" style="font-family:sans,sans-serif; text-decoration:none; color:white;">Portal do Governo Brasileiro</a></li>
				<li><a style="font-family:sans,sans-serif; text-decoration:none; color:white;" href="http://epwg.governoeletronico.gov.br/barra/atualize.html">Atualize sua Barra de Governo</a></li>
			</ul>
		</div> -->
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
	<div class="block-title">
		<div class="container">
			<span class="breadcrumb"><strong>Você está aqui:</strong> ${breadcrumb}</span>
			<h2 class="title-page pull-left">${titulo}</h2>
			<a href="criando_avaliacao_da_gestao.php" class="button--primary-a pull-right" role="button">Nova avaliação</a>
		</div>
	</div>

	<div class="container">
		<#nested/>		
	</div>
</main>


		

		<script src="<@spring.url '/static/js/plugins/jquery-1.10.1.min.js'/>"></script>
		<script src="<@spring.url '/static/libs/bootstrap/js/bootstrap.min.js'/>"></script>
		<script src="<@spring.url '/static/js/plugins/jquery.form-validator.min.js'/>"></script>
		<script src="<@spring.url '/static/js/plugins/d3.v3.min.js'/>" charset="utf-8"></script>

		<script src="<@spring.url '/static/js/admin/base.js'/>"></script>

		
<script src="<@spring.url '/static/js/plugins/moment-with-locales.js'/>"></script>
<script src="<@spring.url '/static/libs/bootstrap-datepicker/bootstrap-datetimepicker.js'/>"></script>
<script src="<@spring.url '/static/js/plugins/jquery.form-validator.min.js'/>"></script>

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