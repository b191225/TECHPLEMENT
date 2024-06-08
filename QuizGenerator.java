import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGenerator {
    private List<Quiz> quizzes;
    private Scanner scanner;

    public QuizGenerator() {
        quizzes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Quiz Generator Application");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add questions to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    addQuestionsToQuiz();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createQuiz() {
        System.out.print("Enter the title of the quiz: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);
        quizzes.add(quiz);
        System.out.println("Quiz created successfully!");
    }

    private void addQuestionsToQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
        System.out.print("Choose a quiz to add questions to: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizIndex);
        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();

        System.out.println("Enter the options (comma separated): ");
        String[] optionsArray = scanner.nextLine().split(",");
        List<String> options = new ArrayList<>();
        for (String option : optionsArray) {
            options.add(option.trim());
        }

        System.out.print("Enter the index of the correct answer (1-based index): ");
        int correctAnswerIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        Question question = new Question(questionText, options, correctAnswerIndex);
        selectedQuiz.addQuestion(question);
        System.out.println("Question added successfully!");
    }

    private void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
        System.out.print("Choose a quiz to take: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizIndex);
        List<Question> questions = selectedQuiz.getQuestions();
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Your answer: ");
            int userAnswerIndex = scanner.nextInt() - 1;

            if (question.isCorrect(userAnswerIndex)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The correct answer is: " + options.get(question.getCorrectAnswerIndex()));
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizGenerator app = new QuizGenerator();
        app.run();
    }
}

    
        