package agenda.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import agenda.DAO.GuardaValor;

/**
 * @author flavio.rocha
 */
public class InterfaceGUI extends JFrame implements ActionListener {

	/**
	 * Atributo
	 */
	private static final long serialVersionUID = 1L;

	// Atributos
	private JButton bAdicionar, bVerItem, bVerTudo, bAtualizar, bRemoverItem;
	private JPanel pEsquerda, pCentro;
	@SuppressWarnings("unused")
	private JLabel lFoto;
	public Container janela;
	private int codigo;
	GuardaValor g = new GuardaValor();

	public InterfaceGUI() {
		Janela();
	}

	public void Janela() {
		setTitle("Agenda - 2.0");
		setSize(500, 400);
		setLocation(500, 150);
		setResizable(false);

		janela = getContentPane();
		janela.setLayout(new BorderLayout());

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// e.printStackTrace();
		}

		// Itens Painel Esquerda
		pEsquerda = new JPanel(new GridLayout(0, 1, 2, 2));
		pEsquerda.setPreferredSize(new Dimension(150, 300));
		pEsquerda.add(bAdicionar = new JButton("Adicionar"));
		bAdicionar.addActionListener(this);
		pEsquerda.add(bVerItem = new JButton("Ver Item"));
		bVerItem.addActionListener(this);
		pEsquerda.add(bVerTudo = new JButton("Ver tudo"));
		bVerTudo.addActionListener(this);
		pEsquerda.add(bAtualizar = new JButton("Atualizar"));
		bAtualizar.addActionListener(this);
		pEsquerda.add(bRemoverItem = new JButton("Remover item"));
		bRemoverItem.addActionListener(this);

