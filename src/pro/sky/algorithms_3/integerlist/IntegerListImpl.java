package pro.sky.algorithms_3.integerlist;


import pro.sky.algorithms_3.exception.InvalidIndexException;
import pro.sky.algorithms_3.exception.ItemNotFoundException;
import pro.sky.algorithms_3.exception.NullParameterException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[10];
    }

    @Override
    public Integer add(Integer item) {
        if (size >= storage.length) {
            grow();
        }
        storage[size++] = item;
        return item;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, (int) (storage.length + (storage.length * 0.5)));
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int itemIndex = indexOf(item);
        if (itemIndex == -1) {
            throw new ItemNotFoundException();
        }
        if (itemIndex != storage.length - 1) {
            System.arraycopy(storage, itemIndex + 1, storage, itemIndex, size - itemIndex);
        }
        size--;
        if (size < storage.length / 2) {
            resize();
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index >= size || index < 0) {
            throw new InvalidIndexException();
        }
        Integer itemToRemove = storage[index];
        if (index != storage.length - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        size--;
        if (size < storage.length / 2) {
            resize();
        }
        return itemToRemove;
    }

    private void resize() {
        storage = Arrays.copyOf(storage, (storage.length - (storage.length / 3)));
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageClone = storage.clone();
        sort(storageClone, 0, size - 1);
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(storageClone[mid])) {
                return true;
            }
            if (item < storageClone[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index <= size && index >= 0) {
            return storage[index];
        }
        throw new InvalidIndexException();
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullParameterException();
        }
        return Arrays.equals(toArray(), otherList.toArray());
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void sort(Integer[] arr, Integer begin, Integer end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, Integer begin, Integer end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, Integer left, Integer right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
