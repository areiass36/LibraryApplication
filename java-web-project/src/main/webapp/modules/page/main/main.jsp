<style><%@include file="main.css" %></style>
<%@ page import="com.barretoareias.model.bean.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% User user = (User)session.getAttribute("userLogged");%>
<article class="main">

    <h2>Meus livros</h2>

    <div class="user-info">
        <img id="avatar"/>
        <p id="user-name"><%= user.getName() %></p>
        <p id="user-email"><%= user.getEmail() %></p>
    </div>
    
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp booklist-table">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Title</th>
                <th class="mdl-data-table__cell--non-numeric">Author</th>
                <th class="mdl-data-table__cell--non-numeric">Category</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${borrowedBooks}" var="borrowedBook">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value="${borrowedBook.book.title}"></c:out></td>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value="${borrowedBook.book.author.name}"></c:out></td>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value="${borrowedBook.book.category}"></c:out></td>
                    <td>
                        <form action="returnBook" method="POST">
                            <input type="hidden" value="${borrowedBook.book.id}" name="book">
                            <button class="btn-borrow mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                                <i class="material-icons-outlined">assignment_return</i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</article>
<script src="./js/main/main.js"></script>