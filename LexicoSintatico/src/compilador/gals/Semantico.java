package compilador.gals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

import compilador.semantico.Categoria;
import compilador.semantico.ContextoLID;
import compilador.semantico.Item;
import compilador.semantico.Operador;
import compilador.semantico.OperadorComparacao;
import compilador.semantico.SubCategoria;
import compilador.semantico.Tabela;
import compilador.semantico.TipoParametro;
import compilador.semantico.TipoVariavel;

public class Semantico implements Constants {
	private Tabela ts = new Tabela();
	private Categoria categoriaAtual;
	private SubCategoria subCategoria;
	private TipoVariavel tipoConstante;
	private TipoVariavel tipoAtual;
	private Integer numElementos;
	private String valConst;
	private int nivelAtual;
	private int deslocamentoAtual;
	private Stack<Integer> pilhaDeslocamento = new Stack<Integer>();
	private ContextoLID contextoLid;
	private TipoParametro MPP;// mecanismo de passagem de parametros
	private ArrayList<Item> listaPar = new ArrayList<Item>();
	private int NPF; // numero de parametros formais
	private TipoVariavel tipoMetodo;
	private int posID; // posi��o ID na TS
	private TipoVariavel tipoLadoEsquerdo;
	private boolean opNega;
	private TipoVariavel tipoFator;
	private boolean OpUnario;
	private TipoVariavel tipoExpressao;
	private TipoVariavel tipoVariavel;
	private int NPA; // numero parametros atuais
	private ContextoLID contextoEXPR;
	private int quantidadeIDs; // a��o 121 e 122
	private Stack<TipoVariavel> tipoTermo = new Stack<TipoVariavel>();
	private Operador operadorAtual;
	private Stack<TipoVariavel> tipoExpressaoSimples = new Stack<TipoVariavel>();
	private OperadorComparacao operadorComparacao;
	private TipoVariavel tipoResultadoFuncao;
	private SubCategoria tipoVarIndexada;
	private Stack<Item> pilhaMetodos = new Stack<Item>();

	/*
	 * #101 � Inicializa com zero n�vel atual (NA) e deslocamento - Insere id na
	 * TS juntamente com seus atributos categoria (id-programa) e n�vel ( NA )
	 */
	public void acao101(Token token) {
		this.nivelAtual = 0;
		this.deslocamentoAtual = 0;
		this.pilhaDeslocamento.add(0);

		Item identificadorAtual = new Item(Categoria.PROGRAMA,
				token.getLexeme());
		this.ts.addItem(identificadorAtual);
	}

	/*
	 * #102 � seta contextoLID para �decl� Guarda pos. na TS do primeiro id da
	 * lista
	 */
	public void acao102(Token token) {
		this.contextoLid = ContextoLID.DECLARACAO;
		Item novo = new Item(Categoria.VARIAVEL, token.getLexeme(), this.nivelAtual,
				this.deslocamentoAtual);
		this.ts.addItem(novo);
		this.ts.setPrimeiraPosicaoLID(0);
	}

	/*
	 * #103 � Guarda pos. na TS do �ltimo id da lista
	 */

	public void acao103(Token token) {
		this.ts.setUltimaPosicaoLID(this.ts.getTabela().size() - 1);
	}

	/*
	 * #104 � Atualiza atributos dos id de <lid> de acordo com a CategoriaAtual
	 * e com a SubCategoria. Para c�lculo do Deslocamento de vari�veis,
	 * considere que toda vari�vel ocupa 1 c�lula de mem�ria (exceto vetor que
	 * ocupa 1 c�lula para cada elemento)
	 */
	public void acao104(Token token) {

		for (int i = this.ts.getPrimeiraPosicaoLID() + 1; i < this.ts.getTabela().size(); i++) {
			this.ts.getTabela().get(i).setTipoCategoria(this.categoriaAtual);
			this.ts.getTabela().get(i).setTipoSubCategoria(this.subCategoria);
			this.ts.getTabela().get(i).setTipo(this.tipoAtual);

			if (this.ts.getTabela().get(i).getTipoCategoria() == Categoria.VARIAVEL
					&& this.ts.getTabela().get(i).getTipoSubCategoria() == SubCategoria.VETOR) {
				this.pilhaDeslocamento.add(this.numElementos);
				this.deslocamentoAtual = this.numElementos;
			} else {
				this.pilhaDeslocamento.add(1);
				this.deslocamentoAtual++;
			}
		}
		/*
		 * for (Item atual : ts.getTabela()) {
		 * atual.setTipoCategoria(categoriaAtual);
		 * atual.setTipoSubCategoria(subCategoria); atual.setTipo(tipoAtual);
		 *
		 * if (atual.getTipoCategoria() == Categoria.VARIAVEL &&
		 * atual.getTipoSubCategoria() == SubCategoria.VETOR) {
		 * pilhaDeslocamento.add(numElementos); deslocamentoAtual =
		 * numElementos; } else { pilhaDeslocamento.add(1); deslocamentoAtual++;
		 * } }
		 */
	}

