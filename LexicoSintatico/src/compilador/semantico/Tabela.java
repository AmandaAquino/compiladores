package compilador.semantico;

import java.util.ArrayList;
import java.util.Stack;

import compilador.gals.Token;

public class Tabela {

	private ArrayList<Item> tabela;
	private int primeiraPosicaoLID;
	private int ultimaPosicaoLID;
	private int NPF;
	private TipoVariavel tipoMetodo;
	private Stack<Item> pilhaIDs;

	public Tabela() {
		this.tabela = new ArrayList<Item>();
		this.pilhaIDs = new Stack<Item>();
	}

	public void addItem(Item identificador) {
		this.pilhaIDs.push(identificador);
		this.tabela.add(identificador);
	}

	public int getUltimaPosicao() {
		return this.tabela.size() - 1;
	}

	public Item getElementoPosicao(int posicao) {
		return this.tabela.get(posicao);
	}

	public void setElementoPosicao(int posicao, Item identificador) {
		this.tabela.set(posicao, identificador);
	}

	public void excluiElemento(int posicao) {
		this.tabela.remove(posicao);
	}

	public int retornaTamanho() {
		return this.tabela.size();
	}

	public Item existe(String nome) {
		System.out.println("Buscando: " + nome + "\n---");
		for (Item i : this.getTabela()) {
			/*
			 * System.out.println("Item: " + i.getNome() + "\nTipo: " + i.getTipo()
			 * + "\nNivel: " + i.getNivel() + "\nDeslocamento: " + i.getDeslocamento());
			 */
			if (nome.equalsIgnoreCase(i.getNome())) {
				return i;
			}
		}

		return null;
	}

	public Item getIdNomePosicaoNivelMenor(String nome, int nivel) {

		for (Item r : this.getTabela()) {
			if (r.getNome().equals(nome)) {
				if (r.getNivel() <= nivel) {
					return r;
				}
			}
		}
		// System.exit(0);
		return null;
	}

	public Item getIdNomePosicao(String nome, int nivel) {
		Item i = new Item();
		for (int x = 0; x < this.tabela.size() - 1; x++) {
			if (this.tabela.get(x).getNome().equals(nome)
					&& this.tabela.get(x).getNivel() == nivel) {
				i = this.tabela.get(x);
			}
		}

		return i;
	}

	public ArrayList<Item> getTabela() {
		return this.tabela;
	}

	public void show() {
		System.out.println(this.tabela.toString());
	}

	public int getPrimeiraPosicaoLID() {
		return this.primeiraPosicaoLID;
	}

	public void setPrimeiraPosicaoLID(int primeiraPosicaoLID) {
		this.primeiraPosicaoLID = primeiraPosicaoLID;
	}

	public int getUltimaPosicaoLID() {
		return this.ultimaPosicaoLID;
	}

	public void setUltimaPosicaoLID(int ultimaPosicaoLID) {
		this.ultimaPosicaoLID = ultimaPosicaoLID;
	}

	public boolean estaDeclaradoNivelMenor(String lexeme, int nivel) {
		for (Item temp : this.tabela) {
			if (temp.getNivel() <= nivel) {
				if (temp.getNome().equals(lexeme)) {
					System.out.println("Comp: " + lexeme + "  "
							+ temp.getNome() + " = TRUE");
					return true;
				}
			}
		}
		return false;
	}

	public boolean estaDeclarado(String lexeme, int nivel) {
		for (Item temp : this.tabela) {
			if (temp.getNivel() == nivel) {
				if (temp.getNome().equals(lexeme)) {
					System.out.println("Comp: " + lexeme + "  "
							+ temp.getNome() + " = TRUE");
					return true;

				}
			}

		}
		return false;
	}

	public boolean identificadorDeclarado(String lexeme, int nivel) {
		boolean resultado = false;

		/*
		 * if (this.indexLexeme.containsKey(lexeme)) { ArrayList<Integer>
		 * indices = this.indexLexeme.get(lexeme); for (Integer i : indices) {
		 * Identificador identificador = this.get(i); if
		 * (identificador.getNivel() <= nivel) { resultado = true; break; } } }
		 */
		return resultado;
	}

	public int getPosicaoIDTS(Token token, int nivel) {
		int posicao = 0;
		for (int i = 0; i < this.tabela.size(); i++) {
			if (this.tabela.get(i).getNome().equals(token.getLexeme())
					&& this.tabela.get(i).getNivel() == nivel) {
				posicao = i;
				break;
			}
		}
		System.out.println("posicao do ID na tabela Simbolo: " + posicao);
		return posicao;
	}

	public int getNPF() {
		return this.NPF;
	}

	public void setNPF(int nPF) {
		this.NPF = nPF;
	}

	public TipoVariavel getTipoMetodo() {
		return this.tipoMetodo;
	}

	public void setTipoMetodo(TipoVariavel tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	public boolean tiposCompativeis(TipoVariavel t1, TipoVariavel t2) {
		boolean compativel = false;
		switch (t1) {
		case INTEIRO:
			compativel = t2 == TipoVariavel.REAL
					|| t2 == TipoVariavel.INTEIRO;
			break;

		case REAL:
			compativel = t2 == TipoVariavel.REAL
					|| t2 == TipoVariavel.INTEIRO;
			break;

		case BOOLEANO:
			compativel = t2 == TipoVariavel.BOOLEANO;
			break;

		case CARACTER:
			compativel = t2 == TipoVariavel.CARACTER;
			break;

		case CADEIA:
			compativel = t2 == TipoVariavel.CADEIA
					|| t2 == TipoVariavel.CARACTER;
			break;
		default:
			break;
		}
		return compativel;
	}

	public Stack<Item> getPilhaIDs() {
		return this.pilhaIDs;
	}

	public void setPilhaIDs(Stack<Item> pilhaIDs) {
		this.pilhaIDs = pilhaIDs;
	}
}
