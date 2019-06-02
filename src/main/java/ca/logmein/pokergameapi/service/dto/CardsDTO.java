package ca.logmein.pokergameapi.service.dto;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@ApiModel(description = "@author Blaise Siani")
@JsonInclude(Include.NON_NULL)
public class CardsDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String color;
	
    private Long deckId;

    private String faceName;


    private Integer faceValue;
    @JsonIgnore
    private Long id;
    
    
    private Long playerId;
    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

    public String getColor() {
        return color;
    }

  

    /**
	 * @return the deckId
	 */
	public Long getDeckId() {
		return deckId;
	}

    public String getFaceName() {
        return faceName;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public Long getId() {
        return id;
    }

    /**
	 * @return the playerId
	 */
	public Long getPlayerId() {
		return playerId;
	}

   

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

   

    public void setColor(final String color) {
        this.color = color;
    }

	/**
	 * @param deckId the deckId to set
	 */
	public void setDeckId(final Long deckId) {
		this.deckId = deckId;
	}

	public void setFaceName(final String faceName) {
        this.faceName = faceName;
    }

	public void setFaceValue(final Integer faceValue) {
        this.faceValue = faceValue;
    }

	public void setId(final Long id) {
        this.id = id;
    }

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(final Long playerId) {
		this.playerId = playerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarteDTO [color=" + color + ", deckId=" + deckId + ", faceName=" + faceName + ", faceValue=" + faceValue
				+ ", id=" + id + ", playerId=" + playerId + "]";
	}

	
	
   
}
