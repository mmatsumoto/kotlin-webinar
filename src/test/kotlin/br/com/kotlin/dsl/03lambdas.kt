package br.com.kotlin.dsl

// simple lambda

fun higherorder1(λ: (Int) -> Int) {
    println(λ(3))
}

fun higherorder2(lambda: (x: Int, y:Int) -> Int) {
    println(lambda(2,2))
}

fun main() {
    higherorder1( { a -> a * a} )

    higherorder2({ x, y -> x * y })

    higherorder2 { x, y: Int ->
        x * y
    }

    higherorder1 {
        it * it
    }


    /*
            List<Integer> result = IntStream.rangeClosed(1, 100)
            // stream()
                .filter(x -> x % 2 == 0)
                .mapToObj(x -> x * 2)
                .collect(Collectors.toList());

     */
    val result =
            (1..100).filter { it % 2 == 0 }
                    .map    { it * 2 }


}