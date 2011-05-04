import java.awt.geom.Rectangle2D;


	public class TextToDisplay extends Rectangle2D.Float {

		/*
		 * 
		 */
		private static final long serialVersionUID = 2750632610482025427L;
		
		/**
		 * The string/text 
		 */
		private boolean hasNext;
		private String text;

		/**
		 * 
		 * @param x x coordinate for the text
		 * @param y y coordinate for the text
		 * @param width width of object containing the text
		 * @param height height of object containing the text
		 */
		public TextToDisplay() {
		}

		/**
		 * Set the string/text for the object. used to update and set for first time.
		 * @param tmp string which the object will contain.
		 */
		public void setString(String tmp) {
			/*
			 * if(tmp.length()>20){ tmp=new StringBuffer(tmp).insert(20,
			 * "<BR>").toString(); }
			 */ 
			this.text = tmp;
		}

		/**
		 * counts the length of the string
		 * @return returns the length of the string in int, null if no string
		 */
		public int length() {
			return text.length();
		}

		/**
		 * Gets the string
		 * @return returns the string, null if no string
		 */
		public String getString() {
			return text;
		}

		/**
		 * Adds specified value to current x-coordinates.
		 * @param x float to increase current x with, "-" to subtract x.
		 */
		public void addX(float x) {
			this.x += x;
		}

		/**
		 * Adds specified value to current y-coordinates.
		 * @param y float to increase current y with, "-" to subtract y.
		 */
		public void addY(float y) {
			this.y += y;
		}

		/**
		 * Adds specified value to current Width.
		 * @param Width float to increase current Width with, "-" to subtract Width.
		 */
		public void addWidth(float w) {
			this.width += w;
		}

		/**
		 * Adds specified value to current height value.
		 * @param height float to increase current height with, "-" to subtract height
		 */
		public void addHeight(float h) {
			this.height += h;
		}

		/**
		 * resets the position to (0,0).
		 */
		public void resetPos() {
			this.y = 0;
			this.x = 0;
		}
		
		public void setHasNext(boolean in){
			hasNext = in;
		}
		
		public boolean hasNext(){
			return hasNext;
		}

	}