package stuct;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 11 24
 */
public class TestTime {

    public static void main(String[] args) {

    }

    private static double testRunTime() {
        long startTime = System.nanoTime();

        int count = 1000*1000;
        //添加
        for (int i = 0; i < count; i++) {
        }

        //删除
        for (int i = 0; i < count / 3; i++) {

        }

        return (System.nanoTime() - startTime) / 1.0e9;
    }
}
