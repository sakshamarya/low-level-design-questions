import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Group {
    private AtomicLong groupId = new AtomicLong(0);
    private String groupName;
    private List<User> groupMembers;
    private List<Expense> expenses;
    private List<Transaction> transactions;
    private boolean isSimplifiedPaymentsOn = false;
    private SimplifyService simplifyService = new SimplifyService();

    public Group(String groupName, List<User> groupMembers) {
        this.groupId = new AtomicLong(groupId.incrementAndGet());
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.expenses = new CopyOnWriteArrayList<>();
        this.transactions = new CopyOnWriteArrayList<>();
    }

    public void printPayments(){
        /*
        * Build a map having A -> (B, 10), (c, 20) :: This denotes A needs to give Rs 10 to B and Rs 20 to C
        * */

        HashMap<String, Integer> paymentMap = new HashMap<>();

        for(Expense expense: expenses){
            List<Map.Entry<User, Integer>> userPaid = expense.getUsersPaid().entrySet().stream().collect(Collectors.toList());
            List<Map.Entry<User, Integer>> userOwe = SplitFactory.getSplitService(expense.getExpenseDistributionMethod()).getSplitAmounts(expense).entrySet().stream().collect(Collectors.toList());

            for(int i=0;i<userPaid.size();i++){
                for(int j=0;j<userOwe.size();j++){
                    int p = userPaid.get(i).getValue();
                    int o = userOwe.get(j).getValue();
                    UserPair userPair = new UserPair(userOwe.get(j).getKey(), userPaid.get(i).getKey());
                    if(userPaid.get(i).getKey()!=userOwe.get(j).getKey()){
                        int mini = Math.min(p, o);
                        userOwe.get(j).setValue(o-mini);
                        paymentMap.put(userPair.toString(), paymentMap.getOrDefault(userPair.toString(), 0)+mini);
                    }
                }
            }

        }

        for(Transaction transaction: transactions){
            UserPair userPair = new UserPair(transaction.getFromUser(), transaction.getToUser());

            paymentMap.put(userPair.toString(), paymentMap.getOrDefault(userPair.toString(), 0) - transaction.getAmount());

            if(paymentMap.get(userPair.toString()) < 0){
                UserPair userPair1 = new UserPair(transaction.getToUser(), transaction.getFromUser());
                paymentMap.put(userPair1.toString(), paymentMap.getOrDefault(userPair1.toString(), 0)+Math.abs(paymentMap.get(userPair.toString())));
                paymentMap.remove(userPair.toString());
            }
        }

        for(Map.Entry<String, Integer> entry: paymentMap.entrySet()){
            if(entry.getValue() > 0){
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

        }

    }

    private HashMap<User, Integer> getTotalExpenseMap(){
        HashMap<User, Integer> totalExpenseMap = new HashMap<>();

        for(Expense expense : expenses) {
            HashMap<User, Integer> expenseMap = SplitFactory.getSplitService(expense.getExpenseDistributionMethod()).getSplitAmounts(expense);
            for(Map.Entry<User, Integer> expense1: expenseMap.entrySet()){
                int amount = totalExpenseMap.getOrDefault(expense1.getKey(), 0) + expense1.getValue();
                totalExpenseMap.put(expense1.getKey(), amount);
            }
        }

        // process the transactions as well

        for(Transaction transaction: transactions){
            User fromUser = transaction.getFromUser();
            User toUser = transaction.getToUser();
            int amount = transaction.getAmount();

            // Deduct from the payer
            totalExpenseMap.put(fromUser, totalExpenseMap.getOrDefault(fromUser, 0) - amount);
            // Add to the receiver
            totalExpenseMap.put(toUser, totalExpenseMap.getOrDefault(toUser, 0) + amount);
        }

        return totalExpenseMap;
    }

    public void printSimplifiedTotalExpenseMap() {
        simplifyService.printSimplifiedMap(getTotalExpenseMap());
    }

    public void setSimplifiedPaymentsOn(boolean simplifiedPaymentsOn) {
        isSimplifiedPaymentsOn = simplifiedPaymentsOn;
    }

    public void printAllPayments(){
        if(isSimplifiedPaymentsOn) {
            printSimplifiedTotalExpenseMap();
        } else {
            printPayments();
        }
    }

    public void addUserToGroup(User user) {
        if (!groupMembers.contains(user)) {
            groupMembers.add(user);
            System.out.println("User " + user.getUserName() + " added to the group " + groupName + ".");
        } else {
            System.out.println("User already exists in the group.");
        }
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null && groupMembers.contains(transaction.getFromUser()) && groupMembers.contains(transaction.getToUser())) {
            transactions.add(transaction);
            System.out.println("Transaction added: " + transaction.getFromUser().getUserName() + " pays " + transaction.getAmount() + " to " + transaction.getToUser().getUserName());
        } else {
            System.out.println("Invalid transaction or users not in the group.");
        }
    }

    public void addExpense(Expense expense) {
        if (expense != null && groupMembers.containsAll(expense.getUsersPaid().keySet()) && groupMembers.containsAll(expense.getUsersOwe().keySet())) {
            expenses.add(expense);
            System.out.println("Expense added: " + expense.getExpenseName() + " of amount " + expense.getAmount());
        } else {
            System.out.println("Invalid expense or users not in the group.");
        }
    }

}
