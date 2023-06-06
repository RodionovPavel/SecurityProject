package test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EventResponse {

    private UUID id;

    private String title;

    private String description;

    private String imagePreview;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime drawDate;

    private List<ClientResponse> clients;

    private List<PrizeResponse> prizes;
}
