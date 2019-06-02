package ca.logmein.pokergameapi.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@ApiModel(description = "@author Blaise Siani")
public class PlayerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Nullable
	@JsonIgnore
	private LocalDate createDate;
	
	@JsonIgnore
	private String fistName;
	
	
	private Long gameDeckId;
	
	@JsonIgnore
	private Long id;

	@JsonIgnore
	private String lastName;
	
	@Nonnull
    private String pseudo;


	public LocalDate getCreateDate() {
		return createDate;
	}

	public String getFistName() {
		return fistName;
	}

	public Long getGameDeckId() {
		return gameDeckId;
	}

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

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setFistName(final String fistName) {
		this.fistName = fistName;
	}

	public void setGameDeckId(final Long gameDeckId) {
		this.gameDeckId = gameDeckId;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "PlayerDTO{" + "id=" + getId() + ", fistName='" + getFistName() + "'" + ", lastName='" + getLastName()
				+ "'" + ", createDate='" + getCreateDate() + "'" + ", gameDeck=" + getGameDeckId() + ", gameDeck="
				+ getGameDeckId() + "}";
	}
}
