package compilador.semantico;

public class Item {

	private String nome;
	private int nivel;
	private int deslocamento;
	private TipoVariavel tipo;
	private Categoria tipoCategoria;
	private SubCategoria tipoSubCategoria;
	private String valor;
	private TipoParametro MPP;
	private int tamanho;

	public Item() {

	}

	// id Programa
	public Item(Categoria categoria, String nome) {
		this.tipoCategoria = categoria;
		this.nome = nome;
	}

	// id Constante
	public Item(Categoria categoria, String nome, int nivel,
			TipoVariavel tipoVariavel) {
		this.tipoCategoria = categoria;
		this.nome = nome;
		this.nivel = nivel;
		this.setTipo(tipoVariavel);
	}

	// id variavel
	public Item(Categoria categoria, String nome, int nivel, int deslocamento,
			SubCategoria subcategoria, TipoVariavel tipo) {
		this.tipoCategoria = categoria;
		this.nome = nome;
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.tipoSubCategoria = subcategoria;
		this.tipo = tipo;
	}

	// id metodo
	public Item(Categoria categoria, String nome, int nivel, int deslocamento) {
		this.tipoCategoria = categoria;
		this.nome = nome;
		this.nivel = nivel;
		this.deslocamento = deslocamento;
	}

	// id parametro
	public Item(Categoria categoria, String nome, int nivel) {
		this.tipoCategoria = categoria;
		this.nome = nome;
		this.nivel = nivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(int deslocamento) {
		this.deslocamento = deslocamento;
	}

	public Categoria getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(Categoria tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public SubCategoria getTipoSubCategoria() {
		return tipoSubCategoria;
	}

	public void setTipoSubCategoria(SubCategoria tipoSubCategoria) {
		this.tipoSubCategoria = tipoSubCategoria;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoVariavel getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariavel tipo) {
		this.tipo = tipo;
	}

	public TipoParametro getMPP() {
		return MPP;
	}

	public void setMPP(TipoParametro mPP) {
		MPP = mPP;
	}

	public String toString() {
		return "\n--------------------------\nItem " + "\nNome : " + nome
				+ "\nN�vel : " + nivel + "\nDeslocamento : " + deslocamento
				+ "\nTipo : " + tipo + "\ntipoCategoria : " + tipoCategoria
				+ "\ntipoSubCategoria : " + tipoSubCategoria + "\nValor : "
				+ valor + "\nMPP : " + MPP + "\nTamanho : " + tamanho
				+ "\n--------------------------\n";
	}
}
