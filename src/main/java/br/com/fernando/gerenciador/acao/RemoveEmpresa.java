package br.com.fernando.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fernando.gerenciador.modelo.Banco;

public class RemoveEmpresa {
	
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ação removendo empresa");
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId); // transformar em int
		
		System.out.println(id);
		
		Banco banco = new Banco();
		banco.removeEmpresa(id);
		
		response.sendRedirect("entrada?acao=ListaEmpresas"); // navegador redireciona
	}

}
