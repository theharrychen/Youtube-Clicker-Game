package codes;

public class Video {

	// Variables that are set only once when the object is created
	private double baseCost = 50;
	private int viewsperVid = 1;
	private int subsperVid = 1;

	private static final double MULTIPLIER = 1.10;

	// Variables that change depending on the quantity
	private int numofVideos = 0;
	private int currentCost = (int) Math.round(baseCost * Math.pow(MULTIPLIER, numofVideos));
	private int viewsperSec = numofVideos * viewsperVid;
	private int subsperMin = numofVideos * subsperVid;

	public Video(double baseCost, int viewsperVid, int subsperVid) { // Video Class Constructor
		this.baseCost = baseCost;
		this.viewsperVid = viewsperVid;
		this.subsperVid = subsperVid;
		updatecurrentCost();
		updateviewsperSec();
		updatesubsperMin();
	}

	// Updating video variables
	public void updatecurrentCost() {
		currentCost = (int) Math.round(baseCost * Math.pow(MULTIPLIER, this.numofVideos));
	}

	public void updateviewsperSec() {
		this.viewsperSec = this.numofVideos * this.viewsperVid;
	}

	public void updatesubsperMin() {
		this.subsperMin = this.numofVideos * subsperVid;
	}

	// Incrementing the quantity of a type of video method
	public void addoneVideo() {
		this.numofVideos++;
		updatecurrentCost();
		updateviewsperSec();
		updatesubsperMin();
	}

	// Display object variables
	public int numofVideos() {
		return this.numofVideos;
	}

	public int currentCost() {
		return this.currentCost;
	}

	public long viewsperSec() {
		return this.viewsperSec;
	}

	public long subsperMin() {
		return this.subsperMin;
	}

	public long viewsperVid() {
		return this.viewsperVid;
	}

	public long subsperVid() {
		return this.subsperVid;
	}

}
