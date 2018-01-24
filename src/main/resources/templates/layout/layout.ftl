<#setting number_format="computer">
<#assign url = springMacroRequestContext.getRequestUri() />
<#macro portal titulo="" header="" script="" breadcrumb="" botoesFlutuantes="" offside="">
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="@app.context@ - CGU">
    <title>${titulo} - @app.context@</title>
    <script defer src="https://use.fontawesome.com/releases/v5.0.2/js/all.js"></script>
    <#--<script defer src="https://use.fontawesome.com/releases/v5.0.0/js/v4-shims.js"></script>-->
    <link rel="stylesheet" href="<@spring.url '/static/libs/simple-line-icons/css/simple-line-icons.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/animate.css/animate.min.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/spinkit/css/spinkit.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/whirl/dist/whirl.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/bootstrap/css/bootstrap.min.css'/>" media="screen" id="bscss"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" media="screen"/>
    <link rel="stylesheet" href="<@spring.url '/static/css/app.css'/>" id="maincss"/>
    <link rel="stylesheet" href="<@spring.url '/static/css/azul-black.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/static/css/cgu.css?v=@project.version@'/>" media="screen"/>
    <link rel="stylesheet" href="<@spring.url '/static/libs/introjs/introjs.min.css'/>" media="screen" />
</head>

<body>
<script type="text/javascript" src="<@spring.url '/static/libs/jquery/jquery-1.12.0.min.js'/>"></script>
<div class="wrapper">
    <#if !modoPopup??>
    <header class="topnavbar-wrapper">
        <nav class="navbar topnavbar" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="<@spring.url '/'/>">
                    <div class="brand-logo">
                        @project.name@<br/>
                        <small title="Versão: @project.version@ ---- Build: @buildTimestamp@" data-toggle="tooltip" data-placement="bottom"><i class="fas fa-code-branch"></i> @project.version@</small>
                    </div>
                    <div class="brand-logo-collapsed">@app.context@</div>
                </a>
            </div>

            <div class="nav-wrapper">
                <ul class="nav navbar-nav">
                    <li>
                        <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
                        <a class="hidden-xs" href="#" data-trigger-resize="" data-toggle-state="aside-collapsed">
                            <em class="fas fa-bars"></em>
                        </a>
                        <!-- Button to show/hide the sidebar on mobile. Visible on mobile only.-->
                        <a class="visible-xs sidebar-toggle" href="#" data-toggle-state="aside-toggled" data-no-persist="true">
                            <em class="fas fa-bars"></em>
                        </a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-search-open="">
                            <em class="icon-magnifier"></em>
                        </a>
                    </li>

                    <#if usuarioLogado??>
                    <li class="dropdown dropdown-list">
                        <a href="#" data-toggle="dropdown">
                            <em class="icon-bell"></em>
                            <div class="label label-danger" id="notificacoesBadge"></div>
                        </a>
                        <ul class="dropdown-menu animated fadeIn z-depth-3">
                            <li>
                                <div class="list-group" id="notificacoesTopo">
                                </div>
                            </li>
                        </ul>
                    </li>
                    </#if>

                    <li>
                        <a id="btnAjuda" style="display: none;" data-toggle="tooltip" title="Clique aqui para entender como essa página funciona" data-intro="Em qualquer página do sistema, clique nesse ícone para obter uma ajuda rápida!">
                            <i class="icon-question"></i>
                        </a>
                    </li>

                </ul>
            </div>

            <form class="navbar-form" role="search" id="formBuscaRapida">
                <div class="form-group has-feedback">
                    <input class="form-control" type="text" placeholder="Informe um ID de uma instância para acesso rápido" id="idBuscaRapida">
                    <div class="fas fa-times form-control-feedback" data-search-dismiss=""></div>
                </div>
                <button class="hidden btn btn-default" type="submit" id="btnBuscaRapida">Pesquisar</button>
            </form>

        </nav>
    </header>

    <aside class="aside">
        <div class="aside-inner">
            <nav class="sidebar" data-sidebar-anyclick-close="">
                <ul class="nav">
                    <li class="has-user-block">
                        <div id="user-block">
                            <div class="item user-block">
                                <div class="user-block-info">
                                    <#if usuarioLogado??>
                                        <span class="user-block-name">
                                            ${usuarioLogado.nome}<br/>
                                            <a href="#" id="linkUsuarioLogado" data-toggle="modal" data-target="#modalUsuarioLogado">
                                                <i class="fas fa-user"></i> Meus Dados
                                            </a>
                                        </span>

                                        <a href="<@spring.url '/saml/logout' />">
                                            <span class="user-block-role"><i class="fas fa-sign-out-alt"></i> Sair</span>
                                        </a>
                                    <#else>
                                        <a href="<@spring.url '/auth' />"><span class="user-block-name">Login <i class="fas fa-sign-in-alt"></i></span></a>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </li>


                    <li class="nav-heading ">
                        <span>Menu</span>
                    </li>
                    <li class="${(url?ends_with("@app.context@/") || url?ends_with("gov.br/"))?then("active","")}">
                        <a href="<@spring.url '/'/>" title="Avisos"><em class="fas fa-bullhorn"></em> <span>Avisos</span></a>
                    </li>

                    <#if usuarioLogado??>
                                <li class="nav-heading ">
                            <span>Módulos</span>
                        </li>

                        <#if usuarioLogado.hasPermissao("MENU_ADMINISTRACAO")>
                            <#assign isAdmin=(url?contains("/usuario") || url?contains("/unidade") || url?contains("/orgao")
                                            || url?contains("/perfi") ) />
                            <li class="${isAdmin?then("active","")}">
                                <a href="#menu_adm" title="Administração" data-toggle="collapse" aria-expanded="${isAdmin?c}">
                                    <em class="fas fa-cog"></em>
                                    <span>Administração <span class="caret"></span></span>
                                </a>
                                <ul class="nav sidebar-subnav collapse ${isAdmin?then("in","")}" id="menu_adm" aria-expanded="${isAdmin?c}">
                                    <li class="sidebar-subnav-header">Administração</li>
                                    <li class="${url?contains("auth/perfis")?then("active","")}"><a href="<@spring.url '/auth/perfil'/>"><em class="far fa-id-card"></em> Perfis</a></li>
                                    <li class="${url?contains("auth/usuario")?then("active","")}"><a href="<@spring.url '/auth/usuario'/>"><em class="fas fa-users"></em> Usuários</a></li>
                                    <li class="${url?contains("auth/orgao")?then("active","")}"><a href="<@spring.url '/auth/orgao'/>"><em class="fas fa-building"></em> Órgãos</a></li>
                                    <li class="${url?contains("auth/unidade")?then("active","")}"><a href="<@spring.url '/auth/unidade'/>"><em class="fas fa-home"></em> Unidades</a></li>
                                </ul>
                            </li>
                        </#if>
                    </#if>
                    <li><a href="<@spring.url '/swagger-ui.html'/>" title="API Rest"><em class="fas fa-plug"></em> <span>API REST</span></a></li>
                </ul>
            </nav>
        </div>
    </aside>
    </#if>

    <!-- offsidebar-->
    <aside class="offsidebar hide">
        ${offside}
    </aside>

    <#if !modoPopup??>
    <section>
        <div class="content-wrapper">
            <#if (breadcrumb?is_string && breadcrumb!="") || !breadcrumb?is_string >
            <div class="content-heading">
                ${breadcrumb}
            </div>
            </#if>
    </#if>
            <!--Aqui vai o conteudo da pagina-->
            <#nested/>

            <div class="botoes-flutuantes animated slideInRight">${botoesFlutuantes!}</div>
    <#if !modoPopup??>
        </div>
    </section>
    </#if>

