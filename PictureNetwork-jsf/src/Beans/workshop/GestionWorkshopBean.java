package Beans.workshop;

import interfaces.WorkShopBeanLocal;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entities.Admin;
import entities.Event;
import entities.Workshop;

@ManagedBean
@ViewScoped
public class GestionWorkshopBean {
	
	private Workshop workshop = new Workshop();
	private final Date today = new Date();
	private Date date = new Date();
	@ManagedProperty("#{authenticationBean.admin}")
	private Admin admin;
	
	@EJB
	WorkShopBeanLocal localWorkshopBean;
	
	
	@PostConstruct
	public void init()
	{
		workshop = new Workshop();
		workshop.setOwner(admin);
		workshop.setWorkshopDate(new Date());
		workshop.setAddress("Tunis,Tunisia");
	}
	
	public String add()
	{
		String nav = null;
		localWorkshopBean.addWorkShop(workshop);
		
		
		return nav;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getToday() {
		return today;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
