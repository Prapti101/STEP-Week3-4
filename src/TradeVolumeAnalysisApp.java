public class TradeVolumeAnalysisApp {

    // Trade Class
    static class Trade {

        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        public String toString() {
            return id + ":" + volume;
        }
    }

    public static void mergeSort(Trade[] arr,
                                 int left,
                                 int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr,
                              int left,
                              int mid,
                              int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            if (L[i].volume <= R[j].volume) {

                arr[k] = L[i];
                i++;

            } else {

                arr[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSortDesc(Trade[] arr,
                                     int low,
                                     int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr,
                                 int low,
                                 int high) {

        int pivot = arr[high].volume;

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j].volume > pivot) {

                i++;

                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static Trade[] mergeTwoSorted(
            Trade[] arr1,
            Trade[] arr2) {

        int n1 = arr1.length;
        int n2 = arr2.length;

        Trade[] result =
                new Trade[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {

            if (arr1[i].volume <=
                    arr2[j].volume) {

                result[k++] = arr1[i++];

            } else {

                result[k++] = arr2[j++];
            }
        }

        while (i < n1)
            result[k++] = arr1[i++];

        while (j < n2)
            result[k++] = arr2[j++];

        return result;
    }


    public static int totalVolume(
            Trade[] arr) {

        int sum = 0;

        for (Trade t : arr) {
            sum += t.volume;
        }

        return sum;
    }

    public static void printTrades(
            Trade[] arr) {

        for (Trade t : arr) {
            System.out.println(t);
        }
    }
    public static void main(String[] args) {

        Trade[] trades = {

                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        System.out.println(
                "Original Trades:");

        printTrades(trades);

        // Merge Sort ASC
        mergeSort(trades, 0,
                trades.length - 1);

        System.out.println(
                "\nMerge Sort (Ascending):");

        printTrades(trades);

        // Quick Sort DESC
        quickSortDesc(trades,
                0,
                trades.length - 1);

        System.out.println(
                "\nQuick Sort (Descending):");

        printTrades(trades);

        // Morning & Afternoon Merge
        Trade[] morning = {

                new Trade("M1", 100),
                new Trade("M2", 200)
        };

        Trade[] afternoon = {

                new Trade("A1", 300),
                new Trade("A2", 400)
        };

        Trade[] merged =
                mergeTwoSorted(
                        morning,
                        afternoon);

        System.out.println(
                "\nMerged Morning + Afternoon:");

        printTrades(merged);

        int total =
                totalVolume(merged);

        System.out.println(
                "\nTotal Volume: "
                        + total);
    }
}