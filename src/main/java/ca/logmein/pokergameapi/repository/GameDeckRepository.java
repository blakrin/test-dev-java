package ca.logmein.pokergameapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.logmein.pokergameapi.domain.GameDeck;


/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Repository
public interface GameDeckRepository extends JpaRepository<GameDeck, Long> {

}
