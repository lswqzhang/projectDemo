package com.lswq.app.model;

/**
 * Created by zhangsw on 2016/12/30.
 */
public class Master implements Comparable<Master> {
    private int id;
    private String name;

    public Master(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Master master = (Master) o;

        if (id != master.id) return false;
        return name != null ? name.equals(master.name) : master.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Master o) {

        return getName().compareTo(o.getName());
    }
}
