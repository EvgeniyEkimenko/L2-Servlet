package dao.impl;

import dao.SessionDAO;

import java.util.HashMap;
import java.util.Map;

public class MapSessionDAO implements SessionDAO {

    private final Map<String, String> sessionIdToLoginId = new HashMap<>();

    @Override
    public String getUserLoginBySessionId(String sessionId) {
        return sessionIdToLoginId.get(sessionId);
    }

    @Override
    public void create(String sessionId, String loginId) {
        sessionIdToLoginId.put(sessionId, loginId);
    }

    @Override
    public void delete(String sessionId) {
        sessionIdToLoginId.remove(sessionId);
    }
}
