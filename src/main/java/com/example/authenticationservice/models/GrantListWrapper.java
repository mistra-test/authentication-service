package com.example.authenticationservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class GrantListWrapper implements Serializable {
    private List<String> grantList;
}
