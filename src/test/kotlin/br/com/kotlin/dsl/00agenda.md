

# Building DSL with Kotlin

## Introduction

 - What's Kotlin? 
    - Jetbrains
    - Inspired: Scala, Groovy, C#, Ruby, etc
    - Java/Jvm
    - Android
    - Javascript
    - Native
    - Others
        - Kotlin Shell Script (Zalando deploy pipelines)
        - Ktor
        - React+Kotlin/JS
        - Arrowkt
        - Test suite (assertk, assertj, strikt) //todo get the exactly names here
        - Gradle DSL
        - Cucumber DSL
    
 - Why Kotlin?
    - Secure (nullpointer free)
    - Idiomatic and concise ([less verbose](java-to-kotlin.png))
    - Richer API (list, map, constructors)
    - Functional 
    - Coroutines (lightweight threads: async code in sequential style)
    - Official supported by [Springboot 2.x.x](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-fn)
    - DSL support
    - Onboard 
        - For Scala, Groovy 🍰
        - IntelliJ it's going to teach you more than Google
        - Does not force you go into another full paradigm, framework, tools

- Kotlin keynotes:
  - Secure
  - Idiomatic
  - Write less
  - Sugar syntax
    
## Getting comfortable with Kotlin
    
 - Tools -> Kotlin -> Kotlin REPL
 - Convert to Kotlin -> Code -> [Convert Java File to Kotlin](./CityJava.java)
 - Tools -> Kotlin -> Kotlin Bytecode 
 - Json to data class
 - [Basic](./01basic.kt)
 - [Nullpointer](./02nullpointer.kt)
 - [Lambdas](./03lambdas.kt)
 - [Default values](04defaultvalues.kt)
 - [Operators](./05operators.kt)
 - [Extension Function](./06extfun.kt)
    
## DSL Domain-Specific Language
 - _A code that looks less like code and more like a human writing_
   - Idiomatic 
   - Fluent
   - Easier to the users. _not so much for the creators_
   
 - Start with builders
   - Examples
     - [Html Render](./examples/html)
     - [Ktor](./examples/ktor/ktorexample.md)
     - [Spring](./examples/spring/springexample.md)
     - [Gradle](./examples/gradle/gradleexample.md)
   - Dsl
 - Pre-requisites
 - 

## Extra
   
   - List vs Sequences - lambdas
   - Inline - lambdas
