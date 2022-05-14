<style><%@include file="booklist.css" %></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<article class="booklist">
    <h2>Lista da livros</h2>

    <form>
        <div class="form-line">
            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="title" name="title">
                <label class="mdl-textfield__label" for="title">Titulo</label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
                <input type="text" value="" class="mdl-textfield__input" id="author" readonly>
                <input type="hidden" value="" name="author">
                <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                <label for="author" class="mdl-textfield__label">Autor</label>
                <ul for="author" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                    <li class="mdl-menu__item" data-val="" data-selected="true"></li>
                    <c:forEach items="${authors}" var="author">
                        <li class="mdl-menu__item" data-val="${author.name}"><c:out value="${author.name}"></c:out></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        
        <div class="form-line">
            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
                <input type="text" value="" class="mdl-textfield__input" id="category" readonly>
                <input type="hidden" value="" name="category">
                <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                <label for="category" class="mdl-textfield__label">Categoria</label>
                <ul for="category" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                    <li class="mdl-menu__item" data-val="Undefined" data-selected="true"></li>
                    <c:forEach items="${categories}" var="category">
                        <li class="mdl-menu__item" data-val="${category}"><c:out value="${category}"></c:out></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
                <input type="text" value="" class="mdl-textfield__input" id="available" readonly>
                <input type="hidden" value="" name="available">
                <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
                <label for="available" class="mdl-textfield__label">Disponivel</label>
                <ul for="available" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                    <li class="mdl-menu__item" data-val="" data-selected="true"></li>
                    <li class="mdl-menu__item" data-val="Available">Sim</li>
                    <li class="mdl-menu__item" data-val="Not Available">Nao</li>
                </ul>
            </div>
        </div>

        <div class="form-line">
            <button class="search-button mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                Pesquisar
            </button>
        </div>
    </form>

    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp booklist-table">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Title</th>
                <th>Author</th>
                <th>Category</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${booklist}" var="book">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value="${book.title}"></c:out></td>
                <td><c:out value="${book.author.name}"></c:out></td>
                <td><c:out value="${book.category}"></c:out></td>
                <td>
                    <form action="booklist" method="POST">
                        <input type="hidden" value="${book.id}" name="book">
                        <button class="btn-borrow mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                            <i class="material-icons-outlined">book</i>
                        </button>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</article>