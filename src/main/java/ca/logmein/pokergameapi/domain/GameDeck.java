package ca.logmein.pokergameapi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Entity
@Table(name = "game_deck")
public class GameDeck implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Column(name = "create_date")
    @Nonnull
    private LocalDate createDate;

    @OneToMany(mappedBy = "gameDeck")
    @Nonnull
    private Set<Deck> decks = new HashSet<>();

    @Column(name = "game_deck_name",unique=true)
    @Nonnull
    private String gameDeckName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "gameDeck")
    private Set<Player> players = new HashSet<>();
    public GameDeck addDeck(final Deck deck) {
        decks.add(deck);
        deck.setGameDeck(this);
        return this;
    }

    public GameDeck addPlayer(final Player player) {
        players.add(player);
        player.setGameDeck(this);
        return this;
    }

    public GameDeck createDate(final LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public GameDeck decks(final Set<Deck> decks) {
        this.decks = decks;
        return this;
    }


    public GameDeck gameDeckName(final String gameDeckName) {
        this.gameDeckName = gameDeckName;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Set<Deck> getDecks() {
        return decks;
    }

    public String getGameDeckName() {
        return gameDeckName;
    }

    public Long getId() {
        return id;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public GameDeck players(final Set<Player> players) {
        this.players = players;
        return this;
    }

    public GameDeck removeDeck(final Deck deck) {
        decks.remove(deck);
        deck.setGameDeck(null);
        return this;
    }

    public GameDeck removePlayer(final Player player) {
        players.remove(player);
        player.setGameDeck(null);
        return this;
    }

    public void setCreateDate(final LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setDecks(final Set<Deck> decks) {
        this.decks = decks;
    }

    public void setGameDeckName(final String gameDeckName) {
        this.gameDeckName = gameDeckName;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPlayers(final Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "GameDeck{" +
            "id=" + getId() +
            ", gameDeckName='" + getGameDeckName() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
	

}
