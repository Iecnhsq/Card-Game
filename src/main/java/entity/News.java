package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "cg_news")
public class News implements Serializable {

    @Id
    private int id;
    private String subject;
    private String author;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timein;
    private String text;

    public News() {
    }

    public News(int id, String subject, String author, Date timein, String text) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.timein = timein;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimein() {
        return timein;
    }

    public void setTimein(Date timein) {
        this.timein = timein;
    }

}
