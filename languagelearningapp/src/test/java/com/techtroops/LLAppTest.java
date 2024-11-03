package com.techtroops;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class LLAppTest {
    
    @Test
    public void testTesting(){
        assertTrue(true);
    }

    @Test
    public void testLoadingUsers(){
        assertTrue(DataLoader.loadUsers().size() >= 2);
    }

    @Test
    public void testLoadingLessons(){
        assertTrue(DataLoader.loadLessons().size() >= 2);
    }

    @Test
    public void testLoadingQuizzes(){
        assertTrue(DataLoader.loadQuizzes().size() >= 2);
    }

    // Also tests for existing user if the user doesn't already exist in the user list
    @Test
    public void testRegistering(){
        if (UserList.getInstance().locateUserByUsername("Test") == null){
            assertTrue(UserFactory.getInstance().register("testmail@.", "Test", "testword"));
        } else {
            assertFalse(UserFactory.getInstance().register("testmail@.", "Test", "testword"));
        }
    }

    // Logs in the user named MarketingEmma and checks if the user id is correct.
    @Test
    public void testLogIn(){
        assertEquals(UserFactory.getInstance().login("MarketingEmma", "BrandM@ster2024!").getUserID(),"test1");
    }

    @Test
    public void testLookUpLesson(){
        assertEquals(LessonFactory.getInstance().getLesson("Spanish", "Spanish1").getLessonID(), "Spanish1");
    }

    // Pulls a quiz MarketingEmma has already completed and checks if they can repeat it.
    @Test
    public void testStartQuiz(){
        QuizFactory quizFactory = QuizFactory.getInstance();
        ArrayList<Quiz> availableQuizzes = quizFactory.getAvailableQuizzes(UserList.getInstance().locateUserByUsername("MarketingEmma"), "Spanish");
        quizFactory.launchQuiz(availableQuizzes.get(0));
        assertEquals(QuizFactory.getInstance().getCurrentQuiz().getQuizID(), "Spanish1");
    }

    @Test
    public void testAnswerQuestion(){
        QuizFactory quizFactory = QuizFactory.getInstance();
        ArrayList<Quiz> availableQuizzes = quizFactory.getAvailableQuizzes(UserList.getInstance().locateUserByUsername("MarketingEmma"), "Spanish");
        quizFactory.launchQuiz(availableQuizzes.get(0));
        quizFactory.validateAnswer("como");
        assertEquals(quizFactory.getNumberOfCorrectAnswers(), 1);
    }

    @Test
    public void testGettingCorrectProgressTracker(){
        User user = UserList.getInstance().locateUserByUsername("MarketingEmma");
        assertEquals(user.getProgressTrackerByLanguage("Spanish").getLanguage(), "Spanish");
    }

    @Test
    public void testProgressTrackerProgress(){
        LanguageLearningApp.setCurrentUser(UserFactory.getInstance().login("MarketingEmma", "BrandM@ster2024!"));
        ProgressTracker pT = LanguageLearningApp.getCurrentUser().getProgressTrackerByLanguage("Spanish");
        pT.completeLesson(LessonList.getInstance().getLesson("Spanish2"));
        assertEquals((long) pT.getProgress(), (long) 1);;
    }
}
