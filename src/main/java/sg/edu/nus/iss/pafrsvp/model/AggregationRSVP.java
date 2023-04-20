package sg.edu.nus.iss.pafrsvp.model;

import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class AggregationRSVP {
    private String _id;
    private List<String> names;
    private Integer count;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public List<String> getNames() {
        return names;
    }
    public void setNames(List<String> names) {
        this.names = names;
    }
    
    public static AggregationRSVP create(Document d) {
        AggregationRSVP a = new AggregationRSVP();
        a.set_id(d.getString("_id"));
        a.setNames(d.getList("names", String.class));
        a.setCount(d.getInteger("count"));
        return a;
    }

    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("_id", get_id());
        builder.add("names", getNames().toString());
        builder.add("count", getCount());
        return builder.build();
    }
}
