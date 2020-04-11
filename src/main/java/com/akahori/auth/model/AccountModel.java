package com.akahori.auth.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class AccountModel implements Serializable {
    @Setter
    @Getter
    String username;

    @Setter
    @Getter
    String password;
}
