package Beans.admin;

import interfaces.AdminBeanLocal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Admin;


@ManagedBean
@ViewScoped
public class GestionAdminBean {

	private List<Admin> admins = new ArrayList<Admin>();
	private List<Admin> filteredAdmin = new ArrayList<Admin>();
	private Admin admin = new Admin();
	private Boolean showAdminForm = false;

	@EJB
	AdminBeanLocal localAdminBean;

	

	@PostConstruct
	public void init() {
		admins = localAdminBean.findAllAdmins();
		filteredAdmin = localAdminBean.findAllAdmins();
		admin = new Admin();
		showAdminForm = false;
		
	}
	
	public String delete() {
		String nav = null;
		localAdminBean.removeAdmin(admin);
		init();
		return nav;
	}

	public String initialiserForm() {
		String nav = null;
		admin = new Admin();
		showAdminForm = true;
		return nav;
	}

	public String doAddOrUpdate() {
		String nav = null;
		localAdminBean.updateAdmin(admin);
		init();
		return nav;
	}
	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<Admin> getFilteredAdmin() {
		return filteredAdmin;
	}

	public void setFilteredAdmin(List<Admin> filteredAdmin) {
		this.filteredAdmin = filteredAdmin;
	}

	public Boolean getShowAdminForm() {
		return showAdminForm;
	}

	public void setShowAdminForm(Boolean showAdminForm) {
		this.showAdminForm = showAdminForm;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