	/*
	 * #105 � TipoAtual := �inteiro�
	 */
	public void acao105(Token token) {
		this.setTipoAtual(TipoVariavel.INTEIRO);
	}

	/*
	 * #106 � TipoAtual := �real�
	 */
	public void acao106(Token token) {
		this.setTipoAtual(TipoVariavel.REAL);
	}

	/*
	 * #107 � TipoAtual := �booleano�
	 */
	public void acao107(Token token) {
		this.setTipoAtual(TipoVariavel.BOOLEANO);
	}

	public void acao108(Token token) {
		this.setTipoAtual(TipoVariavel.CARACTER);
	}

	public void acao109(Token token) throws SemanticError {
		if (this.tipoConstante != TipoVariavel.INTEIRO) {
			throw new SemanticError(
					"#109 [ 1 ] - Esperava-se uma constante inteira",
					token.getPosition());
		} else if (Integer.parseInt(this.valConst) > 256) {
			throw new SemanticError("#109 [ 2 ] Cadeia > que o permitido",
					token.getPosition());
		} else {
			this.tipoAtual = TipoVariavel.CADEIA;
		}
	}

	public void acao110(Token token) throws SemanticError {
		if (this.getTipoAtual() == TipoVariavel.CADEIA) {
			throw new SemanticError(
					"#110 Vetor do tipo cadeia n�o � permitido",
					token.getPosition());
		} else {
			this.subCategoria = SubCategoria.VETOR;
		}
	}

	public void acao111(Token token) throws SemanticError {
		if (this.tipoConstante != TipoVariavel.INTEIRO) {
			throw new SemanticError(
					"#111 - A dimens�o deve ser uma constante inteira",
					token.getPosition());
		} else {
			this.numElementos = Integer.parseInt(this.valConst);
		}
	}

	public void acao112(Token token) throws SemanticError {
		if (this.getTipoAtual() == TipoVariavel.CADEIA) {
			this.subCategoria = SubCategoria.CADEIA;
		} else {
			this.subCategoria = SubCategoria.PREDEFINIDO;
		}
	}

	public void acao113(Token token) throws SemanticError {
		if (this.contextoLid == ContextoLID.DECLARACAO) {
			if (this.ts.estaDeclarado(token.getLexeme(), this.nivelAtual)) {
				throw new SemanticError("#113 [ 1 ] - Id j� declarado",
						token.getPosition());
			} else {
				Item novo = new Item(Categoria.VARIAVEL, token.getLexeme(),
						this.nivelAtual, this.deslocamentoAtual,
						SubCategoria.PREDEFINIDO, this.tipoAtual);
				this.ts.addItem(novo);
			}

		} else if (this.contextoLid == ContextoLID.PARAMETRO_FORMAL) {

			if (this.ts.estaDeclarado(token.getLexeme(), this.nivelAtual)) {
				throw new SemanticError("#113 [ 2 ] Id par�metro repetido",
						token.getPosition());
			} else {
				Item novo = new Item(Categoria.VARIAVEL, token.getLexeme(),
						this.nivelAtual, this.deslocamentoAtual,
						SubCategoria.PREDEFINIDO, this.tipoAtual);
				novo.setNome(token.getLexeme());
				this.ts.addItem(novo);
				this.NPF++;
			}

		} else if (this.contextoLid == ContextoLID.LEITURA) {
			if (!this.ts.estaDeclaradoNivelMenor(token.getLexeme(), this.nivelAtual)) {
				throw new SemanticError("#113 [ 3 ] Id n�o declarado",
						token.getPosition());
			} else {
				Item id = this.ts.getIdNomePosicaoNivelMenor(token.getLexeme(),
						this.nivelAtual);
				if ((id.getTipoCategoria() == Categoria.VARIAVEL || id
						.getTipoCategoria() == Categoria.PARAMETRO)
						&& id.getTipoSubCategoria() == SubCategoria.PREDEFINIDO
						&& id.getTipo() != TipoVariavel.BOOLEANO && id
								.getTipo() != TipoVariavel.CADEIA) {
					/* gera codigo para leitura */

				} else {
					throw new SemanticError(
							"#113 [ 4 ] Tipo Invalido para leitura",
							token.getPosition());
				}
			}
		}
	}

