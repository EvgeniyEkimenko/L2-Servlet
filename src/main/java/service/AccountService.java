package service;

import entity.UserProfile;
import dao.SessionDAO;
import dao.UserDAO;


public class AccountService {

    private UserDAO userDAO;
    private SessionDAO sessionDAO;

    //ссылка на интерфейс , выбираем релизацию через map
    public AccountService(UserDAO userDAO, SessionDAO sessionDAO) { //при переходе на jdbc изменяем реализацию
        this.userDAO=userDAO;
        this.sessionDAO = sessionDAO;
    }

    public void addNewUser(UserProfile userProfile) {
        userDAO.create(userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        String userLogin = sessionDAO.getUserLoginBySessionId(sessionId);
        if (userLogin==null) return null;
        return userDAO.getByLogin(userLogin);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionDAO.create(sessionId , userProfile.getLogin());
    }

    public void deleteSession(String sessionId) {
        sessionDAO.delete(sessionId);
    }
}
