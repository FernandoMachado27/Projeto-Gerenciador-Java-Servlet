package br.com.fernando.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/entrada") // mesmo mapeamento do Servlet
public class MonitoramentoFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("MonitoramentoFilter");
		
		long antes = System.currentTimeMillis(); // medir tempo de execu��o 
		
		String acao = request.getParameter("acao");
		
		// executa a a��o
		chain.doFilter(request, response); // tem que chamar sen�o a requisi��o para
		
		long depois = System.currentTimeMillis();
		
		System.out.println("Tempo de execu��o: " + acao + " -> " + (depois-antes));
	}
	
	

}
