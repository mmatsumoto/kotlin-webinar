package br.com.kotlin.dsl.examples.mock

//// @formatter:off


/**java

mockFacade.mockRequest(
   request()
      .withMethod(GET)
      .withPath(STOCK_PRODUCT_QUANTITIES_PATH)
      .withQueryParam("legacy_skus", simpleSku.sku)
      .withQueryParam("legacy_appdomain_id", appDomainId.id.toString())
      .withHeader("Authorization", "Bearer .*")
      .withHeader("X-Flow-Id", "foobar1")
      .withHeader("X-zalando-client-id", "client1")
      .build(),
   response()
      .withStatusCode(HttpStatus.OK)
      .withHeader("Content-Type", "application/json")
      .withBody(listOf(stockResponse).toJson())
      .withDelayInMilliseconds(delayInMilliseconds)
      .build()
)


 */
// @formatter:on

typealias Name = String
typealias Value = String
typealias Path = String
typealias Body = String
typealias HttpMethod = String
typealias StatusCode = Int





class Step3 {
    // 1 - declare request and response

    class Request(var method: HttpMethod, var path: Path, var body: Body)
    class Response(var code: StatusCode, var body: Body)

    // 2 - declare mock func
    /*
     fun mock(request: Request, response: Response) {
     }
    */

    // 3 - declare Handler and change mock fun

    class Handler(val request: Request, val response: Response)


    fun mock(handler: (Handler) -> Unit) {
    }

    fun main() {
        mock {
            it.request.path = "/hello"
            it.request.body = "{name: Mike}"
            it.response.code = 201
            it.response.body = "typejson here"
        }
    }
}


// remove the it
class Step4 {
    class Request(var method: HttpMethod, var path: Path, var body: Body)
    class Response(var code: StatusCode, var body: Body)
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) { }

    fun main() {
        mock {
            request.path = "/hello"
            request.body = "{name: Mike}"
            response.code = 201
            response.body = "{message: Hello Mike}"
        }
    }
}

// make request look like request { it.path }
class Step5 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        operator fun invoke(function: (Request) -> Unit) {
            TODO("Not yet implemented")
        }
    }

    class Response(var code: StatusCode, var body: Body)
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) { }

    fun main() {

        class Request(var method: HttpMethod, var path: Path, var body: Body) {
            operator fun invoke (init: (Request) -> Unit) {
            }
        }

        mock {
            request {
                it.path = "/hello"
                it.body = "{name: Mike}"
            }
            response.code = 201
            response.body = "typejson here"
        }
    }
}

// remove it from request

// need to explain anonymous function of extension function here

fun test (init: String.(String) -> String) {
    println(init("hello", "mike"))
    println("hello".init( "mike" ))
}

fun main() {
    test {
        "${this.toUpperCase()} ${it}"
    }
}

class Step6 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        operator fun invoke(init: Request.() -> Unit) {
            this.init()
        }
    }

    class Response(var code: StatusCode, var body: Body) {
        operator fun invoke(function: Response.() -> Unit) {
            TODO("Not yet implemented")
        }
    }
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) { }

    fun main() {
        class Request(var method: HttpMethod, var path: Path, var body: Body) {
            operator fun invoke (init: (Request) -> Unit) {
            }
        }

        mock {
            request {
                path = "/hello"
                body = "{name: Mike}"
            }
            response {
                code = 201
                body = "typejson here"
            }
        }
    }
}

// remove it from request
// header in the wrong way just one value it will override the value
class Step7 {
    data class Header(var name: Name, var values: Set<Value>) {
        operator fun invoke(function: Header.() -> Unit) {
            TODO("Not yet implemented")
        }
    }

    class Request(var method: HttpMethod, var path: Path, var body: Body, var header: Header) {
        operator fun invoke(function: Request.() -> Unit) {
            TODO("Not yet implemented")
        }
    }
    class Response(var code: StatusCode, var body: Body) {
        operator fun invoke(function: Response.() -> Unit) {
            TODO("Not yet implemented")
        }
    }
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) { }

    fun main() {
        class Request(var method: HttpMethod, var path: Path, var body: Body) {
            operator fun invoke (init: (Request) -> Unit) {
            }
        }

        mock {
            request {
                path = "/hello"
                body = "{name: Mike}"
                header {
                    name = "x-flow-id"
                    values = setOf("v1", "v2")
                }
                header {// wrong
                    name = "other"
                    values = setOf("v1", "v2")
                }
            }
            response {
                code = 201
                body = "typejson here"
            }
        }
    }
}


// header as a function
// show other headers declarations options
class Step8 {
    data class Header(var name: Name = "", var values: Set<Value> = emptySet())

    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        val headers: MutableSet<Header> = mutableSetOf()

        operator fun invoke(function: Request.() -> Unit) {
        }

        fun header(init: Header.() -> Unit): Header {
            return Header().apply {
                init()
                headers += this
            }
        }

        fun header(name: Name, init: () -> Set<Value>): Header {
            TODO()
        }
    }
    class Response(var code: StatusCode, var body: Body) {
        operator fun invoke(function: Response.() -> Unit) {
            TODO("Not yet implemented")
        }
    }
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) { }

    fun main() {
        class Request(var method: HttpMethod, var path: Path, var body: Body) {
            operator fun invoke (init: (Request) -> Unit) {
            }
        }

        mock {
            request {
                path = "/hello"
                body = "{name: Mike}"
                header {
                    name = "x-flow-id"
                    values = setOf("v1", "v2")
                }
                header {// wrong
                    name = "other"
                    values = setOf("v1", "v2")
                }

                // other options

                header {
                    "x-flow-id" to setOf("v1", "v2")
                }

                header(name = "xflow-id") {
                    setOf("v1", "v2")
                }

            }
            response {
                code = 201
                body = "typejson here"
            }
        }
    }
}


// header as a function
// final example, creating a better header
class Step9 {

    class HeaderBuilder {
        val headers: MutableMap<String, PartialHeader> = mutableMapOf()

        infix fun withName(name: String): HeaderBuilder.PartialHeader {
            return headers.computeIfAbsent(name, ::PartialHeader)
        }

        class PartialHeader (val name: String) {
            val values = mutableSetOf<Value>()

            infix fun withValue(value: String): PartialHeader {
                values += value
                return this
            }
        }

        fun build(): Set<Header> {
            return this.headers.values.map { Header(it.name, it.values) }.toSet()
        }
    }


    data class Header(var name: Name = "", var values: Set<Value> = emptySet()) {
        operator fun invoke(function: Header.() -> Unit) {
        }
    }

    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        val header: HeaderBuilder = HeaderBuilder()

        operator fun invoke(function: Request.() -> Unit) {
        }

        fun headers(): Set<Header> = header.build()
    }
    class Response(var code: StatusCode, var body: Body) {
        operator fun invoke(function: Response.() -> Unit) {
            TODO("Not yet implemented")
        }
    }
    class Handler(val request: Request, val response: Response)
    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {
        class Request(var method: HttpMethod, var path: Path, var body: Body) {
            operator fun invoke (init: (Request) -> Unit) {
            }
        }

        mock {
            request {
                path = "/hello"
                body = "{name: Mike}"
                header withName "x-flow-id" withValue "v1" withValue "v2"
            }
            response {
                code = 201
                body = "typejson here"
            }
        }
    }
}