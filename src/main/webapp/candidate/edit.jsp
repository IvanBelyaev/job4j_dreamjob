<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Работа мечты</title>
    <%
        String id = request.getParameter("id");
        Candidate candidate = new Candidate(0, "", 0);
        if (id != null) {
            candidate = PsqlStore.instOf().findCandidateById(Integer.valueOf(id));
        }
    %>
    <script>
        function validate() {
            let result = true;
            if ($("#name").val() === "") {
                $("#nameLabel").text("Имя (заполните поле)").css("color", "#740000");
                result = false;
            } else {
                $("#nameLabel").text("Имя").css("color", "#000000");
            }
            if ($("#cities").val() === "") {
                $("#cityLabel").text("Город (заполните поле)").css("color", "#740000");
                result = false;
            } else {
                $("#cityLabel").text("Город").css("color", "#000000");
            }
            return result;
        }

        $(document).ready(function(){
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/job4j_dreamjob/cities.do',
                dataType: 'json'
            }).done(function(data) {
                let sortable = [];
                for (let key in data) {
                    sortable.push([key, data[key]]);
                }

                sortable.sort(function(a, b) {
                    return a[1].localeCompare(b[1]);
                });
                for (let i = 0; i < sortable.length; i++) {
                    let opt = $("<option></option>").text(sortable[i][1]);
                    opt.attr("value", sortable[i][0]);
                    if (sortable[i][0] === "<%=candidate.getCityId()%>") {
                        opt.attr("selected", "");
                    }
                    $("#cities").append(opt);
                };
            }).fail(function(err){
                alert(err);
            });
        });
    </script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/index.do" />'>Главная страница</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Вакансии</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Кандидаты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/editPost.do">Добавить вакансию</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/editCandidate.do">Добавить кандидата</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/auth.do"> <c:out value="${user.name}"/> | Выйти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование кандидата.
                <% } %>
            </div>
            <div class="card-body">
                <form method="post" action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" onsubmit="return validate();">
                    <div class="form-group">
                        <label id="nameLabel">Имя</label>
                        <input type="text" class="form-control" id="name" name="name" value="<%=candidate.getName()%>">
                    </div>
                    <div class="form-group">
                        <label id="cityLabel">Город</label>
                        <select id="cities" name="city">
                            <option></option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>