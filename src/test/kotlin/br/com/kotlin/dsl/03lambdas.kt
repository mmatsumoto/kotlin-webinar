package br.com.kotlin.dsl

// tip: /lambda

fun higherorder1(x: Int, myFunc: (Int) -> Int) {
    println(myFunc(3))
}


fun main() {

    higherorder1(3) {x: Int ->
        x
    }
































    /*
            List<Integer> result = IntStream.rangeClosed(1, 100)
            // stream()
                .filter(x -> x % 2 == 0)
                .mapToObj(x -> x * 2)
                .collect(Collectors.toList());

     */
    val result: List<Int> = (1..100).filter { it % 2 == 0 }
                                    .map    { it * 2 }



}