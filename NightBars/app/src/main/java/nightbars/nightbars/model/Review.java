package nightbars.nightbars.model;

public class Review {
    private int id;
    private int placeId;
    private int userId;
    private String comment;
    private int score;

    public Review() {}

    public Review(int id, int placeId, int userId, String comment, int score) {
        this.id = id;
        this.placeId = placeId;
        this.userId = userId;
        this.comment = comment;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
