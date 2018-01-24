<#assign scriptContent>

<script src="<@spring.url '/static/libs/cycle2/jquery.cycle2.min.js'/>" type="text/javascript"></script>
<script>
    $(function () {
    });
</script>

</#assign>

<@layout.portal titulo = "Início" script=scriptContent>

<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="box-simples">
                <h2>Login</h2>

                <form role="form" method="POST">
                    <div class="form-group">
                        <input type="text" class="form-control text-input" placeholder="Usuário" name="username" id="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control text-input" placeholder="Senha" name="password" id="password">
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <button type="submit" id="btnSubmit" class="btn btn-success btn-block"><i class="fa fa-unlock"></i> Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

</@layout.portal>
