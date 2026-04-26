package com.project.videorental.Exchange.VideoDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoDTO {
    @NotBlank
    private String title;
    private String director;
    private String genre;
    private boolean isAvailableForRent;
}
