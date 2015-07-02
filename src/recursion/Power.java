package recursion;

/**
 * 求一个数的乘方
 * 求x^y，y是一个正整数。设计算器只能计算两数相乘，不能一次计算n个数相乘。
 * 知：2^5=(2^2)^2*2;   2^6=(2^2)^3=((4)^2)*4;  2^8=(2^2)^4= (4^2)^2= 16^2
 * 得到规律：x^y= (x^2)^(y/2)，定义a=x^2,b=y/2, 则得到形如： x^y= a^b;
 *     y如果是奇数，则分解的最后还要再乘以a(如上面2^6分解成4^3时)：x^y=a^b*a.
 *     
 * 用递归来解：那么每次x都传入一个新的值，即是a；y值倍减，即是b
 *   
 * @author stone
 * @date   2015-7-2 上午11:31:53
 */
public class Power {
	
	private static long pow(long x, long y) {
		if (x == 0 || x == 1) {
			return x;
		}
		long tx = 0;
		long ty = 0;
		if (y > 1) {
			ty = y / 2;
			tx = x * x;
			System.out.println(tx);
			
			if (y % 2 == 0) {
				return pow(tx, ty);
			} else {
				return pow(tx, ty) * x;
			}
		} else {
			return x;
		}
	}
	
	public static void main(String[] args) {
		long result = pow(2, 10);
		System.out.println(result);
	}
}
