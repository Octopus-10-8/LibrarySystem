package zy.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Date: 2018/12/20 0020    id	name	count	type	author	discount	hasLended
 **/
public class Books  implements Serializable {

    private int id;
    private String name;
    private int count;
    private String type;
    private String author;
    private int discount;
    private int hasLended;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books)) return false;
        Books books = (Books) o;
        return Objects.equals(getName(), books.getName()) &&
                Objects.equals(getAuthor(), books.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor());
    }

    public Books(String name, int count, String type, String author) {
        this.name = name;
        this.count = count;
        this.type = type;
        this.author = author;

    }

    public Books(int id, String name, int count, String type, String author, int discount, int hasLended) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.type = type;
        this.author = author;
        this.discount = discount;
        this.hasLended = hasLended;
    }


    @Override
    public String toString() {
        return "图书{" +
                "书表ID=" + id +
                ", 书本名称='" + name + '\'' +
                ", 剩余数量=" + count +
                ", 类型='" + type + '\'' +
                ", 作者='" + author + '\'' +
                ", 被借出次数=" + discount +
                ", 已借出数量=" + hasLended +
                '}';
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getHasLended() {
        return hasLended;
    }

    public void setHasLended(int hasLended) {
        this.hasLended = hasLended;
    }
}
