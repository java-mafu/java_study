package gui.ex14;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;


public class Property extends Dialog implements ActionListener{


	final int LISTSIZE = 8;//表示するListの縦の大きさ
	//Layout
	GridBagLayout gbLayout = new GridBagLayout();

	//List一覧
	List fontList;
	List fontSizeList;
	List fontColorList;
	List backgroundColorList;
	Button okButton;
	Button cancelButton;
	final String ListStrings[] = {
			"フォント",
			"フォントサイズ",
			"文字色",
			"背景色"
	};

	//環境情報の参照
	GraphicsEnvironment ge;
	Font fonts[];
	Color colors[] = {
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.DARK_GRAY,
			Color.GRAY,
			Color.GREEN,
			Color.LIGHT_GRAY,
			Color.MAGENTA,
			Color.ORANGE,
			Color.PINK,
			Color.RED,
			Color.WHITE,
			Color.YELLOW
			};
	String ColorName[] = {
				"BLACK",
				"BLUE",
				"CYAN",
				"DARK_GRAY",
				"GRAY",
				"GREEN",
				"LIGHT_GRAY",
				"MAGENTA",
				"ORANGE",
				"PINK",
				"RED",
				"WHITE",
				"YELLOW"
			};

	//現在のGraphics情報
	Font graphicsFont;
	float graphicsFontSize;
	Color graphicsFontColor;
	Color graphicsBackgroundColor;
	Dimension winsize;
	int[] graphicsID = new int[4];

	//以前のGraghics情報
	Font prevGraphicsFont;
	float prevGraphicsFontSize;
	Color prevGraphicsFontColor;
	Color prevGraphicsBackgroundColor;
	Dimension prevWinsize;
	int[] prevGraphicsID = new int[4];

		private Preferences prefs;
	    private static final String KEY_BY_FT[]
	    		= {"FontNum_I_Wannar_be_alive",
	    		"FontSize_I_Wannar_be_alive",
	    		"FontColor_I_Wannar_be_alive",
	    		"BackgroundColor_I_Wannar_be_alive"};

	public Property(Frame f){
		super(f);
		prefs = Preferences.userNodeForPackage(this.getClass());
		init();
		addWindowListener(new DialogWindowListener());
	}

	private void init(){

		setLayout(gbLayout);

		Label fontLabel = new Label(ListStrings[0]);
		fontLabel.setAlignment(Label.RIGHT);
		setComponent(fontLabel,0,0,1,1,GridBagConstraints.EAST);

	    fontList = new List(LISTSIZE);
	    setFontList(fontList);
	    setComponent(fontList, 2, 0, 2, 1, GridBagConstraints.WEST);
	    fontList.addItemListener(new DialogItemListener());

	    Label fontSizeLabel = new Label(ListStrings[1]);
	    fontSizeLabel.setAlignment(Label.RIGHT);
	    setComponent(fontSizeLabel,0,1,1,1,GridBagConstraints.EAST);

	    fontSizeList = new List(LISTSIZE);
	    setFontSizeList(fontSizeList);
	    setComponent(fontSizeList, 2, 1, 2, 1, GridBagConstraints.WEST);
	    fontSizeList.addItemListener(new DialogItemListener());

	    Label fontColorLabel = new Label(ListStrings[2]);
	    fontColorLabel.setAlignment(Label.RIGHT);
	    setComponent(fontColorLabel,0,2,1,1,GridBagConstraints.EAST);
	    fontColorList = new List(LISTSIZE);
	    setFontColorList(fontColorList);
	    setComponent(fontColorList, 2, 2, 2, 1, GridBagConstraints.WEST);
	    fontColorList.addItemListener(new DialogItemListener());

	    Label backgroundColorLabel = new Label(ListStrings[3]);
	    backgroundColorLabel.setAlignment(Label.RIGHT);
	    setComponent(backgroundColorLabel,0,3,1,1,GridBagConstraints.EAST);
	    backgroundColorList = new List(LISTSIZE);
	    setBackgroundColorList(backgroundColorList);
	    setComponent(backgroundColorList, 2, 3, 2, 1, GridBagConstraints.WEST);
	    backgroundColorList.addItemListener(new DialogItemListener());

	    okButton = new Button();
	    okButton.setLabel("ok");
	    okButton.setName("OK");
	    okButton.addActionListener(this);
	    setComponent(okButton, 4, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);

	    cancelButton = new Button();
	    cancelButton.setLabel("cancel");
	    cancelButton.setName("cancel");
	    cancelButton.addActionListener(this);
	    setComponent(cancelButton, 5, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);

	    graphicsID[0] = prefs.getInt(KEY_BY_FT[0], 0);
	    graphicsFont = fonts[graphicsID[0]];
	    graphicsID[1] = (prefs.getInt(KEY_BY_FT[1], 9)+1)*10;
		graphicsFontSize = graphicsID[1];
	    graphicsID[2] = prefs.getInt(KEY_BY_FT[2], 0);
		graphicsFontColor = colors[graphicsID[2]];
	    graphicsID[2] = prefs.getInt(KEY_BY_FT[3], 11);
		graphicsBackgroundColor = colors[graphicsID[2]];
		winsize = new Dimension();
		winsize.setSize(graphicsFontSize*4.5, graphicsFontSize);
		setTitle("Property");
		setSize(600, 800);
		updateGraphics();
	}


