<#ftl output_format="HTML">
<#include "../_formulario.ftl"/>
<#-- @ftlvariable name="usuarioLogado" type="br.gov.cgu.projetoexemplosb.negocio.auth.Usuario" -->
<#if usuarioLogado??>
<#assign matrizFrequenciaRecebimentoEmail = [   [0, " Não receber e-mail"],
                                                [1, " Receber um e-mail para cada notificação/pendência"],
                                                [2, " Receber lista do dia"],
                                                [3, " Receber lista da semana"]] />

<#macro scriptsUsuarioLogado>
<script xmlns="http://www.w3.org/1999/html">
    $(function () {
        // Validação do formulário de dados do usuário logado
        $("#formUsuarioLogado").validate({
            rules: {
                emailUsuarioLogado: {required: false, maxlength: 200, email: true}
                ,telefoneUsuarioLogado: {required: false, maxlength: 25, telefone: true}
            }
        });

        $("#btnSalvarUsuarioLogado").click(function() {
            const form = $('#formUsuarioLogado');

            if(!form.valid()) {
                return;
            }

            const data = form.serializeArray();

            $.post("<@spring.url '/auth/usuarioLogado/salvar' />", data, function(){
                cgu.exibirSucesso('Operação realizada com sucesso.');
            });
        });

        // Botao ajax para gerar nova chave
        $("#btnChaveApiUsuarioLogado").click(function() {
            $.get("<@spring.url '/auth/usuarioLogado/gerarChaveApi' />", function(data){
                $("#chaveApiUsuarioLogado").val(data);
            });
        });
    });
</script>
</#macro>

<div class="modal fade" id="modalUsuarioLogado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog modal-lg " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${usuarioLogado.nome}</h4>
            </div>
            <form id="formUsuarioLogado" method="POST">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="emailUsuarioLogado">E-mail</label>
                                <input type="text" id="emailUsuarioLogado" name="emailUsuarioLogado" value="${usuarioLogado.email!}" class="form-control" />
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="telefoneUsuarioLogado">Telefone</label>
                                <input type="text" id="telefoneUsuarioLogado" name="telefoneUsuarioLogado" value="${usuarioLogado.telefone!}" class="form-control" />
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="chaveApiUsuarioLogado">Chave API</label>
                                <span class="input-group">
                            <input  id="chaveApiUsuarioLogado" name="chaveApiUsuarioLogado" type="text" value="${usuarioLogado.chaveApi!}" class="form-control" readonly="readonly"/>
                            <span class="input-group-addon" data-toggle="tooltip" title="Gerar nova chave" id="btnChaveApiUsuarioLogado">
                                <i class="fas fa-sync-alt"></i>
                            </span>
                        </span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="frequenciaEnvioEmailUsuarioLogado">Frequência de recebimento de e-mails</label>
                        <@campoRadiobuttons id="frequenciaRecebimentoEmail" matriz=matrizFrequenciaRecebimentoEmail valorAtual="${usuarioLogado.frequenciaRecebimentoEmail}" tamanho=6/>
                    </div>

                    <div class="form-group">
                        <label for="unidadesDoUsuarioLogado">Unidades</label>
                        <div>
                            <#list usuarioLogado.unidades as unidade>
                                <h4>
                                    <span class="label label-info" title="${unidade.sigla}">${unidade.toString()}</span>
                                </h4>
                            </#list>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button id="btnFechar" type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Fechar</button>
                    <button id="btnSalvarUsuarioLogado" type="button" class="btn btn-primary"><i class="fa fa-check"></i> Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>

</#if>