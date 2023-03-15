package br.com.fernando.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco { // classe para simular banco de dados
	
	private static List<Empresa> lista = new ArrayList<>();  // lista de empresas
	private static Integer chaveSequencial = 1;
	
	static { // executado quando a classe é carregada
		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++); // aumenta 1
		empresa.setNome("Alura");
		Empresa empresa2 = new Empresa();
		empresa2.setId(chaveSequencial++);
		empresa2.setNome("Caelum");
		lista.add(empresa);
		lista.add(empresa2);
	}

	public void adicionar(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		lista.add(empresa);
	} 
	
	public List<Empresa> getEmpresas(){
		return Banco.lista;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = lista.iterator();
		
		while(it.hasNext()) { // enquanto existe um proximo elemento, continue 
			Empresa emp = it.next(); // proxomo
			
			if (emp.getId() == id) {
				it.remove();
			}
		}
	}

	public Empresa buscaEmpresaPeloId(Integer id) {
		for (Empresa empresa : lista) {
			if (empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

}
