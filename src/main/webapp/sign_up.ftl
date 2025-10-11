<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<script>
    $(document).on("input", "#login", function () {
    var formData = {
        name: $('#name').val(),
        lastname: $('#lastname').val(),
        login: $('#login').val(),
        password: $('#password').val(),
    };

    // if (!formData.name || !formData.lastname || !formData.login || !formData.password) {
    //     alert('Заполните все поля!');
    //     return;
    // }

    $.get("/check-login?login=" + formData.login.trim(), function (response) {
        if (response === "true") {
            $("#loginStatus").text("Логин занят")
            $('#submitBtn').prop('disabled', true);
        } else {
            $("#loginStatus").text("Логин свободен")
            $('#submitBtn').prop('disabled', false);
        }
    })

    });</script>

<#macro content>

    <form method="post" action="/sign_up">
        Name:
        <input type="text" name="name">
        Lastname:
        <input type="text" name="lastname">
        <br>
        Login:
        <input type="text" name="login" id="login" placeholder="type your login here">
        Password:
        <input type="password" name="password">
        <br>
        <span id="loginStatus"></span>
        <input type="submit" id="submitBtn" value="Sign Up">
    </form>

</#macro>


</html>