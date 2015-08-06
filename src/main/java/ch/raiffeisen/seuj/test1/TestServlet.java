package ch.raiffeisen.seuj.test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="wlp_server_name")
	private String serverName;
	@Resource(lookup="wlp_install_dir")
	private String installDir;
	@Resource(lookup="wlp_user_dir")
	private String userDir;
	@Resource(lookup="server_output_dir")
	private String server_output_dir;
	
	@Inject
	private AppInfo appInfo;
	
	@Resource(lookup="jdbc/SampleDS")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter()
		.append("Served at:    ").append(request.getContextPath()).append("\n")
		.append("Server Name:  ").append(serverName).append("\n")
		.append("Install Dir:  ").append(installDir).append("\n")
		.append("Project Name: ").append(appInfo.getProjectName()).append("\n")
		.append("Project Version: ").append(appInfo.getProjectVersion()).append("\n")
		;
		testDataBaseConnection(response.getWriter());
	}

	private void testDataBaseConnection(Writer writer) throws ServletException, IOException {
		writer.append("\n\n-------------------------------------------------\n")
		.append("Database Connection")
		;
		try {
			Connection con = ds.getConnection();
			writer.append(" - Connected");
			Properties ci = con.getClientInfo();
			ci.list(new PrintWriter(writer));
			con.close();
		
			
		} catch (SQLException e) {
			writer.append(" - Could not connect!!! \n");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			writer.append(sw.toString());
		} 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
