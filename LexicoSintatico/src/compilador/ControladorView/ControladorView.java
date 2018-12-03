package compilador.ControladorView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import compilador.gals.LexicalError;
import compilador.gals.Lexico;
import compilador.gals.SemanticError;
import compilador.gals.Semantico;
import compilador.gals.Sintatico;
import compilador.gals.SyntaticError;
import compilador.view.Inicio;

public class ControladorView {
	private Inicio view;

	public ControladorView(Inicio inicio) {
		this.view = inicio;
	}

	public void clickSobre() {
		this.view.openSobre();
	}

	public void clickCarregar() {
		String path = this.view.getPath("carregar");

		if (path != null) {
			File f = new File(path);
			if (f.exists()) {
				StringBuilder codigo = new StringBuilder();
				FileInputStream fis = null;

				try {
					fis = new FileInputStream(f);

					int ln;
					while ((ln = fis.read()) != -1) {
						codigo.append((char) ln);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				this.view.setCodigo(codigo.toString());
			}
		}
	}

	public void clickSalvar() {
		String path = this.view.getPath("salvar");
		String codigo = this.view.getCodigo();

		if (path != null && codigo != null) {
			File f = new File(path);
			FileOutputStream fos = null;

			try {
				fos = new FileOutputStream(f);

				fos.write(codigo.getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void clickLexico() throws LexicalError {
		Lexico lex = new Lexico();
		String codigo = this.view.getCodigo();
		lex.setInput(codigo);
		while (lex.nextToken() != null) {
			;
		}
		this.view.informeSucessoLexico();

	}

	public void clickSintatico() throws LexicalError, SyntaticError, SemanticError {
		Lexico lex = new Lexico();
		String codigo = this.view.getCodigo();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		lex.setInput(codigo);
		sintatico.parse(lex, semantico);
		this.view.informeSucessoSintatico();

	}

	public void clickSemantico() throws LexicalError, SyntaticError, SemanticError {
		Sintatico sintatico = new Sintatico();
		Semantico analisadorSemantico = new Semantico();
		String codigo = this.view.getCodigo();
		sintatico.executarSemantico(true);
		sintatico.parse(new Lexico(codigo), analisadorSemantico);

		// Lexico lex = new Lexico();
		// String codigo = this.view.getCodigo();
		// Sintatico sintatico = new Sintatico();
		// Semantico semantico = new Semantico();
		// lex.setInput(codigo);
		// sintatico.parse(lex, semantico);
		this.view.informeSucessoSemantico();

	}

}
