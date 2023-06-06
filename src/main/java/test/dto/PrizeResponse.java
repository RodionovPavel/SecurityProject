package test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrizeResponse {

    private UUID id;

    private String title;

    private String description;

    private Integer place;

    private UUID winnerId;

}
