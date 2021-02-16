package es.udc.juanporta.psi.clean.app.module.song.viewmodel;

public class SongViewModel {

    private String id;
    private String name;

    public SongViewModel(String id, String name){

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