	public void acao114(Token token) throws SemanticError {
		if (this.subCategoria == SubCategoria.CADEIA
				|| this.subCategoria == SubCategoria.VETOR) {
			throw new SemanticError(
					"#114 - Apenas id de tipo pr�-def podem ser declarados como constante",
					token.getPosition());
		} else {
			this.categoriaAtual = Categoria.CONSTANTE;
		}

	}

	public void acao115(Token token) throws SemanticError {
		if (this.tipoConstante != this.tipoAtual) {
			throw new SemanticError("#115 - Tipo da constante incorreto",
					token.getPosition());
		}
	}

	public void acao116(Token token) {
		this.categoriaAtual = Categoria.VARIAVEL;
	}

	public void acao117(Token token) throws SemanticError {
		if (this.ts.estaDeclarado(token.getLexeme(), this.nivelAtual)) {
			throw new SemanticError("#117 - Id j� declarado",
					token.getPosition());
		} else {

			Item novo = new Item(Categoria.METODO, token.getLexeme(),
					this.nivelAtual);
			this.ts.addItem(novo);
			this.NPF = 0;
			this.nivelAtual++;
		}
	}

	public void acao118(Token token) {
		this.ts.setNPF(this.NPF);
	}

	public void acao119(Token token) {
		Item item = this.ts
				.getIdNomePosicaoNivelMenor(token.getLexeme(), this.nivelAtual);
		item.setTipoCategoria(Categoria.METODO);
	}

	public void acao120(Token token) {
		/*
		 * for (Item i : ts.getTabela()) { if (i.getNivel() == nivelAtual) {
		 * ts.getTabela().remove(i); } }
		 */
		this.nivelAtual = this.nivelAtual - 1;
	}

	public void acao121(Token token) {
		this.contextoLid = ContextoLID.PARAMETRO_FORMAL;
		this.ts.setPrimeiraPosicaoLID(this.ts.getTabela().size());
	}

	public void acao122(Token token) {
		this.ts.setUltimaPosicaoLID(this.ts.getTabela().size() + this.NPF);
	}

	public void acao123(Token token) throws SemanticError {
		if (this.tipoAtual != TipoVariavel.BOOLEANO
				|| this.tipoAtual != TipoVariavel.CARACTER
				|| this.tipoAtual != TipoVariavel.INTEIRO
				|| this.tipoAtual != TipoVariavel.REAL) {
			throw new SemanticError(
					"#123 - Par�metros devem ser de tipo Pr�-Definido",
					token.getPosition());
		} else {

			for (int i = this.ts.getPrimeiraPosicaoLID(); i < this.ts
					.getUltimaPosicaoLID(); i++) {

				Item atual = this.ts.getTabela().get(i);
				atual.setTipoCategoria(Categoria.PARAMETRO);

				atual.setTipo(this.tipoAtual);

				atual.setMPP(this.MPP);
				this.listaPar.add(atual);
			}
		}
	}

	public void acao124(Token token) throws SemanticError {
		if (this.tipoAtual == TipoVariavel.CADEIA) {
			throw new SemanticError(
					"#124 - M�todos devem ser de tipo pr�-definido",
					token.getPosition());
		} else {
			this.tipoMetodo = this.tipoAtual;
		}
	}

	public void acao125(Token token) {
		this.tipoMetodo = TipoVariavel.NULO;
	}

	public void acao126(Token token) {
		this.MPP = TipoParametro.REFERENCIA;
	}

	public void acao127(Token token) {
		this.MPP = TipoParametro.VALOR;
	}

	public void acao128(Token token) throws SemanticError {

		Item teste = this.ts.existe(token.getLexeme());
		System.out.println("Nivel: " + this.nivelAtual);
		if (teste == null) {
			throw new SemanticError("#128 - Identificador n�o declarado",
					token.getPosition());
		} else {
			this.posID = this.ts.getPosicaoIDTS(token, this.nivelAtual);
		}
	}

