<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 6/20/2023
  Time: 9:58 PM
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
  
  <title>Read Share - Dashboard</title>
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
  <h1 class="mb-3">Welcome, ${userName}!</h1>
  <div class="d-flex justify-content-between">
    <p>Books from everyone's shelves:</p>
    <a href="/books/new/form"> -Add a book to my shelf- </a>
  </div>
  <div class="d-flex justify-content-center">
    <div class="card w-100">
      <div class="card-body">
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th scope="col">Book ID</th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Added by</th>
            <th scope="col">Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="book" items="${allBooks}">
            <tr>
              <th scope="row">${book.id}</th>
              <td>${book.title}</td>
              <td>${book.author}</td>
              <td>${book.user.userName}</td>
              <td>
                <div class="d-flex justify-content-center">
                  <form action="/books/delete/${book.id}" method="post">
                    <div class="btn-group" role="group">
                      <a href="/books/${book.id}" class="btn btn-primary">
                        View
                      </a>
                      <c:if test="${book.user.id == userId}">
                        <a href="/books/edit/${book.id}" class="btn btn-warning">
                          Edit
                        </a>
                        <input type="hidden" name="_method" value="delete">
                        <button type="submit" class="btn btn-danger">Delete</button>
                      </c:if>
                    </div>
                  </form>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>