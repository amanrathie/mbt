<#--CÓDIGO NÃO TESTADO -->
<#macro breadcrumb path="" title="" titleClass="" hasTitleButtonDiv=false hasComplement=false titleDivClass="" hasSubtitle=false subtitle="" subtitleClass="" buttonDivClass="" hasButtons=false buttons=[] entidadeResponsavel="" nomeAdministrador="" inicio="" termino="">
	<div class="block-title">
		<div class="container">
			<span class="breadcrumb"><strong>Você está aqui:</strong> ${path}</span>	
			
			<#if hasTitleButtonDiv>			
				<div class="row">		
					<div class="${titleDivClass}">		
						<h2 class="${titleClass}">${title}</h2>
						<#if subtitle??>
							<h3 class="${subtitleClass}">${subtitle}</h3>
						</#if>
					</div>
					<div class="${buttonDivClass}">
						<#if buttons??>
							<#list buttons as b>					
								${b}
							</#list>
						</#if>
					</div>			
				</div>				
			
			<#else>
				<h2 class="${titleClass}">${title}</h2>
				<#if hasSubtitle>
					<h3 class="${subtitleClass}">${subtitle}</h3>
				</#if>
				<#if hasButtons>
					<#list buttons as b>					
						${b}
					</#list>
				</#if>
				<#if hasComplement>
					<div class="row complemento">
						<div class="col-xs-8">
							<div>${entidadeResponsavel}</div>
							<div>${nomeAdministrador}</div>
							<div>Início ${inicio?date} | Término ${termino?date}</div>
						</div>
						<div class="col-xs-4">
							<a href="criando_avaliacao_independente.php" class="button--icon-a pull-right"><i class="fa fa-pencil" aria-hidden="true"></i> Editar</a>
						</div>
					</div>
				</#if>
			</#if>	
		</div>
	</div>
</#macro>