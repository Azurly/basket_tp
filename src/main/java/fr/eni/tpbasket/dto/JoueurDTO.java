package fr.eni.tpbasket.dto;

import fr.eni.tpbasket.bo.Equipe;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record JoueurDTO(@NotNull Integer id,
                        @NotNull @Size(min = 1, max = 30) String nom,
                        @NotNull @Size(min = 1, max = 30) String prenom,
                        @Email String email,
                        @NotNull Equipe nEquipe) {
}
