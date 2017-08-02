package gui.ex24;

import javax.swing.Icon;

public class ComboLabel{
    String text;
    Icon icon;

    ComboLabel(String text, Icon icon){
      this.text = text;
      this.icon = icon;
    }

    public String getText(){
      return text;
    }

    public Icon getIcon(){
      return icon;
    }

    public String toString(){
    	return text;
    }
  }