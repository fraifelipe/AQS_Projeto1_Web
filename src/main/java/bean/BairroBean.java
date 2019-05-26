package bean;

import domain.repositories.BairroRepository;
import domain.models.Bairro;
import domain.models.Status;
import infrastructure.tx.Transacional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class BairroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private BairroRepository bairroRepository;
	private Bairro bairro;
	private Bairro selected;
	private List<Bairro> bairros = new ArrayList<>();
	private Status status = Status.pesquisando;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public void solicitaIncluir() {
		this.bairro = new Bairro();
		status = Status.incluindo;
	}

	public void solicitaAlterar() {
		this.bairro = selected;
		status = Status.alterando;
	}

	public void pesquisar() {
		this.bairros = bairroRepository.pesquisar(this.nome);
		this.selected = null;
	}

	public void cancelar() {
		status = Status.pesquisando;
	}
	public boolean isMostraEdicao() {
		return (status == Status.incluindo) || (status == Status.alterando);
	}
	public boolean isIncluindo() {
		return (status == Status.incluindo);
	}
	public boolean isAlterando() {
		return (status == Status.alterando);
	}
	public boolean isMostraPesquisa() {
		return (status == Status.pesquisando);
	}
	public boolean isDesabilitaAlteracao() {
		return (selected == null);
	}
	public boolean isDesabilitaExclusao() {
		return (selected == null);
	}

	public Bairro getSelected() {
		return selected;
	}
	public void setSelected(Bairro selected) {
		if (selected!= null) {
			System.out.println(selected.getId());
		}
		this.selected = selected;
	}
	public List<Bairro> getBairros() {
		return bairros;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public List<Bairro> getTodos(){
		return this.bairroRepository.listaTodos();
	}

	@Transacional
	public void confirmaInclusao(){
		this.bairroRepository.adiciona(bairro);
		status = Status.pesquisando;
	}

	@Transacional
	public void confirmaAlteracao() {
		this.bairroRepository.atualiza(bairro);
		this.selected = null;
		status = Status.pesquisando;
	}

	@Transacional
	public void solicitaExcluir() {
		this.bairroRepository.remove(selected);
		this.selected = null;
	}

	@PostConstruct
	public void init() {
		this.bairros = bairroRepository.listaTodos();
		System.out.println("@PostConstruct BairroBean.init();");
	}
}
