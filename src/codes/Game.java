package codes;

/*
 * @author Harry Chen
 *	June 15, 2017
 *	Western Canada High School
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {

	static String channelName = "PewDiePie";

	// TODO: Use BigInteger Class to replace the long variable
	// in order to extend how high the views can go.
	static long currentViews = 0L; // Main Currency - can go up 9 quintillion
	static long lifetimeViews = 0L, subscribers = 0L;
	static int playbuttonClicks = 0;

	// Video Objects - baseCost, viewspervid, subspervid
	static Video vlog = new Video(100, 1, 1);
	static Video cat = new Video(1100, 8, 26);
	static Video gaming = new Video(12000, 47, 125);
	static Video howto = new Video(130000, 260, 525);
	static Video comedy = new Video(14000000, 1400, 2750);
	static Video viral = new Video(50000000, 1000000, 2500000);

	public static long viewRate() {// Calculates total views/second rate
		long viewsperSec = vlog.viewsperSec() + cat.viewsperSec() + howto.viewsperSec() + gaming.viewsperSec()
				+ comedy.viewsperSec() + viral.viewsperSec();

		return viewsperSec;
	}

	public static long subRate() {// Calculates subs/second rate
		long subsperMin = vlog.subsperMin() + cat.subsperMin() + howto.subsperMin() + gaming.subsperMin()
				+ comedy.subsperMin() + viral.subsperMin();

		return subsperMin;
	}

	public static long clickRate() {
		double calculate = Math.round((double) subscribers / 50);
		long viewsperClick = 1L + (long) calculate;
		return viewsperClick;
	}

	// WindowFrame Constructor - creates the main window
	static WindowFrame frame = new WindowFrame(1024, 576, "Youtube Clicker");

	// Swing Variables - for the window
	static JLabel channelLabel = new JLabel(channelName + "'s Channel");

	static JLabel viewLabel = new JLabel(currentViews + " Views");
	static JLabel subLabel = new JLabel(subscribers + " Subscribers");
	static JLabel viewrateLabel = new JLabel(viewRate() + " Views per Second");
	static JLabel subrateLabel = new JLabel(subRate() + " Subscribers per Minute");
	static JLabel clickrateLabel = new JLabel(clickRate() + " Views per Click");

	static JLabel creatorStudio = new JLabel("CREATOR STUDIO");
	static JLabel studioMessage = new JLabel();

	static JLabel channelNews = new JLabel("You feel like making videos. But no one wants to watch them.");
	static JLabel analyticsTitle = new JLabel("Analytics");
	static JLabel analytics = new JLabel("<html>Lifetime Views: " + lifetimeViews + "<br>Play Button Clicks: "
			+ playbuttonClicks + "<br>Vlogs: " + vlog.numofVideos() + "<br>Cat Videos: " + cat.numofVideos()
			+ "<br>Gaming Videos " + gaming.numofVideos() + "<br>How-To Videos: " + howto.numofVideos()
			+ "<br>Comedy Videos: " + comedy.numofVideos() + "<br>Viral Videos: " + viral.numofVideos());
	static JLabel achieveTitle = new JLabel("Achievements");

	// CREATE VIDEO BUTTONS
	static JButton vlogButton = new JButton("Vlog Video | " + vlog.currentCost() + " Views"); // Constructs a JButton
	static JButton catButton = new JButton("Cat Video | " + cat.currentCost() + " Views");
	static JButton gameButton = new JButton("Gaming Video | " + gaming.currentCost() + " Views");
	static JButton howtoButton = new JButton("How-To Video | " + howto.currentCost() + " Views");
	static JButton comedyButton = new JButton("Comedy Video | " + comedy.currentCost() + " Views");
	static JButton viralButton = new JButton("Viral Video | " + viral.currentCost() + " Views");

	public static void updatelabelStats() {// Updates labels on the window
		viewLabel.setText(currentViews + " Views");
		subLabel.setText(subscribers + " Subscribers");
		viewrateLabel.setText(viewRate() + " Views per Second");
		subrateLabel.setText(subRate() + " Subscribers per Minute");
		clickrateLabel.setText(clickRate() + " Views per Click");

		analytics.setText("<html>Lifetime Views: " + lifetimeViews + "<br>PlayButton Clicks: " + playbuttonClicks
				+ "<br>Vlogs: " + vlog.numofVideos() + "<br>Cat Videos: " + cat.numofVideos() + "<br>Gaming Videos "
				+ gaming.numofVideos() + "<br>How-To Videos: " + howto.numofVideos() + "<br>Comedy Videos: "
				+ comedy.numofVideos() + "<br>Viral Videos: " + viral.numofVideos());

		vlogButton.setText("Vlog Video | " + vlog.currentCost() + " Views");
		catButton.setText("Cat Video | " + cat.currentCost() + " Views");
		gameButton.setText("Gaming Video | " + gaming.currentCost() + " Views");
		howtoButton.setText("How-To Video | " + howto.currentCost() + " Views");
		comedyButton.setText("Comedy Video | " + comedy.currentCost() + " Views");
		viralButton.setText("Viral Video | " + viral.currentCost() + " Views");
	}

	static int mincounter = 0;// Minute timer for Subs/min

	static JPanel centerPanel = new JPanel();

	public static int rng(int min, int max) { // Random Number Generator Method
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		int number = (int) (Math.random() * (max - min + 1) + min);
		return number;
	}

	// News Ticker array
	static final int LISTSIZE = 24;
	static String[] newslist = new String[LISTSIZE];
	static int messageTimer = 0;

	public static void newstoArray() throws IOException {// Imports all channel messages to an array
		InputStream stream = Game.class.getResourceAsStream("/gamefiles/news.txt");
		if (stream == null)
			JOptionPane.showMessageDialog(null, "Resource not located.");

		Scanner input = null;
		try {
			input = new Scanner(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Scanner error");
		}

		// while (input.hasNextLine()) JOptionPane.showMessageDialog(null,
		// input.nextLine());
		for (int x = 0; x < LISTSIZE; x++) {
			newslist[x] = input.nextLine();
		}

	}

	public static void changeNews() {
		if (lifetimeViews <= 200) {
			channelNews.setText(newslist[rng(0, 3)]);
		} else if (lifetimeViews > 200 && lifetimeViews <= 1000) {
			channelNews.setText(newslist[rng(4, 7)]);
		} else if (lifetimeViews > 1000 && lifetimeViews <= 50000) {
			channelNews.setText(newslist[rng(8, 11)]);
		} else if (lifetimeViews > 50000 && lifetimeViews <= 500000) {
			channelNews.setText(newslist[rng(12, 15)]);
		} else if (lifetimeViews > 500000 && lifetimeViews <= 5000000) {
			channelNews.setText(newslist[rng(12, 19)]);
		} else if (lifetimeViews > 5000000) {
			channelNews.setText(newslist[rng(16, 23)]);
		}
	}

	public static void main(String[] args) throws IOException { // MAIN METHOD BEGINS
		// GAME INTRODUCTION
		JOptionPane.showMessageDialog(null,
				"You have decided to start a Youtube Channel.\n" + "Will you rise to the top?\n ~Harry Chen June 2017",
				"Tutorial", JOptionPane.INFORMATION_MESSAGE);

		channelName = JOptionPane.showInputDialog("What is the name of your Youtube Channel?");
		if (channelName == null || channelName.equals("")) {
			channelName = "PewDiePie";
		}
		channelLabel.setText(channelName + "'s Channel");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);

		// TOP PANEL SETUP
		JPanel topPanel = new JPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		channelLabel.setFont(new Font("Roboto", Font.BOLD, 33));
		channelLabel.setForeground(Color.BLACK);
		channelNews.setFont(new Font("Roboto", Font.BOLD, 14));
		channelNews.setForeground(Color.BLACK);

		channelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		channelNews.setAlignmentX(Component.CENTER_ALIGNMENT);

		topPanel.add(channelLabel);
		topPanel.add(channelNews);
		newstoArray();
		// ticker

		// LEFT PANEL SETUP
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel, BorderLayout.WEST);

		viewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		subLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		viewrateLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		subrateLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		clickrateLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(viewLabel);
		leftPanel.add(subLabel);
		leftPanel.add(viewrateLabel);
		leftPanel.add(subrateLabel);
		leftPanel.add(clickrateLabel);

		// MAIN BUTTON SET UP
		JButton mainButton = new JButton(); // Constructs a JButton
		mainButton.setVisible(false);
		leftPanel.add(mainButton); // Adds button to left panel

		// Image re-scaling for the main button
		URL url = Game.class.getResource("/gamefiles/playbutton.png");
		ImageIcon play = new ImageIcon(url);
		Image img = play.getImage();
		Image rescaledimg = img.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
		play.setImage(rescaledimg);

		ImageIcon clickedPlay = new ImageIcon(url);
		img = play.getImage();
		rescaledimg = img.getScaledInstance(295, 195, java.awt.Image.SCALE_SMOOTH);
		clickedPlay.setImage(rescaledimg);

		mainButton.setIcon(play);// Sets the default button
		mainButton.setPressedIcon(clickedPlay);// Sets the clicked button
		frame.setIconImage(play.getImage());// Sets the symbol for the Window

		// Gets rid of the default button image
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setFocusPainted(false);
		mainButton.setOpaque(false);
		mainButton.setVisible(true);

		mainButton.addActionListener(new ActionListener() { // Listens for a button click
			public void actionPerformed(ActionEvent e) {
				// Code executed when button is clicked
				playbuttonClicks++;
				currentViews += clickRate();
				lifetimeViews += clickRate();
				viewLabel.setText(currentViews + " Views");
				// TODO lifetimeviews label update

			}
		});

		// RIGHT PANEL SETUP
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel, BorderLayout.EAST);

		creatorStudio.setFont(new Font("Roboto", Font.BOLD, 25));
		studioMessage.setFont(new Font("Roboto", Font.PLAIN, 15));

		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(creatorStudio);
		rightPanel.add(studioMessage);

		// START OF CONFIGURING VIDEO BUTTONS
		vlogButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		vlogButton.setToolTipText("<html>A video on your daily life. <br>Generates " + vlog.viewsperVid()
				+ " View/s and " + vlog.subsperVid() + " Sub/s");
		vlogButton.setVisible(true);
		rightPanel.add(vlogButton);
		vlogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= vlog.currentCost()) { // Creating video conditions
					currentViews -= vlog.currentCost();
					vlog.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < vlog.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		catButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		catButton.setToolTipText("<html>MeeoOW! Adorable kitties. <br>Generates " + cat.viewsperVid() + " View/s and "
				+ cat.subsperVid() + " Sub/s");
		catButton.setVisible(false);
		rightPanel.add(catButton);
		catButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= cat.currentCost()) { // Creating video conditions
					currentViews -= cat.currentCost();
					cat.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < cat.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		gameButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		gameButton.setToolTipText("<html>Hey guys, <insertname> here, today... <br>Generates " + gaming.viewsperVid()
				+ " View/s and " + gaming.subsperVid() + " Sub/s");
		gameButton.setVisible(false);
		rightPanel.add(gameButton);
		gameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= gaming.currentCost()) { // Creating video conditions
					currentViews -= gaming.currentCost();
					gaming.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < gaming.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		howtoButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		howtoButton.setToolTipText("<html>Teach people stuff ;D... <br>Generates " + howto.viewsperVid()
				+ " View/s and " + howto.subsperVid() + " Sub/s");
		howtoButton.setVisible(false);
		rightPanel.add(howtoButton);
		howtoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= howto.currentCost()) { // Creating video conditions
					currentViews -= howto.currentCost();
					howto.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < howto.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		comedyButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		comedyButton.setToolTipText("<html>Jokes and satirical sketches. <br>Generates " + comedy.viewsperVid()
				+ " View/s and " + comedy.subsperVid() + " Sub/s");
		comedyButton.setVisible(false);
		rightPanel.add(comedyButton);
		comedyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= comedy.currentCost()) { // Creating video conditions
					currentViews -= comedy.currentCost();
					comedy.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < comedy.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		viralButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		viralButton.setToolTipText("<html>Jokes and satirical sketches. <br>Generates " + viral.viewsperVid()
				+ " View/s and " + viral.subsperVid() + " Sub/s");
		viralButton.setVisible(false);
		rightPanel.add(viralButton);
		viralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentViews >= viral.currentCost()) { // Creating video conditions
					currentViews -= viral.currentCost();
					viral.addoneVideo();
					updatelabelStats();
					studioMessage.setText("Video Successfully Uploaded!");
				} else if (currentViews < viral.currentCost()) {
					studioMessage.setText("Insufficient Funds!");
				}
			}
		});

		// END OF CREATE VIDEO BUTTONS

		// CENTER PANEL SETUP
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		analyticsTitle.setFont(new Font("Roboto", Font.BOLD, 20));
		analytics.setFont(new Font("Roboto", Font.PLAIN, 15));
		achieveTitle.setFont(new Font("Roboto", Font.BOLD, 20));

		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(analyticsTitle);
		centerPanel.add(analytics);
		centerPanel.add(achieveTitle);

		frame.setVisible(true);// Sets the window to be visible

		// SWING TIMER - Executes code once per second
		Runnable runner = new Runnable() {
			public void run() {
				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						currentViews += viewRate();
						lifetimeViews += viewRate();

						mincounter++;// Minute counter for Sub/Min
						if (mincounter % 60 == 0) {
							mincounter = 0;
							subscribers += subRate();
						}

						messageTimer++;
						if (messageTimer % 8 == 0) { // Changes the news every 8 seconds
							messageTimer = 0;
							changeNews();
						}

						updatelabelStats();

						if (allbuttonsrevealed == false) {// Reveals videos depending on lifetime views
							checkrevealButtons();
						}

						if (allplaques == false) {// Checks for Achievements
							checkAchievements();

							if (silverPlaque.isVisible() == false) {
								silverPlaque.setVisible(true);
							}
							if (goldPlaque.isVisible() == false) {
								goldPlaque.setVisible(true);
							}
							if (diamondPlaque.isVisible() == false) {
								diamondPlaque.setVisible(true);
							}
							if (rubyPlaque.isVisible() == false) {
								rubyPlaque.setVisible(true);
							}
						}
					}
				};
				Timer timer = new Timer(1000, actionListener);
				timer.start();
			}
		};
		EventQueue.invokeLater(runner);

	}

	static boolean allbuttonsrevealed = false;

	public static void checkrevealButtons() { // Reveals buttons at certain milestones
		if (lifetimeViews >= 50) {
			catButton.setVisible(true);
		}

		if (lifetimeViews >= 250) {
			gameButton.setVisible(true);
		}

		if (lifetimeViews >= 2000) {
			howtoButton.setVisible(true);
		}

		if (lifetimeViews >= 50000) {
			comedyButton.setVisible(true);
		}

		if (lifetimeViews >= 200000) {
			viralButton.setVisible(true);
			allbuttonsrevealed = true;
		}
	}

	static boolean silver = false, gold = false, diamond = false, ruby = false, allplaques = false;
	static JLabel silverPlaque = new JLabel("100,000 Subscriber Silver Play Button");
	static JLabel goldPlaque = new JLabel("1 Million Subscriber Gold Play Button");
	static JLabel diamondPlaque = new JLabel("10 Million Subscriber Diamond Play Button");
	static JLabel rubyPlaque = new JLabel("50 Million Subscriber Ruby Play Button");

	public static void checkAchievements() {// Checks to see if the Channel qualifies for awards
		if (subscribers >= 100000 && silver == false) {
			URL url = Game.class.getResource("/gamefiles/silver.png");
			ImageIcon silvericon = new ImageIcon(url);
			Image img = silvericon.getImage();
			Image rescaledimg = img.getScaledInstance(100, 66, java.awt.Image.SCALE_SMOOTH);
			silvericon.setImage(rescaledimg);

			silverPlaque.setIcon(silvericon);// Sets the icon
			centerPanel.add(silverPlaque);
			silverPlaque.setEnabled(true);
			silverPlaque.setVisible(false);

			JOptionPane.showMessageDialog(null,
					"Congratulations " + channelName + " on surpassing 100,000 Subscribers!\n"
							+ "Youtube has sent you a Silver Play Button to merit this amazing accomplishment!",
					"Achievement Unlocked!", JOptionPane.INFORMATION_MESSAGE);
			silver = true;

		} else if (subscribers >= 1000000 && gold == false) {
			URL url = Game.class.getResource("/gamefiles/gold.png");
			ImageIcon goldicon = new ImageIcon(url);
			Image img = goldicon.getImage();
			Image rescaledimg = img.getScaledInstance(100, 66, java.awt.Image.SCALE_SMOOTH);
			goldicon.setImage(rescaledimg);

			goldPlaque.setIcon(goldicon);// Sets the icon
			centerPanel.add(goldPlaque);
			goldPlaque.setEnabled(true);
			goldPlaque.setVisible(false);

			JOptionPane.showMessageDialog(null,
					"Congratulations " + channelName + " on surpassing 1,000,000 Subscribers!\n"
							+ "Youtube has sent you a Gold Play Button to merit this amazing accomplishment!",
					"Achievement Unlocked!", JOptionPane.INFORMATION_MESSAGE);
			gold = true;
		} else if (subscribers >= 10000000 && diamond == false) {
			URL url = Game.class.getResource("/gamefiles/diamond.png");
			ImageIcon diamondicon = new ImageIcon(url);
			Image img = diamondicon.getImage();
			Image rescaledimg = img.getScaledInstance(100, 66, java.awt.Image.SCALE_SMOOTH);
			diamondicon.setImage(rescaledimg);

			diamondPlaque.setIcon(diamondicon);// Sets the icon
			centerPanel.add(diamondPlaque);
			diamondPlaque.setEnabled(true);
			diamondPlaque.setVisible(false);

			JOptionPane.showMessageDialog(null,
					"Congratulations " + channelName + " on surpassing 10,000,000 Subscribers!\n"
							+ "Youtube has sent you a Diamond Play Button to merit this amazing accomplishment!",
					"Achievement Unlocked!", JOptionPane.INFORMATION_MESSAGE);
			diamond = true;
		} else if (subscribers >= 50000000 && ruby == false) {
			URL url = Game.class.getResource("/gamefiles/ruby.png");
			ImageIcon rubyicon = new ImageIcon(url);
			Image img = rubyicon.getImage();
			Image rescaledimg = img.getScaledInstance(100, 66, java.awt.Image.SCALE_SMOOTH);
			rubyicon.setImage(rescaledimg);

			rubyPlaque.setIcon(rubyicon);// Sets the icon
			centerPanel.add(rubyPlaque);
			rubyPlaque.setEnabled(true);
			rubyPlaque.setVisible(false);

			JOptionPane.showMessageDialog(null,
					"Congratulations " + channelName + " on surpassing 50,000,000 Subscribers!\n"
							+ "Youtube has sent you a Ruby Play Button to merit this amazing accomplishment!",
					"Achievement Unlocked!", JOptionPane.INFORMATION_MESSAGE);
			ruby = true;
		}

		if (silver == true && gold == true && diamond == true && ruby == true) {
			allplaques = true;
		}
	}

}
