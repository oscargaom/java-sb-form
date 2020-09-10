package com.bolsadeideas.springboot.app.springbootform.editors;

import java.beans.PropertyEditorSupport;

public class MayusculasEditor extends PropertyEditorSupport {

    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim());
    }
    
}
