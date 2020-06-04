package com.daniel.roulette.model;
public class Bet {
    private String betOn;
    private Long amount;
    private String userId;
    public String getBetOn() {
        return betOn;
    }
    public void setBetOn(String betOn) {
        this.betOn = betOn;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
