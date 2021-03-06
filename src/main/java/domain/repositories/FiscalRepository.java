package domain.repositories;

import domain.models.Fiscal;
import domain.models.Fiscal;
import infrastructure.persistence.DAO;
import infrastructure.persistence.Repository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class FiscalRepository implements Serializable, Repository<Fiscal> {

    private static final long serialVersionUID = 1L;

    private DAO<Fiscal> dao;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        this.dao = new DAO<Fiscal>(this.em, Fiscal.class);
    }

    public void adiciona(Fiscal fiscal) {
        dao.adiciona(fiscal);
    }

    public Fiscal buscaPorId(Long id){
        return dao.buscaPorId(id);
    }

    public void atualiza(Fiscal fiscal) {
        dao.atualiza(fiscal);
    }

    public void remove(Fiscal fiscal) {
        dao.remove(fiscal);
    }

    public List<Fiscal> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }

    public List<Fiscal> listaTodos() {
        return dao.listaTodos();
    }

    public List<Fiscal> pesquisar(String textoDePesquisa) {
        String jpqlFiscal = "select u from Fiscal u where u.nome like :pNome or u.cpf like :pNome";
        TypedQuery<Fiscal> queryFiscal = this.em.createQuery(jpqlFiscal, Fiscal.class);
        queryFiscal.setParameter("pNome", "%" + textoDePesquisa + "%");
        try {
            return queryFiscal.getResultList();
        } catch(NoResultException ex) {
            System.out.println(this.em);
        }
        return new ArrayList<>();
    }
}
