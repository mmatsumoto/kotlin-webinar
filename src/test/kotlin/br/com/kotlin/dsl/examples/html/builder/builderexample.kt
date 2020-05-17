package br.com.kotlin.dsl.examples.html.builder

typealias Title = String
typealias Style = String
typealias Content = String

data class Header(val title: Title)
data class Body(val divs: List<Div>)
data class Div(var style: Style, var content: Content)

data class Html(val header: Header, val body: Body) {

    companion object  {
        fun builder(): HtmlBuilder = HtmlBuilder()
    }

    class HtmlBuilder {
        lateinit var body: Body
        lateinit var header: Header

        fun withBody(body: Body): HtmlBuilder = this.apply { this.body = body }
        fun withHeader(header: Header): HtmlBuilder = this.apply { this.header = header }

        fun build(): Html {
            return Html(header, body)
        }
    }
}

fun renderHtml(html: Html) {
    println("title: ${html.header.title}")
    println("title: ${html.body.divs}")
}

fun main() {
    val html: Html = Html.builder()
            .withHeader(Header("Hallo Deutschland"))
            .withBody(Body(
                    divs = listOf(
                            Div("style1",
                                                                                           "<div>foobar1</div>"),
                            Div("style2",
                                                                                           "<div>foobar2</div>"))
                                                                                     ))
            .build()

    renderHtml(html)
}


