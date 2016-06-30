<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%--<link rel="stylesheet" type="text/css" href="Style.css">--%>
        <title>Title</title>
    </head>
    <body>
        <img src="resources/servletImage.jpg" alt="Mountain View"  name="mountain">
        <form method= "post" action="/LogIn">
            <div name="login" >
                User name:
                <input name="userName"><br>
                Password:
                <input type="password" name="psw"><br>
                <input type="submit" name="submit">
            </div>
        </form>

        <form method="post" action="/redirect/SignUp">
            <input type="submit" value="SignUp" name="submit-redirect-SignUp">
        </form>
        <br>
    </body>
</html>