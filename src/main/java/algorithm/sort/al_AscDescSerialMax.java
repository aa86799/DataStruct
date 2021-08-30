package algorithm.sort;

import java.util.Arrays;

/**
 * 先升后降 求最大值
 */
public class al_AscDescSerialMax {

    //O(n)
    private static int getMax1(int[] ary) {
        int n = 0;
        for (int i = 1; i < ary.length; i++) {
            n++;
            if (ary[i] < ary[i - 1]) {
                System.out.println("getMax1 n=" + n);
                return ary[i - 1];
            }
        }
        System.out.println("-------");
        return 0;
    }

    private static int getMax2(int[] ary) {
        int l = 0;
        int r = ary.length - 1;
        int n = 0;
        int mid = 0;
        while (l >= 0 && l < ary.length && r >= 0 && r < ary.length) {
            n++;

            mid = l + (r - l) >> 1;
            if (ary[l] <= ary[mid]) {
                l++;
            } else {
                Util.swap(ary, l, mid);
            }
            if (ary[r] <= ary[mid]) {
                r--;
            } else {
                Util.swap(ary, r, mid);
            }
        }
        System.out.println("getMax2 n=" + n);
        System.out.println("-------");
        return ary[mid];
    }

    private static int getMax3(int[] ary) {
        int l = 0;
        int r = ary.length - 1;
        int n = 0;
        while (l < r) {
            n++;

            int mid = ((r - l) >> 1) + l; //位移运算优先级 比 加减法低 要加小括号
            if (ary[mid] < ary[mid + 1]) {//升序中
                l = mid + 1;
            } else {//降序中
                r = mid;
            }
        }
        System.out.println("getMax3 n=" + n);
        System.out.println("-------");
        return ary[l];
    }

    /*
     * 一个数组内的元素，一段升序，一段降序的排列；且仅有两段
     * 比 getMax3() 多考虑了 重复值问题
     */
    private static int getMax4(int[] ary, int l, int r) {
        int n = 0;
        while (l < r) {
            n++;

            int mid = ((r - l) >> 1) + l; //位移运算优先级 比 加减法低 要加小括号
            if (ary[mid] < ary[mid + 1]) {//升序中
                l = mid + 1;
            } else if (ary[mid] > ary[mid + 1]) {//降序中
                r = mid;
            } else {
                if (mid - 1 >= l) {
                    /*
                     * ary[mid] = ary[mid + 1]
                     * 这时不知在升序还是降序中; 所以与前一个进行判断
                     */
                    if (ary[mid] > ary[mid - 1]) {//升序中
                        l = mid;
                    } else if (ary[mid] < ary[mid - 1]) {//降序中
                        r = mid - 1;
                    } else {
                        /*
                         * ary[mid] = ary[mid + 1] = ary[mid - 1]
                         * 前中后都相等
                         * 那就分段递归
                         */
                        int left = getMax4(ary, l, mid - 1);
                        int right = getMax4(ary, mid + 1, r);
                        return left > right ? left : right;
                    }
                } else {
                    return ary[l];
                }
            }
        }
        System.out.println("getMax4 n=" + n);
        System.out.println("-------");
        return ary[l];
    }

    public static void main(String[] args) {
        int[] ary1 = Util.buildAscDesc(20);
        int[] ary2 = ary1.clone();
        int[] ary3 = ary1.clone();
        int[] ary4 = ary1.clone();
        System.out.println(Arrays.toString(ary1));
        System.out.println(getMax1(ary1) + "\n");
        System.out.println(getMax2(ary2) + "\n");
        System.out.println(getMax3(ary3) + "\n");
        System.out.println(getMax4(ary4, 0, ary4.length - 1) + "\n");
        int[] ary5 = new int[]{1, 2,2,2,3,3,3,2,2,1,1,1};
//        int[] ary5 = new int[]{10, 20, 15, 2, 23, 90, 6};
//        int[] ary5 = new int[]{1,2,2,2,2,3,1};
//        int[] ary5 = new int[]{1, 3, 4, 4, 4, 2, 0, 9, 10, 20, 13, 12, 5, 5, 5, 21, 21, 44, 44, 33, 22, 11}; //getMax4 不满足，因为有多段的升和降
        System.out.println(getMax4(ary5, 0, ary5.length - 1));
    }


}
