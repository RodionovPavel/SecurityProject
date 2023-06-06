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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "draw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Draw {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title_draw", nullable = false, length = 40)
    private String title;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "image_preview", length = 50)
    private String imagePreview;

    @CreationTimestamp
    @Column(name = "create_ts", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "draw_ts", nullable = false)
    private LocalDateTime drawDate;

    @ManyToMany(mappedBy = "draws", fetch = FetchType.EAGER)
    private Set<User> clients;

    @OneToMany(mappedBy="draw",  fetch = FetchType.EAGER)
    private Set<WinPrize> prizes;

}
