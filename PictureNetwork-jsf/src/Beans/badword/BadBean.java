package Beans.badword;

import interfaces.BadWordBeanLocal;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import entities.BadWorld;

@ManagedBean
@ViewScoped
public class BadBean {

	@EJB
	private BadWordBeanLocal badWordBeanLocal;

	private boolean formDisplayed = false;
	private List<BadWorld> fileteredBadword;
	private BadWorld badword = new BadWorld();
	private List<BadWorld> badwords;

	public BadBean() {

	}

	@PostConstruct
	public void init() {
		badwords = badWordBeanLocal.findAllBadWorld();

	}

	public void doNew() {

		badword = new BadWorld();
		formDisplayed = true;

	}

	public String doDelete() {

		badWordBeanLocal.removeBadWord(badword);
		init();
		return null;

	}

	public String doSaveOrUpdate() {

		badWordBeanLocal.addBadWord(badword);
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

	public List<BadWorld> getBadwords() {
		return badwords;
	}

	public void setBadwords(List<BadWorld> badwords) {
		this.badwords = badwords;
	}

	public BadWorld getBadword() {
		return badword;
	}

	public void setBadword(BadWorld badword) {
		this.badword = badword;
	}

	public List<BadWorld> getFileteredBadword() {
		return fileteredBadword;
	}

	public void setFileteredBadword(List<BadWorld> fileteredBadword) {
		this.fileteredBadword = fileteredBadword;
	}

}
