<#assign scriptContent>

<script src="<@spring.url '/static/libs/cycle2/jquery.cycle2.min.js'/>" type="text/javascript"></script>
<script>
    $(function () {
    });
</script>

</#assign>

<@layout.portal titulo = "Início" script=scriptContent>
    <div class="row">
        <#if !usuarioLogado??>
            <div class="col-md-3">
                <@bs.panel titulo="<i class='fas fa-lock'></i> Acesso ao sistema" intro="Clique aqui para fazer o login no sistema.">
                    <h3 class="text-thin text-muted">Login</h3>
                    <a href="<@spring.url '/auth/usuario/'/>">Clique aqui</a> para fazer login

                    <h3 class="text-thin text-muted">Novos Usuários da CGU</h3>
                    <p>
                        Usuários da CGU devem solicitar o acesso ao sistema através do <a href="https://acesso.cgu.gov.br/">Sistema Acesso</a>.
                    </p>

                    <h3 class="text-thin text-muted">Novos Usuários Externos</h3>
                    <p>
                        Usuários externos devem solicitar o acesso ao Gestor do seu Órgão.
                        O gestor do órgão pode conceder acessos através do <a href="https://cadu.cgu.gov.br/">CADU</a>.
                    </p>
                </@bs.panel>
            </div>
            <div class="col-md-9">
        <#else>
            <div class="col-md-12">
        </#if>
            <@bs.panel titulo="<i class='fas fa-bullhorn'></i> Avisos">
                Nenhum aviso disponível.
            </@bs.panel>
        </div>
    </div>

</@layout.portal>
