package bridgeit.cabservice;

public class CabRide {

	int distance;
	int time;
	int cost;
	int minimumFare = 5;

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public CabRide(int distance, int time, String type) {
		this.distance = distance;
		this.time = time;
		if (type == "Normal") {
			if (distance > 0) {
				this.cost = (distance * 10) + time;
			} else {
				this.cost = minimumFare + time;
			}
		} else if (type == "Premium") {
			if (distance > 0) {
				this.cost = (distance * 15) + 2 * time;
			} else {
				this.cost = minimumFare + 2 * time;
			}
		}
	}

	@Override
	public String toString() {
		return distance + "," + time + "," + cost;
	}

}
