package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        int c1, c2;
        int numberOfPurchasedTickets = 0;
        int currentIncome = 0;
        boolean condition = true;
        System.out.println("Enter the number of rows:");
        x = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        y = scanner.nextInt();
        String[][] cinema = new String[x][y];
        initializerArray(cinema, x, y);
        while (condition) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    ShowSeats(cinema, x, y);
                    break;
                case 2:
                    boolean validation = true;
                    do {
                        System.out.println("Enter a row number:");
                        c1 = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        c2 = scanner.nextInt();
                        if (c1 > x || c2 > y || c2 < 0 || c1 < 0) {
                            System.out.println("Wrong input!");
                        } else {
                            if (cinema[c1 - 1][c2 - 1] != "B" && cinema[c1 - 1][c2 - 1] != "B ") {
                                currentIncome+=buyATicket(cinema, x, y, c1, c2);
                                numberOfPurchasedTickets++;
                                validation = false;
                            } else {
                                System.out.println("That ticket has already been purchased!");
                            }
                        }
                    }while (validation);
                    break;
                case 3:
                    statistic(x, y, numberOfPurchasedTickets, currentIncome);
                    break;
                case 0:
                default:
                    condition = false;
                    break;
            }
        }
    }
    public static void ShowSeats(String[][] cinema, int x, int y) {
        //show seats
        System.out.println("Cinema:");
        for (int i = 0; i <= y; i++) {
            if (i != 0) {
                if (i == y) {
                    System.out.println(i);
                } else {
                    System.out.print(i + " ");
                }
            } else {
                System.out.print("  ");
            }
        }
        for (int i = 0; i < x; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < y; j++) {
                if (j == y - 1) {
                    System.out.println(cinema[i][j]);
                } else {
                    System.out.print(cinema[i][j]);
                }
            }
        }
    }
    public static void initializerArray(String[][] cinema, int x, int y) {
        //inicializar el arreglo
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (j == y - 1) {
                    cinema[i][j] = "S";
                } else {
                    cinema[i][j] = "S ";
                }
            }
        }
    }
    public static int buyATicket(String[][] cinema, int x, int y, int c1, int c2) {
        //Buy a ticket
        int total;
        if (x * y < 60) {
            total = 10;
        } else {
            if (x % 2 == 0) {
                if (x / 2 >= c1) {
                    total = 10;
                } else {
                    total = 8;
                }
            } else {
                if ((x / 2) >= c1) {
                    total = 10;
                } else {
                    total = 8;
                }
            }
        }
        System.out.println("Ticket price: $" + total);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j <= y - 1; j++) {
                if (i + 1 == c1 && j + 1 == c2) {
                    if (j == y - 1) {
                        cinema[i][j] = "B";
                    } else {
                        cinema[i][j] = "B ";
                    }
                }
            }
        }
        return total;
    }
    public static void statistic(int x, int y, int numberOfPurchasedTickets, int currentIncome) {
        float num = numberOfPurchasedTickets;
        //percentage
        float percentage = 100 * num / (x * y);
        String resultado = String.format("%.2f", percentage);
        int totalIncome;
        if (x * y < 60) {
            totalIncome = 10 * x * y;
        } else {
            if (x % 2 == 0) {
                totalIncome = ((10 * x * y) / 2) + ((8 * x * y) / 2);
            } else {
                totalIncome = ((x / 2) * y * 10) + ((8 * (x + 1) * y) / 2);
            }
        }
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.println("Percentage: " + resultado + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}