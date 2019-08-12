package sametdundar.com.sametdundartodolistapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Notebook implements Serializable {

    private String id;
    private String noteBookName;
    private String userId;

    public Notebook() {
    }

    public Notebook(String id, String noteBookName, String userId) {
        this.id = id;
        this.noteBookName = noteBookName;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteBookName() {
        return noteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        this.noteBookName = noteBookName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
