package main;

import service.AccountService;
import entity.UserProfile;
import dao.SessionDAO;
import dao.UserDAO;
import dao.impl.MapSessionDAO;
import dao.impl.MapUserDAO;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;


public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService(getUserDao(), getSessionDao());

        accountService.addNewUser(new UserProfile("admin"));
        //accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html"); //путь к статическому контенту

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }

    public static UserDAO getUserDao() {
        return new MapUserDAO();
    }

    public static SessionDAO getSessionDao() {
        return new MapSessionDAO();
    }

    //вернуть другие реализации
}
