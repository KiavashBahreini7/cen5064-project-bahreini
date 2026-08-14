package com.example.habittracker.domain;
   import java.util.UUID;
   public final class Habit {
       private final UUID id;
       private final String name;
       private final String reminderTime;
       private final String colorTag;
       private final String notes;
       private Habit(Builder b) {
           this.id = b.id;
           this.name = b.name;
           this.reminderTime = b.reminderTime;
           this.colorTag = b.colorTag;
           this.notes = b.notes;
       }
       public UUID id() { return id; }
       public String name() { return name; }
       public String reminderTime() { return reminderTime; }
       public String colorTag() { return colorTag; }
       public String notes() { return notes; }
       public static class Builder {
           private final UUID id = UUID.randomUUID();
           private final String name;
           private String reminderTime = "";
           private String colorTag = "";
           private String notes = "";
           public Builder(String name) {
               this.name = name;
           }
           public Builder reminderTime(String t) { this.reminderTime = t; return this; }
           public Builder colorTag(String c) { this.colorTag = c; return this; }
           public Builder notes(String n) { this.notes = n; return this; }
           public Habit build() { return new Habit(this); }
       }
   }