		// Itens Painel Centro
		pCentro = new JPanel(new FlowLayout());
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/_imagens/agenda.gif"));
		pCentro.add(lFoto = new JLabel(logo));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pCentro, BorderLayout.CENTER);
		setVisible(true);
	}

	// Métoda e atributos para adicionar Itens a agenda
	private JPanel pCentroAdd;
	@SuppressWarnings("unused")
	private JLabel lDescricao, lLocal, lData, lStatus;
	private JTextField tDescricao, tLocal, tData, tStatus;
	private JButton bOk, bVoltar;

	public void janelaAdicionar() {
		pCentroAdd = new JPanel(new GridLayout(0, 2, 2, 2));
		pCentroAdd.add(lDescricao = new JLabel("Descrição:"));
		pCentroAdd.add(tDescricao = new JTextField());
		pCentroAdd.add(lLocal = new JLabel("Local:"));
		pCentroAdd.add(tLocal = new JTextField());
		pCentroAdd.add(lData = new JLabel("Data:"));
		pCentroAdd.add(tData = new JTextField());
		pCentroAdd.add(lStatus = new JLabel("Confirmação:"));
		pCentroAdd.add(tStatus = new JTextField());
		pCentroAdd.add(bOk = new JButton("Adicionar"));
		bOk.addActionListener(this);
		pCentroAdd.add(bVoltar = new JButton("Voltar"));
		bVoltar.addActionListener(this);

		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pCentroAdd, BorderLayout.CENTER);
		setVisible(true);
	}

	// Método e atributos para ver um item
	private JPanel pTexto, pAjuste, pBaixo;
	private JTextArea areaTextoItem;
	private JScrollPane barra_rolagem; // Barra de rolagem

	public void janelaVerItem() {
		pAjuste = new JPanel(new BorderLayout()); // Utilizado para ajustar os itens.
		pBaixo = new JPanel(new FlowLayout());

		pTexto = new JPanel(new FlowLayout());
		pTexto.add(areaTextoItem);
		pBaixo.add(bVoltar = new JButton("Voltar"));
		bVoltar.addActionListener(this);

		barra_rolagem = new JScrollPane(areaTextoItem);
		this.add(barra_rolagem);

		areaTextoItem.setEditable(false);

		// Ajusta o que sera exibina na janela.
		pAjuste.add(barra_rolagem, BorderLayout.CENTER);
		pAjuste.add(pBaixo, BorderLayout.SOUTH);
		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pAjuste, BorderLayout.CENTER);
		setVisible(true);
	}

	// Método e atributos para ver todos os itens
	private JPanel pTextoTudo, pAjusteTudo;
	private JTextArea areaTextoItemTudo;

	public void janelaVerTudo() {
		pAjusteTudo = new JPanel(new BorderLayout()); // Utilizado para ajustar os itens.

		pTextoTudo = new JPanel(new FlowLayout());
		pBaixo = new JPanel(new FlowLayout());
		pTextoTudo.add(areaTextoItemTudo);
		pBaixo.add(bVoltar = new JButton("Voltar"));
		bVoltar.addActionListener(this);

		barra_rolagem = new JScrollPane(areaTextoItemTudo);
		this.add(barra_rolagem);

		areaTextoItemTudo.setEditable(false);

		// Ajusta o que sera exibina na janela.
		pAjusteTudo.add(barra_rolagem, BorderLayout.CENTER);
		pAjusteTudo.add(pBaixo, BorderLayout.SOUTH);
		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pAjusteTudo, BorderLayout.CENTER);
		setVisible(true);
	}

	// Métoda e atributos para atualizar Itens a agenda
	private JButton bAtualizarItem;

	public void janelaAtualizar(String a, String b, String c, int d) {
		pCentroAdd = new JPanel(new GridLayout(0, 2, 2, 2));
		pCentroAdd.add(lDescricao = new JLabel("Descrição:"));
		pCentroAdd.add(tDescricao = new JTextField());
		pCentroAdd.add(lLocal = new JLabel("Local:"));
		pCentroAdd.add(tLocal = new JTextField());
		pCentroAdd.add(lData = new JLabel("Data:"));
		pCentroAdd.add(tData = new JTextField());
		pCentroAdd.add(lStatus = new JLabel("Confirmação:"));
		pCentroAdd.add(tStatus = new JTextField());
		pCentroAdd.add(bAtualizarItem = new JButton("Atualizar"));
		bAtualizarItem.addActionListener(this);
		pCentroAdd.add(bVoltar = new JButton("Voltar"));
		bVoltar.addActionListener(this);

		// Adiciona os itens na tabela.
		tDescricao.setText(a);
		tLocal.setText(b);
		tData.setText(c);
		tStatus.setText("" + d);

		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pCentroAdd, BorderLayout.CENTER);
		setVisible(true);
	}

	// Métoda e atributos para remover Itens da agenda

	public void janelaRemoverItem() {
		pAjusteTudo = new JPanel(new BorderLayout()); // Utilizado para ajustar os itens.

		pTextoTudo = new JPanel(new FlowLayout());
		pBaixo = new JPanel(new FlowLayout());
		pTextoTudo.add(areaTextoItemTudo);
		pBaixo.add(bVoltar = new JButton("Voltar"));
		bVoltar.addActionListener(this);

		barra_rolagem = new JScrollPane(areaTextoItemTudo);
		this.add(barra_rolagem);

		areaTextoItemTudo.setEditable(false);

		// Ajusta o que sera exibina na janela.
		pAjusteTudo.add(barra_rolagem, BorderLayout.CENTER);
		pAjusteTudo.add(pBaixo, BorderLayout.SOUTH);
		janela.add(pEsquerda, BorderLayout.WEST);
		janela.add(pAjusteTudo, BorderLayout.CENTER);
		setVisible(true);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent evento) {
		// Exibe a tela para adicionar um novo item.
		if (evento.getSource() == bAdicionar) {
			janela.removeAll();
			janelaAdicionar();
		}

		// Exibe o item do código informado.
		if (evento.getSource() == bVerItem) {
			try {
				String entrada = JOptionPane.showInputDialog("Digite o código do item que deseja ver.");
				areaTextoItem = new JTextArea(18, 28);
				if (null == entrada) {

				} else {
					int opc = Integer.parseInt(entrada); // Conversão
					if (g.consultaPorCodigo(opc) != "") {
						areaTextoItem.setText(
								"Informação sobre o item de código: " + opc + "\n\n" + g.consultaPorCodigo(opc));
					}
					janela.removeAll();
					janelaVerItem();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Código inválido", "Mensagem - Erro", 0);
				// e.printStackTrace();
			}
		}

		// Exibe todos os itens.
		if (evento.getSource() == bVerTudo) {

			try {
				areaTextoItemTudo = new JTextArea(18, 28);
				if (g.listaItens() != "") {
					areaTextoItemTudo.setText("Itens salvos na agenda\n" + g.listaItens());
				} else {
					areaTextoItemTudo.setText("Não há itens na sua agenda");
				}
				janela.removeAll();
				janelaVerTudo();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Código inválido", "Mensagem - Erro", 0);
				// e.printStackTrace();
			}

			janela.removeAll();
			janelaVerTudo();
		}

		if (evento.getSource() == bAtualizar) {

			try {

				codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do item que deseja atualizar."));
				if (g.consultaPorCodigo(codigo) != "Item não localizado.") {
					janela.removeAll();
					janelaAtualizar(g.consultaDescricao(codigo), g.consultaLocal(codigo), g.consultaData(codigo),
							g.consultaStatus(codigo));
				} else {
					JOptionPane.showMessageDialog(null, "Alerta - Item não localizado", "Mensagem - Alerta", 2);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		// Remove um item da agenda.
		if (evento.getSource() == bRemoverItem) {
			try {
				String entrada = JOptionPane.showInputDialog("Digite o código do item que deseja ver.");

				if (null == entrada) {

				} else {
					int opc = Integer.parseInt(entrada);
					// Adiciona o item removido a tela:
					areaTextoItemTudo = new JTextArea(18, 28);
					String item = g.consultaPorCodigo(opc);

					if (g.removerItem(opc) == true && item != "Item não localizado.") {
						JOptionPane.showMessageDialog(null, "Item removido com sucesso", "Mensagem", 1);
						areaTextoItemTudo.setText("O item romovido foi:\n" + item);
					} else {
						JOptionPane.showMessageDialog(null, "Item não removido", "Mensagem- Erro", 0);
						areaTextoItemTudo.setText("O item não foi romovido:\n" + item);
					}

					janela.removeAll();
					janelaRemoverItem();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Código inválido", "Mensagem - Erro", 0);
				// e.printStackTrace();
			}
		}

		// Retorna a página inicial do aplicativo.
		if (evento.getSource() == bVoltar) {
			janela.removeAll();
			Janela();
			setVisible(true);
		}

		// Botão adicionar
		if (evento.getSource() == bOk) {
			int opc;
			try {
				opc = Integer.parseInt(tStatus.getText());

				boolean sucesso = g.adicionaValor(tDescricao.getText(), tLocal.getText(), tData.getText(), 0);
				if (sucesso == true) {

					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Mensagem", 1);
				} else {
					JOptionPane.showMessageDialog(null, "Operação não realizada", "Mensagem - Alerta", 2);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"No campo confirmação digite:\n 1 - Para confirmar \nou \n 0 - Para cancelar",
						"Mensagem - Alerta", 2);
			}
		}

		// Botão atualizar item
		if (evento.getSource() == bAtualizarItem) {

			int opc;
			try {

				opc = Integer.parseInt(tStatus.getText());

				boolean sucesso = g.adicionaValor(tDescricao.getText(), tLocal.getText(), tData.getText(), opc);
				if (sucesso == true) {
					g.removerItem(codigo);
					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso"
							+ "\nAtenção, o codigo do item foi modificado para " + (codigo += 1), "Mensagem", 1);
				} else {
					JOptionPane.showMessageDialog(null, "Operação não realizada", "Mensagem - Alerta", 2);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"No campo confirmação digite:\n 1 - Para confirmar \nou \n 0 - Para cancelar",
						"Mensagem - Alerta", 2);
			}

		}

	}
}