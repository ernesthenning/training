package util;

import java.util.HashMap;
import java.util.Map;

import bean.UserAccount;
import config.SecurityConfig;

public class DataDAO {
    
    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();
    
    static {
        initUsers();
    }
    
    private static void initUsers() {
        UserAccount employee = new UserAccount ("employee1", "123", UserAccount.GENDER_MALE, SecurityConfig.ROLE_EMPLOYEE);
        UserAccount manager = new UserAccount ("manager1", "123", UserAccount.GENDER_MALE, SecurityConfig.ROLE_MANAGER);
        
        mapUsers.put(employee.getUserName(), employee);
        mapUsers.put(manager.getUserName(), manager);
    }
    
    public static UserAccount findUser (String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

}
