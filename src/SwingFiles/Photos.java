package SwingFiles;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Photos {
	public static Image _regularImage;
	public static Image _arrowAttack;
	public static Image _dinoAttack;
	public static Image _gokuAttack;
	public static Image _lavaAttack;
	public static Image _magicAttack;
	public static Image _poisonAttack;
	public static Image _samAttack;
	public static Image _goli1;
	public static Image _goli2;
	public static Image _knight1;
	public static Image _knight2;
	public static Image _naji1;
	public static Image _naji2;
	public static Image _mike1;
	public static Image _mike2;
	public static Image _dino1;
	public static Image _dino2;
	public static Image _arrowPhoto;
	public static Image _gokuPhoto;
	public static Image _lavaPhoto;
	public static Image _magicPhoto;
	public static Image _poisonPhoto;
	public static Image _samPhoto;
	public static Image _lastRoad;
	public static Image _firstRoad;
	public static Image _win;
	public static Image _lose;
	public static Image _poisonMark;
	public static Image _slowMark;

	public Photos() {
		try {
			_firstRoad = ImageIO.read(new File("arrowRoadPhoto.png"));
			_firstRoad = _firstRoad.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_lastRoad = ImageIO.read(new File("finishLine.jpg"));
			_lastRoad = _lastRoad.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_regularImage = ImageIO.read(new File("roadWhite.jpg"));
			_regularImage = _regularImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_arrowAttack = ImageIO.read(new File("arrowBackground.jpg"));
			_arrowAttack = _arrowAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_dinoAttack = ImageIO.read(new File("dinoBackground.jpg"));
			_dinoAttack = _dinoAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_gokuAttack = ImageIO.read(new File("aura.png"));
			_gokuAttack = _gokuAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_lavaAttack = ImageIO.read(new File("lavaBackground.jpg"));
			_lavaAttack = _lavaAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_magicAttack = ImageIO.read(new File("magicBackground.jpg"));
			_magicAttack = _magicAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_poisonAttack = ImageIO.read(new File("poisonBackground.jpg"));
			_poisonAttack = _poisonAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_samAttack = ImageIO.read(new File("samBackground.jpg"));
			_samAttack = _samAttack.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_goli1 = ImageIO.read(new File("guli-1.png"));
			_goli1 = _goli1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_goli2 = ImageIO.read(new File("guli-2.png"));
			_goli2 = _goli2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_knight1 = ImageIO.read(new File("abir-1.png"));
			_knight1 = _knight1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_knight2 = ImageIO.read(new File("abir-2.png"));
			_knight2 = _knight2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_mike1 = ImageIO.read(new File("mike-1.png"));
			_mike1 = _mike1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_mike2 = ImageIO.read(new File("mike-2.png"));
			_mike2 = _mike2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_naji1 = ImageIO.read(new File("naji-1.png"));
			_naji1 = _naji1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_naji2 = ImageIO.read(new File("naji-2.png"));
			_naji2 = _naji2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

			_dino1 = ImageIO.read(new File("dino-1.png"));
			_dino1 = _dino1.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_dino2 = ImageIO.read(new File("dino-2.png"));
			_dino2 = _dino2.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_arrowPhoto = ImageIO.read(new File("arrowPhoto.png"));
			_arrowPhoto = _arrowPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_gokuPhoto = ImageIO.read(new File("gokuPhoto.png"));
			_gokuPhoto = _gokuPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_lavaPhoto = ImageIO.read(new File("lavaPhoto.png"));
			_lavaPhoto = _lavaPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_magicPhoto = ImageIO.read(new File("magicPhoto.png"));
			_magicPhoto = _magicPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_poisonPhoto = ImageIO.read(new File("poisonPhoto.png"));
			_poisonPhoto = _poisonPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_samPhoto = ImageIO.read(new File("samPhoto.png"));
			_samPhoto = _samPhoto.getScaledInstance(25, 30, Image.SCALE_SMOOTH);

			_slowMark = ImageIO.read(new File("slowMark.png"));
			_slowMark = _slowMark.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

			_poisonMark = ImageIO.read(new File("poisonMark.png"));
			_poisonMark = _poisonMark.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}