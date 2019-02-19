package challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>{
	 Optional<Recipe> findById(String _id);
	 List<Recipe> findAllByIngredientsOrderByTitleAsc(String ingredient);
	 List<Recipe> findByTitleContainsOrDescriptionContainsAllIgnoreCaseOrderByTitleAsc(String arg,String arg2);
}
