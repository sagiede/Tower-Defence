package SwingFiles;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class gameOver extends JFrame implements ActionListener {
	private JButton _nextLevel;
	private JButton _prevLevel;
	private JButton _save;
	private JTextArea _name;
	private JLabel _erorr;
	private BoardGui _board;
	private JLabel _info;
	public boolean _isDone;

	public gameOver(BoardGui board, Boolean isDone) {
		this.setLayout(new FlowLayout());
		this._board = board;
		this._nextLevel = new JButton("next level");
		_nextLevel.setSize(5, 5);
		this._prevLevel = new JButton("prev level");
		_prevLevel.setSize(5, 5);
		this._save = new JButton("save");
		_save.setSize(5, 5);
		this._name = new JTextArea("type name without spaces");
		_name.setSize(50, 50);
		this._erorr = new JLabel();
		_name.setEditable(true);

		this._info = new JLabel();
		this._isDone = isDone;

		this.add(_nextLevel);
		this.add(_prevLevel);
		_info.setText("enemies that died: " + GameTimer._enemyDied + " enemies that pass: " + GameTimer._enemyPass
				+ " time passed: " + GameTimer._time + " hp left:" + Menu._hp);
		_save.addActionListener(this);
		_nextLevel.addActionListener(this);
		_prevLevel.addActionListener(this);
		_save.setFocusable(true);
		_nextLevel.setFocusable(true);
		_prevLevel.setVisible(true);
		_erorr.setVisible(true);
		_info.setVisible(true);
		
		this.add(_info);
		if (isDone == false) {
			_save.setVisible(false);
			_name.setVisible(false);
			this.add(_erorr);
			ImageIcon ii = new ImageIcon("lose.png");
			JLabel lable = new JLabel(ii);
			lable.setSize(50, 50);
			JScrollPane jsp = new JScrollPane(lable);
			this.getContentPane().add(jsp);
		} else {
			this.add(_save);
			this.add(_name);
			this.add(_erorr);
			ImageIcon ii = new ImageIcon("win.png");
			JLabel lable = new JLabel(ii);
			lable.setSize(50, 50);
			JScrollPane jsp = new JScrollPane(lable);
			this.getContentPane().add(jsp);
		}
		this.setSize(500, 500);
		this.setFocusable(true);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//depend the button
		if (e.getSource() == _save) {
			if (_name.getText() == "" | _name.getText().contains(" ")) {
				_erorr.setText("ERROR: Ilegal Char");
			} else {
				write(_name.getText());
				this.dispose();
			}
		} else if (e.getSource() == _prevLevel) {
			_board.getPrevLevel();
			this.dispose();
		} else if (e.getSource() == _nextLevel) {
			_board.getNextLevel();
			this.dispose();
		}

	}
	//write to file the record
	private void write(String text) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = _name.getText();

			fw = new FileWriter("record.txt", true);
			bw = new BufferedWriter(fw);

			bw.write(("name: " + _name.getText() + " level: " + _board._levelNumber + " time: "
					+ GameTimer._time * (20 - Menu._hp + 1)));
			bw.newLine();
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}