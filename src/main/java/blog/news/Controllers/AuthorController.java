package blog.news.Controllers;

import blog.news.Models.AuthorModel;
import blog.news.Repositories.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String HashedPassword = encoder.encode(author.getPassword());

        author.setPassword(HashedPassword);

        authorRepositoryManipulator.save(author);

        HashMap<String, Object> jsonObject = new HashMap<>();

        jsonObject.put("Success", "Author created with success ");
        return jsonObject;
    }

    @GetMapping("/author")
    public List<AuthorModel> Index () {
        return (List<AuthorModel>) authorRepositoryManipulator.findAll();
    }

    @PutMapping("/author")
    public HashMap<String, Object> Update (@RequestBody AuthorModel updatedAuthor, @RequestHeader Long id) {
        try {
            AuthorModel author =  authorRepositoryManipulator.getOne(id);

            author.setCategory(updatedAuthor.getCategory());
            author.setEmail(updatedAuthor.getEmail());
            author.setPassword(updatedAuthor.getPassword());
            author.setFirst_name(updatedAuthor.getFirst_name());
            author.setLast_name(updatedAuthor.getLast_name());
            author.setCellphone(updatedAuthor.getCellphone());
            author.setCpf(updatedAuthor.getCpf());

            authorRepositoryManipulator.save(author);

            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("Success", "Author updated with success ");
            return jsonObject;
        } catch (Error error) {
            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("Failed", "occurred some error when try to update author, please try again");

            return jsonObject;
        }
    }

    @DeleteMapping("/author")
    public HashMap<String, Object> Destroy (@RequestHeader Long id) {
        try {
            authorRepositoryManipulator.deleteById(id);

            HashMap<String, Object> jsonObject = new HashMap<>();

            jsonObject.put("Success","Author deleted with success ");
            return jsonObject;
        } catch (Error err) {
            HashMap<String, Object> jsonObject = new HashMap<>();

            jsonObject.put("Error","Cannot delete the user informed, please try again or inform another author");
            return jsonObject;
        }
    }
}
