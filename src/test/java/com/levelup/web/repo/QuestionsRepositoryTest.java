package com.levelup.web.repo;

import com.levelup.web.model.Question;
import com.levelup.web.model.User;
import com.levelup.tests.TestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class QuestionsRepositoryTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private QuestionsRepository questionsRepository;

    private Date date = new Date();
    private Date dateBefore = new Date(date.getTime() - 100000000);
    private Question testSaveQuestion;

    @Before
    public void setUp() throws Exception {
        User authorFirst = new User("loginFirst", "passFirst", false);
        User authorSecond = new User("loginSecond", "passSecond", false);
        Question questionFirst = new Question("TestTitleFirst", "TestBodyFirst");
        questionFirst.setCreated(date);
        Question questionSecond = new Question("TestTitleSecond ", "TestBodySecond");
        testSaveQuestion = new Question("testSaveTitleQuestion", "testSaveBodyQuestion");

        manager.getTransaction().begin();
        manager.persist(authorFirst);
        manager.persist(authorSecond);
        questionFirst.setAuthor(authorFirst);
        questionSecond.setAuthor(authorSecond);
        manager.persist(questionFirst);
        manager.persist(questionSecond);
        manager.getTransaction().commit();
    }

    @Test
    public void findAll() {
        assertEquals(questionsRepository.findAll().size(), 2);
    }

    @Test
    public void findByTitle() {
        assertNull(questionsRepository.findByTitle("WrongTitle"));
        Question foundQuestion = questionsRepository.findByTitle("TestTitleFirst");
        assertNotNull(foundQuestion);
        assertEquals("TestTitleFirst", foundQuestion.getTitle());
    }

    @Test
    public void findByAuthor() {
        List<Question> wrongAuthorList = questionsRepository.findByAuthorLogin("wrongAuthorLogin");
        assertEquals(0, wrongAuthorList.size());
        List<Question> foundAuthorsList = questionsRepository.findByAuthorLogin("loginFirst");
        assertEquals( "TestTitleFirst", foundAuthorsList.get(0).getTitle());
    }

    @Test
    public void testFindByDateBefore() {
        assertEquals(1, questionsRepository.findByCreatedIsLessThanEqual(date).size());
        assertEquals("TestTitleFirst", questionsRepository.findByCreatedIsLessThanEqual(date).get(0).getTitle());
        assertEquals(0, questionsRepository.findByCreatedIsLessThanEqual(dateBefore).size());
    }

    @Test
    public void saveQuestion() {
        questionsRepository.save(testSaveQuestion);
        Question found = questionsRepository.findByTitle("testSaveTitleQuestion");
        assertNotNull(found);
        assertEquals("testSaveTitleQuestion", found.getTitle());

    }
}