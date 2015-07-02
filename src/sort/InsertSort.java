package sort;


import utils.Util;

/**
 * 插入排序
 * @author stone
 *
 * 比较的元素从前两个开始，然后前三个，直至N
 * 比较的方向为从后向前比较， 满足比较条件则交换
 * 稳定的排序算法
 * 比较的次数为1,2,3...N-1, 总共(N^2-N)
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] ary = Util.generateIntArray(10);
		Util.printArray(ary);
        insertAsc1(ary);//用交换操作，效率略低  
//      insertAsc2(ary);//这种写法不错, 采用单向赋值操作  
//      insertAsc3(ary);//跟2的逻辑一样，只写while变了for 
		Util.printArray(ary);
 
    }  
  
    static void insertAsc1(int[] ary) {  
        int len = ary.length;  
        for (int i = 1; i < len; i++) {// 每轮比较后前i+1个元素都是有序的  
            for (int j = i; j > 0; j--) {  
                if (ary[j] < ary[j - 1]) {// 右边的比左边的小，交换位置  
                    ary[j] = ary[j - 1] + (ary[j - 1] = ary[j]) * 0;  
                } else {  
                	/*
                	 * 如果不用交换，因之前有序，说明当前j的位置不用动
                	 * 如  1, 3, 7, 4   比一下：1347，再比就不用动了  直接 break
                	 */
                    break;  
                }  
                  
            }  
        }  
    }  
    
    /*
     * 采用单向赋值操作  不是每次都交换赋值  直至不满足循环比较条件，才将右边界值赋给不满足比较条件的位置上
     */
    static void insertAsc2(int[] ary) {  
        int len = ary.length;  
        for (int i = 1; i < len; i++) {  
            int temp = ary[i];  
            int j = i;  
            while (j > 0 && ary[j - 1] >= temp) {  
                ary[j] = ary[j - 1];//0赋给1， 1赋给2 ...   
                j--;  
            }  
            if (ary[j] != temp) {  
                ary[j] = temp; //这一轮的右边界值赋给比较后的 左边  
            }  
        }  
    }  
      
    static void insertAsc3(int[] ary) {  
        int len = ary.length;  
        int temp;  
        for (int i = 1; i < len; i++) {  
            temp = ary[i];  
            int j = i;  
            for (; j > 0; j--) {  
                if (temp < ary[j - 1]) {  
                    ary[j] = ary[j - 1];  
                } else {  
                    break;  
                }  
            }  
            if (ary[j] != temp) {  
                ary[j] = temp;  
            }  
        }  
    }  
}
