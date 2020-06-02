package br.com.kotlin.dsl.examples.html.dsl

import br.com.kotlin.dsl.examples.html.builder.Style
import br.com.kotlin.dsl.examples.html.builder.Title


class Header {
    lateinit var title: Title

    operator fun invoke(init: Header.() -> Unit) {
        this.init()
    }
}

class Body {
    private val divs: MutableList<Div> = mutableListOf()

    operator fun invoke(init: Body.() -> Unit) {
        this.init()
    }

    fun div(init: Div.() -> Unit): Div {
        return Div().apply {
            init()
            divs += this
        }
    }

    fun divs(): List<Div> = divs.toList()
}

class Div {
    lateinit var style: Style
    val content: Content = Content()
    override fun toString(): String {
        return "Div(style='$style', content='$content')"
    }
}

class Content {
    lateinit var content: String
    operator fun invoke(init: () -> String) {
        this.content = init()
    }
    override fun toString(): String {
        return "Content(content='$content')"
    }
}

data class Html(val header: Header, val body: Body) {
    companion object {
        operator fun invoke(init: Html.() -> Unit): Html {
            return Html(Header(), Body()).apply(init)
        }
    }
}

fun renderHtml(init: Html.() -> Unit) {
    val html: Html = Html(init)

    println("title: ${html.header.title}")
    println("title: ${html.body.divs()}")
}

fun main() {
    renderHtml {



        header {
            title = "title1"
        }
        body {
            div {
                style = "style1"
                content { "content1" }
            }
            div {
                style = "style2"
                content { "content2" }
            }
        }







    }
}

