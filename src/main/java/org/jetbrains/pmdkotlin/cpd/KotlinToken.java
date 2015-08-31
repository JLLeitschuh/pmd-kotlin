package org.jetbrains.pmdkotlin.cpd;

import com.intellij.psi.tree.IElementType;

public class KotlinToken {
   public KotlinToken(String image, String tokenSrcID, int ofset, int beginLine, IElementType tokenType) {
        this.image = image;
        this.filename = tokenSrcID;
        this.ofset = ofset;
        this.beginLine = beginLine;
        this.tokenType = tokenType;
    }


    public IElementType tokenType;
    public String image;
    public String filename;
    public int beginLine;
    public int ofset;
}
