package com.mygdx.game.GameObject;

import com.mygdx.game.Hero.Hero;

public class GameObject {
    private String name;
    private Book book;
    private Boolean entrega = false;
    private int x;
    private int y;
    private int width;
    private int height;
    private final int HERO_WIDTH = 32;
    private final int HERO_HEIGHT = 32;

    public GameObject(String name, int x, int y, int width, int height, String bookName) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.book = new Book(bookName);
    }

    public GameObject(String name, int x, int y, int width, int height, Boolean bool) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.entrega = bool;
    }

    public boolean isDelivery(){
        return entrega;
    }

    public boolean containsHero(Hero hero){
        if(hero.getX() + HERO_WIDTH/2 >= this.x && hero.getX() + HERO_WIDTH/2 <= this.x + this.width){
            if(hero.getY() + HERO_HEIGHT >= this.y && hero.getY() + HERO_HEIGHT <= this.y + this.height){
                return true;
            }
        }
        return false;
    }

    public String addBook(){
        return book.getColor();
    }

    public String getName()
    {
        return name;
    }

    public void setBookshelf(String bookshelf) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
