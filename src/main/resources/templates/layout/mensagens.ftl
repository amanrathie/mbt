<script>
    $(function () {
    <#if erros??>
        <#list erros as erro>
            toastr.error("${erro}");
        </#list>
    </#if>

    <#if sucesso??>
        toastr.success("${sucesso}");
    </#if>

    <#if info??>
        toastr.info("${info}");
    </#if>

    <#if erro??>
        toastr.error("${erro}");
    </#if>

    <#if alerta??>
        toastr.warning("${alerta}");
    </#if>
    <#if alertas??>
        <#list alertas as a>
            toastr.warning("${a}");
        </#list>
    </#if>
    });

    $(function() {
        let successMessage = cgu.cookies.read("showSuccessMessage");
        if (successMessage) {
            toastr.success(successMessage);
            cgu.cookies.erase("showSuccessMessage");
        }

        let warningMessage = cgu.cookies.read("showWarningMessage");
        if (warningMessage) {
            toastr.error(warningMessage);
            cgu.cookies.erase("showWarningMessage");
        }

        let errorMessage = cgu.cookies.read("showErrorMessage");
        if (errorMessage) {
            toastr.error(errorMessage);
            cgu.cookies.erase("showErrorMessage");
        }
    })
</script>
