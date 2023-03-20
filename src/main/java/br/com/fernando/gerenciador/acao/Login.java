package br.com.fernando.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fernando.gerenciador.modelo.Banco;
import br.com.fernando.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("Logando com usuário " + login);
		
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsuario(login, senha);
		
		if (usuario != null) {
			System.out.println("Usuario existe");
			HttpSession sessao = request.getSession(); // pegar o id, identificação gerada
			sessao.setAttribute("usuarioLogado", usuario); // guardando usuario, pendurando na requisição
			return "redirect:entrada?acao=ListaEmpresas";
		}else {
			System.out.println("Usuario não existe");
			return "redirect:entrada?acao=LoginForm";
		}
		
	}

}
