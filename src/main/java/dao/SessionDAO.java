package dao;


public interface SessionDAO {

    String getUserLoginBySessionId(String sessionId);

    void create(String sessionId, String loginId);

    void delete(String sessionId);
}
