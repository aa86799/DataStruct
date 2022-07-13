package algorithm.bsearch;

// 二分查找, 针对一个有序 序列；依赖顺序的数据结构
public class BinarySearch {

    public static void main(String[] args) {
//        System.out.println(3+ (5>>1)); // 3 + 5 >> 1  先加减，后移位
        System.out.println(1+ (1>>1)); // 3 + 5 >> 1  先加减，后移位


    }

    // 非递归实现
    int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) { // 相交退出
//            int mid = (low + high) / 2; // 加法有 溢出风险
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // 递归实现 。 即在改变 上面非循环实现中的  low、high 时，使用递归
    int bsearchRecursion(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    // 二分查找：第一个等于 搜索值 的索引
    public int bsearchFirst(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else { // a[mid] == value
                /*
                 * 要确定 mid 是不是第一个等于 value 的元素；
                 * 当于0，肯定是第一个； 当前前一个不等于，肯定是第一个；
                 * 若前一个等于，高位索引 = mid -1
                 */
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    // 二分查找：第一个等于 搜索值 的索引。 没有上一个方法好理解
    public int bsearchFirst2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else { // <
                low = mid + 1;
            }
        }

        /*
         * 求顺序序列中，第一个指定的元素；
         * 上面循环中  a[low] < value ，low 不断以 mid 右移1位
         */
        if (low < n && a[low]==value) return low;
        else return -1;
    }

    // 查找最后一个值等于给定值的元素
    public int bsearchLast(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else { // a[mid] == value
                /*
                 * 后一个 不等于 value
                 */
                if ((mid == n-1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    // 查找第一个大于等于给定值的元素
    public int bsearchFirstBigEq(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                // 前一个 小于，当前就是 >=
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else { // a[mid] < value
                low = mid + 1;
            }
        }
        return -1;
    }

    // 查找最后一个值 小于等于 给定值的元素
    public int bsearchLastSmallEq(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else { // a[mid] <= value
                /*
                 * 后一个 大于 value时，那当就就是小于等于
                 */
                if ((mid == n-1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}

