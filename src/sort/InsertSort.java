package sort;

import java.util.Random;

/**
 * 插入排序
 * @author stone
 *
 */
public class InsertSort {
	public static void main(String[] args) {
		int len = 10;
		int[] ary = new int[len];
		Random random = new Random();
		for (int j = 0; j < len; j++) {
			ary[j] = random.nextInt(1000);
		}
		System.out.println("-------排序前------");
//		ary=new int[]{10,9,8,7,6,5,4,3,2,1}; //测试
//		ary=new int[]{1,2,3,4,5,6,7,8,10,9}; //测试
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
//		insertAsc1(ary);//用交换操作，效率略低
		insertAsc2(ary);//这种写法不错, 采用单向赋值操作
//		insertAsc3(ary);//跟2的逻辑一样，只写while变了for
	}
	/*
	 * 比较的元素从前两个开始，然后前三个，直至N
	 * 比较的方向为从后向前比较， 满足比较条件则交换
	 * 稳定的排序算法
	 * 比较的次数为1,2,3...N-1, 总共(N^2-N)
	 */
	static void insertAsc1(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		System.out.println("");
		for (int i = 1; i < len; i++) {// 每轮比较后前i+1个元素都是有序的
			for (int j = i; j > 0; j--) {
				if (ary[j] < ary[j - 1]) {// 右边的比左边的小，交换位置
					ary[j] = ary[j - 1] + (ary[j - 1] = ary[j]) * 0;
					changeCount++;
					compareCount++;
				} else {
					break;
				}
				
			}
		}
		System.out.println("\n-------insertAsc1升序排序后------比较次数：" + compareCount + "，交换次数：" + changeCount);
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
	}

	static void insertAsc2(int[] ary) {
		int compareCount = 0;//比较次数
		int setValueCount = 0;//赋值次数
		int len = ary.length;
		System.out.println("");
		for (int i = 1; i < len; i++) {
			int temp = ary[i];
			int j = i;
			while (j > 0 && ary[j - 1] >= temp) {
				ary[j] = ary[j - 1];//0赋给1， 1赋给2 ... 
				j--;
				compareCount++;
				setValueCount++;
			}
			if (ary[j] != temp) {
				ary[j] = temp; //这一轮的右边界值赋给比较后的 左边
				setValueCount++;
			}
		}
		System.out.println("\n-------insertAsc2升序排序后------比较次数：" + compareCount + "，赋值次数：" + setValueCount);
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
	}
	
	static void insertAsc3(int[] ary) {
		int compareCount = 0;//比较次数
		int setValueCount = 0;//赋值的次数
		int len = ary.length;
		System.out.println("");
		int temp;
		for (int i = 1; i < len; i++) {
			temp = ary[i];
			int j = i;
			for (; j > 0; j--) {
				if (temp < ary[j - 1]) {
					ary[j] = ary[j - 1];
					compareCount++;
					setValueCount++;
				} else {
					break;
				}
			}
			if (ary[j] != temp) {
				ary[j] = temp;
				setValueCount++;
			}
		}
		System.out.println("\n-------insertAsc3升序排序后------比较次数：" + compareCount + "，赋值次数：" + setValueCount);
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
	}
}
