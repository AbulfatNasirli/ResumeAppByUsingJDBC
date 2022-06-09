<%@ page import="main.Contex" %>
<%@ page import="dao.UserDaoInter" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    User u = (User) request.getAttribute("user");

%>
<div>
    <form action="userdetail" method="POST">
        <%--@declare id="name"--%><%--@declare id="surname"--%>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <label for="name">name</label>
        <input type="text" name="name" value="<%=u.getName()%>">
        <br>
        <label for="surname">surname</label>
        <input type="text" name="surname" value="<%=u.getSurname()%>">
        <input type="submit" name="save" value="Save">
    </form>
</div>
<div class="card">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">
    </h3>
    <div class="card-body">
        <div id="table" class="table-editable">
      <span class="table-add float-right mb-3 mr-2"
      ><a href="#!" class="text-success"
      ></a
      ></span>
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center" for="name">Name</th>
                    <th class="text-center" for="surname">Surname</th>
                    <th class="text-center">Age</th>
                    <th class="text-center">Phone</th>
                    <th class="text-center">Email</th>
                    <th class="text-center">Address</th>
                </tr>
                </thead>
                <tbody>
                <form action="userdetail" method="POST">
                <tr>
                    <td class="pt-3-half" contenteditable="true" name="name"><%=u.getName()%></td>
                    <td class="pt-3-half" contenteditable="true" name="surname"><%=u.getSurname()%></td>
                    <td class="pt-3-half" contenteditable="true"><%=u.getAge()%></td>
                    <td class="pt-3-half" contenteditable="true"><%=u.getPhone()%></td>
                    <td class="pt-3-half" contenteditable="true"><%=u.getEmail()%></td>
                    <td class="pt-3-half" contenteditable="true"><%=u.getAddress()%></td>
                    <td class="pt-3-half">

                            <input type="hidden" name="id" value="<%=u.getId()%>">
                            <input type="submit" class="btn btn-danger btn-rounded btn-sm my-0" name="save" value="Save">
                    </td>
                </tr>
                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
