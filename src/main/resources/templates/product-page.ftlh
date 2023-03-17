<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${product.name}</title>
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
<h2 class="text-center">${product.name}</h2><br>
<div id="myCarousel" class="carousel slide mx-auto bg-white" data-ride="carousel" style="height: 300px; width: 50%">
    <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0"></li>
    </ol>
    <div class="carousel-inner">
        <#list productImages as image>
            <div class="carousel-item <#if image.isMainImage()>active</#if>">
                <svg class="bd-placeholder-img" width="0%" height="300px" xmlns="http://www.w3.org/2000/svg"
                     role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="0%" height="100%" fill="#777"></rect>
                    <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <a href="/product/image/${image.id}">
                            <img class="product" style="height: 250px" src="/product/image/${image.id}">
                        </a>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <a class="carousel-control-prev bg-dark" href="#myCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next bg-dark" href="#myCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-4 text-center border border-primary"><h3>Описание</h3></div>
        <div class="col-8 border border-primary"><h3>${product.description}</h3></div>
    </div>
    <div class="row">
        <div class="col-4 text-center border border-primary"><h3>Цена</h3></div>
        <div class="col-8 border border-primary"><h3>${product.price} BYN</h3></div>
    </div>

    <div class="row">
        <div class="col-4 text-center border border-primary"><h3>Автор</h3></div>
        <div class="col-8 border border-primary"><h3>${product.author}</h3></div>
    </div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>
</html>