	public void acao129(Token token) throws SemanticError {
		if (this.tipoExpressao != TipoVariavel.BOOLEANO
				&& this.tipoExpressao != TipoVariavel.INTEIRO) {
			throw new SemanticError("#129 - Tipo Inv�lido da express�o",
					token.getPosition());
		} else {
			/* A��o Gera��o de C�digo */
		}
	}

	public void acao130(Token token) {
		this.contextoLid = ContextoLID.LEITURA;
	}

	public void acao131(Token token) {
		this.contextoEXPR = ContextoLID.IMPRESSAO;
	}

	public void acao132(Token token) throws SemanticError {
		if (this.tipoMetodo == this.tipoMetodo.NULO) {
			throw new SemanticError(
					"#132 - 'Retorne' s� pode ser usado em M�todo com tipo",
					token.getPosition());
		} else {
			if (this.tipoExpressao != this.tipoMetodo) {
				throw new SemanticError("#132 - Tipo retorno Invalido",
						token.getPosition());
			} else {
				/* A��o gera��o Codigo */
			}
		}
	}

	public void acao133(Token token) throws SemanticError {
		Item item = this.ts.getElementoPosicao(this.posID);

		if (item.getTipoCategoria() == Categoria.VARIAVEL
				|| item.getTipoCategoria() == Categoria.PARAMETRO) {

			if (item.getTipoSubCategoria() == SubCategoria.VETOR) {
				throw new SemanticError("#133 - Id deveria ser indexado",
						token.getPosition());
			} else {
				this.tipoLadoEsquerdo = item.getTipo();
			}

		} else {
			throw new SemanticError(
					"#133 - Id deveria ser variavel ou parametro",
					token.getPosition());
		}
	}

	public void acao134(Token token) throws SemanticError {
		Item item = this.ts.getIdNomePosicao(token.getLexeme(), this.nivelAtual);
		if (this.tipoExpressao != this.tipoLadoEsquerdo) {
			throw new SemanticError("Tipos Inconpativeis ( "
					+ String.valueOf(this.tipoExpressao) + " - "
					+ String.valueOf(this.tipoLadoEsquerdo + " )"),
					token.getPosition());
		} else {
			/* gera��o c�digo */
		}
	}

	public void acao135(Token token) throws SemanticError {
		Item item = this.ts
				.getIdNomePosicaoNivelMenor(token.getLexeme(), this.nivelAtual);
		if (item.getTipoCategoria() == Categoria.VARIAVEL) {
			throw new SemanticError("Esperava-se uma vari�vel",
					token.getPosition());
		} else {
			if (item.getTipoSubCategoria() != SubCategoria.VETOR
					&& item.getTipoSubCategoria() != SubCategoria.CADEIA) {
				throw new SemanticError(
						"#135 - Apenas vetores e cadeias podem ser indexados",
						token.getPosition());
			} else {
				this.tipoVarIndexada = item.getTipoSubCategoria();
			}
		}
	}

	public void acao136(Token token) throws SemanticError {
		if (this.tipoExpressao != TipoVariavel.INTEIRO) {
			throw new SemanticError("Indice deveria ser inteiro",
					token.getPosition());
		} else {
			if (this.tipoVarIndexada == SubCategoria.CADEIA) {
				this.tipoLadoEsquerdo = TipoVariavel.CARACTER;
			} else {
			}
		}
	}

	public void acao137(Token token) throws SemanticError {
		Item item = this.ts.getIdNomePosicao(token.getLexeme(), this.nivelAtual);
		if (item.getTipoCategoria() == Categoria.METODO) {
			throw new SemanticError("#137 - Deveria ser um metodo",
					token.getPosition());
		} else {
			if (this.tipoMetodo != TipoVariavel.NULO) {
				throw new SemanticError("#137 - Esperava-se m�todo sem tipo",
						token.getPosition());
			}
		}
	}

	public void acao138(Token token) {
		this.NPA = 0;
		this.contextoEXPR = ContextoLID.PARAMETRO_ATUAL;
	}

	public void acao139(Token token) throws SemanticError {
		if (this.NPA != this.NPF) {
			throw new SemanticError("#138 - Erro na quantidade de Parametros",
					token.getPosition());
		} else {
			/* G. C�digo para chamada de proc */
		}
	}

