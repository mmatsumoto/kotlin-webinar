

# Building DSL with Kotlin

## Agenda

 - What/Why Kotlin? - (2, 3 minutes) 
 - Getting comfortable with Kotlin (20-25 minutes)
 - DSL Domain-Specific Language (35 minutes)
   - What's a DSL?      
   - Why should I care?
   - Where to start?
   - Let's create one










### What's Kotlin? 

- Jetbrains
- Inspired: Scala, Groovy, C#, Ruby, etc
- Java/Jvm
- Android ⭐️
- Javascript
- Native
- Others
    - Kotlin Shell Script (Zalando deploy pipelines)
    - Ktor
    - React+Kotlin/JS
    - Arrowkt (cats scala)
    - Test suite (kotlin.test package, kotest - by Jetbrains, strikt, assertk, assertj*, junit*)
      - [Kotlin Testing Libraries](https://docs.google.com/presentation/d/1uShNWOJ_mMH03QXn46oha0bW5WDRUAStPOspwWlUoUQ/edit#slide=id.p)
    - Gradle DSL
    - Cucumber DSL
    
    




    
    

### Why Kotlin?

- Secure
- Idiomatic and concise ([less verbose](java-to-kotlin.png))
- Richer API (list, map, constructors, syntactic sugar)
- Functional 
- Coroutines (lightweight threads: async code in [sequential style](./coroutines.png))
- Official supported by [Springboot 2.x.x](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-fn)
  - [Web Flux with Kotlin](https://docs.google.com/presentation/d/1oNKXaFwea9iDo3Mis_uUZkSNmdeVaCumPlDtLxG3wp4/edit#slide=id.g70d189084b_0_17)
- DSL support
- Onboard 
    - For Scala, Groovy 🍰
    - IntelliJ it's going to teach you more than Google
    - Does not force you go into another full paradigm, framework, tools
    
    
    
    

### Kotlin keynotes:

  - Secure
  - Idiomatic
  - Syntactic Sugar (Write less code)

  **Move fast and safe to production!**
  
  
  
  
  
  
  
  
  
   
   
   
    

### Getting comfortable with Kotlin
    
 - Tools
   - Tools -> Kotlin -> Kotlin REPL
   - Convert to Kotlin -> Code -> [Convert Java File to Kotlin](./CityJava.java)
   - Tools -> Kotlin -> Kotlin Bytecode 
   - Json to data class
   
   
   
   
 - Language:
   - [Basic](./01basic.kt)
   - [Nullpointer](./02nullpointer.kt)
   - [Lambdas](./03lambdas.kt)
   - [Default values](04defaultvalues.kt)
   - [Operators](./05operators.kt)
   - [Extension Function](./06extfun.kt)
    
    
    
   
    

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
   - [Acceptance Test Project - Mock Router](./examples/mock/mockroutedsl.kt)
 
 

## Extra
   
   - List vs Sequences - lambdas [Topic13]
   - Inline - lambdas [Topic12]
   - Tacit programming, no point-free style [Topic16]
     - also 
     - let
     - apply
     - run
     - with
   