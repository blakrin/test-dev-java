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
@Table(name = "player", uniqueConstraints=
@UniqueConstraint(columnNames={"pseudo", "game_deck_id"}))
public class Player implements Serializable {

   private static final long serialVersionUID = 1L;
    
    @OneToMany(mappedBy = "player")
    private Set<Carte> cartes = new HashSet<>();

    @Column(name = "create_date")
    private LocalDate createDate;
    

    @Column(name = "fist_name")
    private String fistName;
    
    @ManyToOne
    @JsonIgnoreProperties("players")
    private GameDeck gameDeck;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@Column(name = "last_name")
    private String lastName;
    

    @Column(name = "pseudo")
    @Nonnull
    private String pseudo;

    public Player createDate(final LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public Player fistName(final String fistName) {
        this.fistName = fistName;
        return this;
    }

    public Player gameDeck(final GameDeck gameDeck) {
        this.gameDeck = gameDeck;
        return this;
    }


    /**
	 * @return the cartes
	 */
	public Set<Carte> getCartes() {
		return cartes;
	}

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getFistName() {
        return fistName;
    }

    public GameDeck getGameDeck() {
        return gameDeck;
    }

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


    public String getLastName() {
        return lastName;
    }

    /**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public Player lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
	 * @param cartes the cartes to set
	 */
	public void setCartes(final Set<Carte> cartes) {
		this.cartes = cartes;
	}

    public void setCreateDate(final LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setFistName(final String fistName) {
        this.fistName = fistName;
    }


	public void setGameDeck(final GameDeck gameDeck) {
        this.gameDeck = gameDeck;
    }


	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [createDate=" + createDate + ", fistName=" + fistName + ", cartes=" + cartes + ", gameDeck="
				+ gameDeck + ", id=" + id + ", lastName=" + lastName + ", pseudo=" + pseudo + "]";
	}


	
    
}
