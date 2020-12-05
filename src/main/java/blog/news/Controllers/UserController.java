package blog.news.Controllers;

import blog.news.Models.UserModel;
import blog.news.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepositoryManipulator;

    // Constructor

    public UserController () {}

    @PostMapping("/user")
    public HashMap<String, Object> Store (@RequestBody UserModel user) {
        userRepositoryManipulator.save(user);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("Success", "User created with success ");
        return jsonObject;
    }

    @GetMapping("/user")
    public List<UserModel> Index () {
        return (List<UserModel>) userRepositoryManipulator.findAll();
    }
}
