
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeQuestions {
    private List<Question> questions;

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
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] col = line.split(",");
                String question = col[0];
                String option1 = col[1];
                String option2 = col[2];
                String option3 = col[3];
                String option4 = col[4];
                String bonneReponse = col[5];
                questions.add(new Question(question, option1, option2, option3, option4, bonneReponse));
                
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
        return questions.remove(0); // Retire et retourne la première question de la liste mélangée
    }


}
