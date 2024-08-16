# My first project, Simply Todo application
バックエンドをSpringBootで作成し、フロントエンドをReact+Viteで作成したTodoリスト。 

##  使用技術について

### 環境
- OS macOS [14.4.1]
- npm [10.8.2]
- maven [3.9.8]
- STS(Java IDE)
- VScode

### フロントエンド
- Typescript [5.5.3]
- React [18.3.1]
- vite [5.4.0]
- Redux [2.2.1]
- Axios [1.7.4]

### バックエンド
- Java [21.0.1]
- SpringBoot [3.3.2]
- lombok

### DB
- PostgreSQL [14.12]

## 使用方法

コピペするなりcloneするなりしてもらって大丈夫です。

```
git clone https://github.com/Ayumu3746221/MyTodolist.git
```

SpringBootの/todo/src/main/resourcesにapplication.propertiesを作成して、API側のDB設定を行います。

```
spring.application.name=todo

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/todo
spring.datasource.username='ここにDB操作用のアカウント名を記載'
spring.datasource.password='アカウントのパスワードを設定'
```

あとは起動すれば大丈夫,localhost:3001で起動します。