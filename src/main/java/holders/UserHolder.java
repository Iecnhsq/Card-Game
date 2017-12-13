package holders;

import entity.User;

public class UserHolder {

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void set(User user) {
        this.user = user;
    }

}