</div>

<div id="carregando" style="display: none;">
    <div>
        Carregando...
        <div class="sk-cube-grid">
            <div class="sk-cube sk-cube1"></div>
            <div class="sk-cube sk-cube2"></div>
            <div class="sk-cube sk-cube3"></div>
            <div class="sk-cube sk-cube4"></div>
            <div class="sk-cube sk-cube5"></div>
            <div class="sk-cube sk-cube6"></div>
            <div class="sk-cube sk-cube7"></div>
            <div class="sk-cube sk-cube8"></div>
            <div class="sk-cube sk-cube9"></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    let springUrl = "<@spring.url '/'/>".split(";")[0];
        <#if usuarioLogado??>
        let usuarioLogado = "${usuarioLogado.nome}";
        </#if>
</script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript" src="<@spring.url '/static/libs/modernizr/modernizr.custom.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/static/libs/matchMedia/matchMedia.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/static/js/cgu.js?v=#VERSAO-SISTEMA#'/>"></script>
<script type="text/javascript" src="<@spring.url '/static/libs/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/static/libs/bootstrap-confirmation/bootstrap-confirmation.min.js'/>"></script>
<script src="<@spring.url "/static/libs/jquery-validation/jquery.validate.min.js"/>"></script>
<script src="<@spring.url "/static/libs/jquery-validation/additional-methods.min.js"/>"></script>
<script src="<@spring.url "/static/libs/jquery-validation/jquery.validate.options.js"/>"></script>
<script src="<@spring.url "/static/libs/jquery-mask-plugin/jquery.mask.min.js"/>"></script>
<script src="<@spring.url "/static/libs/jquery.easing/js/jquery.easing.js"/>"></script>
<script src="<@spring.url "/static/libs/jQuery-Storage-API/jquery.storageapi.js"/>"></script>
<script src="<@spring.url "/static/libs/animo.js/animo.js"/>"></script>
<script src="<@spring.url "/static/libs/slimScroll/jquery.slimscroll.min.js"/>"></script>
<script src="<@spring.url "/static/libs/angle/app.js"/>"></script>
<#include "mensagens.ftl"/>

<#if !modoPopup??>
    <#include "../usuario/usuarioLogado.ftl"/>
    <#if usuarioLogado??>
        <@scriptsUsuarioLogado/>
    </#if>
</#if>

<!-- Inclusao dos arquivos JavaScript definidos em cada view -->
${script!}

<script>
    $(function(){
        $("#formBuscaRapida").submit(function(e){
            e.preventDefault();
            const id = parseInt($("#idBuscaRapida").val());
            if (isNaN(id)) {
                alert("Informe um número válido.");
                return;
            }
            window.location.href = springUrl + "auth/instancia/" + id;
        });
        $("#btnBuscaRapida").click(function(){
            $("#formBuscaRapida").submit();
        });
    });
</script>

<script type="text/javascript" src="<@spring.url '/static/libs/introjs/intro.min.js'/>"></script>

</body>
</html>

</#macro>

