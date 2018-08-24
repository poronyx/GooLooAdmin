package sg.edu.rp.c347.goolooadmin;

public class Restaurant {
    private String name;
    private String id;
    private String parent_id;
    private String logo;

    public Restaurant(String name, String id, String parent_id, String logo) {
        this.name = name;
        this.id = id;
        this.parent_id = parent_id;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
