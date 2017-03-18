package panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;//配置コンポーネントの配置制約指定用
import java.awt.GridBagLayout;//配置のクラスの一つ

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GridBagLayoutPanel extends JPanel{
	GridBagLayout layout;
	JButton []btn = new JButton[5];//ボタン配列
	JTextField classNameText;
	JLabel[] labels = new JLabel[4];
	JList[] lists = new JList[2];
	public GridBagLayoutPanel()
	{
		layout = new GridBagLayout();
		this.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();//制約に使うオブジェクト
		//constraints.fill = GridBagConstraints.BOTH;//【１】縦横にコンポーネットサイズを満たすように配置
		//constraints.weightx = 10.0;//【２】余分の水平スペースを分配
		//constraints.weighty = 10.0;//【３】余分の垂直スペースを分配
		//constraints.insets = new Insets(2, 2, 2, 2);//【４】隙間

		constraints.gridx = 0;	//位置x
		constraints.gridy = 0;	//位置y
		constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		layout.setConstraints(labels[0] = new JLabel("Class"), constraints);//現在の制約を使い
		this.add(labels[0]);

		constraints.gridx = 1;	//位置x
		constraints.gridy = 0;	//位置y
		constraints.gridwidth = 3;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		layout.setConstraints(classNameText = new JTextField("please writeClass Name"), constraints);//現在の制約を使い
		this.add(classNameText);


		constraints.gridx = 4;	//位置x
		constraints.gridy = 0;	//位置y
		constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		layout.setConstraints(btn[0] = new JButton("search"), constraints);//現在の制約を使い
		this.add(btn[0]);

		constraints.gridx = 0;	//位置x
		constraints.gridy = 1;	//位置y
		constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		layout.setConstraints(labels[1] = new JLabel("Fields"), constraints);//現在の制約を使い
		this.add(labels[1]);


		constraints.gridx = 3;	//位置x
		constraints.gridy = 1;	//位置y
		constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labels[2] = new JLabel("Method"), constraints);//現在の制約を使い
		this.add(labels[2]);

	    /* JListの初期データ */
	    String[] initData = {"Blue", "Green", "Red", "Whit", "Black"};
	    JList list = new JList(initData);

	    JScrollPane sp = new JScrollPane();
	    sp.getViewport().setView(list);
	    sp.setPreferredSize(new Dimension(200, 80));

		constraints.gridx = 0;	//位置x
		constraints.gridy = 2;	//位置y
		constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(lists[0] = list, constraints);//現在の制約を使い
		this.add(lists[0]);

	    /* JListの初期データ */
	    String[] initData2 = {"Black999999999"};
	    JList list2 = new JList(initData2);

	    JScrollPane sp2 = new JScrollPane();
	    sp2.getViewport().setView(list2);
	    sp2.setPreferredSize(new Dimension(200, 80));

		constraints.gridx = 3;	//位置x
		constraints.gridy = 2;	//位置y
		constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
		constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
		layout.setConstraints(lists[1] = list2, constraints);//現在の制約を使い
		this.add(lists[1]);



//		constraints.gridx = 2;	//位置x
//		constraints.gridy = 1;	//位置y
//		layout.setConstraints(btn[3] = new JButton("btn4"), constraints);//現在の制約を使い
//		this.add(btn[3]);
//
//		constraints.gridx = 0;	//位置x
//		constraints.gridy = 2;	//位置y
//		constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
//		layout.setConstraints(btn[4] = new JButton("btn5"), constraints);//現在の制約を使い
//		this.add(btn[4]);

	}
	public static void main(String[] args){
		JFrame frm2 = new MyFrame();
		frm2.setBounds(100, 150, 300, 250);
	}

}

