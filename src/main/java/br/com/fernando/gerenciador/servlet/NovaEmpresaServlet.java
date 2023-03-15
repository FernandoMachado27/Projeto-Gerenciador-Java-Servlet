package br.com.fernando.gerenciador.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fernando.gerenciador.modelo.Banco;
import br.com.fernando.gerenciador.modelo.Empresa;

/**
 * Servlet implementation class NovaEmpresaServlet
 */

@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; // isso tira o warning da classe

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Cadastrando nova empresa");
		
		String nomeDaEmpresa = request.getParameter("nome");
		String paramDataEmpresa = request.getParameter("data");
		
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e); // leva a exceção original
		}
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeDaEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
		banco.adicionar(empresa);
		
		request.setAttribute("empresa", empresa.getNome()); // colocando nome na requisição para pegar no jsp
		
		response.sendRedirect("listaEmpresas"); // o navegador vai fazer um novo redirecionamento
		
//		// chamar o JSP
//		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas"); // despachar a requisição para outro Servlet
//		request.setAttribute("empresa", empresa.getNome()); // colocando nome na requisição para pegar no jsp
//		rd.forward(request, response); // leva a requisição e a resposta
	}

}
