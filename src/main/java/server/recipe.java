package main.java.server;

public class recipe {
    private String name;
    private String recDetails;
    private String mealType;

    public recipe(String recName, String mealType, String details) {
        this.name = recName;
        this.mealType = mealType;
        this.recDetails = details;
    }

    public String getName() {
        return this.name;
    }

    public String getDetails() {
        return this.recDetails;
    }

    public String getMealType() {
        return this.mealType;
    }

    @Override
    public String toString() {
        return this.name + " " + this.recDetails;
    }
}