	public void acao140(Token token) throws SemanticError {
		Item item = this.ts
				.getIdNomePosicaoNivelMenor(token.getLexeme(), this.nivelAtual);
		this.ts.show();
		if (item.getTipoCategoria() != Categoria.METODO) {
			throw new SemanticError("id deveria ser metodo",
					token.getPosition());
		} else {
			if (this.tipoMetodo != TipoVariavel.NULO) {
				throw new SemanticError("Esperava-se metodo sem tipo",
						token.getPosition());

			} else {
				if (this.NPF != 0) {
					throw new SemanticError("Erro na quantidade de Parametros",
							token.getPosition());
				} else {
					/* GC para chamada de metodos */
				}
			}
		}
	}

	public void acao141(Token token) throws SemanticError {
		if (this.contextoEXPR == ContextoLID.PARAMETRO_ATUAL) {
			if (this.NPF > 0) {
				this.NPA++;
			}
		}

		if (this.contextoEXPR == ContextoLID.IMPRESSAO) {
			throw new SemanticError("Tipo invalido para impressao",
					token.getPosition());
		}
	}

	public void acao142(Token token) {
		this.tipoExpressao = this.tipoExpressaoSimples.pop();
	}

	public void acao143(Token token) throws SemanticError {
		if (this.tipoExpressao != this.tipoExpressaoSimples.peek()) {
			throw new SemanticError("Operandos incompat�veis",
					token.getPosition());
		} else {
			this.tipoExpressao = TipoVariavel.BOOLEANO;
		}
	}

	public void acao144(Token token) {
		this.operadorComparacao = OperadorComparacao.IGUAL;
	}

	public void acao145(Token token) {
		this.operadorComparacao = OperadorComparacao.MENOR;
	}

	public void acao146(Token token) {
		this.operadorComparacao = OperadorComparacao.MAIOR;
	}

	public void acao147(Token token) {
		this.operadorComparacao = OperadorComparacao.MAIOR_IGUAL;
	}

	public void acao148(Token token) {
		this.operadorComparacao = OperadorComparacao.MENOR_IGUAL;
	}

	public void acao149(Token token) {
		this.operadorComparacao = OperadorComparacao.DIFERENTE;
	}

	public void acao150(Token token) {
		this.tipoExpressaoSimples.push(this.tipoFator);
	}

	public void acao151(Token token) throws SemanticError {
		TipoVariavel tpe = this.tipoExpressaoSimples.peek();
		TipoVariavel tpm = this.tipoTermo.peek();

		switch (this.operadorAtual) {
		case ADICAO:

			if (tpe == TipoVariavel.INTEIRO || tpe == TipoVariavel.REAL) {
				if (tpm == TipoVariavel.INTEIRO || tpm == TipoVariavel.REAL) {
					// gera cdo
				} else {
					throw new SemanticError(
							"151 [ add ] Operandos incompat�veis",
							token.getPosition());
				}
			} else {
				throw new SemanticError("151 [ add ] Operandos incompat�veis",
						token.getPosition());
			}

			break;
		case SUBTRACAO:
			if (tpe == TipoVariavel.INTEIRO || tpe == TipoVariavel.REAL) {
				if (tpm == TipoVariavel.INTEIRO || tpm == TipoVariavel.REAL) {
					// gera cdo
				} else {
					throw new SemanticError(
							"151 [ add ] Operandos incompat�veis",
							token.getPosition());
				}
			} else {
				throw new SemanticError("151 [ add ] Operandos incompat�veis",
						token.getPosition());
			}
			break;
		case OU:
			if (this.tipoExpressaoSimples.peek() != TipoVariavel.BOOLEANO) {
				throw new SemanticError("151 [ ou ] Operandos incompat�veis",
						token.getPosition());
			}
			break;
		default:
			log(token.getLexeme());
			break;
		}
	}

	public void acao152(Token token) throws SemanticError {

		if (this.tipoFator != this.tipoExpressaoSimples.peek()) {
			throw new SemanticError("#152 - Operandos incompat�veis�",
					token.getPosition());
		} else {
			TipoVariavel tipo = this.tipoExpressaoSimples.pop();
			switch (this.operadorAtual) {
			case ADICAO:
				if (tipo == TipoVariavel.INTEIRO && this.tipoFator == TipoVariavel.REAL
						|| tipo == TipoVariavel.REAL && this.tipoFator == TipoVariavel.REAL
						|| tipo == TipoVariavel.REAL
								&& this.tipoFator == TipoVariavel.INTEIRO) {
					this.tipoExpressaoSimples.push(TipoVariavel.REAL);
				} else {
					this.tipoExpressaoSimples.push(TipoVariavel.INTEIRO);
				}
				break;
			case SUBTRACAO:
				if (tipo == TipoVariavel.INTEIRO && this.tipoFator == TipoVariavel.REAL
						|| tipo == TipoVariavel.REAL && this.tipoFator == TipoVariavel.REAL
						|| tipo == TipoVariavel.REAL
								&& this.tipoFator == TipoVariavel.INTEIRO) {
					this.tipoExpressaoSimples.push(TipoVariavel.REAL);
				} else {
					this.tipoExpressaoSimples.push(TipoVariavel.INTEIRO);
				}
				break;

			case OU:
				if (tipo == TipoVariavel.BOOLEANO
						&& this.tipoFator == TipoVariavel.BOOLEANO) {
					this.tipoExpressaoSimples.push(TipoVariavel.BOOLEANO);
				}
				break;
			default:
				log(token.getLexeme());
				break;
			}
		}
	}

