package domain.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="tb_fiscalizacao")
public class Fiscalizacao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "razaoSocial")
	private String razaoSocial;
	@Column(name = "logadouro")
	private String logadouro;
	@Column(name = "cep")
	private String cep;
	@ManyToOne
	@JoinColumn(name="fiscal1Id")
	private Fiscal fiscal1;
	@ManyToOne
	@JoinColumn(name="fiscal2Id")
	private Fiscal fiscal2;
	@OneToMany(mappedBy="fiscalizacao")
	private List<Ocorrencia> ocorrencias;
	@ManyToOne
    @JoinColumn(name="ufId")
	private Uf uf;
	@ManyToOne
    @JoinColumn(name="bairroId")
	private Bairro bairro;
	@ManyToOne
    @JoinColumn(name="CidadeId")
	private Cidade cidade;
	@ManyToOne
    @JoinColumn(name="EmpresaId")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
//		this.razaoSocial = empresa.getRazaoSocial();
//		this.logadouro = empresa.getLogadouro();
//		this.cep = empresa.getCep();
//		this.bairro = empresa.getBairro();
//		this.cidade = empresa.getCidade();
//		this.uf = empresa.getUf();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Fiscalizacao other = (Fiscalizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getLogadouro() {
		return logadouro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Fiscal getFiscal1() {
		return fiscal1;
	}

	public void setFiscal1(Fiscal fiscal1) {
		this.fiscal1 = fiscal1;
	}

	public Fiscal getFiscal2() {
		return fiscal2;
	}

	public void setFiscal2(Fiscal fiscal2) {
		this.fiscal2 = fiscal2;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
}