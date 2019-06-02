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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Entity
@Table(name = "deck", uniqueConstraints=
@UniqueConstraint(columnNames={"deck_name", "game_deck_id"}))
public class Deck implements Serializable {

/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1L;
    
    /** The cartes. */
    @OneToMany(mappedBy = "deck")
    private Set<Carte> cartes = new HashSet<>();

    /** The create date. */
    @Column(name = "create_date")
    @Nonnull
    private LocalDate createDate;

    /** The deck name. */
    @Column(name = "deck_name")
    @Nonnull
    private String deckName;

    /** The game deck. */
    @ManyToOne
    @JsonIgnoreProperties("decks")
    private GameDeck gameDeck;
    
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    /**
     * Gets the cartes.
     *
     * @return the cartes
     */
    public Set<Carte> getCartes() {
        return cartes;
    }

    /**
     * Gets the creates the date.
     *
     * @return the creates the date
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Gets the deck name.
     *
     * @return the deck name
     */
    public String getDeckName() {
        return deckName;
    }

    /**
     * Gets the game deck.
     *
     * @return the game deck
     */
    public GameDeck getGameDeck() {
        return gameDeck;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
    

    /**
     * Sets the cartes.
     *
     * @param cartes the new cartes
     */
    public void setCartes(final Set<Carte> cartes) {
        this.cartes = cartes;
    }

    /**
     * Sets the creates the date.
     *
     * @param createDate the new creates the date
     */
    public void setCreateDate(final LocalDate createDate) {
        this.createDate = createDate;
    }

    /**
     * Sets the deck name.
     *
     * @param deckName the new deck name
     */
    public void setDeckName(final String deckName) {
        this.deckName = deckName;
    }

    /**
     * Sets the game deck.
     *
     * @param gameDeck the new game deck
     */
    public void setGameDeck(final GameDeck gameDeck) {
        this.gameDeck = gameDeck;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Deck{" +
            "id=" + getId() +
            ", deckName='" + getDeckName() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
    
}
