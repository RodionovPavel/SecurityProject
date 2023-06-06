package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotBlank
    private String title;

    private String description;

    private String imagePreview;

    @NotNull
    private LocalDateTime drawDate;

    private List<PrizeRequest> prizes;

}
