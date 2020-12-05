package blog.news.Controllers;

import blog.news.Models.AuthorModel;
import blog.news.Models.CategoryModel;
import blog.news.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepositoryManipulator;

    public CategoryController () {}

    @PostMapping("/category")
    public HashMap<String, Object> Store (@RequestBody CategoryModel category) {
        try {
            categoryRepositoryManipulator.save(category);
            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("Success", "Category created with success ");

            return jsonObject;
        } catch (Error err) {
            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("Success", "Category created with success ");
            return jsonObject;
        }

    }

    @GetMapping("/category")
    public List<CategoryModel> Index () {
        return (List<CategoryModel>) categoryRepositoryManipulator.findAll();
    }
}
