package com.example.pr6;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id_Food")
    private int Id;
    @SerializedName("food_Name")
    private String Name;
    @SerializedName("food_Detail")
    private String Detail;
    @SerializedName("food_Img")
    private String Img;
    @SerializedName("student_FIO")
    private String FIO;
    @SerializedName("student_Score")
    private int Score;

    public Food(int id, String name, String detail, String img, String FIO, int score) {
        Id = id;
        Name = name;
        Detail = detail;
        Img = img;
        this.FIO = FIO;
        Score = score;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
