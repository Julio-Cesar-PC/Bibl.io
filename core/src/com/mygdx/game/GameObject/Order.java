package com.mygdx.game.GameObject;

import java.util.ArrayList;

public class Order {
    private boolean concluded;
    private ArrayList<Book> order;

    public ArrayList<Book> getOrder() {
        return order;
    }

    public Order(){
        int r;
        this.concluded = false;
        this.order = new ArrayList<Book>();
        for (int i = 0; i < 5; i++) {
            r = (int) (Math.random() * 7);
            String cor = new String[]{"Azul", "Vermelho", "Amarelo", "Rosa", "Laranja", "Verde", "Branca"}[r];
            order.add(new Book (cor));
        }
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void listOrder(){
        for(Book book: order){
            System.out.println(book.getColor() + '\t');
        }
    }

    public boolean checkOrder(Bag bag) {
        int i = 0;
        for(Book book: order){
            Book bagBook = bag.getBag().get(i);
            if(!book.getColor().equals(bagBook.getColor()))
                return false;
            i++;
        }
        this.concluded = true;
        return true;
    }
}
