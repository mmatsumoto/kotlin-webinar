package br.com.kotlin.dsl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaUtil {

    public void foo1() {


        List<Integer> result = IntStream.rangeClosed(1, 100)
                .filter(x -> x % 2 == 0)
                .mapToObj(x -> x * x)
                .collect(Collectors.toList());

    }

    class Overload {

        public void route(String path) {
            route(path, null ,200);
        }

        public void route(Integer code) {
            route("/", null, code);
        }

        public void route(String path, String body) {
            route(path, body ,200);
        }

        public void route(String path, String body, Integer code) {
            // dosomething
        }

    }
}
