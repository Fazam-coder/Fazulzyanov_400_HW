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
    $.get("/check-login?login=" + formData.login.trim(), function (response) {
        if (response === "true") {
            $("#loginStatus").text("Login already exists").removeClass("text-success").addClass("text-danger");
            $('#submitBtn').prop('disabled', true);
        } else if (response === "false") {
            $("#loginStatus").text("Login is available").removeClass("text-danger").addClass("text-success");
            $('#submitBtn').prop('disabled', false);
        }
    });
    });</script>

<#macro content>

    <form method="post" action="/sign_up" enctype="multipart/form-data">
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
        <span>Upload a photo</span>
        <input type="file" name="file">
        <br>
        <span id="loginStatus"></span>
        <input type="submit" id="submitBtn" value="Sign Up">
    </form>

</#macro>


</html>