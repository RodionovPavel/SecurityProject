package test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question12")
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
    private Integer titleQuestion;

    @Column(name = "question", updatable = false, nullable = false, length = 4000)
    private String question;

    @Column(name = "answer", updatable = false, nullable = false, length = 2000)
    private String answer;

    @Column(name = "if_right_answer", updatable = false, nullable = false, length = 2000)
    private String ifRightAnswer;

    @Column(name = "if_wrong_answer", updatable = false, nullable = false, length = 2000)
    private String ifWrongAnswer;
}
