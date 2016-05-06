package com.jirbo.adcolony;

public class AdColonyV4VCReward {
    boolean f1960a;
    String f1961b;
    int f1962c;

    AdColonyV4VCReward(boolean success, String name, int amount) {
        this.f1960a = success;
        this.f1961b = name;
        this.f1962c = amount;
    }

    public boolean success() {
        return this.f1960a;
    }

    public String name() {
        return this.f1961b;
    }

    public int amount() {
        return this.f1962c;
    }

    public String toString() {
        if (this.f1960a) {
            return this.f1961b + ":" + this.f1962c;
        }
        return "no reward";
    }
}
