package sametdundar.com.sametdundartodolistapp.models;

import java.io.Serializable;

public class Note implements Serializable {

    private String id;
    private String notebookId;
    private String name;
    private String description;
    private String deadLine;
    private boolean status;

    public Note (){

    }

    public Note(String id, String notebookId, String name, String description, String deadLine, boolean status) {
        this.id = id;
        this.notebookId = notebookId;
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
