# Bookmark Service [![CircleCI](https://circleci.com/gh/prabal-es/bookmark-service.svg?style=svg&circle-token=e230712c1d302f0dfbc0bbfccd5a6300457f5f73)](https://circleci.com/gh/prabal-es/bookmark-service) [![codecov](https://codecov.io/gh/prabal-es/bookmark-service/branch/master/graph/badge.svg?token=5FF8ZIXH7F)](https://codecov.io/gh/prabal-es/bookmark-service) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/ad7b78fe92664402a84512aca3e27b15)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=prabal-es/bookmark-service&amp;utm_campaign=Badge_Grade) [![Heroku](https://heroku-badge.herokuapp.com/?app=heroku-badge)](https://bookmark-service-9.herokuapp.com/api/v1/swagger-ui.html)

With bookmark service, you can save favorite webpages, tinify URL, share-it, manage-it and navigate to them in seconds from anywhere. Using bookmark-servive --
- User will generate short URLs which will expire after a standard default timespan. User will also be able to specify the expiration date. This will help us solve the problem of - generating tiny URLs quickly and sharing it with others.
- User will be able to create cards representing the url where each card has a short title, brief description and a customizable picture. Default picture would be the favicon of the serving application.
- User will be able to group cards in terms of tribes, feature teams, platforms or application. This would be like a catalog. User will be able to share the group urls.
- Each card will be a short url with the re-direction to the original url. This short url will have no expiration as it belongs to a group. The generation of this short url will be dynamic and unique and could carry some contextual information too.
- The creator of the group will be the admin(role) user after which s\he will be able to add one or more admin user who would have authority to make changes. Only admin user(s) can remove a user from admin role. At any point of time there must be at least one admin user for the group.
- Admin user(s) of the group will be able to authorize a card to be displayed on the group, make changes like updating or deleting card(s). Unless the admin user approves the card or its changes, it will not be displayed on the group page.
- A normal user i.e. not an admin user can suggest changes to an existing card. The changes are to be queued in order to be approved by the group's admin user(s).
- User will be able to share the group page enlisting all the cards of that group
- Admin user(s) will be able to import or export

## Running locally[without any database setup]:
```
git clone https://github.com/prabal-es/bookmark-service.git

cd bookmark-service

mvn clean package

java -jar -Dspring.profiles.active=test ./bootstrap/target/bootstrap-0.0.1-SNAPSHOT-exec.jar

```
You can then access bookmark-service here: [Open API](http://localhost:8080/api/v1/swagger-ui.html)

## Database setup:
If you want to connect to Postgres database, then you need to update the `.\bootstrap\src\main\resources\application.yml` file from line number 7 to 11:
```
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema=bookmark
    username: postgres
    password: postgres
```
and again build it and run it without `test` profile. Example:
```
git clone https://github.com/prabal-es/bookmark-service.git

cd bookmark-service

mvn clean package

java -jar ./bootstrap/target/bootstrap-0.0.1-SNAPSHOT-exec.jar
```
## Hexagonal Architecture
In this project we have used Hexagonal Architecture with domain driven design.
#### Hexagonal Architecture
![Hexagonal Architecture](https://stefanoalletti.files.wordpress.com/2017/10/clean-architecture.png?w=590&h=333)

For more information: [Click Me](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))

#### Domain driven design: [Click Me](https://en.wikipedia.org/wiki/Domain-driven_design)

## Database ER diagram:
![Database ER diagram](https://github.com/prabal-es/bookmark-service/blob/development/.github/docs/db.svg?raw=true)

## Rest definition:
For this service we are having 5 rest definition:
- **Company** : Operations related to company model. Such as getAllCompanies, getComyanyDetails, getAllCompanyUsers, getAllCompanyGroups. 
- **User**: Operations related to user model. Such as getAllUsers.
- **Group**: Operations related to group model. Such as retrieving, creating or mapping cards and users.
- **Card**: Operations related to card model. Such as retrieving or creating cards.
- **Tiny**: Tiny is used for handling the tiny code redirect. `308 Permanent redirect` is used for valid tiny code.

![Rest definition](https://github.com/prabal-es/bookmark-service/blob/development/.github/docs/rest_definition.png?raw=true)

> For tiny code generation we are using [MurmurHash3](https://en.wikipedia.org/wiki/MurmurHash) algorithm, which will create 8 digit of unique code.


## Running application details: 
- **Swagger URL**: https://bookmark-service-9.herokuapp.com/api/v1/swagger-ui.html
- **CircleCI URL: https://app.circleci.com/pipelines/github/prabal-es/bookmark-service
- **Codecov URL: https://app.codacy.com/manual/prabal-es/bookmark-service/dashboard
- **Codacy URL: https://app.codacy.com/manual/prabal-es/bookmark-service/dashboard
- **Heroku URL: https://dashboard.heroku.com/apps/bookmark-service-9
- **Build Packages: https://github.com/prabal-es/bookmark-service/packages
- Dependent UI project GitHub URL[**Bookmark UI**]: https://github.com/prabal-es/bookmark-ui
- Dependent UI project **running** link: https://bookmark-9.herokuapp.com

> Note: Heroku Dyno shutdown if not in use. So First time when you hit the running app it will take longer time but once the Dyno starts it will run fine.
> Heroku Dyno JVM is running on 128 MB due to memory leak issue.



  
