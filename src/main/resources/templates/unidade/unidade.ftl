<#ftl output_format="HTML">
<#include "../_formulario.ftl"/>
<#-- @ftlvariable name="tipos" type="br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade[]" -->
<#-- @ftlvariable name="dto" type="br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade" -->
<#assign scriptContent>
<@autocompleteScripts/>
<script src="<@spring.url "/static/js/unidade/CadastroUnidade.js"/>"></script>
</#assign>

<#assign breadcrumb>
<i class="fa fa-home"></i> Cadastro de Unidade
</#assign>
<@layout.portal script=scriptContent titulo="Cadastro de Unidade" breadcrumb=breadcrumb>

<#assign disabled=(dto.ativo?? && !dto.ativo)/>
    <form id="form" method="post">
        <@spring.bind "dto" />

        <input type="hidden" id="id" name="id" value="${dto.id!}" class="form-control"/>

        <@bs.panel titulo="Dados da Unidade">
            <fieldset>
                <div class="row">
                    <div class="col-md-12">
                        <@campoTextoFormulario label="Nome" id="nome" obrigatorio=true value=dto.nome! disabled=disabled />
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <@autocomplete id="idOrgao" label="Órgão" tipo="br.gov.cgu.projetoexemplosb.negocio.unidade.Orgao" valores=[dto.idOrgao!] maximoItens=1 obrigatorio=true buscaAvancada="orgao"/>
                    </div>
                    <div class="col-md-6">
                        <@autocomplete id="idUnidadeSuperior" label="Unidade Superior" tipo="br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade" valores=[dto.unidadeSuperior!] maximoItens=1 tipoValores="ENTIDADE" buscaAvancada="unidade"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <@campoSelectFormulario label="Tipo" id="tipo" opcaoPadrao="-- Selecione --" valorSelecionado=dto.tipo opcoes=tipos disabled=disabled obrigatorio=true />
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <@campoTextoFormulario label="Código" id="codigo" obrigatorio=true value=dto.codigo! disabled=disabled />
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <@campoTextoFormulario label="Sigla" id="sigla" obrigatorio=true value=dto.sigla! disabled=disabled />
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <@campoTextoFormulario label="E-mail" id="email" obrigatorio=false value=dto.email! disabled=disabled />
                    </div>
                    <div class="col-md-4">
                        <@campoTextoFormulario label="Telefone" id="telefone" obrigatorio=false value=dto.telefone! disabled=disabled placeholder="(__) _____-_____"/>
                    </div>
                    <div class="col-md-4">
                    <@autocomplete id="idMunicipio" label="Município" tipo="br.gov.cgu.projetoexemplosb.negocio.nucleo.Municipio" valores=[dto.municipio!] tipoValores="ENTIDADE" maximoItens=1 obrigatorio=true />
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <@autocomplete id="gestores" label="Gestores desta Unidade" tipo="br.gov.cgu.projetoexemplosb.negocio.auth.Usuario" valores=dto.gestores tipoValores="ENTIDADE" maximoItens=10 />
                    </div>
                </div>
            </fieldset>

            <a class="btn btn-default btn-labeled" href='<@spring.url "/auth/unidade"/>'><span class="btn-label"><i class="fa fa-arrow-left"></i></span> Voltar</a>
            <#if !disabled>
                <span class="pull-right">
                    <#if dto.id??>
                        <button class="btn btn-danger btn-labeled" id="btnInativar" data-id="${dto.id}"><span class="btn-label"><i class="fa fa-power-off"></i></span>Inativar</button>
                    </#if>
                    <button type="button" class="btn btn-primary btn-labeled" id="btnSalvar"><span class="btn-label"><i class="fa fa-save"></i></span>Salvar</button>
                </span>
            <#else>
                <button class="btn btn-success pull-right btn-labeled" id="btnAtivar" data-id="${dto.id}"><span class="btn-label"><i class="fa fa-power-off"></i></span>Ativar</button>
            </#if>
        </@bs.panel>
    </form>

</@layout.portal>