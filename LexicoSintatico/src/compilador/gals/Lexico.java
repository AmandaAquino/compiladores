package compilador.gals;

public class Lexico implements Constants {
	private int position;
	private String input;

	public Lexico() {
		this("");
	}

	public Lexico(String input) {
		this.setInput(input);
	}

	public void setInput(String input) {
		this.input = input;
		this.setPosition(0);
	}

	public void setPosition(int pos) {
		this.position = pos;
	}

	public Token nextToken() throws LexicalError {
		if (!this.hasInput()) {
			return null;
		}

		int start = this.position;

		int state = 0;
		int lastState = 0;
		int endState = -1;
		int end = -1;

		while (this.hasInput()) {
			lastState = state;
			state = this.nextState(this.nextChar(), state);

			if (state < 0) {
				break;
			} else {
				if (this.tokenForState(state) >= 0) {
					endState = state;
					end = this.position;
				}
			}
		}
		if (endState < 0 || endState != state && this.tokenForState(lastState) == -2) {
			throw new LexicalError(SCANNER_ERROR[lastState], start);
		}

		this.position = end;

		int token = this.tokenForState(endState);

		if (token == 0) {
			return this.nextToken();
		} else {
			String lexeme = this.input.substring(start, end);
			token = this.lookupToken(token, lexeme);
			return new Token(token, lexeme, start);
		}
	}

	private int nextState(char c, int state) {
		int next = SCANNER_TABLE[state][c];
		return next;
	}

	private int tokenForState(int state) {
		if (state < 0 || state >= TOKEN_STATE.length) {
			return -1;
		}

		return TOKEN_STATE[state];
	}

	public int lookupToken(int base, String key) {
		int start = SPECIAL_CASES_INDEXES[base];
		int end = SPECIAL_CASES_INDEXES[base + 1] - 1;

		while (start <= end) {
			int half = (start + end) / 2;
			int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

			if (comp == 0) {
				return SPECIAL_CASES_VALUES[half];
			} else if (comp < 0) {
				start = half + 1;
			} else {
				end = half - 1;
			}
		}

		return base;
	}

	private boolean hasInput() {
		return this.position < this.input.length();
	}

	private char nextChar() {
		if (this.hasInput()) {
			return this.input.charAt(this.position++);
		} else {
			return (char) -1;
		}
	}
}
