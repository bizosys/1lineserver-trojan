package com.bizosys.oneline.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class DBServlet
 */
public class ShutdownServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(ShutdownServlet.class);
	
	public void init(ServletConfig config)
	{
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShutdownServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.info("Servlet Called - Going for shutdown now - " +new Date(System.currentTimeMillis()).toString());
		shutdown();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LOG.info("Servlet Called - Going for shutdown now - " +new Date(System.currentTimeMillis()).toString());
		shutdown();
	}
	
	//TODO: Try  Apache Commons Lang's SystemUtils
	private static void shutdown() throws RuntimeException, IOException 
	{
		if(System.getProperty("os.name").startsWith("Win"))
		{
		    Runtime.getRuntime().exec("shutdown -s -t 01");
		}
		else
		{
		    Runtime.getRuntime().exec("sudo shutdown -h now");
		}
	    System.exit(0);
	}
	
//	public static void main(String[] args) throws Exception {
//		ShutdownServlet.shutdown();
//	}
}
