package blog.news.Models;

import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class AuthorModel {

    // attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_email", nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cellphone", nullable = false)
    private String cellphone;

    @ManyToOne
    @JoinColumn(name = "fk_category", nullable = false)
    private CategoryModel category;


    // constructors

    public AuthorModel () {}

    public AuthorModel (String email, String cpf, String first_name, String last_name, String password, String cellphone) {
        this.email = email;
        this.cpf = cpf;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.cellphone = cellphone;
    }

    //getters

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    // setters


    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    // toString


    @Override
    public String toString() {
        return "AuthorModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", category=" + category +
                '}';
    }
}
