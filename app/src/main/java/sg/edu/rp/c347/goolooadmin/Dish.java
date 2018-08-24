package sg.edu.rp.c347.goolooadmin;

public class Dish {
    private String id;
    private String name;
    private String image;

    public Dish(String name, String id,String image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
