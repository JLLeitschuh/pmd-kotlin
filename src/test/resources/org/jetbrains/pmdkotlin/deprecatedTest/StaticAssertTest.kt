fun foo(a: Int, b: Int?) {
    if (b == null) {
        println(a)
    } else {
        println(b)
    }
}

fun Main() {
    foo(5, null: Int?)
}