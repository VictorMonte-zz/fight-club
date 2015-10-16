package br.com.fightclub.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fightclub.dao.AdministradorDAO;
import br.com.fightclub.dao.ClienteDAO;
import br.com.fightclub.model.Administrador;
import br.com.fightclub.model.Cliente;

@ManagedBean
@SessionScoped
public class FightClubBean {
	
	// objects
	private Cliente cliente;
	private Administrador admin;

	// lists
	private List<Cliente> listaCliente;
	private List<Administrador> listaAdmin;

	// daos
	private ClienteDAO clienteDAO;
	private AdministradorDAO admDAO;

	public FightClubBean() {
		this.cliente = new Cliente();
		this.admin = new Administrador();
		this.admDAO = new AdministradorDAO();
		this.clienteDAO = new ClienteDAO();

		this.listaCliente = new ArrayList<Cliente>();
		this.listaAdmin = new ArrayList<Administrador>();
		
	}
	
	
	// getters & setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public AdministradorDAO getAdmDAO() {
		return admDAO;
	}

	public void setAdmDAO(AdministradorDAO admDAO) {
		this.admDAO = admDAO;
	}
	
	public List<Administrador> getListaAdmin() {
		return listaAdmin;
	}

	public void setListaAdmin(List<Administrador> listaAdmin) {
		this.listaAdmin = listaAdmin;
	}
	
	// methods
	public String clienteInsert(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			cliente = clienteDAO.save(cliente);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(cliente != null){
			FacesMessage successMessage = new FacesMessage("Cliente cadastrado com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível cadastrar o cliente.");
			context.addMessage(null, errorMessage);
		}
		
		cliente = new Cliente();
		
		return null;
		
	}
	
	public String clienteUpdate(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = clienteDAO.update(cliente);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Dados do cliente atualizados com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível modificar os dados do cliente.");
			context.addMessage(null, errorMessage);
		}
		
		return null;
		
	}
	
	public String clienteDelete(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = clienteDAO.delete(cliente);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Cliente excluído com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível excluir o cliente.");
			context.addMessage(null, errorMessage);
		}
		
		return null;
		
	}
	
	public String adminInsert(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			admin = admDAO.save(admin);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(admin != null){
			FacesMessage successMessage = new FacesMessage("Administrador cadastrado com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível cadastrar o administrador.");
			context.addMessage(null, errorMessage);
		}
		
		admin = new Administrador();
		
		return null;
		
	}
	
	public String adminUpdate(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = admDAO.update(admin);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Senha do administrador atualizada com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível modificar a senha do administrador.");
			context.addMessage(null, errorMessage);
		}
		
		return null;
		
	}
	
	public String adminDelete(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = admDAO.delete(admin);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage(null, exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage(null, exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Administrador excluído com sucesso!");
			context.addMessage(null, successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível excluir o administrador.");
			context.addMessage(null, errorMessage);
		}
		
		return null;
		
	}
}
