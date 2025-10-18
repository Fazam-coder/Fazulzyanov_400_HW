<html lang="en">
<#include "base.ftl">

<#macro title>Main page</#macro>

<#macro content>
    <h3>
        Hello, ${sessionUser}! Login successful
        <br>
        <#assign cookieUser = "Не установлен">
        <#if cookies?has_content>
            <#list cookies as c>
                <#if "user" == (c.name!"")?lower_case>
                    <#assign cookieUser = c.value>
                <#elseif "jsessionid" == (c.name!"")?lower_case>
                    <#assign sessionId = c.value>
                </#if>
            </#list>
        <#else>
            <#assign sessionId = session.id>
        </#if>
        Session ID = ${sessionId!}
        <br>
        Cookie user = ${cookieUser}
    </h3>
    <img src="${imagePath}">

</#macro>


</html>