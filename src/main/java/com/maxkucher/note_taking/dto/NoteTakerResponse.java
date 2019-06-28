package com.maxkucher.note_taking.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class NoteTakerResponse {
    private boolean success = false;
    @NonNull
    private String message;
}
