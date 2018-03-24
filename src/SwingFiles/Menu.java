package SwingFiles;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu extends JPanel implements ActionListener {
	private JButton _speed2;
	private JButton _speed;
	private JButton _go;
	private JButton _nextWave;
	public static JLabel _hpText;
	public static JLabel _wave;
	private BoardGui _boardGui;
	public static int _hp = 20;
	public static JLabel _error;
	private JButton _nextLevel;
	private JButton _prevLevel;
	private JButton _top5B;
	public static JLabel _level;
	public static JLabel _time;
	public static JTextArea _top5;

	private static final String FILENAME = "record.txt";

	public Menu(BoardGui boardgui) {
		super(new FlowLayout());
		// JPanel buttonPanel = new JPanel( new FlowLayout());
		this._speed2 = new JButton("speed X2");
		_speed2.setSize(30, 30);
		this._speed = new JButton("speed");
		_speed.setSize(30, 30);
		this._nextWave = new JButton("add wave");
		_nextWave.setSize(30, 30);
		this._go = new JButton("go !");
		_go.setSize(30, 30);
		this._top5B = new JButton("top 5");
		_top5B.setSize(30, 30);
		this._boardGui = boardgui;
		this._nextLevel = new JButton("next level");
		_nextLevel.setSize(30, 30);
		this._prevLevel = new JButton("prev level");
		_prevLevel.setSize(30, 30);

		this._hpText = new JLabel();
		this._wave = new JLabel();
		this._error = new JLabel();
		this._level = new JLabel();
		this._time = new JLabel();
		this._top5 = new JTextArea();

		this.add(_error);

		this.add(_hpText);
		this.add(_wave);
		this.add(_level);
		this.add(_time);
		this.add(_speed);
		this.add(_speed2);
		this.add(_nextWave);
		this.add(_nextLevel);
		this.add(_prevLevel);

		this.add(_top5B);
		this.add(_top5);

		_speed.addActionListener(this);
		_speed2.addActionListener(this);
		_nextWave.addActionListener(this);
		_top5B.addActionListener(this);

		_nextWave.setFocusable(false);
		_speed.setFocusable(false);
		_speed2.setFocusable(false);
		_wave.setFocusable(false);
		_hpText.setFocusable(false);
		_top5B.setFocusable(false);

		Font font = new Font("Arial", Font.BOLD, 16);

		_speed2.setForeground(Color.orange);
		_speed.setBackground(Color.red);

		_nextLevel.addActionListener(this);
		_prevLevel.addActionListener(this);

		_prevLevel.setFocusable(true);
		_nextLevel.setFocusable(true);
		_prevLevel.setVisible(true);
		_nextLevel.setVisible(true);
		_level.setVisible(true);
		_top5B.setVisible(true);

		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		_error.setText("");
		_top5.setText("");
		if (e.getSource() == _speed2) {
			this._boardGui._gameTimer.setDelay(125);
		} else if (e.getSource() == _speed) {
			this._boardGui._gameTimer.setDelay(250);
		} else if (e.getSource() == _top5B) {
			_top5.setText("");
			personRecord[] persons = giveMeFive();
			for (int i = 0; i < 5; i++) {
				personRecord person = giveMin(persons);
				if (person != null)
					_top5.setText(_top5.getText().toString() + person.getInfo().toString() + "\n");
			}

		} else if (e.getSource() == _nextWave) {
			
			if (GameTimer._numOfEnemy == 0) {
				int w = BoardGui._wave + 1;
				this._boardGui._gameTimer.updateNextWaveList(w);
			}
		} else if (e.getSource() == _nextLevel) {
			this._boardGui.getNextLevel();
		} else if (e.getSource() == _prevLevel) {
			this._boardGui.getPrevLevel();
		}
	}
	// return the min time in the array
	private personRecord giveMin(personRecord[] persons) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < 5; i++) {
			if (persons[i].getTime() < min) {
				min = persons[i].getTime();
				index = i;
			}
		}
		if (min != 0 & min != Integer.MAX_VALUE) {
			personRecord p = new personRecord(persons[index].getTime(), persons[index].getInfo());
			persons[index].setTime(Integer.MAX_VALUE);
			return p;
		}
		return null;
	}
	//return the top 5
	private personRecord[] giveMeFive() {
		personRecord[] persons = new personRecord[5];
		for (int i = 0; i < 5; i++) {
			persons[i] = new personRecord(Integer.MAX_VALUE, "");
		}

		String tmpname = "";
		String name = "";
		int time = Integer.MAX_VALUE;
		String timetmp;
		int level = 0;
		String leveltmp = "";

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader("record.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader("record.txt"));

			while ((sCurrentLine = br.readLine()) != null) {

				StringTokenizer words = new StringTokenizer(sCurrentLine, " ");
				words.nextToken();

				tmpname = words.nextToken();

				words.nextToken();
				leveltmp = words.nextToken();

				words.nextToken();
				timetmp = words.nextToken();

				int check = Integer.parseInt(timetmp);
				int min = persons[0].getTime();
				int ii = 0;

				for (int i = 0; i < 5; i++) {
					if (persons[i].getTime() > min) {
						min = persons[i].getTime();
						ii = i;
					}
				}

				if (check < min)
					persons[ii] = new personRecord(check, "name: " + name + " level: " + level + " time: " + check);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
		return persons;
	}
}