package ca.logmein.pokergameapi.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import ca.logmein.pokergameapi.service.dto.CardsDTO;

@RunWith(SpringRunner.class)
@JsonTest
public class GameDeckServiceTDD {
	
	@Value("classpath:data/cards-with-out-players.json")
	private org.springframework.core.io.Resource cards;
	
	@Autowired
	JacksonTester<List<CardsDTO>> cardsList;
	
	@Value("classpath:data/remaining-cards.json")
	private org.springframework.core.io.Resource output;
	
	@Test
	public void remainingCardsTest() {
		
	}
	
	@Test
	public void remainingCardTDD() {
		  try {
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			
			final List<String> orderSuit = Arrays.asList("hearts", "spades", "clubs", "diamonds");
			
			assertThat(cardsList.write(cardLs.stream().sorted(new Comparator<CardsDTO>() {
				@Override
				public int compare(CardsDTO o1, CardsDTO o2) {
					if (orderSuit.indexOf(o1.getColor().toLowerCase()) > orderSuit.indexOf(o2.getColor().toLowerCase()))
						return 1;
					else
						return -1;
				}
			}).collect(Collectors.toList()))).isEqualToJson(output);
			
			final List<CardsDTO> cardL1 =cardsList.readObject(output.getInputStream()).stream().sorted(new Comparator<CardsDTO>() {

				@Override
				public int compare(CardsDTO card1, CardsDTO card2) {
					if (card1.getColor().toLowerCase().equals(card2.getColor().toLowerCase()))
						return -card1.getFaceValue().compareTo(card2.getFaceValue());
					return 0;
				}
			}).collect(Collectors.toList());
			
			System.out.println("the result"+cardL1);
			} catch (final IOException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void shuffleTTD() {
		try {
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			final List<CardsDTO> cardLsClone = new ArrayList<>();
			cardLsClone.addAll(cardLs);
			cardLs.clear();
			assertThat(cardLsClone.size(), is(52));
			assertThat(cardLs.size(), is(0));
			for (int i = 0; i < 52; i++) {
				final int randomPosition = new Random().nextInt(cardLsClone.size());
				cardLs.add(cardLsClone.get(randomPosition));
				cardLsClone.remove(randomPosition);
			}
			assertThat(cardLsClone.size(), is(0));
			assertThat(cardLs.size(), is(52));
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
}
