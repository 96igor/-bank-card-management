---
**Пользователи:**
- `POST /api/users` – создать пользователя
- `GET /api/users/{id}` – получить пользователя по ID
- `GET /api/users` – получить всех пользователей
- `DELETE /api/users/{id}` – удалить пользователя

**Карты:**
- `POST /api/cards` – создать карту
- `GET /api/cards/{id}` – получить карту по ID
- `GET /api/cards` – получить все карты
- `GET /api/cards/user/{userId}` – получить карты пользователя
- `PUT /api/cards/{id}/balance?balance={amount}` – обновить баланс карты
- `DELETE /api/cards/{id}` – удалить карту

Сборка и запуск
----------------
1. Перейти в директорию проекта:
   ```
   cd bank-card-management
   ```
2. Собрать проект:
   ```
   mvn clean package
   ```
3. Запустить JAR:
   ```
   java -jar target/bank-card-management-0.0.1-SNAPSHOT.jar
   ```

Конфигурация
------------
- База данных PostgreSQL (параметры подключения в `application.yml`)
- Liquibase для автоматического управления схемой базы
- Spring Security с временным паролем для разработки (обязательно настроить перед продакшеном)

Тесты
-----
Проект содержит базовый тест запуска контекста Spring Boot (`BankCardManagementApplicationTests`).

Примечания
-----------
- Все DTO и мапперы настроены для корректного преобразования между сущностями и данными для API.
- Валидность и безопасность данных можно расширить дополнительными проверками.
- Для продакшена рекомендуется настроить Spring Security и отключить `spring.jpa.open-in-view`.