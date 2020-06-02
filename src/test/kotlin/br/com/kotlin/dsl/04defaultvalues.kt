package br.com.kotlin.dsl

/* java

    class Overload {

        public void route(String path,
                          String request,
                          Integer code) {
            // dosomething
        }


        public void route(String path) {
            route(path, null, 200);
        }

        public void route(Integer code) {
            route("/", null, code);
        }

        public void route(String path, String request) {
            route(path, request, 200);
        }


    }
 */


fun route1(path: String, request: String? = null, code: Int = 200) {
}


fun main() {
    route1(path = "/hello")
    route1("/hello", "<request>")
    route1("/hello", "<request>", 201)

    // in any other
    route1(request = "<request>", code = 201, path = "/hello")

























    fun route2(path: String = "/",
               request: String? = null ,
               code: Int = 200,
               response: (request: String) -> String) {
        TODO()
    }










    route2(path = "/hello") {
        "<my-response>"
    }

    // coroutines async
    // beans

}