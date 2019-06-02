package ca.logmein.pokergameapi.service.dto;

import java.io.Serializable;

/**
 * 
 * @author Blaise Siani
 * @Date	Jun 1, 2019
 *
 */
public class CardRest implements Serializable{

	private static final long serialVersionUID = 1L;
	private  int quatity;
	private String suit;
	/**
	 * @return the quatity
	 */
	public int getQuatity() {
		return quatity;
	}
	/**
	 * @return the suit
	 */
	public String getSuit() {
		return suit;
	}
	/**
	 * @param quatity the quatity to set
	 */
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	/**
	 * @param suit the suit to set
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
}
