package com.daniel.roulette.model;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.List;
@RedisHash("Roulette")
public class Roulette implements Serializable {
    private String id;
    private String name;
    private boolean opening;
    private List<Bet> bets;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isOpening() {
        return opening;
    }
    public void setOpening(boolean opening) {
        this.opening = opening;
    }
    public List<Bet> getBets() {
        return bets;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
