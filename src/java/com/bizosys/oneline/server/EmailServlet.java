package com.bizosys.oneline.server;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class DBServlet
 */
public class EmailServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EmailServlet.class);
	
	public void init(ServletConfig config)
	{
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("Got Email GET Request");
//		response.sendRedirect("http://mail.google.com");
		openURL("http://mail.google.com");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LOG.info("Got Email POST Request");
		openURL("http://mail.google.com");
	}
	
	public static void openURL(String url)
	{
		String osName = System.getProperty("os.name");
		try
		{
			if (osName.startsWith("Windows"))
			{
//				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
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

	public static void main(String[] args)
	{
		String os = System.getProperty("os.name");
		System.out.println("name = "+os);
		
		String url = "http://mail.google.com";
		
		System.out.println("url="+url);
		openURL(url);
	}

}
