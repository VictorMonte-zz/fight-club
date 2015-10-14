package br.com.fightclub.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	// daos
	private ClienteDAO clienteDAO;
	private AdministradorDAO admDAO;

	public FightClubBean() {
		this.cliente = new Cliente();
		this.admin = new Administrador();
		this.admDAO = new AdministradorDAO();
		this.clienteDAO = new ClienteDAO();

		this.listaCliente = new ArrayList<Cliente>();
	}

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

}
