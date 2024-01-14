# PetProject
# О проекте
Язык программирования: **Java**  
  
Сборщик: **Maven**  
  
Используемые технологии:
- Spring MVC
- PostgreSql
- Hibernate
- Spring Security
# Описание
Web-приложение позволяет изучать иностранные языки при помощи метода флэш-карточек. Данный метод помогает пополнять пассивный словарный запас. 

Вход и регистрация:  

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/f0f12471-9d53-4fc7-a4d6-95a9aef92319" width=30% height=30%>
<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/f4d61ca4-6d11-446f-8789-0a3f75030823" width=30% height=30%>


На главной странице отображается список модулей – наборов слов и выражений. У пользователя есть возможность просматривать, редактировать, удалять, изучать и добавлять наборы слов: 

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/d5cd93e5-3dcc-4a96-9e03-d6a3f6f2908d" width=95% height=95%>

Просмотр модуля (набора слов): 

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/cf95e903-c481-4282-9547-b681b1a78655" width=95% height=95%>

Здесь пользователь может добавлять и редактировать слова модуля: 

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/ffec11ea-71cc-4596-ba57-d9c9a1b82fe5" width=48% height=48%>
<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/bfbde4fe-5e08-4a8c-be9f-0ff553ea9cca" width=48% height=48%>

При изучении модуля на странице поочередно отображаются слова соответствующего набора: 

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/2ff51cf4-f2e0-44e9-8385-1ad2eba396f4" width=30% height=30%>
<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/7d33266e-5c87-4aa4-bd08-9b4395047726" width=30% height=30%>

В конце изучения пользователь видит количество правильных ответов. Сохранение результата повлияет на статистику модуля.

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/9c35c7b2-3238-4e3e-b5c2-504f3455f54e" width=30% height=30%>
<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/9897d285-fbd0-46c0-926e-39305542bc94" width=30% height=30%>

По завершении изучения приложение переносит пользователя на страницу модулей:  

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/37a9154e-2f48-48cb-89ad-68e8e7d13bc9" width=95% height=95%>

В приложении реализована функция интервального повторения, которая поможет перенести изученный материал из кратковременной памяти в долговременную.
Если оставшееся время до изучения истекло, пользователь перейдет на следующую ступень метода интервального повторения и снова увидит отсчет времени. 
Всего в приложении 9 ступеней. Модуль необходимо повторять через следующие промежутки времени: 2 минуты, 10 минут, 1 час, 5 часов, день, 5 дней, 25 дней, 4 месяца, 2 года.
Повторение модуля до истечения времени никак не повлияет на обратный отсчет.

У модуля animals уже было пройдено 3 ступени метода интервального повторения. 

<img src="https://github.com/Anastasia-n/FlashcardsSpring/assets/52749662/1ae891f9-8a22-4321-b74b-19a1ea573add" width=35% height=35%>

Статистика модуля отображает количество проведенных занятий, дату последнего занятия, а также средний результат, 
который рассчитывается как соотношение правильных ответов к общему количеству вопросов.
