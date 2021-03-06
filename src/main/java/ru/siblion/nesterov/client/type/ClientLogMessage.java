package ru.siblion.nesterov.client.type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rnaway on 05.02.2017.
 */

/* Класс, определяющий сущность лог сообщения, которое записывается в базу данных */
@Entity
@Table(name="RECORDS")
public class ClientLogMessage {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="USERNAME")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTION")
    private Action action;

    @Column(name="MESSAGE")
    private String message;

    @Column(name="DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public ClientLogMessage() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}