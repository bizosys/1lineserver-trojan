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
public class SystemServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(SystemServlet.class);
	
	public void init(ServletConfig config)
	{
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestType = request.getParameter("type");
		
		if(null == requestType || "".equals(requestType))
			return;
		
		if(requestType.equals("shutdown"))
			shutdown();
		else if(requestType.equals("email"))
		{
			String urlToOpen = request.getParameter("openURL");

			if(null != urlToOpen || "".equals(urlToOpen))
				openURL(urlToOpen);
			else
				openURL("http://mail.google.com");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}
	
	private static void shutdown() throws RuntimeException, IOException 
	{
		LOG.info("Servlet Called - Going for shutdown now - " +new Date(System.currentTimeMillis()).toString());
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
	
	public static void openURL(String url)
	{
		LOG.info("Servlet Called - Opening URL - " +url);
		String osName = System.getProperty("os.name");
		try
		{
			if (osName.startsWith("Windows"))
			{
				Runtime.getRuntime().exec("C://Program Files (x86)//Mozilla Firefox//firefox.exe " +url);
			}
			else
			{
				//assume Unix or Linux
				Runtime.getRuntime().exec(new String[] {"firefox", url});
			}
		}
		catch (Exception e)
		{
			LOG.fatal(e.getMessage());
		}
	}
}
