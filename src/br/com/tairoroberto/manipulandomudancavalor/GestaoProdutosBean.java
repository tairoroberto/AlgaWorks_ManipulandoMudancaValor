package br.com.tairoroberto.manipulandomudancavalor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import br.com.tairoroberto.domain.Produto;



@ManagedBean
//@ApplicationScoped //todo mundo  que acessa ra pagina poderá ver os dados
@SessionScoped  //apenas o proprio usuaro exergar seus dados
//@ViewScoped  //apenas o proprio usuaro exergar seus dados, porém so enquanto a tela esta aberta
//@RequestScoped //os dados não são a guardados, a cada nova requisição os dados são deletados
//@NoneScoped //Escopo sem tempo de vida, é chamado a cada nomponente da aplicação .obs: menos usado.
public class GestaoProdutosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2411889286587271915L;
	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;
	private Produto produto;
	private Produto produtoSelecionado;
	private String nomePesquisa;
	
	//construct
	public GestaoProdutosBean() {
		this.produtos = new ArrayList<Produto>();
		this.produtosFiltrados = new ArrayList<Produto>();
		this.produto = new  Produto();
	}
	
	//method to redirect link
	public String  obterAjuda() {
		if (produtos.isEmpty()) {
			return "AjudaGestaoProdutos?faces-redirect=true";
		}else{
			return "AjudaGestaoProdutosTelefone?faces-redirect=true";
		}
		
	}
	
	//method to verify if product was inserted
	public void  verificarInclusao(ActionEvent event) {
		System.out.println("Verificando...");
		if ("".equals(this.produto.getFabricante())) {
			this.produto.setFabricante("Sem Fabricante");
		}
	}
	
	//methid to delete product
	public void excluir() {
		this.produtos.remove(this.produtoSelecionado);
	}
	
		
	//method call when is change input value
	public void nomePesquisaAlterado(ValueChangeEvent event) {
		System.out.println("Valor atual: "+ this.nomePesquisa);
		System.out.println("Novo Valor: "+ event.getNewValue());
		
		//clear list
		this.produtosFiltrados.clear();
		
		for (Produto produto : this.produtos) {
			if (produto.getNome() != null && produto.getNome().toLowerCase().startsWith(event.getNewValue().toString().toLowerCase())) {
				this.produtosFiltrados.add(produto);
			}
		}
	}
	
	/**
	 * @PostConstruct
	 * é utilizado para iniciar algum recurso que o managedBean necessita
	 */
	@PostConstruct
	public void inicializar() {
		System.out.println("Inicializou Bean");
	}
	
	
	/**
	 * @PreDestroy
	 * é utilizado para finalizar algum recurso que o managedBean necessita
	 */
	@PreDestroy
	public void finalizar() {
		System.out.println("Finalizou Bean");
	}
	
	public void incluir() {
		System.out.println("Incluindo...");
		this.produtos.add(this.produto);
		this.produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	
	
	
	
}
