<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Администратор</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"></a>
    <ul class="nav col-12 col-md-auto mb-4 justify-content-center mb-md-0">
        <li><b><a href="/" class="nav-link px-4 link-secondary">Главная страница</a></b></li>
        <li><b><a href="/about" class="nav-link px-4 link-dark">О нас</a></b></li>
        <li><b><a href="/admin" class="nav-link px-4 link-dark">Панель администратора</a></b></li>
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="get" action="/search">
            <input type="text" class="form-control form-control-dark text-bg-dark" placeholder="Поиск" name="search">
        </form>
    </ul>
    <div class="col-md-3 text-end" bis_skin_checked="1">
        <a href="/logout">
            <button type="button" class="btn btn-primary">Выйти</button>
        </a>
    </div>
</header>
<br>
<table class="table table-hover table-dark">
    <tr>
        <th scope="col">Email</th>
        <th scope="col">Номер телефона</th>
        <th scope="col">Активность</th>
        <th scope="col">Роли</th>
        <th scope="col">Бан</th>
        <th scope="col">Подробная информация</th>
    </tr>
    <#list listOfUsers as user>
        <tr>
            <th scope="col">${user.email}</th>
            <th scope="col">${user.number}</th>
            <th scope="col">
                <#if user.enable>
                    Активен
                <#else>
                    Неактивен
                </#if>
            </th>
            <th scope="col">
                <#list user.userRoles as role>
                    ${role.name()}
                </#list>
            </th>
            <th>
                <#if user.enable == true>
                    <form action="/admin/ban/${user.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Забанить">
                    </form>
                <#else>
                    <form action="/admin/unban/${user.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Разбанить">
                    </form>
                </#if>
            </th>
            <th><a href="/user/${user.id}">Информация</a></th>
        </tr>
    </#list>
</table>
</body>
</html>