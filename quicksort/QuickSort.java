
public class QuickSort {
    /**
     * Simplest template for quick sort. 
     */
    public static void quickSort(int[] array, int low, int high) {
        int left = low, right = high, mid = array[left + right >> 1];
        while (left < right) {
            while (array[left] < mid) ++left;
            while (array[right] > mid) --right;
            if (left <= right) {
                exchange(array, left, right);
                ++left;
                --right;
            }
        }
       
        if (left < high) quickSort(array, left, high);
        if (low < right) quickSort(array, low, right);
    }
    
    /**
     * Sedgewick quick sort. Shuffle first to protect against worst case.
     */
    public static void sedgewickQuickSort(int[] array) {
        shuffle(array);
        quickSort2(array, 0, array.length - 1);
    }
    
    public static void quickSort2(int[] array, int low, int high) {
        if (low >= high) return;
        int mid = partition(array, low, high);
        quickSort2(array, low, mid - 1);
        quickSort2(array, mid + 1, high);
    }
    
    /** The partition pivot is the last element */
    private static int partition(int[] array, int low, int high) {
        int left = low - 1, right = high;
        while (true) {
            while (array[++left] < array[high]);
            while (array[high] < array[--right]) {
                if (right == low) break;
            }
            if (left >= right) break;
            exchange(array, left, right);
        }
        exchange(array, left, high);
        return left;
    }
    
    private static void exchange(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    
    private static void shuffle(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; ++i) {
            int target = i + (int)Math.random() * (len - i);
            exchange(array, i, target);
        }
    }
    
    private static void printArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i != array.length -1 ) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
        int[] numbers = new int[] {1, 4, 3, 6, 3, 2};
        QuickSort.quickSort(numbers, 0, numbers.length - 1);
        printArray(numbers);
        
        int[] numbers2 = new int[] {1, 4, 3, 6, 3, 2};
        QuickSort.sedgewickQuickSort(numbers2);
        printArray(numbers2);
        
    }
}