import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("== SPLITWISE SERVICE ==");
        SplitwiseApp app = SplitwiseApp.getInstance();

        User saksham = app.createUser("Saksham", "abc@gmail.com", "12312");
        User saksham1 = app.createUser("Saksham1", "abc@gmail.com", "12312");
        User saksham2 = app.createUser("Saksham2", "abc@gmail.com", "12312");
        User saksham3 = app.createUser("Saksham3", "abc@gmail.com", "12312");
        User saksham4 = app.createUser("Saksham4", "abc@gmail.com", "12312");

        Group group1 = app.createGroup("group1", Arrays.asList(saksham, saksham1, saksham2));
        Group group2 = app.createGroup("group2", Arrays.asList(saksham3, saksham4));


        HashMap<User, Integer> expense1Paid = new HashMap<>();
        HashMap<User, Integer> expense1Owe = new HashMap<>();
        expense1Paid.put(saksham, 100);
        expense1Paid.put(saksham1, 900);
        expense1Owe.put(saksham, 333);
        expense1Owe.put(saksham1, 333);
        expense1Owe.put(saksham2, 334);
        group1.printPayments();
        group1.printSimplifiedTotalExpenseMap();
        group1.addExpense(new Expense("Dominos", 1000, expense1Paid, expense1Owe, ExpenseDistributionMethod.BY_AMOUNT));
//        group1.addTransaction(app.createTransaction(saksham, saksham2, 100));
//        group1.addTransaction(app.createTransaction(saksham2, saksham1, 100));
//        group1.addTransaction(app.createTransaction(saksham1, saksham, 100));
        group1.printPayments();
        group1.printSimplifiedTotalExpenseMap();
    }
}