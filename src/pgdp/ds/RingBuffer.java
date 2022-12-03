package pgdp.ds;

import java.util.Arrays;

public class RingBuffer {

	private int[] mem;
	private int in;
	private int out;
	private int stored;

	RingBuffer(int capacity) {
		mem = new int[capacity];
		in = 0;
		out = 0;
		stored = 0;
	}

	// TODO implement missing methods
	public boolean isEmpty() {
		if (stored == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isFull() {
		if (stored < mem.length && mem.length > 0) {
			return false;
		}
		else if (stored >= mem.length - 1 && mem.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean put(int value) {
		if (stored != mem.length - 1) {//Wenn nicht voll
			mem[in] = value;
			stored += 1;
			return true;
		}
		else {
			return false;
		}
	}
	public int get() {
		if (!isFull()) {//Wenn nicht voll
			int oldestValue = mem[out];
			stored -= 1;
			return oldestValue;
		}
		else {
			return Integer.MAX_VALUE;
		}
	}




	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RingBuffer := { capacity = ").append(mem.length).append(", out = ").append(out).append(", in = ")
				.append(in).append(", stored = ").append(stored).append(", mem = ").append(Arrays.toString(mem))
				.append(", buffer = [");
		if (!isEmpty()) {
			if(in >= 0 || in < mem.length) {
				int i = out;
				do {
					sb.append(mem[i]).append(", ");
					i = (i + 1) % mem.length;
				} while (i != in);
				sb.setLength(sb.length() - 2);
			} else {
				sb.append("Error: Field 'in' is <").append(in).append(">, which is out of bounds for an array of length ").append(mem.length);
			}
		}
		sb.append("] }");
		return sb.toString();
	}
}
