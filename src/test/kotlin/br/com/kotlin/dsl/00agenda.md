

# Building DSL with Kotlin

### DSL Domain-Specific Language

 #### What's a DSL?
   - _A code that looks less like code and more like a human writing_
   - Idiomatic 
   - Fluent
   - Easier to the users (_not so much for the creators_)
 
 #### Why should I care?
   - Examples:
     - [Ktor](./examples/ktor/ktorexample.md)
     - [Spring](./examples/spring/springexample.md)
     - [Gradle](./examples/gradle/gradleexample.md)
   
 #### Where to start?
   - Builders:
     - [Html Render Builder](./examples/html/builder/builderexample.kt)
     - [Html Render DSL](./examples/html/dsl/dslexample.kt)
 
  #### Let's create one
   - [Acceptance Test Project - Mock Router](examples/dslexample/mockroutedsl.kt)
 


```Java
        mockFacade.mock(
           request()
              .withMethod(GET)
              .withPath(" /hello")
              .withQueryParam("legacy_skus", simpleSku.sku)
              .withQueryParam("legacy_appdomain_id", appDomainId.id.toString())
              .withHeader("Authorization", "Bearer .*")
              .withHeader("X-Flow-Id", "foobar1")
              .withHeader("X-zalando-client-id", "client1")
              .withBody("{'name':'Mike'}")
              .build(),
           response()
              .withStatusCode(201)
              .withHeader("Content-Type", "application/json")
              .withBody("{'message': 'Hello Mike'}".toJson())
              .withDelay(200ms)
              .build()
        )
```

```kotlin
     mock {
        request {
            path = "/hello"
            body = "{'name':'Mike'}"
            header.withName("x-flow-id").withValue("v1")
            header withName "x-asdfasdf-id" withValue "v1" withValue " v2"
        }
        response {
            code = 201
            body = "{'message': 'Hello Mike'}"
            delay = 100ms
        }
    }
```