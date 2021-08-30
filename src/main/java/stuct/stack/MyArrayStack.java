package stuct.stack;

/**
 * desc  : 栈：后进先出 LIFO last in first out 的 线性结构
 *             出栈 入栈 查看栈顶： O(1)
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/23 11 31
 */
public class MyArrayStack<E> implements IStack<E> {

    public static void main(String[] args) {
        MyArrayStack<Object> stack = new MyArrayStack<>(5);
        for (int i = 0; i < 4; i++) {
            stack.push(i+"-item");
        }
        System.out.println(stack);

        stack.push(99);
        stack.push(100);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println("peek: " + stack.peek());

        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }


    private MyDynamicGenericArray<E> array;

    public MyArrayStack(int capacity) {//给定初始容量
        array = new MyDynamicGenericArray<>(capacity);
    }

    public MyArrayStack() {
        array = new MyDynamicGenericArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("stack size: %d, capacity: %d, array: \n", getSize(), getCapacity()));
        sb.append("[");
        for (int i = 0; i < getSize(); i++) {
            sb.append(array.get(i));
            if (i != getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] 'stack top'\n");
        return sb.toString();
    }
}