	public void acao153(Token token) {
		this.operadorAtual = Operador.ADICAO;
	}

	public void acao154(Token token) {
		this.operadorAtual = Operador.SUBTRACAO;
	}

	public void acao155(Token token) {
		this.operadorAtual = Operador.OU;
	}

	public void acao156(Token token) {
		this.tipoTermo.push(this.tipoFator);
	}

	public void acao157(Token token) throws SemanticError {

		switch (this.operadorAtual) {
		case MULTIPLICACAO:
			if (this.tipoTermo.peek() != TipoVariavel.INTEIRO
					&& this.tipoTermo.peek() != TipoVariavel.REAL) {
				throw new SemanticError("#157 - Operandos incompat�veis",
						token.getPosition());
			}
			break;

		case DIVISAO:
			if (this.tipoTermo.peek() != TipoVariavel.INTEIRO
					&& this.tipoTermo.peek() != TipoVariavel.REAL) {
				throw new SemanticError("#157 - Operandos incompat�veis�",
						token.getPosition());
			}
			break;
		case E:
			if (this.tipoTermo.peek() != TipoVariavel.BOOLEANO) {
				throw new SemanticError("#157 - Operandos incompat�veis�",
						token.getPosition());
			}
			break;

		case DIV:
			if (this.tipoTermo.peek() != TipoVariavel.INTEIRO) {
				throw new SemanticError("#157 - Operandos incompat�veis",
						token.getPosition());
			}
			break;

		default:
			break;
		}
	}

	public void acao158(Token token) throws SemanticError {

		boolean operavel = false;

		TipoVariavel t1 = this.tipoTermo.pop();
		TipoVariavel t2 = this.tipoTermo.pop();

		switch (this.operadorAtual) {
		case ADICAO:
			if ((t1 == TipoVariavel.INTEIRO || t1 == TipoVariavel.REAL)
					&& (t1 == TipoVariavel.INTEIRO || t1 == TipoVariavel.REAL)) {
				operavel = true;
			}
			break;
		case DIVISAO:
			if (t1 == TipoVariavel.INTEIRO || t1 == TipoVariavel.REAL) {
				if (t2 == TipoVariavel.INTEIRO || t2 == TipoVariavel.REAL) {
					operavel = true;
				}
			}
			break;
		case DIV:
			operavel = t1 == TipoVariavel.INTEIRO && t2 == TipoVariavel.INTEIRO;
			break;
		case MULTIPLICACAO:
			if (t1 == TipoVariavel.INTEIRO || t1 == TipoVariavel.REAL) {
				if (t2 == TipoVariavel.INTEIRO || t2 == TipoVariavel.REAL) {
					operavel = true;
				}
			}
			break;
		case E:
			operavel = t1 == TipoVariavel.BOOLEANO && t2 == TipoVariavel.BOOLEANO;
			break;

		case OU:
			operavel = t1 == TipoVariavel.BOOLEANO && t2 == TipoVariavel.BOOLEANO;
			break;

		case SUBTRACAO:
			if (t1 == TipoVariavel.INTEIRO || t1 == TipoVariavel.REAL) {
				if (t2 == TipoVariavel.INTEIRO || t2 == TipoVariavel.REAL) {
					operavel = true;
				}
			}
			break;

		default:
			break;
		}

		if (operavel) {
			// Geracao de cod
		} else {
			throw new SemanticError("#158 - Operadores n�o s�o compat�veis",
					token.getPosition());
		}
	}

	public void acao159(Token token) {
		this.operadorAtual = Operador.MULTIPLICACAO;
	}

	public void acao160(Token token) {

		this.operadorAtual = Operador.DIVISAO;
	}

