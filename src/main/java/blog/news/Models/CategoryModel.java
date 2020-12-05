package blog.news.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryModel {

    // attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "category")
    private List<AuthorModel> author = new ArrayList<>();

    // constructors

    public CategoryModel() {}

    public CategoryModel(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    // setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
