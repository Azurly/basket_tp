package fr.eni.tpbasket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipeDTO(@NotBlank @Size(max = 30) String nom) {
}
