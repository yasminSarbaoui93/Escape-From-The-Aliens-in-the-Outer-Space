package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.EscapeHatchCard;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.Human;
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
			boolean checkEnd= false;
			for(Character character : gameState.getCharacterList()){
				if(character.getClass()==Human.class&& !character.equals(escapedCharacter)){
					checkEnd=true;
				}
			}
			gameState.getWinners().add(escapedCharacter);
			if(checkEnd){
			EndTurn endturn = new EndTurn(this.gameState,this.match);
			endturn.execute();
			}
			
			gameState.getCharacterList().remove(escapedCharacter);
			gameState.getTurn().setTurnState(TurnState.STARTED);
			

		}
		gameState.destroyShallop(destinationSector);
		boolean checkEndedHatch= true;
		if(escapeCard.getEscapeHatchType()==EscapeHatchType.RED_SHALLOP){
			for(EscapeSector sector : gameState.getMap().getEscapeHatchList()){
				if(sector.isAvailable()){
					checkEndedHatch=false;
				}	
				}
			if(checkEndedHatch){
				gameState.getCharacterList().remove(escapedCharacter);
			}
			
		}
		
			
		 if(match.isGameOver()){
				
				match.setMatchState(MatchState.ENDED);
				
				}
	}
}
