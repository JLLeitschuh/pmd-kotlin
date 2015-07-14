fun factorial10() : Int {
    /* it's comment */
    var start = 1
    for (i in (1..10)) {
        start *= i
    }

    return start
}

fun factorial20() : Int {
    var res = 1
    for (n in (1..20)) {
        res *= n
    }
    return res
    // it's another comment
}

fun factorial30() : Int {
    var n = 30
    var ans = 1
    for (n in (1..n)) {
        ans = ans * n
    }

    return ans
}