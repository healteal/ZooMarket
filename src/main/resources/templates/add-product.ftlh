<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Добавить продукт</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background-image: url('/static/images/background.jpg'); background-size: 100% 100%; background-attachment: fixed">
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"></a>
    <ul class="nav col-12 col-md-auto mb-4 justify-content-center mb-md-0">
        <li><a href="/"><img src="/static/images/headLogo.jpg" alt="" width="40" height="40"></a></li>
        <li><b><a href="/" class="nav-link px-4 link-secondary">Главная страница</a></b></li>
        <li><b><a href="/about" class="nav-link px-4 link-dark">О нас</a></b></li>
        <#if marketUser??>
            <li><b><a href="/add" class="nav-link px-4 link-dark">Добавить</a></b></li>
        </#if>
    </ul>
    <div class="col-md-3 text-end" bis_skin_checked="1">
        <#if marketUser??>
            <a href="/user/${marketUser.id}">
                <button type="button" class="btn btn-outline-primary me-2">Аккаунт
                </button>
            </a>
            <a href="/logout">
                <button type="button" class="btn btn-primary">Выйти</button>
            </a>
        <#else>
            <a href="/login">
                <button type="button" class="btn btn-outline-primary me-2">Войти
                </button>
            </a>
            <a href="/registration">
                <button type="button" class="btn btn-primary">Регистрация</button>
            </a>
        </#if>
    </div>
</header>
<div class="w-50 mx-auto">
    <form action="/add" method="post" enctype="multipart/form-data">
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label"><b>Название</b></label>
            <input type="text" class="form-control" name="name" value="" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести название
            </div>
        </div>
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label"><b>Описание</b></label>
            <input type="text" class="form-control" name="description" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести описание
            </div>
        </div>
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label"><b>Цена</b></label>
            <input type="number" class="form-control" name="price" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести цену
            </div>
        </div>
        <br>
        <div class="text-center">
            <select class="form-control col-sm-5 mx-auto" name="type_of_product">
                <option>Питомец</option>
                <option>Еда</option>
                <option>Оборудование</option>
            </select>
        </div>
        <br>
        <div class="text-center">
            <label for="file1" class="btn btn-primary">Фото №1</label>
            <input id="file1" style="display:none" placeholder="Добавить" type="file" name="first_image"
                   required="required">
            <label for="file2" class="btn btn-primary">Фото №2</label>
            <input id="file2" style="display:none" value="Добавить" type="file" name="second_image" required="required">
            <label for="file3" class="btn btn-primary">Фото №3</label>
            <input id="file3" style="display:none" value="Добавить" type="file" name="third_image" required="required">
        </div>
        <br>
        <div class="text-center">
            <button class="btn btn-primary btn-lg" type="submit">Добавить товар</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>