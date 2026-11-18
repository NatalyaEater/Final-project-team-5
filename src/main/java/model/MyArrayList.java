package model;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY=10;
    private Object[] elements;
    private int size;

    public MyArrayList(){
        this.elements=new Object[DEFAULT_CAPACITY];
        this.size=0;
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть отрицательной: " + initialCapacity);
        }

        this.elements=new Object[initialCapacity];
        this.size=0;
    }

    public void add(T element){
        if(size==elements.length){
            grow();
        }
        elements[size++]=element;
    }

    public T get(int index){
        checkIndex(index);
        return (T) elements[index];
    }

    public void set(int index, T element){
        checkIndex(index);
        elements[index] = element;
    }

    public int size(){
        return size;
    }
    private void grow(){
        int newCapacity=(elements.length==0)? DEFAULT_CAPACITY: elements.length+(elements.length>>1);
        elements=Arrays.copyOf(elements, newCapacity);

    }

    private void checkIndex(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Индекс: " + index + " некорректен, Размер: " + size);
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private int currentIndex;

            @Override
            public boolean hasNext(){
                return currentIndex<size;
            }

            @Override
            public T next(){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }
        };
    }
}
