// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class hello extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Hello Web!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Enumeration paramNames = request.getParameterNames();

        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>" +
                    "table, th, td {" +
                    "border: 1px solid black;" +
                    "border-collapse: collapse;" +
                    "}" +
                    "th,td {" +
                    "padding: 5px;" +
                    "}" +
                    "</style>");
        out.println("<title>" + message + "</title>");
        out.println("</head>");
        out.println("<body>");
        if (paramNames.hasMoreElements()) {
            out.println(getParamsTable(request, paramNames));
        } else {
            out.println(message);
        }        
        out.println("</body>");
        out.println("</html>");
    }

    @SuppressWarnings("unchecked")
    private String getParamsTable(HttpServletRequest request, Enumeration paramNames) {
        StringBuilder sb = new StringBuilder();
        SortedSet<String> set = new TreeSet<String>(Collections.list(paramNames));
        Iterator it = set.iterator();

        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<th>Name</th>");
        sb.append("<th>Value</th>");
        sb.append("</tr>");
        while (it.hasNext()) {
            String paramName = (String) it.next();
            String[] paramValues = request.getParameterValues(paramName);

            sb.append("<tr>");
            sb.append("<td>");
            sb.append(paramName);
            sb.append("</td>");
            sb.append("<td>");
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];

                if (paramValue.length() == 0) {
                    sb.append("N/A");
                } else {
                    sb.append(paramValue);
                }
            } else {
                sb.append("<ul>");

                for (int i = 0; i < paramValues.length; i++) {
                    sb.append("<li>" + paramValues[i] + "</li>");
                }

                sb.append("</ul>");
            }
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("<table>");

        return sb.toString();
    }
}

