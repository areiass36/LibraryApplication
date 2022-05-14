<style><%@include file="book.css" %></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<article class="book">
    <form action="book" method="POST">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Adicione um livro</h2>
        </div>
        
            <div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="title" name="title" required>
                    <label class="mdl-textfield__label" for="name">Titulo</label>
                </div>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
                <input type="text" value="" class="mdl-textfield__input" id="author" readonly required>
                <input type="hidden" value="" name="author" required>
                <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                <label for="author" class="mdl-textfield__label">Autor</label>
                <ul for="author" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                    <c:forEach items="${authors}" var="author">
                        <li class="mdl-menu__item" data-val="${author.id}"><c:out value="${author.name}"></c:out></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
                <input type="text" value="" class="mdl-textfield__input" id="category" readonly required>
                <input type="hidden" value="" name="category" required>
                <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                <label for="category" class="mdl-textfield__label">Categoria</label>
                <ul for="category" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                    <c:forEach items="${categories}" var="category">
                        <li class="mdl-menu__item" data-val="${category}"><c:out value="${category}"></c:out></li>
                    </c:forEach>
                </ul>
            </div>
        <div class="footer">
            <button id="registerButton" type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                Cadastrar
            </button>
        </div>
    </form>
</article>