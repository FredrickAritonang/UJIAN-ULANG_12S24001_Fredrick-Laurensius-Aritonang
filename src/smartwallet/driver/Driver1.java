package smartwallet.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import smartwallet.model.Account;

public class Driver1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        String line;
        while (!(line = sc.nextLine()).equals("---")) {
            String[] parts = line.split("#");
            if (parts[0].equals("create-account")) {
                String name = parts[1];
                String username = parts[2];
                String type = parts[3];
                accounts.add(new Account(name, username, type));
            }
        }
        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getAccountType() + "|" + acc.getBalance());
        }
    }
}