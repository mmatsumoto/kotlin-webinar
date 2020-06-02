package br.com.kotlin.dsl


class ExStep1 {

    class Request(var method: HttpMethod, var path: Path, var body: Body)

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String)

    class Handler(var request: Request, var response: Response)

    fun mock(handler: (Handler) -> Unit) {

    }

    fun main() {

        mock {
            it.request.method = "GET"
            it.request.path = "/foobar"
            it.request.body.content = "{'name':'Mike'}"

            it.response.code = 201
            it.response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}

// remove it
class ExStep2 {

    class Request(var method: HttpMethod, var path: Path, var body: Body)

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String)

    class Handler(var request: Request, var response: Response)

    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {

        mock {
            request.method = "GET"
            request.path = "/foobar"
            request.body.content = "{'name':'Mike'}"

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }

    //  anonymous function of extension function here - Context Object
    fun test(init: String.(String) -> String) {
        println(init("hello", "mike"))
        println("hello".init("mike"))
    }

    fun test() {
        test {
            "${this.toUpperCase()} ${it}"
        }
    }
}


// invoke as body - request { body = aaa, path = 111 }
class ExStep3 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        operator fun invoke(init: (Request) -> Unit) {
        }
    }

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String)

    class Handler(var request: Request, var response: Response)

    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {

        mock {
            request {
                it.method = "GET"
                it.path = "/foobar"
                it.body.content = "{'name':'Mike'}"
            }

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}

// remove request.body.content = "xx" with body { "request " }
class ExStep4 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {
        operator fun invoke(init: Request.() -> Unit) {
        }
    }

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String) {
        operator fun invoke(init: () -> String) {
        }
    }

    class Handler(var request: Request, var response: Response)

    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {

        mock {
            request {
                method = "GET"
                path = "/foobar"
                body {
                    "request content"
                }
            }

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}

// adding method header(): Header { }
class ExStep5 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {

        private val headers: MutableSet<Header> = mutableSetOf()

        operator fun invoke(init: Request.() -> Unit) {
        }

        fun header(initHeader: (Header) -> Unit): Header {
            val header = Header().apply(initHeader)
            headers += header
            return header
        }
    }

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String) {
        operator fun invoke(init: () -> String) {
        }
    }

    class Header {
        lateinit var name: Name
        var values: List<Value> =  emptyList()
    }

    class Handler(var request: Request, var response: Response)

    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {
        mock {
            request {
                method = "GET"
                path = "/foobar"
                body {
                    "request content"
                }

                header {
                    it.name = "h1"
                    it.values = listOf("value1", "value2")
                }
            }

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}


// adding method header(): Header { } remove the it
class ExStep6 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {

        private val headers: MutableSet<Header> = mutableSetOf()

        operator fun invoke(init: Request.() -> Unit) {
        }

        fun header(initHeader: Header.() -> Unit): Header {
            val header = Header().apply(initHeader)
            headers += header
            return header
        }
    }

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String) {
        operator fun invoke(init: () -> String) {
        }
    }

    class Header {
        lateinit var name: Name
        var values: List<Value> =  emptyList()
    }

    class Handler(var request: Request, var response: Response)

    fun mock(handler: Handler.() -> Unit) {

    }

    fun main() {
        mock {
            request {
                method = "GET"
                path = "/foobar"
                body {
                    "request content"
                }

                header {
                    name = "h1"
                    values = listOf("value1", "value2")
                }
            }

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}

// adding HeaderBuilder - infix withName
class ExStep7 {
    class Request(var method: HttpMethod, var path: Path, var body: Body) {

        operator fun invoke(init: Request.() -> Unit) {}

        val header: HeaderBuilder = HeaderBuilder()

        fun headers(): Set<Header> = this.header.build()
    }

    class Response(var code: StatusCode, var body: Body)

    class Body(var content: String) {
        operator fun invoke(init: () -> String) {
        }
    }

    class Header(val name: Name, values: Set<Value>)

    class HeaderBuilder {
        private val headers: MutableMap<String, PartialHeader> = mutableMapOf()

        infix fun withName(name: Name) : PartialHeader {
            return headers.computeIfAbsent(name, ::PartialHeader)
        }

        class PartialHeader(val name: Name) {
            val values = mutableSetOf<Value>()

            infix fun withValue(value: Value): PartialHeader {
                return this.apply { values += value }
            }
        }

        fun build(): Set<Header> {
            return this.headers.values.map { Header(it.name, it.values) }.toSet()
        }
    }

    class Handler {
        lateinit var request: Request
        lateinit var response: Response
    }

    fun mock(init: Handler.() -> Unit) {
        val handler = Handler().apply(init)

        // do something with
        handler.request.body
        handler.request.headers()

        handler.response.body
    }

    fun main() {
        mock {
            request {
                method = "GET"
                path = "/foobar"
                body {
                    "request content"
                }

                header withName "h1" withValue "v1" withValue "v2"
            }

            response.code = 201
            response.body.content = "{'message': 'Hello Mike'}"
        }

    }
}