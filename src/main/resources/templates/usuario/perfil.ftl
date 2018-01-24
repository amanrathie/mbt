<#ftl output_format="HTML">
<#include "../_formulario.ftl"/>
<#-- @ftlvariable name="perfil" type="br.gov.cgu.projetoexemplosb.negocio.auth.Perfil" -->
<#-- @ftlvariable name="permissoes" type="br.gov.cgu.projetoexemplosb.negocio.auth.Permissao[]" -->
<#assign scriptContent>
<@autocompleteScripts/>
<script src="<@spring.url "/static/js/auth/CadastroPerfil.js"/>"></script>
</#assign>

<#assign breadcrumb>
<i class="far fa-id-card"></i> Cadastro de Perfil
</#assign>
<@layout.portal script=scriptContent titulo="Cadastro de Perfil" breadcrumb=breadcrumb>

    <form id="form" method="post">
        <input type="hidden" id="id" name="id" value="${perfil.id!}" class="form-control"/>

        <@bs.panel titulo="Dados do Perfil">
            <fieldset>
                <@campoTextoFormulario label="Nome" id="nome" obrigatorio=true value=perfil.nome!/>

                <@campoCheckbox label="Oferecido no CADU?" id="oferecidoPublicoExterno" isChecked=perfil.oferecidoPublicoExterno />

                <div class="form-group">
                    <label class="control-label">Permissões</label>
                    <div class="row equal-height">
                        <#list permissoes as p>
                        <div class="col-lg-3 col-md-4 col-sm-6 ">
                            <@campoCheckbox label="${p.nome}" id="permissao_${p.name()}"
                            isChecked=perfil.permissoes?seq_contains(p)
                            dataName="permissoes" dataValue="${p.name()}" />
                        </div>
                        </#list>
                    </div>
                </div>
            </fieldset>

            <a class="btn btn-default btn-labeled" href='<@spring.url "/auth/perfil"/>'><span class="btn-label"><i class="fa fa-arrow-left"></i></span> Voltar</a>
            <span class="pull-right">
                <button id="btnSalvar" type="button" class="btn btn-primary btn-labeled"><span class="btn-label"><i class="fa fa-save"></i></span>Salvar</button>
            </span>
        </@bs.panel>
    </form>

</@layout.portal>