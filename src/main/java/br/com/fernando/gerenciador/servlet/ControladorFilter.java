package br.com.fernando.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fernando.gerenciador.acao.Acao;

//@WebFilter("/entrada")
public class ControladorFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("ControladorFilter");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest; // cast
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
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
