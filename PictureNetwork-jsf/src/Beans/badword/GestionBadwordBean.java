package Beans.badword;

import interfaces.BadWordBeanLocal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.BadWorld;

@ManagedBean
@ViewScoped
public class GestionBadwordBean {

	private List<BadWorld> badwords = new ArrayList<BadWorld>();
	private List<BadWorld> filteredBadWorld = new ArrayList<BadWorld>();
	private BadWorld badWorld = new BadWorld();
	private Boolean showBadWorldForm = false;

	@EJB
	BadWordBeanLocal localBadwordBean;

	@PostConstruct
	public void init() {
		badwords = localBadwordBean.findAllBadWorld();
		filteredBadWorld = localBadwordBean.findAllBadWorld();
		badWorld = new BadWorld();
		showBadWorldForm = false;
	}

	public String delete() {
		String nav = null;
		localBadwordBean.removeBadWord(badWorld);
		init();
		return nav;
	}

	public String initialiserForm() {
		String nav = null;
		badWorld = new BadWorld();
		showBadWorldForm = true;
		return nav;
	}

	public String doAddOrUpdate() {
		String nav = null;
		localBadwordBean.updateBadWord(badWorld);
		init();
		return nav;
	}
	
	public String add()
	{
		String nav = null;
		localBadwordBean.addBadWord(badWorld);
		
		
		return nav;
	}

	public List<BadWorld> getBadwords() {
		return badwords;
	}

	public void setBadwords(List<BadWorld> badwords) {
		this.badwords = badwords;
	}

	public List<BadWorld> getFilteredBadWorld() {
		return filteredBadWorld;
	}

	public void setFilteredBadWorld(List<BadWorld> filteredBadWorld) {
		this.filteredBadWorld = filteredBadWorld;
	}

	public BadWorld getBadWorld() {
		return badWorld;
	}

	public void setBadWorld(BadWorld badWorld) {
		this.badWorld = badWorld;
	}

	public Boolean getShowBadWorldForm() {
		return showBadWorldForm;
	}

	public void setShowBadWorldForm(Boolean showBadWorldForm) {
		this.showBadWorldForm = showBadWorldForm;
	}

}
