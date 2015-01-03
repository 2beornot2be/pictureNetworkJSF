package Beans.event;

import interfaces.EventBeanLocal;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import entities.Event;

@ManagedBean
@ViewScoped
public class EventBean {

	@EJB
	private EventBeanLocal eventBeanLocal;

	private boolean formDisplayed = false;
	private List<Event> fileteredEvent;
	private Event event = new Event();
	private List<Event> events;

	public EventBean() {

	}

	@PostConstruct
	public void init() {
		events = eventBeanLocal.findAllEvents();

	}

	public void doNew() {

		event = new Event();
		formDisplayed = true;

	}

	public String doDelete() {

		eventBeanLocal.removeEvent(event);
		init();
		return null;

	}

	public String doSaveOrUpdate() {

		eventBeanLocal.CreateEvent(event);
		init();
		return null;

	}

	public void onRowSelect(SelectEvent event) {

		formDisplayed = true;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Event> getEvents() {

		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getFileteredEvent() {
		return fileteredEvent;
	}

	public void setFileteredEvent(List<Event> fileteredEvent) {
		this.fileteredEvent = fileteredEvent;
	}

}
