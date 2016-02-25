/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.turismo.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "avaliacao_usuario")
@NamedQueries({ @NamedQuery(name = "AvaliacaoUsuario.findAll", query = "SELECT a FROM AvaliacaoUsuario a"),
		@NamedQuery(name = "AvaliacaoUsuario.findById", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.id = :id"),
		@NamedQuery(name = "AvaliacaoUsuario.findByUsuarioId", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.usuario.id = :usuarioId"),
		@NamedQuery(name = "AvaliacaoUsuario.findByPontoInteresseId", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.pontoInteresse.id = :pontoInteresseId"),
		@NamedQuery(name = "AvaliacaoUsuario.findByComentario", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.comentario = :comentario"),
		@NamedQuery(name = "AvaliacaoUsuario.findByRecomendar", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.recomendar = :recomendar"),
		@NamedQuery(name = "AvaliacaoUsuario.findByDataAvaliacao", query = "SELECT a FROM AvaliacaoUsuario a WHERE a.dataAvaliacao = :dataAvaliacao") })
public class AvaliacaoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "comentario")
	private String comentario;
	@Column(name = "recomendar")
	private Boolean recomendar;
	@Basic(optional = false)
	@Column(name = "data_avaliacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAvaliacao;
	@JoinColumn(name = "ponto_interesse_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PontoInteresse pontoInteresse;
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "avaliacao_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Avaliacao avaliacao;

	public AvaliacaoUsuario() {
	}

	public AvaliacaoUsuario(Integer id, String comentario, Boolean recomendar, Date dataAvaliacao,
			PontoInteresse pontoInteresse, Usuario usuario, Avaliacao avaliacao) {
		this.id = id;
		this.comentario = comentario;
		this.recomendar = recomendar;
		this.dataAvaliacao = dataAvaliacao;
		this.pontoInteresse = pontoInteresse;
		this.usuario = usuario;
		this.avaliacao = avaliacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getRecomendar() {
		return recomendar;
	}

	public void setRecomendar(Boolean recomendar) {
		this.recomendar = recomendar;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public PontoInteresse getPontoInteresse() {
		return pontoInteresse;
	}

	public void setPontoInteresse(PontoInteresse pontoInteresse) {
		this.pontoInteresse = pontoInteresse;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AvaliacaoUsuario)) {
			return false;
		}
		AvaliacaoUsuario other = (AvaliacaoUsuario) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.turismo.core.model.AvaliacaoUsuario[ id=" + id + " ]";
	}

}
