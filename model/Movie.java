package model;

/**
 * This class models a simple Movie object with minimal data: title and rating.
 * The movie object are displayed in the GUI.
 * 
 * @author Rick Mercer
 */
public class Movie {

  // Instance Variables
  private String title;
  private int rating;

  // Construct a simpe Movie object
  public Movie(String title, int rating) {
    this.title = title;
    this.rating = rating;
  }

  @Override
  public String toString() {
    String result = title + " ";
    for (int j = 1; j <= rating; j++)
      result += "*";

    return result;
  }

  public String getTitle() {
    return title;
  }

}
