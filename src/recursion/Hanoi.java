package recursion;

/**
 * 汉诺塔 问题
 * 有塔A、B、C， 其中A中叠放了一些盘子，最下层的盘子最大，最上层的最小
 * 要将A的盘子移动到C，且较大的盘子不能放在较小的盘子上
 * 
 * @author stone
 * @date   2015-6-29 下午9:56:53
 */
public class Hanoi {
	
	public static void main(String[] args) {
		int disk = 3; //盘子
		move(disk, 'A', 'B', 'C');
	}
	
	/*
	 * 根据题意，从上向下编号 => 1 ~ n
	 */
	/**
	 * 
	 * @param topN 源塔顶的盘子编号
	 * @param from 从哪个塔移动
	 * @param inter 中介，过渡的塔
	 * @param to  目的塔
	 */
	private static void move(int topN, char from, char inter, char to) {
		if (topN == 1) {
			System.out.println("Disk 1 from " + from + " to " + to);
		} else {
			move(topN - 1, from, to, inter); //从from移动到中间inter
			System.out.println("Disk " + topN + " from " + from + " to " + to);
			move(topN - 1, inter, from, to); //从inter移动到to
		}
	}
	
	
}
