<#ftl output_format="HTML">
<#include "../_formulario.ftl"/>
<#-- @ftlvariable name="usuario" type="br.gov.cgu.projetoexemplosb.negocio.auth.Usuario" -->
<#-- @ftlvariable name="perfis" type="br.gov.cgu.projetoexemplosb.negocio.auth.Perfil[]" -->
<#assign scriptContent>
<@autocompleteScripts/>
<script src="<@spring.url "/static/js/auth/CadastroUsuario.js"/>"></script>
</#assign>

<#assign breadcrumb>
<i class="fa fa-user"></i> Cadastro de Usuário
</#assign>
<@layout.portal script=scriptContent titulo="Cadastro de Usuário" breadcrumb=breadcrumb>

<#assign disabled=(usuario.ativo?? && !usuario.ativo)/>

    <form id="form" method="post">
        <input type="hidden" id="id" name="id" value="${usuario.id!}" class="form-control"/>

        <@bs.panel titulo="Dados do Usuário">
            <fieldset>
                <@campoTextoFormulario label="Nome" id="nome" obrigatorio=true value=usuario.nome! disabled=disabled />
                <div class="row">
                    <div class="col-md-4">
                        <@campoTextoFormulario label="CPF" id="cpf" obrigatorio=true value=usuario.cpf! disabled=disabled />
                    </div>
                    <div class="col-md-4" data-intro="Login de usuários internos começa com <b>CGU\</b>">
                        <@campoTextoFormulario label="Login" id="login" obrigatorio=true value=usuario.login! disabled=disabled />
                    </div>
                    <div class="col-md-4">
                        <@campoTextoFormulario label="SIAPE" id="siape" obrigatorio=false value=usuario.siape! disabled=disabled />
                    </div>
                </div>
                <@autocomplete id="unidades" label="Unidades" tipo="br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade" valores=usuario.unidades tipoValores="ENTIDADE" maximoItens=10 />
                <div class="row">
                    <div class="col-md-6">
                        <@campoTextoFormulario label="E-mail" id="email" obrigatorio=false value=usuario.email! disabled=disabled />
                    </div>
                    <div class="col-md-6">
                        <@campoTextoFormulario label="Telefone" id="telefone" obrigatorio=false value=usuario.telefone! disabled=disabled />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label">Perfis</label>
                    <div class="row equal-height">
                        <#list perfis as perfil>
                        <div class="col-lg-3 col-md-4 col-sm-6 ">
                            <@campoCheckbox label="${perfil.nome}" id="perfil_${perfil.id}"
                            isChecked=usuario.hasPerfil(perfil)
                            dataName="perfis" dataValue="${perfil.id}" />
                        </div>
                        </#list>
                    </div>
                </div>
            </fieldset>

            <a class="btn btn-default btn-labeled" href='<@spring.url "/auth/usuario"/>'><span class="btn-label"><i class="fa fa-arrow-left"></i></span> Voltar</a>
            <span class="pull-right">
                <#if !disabled>
                    <#if usuario.id??>
                        <button class="btn btn-danger btn-labeled" id="btnDesativar" data-id="${usuario.id}"><span class="btn-label"><i class="fa fa-power-off"></i></span>Inativar</button>
                    </#if>
                    <button id="btnSalvar" type="button" class="btn btn-primary btn-labeled"><span class="btn-label"><i class="fa fa-save"></i></span>Salvar</button>
                <#else>
                    <button class="btn btn-success btn-labeled" id="btnAtivar" data-id="${usuario.id}"><span class="btn-label"><i class="fa fa-power-off"></i></span>Ativar</button>
                </#if>
            </span>
        </@bs.panel>
    </form>

</@layout.portal>