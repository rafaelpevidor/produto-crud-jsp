/**
 * 
 */
package br.com.psystems.crud.model.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @author rafael.saldanha
 *
 */
public class Produto implements EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048749574413769644L;

	private Integer id;

	private Integer fornecedorId;

	private Fornecedor fornecedor;

	private String nome;
	
	private String lote;

	private Double quantidade;

	private Double valorDeCompra;

	private Double valorDeVenda;

	private String descricao;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getFornecedorId() {
		return fornecedorId;
	}



	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}



	public Fornecedor getFornecedor() {
		return fornecedor;
	}



	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Double getValorDeCompra() {
		return valorDeCompra;
	}



	public void setValorDeCompra(Double valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}



	public Double getValorDeVenda() {
		return valorDeVenda;
	}



	public void setValorDeVenda(Double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}



	public String getLote() {
		return lote;
	}



	public void setLote(String lote) {
		this.lote = lote;
	}



	public Double getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result
				+ ((fornecedorId == null) ? 0 : fornecedorId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lote == null) ? 0 : lote.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result
				+ ((valorDeCompra == null) ? 0 : valorDeCompra.hashCode());
		result = prime * result
				+ ((valorDeVenda == null) ? 0 : valorDeVenda.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (fornecedorId == null) {
			if (other.fornecedorId != null)
				return false;
		} else if (!fornecedorId.equals(other.fornecedorId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lote == null) {
			if (other.lote != null)
				return false;
		} else if (!lote.equals(other.lote))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valorDeCompra == null) {
			if (other.valorDeCompra != null)
				return false;
		} else if (!valorDeCompra.equals(other.valorDeCompra))
			return false;
		if (valorDeVenda == null) {
			if (other.valorDeVenda != null)
				return false;
		} else if (!valorDeVenda.equals(other.valorDeVenda))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}