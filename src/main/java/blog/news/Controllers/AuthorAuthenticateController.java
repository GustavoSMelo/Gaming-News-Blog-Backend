package blog.news.Controllers;

import blog.news.Models.AuthorModel;
import blog.news.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestController
public class AuthorAuthenticateController {

    @Autowired
    private AuthorRepository authorRepositoryManipulator;


    public AuthorAuthenticateController() {}

    @PostMapping("/author/login")
    public Boolean Store (@RequestBody HashMap<String, String> json) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String HashedPassword = "";

        ArrayList<AuthorModel> listAuthor = (ArrayList<AuthorModel>) authorRepositoryManipulator.findAll();


        for (int i = 0; i < listAuthor.size(); i++) {
            if (listAuthor.get(i).getEmail().equals(json.get("email"))) {
                HashedPassword = listAuthor.get(i).getPassword();
            }
        }

        if (encoder.matches(json.get("password"), HashedPassword)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(json.get("email"), HashedPassword);

            Authentication auth = new ProviderManager().authenticate(token);

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(auth);

            HttpServletRequest req = null;

            HttpSession session = req.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            return true;

        } else {
            return false;
        }
    }

    @DeleteMapping("/author/login")
    @Async
    public HashMap<String, Object> Destroy () {

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication auth = context.getAuthentication();
        auth.setAuthenticated(false);

        System.out.println(context.getAuthentication());

        HashMap<String, Object> json = new HashMap<>();
        json.put("Success", "Author Logout with success ");
        return json;
    }
}
