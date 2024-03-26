<%-- 
    Document   : form
    Created on : Feb 13, 2024, 1:52:40 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pré-inscréption</title>
        <link rel="stylesheet" type="text/css" href="css/styleform.css" />
    </head>
    <body>
        <form action="preform" method="POST">
			<input type="email" name='email'/>
                        <input type="submit"  value="submit"/>
		</form>
        <div class="container right-panel-active">
	<!-- Sign Up -->
	<div class="container__form container--signup">
		<form action="preform" method="POST" class="form" id="form1">
			<h2 class="form__title">Pré-inscription</h2>
			<input type="email" name='email' placeholder="Entrez votre email" class="input" />
                        <input type="submit" class="btn" value="Sign Up"/>
		</form>
	</div>

	<!-- Sign In -->
	<div class="container__form container--signin">
		<form action="#" class="form" id="form2">
			<h2 class="form__title">Sign In</h2>
			<input type="email" placeholder="Email" class="input" />
			<input type="password" placeholder="Password" class="input" />
			<a href="#" class="link">Forgot your password?</a>
			<button class="btn">Sign In</button>
		</form>
	</div>

	<!-- Overlay -->
	<div class="container__overlay">
		<div class="overlay">
			<div class="overlay__panel overlay--left">
				<button class="btn" id="signIn">Open</button>
			</div>
			<div class="overlay__panel overlay--right">
				<button class="btn" id="signUp">Close</button>
			</div>
		</div>
	</div>
</div>
    </body>
    <script src="js/mainform.js"></script>
</html>
