<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 6/20/2023
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
  <script type="text/javascript" src="/js/app.js"></script>
  
  <title>Read Share - Book #${book.id}</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a href="/dashboard" class="navbar-brand">Read Share</a>
    <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a href="/dashboard" class="nav-link">Home</a>
        </li>
      </ul>
      <a href="/logout" class="btn btn-outline-danger" role="button">Log Out</a>
    </div>
  </div>
</nav>
<div class="container p-5">
  <div class="card w-100 mb-3">
    <div class="card-header text-center fs-3">
      ${book.title}
    </div>
    <div class="card-body">
      <p class="fs-4 mb-3">
        <span style="color: red">${book.user.userName}</span> read
        <span style="color: cornflowerblue">${book.title}</span> by
        <span style="color: rebeccapurple">${book.author}</span> on
        <span class="fw-bold">
          <fmt:formatDate value="${book.createdAt}"/>
        </span>.
      </p>
      <p>Here are ${book.user.userName}'s thoughts...</p>
      <hr>
      <p class="fs-4 mb-3">
        ${book.comments}
      </p>
    </div>
  </div>
</div>
</body>
</html>