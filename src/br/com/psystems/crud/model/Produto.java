/**
 * 
 */
package br.com.psystems.crud.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.psystems.crud.base.BaseEntity;


/**
 * @author rafael.saldanha
 *
 */
public class Produto implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048749574413769644L;

	private Long id;
	private Long fornecedorId;
	private String nome;
	private String cor;
	private Integer referencia;
	private String lote;
	private Double quantidade;
	private Double valorDeCompra;
	private Double valorDeVenda;
	private String descricao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFornecedorId() {
		return fornecedorId;
	}
	
	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public Integer getReferencia() {
		return referencia;
	}
	
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}