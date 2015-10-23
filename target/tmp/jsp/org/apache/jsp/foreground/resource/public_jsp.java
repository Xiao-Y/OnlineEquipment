package org.apache.jsp.foreground.resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class public_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\t<!-- start: CSS -->\r\n");
      out.write("\t<link href=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/css/bootstrap.min.css\" id=\"bootstrap-style\" rel=\"stylesheet\">\r\n");
      out.write("\t<link href=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\t<link href=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/css/style.css\" id=\"base-style\" rel=\"stylesheet\">\r\n");
      out.write("\t<link href=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/css/style-responsive.css\" id=\"base-style-responsive\" rel=\"stylesheet\">\r\n");
      out.write("\t<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>\r\n");
      out.write("\t<!-- end: CSS -->\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- start: JavaScript-->\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery-1.9.1.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery-migrate-1.0.0.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery-ui-1.10.0.custom.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.ui.touch-punch.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/modernizr.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/bootstrap.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.cookie.js\"></script>\r\n");
      out.write("\t<script src='");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/fullcalendar.min.js'></script>\r\n");
      out.write("\t<script src='");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.dataTables.min.js'></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/excanvas.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.flot.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.flot.pie.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.flot.stack.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.flot.resize.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.chosen.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.uniform.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.cleditor.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.noty.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.elfinder.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.raty.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.iphone.toggle.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.uploadify-3.1.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.gritter.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.imagesloaded.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.masonry.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.knob.modified.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/jquery.sparkline.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/counter.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/retina.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath() );
      out.write("/foreground/resource/js/custom.js\"></script>\r\n");
      out.write("\t<!-- end: JavaScript-->");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
