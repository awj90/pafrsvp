package sg.edu.nus.iss.pafrsvp.model;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class RSVP implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private DateTime confirmationDate;
    private String comments;
    private String foodType;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public DateTime getConfirmationDate() {
        return confirmationDate;
    }
    public void setConfirmationDate(DateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public static RSVP create(SqlRowSet rs) {
        RSVP rsvp = new RSVP();
        rsvp.setId(rs.getInt("id"));
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        DateTime confirmationDate = new DateTime(DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(rs.getString("confirmation_date")));
        rsvp.setConfirmationDate(confirmationDate);
        rsvp.setComments(rs.getString("comments"));
        rsvp.setFoodType(rs.getString("food_type"));    
        return rsvp;
    } 

    public static RSVP create(String jsonString) throws Exception {
        JsonReader reader = Json.createReader(
            new ByteArrayInputStream(jsonString.getBytes())
        );
        return create(reader.readObject());
    }

    private static RSVP create(JsonObject jsonObject) {
        final RSVP rsvp = new RSVP();
        // id is autoincremented by mysql when a new rsvp is posted
        rsvp.setName(jsonObject.getString("name"));
        rsvp.setEmail(jsonObject.getString("email"));
        rsvp.setPhone(jsonObject.getString("phone"));
        DateTime confirmationDate = new DateTime(Instant.parse(jsonObject.getString("confirmation_date")));
        rsvp.setConfirmationDate(confirmationDate);
        rsvp.setComments(jsonObject.getString("comments"));
        rsvp.setFoodType(jsonObject.getString("food_type"));
        return rsvp;
    }
}