	private <T extends Component> void setComponent(T component, int x, int y, int width, int height, int anchor){
		 GridBagConstraints gbc = new GridBagConstraints();
	        gbc.anchor = anchor;
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = width;
	        gbc.gridheight = height;
	        gbLayout.setConstraints(component, gbc);
	        add(component);
	}

	private <T extends Component> void setComponent(T component, int x, int y, int width, int height, int anchor, int fill){
		 GridBagConstraints gbc = new GridBagConstraints();
	        gbc.anchor = anchor;
	        gbc.fill = fill;
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = width;
	        gbc.gridheight = height;
	        gbLayout.setConstraints(component, gbc);
	        add(component);
	}



	public Dimension getWinsize() {
		return winsize;
	}

	public Font getGraphicsFont() {
		return graphicsFont;
	}

	public float getGraphicsFontSize() {
		return graphicsFontSize;
	}

	public Color getGraphicsFontColor() {
		return graphicsFontColor;
	}

	public Color getGraphicsBackgroundColor() {
		return graphicsBackgroundColor;
	}


	private void setFontList(List List) {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = ge.getAllFonts();
		for(int i = 0; i < fonts.length; i++){
			String fontName = fonts[i].getName();
			List.add(fontName);
		}
	}

	private void setFontSizeList(List List) {
		for(Integer i = 10; i < 200; i+=10){
			List.add(i.toString());
		}
	}
	private void setFontColorList(List List) {

		for(int i = 0; i< colors.length; i++){
			List.add(ColorName[i]);
		}
	}

	private void setBackgroundColorList(List List) {
		for(int i = 0; i< colors.length; i++){
			List.add(ColorName[i]);
		}
	}


	private void setNewFont(){
		graphicsID[0] = fontList.getSelectedIndex();
		if(graphicsID[0] != -1)
			graphicsFont = fonts[graphicsID[0]];
	}

	private void setNewFontSize(){
		graphicsID[1] = fontSizeList.getSelectedIndex();
		if(graphicsID[1] != -1){
		graphicsFontSize = (graphicsID[1] + 1)*10;
		winsize.setSize(graphicsFontSize * 4.5, graphicsFontSize);
		}
	}

	private void setNewFontColor(){
		graphicsID[2] = fontColorList.getSelectedIndex();
		if(graphicsID[2] != -1)
			graphicsFontColor = colors[graphicsID[2]];
	}

	private void setNewBackGroundColor(){
		graphicsID[3] = backgroundColorList.getSelectedIndex();
		if(graphicsID[3] != -1)
			graphicsBackgroundColor = colors[graphicsID[3]];
	}

	private void updateGraphics(){
		//現在のGraphics情報
		prevGraphicsFont = graphicsFont;
		prevGraphicsFontSize = graphicsFontSize;
		prevGraphicsFontColor = graphicsFontColor;
		prevGraphicsBackgroundColor = graphicsBackgroundColor;
		prevWinsize = (Dimension)winsize.clone();

		for(int i = 0; i< 4; i++){
			prevGraphicsID[i] = graphicsID[i];
			prefs.putInt(KEY_BY_FT[i],graphicsID[i]);
		}
	}

	private void downgradeGraphics(){
		//現在のGraphics情報
		graphicsFont = prevGraphicsFont;
		graphicsFontSize = prevGraphicsFontSize;
		graphicsFontColor = prevGraphicsFontColor;
		graphicsBackgroundColor = prevGraphicsBackgroundColor;
		winsize = (Dimension)prevWinsize.clone();
		for(int i = 0; i< 4; i++)
			graphicsID[i] = prevGraphicsID[i];
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "ok"){
			updateGraphics();
			dispose();
		}
		if(e.getActionCommand() == "cancel"){
			downgradeGraphics();
		}
	}

	class DialogItemListener implements ItemListener {
	public void itemStateChanged(ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED){
				setNewFont();
				setNewFontSize();
				setNewFontColor();
				setNewBackGroundColor();
		}
	}
	}

	class DialogWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
