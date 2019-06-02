package ca.logmein.pokergameapi.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Carte.
 *
 * @author Blaise Siani
 */
@Entity
@Table(name = "carte")
public class Carte implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The color. */
    @Column(name = "color")
    @Nonnull
    private String color;

    /** The deck. */
    @ManyToOne
    @JsonIgnoreProperties("cartes")
    private Deck deck;
    
    
    
    /** The face name. */
    @Column(name = "face_name")
    @Nonnull
    private String faceName;

    /** The face value. */
    @Column(name = "face_value")
    @Nonnull
    private Integer faceValue;
    
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** The player. */
    @ManyToOne
    @JsonIgnoreProperties("cartes")
    private Player player;


	/**
	 * Color.
	 *
	 * @param color the color
	 * @return the carte
	 */
	public Carte color(final String color) {
        this.color = color;
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

    /**
     * Face name.
     *
     * @param faceName the face name
     * @return the carte
     */
    public Carte faceName(final String faceName) {
        this.faceName = faceName;
        return this;
    }


    /**
     * Face value.
     *
     * @param faceValue the face value
     * @return the carte
     */
    public Carte faceValue(final Integer faceValue) {
        this.faceValue = faceValue;
        return this;
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the deck.
     *
     * @return the deck
     */
	public Deck getDeck() {
		return deck;
	}

    /**
     * Gets the face name.
     *
     * @return the face name
     */
    public String getFaceName() {
        return faceName;
    }


    /**
     * Gets the face value.
     *
     * @return the face value
     */
    public Integer getFaceValue() {
        return faceValue;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
	public Player getPlayer() {
		return player;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * Sets the color.
     *
     * @param color the new color
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Sets the deck.
     *
     * @param deck the deck to set
     */
	public void setDeck(final Deck deck) {
		this.deck = deck;
	}

  

    /**
     * Sets the face name.
     *
     * @param faceName the new face name
     */
    public void setFaceName(final String faceName) {
        this.faceName = faceName;
    }

    /**
     * Sets the face value.
     *
     * @param faceValue the new face value
     */
    public void setFaceValue(final Integer faceValue) {
        this.faceValue = faceValue;
    }

	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Long id) {
        this.id = id;
    }

	/**
	 * Sets the player.
	 *
	 * @param player the player to set
	 */
	public void setPlayer(final Player player) {
		this.player = player;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Carte [color=" + color + ", deck=" + deck + ", faceName=" + faceName + ", faceValue=" + faceValue
				+ ", id=" + id + ", player=" + player + "]";
	}
    
    
}
