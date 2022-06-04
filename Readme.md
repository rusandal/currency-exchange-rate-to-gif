## Сервис выводящий Gif в зависимости от разницы курса валют вчера и сегодня
Стек: Spring Boot Web, Feign, Thymeleaf, JUnit, Mockito, Docker, Lombok
### Описание
REST сервис получающий запрос на вывод картинки в зависимости от изменения отношения курса запрошенной валюты в отношении к доллару вчера и сегодня. Если курс по отношению к USD за сегодня стал выше вчерашнего, то отображает рандомную отсюда https://giphy.com/search/rich ... если ниже - отсюда https://giphy.com/search/broke.
Курс валют получаем с помощью запроса REST API - https://docs.openexchangerates.org/
### Запуск
1. Создаем Docker контейнер с программой.
```
docker build -t myexchgif .
```
где указываем, что надо создать docker контейнер с тэгом образа конейнера "myexchgif" по инструкции Docker файла находящегося в текущей директории.
2. Запуск созданного контейнера.
```
docker run --name myapp -d -p 8080:8080 myexchgif
```
где указываем, что надо запустить образ docker контейнера с тэгом(именем) "myexchgif" ... дать запущенному контейнеру имя "myapp" ... проводить port mapping порта 8080 хоста на 8080 конейнера.
3. Проверяем статус контейнера.
```
docker ps -a
```
и убеждаемся что наш контейнер status UP

### Выполнение
В конце указываем код валюты для сравнение с USD
```
http://127.0.0.1:8080/EUR
```
Ура видим картинку в браузере