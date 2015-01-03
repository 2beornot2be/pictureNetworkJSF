package Beans;

import interfaces.AdminBeanLocal;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Admin;




@ManagedBean
@SessionScoped
public class AuthenticationBean implements Serializable{
	
	private static final long serialVersionUID = -6916676537171647179L;
	
	@EJB	
	private AdminBeanLocal adminBeanLocal;
	
	//our model
	
	private Admin admin ;
	private List<Admin> admins;
	private boolean loggedIn;
	private String userType;

	//
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public AuthenticationBean() {
	}
	
	@PostConstruct
	public void init(){
		admin = new Admin();
		admins=adminBeanLocal.findAllAdmins();
		setLoggedIn(false);
	}
	
	public String doLogin(){
		String navigateTo = null;
		admin = adminBeanLocal.authenticate(admin.getLastName(), admin.getPassword());
		if (admin != null) {
			
			loggedIn = true;
			
			navigateTo = "/admin/index?faces-redirect=true";
			
			
			
	}
			else {
		FacesContext.getCurrentInstance()
			.addMessage("LastName_form:LastName_submit", new FacesMessage("Bad Credentials!"));
	}
		return navigateTo;
	}

	public String doLogout(){
		//String navigateTo = null;
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		//init();
		//navigateTo = "/login?faces-redirect=true";
		//return navigateTo;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		return "/login?faces-redirect=true";
		
		
	}
	
	


	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

public String doModify(){
	
	adminBeanLocal.updateAdmin(admin);
	return "/admin/index?faces-redirect=true";
}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}