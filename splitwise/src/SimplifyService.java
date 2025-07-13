import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimplifyService {
    public void printSimplifiedMap(HashMap<User, Integer> expenseMap){
        HashMap<User, Integer> usersPaid = new HashMap<>();
        HashMap<User, Integer> userOwes = new HashMap<>();

        for(Map.Entry<User, Integer> entry: expenseMap.entrySet()){
            if(entry.getValue() < 0){
                usersPaid.put(entry.getKey(), Math.abs(entry.getValue()));
            } else if(entry.getValue() > 0){
                userOwes.put(entry.getKey(), entry.getValue());
            }
        }

        List<Map.Entry<User, Integer>> userPaidList = usersPaid.entrySet().stream().collect(Collectors.toList());
        List<Map.Entry<User, Integer>> userOwesList = userOwes.entrySet().stream().collect(Collectors.toList());


        int i=0, j=0;
        int flag=0;

        while(i<userPaidList.size()){
            int currSum=0;

            while(j<userOwesList.size()){
                int toPay = Math.min(userPaidList.get(i).getValue(), userOwesList.get(j).getValue());
                userPaidList.get(i).setValue(userPaidList.get(i).getValue()-toPay);
                userOwesList.get(j).setValue(userOwesList.get(j).getValue()-toPay);
                if(toPay > 0){
                    flag=1;
                    System.out.println(userOwesList.get(j).getKey().getUserName() + " needs to pay Rs: " + toPay + " to " + userPaidList.get(i).getKey().getUserName());
                }

                if(userOwesList.get(j).getValue() == 0){
                    j++;
                }
                else{
                    break;
                }
            }

            if(userPaidList.get(i).getValue() == 0){
                i++;
            }
        }

        if(flag==0){
            System.out.println("All Settled");
        }
    }
}
