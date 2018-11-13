package compilador.view;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sobre extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel labelTrabalho;
	private JLabel labelAmanda;
	private JLabel labelIsabelle;

	public Sobre(JFrame frame) {
		super(frame, "Sobre", true);

		this.render();

		this.pack();
	}

	private void definaLayout() {
		this.setMinimumSize(new Dimension(100, 100));
		this.setLocationRelativeTo(this.getOwner());

		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.labelTrabalho).addComponent(this.labelAmanda).addComponent(this.labelIsabelle));
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.labelTrabalho));
		vGroup.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.labelAmanda));
		vGroup.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.labelIsabelle));
		layout.setVerticalGroup(vGroup);

		this.setLayout(layout);
	}

	private void render() {
		this.labelTrabalho = new JLabel("INE5622 - Introdução a Compiladores Trabalho Compilador Análise Léxica e Sintática");
		this.labelAmanda = new JLabel("Amanda Christoval da Veiga de Aquino");
		this.labelIsabelle = new JLabel("Isabelle Pinheiro");

		this.add(this.labelTrabalho);
		this.add(this.labelAmanda);
		this.add(this.labelIsabelle);

		this.definaLayout();
	}

}
