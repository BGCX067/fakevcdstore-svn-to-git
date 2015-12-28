package com.thoughtworks.seleniumtest.testhelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.util.Assert;

public class AppServer {

    private static final String DEFAULT_WEBAPP_PATH = "dist/";

    private String warPath;

    private Server jettyServer;

    private static final int PORT = 9090;

    public AppServer() {
        this(DEFAULT_WEBAPP_PATH);
    }

    public AppServer(String warPath) {
        super();
        this.warPath = warPath;
        this.jettyServer = new Server();
        init();
    }

    public void start() throws Exception {
        jettyServer.start();
    }

    private void init() {
        Assert.notNull(warPath);
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(PORT);
        jettyServer.addConnector(connector);

        WebAppContext wac = new WebAppContext();
        wac.setContextPath("/vcdstore");
        wac.setWar(this.warPath + "/vcdstore.war");
        
        
        ServletHolder holder = new ServletHolder();
        holder.setServlet(new StopTestingServerServlet(jettyServer));
        wac.addServlet(holder, "/jetty/stop");
        jettyServer.setHandler(wac);
        jettyServer.setStopAtShutdown(true);
    }

    public void stop() throws Exception {
        jettyServer.stop();
    }

    public static void main(String[] strings) throws Exception {
        AppServer server = new AppServer(DEFAULT_WEBAPP_PATH);
        server.start();
    }

    public class StopTestingServerServlet extends HttpServlet {
        private static final long serialVersionUID = 1801708603191297219L;
        private final Server stoppingServer;
        public StopTestingServerServlet(Server jettyServer) {
            stoppingServer = jettyServer;
        }
        public void service(ServletRequest request, ServletResponse response) 
                throws ServletException, IOException {
            try {
                stoppingServer.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}