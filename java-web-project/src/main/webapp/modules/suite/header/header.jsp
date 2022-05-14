<%@ page import="com.barretoareias.model.bean.User" %>
<%@ page import="com.barretoareias.model.bean.UserType" %>
<% User user = (User)session.getAttribute("userLogged");%>

<style><%@include file="header.css" %></style>
<header class="suite-header">
    <% if(user != null) { %>
        <button id="menu" class="mdl-button mdl-js-button mdl-button--icon">
            <i class="material-icons">more_vert</i>
        </button>
    <% } %>

    <ul class="mdl-menu mdl-menu--bottom-left mdl-js-menu mdl-js-ripple-effect" for="menu">
        <!--<li class="mdl-menu__item"><a href="">Meus dados</a></li>-->
        <li class="mdl-menu__item"><a href="index">Meus livros<a></li>
        <li class="mdl-menu__item"><a href="booklist">Listagem de livros</a></li>

        <% if(user != null && user.getType() == UserType.Admin) { %>
            <li class="mdl-menu__item"><a href="author">Autores</a></li>
            <li class="mdl-menu__item"><a href="book">Livros</a></li>
        <% } %>

        <li class="logout mdl-menu__item"><a href="logout"><span class="material-icons">logout</span>Sair</a></li>
    </ul>

    <h1>Minha biblioteca</h1>
</header>