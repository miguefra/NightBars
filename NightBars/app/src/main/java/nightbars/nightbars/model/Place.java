package nightbars.nightbars.model;

public class Place {
    private int id;
    private String name;
    private String type;
    private int price;
    private String location;
    private int score;

    public Place() {}

    public Place(int id, String name, String type, int price, String location, int score) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.location = location;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
