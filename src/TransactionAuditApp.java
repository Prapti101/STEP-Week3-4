import java.util.ArrayList;
import java.util.List;

public class TransactionAuditApp {

    // Transaction Class
    static class Transaction {

        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort by Fee
    public static void bubbleSortByFee(List<Transaction> list) {

        int n = list.size();
        boolean swapped;

        int passCount = 0;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {

            swapped = false;
            passCount++;

            for (int j = 0; j < n - i - 1; j++) {

                if (list.get(j).fee >
                        list.get(j + 1).fee) {

                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                    swapCount++;
                }
            }

            if (!swapped)
                break;
        }

        System.out.println(
                "Bubble Sort passes: " + passCount);

        System.out.println(
                "Bubble Sort swaps: " + swapCount);
    }

    public static void insertionSortByFeeTimestamp(
            List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {

            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    compare(list.get(j), key) > 0) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    private static int compare(
            Transaction t1,
            Transaction t2) {

        if (t1.fee > t2.fee)
            return 1;

        if (t1.fee < t2.fee)
            return -1;

        return t1.timestamp
                .compareTo(t2.timestamp);
    }

    public static List<Transaction>
    findHighFeeOutliers(List<Transaction> list) {

        List<Transaction> outliers =
                new ArrayList<>();

        for (Transaction t : list) {

            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        return outliers;
    }

    private static void printList(
            List<Transaction> list) {

        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions =
                new ArrayList<>();

        transactions.add(
                new Transaction("id1", 10.5, "10:00"));

        transactions.add(
                new Transaction("id2", 25.0, "09:30"));

        transactions.add(
                new Transaction("id3", 5.0, "10:15"));

        transactions.add(
                new Transaction("id4", 55.0, "11:00"));

        System.out.println("Original Transactions:");
        printList(transactions);

        List<Transaction> bubbleList =
                new ArrayList<>(transactions);

        bubbleSortByFee(bubbleList);

        System.out.println("\nBubble Sort Result:");
        printList(bubbleList);

        List<Transaction> insertionList =
                new ArrayList<>(transactions);

        insertionSortByFeeTimestamp(
                insertionList);

        System.out.println(
                "\nInsertion Sort Result:");
        printList(insertionList);

        List<Transaction> outliers =
                findHighFeeOutliers(transactions);

        System.out.println(
                "\nHigh-Fee Outliers:");

        if (outliers.isEmpty()) {

            System.out.println("None");

        } else {

            printList(outliers);
        }
    }
}