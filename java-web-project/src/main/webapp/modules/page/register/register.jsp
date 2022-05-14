<style><%@include file="register.css" %></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<article class="register">
    <form class="mdl-card mdl-shadow--2dp" action="register" method="POST">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Cadastre-se na nossa plataforma!</h2>
        </div>
        
        <div class="mdl-card__supporting-text">
            <div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="name" name="name" required>
                    <label class="mdl-textfield__label" for="name">Nome</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="email" name="email" required>
                    <label class="mdl-textfield__label" for="email">Email</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="password" id="password" name="password" required>
                    <label class="mdl-textfield__label" for="password">Senha</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="password" id="confirm-password" name="confirm-password" required>
                    <label class="mdl-textfield__label" for="confirm-password">Confimar senha</label>
                </div>
            </div>
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <button id="registerButton" type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                Cadastrar
            </button>
        </div>
    </form>
</article>
<script src="./js/main/register.js"></script>