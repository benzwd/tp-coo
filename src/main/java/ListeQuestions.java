
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeQuestions {
    private final List<Question> questions;

    /**
     * Constructeur qui charge les questions depuis un fichier CSV.
     * 
     * @param filePath Le chemin vers le fichier CSV contenant les questions.
     */
    public ListeQuestions(String filePath) {
        this.questions = new ArrayList<>();
        chargerQuestions(filePath);
    }

    /**
     * Verifie que la liste des questions est vide.
     * 
     * @return un booléen, si la liste est vide renvoie true, sinon renvoie false.
     */
    public boolean estVide(){
        return questions.isEmpty();
    }

    /**
     * Charge les questions depuis un fichier CSV et les ajoute à la liste des questions.
     * 
     * @param filePath Le chemin vers le fichier CSV.
     */
    private void chargerQuestions(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Fichier non trouvé : " + filePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] col = line.split(";");
                    String question = col[0];
                    String option1 = col[1];
                    String option2 = col[2];
                    String option3 = col[3];
                    String option4 = col[4];
                    String bonneReponse = col[5];
                    questions.add(new Question(question, option1, option2, option3, option4, bonneReponse));

                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
    }

    /**
     * Retourne une question aléatoire depuis la liste.
     * 
     * @return Une instance de `Question`, ou null si aucune question n'est disponible.
     */
    public Question getQuestionAleatoire() {
        if (questions.isEmpty()) {
            return null;
        }
        Collections.shuffle(questions);
        return questions.removeFirst(); // Retire et retourne la première question de la liste mélangée
    }


}
