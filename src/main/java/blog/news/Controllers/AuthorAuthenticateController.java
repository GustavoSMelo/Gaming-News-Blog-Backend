package blog.news.Controllers;

import blog.news.Models.AuthorModel;
import blog.news.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AuthorAuthenticateController {

    @Autowired
    private AuthorRepository authorRepositoryManipulator;


    public AuthorAuthenticateController() {}

    @PostMapping("/author/login")
    public Boolean Store (@RequestBody String email, @RequestBody String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String HashedPassword = "";

        ArrayList<AuthorModel> listAuthor = (ArrayList<AuthorModel>) authorRepositoryManipulator.findAll();


        for (int i = 0; i < listAuthor.size(); i++) {
            if (listAuthor.get(i).getEmail().equals(email)) {
                HashedPassword = listAuthor.get(i).getPassword();
            }
        }

        if (encoder.matches(password, HashedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
