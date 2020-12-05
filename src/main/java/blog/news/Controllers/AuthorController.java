package blog.news.Controllers;

import blog.news.Models.AuthorModel;
import blog.news.Models.CategoryModel;
import blog.news.Repositories.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepositoryManipulator;

    public AuthorController () {}

    @PostMapping("/author")
    public HashMap<String, Object> Store (@RequestBody AuthorModel author) {
        System.out.println(author.toString());
        authorRepositoryManipulator.save(author);

        HashMap<String, Object> jsonObject = new HashMap<>();

        jsonObject.put("Success", "Author created with success ");
        return jsonObject;
    }

    @GetMapping("/author")
    public List<AuthorModel> Index () {
        return (List<AuthorModel>) authorRepositoryManipulator.findAll();
    }
}
