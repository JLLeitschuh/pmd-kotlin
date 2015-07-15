fun testfun(z : Char, x : Double, y : Char) {
    var a = 2 * x;
    var b = 3 * a;
    val c = 1 * 2 * 3 * 4 * 5;
    var t = y;

    y = 'a';

    return;
}

fun factorial10() : Int {
    /* it's comment */
    var start = 1
    for (i in (1..10)) {
        start *= i
    }

    return start
}


fun mus(x : Int, y : Int) : Int {
    return y + x
}
