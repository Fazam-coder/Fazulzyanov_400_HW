<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<#macro content>

    <form method="post" action="/sign_up">
        Name:
        <input type="text" name="name">
        Lastname:
        <input type="text" name="lastname">
        <br>
        Login:
        <input type="text" name="login" placeholder="type your login here">
        Password:
        <input type="password" name="password">
        <br>
        <input type="submit" value="Sign Up">
    </form>

</#macro>


</html>