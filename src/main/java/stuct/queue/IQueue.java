package stuct.queue;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/23 23 58
 */
public interface IQueue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e); //入队
    E dequeue();//出队
    E getFront(); //获得最前的
}
