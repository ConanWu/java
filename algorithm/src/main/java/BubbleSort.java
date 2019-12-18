import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BubbleSort {

    public void bubbleSort(List<Integer> integerList) {
        Integer len = integerList.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1; j++) {
                if (integerList.get(j) > integerList.get(j + 1)) {
                    Integer temNumber = integerList.get(j);
                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temNumber);
                }
            }

        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> integerList = Arrays.asList(7, 3, 5, 2, 0, 12, 1);
        List<Integer> integerList1 = Stream.of(1, 5, 8, 0, 9, 4, 3, 2, 34, 51, 7, 21, 99, -1).collect(Collectors.toList());
        System.out.println(integerList);
        bubbleSort.bubbleSort(integerList);
        System.out.println(integerList);
        System.out.println(integerList1);
        bubbleSort.bubbleSort(integerList1);
        System.out.println(integerList1);

    }
}
