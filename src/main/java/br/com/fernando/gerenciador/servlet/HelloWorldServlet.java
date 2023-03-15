package br.com.fernando.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/oi") // mapeamento: nome do servlet que vamos usar na url
public class HelloWorldServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{ // requisição e resposta
		
		PrintWriter out = resp.getWriter(); // objeto p/ devolver caracteres
		out.println("<html>");
		out.println("<body>");
		out.println("Hello World, parabens você escreveu o primeiro servlets");
		out.println("</body>");
		out.println("</html>");
		
		System.out.println("o servlet HelloWorld foi chamado");
		
	}
	
}
