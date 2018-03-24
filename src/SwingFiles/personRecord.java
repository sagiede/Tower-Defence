package SwingFiles;

public class personRecord {

	private int _time;
	private String _info;

	public personRecord(int time, String info) {
		this._time = time;
		this._info = info;
	}

	public int getTime() {
		return _time;
	}

	public String getInfo() {
		return _info;
	}

	public void setInfo(String info) {
		this._info = info;
	}

	public void setTime(int time) {
		this._time = time;
	}
}
