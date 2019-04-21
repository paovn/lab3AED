package model;

import java.util.Date;

public class Historical implements Comparable<Historical>{

    private Date time;
    private double price;

    public Historical(Date time, double price) {
        this.time = time;
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "time: "+time+"\nprice: "+price;
    }

    @Override
    public int compareTo(Historical o) {
        if (o == null) return 1;

        int tC = time.compareTo(o.time);
        if (tC != 0){
            return tC;
        }else {
            return (int) (price - o.price);
        }
    }
}
