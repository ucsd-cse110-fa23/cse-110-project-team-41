package main.java;

public class recipe {
    private String name;
    private String recDetails;

    public recipe(String recName, String details) {
        this.name = recName;
        this.recDetails = details;
    }

    public String getName() {
        return this.name;
    }

    public String getDetails() {
        return this.recDetails;
    }

    @Override
    public String toString() {
        return this.name + " " + this.recDetails;
    }
}
