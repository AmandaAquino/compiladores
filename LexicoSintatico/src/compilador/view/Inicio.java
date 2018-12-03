package compilador.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.glass.events.KeyEvent;

import compilador.ControladorView.ControladorView;
import compilador.gals.AnalysisError;
import compilador.gals.LexicalError;
import compilador.gals.SemanticError;
import compilador.gals.SyntaticError;

public class Inicio extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private ControladorView controlador;

	private JMenuBar menuBar;

	private JMenu arquivo;
	private JMenuItem carregar;
	private JMenuItem salvar;

	private JMenu analise;
	private JMenuItem lexico;
	private JMenuItem sintatico;
	private JMenuItem semantico;

	private JMenu ajuda;
	private JMenuItem sobre;

	private JLabel labelCodigo;
	private JTextArea codigo;
	private JLabel labelResultado;
	private JTextArea resultado;

	private JScrollPane codigoScrollPane;
	private JScrollPane resultadoScrollPane;

	public Inicio() {
		this.controlador = new ControladorView(this);
		this.render();
		this.pack();
	}

	private void definaLayout() {
		this.setMinimumSize(new Dimension(300, 300));

		this.setTitle("Compilador");

		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.labelCodigo).addComponent(this.codigoScrollPane).addComponent(this.labelResultado)
				.addComponent(this.resultadoScrollPane));
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.labelCodigo));
		vGroup.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.codigoScrollPane));
		vGroup.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.labelResultado));
		vGroup.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.resultadoScrollPane));
		layout.setVerticalGroup(vGroup);

		this.setLayout(layout);
	}

	private void render() {
		this.renderMenuBar();
		this.renderCodigo();
		this.renderResultado();

		this.definaLayout();
	}

	private void renderMenuBar() {
		this.menuBar = new JMenuBar();

		this.arquivo = new JMenu("Arquivo");
		this.carregar = new JMenuItem("Carregar");
		this.salvar = new JMenuItem("Salvar");

		this.analise = new JMenu("Análise");
		this.lexico = new JMenuItem("Léxico");
		this.sintatico = new JMenuItem("Sintático");
		this.semantico = new JMenuItem("Semântico");

		this.ajuda = new JMenu("Ajuda");
		this.ajuda.setMnemonic(KeyEvent.VK_A);
		this.sobre = new JMenuItem("Sobre");

		this.setJMenuBar(this.menuBar);

		this.menuBar.add(this.arquivo);
		this.menuBar.add(this.analise);
		this.analise.add(this.lexico);
		this.analise.add(this.sintatico);
		this.analise.add(this.semantico);
		this.menuBar.add(this.ajuda);

		this.arquivo.add(this.carregar);
		this.arquivo.add(this.salvar);
		this.ajuda.add(this.sobre);

		this.carregar.addActionListener(this);
		this.salvar.addActionListener(this);
		this.lexico.addActionListener(this);
		this.sintatico.addActionListener(this);
		this.semantico.addActionListener(this);
		this.sobre.addActionListener(this);
	}

	private void renderCodigo() {
		this.labelCodigo = new JLabel("Código:");

		this.codigo = new JTextArea(10, 10);
		this.codigo.setLineWrap(true);
		this.codigo.setWrapStyleWord(true);

		this.codigoScrollPane = new JScrollPane(this.codigo);
		this.codigoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(this.labelCodigo);
		this.add(this.codigoScrollPane);
	}

	private void renderResultado() {
		this.labelResultado = new JLabel("Resultado:");

		this.resultado = new JTextArea(10, 10);
		this.resultado.setEditable(false);
		this.resultado.setLineWrap(true);
		this.resultado.setWrapStyleWord(true);

		this.resultadoScrollPane = new JScrollPane(this.resultado);
		this.resultadoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(this.labelResultado);
		this.add(this.resultadoScrollPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.carregar) {
			this.controlador.clickCarregar();
		}

		if (e.getSource() == this.salvar) {
			this.controlador.clickSalvar();
		}

		if (e.getSource() == this.sobre) {
			this.controlador.clickSobre();
		}
		if (e.getSource() == this.lexico) {
			try {
				this.controlador.clickLexico();
			} catch (LexicalError e1) {
				this.informeErro(e1, Analise.LEXICA);
			}
		}
		if (e.getSource() == this.sintatico) {
			try {
				this.controlador.clickSintatico();
			} catch (LexicalError e1) {
				this.informeErro(e1, Analise.LEXICA);
				e1.printStackTrace();
			} catch (SyntaticError e1) {
				this.informeErro(e1, Analise.SINTATICA);
				e1.printStackTrace();
			} catch (SemanticError e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == this.semantico) {
			try {
				this.controlador.clickSemantico();
			} catch (LexicalError e1) {
				this.informeErro(e1, Analise.LEXICA);
				e1.printStackTrace();
			} catch (SyntaticError e1) {
				this.informeErro(e1, Analise.SINTATICA);
				e1.printStackTrace();
			} catch (SemanticError e1) {
				this.informeErro(e1, Analise.SEMANTICA);
				e1.printStackTrace();
			}
		}
	}

	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void openSobre() {
		new Sobre(this).setVisible(true);
	}

	public String getCodigo() {
		String retorno = this.codigo.getText();
		return retorno;
	}

	public void setCodigo(String codigo) {
		this.codigo.setText(codigo);
	}

	public String getPath(String tipo) {
		JFileChooser jFileChooser = new JFileChooser();
		if (tipo.equals("carregar")) {
			if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				return jFileChooser.getSelectedFile().getAbsolutePath();
			}
		} else {
			if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				return jFileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		return null;
	}

	public void informeSucessoLexico() {
		this.resultado.setText("O programa está lexicamente correto!");
	}

	public void informeSucessoSintatico() {
		this.resultado.setText("O programa está sintaticamente correto!");
	}

	public void informeSucessoSemantico() {
		this.resultado.setText("O analisador semântico ainda não foi desenvolvido");

	}

	private void informeErro(AnalysisError analysisError, Analise analise) {
		this.resultado.setText("Ocorreu um erro inesperado durante a análise " + analise.toString() + ": " + analysisError.getMessage() + "\nFoi lido: " + analysisError.getToken()
				+ "\nPosição: " + analysisError.getPosition() + " indicado pelo cursor acima no código");
		this.codigo.setCaretPosition(analysisError.getPosition());
	}

}
