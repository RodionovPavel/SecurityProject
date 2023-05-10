package test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "result3")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "count_questions")
    private Integer countQuestions;

    @Column(name = "count_right_answers")
    private Integer countRightAnswers;
}
