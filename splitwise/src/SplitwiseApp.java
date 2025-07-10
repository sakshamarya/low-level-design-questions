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

}
