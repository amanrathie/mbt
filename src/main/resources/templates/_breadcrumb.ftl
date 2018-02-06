<#--CÓDIGO NÃO TESTADO -->
<#macro breadcrumb path="" title="" subtitle="" buttons=[]>
	<div class="block-title">
		<div class="container">
			<span class="breadcrumb"><strong>Você está aqui:</strong> ${path}</span>			
			<h2 class="title-page pull-left">${title}</h2>
			<#if subtitle>
				<h3 class="title-text-1">${subtitle}</h3>
			</#if>
			<#if buttons>
				<#list buttons as b>
					<a href="${b.url}" class="button--primary-a pull-right" role="button">b.name/a>
				</#list>
			</#if>			
		</div>
	</div>
</#macro>