	public void acao161(Token token) {
		this.operadorAtual = Operador.E;
	}

	public void acao162(Token token) {
		this.operadorAtual = Operador.DIV;
	}

	public void acao163(Token token) throws SemanticError {
		if (this.opNega) {
			throw new SemanticError("Opera��o 'n�o' repetido - n�o pode!",
					token.getPosition());
		} else {
			this.opNega = true;
		}
	}

	public void acao164(Token token) throws SemanticError {
		if (this.tipoFator != TipoVariavel.BOOLEANO) {
			throw new SemanticError("Opera��o 'n�o' exige operando booleano",
					token.getPosition());
		} else {
			this.opNega = false;
		}
	}

	public void acao165(Token token) throws SemanticError {
		if (this.OpUnario) {
			throw new SemanticError("Operador Un�rio repetido",
					token.getPosition());
		} else {
			this.OpUnario = true;
		}
	}

	public void acao166(Token token) throws SemanticError {
		if (this.tipoFator != TipoVariavel.INTEIRO || this.tipoFator != TipoVariavel.REAL) {
			throw new SemanticError(
					"Operador Un�rio exige exige operando numero",
					token.getPosition());
		} else {
			this.OpUnario = false;
		}
	}

	public void acao167(Token token) {
		this.opNega = this.OpUnario = false;
	}

	public void acao168(Token token) {
		this.tipoFator = this.tipoExpressao;
	}

	public void acao169(Token token) {
		this.tipoFator = this.tipoVariavel;
	}

	public void acao170(Token token) {
		this.tipoFator = this.tipoConstante;
	}

	public void acao171(Token token) throws SemanticError {

		Item item = this.ts.getIdNomePosicao(token.getLexeme(), this.nivelAtual);
		this.pilhaMetodos.push(item);
		if (item.getTipoCategoria() != Categoria.METODO) {
			throw new SemanticError("id deveria ser um metodo",
					token.getPosition());
		} else {
			if (this.tipoMetodo == TipoVariavel.NULO) {
				throw new SemanticError("Esperava-se m�todo com tipo",
						token.getPosition());
			} else {
				this.NPA = 0;
				this.contextoEXPR = ContextoLID.PARAMETRO_ATUAL;
			}
		}
	}

	/*
	 * #172 se NPA = NPF ent�o TipoVar := Tipo do resultado da fun��o (* Gera
	 * C�digo p/ ativa��o do m�todo *) sen�o ERRO(�Erro na quant de par�metros�)
	 */
	public void acao172(Token token) throws SemanticError {
		if (this.NPA == this.NPF) {
			if (this.tipoVariavel == this.tipoResultadoFuncao) {
			} else {
				throw new SemanticError("Erro no numero de parametros",
						token.getPosition());
			}
		}
	}

	/*
	 * #173 - Se TipoExpr <> �inteiro� ent�o ERRO(��ndice deveria ser inteiro�)
	 * sen�o se TipoVarIndexada = cadeia ent�o TipoVar := �caracter� senao
	 * TipoVar := TipoElementos do vetor
	 */
	public void acao173(Token token) throws SemanticError {

		if (this.tipoExpressao != TipoVariavel.INTEIRO) {
			throw new SemanticError("Indice deveria ser inteiro",
					token.getPosition());
		} else {
		}
	}

	/*
	 * #174 - se categoria de id = �vari�vel� ou ent�o se tipo de id = �vetor�
	 * �Par�metro� ent�o ERRO(�vetor deve ser indexado�) sen�o TipoVar := Tipo
	 * de id sen�o se categoria de id = m�todo ent�o se tipo m�todo = �nulo�
	 * ent�o ERRO(�Esperava-se m�todo com tipo�) sen�o se NPF <> 0 ent�o
	 * ERRO(�Erro na quant. de par�metros�) sen�o TipoVar:=Tipo resultado (*
	 * Gera C�digo *) Sen�o se categoria de id = �constante� ent�o TipoVar:=
	 * TipoConst Sen�o ERRO(�esperava-se var, id-m�todo ou constante�)
	 */
	public void acao174(Token token) throws SemanticError {

		Item id = this.ts.getIdNomePosicaoNivelMenor(token.getLexeme(), this.nivelAtual);

		if (id == null) {
			throw new SemanticError("#174 - Id n�o existe.",
					token.getPosition());
		}

		if (id.getTipoCategoria() == Categoria.VARIAVEL
				|| id.getTipoCategoria() == Categoria.PARAMETRO) {

			if (id.getTipoSubCategoria() == SubCategoria.VETOR) {
				throw new SemanticError("Indice deveria ser inteiro",
						token.getPosition());
			} else {
				this.tipoVariavel = id.getTipo();
			}

		} else {

			if (id.getTipoCategoria() == Categoria.METODO) {
				if (this.tipoMetodo == TipoVariavel.NULO) {
					throw new SemanticError("Esperava-se metodo com tipo",
							token.getPosition());
				} else {
					if (this.NPF != 0) {
						throw new SemanticError(
								"Erro na quantidade de parametros",
								token.getPosition());
					} else {
						this.tipoVariavel = this.tipoAtual;
					}
				}
			} else {
				if (id.getTipoCategoria() == Categoria.CONSTANTE) {
					this.tipoVariavel = this.tipoConstante;
				} else {
					throw new SemanticError(
							"Esperava-se var, id-m�todo ou constante",
							token.getPosition());
				}
			}
		}
	}

