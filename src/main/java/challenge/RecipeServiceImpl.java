package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private RecipeCommentRepository recipeCommentRepository;
	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Optional<Recipe> rcp = recipeRepository.findById(id);
		System.out.println(rcp.get().getComments());
		if(rcp.isPresent()) {
			rcp.get().setTitle(recipe.getTitle());
			rcp.get().setDescription(recipe.getDescription());
			rcp.get().setIngredients(recipe.getIngredients());
			recipeRepository.save(rcp.get());
			
		}
		
	}

	@Override
	public void delete(String id) {
		recipeRepository.deleteById(id);

	}

	@Override
	public Recipe get(String id) {
		Optional<Recipe> recipe =recipeRepository.findById(id);
		if(recipe.isPresent())return recipe.get();
		return null;
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		List<Recipe> recipes = recipeRepository.findAllByIngredientsOrderByTitleAsc(ingredient);
		if(recipes.size()>0) {
			return recipes;
		}
		return null;
	}

	@Override
	public List<Recipe> search(String search) {
		List<Recipe> recipes = recipeRepository.findByTitleContainsOrDescriptionContainsAllIgnoreCaseOrderByTitleAsc(search, search);
		if(recipes.size()>0) {
			return recipes;
		}
		return null;
	}


	@Override
	public void like(String id, String userId) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			if(recipe.get().getLikes()!=null) {
				recipe.get().getLikes().add(userId);
			}else {
				List<String> likes = new ArrayList<>();
				likes.add(userId);
				recipe.get().setLikes(likes);
			}
			recipeRepository.save(recipe.get());
		}
	}

	@Override
	public void unlike(String id, String userId) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			if(recipe.get().getLikes()!=null) {
				recipe.get().getLikes().remove(userId);
			}
			recipeRepository.save(recipe.get());
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		Optional<Recipe>  recipe = recipeRepository.findById(id);
		RecipeComment recipeComment = recipeCommentRepository.save(comment);
		if(recipe.isPresent()) {
			if(recipe.get().getComments()!=null) {
				recipe.get().getComments().add(recipeComment);
			}else {
				List<RecipeComment> rcpComment = new ArrayList<>();
				rcpComment.add(recipeComment);
				recipe.get().setComments(rcpComment);
			}
			recipeRepository.save(recipe.get());
		}
		return recipeComment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Optional<Recipe>  recipe = recipeRepository.findById(id);
		Optional<RecipeComment> recipeComment = recipeCommentRepository.findById(commentId);
		if(recipe.isPresent() && recipeComment.isPresent()) {
			recipeComment.get().setComment(comment.getComment());
			recipeCommentRepository.save(recipeComment.get());
		}
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Optional<Recipe>  recipe = recipeRepository.findById(id);
		Optional<RecipeComment> recipeComment = recipeCommentRepository.findById(commentId);
		if(recipe.isPresent() && recipeComment.isPresent()) {
			recipeCommentRepository.deleteById(commentId);
		}
	}

}
