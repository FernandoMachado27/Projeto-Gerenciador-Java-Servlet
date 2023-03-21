package br.com.fernando.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fernando.gerenciador.acao.Acao;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet { // controlador
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm")); // protegemos todas menos login, se não for igual login é uma ação protegida
		
		if (ehUmaAcaoProtegida && usuarioNaoEstaLogado) { // se o usuario é nulo, vá para formLogin
			response.sendRedirect("entrada?acao=LoginForm");
			return; // sai da execução
		}
		
		String nomeDaClasse = "br.com.fernando.gerenciador.acao." + paramAcao;
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse); // carrega a classe com o nome da String, carrega a classe e deixa em memoria
			Acao acao = (Acao)classe.newInstance(); // faz um cast e cria a instancia
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEEndereco = nome.split(":"); // separa a String baseada no caractere
		
		if(tipoEEndereco[0].equals("forward")) { // se na primeira posição tem forward
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]); // então usa o valor
			rd.forward(request, response);
		}else { 
			response.sendRedirect(tipoEEndereco[1]); // navegador redireciona
		}
		
	}

}
