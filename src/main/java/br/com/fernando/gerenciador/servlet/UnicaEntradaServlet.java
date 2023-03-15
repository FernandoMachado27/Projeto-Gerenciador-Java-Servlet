package br.com.fernando.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fernando.gerenciador.acao.AlteraEmpresa;
import br.com.fernando.gerenciador.acao.ListaEmpresas;
import br.com.fernando.gerenciador.acao.MostraEmpresa;
import br.com.fernando.gerenciador.acao.NovaEmpresa;
import br.com.fernando.gerenciador.acao.RemoveEmpresa;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet { // controlador
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("acao");
		String nome = null;
		
		if (paramAcao.equals("ListaEmpresas")) {
			ListaEmpresas acao = new ListaEmpresas();
			nome = acao.executa(request, response);
			
		}else if (paramAcao.equals("RemoveEmpresa")) {
			RemoveEmpresa acao = new RemoveEmpresa();
			nome = acao.executa(request, response);
			
		}else if (paramAcao.equals("MostraEmpresa")) {
			MostraEmpresa acao = new MostraEmpresa();
			nome = acao.executa(request, response);
			
		}else if (paramAcao.equals("AlteraEmpresa")) {
			AlteraEmpresa acao = new AlteraEmpresa();
			nome = acao.executa(request, response);
			
		}else if (paramAcao.equals("NovaEmpresa")) {
			NovaEmpresa acao = new NovaEmpresa();
			nome = acao.executa(request, response);
			
		}
		
		String[] tipoEEndereco = nome.split(":"); // separa a String baseada no caractere
		
		if(tipoEEndereco[0].equals("forward")) { // se na primeira posição tem forward
			RequestDispatcher rd = request.getRequestDispatcher(tipoEEndereco[1]); // então usa o valor
			rd.forward(request, response);
		}else { 
			response.sendRedirect(tipoEEndereco[1]); // navegador redireciona
		}
	}

}
