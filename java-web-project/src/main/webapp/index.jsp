<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<input id="sys-message" type="hidden" value="${message}" name="message">
<% 
    request.removeAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--<meta http-equiv="refresh" content="3">-->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Application</title>
    <link rel="icon" href="./img/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-green.min.css" />
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <!--getmdl-select-->   
    <link rel="stylesheet" href="js/getmdl-select/getmdl-select.min.css">
    <script defer src="js/getmdl-select/getmdl-select.min.js"></script>

    <style><%@include file="theme/styles.css" %></style>
</head>
<body>
    <main>
        <jsp:include page="modules/suite/header/header.jsp"/>    
        <%  
            String pagePath = "modules/page/";
            String currentPage = request.getAttribute("page") != null ? (String)request.getAttribute("page") : "login";
            pagePath+= currentPage + "/" + currentPage + ".jsp";
        %>
        <jsp:include page="<%=pagePath%>"/>
    </main>
    <div id="message-container" class="mdl-js-snackbar mdl-snackbar">
        <div class="mdl-snackbar__text"></div>
        <button class="mdl-snackbar__action" type="button"></button>
    </div>
    <script src="./js/main/message.js"></script>
</body>
</html>