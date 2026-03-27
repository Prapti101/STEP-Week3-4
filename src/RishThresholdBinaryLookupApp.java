import java.util.Arrays;

public class RishThresholdBinaryLookupApp {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearSearch(int[] arr, int target) {

        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {

            linearComparisons++;

            if (arr[i] == target)
                return i;
        }

        return -1;
    }

    public static int floorValue(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        int floor = -1;

        binaryComparisons = 0;

        while (low <= high) {

            binaryComparisons++;

            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] < target) {

                floor = arr[mid];
                low = mid + 1;
            }

            else
                high = mid - 1;
        }

        return floor;
    }

    public static int ceilingValue(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        int ceiling = -1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] > target) {

                ceiling = arr[mid];
                high = mid - 1;
            }

            else
                low = mid + 1;
        }

        return ceiling;
    }

    public static int insertionPoint(int[] arr, int target) {

        int low = 0;
        int high = arr.length;

        while (low < high) {

            int mid = (low + high) / 2;

            if (arr[mid] < target)
                low = mid + 1;

            else
                high = mid;
        }

        return low;
    }

    public static void printArray(int[] arr) {

        for (int v : arr) {
            System.out.print(v + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] risks = {50, 10, 100, 25};

        int threshold = 30;

        System.out.println("Unsorted Risks:");

        printArray(risks);

        int linearIndex =
                linearSearch(risks, threshold);

        System.out.println(
                "\nLinear Search threshold="
                        + threshold +
                        " → "
                        + (linearIndex == -1 ? "not found" : "found")
                        + " (" +
                        linearComparisons +
                        " comparisons)");

        Arrays.sort(risks);

        System.out.println(
                "\nSorted Risks:");

        printArray(risks);

        int floor =
                floorValue(risks, threshold);

        int ceiling =
                ceilingValue(risks, threshold);

        int insert =
                insertionPoint(risks, threshold);

        System.out.println(
                "\nBinary floor("
                        + threshold +
                        "): "
                        + floor +
                        ", ceiling: "
                        + ceiling +
                        " (" +
                        binaryComparisons +
                        " comparisons)");

        System.out.println(
                "Insertion point for "
                        + threshold +
                        ": index "
                        + insert);
    }
}