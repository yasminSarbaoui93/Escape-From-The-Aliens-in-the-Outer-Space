package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.EscapeHatchCard;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.model.Character;

public class EscapeMove extends Move {
	EscapeHatchCard escapeCard=null;
	private Match match;
    public EscapeHatchCard getEscapeCard() {
		return escapeCard;
	}


	Character escapedCharacter;
	
	public EscapeMove(GameState gameState, Sector destinationSector,Match match) {
		super(gameState, destinationSector);
		this.match=match;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void execute(){
		gameState.getCurrentCharacter().getCurrentSector().getCharacterList().
		remove(gameState.getCurrentCharacter());
		
		gameState.getMap().takeSector(destinationSector.getSectorName()).getCharacterList()
			.add(gameState.getCurrentCharacter());
		gameState.getCurrentCharacter().setCurrentSector(destinationSector);
		gameState.getTurn().setTurnState(TurnState.HASMOVED);
		
		escapeCard=(EscapeHatchCard) gameState.getEscapeHatchDeck().removeCard();
			
		
		if(escapeCard.getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
			escapedCharacter=gameState.getCurrentCharacter();
			//aggiungo ai vincitori
			gameState.getWinners().add(escapedCharacter);
			gameState.goToNextCharacter();
			gameState.getCharacterList().remove(escapedCharacter);
			gameState.getTurn().setTurnState(TurnState.STARTED);
			

		}
		gameState.destroyShallop(destinationSector);
			
		 if(match.isGameOver()){
				System.out.println("aaa");
				match.setMatchState(MatchState.ENDED);
				System.out.println(match.getGameState());
				}
	}
}
