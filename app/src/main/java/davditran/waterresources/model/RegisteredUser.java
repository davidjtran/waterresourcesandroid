package davditran.waterresources.model;

import java.io.Serializable;
import java.util.HashMap;



public class RegisteredUser implements Serializable {

    private static final HashMap<String, User> registeredUserMap = new HashMap<>();

    /**
     * @return HashMap of registered users
     */
    public HashMap getRegisteredUserMap() {
        return registeredUserMap;
    }

    /**
     * checks if the username currently exists in the list of registered users
     */
    public boolean usernameExist(User user) {
        return registeredUserMap.containsKey(user.getUsername());
    }

    /**
     * adds user to the list of registered users
     */
    public void addUser(User user) {
        if (usernameExist(user)) {
            throw new IllegalArgumentException("Username already exists, please enter a different username!");
        } else {
            registeredUserMap.put(user.getUsername(), user);
        }
    }

}