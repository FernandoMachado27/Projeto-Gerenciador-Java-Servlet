package br.com.fernando.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fernando.gerenciador.modelo.Banco;
import br.com.fernando.gerenciador.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> empresas = new Banco().getEmpresas();
		
		Gson gson = new Gson();
		String json = gson.toJson(empresas);  // transforma lista empresas em Json
		
		response.setContentType("application/json"); // formato para devolver json
		response.getWriter().print(json); // getWriter para devolver strings
		
	}

}