	/*
	 * #175 - Se id n�o est� declarado ent�o ERRO(�Id n�o declarado�) sen�o se
	 * categoria de id <> constante entao ERRO (�id de Constante esperado�)
	 * sen�o TipoConst = Tipo do id-constante ValConst = Valor da constante id
	 */
	public void acao175(Token token) throws SemanticError {
		if (!this.ts.estaDeclarado(token.getLexeme(), this.nivelAtual)) {
			throw new SemanticError("#175 - Identificador n�o declarado: "
					+ token.getLexeme(), token.getPosition());
		} else {
			Item item = this.ts.getIdNomePosicao(token.getLexeme(), this.nivelAtual);// constante

			if (item.getTipoCategoria() != Categoria.CONSTANTE) {
				throw new SemanticError("#175 - id de Constante esperado",
						token.getPosition());
			} else {
				this.tipoConstante = item.getTipo();
				this.valConst = item.getValor();
			}
		}
	}

	/*
	 * #176 - ValConst := valor da constante
	 */
	public void acao176(Token token) {
		this.tipoConstante = TipoVariavel.INTEIRO;
		this.valConst = token.getLexeme();
	}

	/*
	 * #177 - ValConst := valor da constante
	 */
	public void acao177(Token token) {
		this.tipoConstante = TipoVariavel.REAL;
		this.valConst = token.getLexeme();
	}

	/*
	 * #178 - ValConst := valor da constante
	 */
	public void acao178(Token token) {
		this.tipoConstante = TipoVariavel.BOOLEANO;
		this.valConst = token.getLexeme();
	}

	/*
	 * #179 - ValConst := valor da constante
	 */
	public void acao179(Token token) {
		this.tipoConstante = TipoVariavel.BOOLEANO;
		this.valConst = token.getLexeme();
	}

	/*
	 * #180 - ValConst := valor da constante
	 */
	public void acao180(Token token) {
		this.tipoConstante = TipoVariavel.CARACTER;
		this.valConst = token.getLexeme();
	}

	public void executeAction(int action, Token token) throws SemanticError {
		// System.out.println("A��o #" + action + ", Token: " + token);

		try {
			@SuppressWarnings("rawtypes")
			Class[] classeParametros = new Class[] { Token.class };
			java.lang.String nomeMetodo = "acao" + action;
			Method metodo = this.getClass().getMethod(nomeMetodo, classeParametros);
			Object[] argumentos = new Object[] { token };

			metodo.invoke(this, argumentos);

		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof SemanticError) {
				SemanticError erroSemantico = (SemanticError) e.getCause();
				throw erroSemantico;
			} else {
				e.printStackTrace();
			}

		} catch (NoSuchMethodException e) {
			throw new SemanticError("Erro, a a��o #" + action
					+ " n�o foi implementada. ", token.getPosition());
		} catch (Exception e) {
			throw new SemanticError("Erro ao invocar m�todo."
					+ e.getCause().getMessage(), token.getPosition());
		}
	}

	public TipoVariavel getTipoAtual() {
		return this.tipoAtual;
	}

	public void setTipoAtual(TipoVariavel tipoVariavel) {
		this.tipoAtual = tipoVariavel;
	}

	public static void logline(int line) {
		System.out.println(" (" + new Semantico().getClass().getSimpleName()
				+ ".java:" + line + ") ");
	}

	public static void log(Object e) {
		System.out.println(e);
	}
}
