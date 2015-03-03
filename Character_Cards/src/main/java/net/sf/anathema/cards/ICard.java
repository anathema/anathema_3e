package net.sf.anathema.cards;

import net.sf.anathema.cards.data.ICardData;

import com.itextpdf.text.pdf.PdfContentByte;

public interface ICard {
	PdfContentByte getPdfContent();
	
	float getX();
	
	float getY();
	
	ICardData getData();
}
