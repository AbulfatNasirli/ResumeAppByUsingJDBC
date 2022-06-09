<%@ page import="entity.User" %>
<%@ page import="dao.UserDaoInter" %>
<%@ page import="main.Contex" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/24/2022
  Time: 7:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8 ">
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="assets/js/users.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<body>
<%

    List<User> list = (List<User>) request.getAttribute("list");

%>
<div class="container mycontainer">
    <div class="col-12">
        <div class="col-4">
            <form action="users" method="GET">
                <div>
                    <input class="form-control" type="hidden" name="id" value=""/>
                    <div class="form-group">
                        <label>name</label>
                        <input type="text" placeholder="Enter name" class="form-control" name="name" value=""/>
                    </div>

                    <br>
                    <div class="form-group">
                        <label>surname</label>
                        <input type="text" placeholder="Enter surname" class="form-control" name="surname"
                               value=""/>
                    </div>

                    <input type="submit" class="btn btn-primary" name="search" value="search" id="btnSearch"/>

                </div>
             
                <hr/>
            </form>
        </div>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>name</th>
                    <th>surname</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (User u : list) {
                %>

                <tr>
                    <td><%=u.getName()%>
                    </td>
                    <td><%=u.getSurname()%>
                    </td>
                    <td style="width: 5px">

                        <input type="hidden" name="id" value="<%=u.getId()%>">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="btn btn-danger" value="delete"
                                data-toggle="modal" data-target="#exampleModalLong"
                        onclick="setIdForDelete('<%=u.getId()%>')">
                            <i class="btn_table fa fa-trash-o" aria-hidden="true"></i>
                        </button>

                    </td>
                    <td style="width: 5px">
                        <form action="userdetail" method="GET">
                            <input type="hidden" name="id" value="<%=u.getId()%>">
                            <input type="hidden" name="action" value="update">
                            <button type="submit" class="btn btn-secondary" value="update">
                                <i class="btn_table fa fa-pencil" aria-hidden="true"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>


<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
               Are you sure ?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="POST">
                  <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete"/>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <input type="submit" class="btn btn-danger" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
