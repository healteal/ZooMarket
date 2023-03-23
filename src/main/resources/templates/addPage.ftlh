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
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
            <use xlink:href="#bootstrap"></use>
        </svg>
    </a>
    <ul class="nav col-12 col-md-auto mb-4 justify-content-center mb-md-0">
        <li><b><a href="/" class="nav-link px-4 link-secondary">Главная страница</a></b></li>
        <li><b><a href="/about" class="nav-link px-4 link-dark">О нас</a></b></li>
        <li><b><a href="/add" class="nav-link px-4 link-dark">Добавить</a></b></li>
    </ul>
    <div class="col-md-3 text-end" bis_skin_checked="1">
        <a href="/login">
            <button type="button" class="btn btn-outline-primary me-2">Войти
            </button>
        </a>
        <a href="/registration">
            <button type="button" class="btn btn-primary">Регистрация</button>
        </a>
    </div>
</header>
<div class="w-50 mx-auto">
    <form action="/add" method="post" enctype="multipart/form-data">
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label">Название</label>
            <input type="text" class="form-control" name="name" value="" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести название
            </div>
        </div>
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label">Описание</label>
            <input type="text" class="form-control" name="description" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести описание
            </div>
        </div>
        <div class="col-sm-6 mx-auto" bis_skin_checked="1">
            <label class="form-label">Цена</label>
            <input type="number" class="form-control" name="price" required="required">
            <div class="invalid-feedback" bis_skin_checked="1">
                Необходимо ввести цену
            </div>
        </div>
        <br>
        <div>
            <input type="file" name = "first_image">
            <input type="file" name = "second_image">
            <input type="file" name = "third_image">
        </div>
        <br>
        <div class="text-center">
            <button class="btn btn-primary btn-lg" type="submit">Добавить товар</button>
        </div>
    </form>
</div>
</body>
</html>