package com.daniel.roulette.service;
import com.daniel.roulette.model.Bet;
import com.daniel.roulette.model.Roulette;
import com.daniel.roulette.repository.RouletteRepository;
import com.daniel.roulette.util.exception.ConflictException;
import com.daniel.roulette.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class RouletteService {
    @Autowired
    private RouletteRepository rouletteRepository;
    private final Long MAX_AMOUNT_BET = 10_000L;
    public Map<String, Boolean> getAll() {
        List<Roulette> roulettes = new ArrayList<>();
        rouletteRepository.findAll().forEach(roulette -> roulettes.add(roulette));

        return roulettes.stream().collect(Collectors.toMap(Roulette::getId, Roulette::isOpening));
    }
    public String post() {
        return rouletteRepository.save(new Roulette()).getId();
    }
    public String openingRoulette(String rouletteId) {
        existById(rouletteId);
        Roulette byId = rouletteRepository.findById(rouletteId).get();
        if (byId.isOpening()) {
            throw new ConflictException("ya se encuentra abierta la ruleta: " + rouletteId);
        } else {
            byId.setOpening(true);
            byId.setBets(new ArrayList<>());
            rouletteRepository.save(byId);

            return "Operacion exitosa";
        }
    }
    public void bet(Bet bet, String rouletteId) {
        Long totalAmount = 0L;
        existById(rouletteId);
        Roulette byId = rouletteRepository.findById(rouletteId).get();
        isClosed(byId);
        if (byId.getBets() != null) {
            totalAmount = byId.getBets().stream().mapToLong(Bet::getAmount).sum();
        } else {
            byId.setBets(new ArrayList<>());
        }
        exceedsMaximumValue(totalAmount + bet.getAmount());
        valueOfBetIsValid(bet.getBetOn());
        byId.getBets().add(bet);
        rouletteRepository.save(byId);
    }
    public List<Bet> closeRoulette(String rouletteId) {
        existById(rouletteId);
        Roulette byId = rouletteRepository.findById(rouletteId).get();
        if (byId.isOpening()) {
            byId.setOpening(false);
            rouletteRepository.save(byId);
        }

        return byId.getBets();
    }
    private void exceedsMaximumValue(Long amount) {
        if (amount > MAX_AMOUNT_BET) {
            throw new ConflictException("supera el monto maximo de apuesta en la ruleta");
        }
    }
    private void existById(String rouletteId) {
        if (!rouletteRepository.existsById(rouletteId)) {
            throw new NotFoundException("No existe la ruleta con id: " + rouletteId);
        }
    }
    private void valueOfBetIsValid(String valueBet) {
        try {
            Integer valueInt = Integer.valueOf(valueBet);
            if (valueInt < 0 || valueInt > 36) {
                throw new ConflictException("el numero ingresado debe estar entre 0 y 36");
            }
        } catch (NumberFormatException n) {
            if (!valueBet.equalsIgnoreCase("rojo") && !valueBet.equalsIgnoreCase("negro")) {
                throw new ConflictException("el color debe ser negro o rojo");
            }
        }
    }
    private void isClosed(Roulette roulette) {
        if (!roulette.isOpening()) {
            throw new ConflictException("la ruleta a la que desea apostar se encuentra cerrada");
        }
    }
}
