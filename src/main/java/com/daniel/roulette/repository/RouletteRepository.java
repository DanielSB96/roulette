package com.daniel.roulette.repository;
import com.daniel.roulette.model.Roulette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RouletteRepository extends CrudRepository<Roulette, String> {
}
