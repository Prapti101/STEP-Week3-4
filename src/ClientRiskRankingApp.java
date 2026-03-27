public class ClientRiskRankingApp {

    // Client Class
    static class Client {

        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore,
               double accountBalance) {

            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        public String toString() {
            return name + ":" + riskScore +
                    " (Balance=" +
                    accountBalance + ")";
        }
    }

    // Bubble Sort ASCENDING riskScore
    public static void bubbleSortAscending(
            Client[] clients) {

        int n = clients.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (clients[j].riskScore >
                        clients[j + 1].riskScore) {

                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;

                    swapCount++;

                    System.out.println(
                            "Swapped: "
                                    + clients[j].name
                                    + " and "
                                    + clients[j + 1].name);
                }
            }
        }

        System.out.println(
                "Total Swaps: " + swapCount);
    }

    // Insertion Sort DESC riskScore + balance
    public static void insertionSortDescending(
            Client[] clients) {

        for (int i = 1; i < clients.length; i++) {

            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 &&
                    compareDescending(
                            clients[j], key) > 0) {

                clients[j + 1] = clients[j];
                j--;
            }

            clients[j + 1] = key;
        }
    }

    private static int compareDescending(
            Client c1,
            Client c2) {

        if (c1.riskScore < c2.riskScore)
            return 1;

        if (c1.riskScore > c2.riskScore)
            return -1;

        // If riskScore same → compare balance
        if (c1.accountBalance <
                c2.accountBalance)
            return 1;

        if (c1.accountBalance >
                c2.accountBalance)
            return -1;

        return 0;
    }

    public static void printClients(
            Client[] clients) {

        for (Client c : clients) {
            System.out.println(c);
        }
    }

    public static void printTopRisks(
            Client[] clients,
            int topN) {

        System.out.println(
                "\nTop " + topN +
                        " Highest Risk Clients:");

        for (int i = 0;
             i < topN && i < clients.length;
             i++) {

            System.out.println(
                    clients[i].name +
                            "(" +
                            clients[i].riskScore +
                            ")");
        }
    }

    public static void main(String[] args) {

        Client[] clients = {

                new Client(
                        "clientC", 80, 50000),

                new Client(
                        "clientA", 20, 20000),

                new Client(
                        "clientB", 50, 30000),

                new Client(
                        "clientD", 90, 70000),

                new Client(
                        "clientE", 65, 40000)
        };

        System.out.println(
                "Original Clients:");

        printClients(clients);

        bubbleSortAscending(clients);

        System.out.println(
                "\nBubble Sort (Ascending):");

        printClients(clients);

        insertionSortDescending(clients);

        System.out.println(
                "\nInsertion Sort (Descending):");

        printClients(clients);

        printTopRisks(clients, 10);
    }
}