package compilador.semantico;

import java.util.ArrayList;
import java.util.Stack;

public class AnalisadorSemantico {

	/* enums */
	
	private TipoFator tipoFator;
	private TipoParametro tipoParametro;
	//private SubCategoria subCategoria;
	private ContextoLID contextoLid;


	/* pilhas */
	private Stack<Integer> pilhaDeslocamento;

	/* atributos gerais */
	private int nivelAtual;
	private int deslocamento;
	private int NPF;

	private ArrayList<Item> lid;

	/* quantidade de ids */

	public AnalisadorSemantico() {
		this.pilhaDeslocamento = new Stack<Integer>();
		this.lid = new ArrayList<Item>();
	}

	public TipoFator getTipoFator() {
		return tipoFator;
	}

	public void setTipoFator(TipoFator tipoFator) {
		this.tipoFator = tipoFator;
	}

	public TipoParametro getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametro tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public Stack<Integer> getPilhaDeslocamento() {
		return pilhaDeslocamento;
	}

	public void setPilhaDeslocamento(Stack<Integer> pilhaDeslocamento) {
		this.pilhaDeslocamento = pilhaDeslocamento;
	}

	public int getNivelAtual() {
		return nivelAtual;
	}

	public void setNivelAtual(int nivelAtual) {
		this.nivelAtual = nivelAtual;
	}

	public void setDeslocamento(int deslocamentoAtual) {
		this.deslocamento = deslocamentoAtual;
	}

	public int getDeslocamento() {
		return deslocamento;
	}

	public void addDeslocamento(int deslocamento) {
		this.pilhaDeslocamento.push(deslocamento);
	}

	/*public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}*/

	public ContextoLID getContextoLid() {
		return contextoLid;
	}

	public void setContextoLid(ContextoLID contextoLid) {
		this.contextoLid = contextoLid;
	}

	public ArrayList<Item> getLid() {
		return lid;
	}

	public void setLid(ArrayList<Item> lid) {
		this.lid = lid;
	}

	public int getNPF() {
		return NPF;
	}

	public void setNPF(int nPF) {
		NPF = nPF;
	}
	

}