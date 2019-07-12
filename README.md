# Tomato

> Tomato is a HTTP web framework written in Java 8.
>
> Thanks [Gin](https://github.com/gin-gonic/gin) for the great design.

- Java8 features used

- Function Style URL Mapping

- MVC

- IOC

- AOP

- Embeded Tomcat

## Get Started

```java
public class App {

  public static void main(String[] args) {
    Tomato.init(App.class);
    Tomato.ROUTE
        .get("/ping", context -> {
          context.text("pong");
        });
    Tomato.run();
  }
}
```

### Querystring parameters

```java
  public static void main(String[] args) {
    Tomato.init(App.class);
    Tomato.ROUTE
        .get("/ping", context -> {
          String where = context.queryParams().getString("where");
          context.text("pong " + where);
        });
    Tomato.run();
  }
```

### Group Route

```java
    // api/ping
    // api/test
    // api/user/name
    // api/user/id
    // api/v2/count
      Tomato.ROUTE.group("/api")
        .get("/ping", context -> {
        })
        .put("/test", context -> {
        })
        .group("/user")
        .post("/name", context -> {
        })
        .delete("/id", context -> {
        });

    route.group("/api/v2/")
        .put("/count/", context -> {
        });
```


## To-dos

- [ ] AOP

- [ ] JSON Render

- [ ] Exception handle

- [ ] Radix tree based routing
