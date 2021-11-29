package algorithm.recursion

/**
 * desc:    有1，2，5，10 四种面值的货币，组合成 总金额为10，这样的组合有多少个？
 *          递归实现
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2021/10/25 23:20
 */
class CurrencyCompose {
    companion object {
        var sumCount = 0
        private val rawList = listOf(1, 2, 5, 10)
        fun calc(total: Int, result: MutableList<Int>) {
            when {
                total == 0 -> {
                    println(result)
                    sumCount++
                }
                total < 0 -> return
                else -> {
                    rawList.forEach {
                        val newer = if (result.isNotEmpty()) mutableListOf() else result
                        if (result.isNotEmpty()) newer.addAll(result)
                        newer.add(it)
                        calc(total - it, newer)
                    }
                }
            }
        }
    }
}

fun main() {
    CurrencyCompose.calc(10, mutableListOf())
    println("共有${CurrencyCompose.sumCount}种组合")
}