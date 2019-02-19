package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe para mapear o comentario da receita no MongoDB
 *
 */

@Document
public class RecipeComment {
	@Id
	private String _id;
	private String comment;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public RecipeComment(String _id,String comment) {
		super();
		this._id=_id;
		this.comment = comment;
	}
	public RecipeComment() {
	}
}
