package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmail;
    private String subject;
    private String content;
    private String recipient;
     @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime sentDate;

    public Email() {
    }

    public Email(Long idEmail, String subject, String content, String recipient, LocalDateTime sentDate) {
        this.idEmail = idEmail;
        this.subject = subject;
        this.content = content;
        this.recipient = recipient;
        this.sentDate = sentDate;
    }

    public Long getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Long idEmail) {
        this.idEmail = idEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public String toString() {
        return "Email{" +
                "idEmail=" + idEmail +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", recipient='" + recipient + '\'' +
                ", sentDate=" + sentDate +
                '}';
    }
}
