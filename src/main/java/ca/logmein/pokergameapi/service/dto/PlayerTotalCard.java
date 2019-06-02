package ca.logmein.pokergameapi.service.dto;

import java.io.Serializable;

import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.service.mapper.PlayerMapper;
import ca.logmein.pokergameapi.service.mapper.PlayerMapperImpl;

/**
 * 
 * @author Blaise Siani
 * @Date	Jun 1, 2019
 *
 */
public class PlayerTotalCard  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final PlayerMapper mapper = new  PlayerMapperImpl();
	private PlayerDTO player;
	private Long totalAddded;
	
	public PlayerTotalCard() {
		super();
	}
	
	public PlayerTotalCard(   Player player,    Long totalAddded) {
		super();
		this.player = mapper.toDto(player);
		this.totalAddded = totalAddded;
	}
	/**
	 * @return the playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return player;
	}
	/**
	 * @return the totalAddded
	 */
	public Long getTotalAddded() {
		return totalAddded;
	}
	/**
	 * @param playerDTO the playerDTO to set
	 */
	public void setPlayerDTO( PlayerDTO playerDTO) {
		player = playerDTO;
	}
	/**
	 * @param totalAddded the totalAddded to set
	 */
	public void setTotalAddded( Long totalAddded) {
		this.totalAddded = totalAddded;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlayerTotalCard [playerDTO=" + player + ", totalAddded=" + totalAddded + "]";
	}
	

}
