<@layout.portal titulo = "Erro de Servidor">
    <@bs.panel>
    <div class="text-center">
        <h1><i class="far fa-5x fa-frown text-info"></i></h1>
        <p>Aconteceu um erro no nosso servidor. Tente novamente mais tarde.</p>
        <p>Os administradores já foram notificados do erro.</p>
        <p>${.now}</p>
        <br/>
        <p><b>Código do erro:</b></p>
        <h2 style="color:crimson">${uniqueID!}</h2>

    </div>
    </@bs.panel>
</@layout.portal>