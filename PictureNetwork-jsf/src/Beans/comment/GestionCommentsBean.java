package Beans.comment;

import interfaces.CommentBeanLocal;
import interfaces.PictureBeanLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import entities.Admin;
import entities.Comment;
import entities.Credit;
import entities.Picture;
@ManagedBean
@ViewScoped
public class GestionCommentsBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	PictureBeanLocal localPictureBean;
	@EJB
	CommentBeanLocal localCommentBean;
	@ManagedProperty("#{authenticationBean.admin}")
	private Admin admin;
	private List<SelectItem> credits;

	
	private List<Picture> pictures = new ArrayList<Picture>();
	private List<Picture> filteredPictures = new ArrayList<Picture>();
	private Picture picture = new Picture();
	private Boolean showListComments = false;
	private List<Comment> comments = new ArrayList<Comment>();
	private List<Comment> filteredComments = new ArrayList<Comment>();
	private Comment commentaire = new Comment();
	private Boolean showCommentForm = false;
	
	public Comment getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Comment commentaire) {
		this.commentaire = commentaire;
	}

	public Boolean getShowCommentForm() {
		return showCommentForm;
	}

	public void setShowCommentForm(Boolean showCommentForm) {
		this.showCommentForm = showCommentForm;
	}

	public List<Comment> getFilteredComments() {
		return filteredComments;
	}

	public void setFilteredComments(List<Comment> filteredComments) {
		this.filteredComments = filteredComments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Boolean getShowListComments() {
		return showListComments;
	}

	public void setShowListComments(Boolean showListComments) {
		this.showListComments = showListComments;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
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

	
	@PostConstruct
	public void init()
	{
		
		credits = new ArrayList<SelectItem>();
		credits.add(new SelectItem("","ALL Credits"));
		credits.add(new SelectItem(Credit.PICTURE_NETWORK_TEAM.toString(),Credit.PICTURE_NETWORK_TEAM.toString()));
		
		credits.add(new SelectItem(Credit.FREE_USER.toString(),Credit.FREE_USER.toString()));
		credits.add(new SelectItem(Credit.WORLD_USER.toString(),Credit.WORLD_USER.toString()));
		System.out.println("(in INIT() ) => logged in admin name: " + admin.getFirstName() + " " + admin.getLastName());
		pictures = localPictureBean.findAllPictures();
		filteredPictures = localPictureBean.findAllPictures();
		comments = new ArrayList<Comment>();
		filteredComments = new ArrayList<Comment>();
		admin.setCredit(Credit.PICTURE_NETWORK_TEAM.toString());
	}
	
	public String initialiserListeCommentairesDeImage()
	{
		String nav = null;
		comments = localCommentBean.findAllComment(picture.getId());
		filteredComments = localCommentBean.findAllComment(picture.getId());
		return nav;
	}
	public String initialiseAddForm()
	{
		System.out.println("logged in admin name: " + admin.getFirstName() + " " + admin.getLastName());
		System.out.println("picture name: " + picture.getName());
		
		String nav = null;
		commentaire = new Comment();
		commentaire.setSender(admin);
		commentaire.setContent("");
		commentaire.setPicture(picture);
		return nav;
	}
	public String doAddOrUpdate()
	{
		String nav = null;
		commentaire.setSender(admin);
		commentaire.setPicture(picture);
		commentaire.setCredit(admin.getCredit());
		localCommentBean.addComment(commentaire);
		initialiserListeCommentairesDeImage();
		commentaire = new Comment();
		showCommentForm = false;
		return nav;
	}
	public String delete()
	{
		String nav = null;
		
		localCommentBean.removeComment(commentaire.getId());
		initialiserListeCommentairesDeImage();
		commentaire = new Comment();
		showCommentForm = false;
		System.out.println("finished delete in bean!");
		return nav;
	}
	public List<SelectItem> getCredits() {
		return credits;
	}

	public void setCredits(List<SelectItem> credits) {
		this.credits = credits;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
