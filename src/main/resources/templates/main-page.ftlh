<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"></a>
    <ul class="nav col-12 col-md-auto mb-4 justify-content-center mb-md-0">
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
<#if products??>
    <div class="container">
        <div class="row row-cols-3">
            <#list products as product>
                <div class="col-4">
                    <div class="card shadow-sm" bis_skin_checked="1">
                        <br>
                        <svg class="bd-placeholder-img card-img-top" height="250px">
                            <image href="/product/image/${product.getIdOfMainImage()}" height="100%" width="100%" />
                        </svg>
                        <div class="card-body" bis_skin_checked="1">
                            <p class="card-text">
                            <h3>${product.name}</h3>
                            <h5>${product.shortDescription}</h5>
                            <br>
                            <div class="d-flex justify-content-between align-items-center" bis_skin_checked="1">
                                <div class="btn-group" bis_skin_checked="1">
                                    <a href="/product/${product.id}">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            Подробнее
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#if>
</body>
</html>