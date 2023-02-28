package fr.todo.module;

public class Todo {

	private int Id;
	private String Titre;
	private String Description;
	private Urgence urg;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Urgence getUrg() {
		return urg;
	}
	public void setUrg(Urgence urg) {
		this.urg = urg;
	}
	public Todo(int id, String titre, String description, Urgence urg) {
		super();
		Id = id;
		Titre = titre;
		Description = description;
		this.urg = urg;
	}
	@Override
	public String toString() {
		return "Todo [Id= "+ Id + ", Titre=" + Titre + ", Description=" + Description + ", urg=" + urg + "]";
	}
	

}
