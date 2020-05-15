package br.com.kotlin.webinar

import org.junit.Test


/*
a + b	a.plus(b)
a - b	a.minus(b)
a * b	a.times(b)
a / b	a.div(b)
a++	a.inc() + see below
a--	a.dec() + see below
+a	a.unaryPlus()
-a	a.unaryMinus()
!a	a.not()
a % b	a.rem(b), a.mod(b) (deprecated)
a..b	a.rangeTo(b)

 */
class Topic11 {

    @Test
    fun `test operators `() {
        var m1 = Money(1, "EUR")
        val m2 = Money(1, "EUR")

        val m3 = m1.plus(m2)

        val m4 = m1 + m2

        val m5 = m1++

    }
}




data class Money(val value: Int, val currency: String = "EUR") {

    operator fun plus(other: Money): Money {
        return Money(this.value + other.value, this.currency)
    }

    operator fun plus(other: Int): Money {
        return Money(this.value + other, this.currency)
    }

    operator fun inc(): Money {
        return Money(this.value + 1, this.currency)
    }
}




class ExampleDslRouter1 {
    data class Request(var contentType: String)
    data class Response(var body: String)
    data class Handler(val request: Request, var response: Response)

    fun getRouter(path: String, handler: (Handler) -> Unit) {
       // something
    }

    fun client() {
        getRouter("/somepath") {
            if(it.request.contentType == "application/json") {
                it.response.body = "{'message': 'some response'}"
            }
        }
    }
}
// how I can remove the it.request and it.response?





// In Extension func you use: this
// Ext func off anonymous func
class ExampleDslRouter2 {
    data class Request(var contentType: String)
    data class Response(var body: String)
    data class Handler(val request: Request, var response: Response)

    fun getRouter(path: String, handler: Handler.() -> Unit) { }

    fun client() {
        getRouter("/somepath") {
            if(request.contentType == "application/json") {
                response.body = "{'message' : 'some response'}"
            }
        }
    }
}

// how I can make remove the . from response.body to response { it.body = "" }


// invoke()
class ExampleDslRouter3 {
    data class Request(var contentType: String)
    data class Response(var body: String) {
        operator fun invoke(response: (Response) -> Unit) {
            TODO()
        }
    }
    data class Handler(val request: Request, var response: Response)

    fun getRouter(path: String, handler: Handler.() -> Unit) {}

    fun client() {
        getRouter("/somepath") {
            if(request.contentType == "application/json") {
                response {
                    it.body = "{'message' : 'some response'}"
                }
            }
        }
    }
}
// how I can remove again the it



// Ext func off anonymous func
class ExampleDslRouter4 {
    data class Request(var contentType: String)
    data class Response(var body: String) {
        operator fun invoke(response: Response.() -> Unit) {
            TODO()
        }
    }
    data class Handler(val request: Request, var response: Response)

    fun getRouter(path: String, handler: Handler.() -> Unit) {}

    fun client() {
        getRouter("/somepath") {
            if(request.contentType == "application/json") {
                response {
                    body = "{'message' : 'some response'}"
                }
            }
        }
    }
    /*  compare

    fun client() {
        getRouter("/somepath") {
            if(it.request.contentType == "application/json") {
                it.response.body = "{'message': 'some response'}"
            }
        }
    }

     */
}




class ExampleHtml1 {
    data class Html(var header: Header, var body: Body)
    data class Header(var title: String) {
        operator fun invoke(header: Header.() -> Unit) {}
    }
    class Body {
        fun div(div: Div.() -> Unit) { }
        operator fun invoke(body: Body.() -> Unit) { }
    }
    data class Div(var style: String, var content: String)

    fun renderHtml(browser: String, html: Html.() -> Unit) {}

    fun client() {
        renderHtml("chrome") {
            header {
                title ="Title Web page Chrome"
            }
            body {
                div {
                    style = "style1"
                    content = "content-1"
                }
                div {
                    style = "style2"
                    content = "content-2"
                }
            }
        }
    }
}



// using the dsl
class ExampleHtml2 {
    class Html(var initHtml: Html.() -> Unit) {
        var title : String? = "empty"

        fun build(): Html = this.apply { initHtml() }
    }

    fun renderHtml(browser: String, initHtml: Html.() -> Unit) {
        println("rendering $browser, $initHtml")

        val html = Html(initHtml).build()

        println("html.title: ${html.title}")
    }

    fun client() {
        renderHtml("chrome") {
           title = "Title Web page Chrome"
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleHtml2().client()
        }
    }
}

// using dsl with internal
class ExampleHtml3 {
    class Html(var initHtml: Html.() -> Unit) {
        lateinit var header: Header

        fun header(initHeader: Header.() -> Unit) {
            this.header = Header(initHeader).invoke()
        }

        fun build(): Html = this.apply { initHtml() }
    }

    class Header(var initHeader: Header.() -> Unit) {
        var title: String = "empty"

        operator fun invoke() = this.apply { initHeader() }
    }

    fun renderHtml(browser: String, initHtml: Html.() -> Unit) {
        println("rendering $browser, $initHtml")

        val html = Html(initHtml).build()

        println("html.header.title: ${html.header.title}")
    }

    fun client() {
        renderHtml("chrome") {
            header {
                title ="Title Web page Chrome"
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleHtml3().client()
        }
    }
}



