package com.ashishchidhava.firebase_third;

public class Post {
    String Title;
    String Description;
    String Author;

//    alt+insert
//    primary constrructer create krna must hai
    public Post() {
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getAuthor() {
        return Author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
