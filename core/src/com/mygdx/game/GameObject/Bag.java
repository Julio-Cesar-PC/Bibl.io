package com.mygdx.game.GameObject;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Book> bag;

    public Bag() {
        bag = new ArrayList<Book>();
    }

    public void addBook(Book book){
        if(bag.size() < 5) {
            bag.add(book);
        }
    }

    public ArrayList<Book> getBag() {
        return bag;
    }

    public void removeBook(){
        bag.remove(bag.size()-1);
    }

    public int amountOfBooks(){
        return bag.size();
    }

    public void listBooks(){
        for(Book book: bag){
            System.out.println(book.getColor() + '\t');
        }
    }
}
