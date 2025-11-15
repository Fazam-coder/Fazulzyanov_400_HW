<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>

    <#if users?has_content>
        Таблица рекордов:
        <br>
        Имя Логин
        <br>
        <#list users as u>
            ${u.name} ${u.login}
            <br>
        </#list>
    </#if>

</#macro>


</html>