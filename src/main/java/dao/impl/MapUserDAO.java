package dao.impl;

import entity.UserProfile;
import dao.UserDAO;

import java.util.HashMap;
import java.util.Map;

public class MapUserDAO implements UserDAO {

    private final Map<String, UserProfile> loginToProfile = new HashMap<>();

    @Override
    public UserProfile getByLogin(String login) {
        return loginToProfile.get(login);
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        return userProfile;
    }

}
