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
	private Administrador adminNovo;

	// lists
	private List<Cliente> listaCliente;
	private List<Administrador> listaAdmin;

	// daos
	private ClienteDAO clienteDAO;
	private AdministradorDAO admDAO;

	public FightClubBean() {
		this.cliente = new Cliente();
		this.admin = new Administrador();
		this.adminNovo = new Administrador();
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
		
	
	public Administrador getAdminNovo() {
		return adminNovo;
	}

	public void setAdminNovo(Administrador adminNovo) {
		this.adminNovo = adminNovo;
	}

	// methods
	public String clienteInsert(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			cliente = clienteDAO.save(cliente);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioCliente:msgCliente", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioCliente:msgCliente", exceptionMessage);
		}
		
		if(cliente != null){
			FacesMessage successMessage = new FacesMessage("Cliente cadastrado com sucesso!");
			context.addMessage("FormularioCliente:msgCliente", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível cadastrar o cliente.");
			context.addMessage("FormularioCliente:msgCliente", errorMessage);
		}
		
		cliente = new Cliente();
		
		return null;
		
	}
	
	public String clienteUpdate(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			for (Cliente cliente : listaCliente) {
				success = clienteDAO.update(cliente);
				cliente.setEditable(false);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioClienteInterno:msgCliente", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioClienteInterno:msgCliente", exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Dados do cliente atualizados com sucesso!");
			context.addMessage("FormularioClienteInterno:msgCliente", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível modificar os dados do cliente.");
			context.addMessage("FormularioClienteInterno:msgCliente", errorMessage);
		}
		
		return null;
		
	}
	
	public String clienteDelete(Cliente cliente){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = clienteDAO.delete(cliente);
			
			listaCliente = clienteDAO.getAll();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioClienteInterno:msgCliente", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioClienteInterno:msgCliente", exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Cliente excluído com sucesso!");
			context.addMessage("FormularioClienteInterno:msgCliente", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível excluir o cliente.");
			context.addMessage("FormularioClienteInterno:msgCliente", errorMessage);
		}
		
		
		
		return null;
		
	}
	
	public String adminInsert(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			adminNovo = admDAO.save(adminNovo);
			
			listaAdmin = admDAO.getAll();
			
			adminNovo = new Administrador();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioAdminCadastro:msgAdminCad", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioAdminCadastro:msgAdminCad", exceptionMessage);
		}
		
		if(admin != null){
			FacesMessage successMessage = new FacesMessage("Administrador cadastrado com sucesso!");
			context.addMessage("FormularioAdminCadastro:msgAdminCad", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível cadastrar o administrador.");
			context.addMessage("FormularioAdminCadastro:msgAdminCad", errorMessage);
		}
		
		admin = new Administrador();
		
		return null;
		
	}
	
	public String adminUpdate(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			for (Administrador admin : listaAdmin) {
				success = admDAO.update(admin);
				admin.setEditable(false);
			}			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioAdminInterno:msgAdmin", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioAdminInterno:msgAdmin", exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Senha do administrador atualizada com sucesso!");
			context.addMessage("FormularioAdminInterno:msgAdmin", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível modificar a senha do administrador.");
			context.addMessage("FormularioAdminInterno:msgAdmin", errorMessage);
		}
		
		return null;
		
	}
	
	public String adminDelete(Administrador admin){
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean success = false;
		
		try {
			success = admDAO.delete(admin);
			
			listaAdmin = admDAO.getAll();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno: Classe não encontrada.");
			context.addMessage("FormularioAdminInterno:msgAdmin", exceptionMessage);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			FacesMessage exceptionMessage = new FacesMessage("Erro interno de SQL.");
			context.addMessage("FormularioAdminInterno:msgAdmin", exceptionMessage);
		}
		
		if(success){
			FacesMessage successMessage = new FacesMessage("Administrador excluído com sucesso!");
			context.addMessage("FormularioAdminInterno:msgAdmin", successMessage);
			
		}else{
			FacesMessage errorMessage = new FacesMessage("Não foi possível excluir o administrador.");
			context.addMessage("FormularioAdminInterno:msgAdmin", errorMessage);
		}
		
		return null;
		
	}

	public String logar()
	{
		try {
			
			admin = admDAO.isValid(admin);	
			
			if (admin != null) {
				
				listaCliente = clienteDAO.getAll();
				
				return "home";
				
			} else {
				
				admin = new Administrador();
				
				FacesMessage exceptionMessage = new FacesMessage("Usuário ou Senha Inválido.");
				FacesContext.getCurrentInstance().addMessage("FormularioLogin:msgLogin", exceptionMessage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String habilitarEdicaoCliente(Cliente cliente){
		try {
			cliente.setEditable(true);
			return "index";			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public String habilitarEdicaoAdmin(Administrador admin){
		try {
			admin.setEditable(true);
			return "administradores";			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public String logout()
	{
		admin = new Administrador();
				
		return "index";
	}

	public String listarAdministradores()
	{
		try {
			
			listaAdmin = admDAO.getAll();
			
			return "administradores";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
