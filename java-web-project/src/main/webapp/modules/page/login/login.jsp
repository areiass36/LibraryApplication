<style><%@include file="login.css" %></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<article class="login">
    <form class="mdl-card mdl-shadow--2dp" action="login" method="POST">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Bem-vindo!</h2>
        </div>
        
        <div class="mdl-card__supporting-text">
            <div class="user-avatar">
                <img id="avatar"/>
            </div>
            <div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="email" name="email">
                    <label class="mdl-textfield__label" for="email">Email</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="password" id="password" name="password">
                    <label class="mdl-textfield__label" for="password">Senha</label>
                </div>
            </div>
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                Login
            </button>
        </div>
    </form>

    <div class="register-card">
        <div>
            Nao possui uma conta? <a href="register"><span>Cadastre</span></a> uma agora mesmo!
        </div>
    </div>
</article>
<script src="./js/main/login.js"></script>