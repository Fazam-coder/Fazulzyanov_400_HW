<html lang="en">
<#include "base.ftl">

<#macro title>Login</#macro>

<#macro content>

    <form method="post" action="/login">
        Login:
        <input type="text" name="login" placeholder="type your login here">
        Password:
        <input type="password" name="password">
        <br>
        <input type="submit" name="submit" value="login">
        <br>
        You are not registered?
        <input type="submit" name="submit" value="Sign Up">
    </form>

</#macro>


</html>