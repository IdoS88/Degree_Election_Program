package Model;

import java.io.Serializable;

public class ElectionException extends Exception implements Serializable {
	 public ElectionException(String msg) {
			super(msg);

		}
		public ElectionException() {
			super("general exception faild to run program");
		}
}
