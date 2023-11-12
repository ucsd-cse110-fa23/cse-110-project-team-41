package main.java;

public interface IChatGPT {
    public String generateRecipe(String prompt);
    public void saveRecipe(String fp, String recipe);
}
