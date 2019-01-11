package com.course.service.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Course {
    private int id;
    private String name;
    private String desc;
    private int duration;

    public Course() {
    }

    public Course(int id, String name, String desc, int duration) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.duration = duration;
    }

    public Course(String name, String desc, int duration) {
        this.name = name;
        this.desc = desc;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", duration=" + duration +
                '}';
    }
}
