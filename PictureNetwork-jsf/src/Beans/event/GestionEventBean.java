package Beans.event;
import interfaces.EventBeanLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import entities.*;

@ManagedBean
@ViewScoped
public class GestionEventBean 
{
	private Event event = new Event();
	private final Date today = new Date();
	private Date date = new Date();
	@ManagedProperty("#{authenticationBean.admin}")
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}



	public void setAdmin(Admin admin) {
		this.admin = admin;
	}



	@EJB
	EventBeanLocal localEventBean;

	public Event getEvent() {
		return event;
	}
	
	
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@PostConstruct
	public void init()
	{
		event = new Event();
		event.setOwner(admin);
		event.setEventDate(new Date());
		event.setAddress("Tunis,Tunisia");
	}

	public String add()
	{
		String nav = null;
		localEventBean.CreateEvent(event);
		
		
		return nav;
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
	
	
	
}
