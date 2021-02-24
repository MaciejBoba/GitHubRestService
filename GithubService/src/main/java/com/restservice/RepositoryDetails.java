package com.restservice;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RepositoryDetails {
    private String fullName;
    private Object description;
    private String cloneUrl;
    private int stars;
    private String createdAt;
}
