
```java
    RouterFunction<ServerResponse> route = route()
        .GET("/person/{id}", accept(APPLICATION_JSON), handler::getPerson)
        .GET("/person", accept(APPLICATION_JSON), handler::listPeople)
        .POST("/person", handler::createPerson)
        .build();
```

```kotlin
val route = router {  
    accept(APPLICATION_JSON).nest {
        GET("/person/{id}", handler::getPerson)
        GET("/person", handler::listPeople)
    }
    POST("/person", handler::createPerson)
}

// for async use coRouter {} 
```



```java
    @Configuration
    public class MyBeansConfig {
        
        @Bean
        public CommandLineRunner resetMockServer(MockFacade mockFacade) {
            CommandLineRunner bean = new CommandLineRunner() {
                @Override
                public void run(String... args) throws Exception {
                    mockFacade.resetAllMocks();
                }
            };
            return bean;
        }
    
    
        @Bean
        public CommandLineRunner resetMockServerLambda(MockFacade mockFacade) {
            return args -> mockFacade.resetAllMocks();
        }
    }
```

```kotlin
    beans {
        bean {
            val mockFacade = ref<MockFacade>()
            mockFacade.resetAllMocks()
        }

        bean {
            val stockMock = ref<StockMock>()
            stockMock.mockDynamicResponse()
        }

        bean {
            val quoteMock = ref<QuoteMock>()
            quoteMock.mockDynamicResponse()
        }
    }
```

