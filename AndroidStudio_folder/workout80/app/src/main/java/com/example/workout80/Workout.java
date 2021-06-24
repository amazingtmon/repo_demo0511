package com.example.workout80;

public class Workout {
    private String name = null;
    private String description = null;
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public Workout(String name, String description){
        this.name = name;
        this.description = description;
    }

    public static Workout[] workouts = {
            new Workout("달리기", "전력질주")
            ,new Workout("수영", "50미터")
            ,new Workout("등산", "동네등산")
    };
}
