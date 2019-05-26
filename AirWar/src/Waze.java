package cr.ac.tec.path_Engine.model.Graph;


public class Waze {
	
	/*
	 *----------------------------------------------------------------------------
	 *                             ATRIBUTES
	 *----------------------------------------------------------------------------
	 */
	private String path;
	private int distance;
	
	/*
	 *----------------------------------------------------------------------------
	 *                         CONSTRUCTOR & GETTERS
	 *----------------------------------------------------------------------------
	 */
	public Waze(String path, int distance) {
		this.path = path;
		this.distance = distance;
	}

	public String getPath() {
		return path;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "{path=" + path + ", distance=" + distance + "}";
	}	
	
	/*
	 *----------------------------------------------------------------------------
	 *                             PROGRAM'S END
	 *----------------------------------------------------------------------------
	 */
}
