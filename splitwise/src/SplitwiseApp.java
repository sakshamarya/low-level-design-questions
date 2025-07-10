import java.util.List;

public class SplitwiseApp {

    public static SplitwiseApp splitwiseApp = null;
    private SplitwiseApp() {
        // Private constructor to prevent instantiation
    }

    public static SplitwiseApp getInstance() {
        if (splitwiseApp == null) {
            synchronized (SplitwiseApp.class) {
                if (splitwiseApp == null) {
                    splitwiseApp = new SplitwiseApp();
                }
            }
        }
        return splitwiseApp;
    }

    public User createUser(String userName, String email, String phoneNumber){
        User newUser = new User(userName, email, phoneNumber);
        System.out.println("User created: " + newUser.getUserName());
        return newUser;
    }

    public Group createGroup(String groupName, List<User> groupMembers){
        Group newGroup = new Group(groupName, groupMembers);
        System.out.println("Group created: " + groupName);
        return newGroup;
    }

    public Transaction createTransaction(User userFrom, User userTo, int amount){
        Transaction newTransaction = new Transaction(userFrom, userTo, amount);
        System.out.println("Transaction created with id: " + newTransaction.getTransactionId());
        return newTransaction;
    }

}
