package Beans.model;

import interfaces.ModelBeanLocal;
import interfaces.PictureBeanLocal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;



import entities.Model;
import entities.ModelState_Activation;
import entities.Picture;
@ManagedBean
@ViewScoped
public class GestionModelsBean 
{
	private List<Model> models = new ArrayList<Model>();
	private List<Model> filteredModels = new ArrayList<Model>();
	private Boolean showListPictures = false;
	private List<Picture> pictures = new ArrayList<Picture>();
	private List<Picture> filteredPictures = new ArrayList<Picture>();
	private Model model = new Model();
	private List<SelectItem> modelStateActivations; 
	private Picture picture = new Picture();
	@EJB
	ModelBeanLocal localModelBean;
	@EJB
	PictureBeanLocal localPictureBean;
	
	
	@PostConstruct
	public void init()
	{
		picture = new Picture();
		modelStateActivations = new ArrayList<SelectItem>();
		modelStateActivations = new ArrayList<SelectItem>();
		modelStateActivations.add(new SelectItem("","ALL Activation States"));
		modelStateActivations.add(new SelectItem(ModelState_Activation.ACTIVATED.toString(),ModelState_Activation.ACTIVATED.toString()));
		modelStateActivations.add(new SelectItem(ModelState_Activation.DESACTIVATED.toString(),ModelState_Activation.DESACTIVATED.toString()));
		models = localModelBean.findAllModels();
		filteredModels = localModelBean.findAllModels();
		showListPictures = false;
		model = new Model();
	}
	public String initialiserListeImagesDeModel()
	{
		String nav = null;
		pictures = localPictureBean.findByUser(model.getId());
		filteredPictures = localPictureBean.findByUser(model.getId());
		return nav;
	}
	public String desactivateModel()
	{
		String nav = null;
		localModelBean.desactivateReactivateModel(model, true);
		init();
		return nav;
	}
	public String activateModel()
	{
		String nav = null;
		localModelBean.desactivateReactivateModel(model, false);
		init();
		return nav;
	}
	public String desactivatePicture()
	{
		String nav = null;
		picture.setActivation(ModelState_Activation.DESACTIVATED.toString());
		localPictureBean.updatePicture(picture);
		initialiserListeImagesDeModel();
		return nav;
	}
	public String activatePicture()
	{
		String nav = null;
		picture.setActivation(ModelState_Activation.ACTIVATED.toString());
		localPictureBean.updatePicture(picture);
		initialiserListeImagesDeModel();
		return nav;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	public List<Model> getFilteredModels() {
		return filteredModels;
	}
	public void setFilteredModels(List<Model> filteredModels) {
		this.filteredModels = filteredModels;
	}
	public Boolean getShowListPictures() {
		return showListPictures;
	}
	public void setShowListPictures(Boolean showListPictures) {
		this.showListPictures = showListPictures;
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public List<SelectItem> getModelStateActivations() {
		return modelStateActivations;
	}
	public void setModelStateActivations(List<SelectItem> modelStateActivations) {
		this.modelStateActivations = modelStateActivations;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public List<Picture> getFilteredPictures() {
		return filteredPictures;
	}
	public void setFilteredPictures(List<Picture> filteredPictures) {
		this.filteredPictures = filteredPictures;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	
}
