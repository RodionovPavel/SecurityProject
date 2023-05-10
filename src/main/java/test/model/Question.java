package test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question16")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "title_question")
    private String titleQuestion;

    @Column(name = "question", updatable = false, nullable = false, length = 4000)
    private String question;

    @Column(name = "answer1", updatable = false, nullable = false, length = 2000)
    private String answer1;

    @Column(name = "answer2", updatable = false, nullable = false, length = 2000)
    private String answer2;

    @Column(name = "right_answer", updatable = false, nullable = false, length = 2000)
    private String rightAnswer;

    @Column(name = "if_right_answer", updatable = false, nullable = false, length = 2000)
    private String ifRightAnswer;

    @Column(name = "if_wrong_answer", updatable = false, nullable = false, length = 2000)
    private String ifWrongAnswer;
}
