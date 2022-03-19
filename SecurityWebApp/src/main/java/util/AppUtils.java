package util;

import java.util.HashMap;
import java.util.Map;

import bean.UserAccount;
import jakarta.servlet.http.HttpSession;

public class AppUtils {
    
    private static int REDIRECT_ID = 0;
    
    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
    
    public static void storeLoginedUser (HttpSession session, UserAccount loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }
    
    public static UserAccount getLoginedUser (HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
    
    public static Integer storeRedirectAfterLoginUrl (HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);
        
        if (id == null) {
            id = REDIRECT_ID++;
            
            id_uri_map.put(id, requestUri);
            uri_id_map.put(requestUri, id);
            return id;
        }
        return id;
    }
    
    public static String getRedirectAfterLoginUrl (HttpSession session, int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

}
