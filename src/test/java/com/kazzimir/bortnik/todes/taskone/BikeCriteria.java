package com.kazzimir.bortnik.todes.taskone;

import com.kazzimir.bortnik.todes.taskone.repository.model.Contacts;
import com.kazzimir.bortnik.todes.taskone.repository.model.Gender;
import com.kazzimir.bortnik.todes.taskone.repository.model.Summary;
import com.kazzimir.bortnik.todes.taskone.repository.model.Technology;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.Query;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.impl.SqlQueryBuilderImpl;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils.PredicatesSQL;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BikeCriteria {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("bikeсriteria").createEntityManager();

    @Test
    public void VerificationOfTheGeneratedSqlAdCheckQueryParameters() {
        Query query = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from(Summary.class, "s")
                .join(PredicatesSQL.join(Gender.class, "g", "id_gender", "s.gender_id"))
                .where(PredicatesSQL.equal("id", 1))
                .where(PredicatesSQL.like("name", "G%"))
                .build();

        final String sql = "select * from Summary s join Gender g on g.id_gender=s.gender_id where id =:param0 and name like :param1 ";
        assertEquals(sql, query.getQuery());

        Map<String, Object> param = query.getQueryParameters();
        assertEquals(1, param.get("param0"));
        assertEquals("G%", param.get("param1"));
    }

    @Test
    public void ObtainingASummaryByLastSurnameEndsWith_ob_AndGenderMale() {
        String lineEnding = "ов";
        Query querySummary = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from(Summary.class, "s")
                .join(PredicatesSQL.join(Gender.class, "g", "id_gender", "s.gender_id"))
                .where(PredicatesSQL.equal("g.sex", "male"))
                .where(PredicatesSQL.like("surname", "%" + lineEnding))
                .build();

        List<Summary> summaryByQuery = getSummaryByQuery(querySummary);
        assertEquals(2, summaryByQuery.size());

        final String CheckLineEndingRegex = ".*" + lineEnding;
        summaryByQuery.forEach(summary -> {
            assertEquals("male", summary.getGender().getName());
            assertTrue(summary.getSurname().matches(CheckLineEndingRegex));
        });
        System.out.println(summaryByQuery);
    }

    @Test
    public void ObtainingASummaryByLastSurnameEndsWith_ob_AndGenderFemale() {
        Query querySummary = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from(Summary.class, "s")
                .join(PredicatesSQL.join(Gender.class, "g", "id_gender", "s.gender_id"))
                .where(PredicatesSQL.equal("g.sex", "female"))
                .where(PredicatesSQL.like("surname", "%ов"))
                .build();

        List<Summary> summaryByQuery = getSummaryByQuery(querySummary);
        assertEquals(0, summaryByQuery.size());
        System.out.println(summaryByQuery);
    }

    @Test
    public void ObtainingASummaryByFirstNameSurnamePatronymic() {
        Query querySummary = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from(Summary.class, "s")
                .join(PredicatesSQL.join(Gender.class, "g", "id_gender", "s.gender_id"))
                .where(PredicatesSQL.equal("name", "Мария"))
                .where(PredicatesSQL.equal("surname", "Морская"))
                .where(PredicatesSQL.equal("patronymic", "Васильевна"))
                .build();

        List<Summary> summaryByQuery = getSummaryByQuery(querySummary);

        assertEquals(1, summaryByQuery.size());
        Summary summaryActual = summaryByQuery.get(0);

        assertEquals("Мария", summaryActual.getName());
        assertEquals("Морская", summaryActual.getSurname());
        assertEquals("Васильевна", summaryActual.getPatronymic());
        System.out.println(summaryActual);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private List<Summary> getSummaryByQuery(Query querySummary) {
        javax.persistence.Query nativeQuerySummaryList = entityManager.createNativeQuery(querySummary.getQuery(), Summary.class);
        querySummary.getQueryParameters().forEach(nativeQuerySummaryList::setParameter);
        List<Summary> summaryList = (List<Summary>) nativeQuerySummaryList.getResultList();

        summaryList.forEach(summary -> {
            List<Contacts> contactList = getContactsByIdSummary(summary.getId());
            summary.setContacts(contactList);

            List<Technology> technologyList = getTechnologyByIdSummary(summary.getId());
            summary.setTechnology(technologyList);
        });
        return summaryList;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private List<Contacts> getContactsByIdSummary(int idSummary) {
        Query queryContacts = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from(Contacts.class, "c")
                .where(PredicatesSQL.equal("summary_id", idSummary))
                .build();
        javax.persistence.Query nativeQueryContactsList = entityManager.createNativeQuery(queryContacts.getQuery(), Contacts.class);
        queryContacts.getQueryParameters().forEach(nativeQueryContactsList::setParameter);
        return (List<Contacts>) nativeQueryContactsList.getResultList();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private List<Technology> getTechnologyByIdSummary(int idSummary) {
        Query queryContacts = SqlQueryBuilderImpl.newBuilder()
                .select()
                .from("Summary_Technology", "st")
                .join(PredicatesSQL.join(Technology.class, "t", "id", "st.technology_id"))
                .where(PredicatesSQL.equal("st.summary_id", idSummary))
                .build();
        javax.persistence.Query nativeQueryTechnologyList = entityManager.createNativeQuery(queryContacts.getQuery(), Technology.class);
        queryContacts.getQueryParameters().forEach(nativeQueryTechnologyList::setParameter);
        return (List<Technology>) nativeQueryTechnologyList.getResultList();
    }
}
