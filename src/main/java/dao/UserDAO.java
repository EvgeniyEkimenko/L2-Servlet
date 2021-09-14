package dao;

import entity.UserProfile;

public interface UserDAO {
    UserProfile getByLogin(String login);

    UserProfile create(UserProfile userProfile);

}
