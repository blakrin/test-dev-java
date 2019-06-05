package ca.logmein.pokergameapi.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Blaise Siani

 * @Date May 31, 2019
 *
 */
@JsonInclude(Include.NON_DEFAULT)
public class GameDeckDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Nullable
	@JsonIgnore
	private LocalDate createDate;
	
	@Nonnull
	private String gameDeckName;
	
	
	private Long id;

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	/**
	 * @return the createDate
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/**
	 * @return the gameDeckName
	 */
	public String getGameDeckName() {
		return gameDeckName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param gameDeckName the gameDeckName to set
	 */
	public void setGameDeckName(final String gameDeckName) {
		this.gameDeckName = gameDeckName;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(final Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameDeckDTO [id=" + id + ", gameDeckName=" + gameDeckName + ", createDate=" + createDate + "]";
	}
	
	
}
