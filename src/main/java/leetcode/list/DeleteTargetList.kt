package leetcode.list

/**
 * desc:    从 source 里面 移除 target
 * author:  stone
 * email:   aa86799@163.com
 * time:    2023/8/24 10:32
 */
class DeleteTargetList {

    /*
     * O(n^2),  模拟20万条数据中删除 4286条，耗时 3300 ms
     */
    fun way1(source: ArrayList<String>, target: ArrayList<String>) {
        val it = source.iterator()
        while (it.hasNext()) {
            if (target.contains(it.next())) {
                it.remove()
            }
        }
    }

    /*
     * hash 查询 O(1)
     * 遍历source, if (!hashTarget.contains(it))  target 中不包含的 source 中的元素，最终应该出现在结果集中
     *
     * 整体 O(n)， 模拟20万条数据中删除 4286条，耗时 50 ms
     */
    fun way2(source: ArrayList<String>, target: ArrayList<String>) {
        val hashTarget = HashSet(target)
        val newList = arrayListOf<String>()
        source.forEach {
            if (!hashTarget.contains(it)) {
                newList.add(it)
            }
        }
        source.clear()
        source.addAll(newList)
    }
}

fun main() {
    val dtl = DeleteTargetList()

    val source = arrayListOf<String>()
    (1..200_000).forEach {
        source.add("index $it")
    }
    println("source.size=${source.size}")

    val target = arrayListOf<String>()
    (50000 until 200_000 step 35).forEach {// 1/35
        target.add(source[it - 1])
    }
    println("target.size=${target.size}")

    val begin = System.currentTimeMillis()

//    dtl.way1(source, target)
    dtl.way2(source, target)

    println("耗时:${System.currentTimeMillis() - begin}")

    println("source.size=${source.size}")
}

