package ca.logmein.pokergameapi.service.dto;
import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@ApiModel(description = "@author Blaise Siani")
public class DeckDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Nullable
	@JsonIgnore
	private LocalDate createDate;
	
	@Nonnull
    private String deckName;
    
    @Nonnull
    private Long gameDeckId;
    
    @JsonIgnore
    private Long id;
    

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getDeckName() {
        return deckName;
    }


    /**
	 * @return the gameDeckId
	 */
	public Long getGameDeckId() {
		return gameDeckId;
	}

    public Long getId() {
        return id;
    }

    public void setCreateDate(final LocalDate createDate) {
        this.createDate = createDate;
    }


    public void setDeckName(final String deckName) {
        this.deckName = deckName;
    }

	/**
	 * @param gameDeckId the gameDeckId to set
	 */
	public void setGameDeckId(final Long gameDeckId) {
		this.gameDeckId = gameDeckId;
	}

	public void setId(final Long id) {
        this.id = id;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeckDTO [createDate=" + createDate + ", deckName=" + deckName + ", id=" + id + "]";
	}
    
}
