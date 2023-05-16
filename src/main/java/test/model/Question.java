package test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question")
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

    @Column(name = "title_question", nullable = false, length = 300)
    private String titleQuestion;

    @Column(name = "question", nullable = false, length = 4000)
    private String question;

    @Column(name = "right_answer", nullable = false, length = 2000)
    private String rightAnswer;

    @Column(name = "if_right_answer", nullable = false, length = 2000)
    private String ifRightAnswer;

    @Column(name = "if_wrong_answer", nullable = false, length = 2000)
    private String ifWrongAnswer;
}
