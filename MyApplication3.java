import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
public class MyApplication3 extends JFrame implements ActionListener , ChangeListener {
    StateManager stateManager;
    MyCanvas canvas;

    private JButton deleteButton = new JButton("Delete"),
	shadowButton = new JButton("Shadow"),
	sortupButton = new JButton("SortUp"),
	sorttallButton = new JButton("SortTall"),
	sortvertButton = new JButton("SortVert"),
	movelocationButton = new JButton("Move");

    private JMenuBar menuBar;
    private JMenu colorMenu,moveMenu, fillMenu, widthMenu;
    private JMenuItem redItem, blueItem, greenItem, otherLine,
	cutItem,copyItem,pasteItem,cancelItem,
	fillRed, fillBlue, fillGreen, fillWhite, otherFill, spoitFill,
	thin, normal, bold;

    private JSlider alphaSlider = new JSlider(0,255);

    public MyApplication3() {
	super("My Paint Application3");

	//キャンバスをセット
	canvas = new MyCanvas();
	canvas.setBackground(Color.white);

	JPanel jp = new JPanel();
	jp.setLayout(new FlowLayout());

	//stateManagerをセット
	stateManager = new StateManager(canvas);
	stateManager.setState(new RectState(stateManager));//初期状態はRectangle

	//ボタンをセット
	RectButton rectButton = new RectButton(stateManager);
	jp.add(rectButton);
	OvalButton ovalButton = new OvalButton(stateManager);
	jp.add(ovalButton);
	StarButton starButton = new StarButton(stateManager);
	jp.add(starButton);
	SelectButton selectButton = new SelectButton(stateManager);
	jp.add(selectButton);
	AnimButton animButton = new AnimButton(stateManager);
	jp.add(animButton);
	jp.add(deleteButton);
	jp.add(shadowButton);
	jp.add(sortupButton);
	jp.add(sorttallButton);
	jp.add(sortvertButton);
	jp.add(movelocationButton);
	jp.add(alphaSlider);
	alphaSlider.addChangeListener(this);
	alphaSlider.setValue(255);
	deleteButton.addActionListener(new deleteListener());
	shadowButton.addActionListener(new shadowListener());
	sortupButton.addActionListener(new sortupListener());
	sorttallButton.addActionListener(new sorttallListener());
	sortvertButton.addActionListener(new sortvertListener());
	movelocationButton.addActionListener(new movelocationListener());
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(jp,BorderLayout.NORTH);
	getContentPane().add(canvas,BorderLayout.CENTER);

	//colorMenuをセット
	menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	colorMenu = new JMenu("Color");
	redItem = new JMenuItem("Red");
	blueItem = new JMenuItem("Blue");
	greenItem = new JMenuItem("green");
	otherLine = new JMenuItem("Other");
	colorMenu.add(redItem);
	colorMenu.add(blueItem);
	colorMenu.add(greenItem);
	colorMenu.add(otherLine);
	redItem.addActionListener(this);
	blueItem.addActionListener(this);
	greenItem.addActionListener(this);
	otherLine.addActionListener(this);
	menuBar.add(colorMenu);

	//copy,cut,pastのMenu
	moveMenu = new JMenu("Move");
	copyItem = new JMenuItem("Copy");
	cutItem = new JMenuItem("Cut");
	pasteItem = new JMenuItem("Paste");
	cancelItem = new JMenuItem("Cancel");
	moveMenu.add(copyItem);
	moveMenu.add(cutItem);
	moveMenu.add(pasteItem);
	moveMenu.add(cancelItem);
	copyItem.addActionListener(this);
    cutItem.addActionListener(this);
    pasteItem.addActionListener(this);
    cancelItem.addActionListener(this);
    menuBar.add(moveMenu);

    //fillのMenu
    fillMenu = new JMenu("fColor");
    fillRed = new JMenuItem("Red");
    fillBlue = new JMenuItem("Blue");
    fillGreen = new JMenuItem("Green");
    fillWhite = new JMenuItem("None");
    otherFill = new JMenuItem("Other");
    spoitFill = new JMenuItem("Spoit");
    fillMenu.add(fillRed);
    fillMenu.add(fillBlue);
    fillMenu.add(fillGreen);
    fillMenu.add(fillWhite);
    fillMenu.add(otherFill);
    fillMenu.add(spoitFill);
    fillRed.addActionListener(this);
    fillBlue.addActionListener(this);
    fillGreen.addActionListener(this);
    fillWhite.addActionListener(this);
    otherFill.addActionListener(this);
    spoitFill.addActionListener(this);
    menuBar.add(fillMenu);

    //lineWidthのMenu
    widthMenu = new JMenu("Width");
    thin = new JMenuItem("Thin");
    normal = new JMenuItem("Normal");
    bold = new JMenuItem("Bold");
    widthMenu.add(thin);
    widthMenu.add(normal);
    widthMenu.add(bold);
    thin.addActionListener(this);
    normal.addActionListener(this);
    bold.addActionListener(this);
    menuBar.add(widthMenu);


	//マウスクリックのリスナー
	canvas.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    stateManager.mouseDown(e.getX(), e.getY());
		}
	    });

	//マウスドラッグのリスナー
	canvas.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		    stateManager.mouseDrag(e.getX(), e.getY());
		}
	    });

	canvas.addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent e) {
		    stateManager.mouseUp(e.getX(), e.getY());
		}
	    });

	canvas.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
		    stateManager.mouseMove(e.getX(), e.getY());
		}
	    });

	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
    }

    //アクションリスナー
    public void actionPerformed(ActionEvent e) {
	//ライン色変更
	if(e.getSource() == redItem) canvas.mediator.selectLineColor(Color.red);
	else if (e.getSource() == blueItem)        canvas.mediator.selectLineColor(Color.blue);
	else if (e.getSource() == greenItem) canvas.mediator.selectLineColor(Color.green);
	else if (e.getSource() == otherLine) {
	    Color lineColor = JColorChooser.showDialog(this,"LineColor",Color.white);
	    canvas.mediator.selectLineColor(lineColor);
	}
	//カット、コピー、ペースト
	else if (e.getSource() == copyItem) {
	    stateManager.move = stateManager.COPY;
	    stateManager.moveSelect();
	} else if (e.getSource() == cutItem) {
	    stateManager.move = stateManager.CUT;
	    stateManager.moveSelect();
	    canvas.mediator.repaint();
	} else if (e.getSource() == pasteItem)
	    stateManager.move = stateManager.PASTE;
	else if (e.getSource() == cancelItem)
	    stateManager.move = stateManager.CANCEL;
	//塗り色
	else if (e.getSource() == fillRed)
	    canvas.mediator.selectFillColor(Color.RED);
	else if (e.getSource() == fillBlue)
	    canvas.mediator.selectFillColor(Color.BLUE);
	else if (e.getSource() == fillGreen)
	    canvas.mediator.selectFillColor(Color.GREEN);
	else if (e.getSource() == fillWhite)
	    canvas.mediator.selectFillColor(Color.WHITE);
	else if (e.getSource() == otherFill) {
	    Color fillColor = JColorChooser.showDialog(this,"FillColor",Color.white);
	    canvas.mediator.selectFillColor(fillColor);
	}
	else if (e.getSource() == spoitFill) {
	    try {
            stateManager.setState(new SpoitState(stateManager));
	    } catch (Exception e1) {
            e1.printStackTrace();
	    }
	}
	//太さ
	else if (e.getSource() == thin)
	    canvas.mediator.selectLineWidth(1);
	else if (e.getSource() == normal)
	    canvas.mediator.selectLineWidth(2);
	else if (e.getSource() == bold)
	    canvas.mediator.selectLineWidth(3);
	canvas.repaint();
    }

    public Dimension getPreferredSize() {
	return new Dimension(1400,800);
    }


    //deleteのリスナークラス
    class deleteListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    canvas.mediator.delete();
	}
    }

    //shadowのリスナークラス
    class shadowListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    canvas.mediator.changeShadow();
	}
    }

    class sortupListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    int y;
	    y = canvas.mediator.selectedDrawings.elementAt(0).getY();
	    for(MyDrawing d : canvas.mediator.selectedDrawings) {
		d.setLocation(d.getX(), y);
	    }
	    canvas.repaint();
	}
    }

    class sorttallListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    int y0,h0;
	    MyDrawing f,b,m;
	    //バブルソートを行う
	    for(int i = 0; i < canvas.mediator.selectedDrawings.size(); i++) {
		for(int j = canvas.mediator.selectedDrawings.size() - 1; j > i; j--) {
		    b = canvas.mediator.selectedDrawings.elementAt(j);
		    f = canvas.mediator.selectedDrawings.elementAt(j - 1);
		    if(b.getH() < f.getH()) {
			canvas.mediator.selectedDrawings.set(j, f);
			canvas.mediator.selectedDrawings.set(j - 1, b);
		    }
		}
	    }
	    y0 = canvas.mediator.selectedDrawings.elementAt(0).getY();
	    h0 = canvas.mediator.selectedDrawings.elementAt(0).getH();
	    for (int k = 0; k < canvas.mediator.selectedDrawings.size(); k++) {
		int h = canvas.mediator.selectedDrawings.elementAt(k).getH();
		canvas.mediator.selectedDrawings.elementAt(k).setLocation(k * 100 + 20, y0 + h0 - h);
	    }
	    canvas.repaint();
	}
    }

    class sortvertListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    int xc = 600;
	    int y = 20;
	    int w, h;
	    for(MyDrawing d : canvas.mediator.selectedDrawings) {
		w = d.getW();
		h = d.getH();
		d.setLocation(xc - w / 2, y);
		y = y + h + 20;
	    }
	    canvas.repaint();
	}
    }

    class alphaChangeListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    for (MyDrawing d : canvas.mediator.selectedDrawings) {
		if(d.colorAlpha == 255)
		    d.colorAlpha = 100;
		else
		    d.colorAlpha = 255;
		int fillRed = d.getFillColor().getRed();
		int fillBlue = d.getFillColor().getBlue();
		int fillGreen = d.getFillColor().getGreen();
		d.setFillColor(new Color(fillRed, fillGreen, fillBlue, d.colorAlpha));
	    }
	    canvas.repaint();
	}
    }

    public void stateChanged(ChangeEvent e) {
	for (MyDrawing d : canvas.mediator.selectedDrawings) {
	    int fillRed = d.getFillColor().getRed();
	    int fillBlue = d.getFillColor().getBlue();
	    int fillGreen = d.getFillColor().getGreen();
	    d.setFillColor(new Color(fillRed, fillGreen, fillBlue, alphaSlider.getValue()));
	}
	canvas.repaint();
    }


    class movelocationListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    stateManager.moveLocation = true;
	}
    }

    public static void main(String[] args) {
	MyApplication3 app = new MyApplication3();
	app.pack();
	app.setVisible(true);
    }
}
