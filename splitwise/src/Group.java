import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Group(String groupName, List<User> groupMembers, List<Expense> expenses, List<Transaction> transactions) {
        this.groupId = new AtomicLong(groupId.incrementAndGet());
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.expenses = expenses;
        this.transactions = transactions;
    }

    private void printPayments(){
        /*
        * Build a map having A -> (B, 10), (c, 20) :: This denotes A needs to give Rs 10 to B and Rs 20 to C
        * */

        HashMap<User, HashMap<User, Integer>> paymentMap = new HashMap<>();
        for(Expense expense: expenses){
            int i=0, j=0;

            List<Map.Entry<User, Integer>> usersOweList = expense.getUsersOwe().entrySet().stream().collect(Collectors.toList());
            List<Map.Entry<User, Integer>> usersPaidList = expense.getUsersPaid().entrySet().stream().collect(Collectors.toList());

            while(i<usersPaidList.size()){
                while(j<usersOweList.size() && usersPaidList.get(i).getValue() > 0){
                    int mini = Math.min(usersPaidList.get(i).getValue(), usersOweList.get(j).getValue());

                    if(paymentMap.containsKey(usersOweList.get(j).getKey())){
                        paymentMap.get(usersOweList.get(j).getKey()).put(usersPaidList.get(i).getKey(), paymentMap.get(usersOweList.get(j).getKey()).getOrDefault(usersPaidList.get(i).getKey(), 0) + mini);
                    }
                    else{
                        paymentMap.put(usersOweList.get(j).getKey(), new HashMap<>());
                        paymentMap.get(usersOweList.get(j).getKey()).put(usersPaidList.get(i).getKey(), mini);
                    }

                    usersPaidList.get(i).setValue(usersPaidList.get(i).getValue() - mini);
                    usersOweList.get(j).setValue(usersOweList.get(j).getValue() - mini);

                    if(usersOweList.get(j).getValue() == 0){
                        j++;
                    }
                }
                i++;
            }
        }

        for(Transaction transaction: transactions){
            User fromUser = transaction.getFromUser();
            User toUser = transaction.getToUser();
            int amount = transaction.getAmount();

            if(paymentMap.containsKey(fromUser) && paymentMap.get(fromUser).containsKey(toUser)) {
                paymentMap.get(fromUser).put(toUser, paymentMap.get(fromUser).get(toUser) - amount);
            } else {
                if(!paymentMap.containsKey(toUser)){
                    paymentMap.put(toUser, new HashMap<>());
                }

                paymentMap.get(toUser).put(fromUser, amount+paymentMap.get(toUser).getOrDefault(fromUser, 0));
            }
        }

        for(Map.Entry<User, HashMap<User, Integer>> entry: paymentMap.entrySet()){
            User payer = entry.getKey();
            HashMap<User, Integer> receiverMap = entry.getValue();
            for(Map.Entry<User, Integer> receiverEntry: receiverMap.entrySet()){
                User receiver = receiverEntry.getKey();
                int amount = receiverEntry.getValue();
                if(amount > 0) {
                    System.out.println(payer.getUserName() + " needs to pay " + amount + " to " + receiver.getUserName());
                }
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

    private void printSimplifiedTotalExpenseMap() {
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
