package gui.ex23;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton okButton;
	private JButton previewButton;
	private JButton cancelButton;

	// 環境情報の参照
	GraphicsEnvironment ge;
	Font fonts[];

	public MenuDialog() {
		super();
		fontColor = Color.BLACK;
		backgroundColor = Color.WHITE;
		font = new Font("Monospaced", Font.BOLD, 32);

		initLayout();

	}

	private void initLayout() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		setResizable(false);

		fontColors = new int[3];
		backgroundColors = new int[3];
		setBounds(100, 100, 400, 200);
		fontColorField = new JTextField[3];
		backgroundColorField = new JTextField[3];
		ActionListener tfa = new ComponentActionListener();
		for (int i = 0; i < 3; i++) {
			fontColors[i] = 0;
			backgroundColors[i] = 255;
			fontColorField[i] = new JTextField("0");
			fontColorField[i].setPreferredSize(new Dimension(50, 20));
			fontColorField[i].addActionListener(tfa);
			backgroundColorField[i] = new JTextField("255");
			backgroundColorField[i].setPreferredSize(new Dimension(50, 20));
			backgroundColorField[i].addActionListener(tfa);
		}
		fontColor = new Color(0, 0, 0);
		backgroundColor = new Color(255, 255, 255);
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
		fontSizeList.setSelectedItem(32);;
		ActionListener act = new ButtonActionListener();
		okButton = new JButton("OK");
		okButton.addActionListener(act);
		previewButton = new JButton("Preview");
		previewButton.addActionListener(act);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(act);
		setLayoutComponent(gbl, 0, 0, 1, 1, new JLabel("Font", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 0, 7, 1, fontList);
		setLayoutComponent(gbl, 0, 1, 1, 1, new JLabel("FontSize", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 1, 2, 1, fontSizeList);
		setLayoutComponent(gbl, 0, 2, 1, 1, new JLabel("FontColor ", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 2, 1, 1, new JLabel("  R  "));
		setLayoutComponent(gbl, 2, 2, 1, 1, fontColorField[0]);
		setLayoutComponent(gbl, 3, 2, 1, 1, new JLabel("  G  "));
		setLayoutComponent(gbl, 4, 2, 1, 1, fontColorField[1]);
		setLayoutComponent(gbl, 5, 2, 1, 1, new JLabel("  B  "));
		setLayoutComponent(gbl, 6, 2, 1, 1, fontColorField[2]);
		setLayoutComponent(gbl, 7, 2, 1, 1, fontColorPanel = new JPanel());
		setLayoutComponent(gbl, 0, 3, 1, 1, new JLabel("BackgroundColor", SwingConstants.RIGHT));
		setLayoutComponent(gbl, 1, 3, 1, 1, new JLabel("  R  "));
		setLayoutComponent(gbl, 2, 3, 1, 1, backgroundColorField[0]);
		setLayoutComponent(gbl, 3, 3, 1, 1, new JLabel("  G  "));
		setLayoutComponent(gbl, 4, 3, 1, 1, backgroundColorField[1]);
		setLayoutComponent(gbl, 5, 3, 1, 1, new JLabel("  B  "));
		setLayoutComponent(gbl, 6, 3, 1, 1, backgroundColorField[2]);
		setLayoutComponent(gbl, 7, 3, 1, 1, backgroundColorPanel = new JPanel());
		setLayoutComponent(gbl, 4, 4, 2, 1, okButton);
		setLayoutComponent(gbl, 6, 4, 2, 1, cancelButton);
		fontColorPanel.setBackground(fontColor);
		backgroundColorPanel.setBackground(backgroundColor);
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
				}
				synchronized (fontColor) {
					fontColor = new Color(fontColors[0], fontColors[1], fontColors[2]);
				}
				synchronized (backgroundColor) {
					backgroundColor = new Color(backgroundColors[0], backgroundColors[1], backgroundColors[2]);
				}
				dispose();
			} else if (e.getSource().equals(previewButton)) {

			} else if (e.getSource().equals(cancelButton)) {

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
			fontColorPanel.setBackground(new Color(fontColors[0], fontColors[1], fontColors[2]));
			backgroundColorPanel
					.setBackground(new Color(backgroundColors[0], backgroundColors[1], backgroundColors[2]));
		}

	}

}
