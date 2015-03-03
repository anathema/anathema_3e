package net.sf.anathema.cards.layout;

import net.sf.anathema.cards.ICard;

import com.itextpdf.text.DocumentException;

public interface ICardLayout {
	void drawCard(ICard card) throws DocumentException;
	
	ICardReportResourceProvider getResourceProvider();
	
	int getCardWidth();
	
	int getCardHeight();
}
