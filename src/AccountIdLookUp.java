import java.util.Arrays;

public class AccountIdLookUp {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearSearchFirst(String[] arr, String target) {

        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {

            linearComparisons++;

            if (arr[i].equals(target))
                return i;
        }

        return -1;
    }

    public static int linearSearchLast(String[] arr, String target) {

        int lastIndex = -1;

        for (int i = 0; i < arr.length; i++) {

            linearComparisons++;

            if (arr[i].equals(target))
                lastIndex = i;
        }

        return lastIndex;
    }

    public static int binarySearch(String[] arr, String target) {

        int low = 0;
        int high = arr.length - 1;

        binaryComparisons = 0;

        while (low <= high) {

            binaryComparisons++;

            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0)
                return mid;

            if (cmp < 0)
                low = mid + 1;

            else
                high = mid - 1;
        }

        return -1;
    }

    public static int countOccurrences(String[] arr, String target) {

        int count = 0;

        for (String s : arr) {

            if (s.equals(target))
                count++;
        }

        return count;
    }

    public static void printArray(String[] arr) {

        for (String s : arr) {
            System.out.print(s + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String[] logs = {
                "accB",
                "accA",
                "accB",
                "accC"
        };

        System.out.println("Original Logs:");

        printArray(logs);

        int first =
                linearSearchFirst(logs, "accB");

        int last =
                linearSearchLast(logs, "accB");

        System.out.println(
                "\nLinear Search First accB: index "
                        + first +
                        " (" +
                        linearComparisons +
                        " comparisons)");

        System.out.println(
                "Linear Search Last accB: index "
                        + last);

        Arrays.sort(logs);

        System.out.println(
                "\nSorted Logs:");

        printArray(logs);

        int binaryIndex =
                binarySearch(logs, "accB");

        int count =
                countOccurrences(logs, "accB");

        System.out.println(
                "\nBinary Search accB: index "
                        + binaryIndex +
                        " (" +
                        binaryComparisons +
                        " comparisons), count="
                        + count);

        System.out.println(
                "\nTime Complexity:");

        System.out.println(
                "Linear Search: O(n)");

        System.out.println(
                "Binary Search: O(log n)");
    }
}