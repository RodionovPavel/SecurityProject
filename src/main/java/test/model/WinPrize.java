package test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "win_prize")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinPrize {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title_draw", nullable = false, length = 40)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "place", nullable = false)
    private Integer place;

    @CreationTimestamp
    @Column(name = "create_ts", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="draw_id")
    private Draw draw;

    @ManyToOne
    @JoinColumn(name="client_id")
    private User client;

}
