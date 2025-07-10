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
                usersPaid.put(entry.getKey(), entry.getValue());
            } else if(entry.getValue() > 0){
                userOwes.put(entry.getKey(), -entry.getValue());
            }
        }

        List<Map.Entry<User, Integer>> userPaidList = usersPaid.entrySet().stream().collect(Collectors.toList());
        List<Map.Entry<User, Integer>> userOwesList = userOwes.entrySet().stream().collect(Collectors.toList());

        int i=0, j=0;

        while(i<userOwesList.size()){
            while(j<userPaidList.size() && userPaidList.get(i).getValue()>0){
                int mini = Math.min(userOwesList.get(i).getValue(), userPaidList.get(j).getValue());
                userOwesList.get(i).setValue(userOwesList.get(i).getValue() - mini);
                userPaidList.get(j).setValue(userPaidList.get(j).getValue() - mini);

                if(userPaidList.get(j).getValue() == 0){
                    System.out.println(userOwesList.get(i).getKey().getUserName() + " needs to pay " + userPaidList.get(j).getKey().getUserName() + " Rs" + mini);
                    j++;

                    if(userOwesList.get(i).getValue() == 0){
                        break;
                    }
                }
                else if(userOwesList.get(i).getValue() == 0){
                    break;
                }
            }
            i++;
        }
    }
}
