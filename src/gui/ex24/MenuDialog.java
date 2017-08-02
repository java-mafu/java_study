package gui.ex24;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.prefs.Preferences;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuDialog extends JDialog {

	private Font font;
	private Color fontColor;
	private Color backgroundColor;
	int[] fontColors;
	int[] backgroundColors;

	public synchronized Font getNewFont() {
		return font;
	}

	public synchronized Color getFontColor() {
		return fontColor;
	}

	public synchronized Color getBackgroundColor() {
		return backgroundColor;
	}

	private JComboBox<String> fontList;
	private JComboBox<Integer> fontSizeList;
	private JTextField[] fontColorField;
	private JTextField[] backgroundColorField;
	private JPanel fontColorPanel;
	private JPanel backgroundColorPanel;
	private JComboBox<ComboLabel> fontColorBox;
	private JComboBox<ComboLabel> backgroundColorBox;
	private JButton okButton;
	private JButton previewButton;
	private JButton cancelButton;

	// 環境情報の参照
	GraphicsEnvironment ge;
	Font fonts[];

	private Preferences prefs;
	private static final String KEY_BY_FT[] = { "FontKind_I_Wannar_be_alive", "FontSize_I_Wannar_be_alive",
			"FontColor_I_Wannar_be_alive", "BackgroundColor_I_Wannar_be_alive" };

	public MenuDialog() {
		super();
		prefs = Preferences.userNodeForPackage(this.getClass());
		font = new Font(prefs.get(KEY_BY_FT[0], "Serif"), Font.BOLD, prefs.getInt(KEY_BY_FT[1], 32));
		fontColor = new Color(prefs.getInt(KEY_BY_FT[2], 0));
		backgroundColor = new Color(prefs.getInt(KEY_BY_FT[3], 0xffffff));
		addWindowListener(new DialogWindowListener());
		initLayout();

	}

	private void initLayout() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		setResizable(false);
		setBounds(100, 100, 600, 300);
		fontColorField = new JTextField[3];
		backgroundColorField = new JTextField[3];
		ActionListener tfa = new ComponentActionListener();
		fontColors = new int[3];
		backgroundColors = new int[3];
		fontColors[0] = fontColor.getRed();
		fontColors[1] = fontColor.getGreen();
		fontColors[2] = fontColor.getBlue();
		backgroundColors[0] = backgroundColor.getRed();
		backgroundColors[1] = backgroundColor.getGreen();
		backgroundColors[2] = backgroundColor.getBlue();

		for (int i = 0; i < 3; i++) {
			fontColorField[i] = new JTextField(String.valueOf(fontColors[i]));
			fontColorField[i].setColumns(4);
			fontColorField[i].addActionListener(tfa);
			backgroundColorField[i] = new JTextField(String.valueOf(backgroundColors[i]));
			backgroundColorField[i].setColumns(4);
			backgroundColorField[i].addActionListener(tfa);
		}
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = ge.getAllFonts();
		String[] fontNames = new String[fonts.length];
		for (int i = 0; i < fonts.length; i++)
			fontNames[i] = fonts[i].getFontName();
		Integer[] fontSizes = new Integer[FontPixel.MAX_FONT];
		for (int i = 0; i < fontSizes.length; i++)
			fontSizes[i] = i + 1;
		fontList = new JComboBox<String>(fontNames);
		fontSizeList = new JComboBox<Integer>(fontSizes);
		fontSizeList.setSelectedItem(prefs.getInt(KEY_BY_FT[1], 32));
		;
		ActionListener act = new ButtonActionListener();
		okButton = new JButton("OK");
		okButton.addActionListener(act);
		previewButton = new JButton("Preview");
		previewButton.addActionListener(act);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(act);

		// ex24で追加
		Field[] colorFieldAll = Color.class.getFields();
		String[] colorField = new String[colorFieldAll.length / 2];
		DefaultComboBoxModel modelf = new DefaultComboBoxModel();
		DefaultComboBoxModel modelb = new DefaultComboBoxModel();
		for (int i = 0; i < colorField.length - 1; i++) {
			colorField[i] = colorFieldAll[i * 2].getName();
			try {
				modelf.addElement(new ComboLabel(colorFieldAll[i * 2].getName(),
						new FillIcon((Color) Color.class.getField(colorFieldAll[i * 2].getName()).get(null), 10, 10)));
				modelb.addElement(new ComboLabel(colorFieldAll[i * 2].getName(),
						new FillIcon((Color) Color.class.getField(colorFieldAll[i * 2].getName()).get(null), 10, 10)));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		modelf.addElement(new ComboLabel("", null));
		modelb.addElement(new ComboLabel("", null));

		fontColorBox = new JComboBox<ComboLabel>(modelf);
		backgroundColorBox = new JComboBox<ComboLabel>(modelb);
		fontColorBox.setRenderer(new CellRenderer());
		backgroundColorBox.setRenderer(new CellRenderer());

		setGridGagLayout(gbl);
		fontColorPanel.setBackground(fontColor);
		backgroundColorPanel.setBackground(backgroundColor);
		fontColorBox.setSelectedIndex(fontColorBox.getItemCount() - 1);
		backgroundColorBox.setSelectedIndex(backgroundColorBox.getItemCount() - 1);
		fontColorBox.addActionListener(new ColorActionListener());
		backgroundColorBox.addActionListener(new ColorActionListener());
	}

	private void setGridGagLayout(GridBagLayout gbl) {
		setLayoutComponent(gbl, 0, 0, 1, 1, new JLabel("Font", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 0, 7, 1, fontList);
		setLayoutComponent(gbl, 0, 1, 1, 1, new JLabel("FontSize", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 1, 2, 1, fontSizeList);
		setLayoutComponent(gbl, 0, 2, 1, 1, new JLabel("FontColor ", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 2, 3, 1, fontColorBox);
		setLayoutComponent(gbl, 4, 2, 3, 1, fontColorPanel = new JPanel());
		setLayoutComponent(gbl, 1, 3, 1, 1, new JLabel("  R  "));
		setLayoutComponent(gbl, 2, 3, 1, 1, fontColorField[0]);
		setLayoutComponent(gbl, 3, 3, 1, 1, new JLabel("  G  "));
		setLayoutComponent(gbl, 4, 3, 1, 1, fontColorField[1]);
		setLayoutComponent(gbl, 5, 3, 1, 1, new JLabel("  B  "));
		setLayoutComponent(gbl, 6, 3, 1, 1, fontColorField[2]);
		setLayoutComponent(gbl, 0, 4, 1, 1, new JLabel("BackgroundColor ", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 4, 3, 1, backgroundColorBox);
		setLayoutComponent(gbl, 4, 4, 3, 1, backgroundColorPanel = new JPanel());
		setLayoutComponent(gbl, 1, 5, 1, 1, new JLabel("  R  "));
		setLayoutComponent(gbl, 2, 5, 1, 1, backgroundColorField[0]);
		setLayoutComponent(gbl, 3, 5, 1, 1, new JLabel("  G  "));
		setLayoutComponent(gbl, 4, 5, 1, 1, backgroundColorField[1]);
		setLayoutComponent(gbl, 5, 5, 1, 1, new JLabel("  B  "));
		setLayoutComponent(gbl, 6, 5, 1, 1, backgroundColorField[2]);
		setLayoutComponent(gbl, 4, 6, 2, 1, okButton);
		setLayoutComponent(gbl, 6, 6, 2, 1, cancelButton);
	}

	private void setLayoutComponent(GridBagLayout gbl, int x, int y, int w, int h, Component b) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(b, gbc);
		add(b);
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(okButton)) {
				String fontName = fontList.getSelectedItem().toString();
				int fontSize = fontSizeList.getSelectedIndex() + 1;
				synchronized (font) {
					font = new Font(fontName, Font.BOLD, fontSize);
					prefs.put(KEY_BY_FT[0], fontName);
					prefs.putInt(KEY_BY_FT[1], fontSize);
				}
				synchronized (fontColor) {
					fontColor = new Color(fontColors[0], fontColors[1], fontColors[2]);
					prefs.putInt(KEY_BY_FT[2], (fontColors[0] << 16) + (fontColors[1] << 8) + fontColors[2]);
				}
				synchronized (backgroundColor) {
					backgroundColor = new Color(backgroundColors[0], backgroundColors[1], backgroundColors[2]);
					prefs.putInt(KEY_BY_FT[3],
							(backgroundColors[0] << 16) + (backgroundColors[1] << 8) + (backgroundColors[2]));
				}
				dispose();
			} else if (e.getSource().equals(previewButton)) {

			} else if (e.getSource().equals(cancelButton)) {
				fontList.setSelectedItem(font.getFontName());
				fontSizeList.setSelectedIndex(font.getSize() - 1);
				fontColorField[0].setText(Integer.toString(fontColor.getRed()));
				fontColorField[1].setText(Integer.toString(fontColor.getGreen()));
				fontColorField[2].setText(Integer.toString(fontColor.getBlue()));
				backgroundColorField[0].setText(Integer.toString(backgroundColor.getRed()));
				backgroundColorField[1].setText(Integer.toString(backgroundColor.getGreen()));
				backgroundColorField[2].setText(Integer.toString(backgroundColor.getBlue()));
				fontColorPanel.setBackground(fontColor);
				backgroundColorPanel.setBackground(backgroundColor);

			} else {
				System.out.println("未対応のボタン");
			}
		}

	}

	private class ComponentActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 3; i++) {
				try {
					fontColors[i] = Integer.parseInt(fontColorField[i].getText());
					if (fontColors[i] < 0 || fontColors[i] > 255)
						throw new NumberFormatException();
				} catch (NumberFormatException nfe) {
					JFrame eframe = new JFrame("Exception");
					JOptionPane.showMessageDialog(eframe, "色指定には0-255の数字を入力して下さい");
					fontColorField[i].setText("0");
					fontColors[i] = 0;
					break;
				}
				try {
					backgroundColors[i] = Integer.parseInt(backgroundColorField[i].getText());
					if (backgroundColors[i] < 0 || backgroundColors[i] > 255)
						throw new NumberFormatException();

				} catch (NumberFormatException nfe) {
					JFrame eframe = new JFrame("Exception");
					JOptionPane.showMessageDialog(eframe, "色指定には0-255の数字を入力して下さい");
					backgroundColorField[i].setText("0");
					backgroundColors[i] = 0;
					break;
				}
			}
			String fontColorStr = fontColorBox.getSelectedItem().toString();
			if (fontColorStr != "") {
				try {
					Color color = (Color) Color.class.getField(fontColorStr).get(null);
					long colorRGB = fontColors[0];
					colorRGB <<= 16;
					colorRGB += fontColors[1] << 8;
					colorRGB += fontColors[2];
					long colorComboRGB = (color.getRGB() & 0xFFFFFF);
					if (colorComboRGB != colorRGB)
						fontColorBox.setSelectedIndex(fontColorBox.getItemCount() - 1);
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}

			String backgroundColorStr = backgroundColorBox.getSelectedItem().toString();
			if (backgroundColorStr != "") {
				try {
					Color color = (Color) Color.class.getField(backgroundColorStr).get(null);
					long colorRGB = backgroundColors[0];
					colorRGB <<= 16;
					colorRGB += backgroundColors[1] << 8;
					colorRGB += backgroundColors[2];
					long colorComboRGB = (color.getRGB() & 0xFFFFFF);
					if (colorComboRGB != colorRGB)
						backgroundColorBox.setSelectedIndex(backgroundColorBox.getItemCount() - 1);
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
			fontColorPanel.setBackground(new Color(fontColors[0], fontColors[1], fontColors[2]));
			backgroundColorPanel
					.setBackground(new Color(backgroundColors[0], backgroundColors[1], backgroundColors[2]));
		}
	}

	private class ColorActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String fontColorStr = fontColorBox.getSelectedItem().toString();
				String backgroundColorStr = backgroundColorBox.getSelectedItem().toString();
				if (fontColorStr != "") {
					Color color = (Color) Color.class.getField(fontColorStr).get(null);
					fontColorPanel.setBackground(color);
					long colorInt = color.getRGB();
					for (int i = 2; i >= 0; i--) {
						int colorIntbyte = (int) (colorInt & 0xFF);
						fontColorField[i].setText("" + colorIntbyte);
						fontColors[i] = colorIntbyte;
						colorInt >>= 8;
					}
				}
				if (backgroundColorStr != "") {
					Color color = (Color) Color.class.getField(backgroundColorStr).get(null);
					backgroundColorPanel.setBackground(color);
					long colorInt = color.getRGB();
					for (int i = 2; i >= 0; i--) {
						int colorIntbyte = (int) (colorInt & 0xFF);
						backgroundColorField[i].setText("" + colorIntbyte);
						backgroundColors[i] = colorIntbyte;
						colorInt >>= 8;
					}
				}
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

		}
	}

	class DialogWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}

}
