package blog.news.Repositories;

import blog.news.Models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> { }
