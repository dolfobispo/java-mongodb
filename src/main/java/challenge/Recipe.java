package challenge;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
@Document(collection = "recipe")
public class Recipe {
	@Id
	private String _id;
	private String title;
	private String description;
	private List<String> ingredients;
	private List<String> likes;
	public Recipe(String _id, String title, String description, List<String> ingredients, List<String> likes,
			List<RecipeComment> comments) {
		super();
		this._id = _id;
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
		this.likes = likes;
		this.comments = comments;
	}
	public List<String> getLikes() {
		return likes;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	@DBRef
	private List<RecipeComment> comments;
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public List<RecipeComment> getComments() {
		return comments;
	}
	public void setComments(List<RecipeComment> comments) {
		this.comments = comments;
	}
	public Recipe() {
		
	}
	
}
